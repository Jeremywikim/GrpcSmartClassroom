import com.mingyan.smartClassroom.CCTVService.*;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class CCTVServerServiceClient {
    private static final Logger logger = Logger.getLogger(CCTVServerServiceClient.class.getName());
    private final ManagedChannel channel;
    private final CCTVServerServiceGrpc.CCTVServerServiceStub asyncStub;

    public CCTVServerServiceClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build());
    }

    CCTVServerServiceClient(ManagedChannel channel) {
        this.channel = channel;
        asyncStub = CCTVServerServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    /**
     * Async client-side streaming call to send video frames to the server.
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

        // Receiving happens asynchronously
        finishLatch.await(1, TimeUnit.MINUTES);
    }

    public static void main(String[] args) throws InterruptedException {
        CCTVServerServiceClient client = new CCTVServerServiceClient("localhost", 50051);
        try {
            client.streamVideo();
        } finally {
            client.shutdown();
        }
    }
}
