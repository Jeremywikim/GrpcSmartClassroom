import com.mingyan.smartClassroom.attendanceTrackingService.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.*;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class AttendanceServerServiceServer extends StreamingServerServiceGrpc.StreamingServerServiceImplBase {

    /*
    * override send sendUnaryRequest to have some customized features
    * formattedDate is the time when client sends name to the server,
    * the server will respond '"Welcome, " + clientName + "! You checked at " + formattedDate'
    * also, the server saves the clientName, formattedDate information from each request.
     */
    @Override
    public void sendUnaryRequest(AttendanceRequest request, StreamObserver<AttendanceResponse> responseObserver) {
        String clientName = request.getName();
        // Get the current time
        LocalDateTime now = LocalDateTime.now();
        // Format it to a readable form
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        String message = "Welcome, " + clientName + "! You checked at " + formattedDate;
        AttendanceResponse response = AttendanceResponse.newBuilder()
                .setMessage(message)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();

        // Save to CSV
        saveAttendanceRecord(clientName, formattedDate);
    }


    /*
    * this is streamServerRequest
     */

    @Override
    public void streamServerRequest(StreamServerRequest request, StreamObserver<StreamServerResponse> responseObserver) {
        String serverName = request.getServerName();
        List<String> clientNames = new ArrayList<>();
        // Load client names from CSV, skipping the first line
        try (BufferedReader reader = new BufferedReader(new FileReader("attendance_records.csv"))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false; // Skip the first line (titles)
                    continue;
                }
                clientNames.add(line.split(",")[0]); // Assuming the name is the first column
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        AtomicBoolean finished = new AtomicBoolean(false);
        AtomicInteger nameCounter = new AtomicInteger(1); // Counter for names

        Runnable streamingTask = () -> {
            try {
                while (!Thread.currentThread().isInterrupted() && !finished.get()) {
                    if (clientNames.isEmpty()) {
                        responseObserver.onNext(StreamServerResponse.newBuilder()
                                .setMessage("finish!")
                                .build());
                        finished.set(true);
                    } else {
                        // Pick a random name from the list
                        int index = (int) (Math.random() * clientNames.size());
                        String name = clientNames.get(index);
                        clientNames.remove(index); // Remove the name to avoid resending

                        String message = "Message #" + nameCounter.getAndIncrement() + ": " + name +
                                " from " + serverName + ". Current time: " + LocalDateTime.now();
                        responseObserver.onNext(StreamServerResponse.newBuilder()
                                .setMessage(message)
                                .build());
                    }
                    Thread.sleep(5000); // Stream every 5 seconds
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                responseObserver.onCompleted();
            }
        };

        Thread streamingThread = new Thread(streamingTask);
        streamingThread.start();
    }







//    @Override
//    public void streamServerRequest(StreamServerRequest request, StreamObserver<StreamServerResponse> responseObserver) {
//        String serverName = request.getServerName();
//        Runnable streamingTask = () -> {
//            try {
//                while (!Thread.currentThread().isInterrupted()) {
//                    String message = "This is a message from the server: " + serverName + ". Current time: " + LocalDateTime.now();
//                    StreamServerResponse response = StreamServerResponse.newBuilder()
//                            .setMessage(message)
//                            .build();
//                    responseObserver.onNext(response);
//                    Thread.sleep(5000); // Stream every 5 seconds
//                }
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            } finally {
//                responseObserver.onCompleted();
//            }
//        };
//
//        Thread streamingThread = new Thread(streamingTask);
//        streamingThread.start();
//    }


    /*
    * The method appends each client's name and check-in time to a CSV file.
    * It uses BufferedWriter and handles potential I/O exceptions.
    * The method is marked synchronized to prevent concurrent modifications
    *  of the file from multiple threads, which might corrupt the file.
     */
    private synchronized void saveAttendanceRecord(String clientName, String date) {
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("attendance_records.csv"), StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            bw.write(clientName + "," + date);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Failed to write to CSV: " + e.getMessage());
        }
    }

    /*
    * initCSV()
    * When the server starts, it checks if the CSV file exists. If not, it creates the file
    * and writes a header row. This ensures that data fields are properly labeled and organized.
     */
    private static void initCSV() throws IOException {
        if (!Files.exists(Paths.get("attendance_records.csv"))) {
            try (BufferedWriter bw = Files.newBufferedWriter(Paths.get("attendance_records.csv"))) {
                bw.write("ClientName,FormattedDate");
                bw.newLine();
            }
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        // // Ensure the CSV file exists and has a header
        initCSV();
        AttendanceServerServiceServer server = new AttendanceServerServiceServer();
        Server grpcServer = ServerBuilder.forPort(8080)
                .addService(server)
                .build();

        grpcServer.start();
        System.out.println("Server started, listening on port 8080");

        // Graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down gRPC server");
            try {
                grpcServer.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));

        grpcServer.awaitTermination();
    }
}
