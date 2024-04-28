import com.mingyan.smartClassroom.ChatService.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


import com.mingyan.smartClassroom.ChatService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

public class ChatServerServiceClient {
    private final ManagedChannel channel;
    private final ClassroomInteractionGrpc.ClassroomInteractionStub stub;

    public ChatServerServiceClient(String host, int port) {
        this.channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = ClassroomInteractionGrpc.newStub(channel);
    }

    public StreamObserver<ClassroomMessage> startChat(StreamObserver<ClassroomMessage> responseObserver) {
        return stub.liveSession(responseObserver);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
}





















//public class ChatServerServiceClient {
//    private static final Logger logger = Logger.getLogger(ChatServerServiceClient.class.getName());
//
//    private final ManagedChannel channel;
//    private final ClassroomInteractionGrpc.ClassroomInteractionStub asyncStub;
//
//    public ChatServerServiceClient(String host, int port) {
//        this.channel = ManagedChannelBuilder.forAddress(host, port)
//                .usePlaintext()
//                .build();
//        this.asyncStub = ClassroomInteractionGrpc.newStub(channel);
//    }
//
//    public void shutdown() throws InterruptedException {
//        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//    }
//
//    public void chat() {
//        StreamObserver<ClassroomMessage> requestObserver = asyncStub.liveSession(new StreamObserver<ClassroomMessage>() {
//            @Override
//            public void onNext(ClassroomMessage value) {
//                logger.info("Received: " + value.getMessage());
//            }
//
//            @Override
//            public void onError(Throwable t) {
//                logger.warning("Streaming Error: " + t.getMessage());
//            }
//
//            @Override
//            public void onCompleted() {
//                logger.info("Server completed the stream.");
//            }
//        });
//
//        try (Scanner scanner = new Scanner(System.in)) {
//            while (true) {
//                System.out.println("Enter a message (type 'quit' to exit): ");
//                String message = scanner.nextLine();
//                if ("quit".equalsIgnoreCase(message)) {
//                    break;
//                }
//
//                ClassroomMessage msg = ClassroomMessage.newBuilder()
//                        .setUser("student_id") // Change this to the appropriate user ID or "teacher"
//                        .setMessage(message)
//                        .setTimestamp(System.currentTimeMillis())
//                        .build();
//                requestObserver.onNext(msg);
//            }
//        } catch (RuntimeException e) {
//            requestObserver.onError(e);
//            throw e;
//        }
//
//        // Mark the end of requests
//        requestObserver.onCompleted();
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        ChatServerServiceClient client = new ChatServerServiceClient("localhost", 8082);
//        try {
//            client.chat();
//        } finally {
//            client.shutdown();
//        }
//    }
//}
