import com.mingyan.smartClassroom.CCTVService.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.logging.Logger;

public class CCTVServerServiceServer {

    private static final Logger logger = Logger.getLogger(CCTVServerServiceServer.class.getName());

    private Server server;

    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 20000;
        server = ServerBuilder.forPort(port)
                .addService(new CCTVServerServiceImpl())
                .build()
                .start();
        logger.info("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            CCTVServerServiceServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    static class CCTVServerServiceImpl extends CCTVServerServiceGrpc.CCTVServerServiceImplBase {

        @Override
        public StreamObserver<VideoFrame> streamVideo(StreamObserver<StreamVideoResponse> responseObserver) {
            return new StreamObserver<VideoFrame>() {
                @Override
                public void onNext(VideoFrame videoFrame) {
                    logger.info("Received video frame with number: " + videoFrame.getNumber() + " at timestamp: " + videoFrame.getTimestamp());
                }

                @Override
                public void onError(Throwable t) {
                    logger.warning("StreamVideo encountered error: " + t);
                }

                @Override
                public void onCompleted() {
                    // Respond back that the stream was processed successfully
                    StreamVideoResponse response = StreamVideoResponse.newBuilder()
                            .setMessage("Video stream received successfully.")
                            .build();
                    responseObserver.onNext(response);
                    responseObserver.onCompleted();
                }
            };
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final CCTVServerServiceServer server = new CCTVServerServiceServer();
        server.start();
        server.blockUntilShutdown();
    }
}

