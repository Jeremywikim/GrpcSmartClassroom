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

    public void sendUnaryRequest(String name, StreamObserver<AttendanceResponse> responseObserver) {
        AttendanceRequest request = AttendanceRequest.newBuilder()
                .setName(name)
                .build();
        stub.sendUnaryRequest(request, responseObserver);  // Use the passed StreamObserver directly
    }



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

    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("Error while shutting down client: " + e.getMessage());
        }
    }
}
