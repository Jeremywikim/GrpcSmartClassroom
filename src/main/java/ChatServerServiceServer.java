import com.mingyan.smartClassroom.ChatService.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ChatServerServiceServer {
    private static final Logger logger = Logger.getLogger(ChatServerServiceServer.class.getName());
    private final int port;
    private final Server server;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /**
     * Constructs a new ChatServerServiceServer that listens on the given port.
     */
    public ChatServerServiceServer(int port) throws IOException {
        this.port = port;
        this.server = ServerBuilder.forPort(port)
                .addService(new ClassroomInteractionImpl())
                .build();
    }

    /**
     * Starts the server and logs its status.
     */
    public void start() throws IOException {
        server.start();
        logger.info("Chat Server started, listening on " + port + "\n");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                ChatServerServiceServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));
    }

    /**
     * Stops the server and the scheduler, ensuring all tasks are terminated gracefully.
     */
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            scheduler.shutdown();
        }
    }

    /**
     * Blocks until the server is terminated.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Implements the gRPC service for classroom interactions.
     */
    class ClassroomInteractionImpl extends ClassroomInteractionGrpc.ClassroomInteractionImplBase {

        /**
         * Receives messages from clients, logs them, and schedules a delayed echo response.
         */
        @Override
        public StreamObserver<ClassroomMessage> liveSession(StreamObserver<ClassroomMessage> responseObserver) {
            return new StreamObserver<ClassroomMessage>() {
                @Override
                public void onNext(ClassroomMessage message) {
                    logger.info("Received message from " + message.getUser() + ": " + message.getMessage());

                    // Schedule the response to be sent after 5 seconds
                    scheduler.schedule(() -> {
                        responseObserver.onNext(ClassroomMessage.newBuilder()
                                .setUser("Server")
                                .setMessage("Echo from server after delay: " + message.getMessage())
                                .setTimestamp(System.currentTimeMillis())
                                .build());
                    }, 5, TimeUnit.SECONDS);
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

    /**
     * Main method to run the chat server.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        ChatServerServiceServer server = new ChatServerServiceServer(10000);
        try {
            server.start();
            server.blockUntilShutdown();
        } catch (IOException e) {
            logger.severe("Server failed to start: " + e.getMessage());
        }
    }
}


