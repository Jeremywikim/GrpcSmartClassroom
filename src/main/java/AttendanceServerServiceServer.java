import com.mingyan.smartClassroom.attendanceTrackingService.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;


public class AttendanceServerServiceServer extends StreamingServerServiceGrpc.StreamingServerServiceImplBase {

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
    }

    @Override
    public void streamServerRequest(StreamServerRequest request, StreamObserver<StreamServerResponse> responseObserver) {
        String serverName = request.getServerName();
        Runnable streamingTask = () -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    String message = "This is a message from the server: " + serverName + ". Current time: " + LocalDateTime.now();
                    StreamServerResponse response = StreamServerResponse.newBuilder()
                            .setMessage(message)
                            .build();
                    responseObserver.onNext(response);
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

    public static void main(String[] args) throws IOException, InterruptedException {
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
