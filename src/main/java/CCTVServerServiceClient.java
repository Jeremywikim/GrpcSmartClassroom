import com.mingyan.smartClassroom.CCTVService.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Client to interact with the CCTV service using gRPC.
 * This class is responsible for establishing a connection to the server,
 * sending video frames, and handling server responses.
 */
public class CCTVServerServiceClient {
    private static final Logger logger = Logger.getLogger(CCTVServerServiceClient.class.getName());
    private final ManagedChannel channel; // Channel for making remote calls
    private final CCTVServerServiceGrpc.CCTVServerServiceStub asyncStub; // Asynchronous stub for making non-blocking calls

    /**
     * Constructs a client for accessing the server at the given host and port.
     */
    public CCTVServerServiceClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // Configure the channel to not use SSL/TLS to allow unencrypted communication
                .build());
    }

    /**
     * Constructs a client for accessing a pre-configured channel.
     */
    CCTVServerServiceClient(ManagedChannel channel) {
        this.channel = channel;
        asyncStub = CCTVServerServiceGrpc.newStub(channel);
    }

    /**
     * Shuts down the channel gracefully.
     */
    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Async client-side streaming call to send video frames to the server.
     * This method prepares a latch to handle the asynchronous completion of the video stream.
     */
    public void streamVideo() throws InterruptedException {
        final CountDownLatch finishLatch = new CountDownLatch(1);
        StreamObserver<StreamVideoResponse> responseObserver = new StreamObserver<StreamVideoResponse>() {
            @Override
            public void onNext(StreamVideoResponse response) {
                logger.info("Server response: " + response.getMessage());
            }

            @Override
            public void onError(Throwable t) {
                logger.warning("StreamVideo failed: " + t);
                finishLatch.countDown();
            }

            @Override
            public void onCompleted() {
                logger.info("Finished streaming video");
                finishLatch.countDown();
            }
        };

        StreamObserver<VideoFrame> requestObserver = asyncStub.streamVideo(responseObserver);
        try {
            // Simulate streaming video data
            for (int i = 0; i < 10; i++) {
                long timestamp = System.currentTimeMillis();
                VideoFrame videoFrame = VideoFrame.newBuilder()
                        .setNumber((int) (Math.random() * 100))
                        .setTimestamp(timestamp)
                        .build();
                requestObserver.onNext(videoFrame);
                // Sleep for demonstration purposes
                Thread.sleep(1000);
            }
        } catch (RuntimeException e) {
            requestObserver.onError(e);
            throw e;
        }
        // Mark the end of requests
        requestObserver.onCompleted();

        // Wait for the server to respond or error before finishing the client.
        finishLatch.await(1, TimeUnit.MINUTES);
    }

    /**
     * Main method to run the client.
     * It creates an instance of the client, starts video streaming, and finally shuts down the client.
     */
    public static void main(String[] args) throws InterruptedException {
        CCTVServerServiceClient client = new CCTVServerServiceClient("localhost", 20000);
        try {
            client.streamVideo();
        } finally {
            client.shutdown();
        }
    }
}
