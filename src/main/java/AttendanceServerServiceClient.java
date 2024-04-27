import com.mingyan.smartClassroom.attendanceTrackingService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AttendanceServerServiceClient {

    private final ManagedChannel channel;
    private final StreamingServerServiceGrpc.StreamingServerServiceStub stub;

    public AttendanceServerServiceClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = StreamingServerServiceGrpc.newStub(channel);
    }

//    public void sendUnaryRequest(String name) {
//        AttendanceRequest request = AttendanceRequest.newBuilder()
//                .setName(name)
//                .build();
//        stub.sendUnaryRequest(request, new StreamObserver<AttendanceResponse>() {
//            @Override
//            public void onNext(AttendanceResponse response) {
//                System.out.println("Unary response from server: " + response.getMessage());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                System.err.println("Error in unary request: " + t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                System.out.println("Unary request completed");
//            }
//        });
//    }


    /*
    * sendUnaryRequest is used to send a Unary Request to server with two parameters.
    * name is to container of name of student, responseObserver is container to contain
    * the information of response of server
     */
    public void sendUnaryRequest(String name, StreamObserver<AttendanceResponse> responseObserver) {
        AttendanceRequest request = AttendanceRequest.newBuilder()
                .setName(name)
                .build();
        stub.sendUnaryRequest(request, responseObserver);  // Use the passed StreamObserver directly
    }


    /*
    * streamServerRequest is
     */
    public void streamServerRequest() {
        StreamObserver<StreamServerResponse> responseObserver = new StreamObserver<StreamServerResponse>() {
            @Override
            public void onNext(StreamServerResponse response) {
                System.out.println("Server message: " + response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                System.err.println("Error in server streaming: " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Server streaming completed");
            }
        };

        stub.streamServerRequest(StreamServerRequest.newBuilder().setServerName("Server01").build(), responseObserver);
    }


    /*
    * for the test (before I change the parameter of sendUnaryRequest ), this main function went well
    * the same for streamServerRequest().
     */
    public static void main(String[] args) {
        AttendanceServerServiceClient client = new AttendanceServerServiceClient("localhost", 8080);
//        client.sendUnaryRequest("Client01");
        client.streamServerRequest();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Press 'Q' to quit");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("Q")) {
                client.shutdown();
                break;
            }
        }
    }

    /*
    * shutdown is used to shut down the client, ACTUALLY, only between the test, I use it to
    * shut down the client (just run the main function), I keep it here maybe it is useful in future.
     */
    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error while shutting down client: " + e.getMessage());
        }
    }
}
