import com.mingyan.smartClassroom.ChatService.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ChatServerServiceServer {
    private static final Logger logger = Logger.getLogger(ChatServerServiceServer.class.getName());

    private final int port;
    private final Server server;

    public ChatServerServiceServer(int port) throws IOException {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new ClassroomInteractionImpl())
                .build();
    }

    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ChatServerServiceServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));
    }

    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class ClassroomInteractionImpl extends ClassroomInteractionGrpc.ClassroomInteractionImplBase {
        @Override
        public StreamObserver<ClassroomMessage> liveSession(StreamObserver<ClassroomMessage> responseObserver) {
            return new StreamObserver<ClassroomMessage>() {
                @Override
                public void onNext(ClassroomMessage message) {
                    logger.info("Received message from " + message.getUser() + ": " + message.getMessage());
                    // Echo the message back to the client
                    responseObserver.onNext(ClassroomMessage.newBuilder()
                            .setUser("Server")
                            .setMessage("Echo from server: " + message.getMessage())
                            .setTimestamp(System.currentTimeMillis())
                            .build());
                }

                @Override
                public void onError(Throwable t) {
                    logger.warning("LiveSession encountered an error: " + t.getMessage());
                }

                @Override
                public void onCompleted() {
                    responseObserver.onCompleted();
                    logger.info("LiveSession completed.");
                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ChatServerServiceServer server = new ChatServerServiceServer(8080);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (IOException e) {
            logger.severe("Server failed to start: " + e.getMessage());
        }
    }
}
