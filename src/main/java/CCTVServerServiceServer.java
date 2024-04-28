import com.mingyan.smartClassroom.CCTVService.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Server to interact with the CCTV client using gRPC.
 * Connection to the client,
 * Receiving video frames, and handling request.
 */
public class CCTVServerServiceServer {

    private static final Logger logger = Logger.getLogger(CCTVServerServiceServer.class.getName());

    private Server server;

    /**
     * Starts the gRPC server.
     * throws IOException if there is an error starting the server.
     */
    private void start() throws IOException {
        int port = 20000; // Initialize and start the server on the specified port
        server = ServerBuilder.forPort(port)
                .addService(new CCTVServerServiceImpl())
                .build()
                .start();
        logger.info("CCTV Server started, listening on " + port);

        // Add a shutdown hook to ensure clean shutdown including freeing port and other resources
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                CCTVServerServiceServer.this.stop();
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** server shut down");
        }));
    }

    /**
     * Stops the server and waits for it to shut down completely within a timeout.
     */
    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS); // 30s
        }
    }

    /**
     * Blocks until the server shuts down.
     * throws InterruptedException if the thread is interrupted while waiting.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Implements the server-side logic for the CCTV service.
     */
    class CCTVServerServiceImpl extends CCTVServerServiceGrpc.CCTVServerServiceImplBase {

        /**
         * Handles incoming video frame streams from clients.
         * return a stream observer to handle the client's video frame stream.
         */
        @Override
        public StreamObserver<VideoFrame> streamVideo(StreamObserver<StreamVideoResponse> responseObserver) {
            return new StreamObserver<VideoFrame>() {
                @Override
                public void onNext(VideoFrame videoFrame) {
                    // Log each received video frame
                    logger.info("Received video frame with number: " + videoFrame.getNumber() + " at timestamp: " + videoFrame.getTimestamp());
                }

                @Override
                public void onError(Throwable t) {
                    // Log any errors encountered during the stream
                    logger.warning("StreamVideo encountered error: " + t);
                }

                @Override
                public void onCompleted() {
                    // Send a completion message back to the client
                    StreamVideoResponse response = StreamVideoResponse.newBuilder()
                            .setMessage("Video stream received successfully.")
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();

                    // Trigger server shutdown here
                    try {
                        CCTVServerServiceServer.this.stop();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
    }

    /**
     * Main method to run the server.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final CCTVServerServiceServer server = new CCTVServerServiceServer();
        server.start();
        server.blockUntilShutdown();
    }
}
