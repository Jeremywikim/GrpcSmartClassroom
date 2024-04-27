package com.mingyan.smartClassroom.CCTVService;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The greeter service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.1)",
    comments = "Source: CCTVServerService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class CCTVServerServiceGrpc {

  private CCTVServerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.mingyan.smartClassroom.CCTVServerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.mingyan.smartClassroom.CCTVService.VideoFrame,
      com.mingyan.smartClassroom.CCTVService.StreamVideoResponse> getStreamVideoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "StreamVideo",
      requestType = com.mingyan.smartClassroom.CCTVService.VideoFrame.class,
      responseType = com.mingyan.smartClassroom.CCTVService.StreamVideoResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.mingyan.smartClassroom.CCTVService.VideoFrame,
      com.mingyan.smartClassroom.CCTVService.StreamVideoResponse> getStreamVideoMethod() {
    io.grpc.MethodDescriptor<com.mingyan.smartClassroom.CCTVService.VideoFrame, com.mingyan.smartClassroom.CCTVService.StreamVideoResponse> getStreamVideoMethod;
    if ((getStreamVideoMethod = CCTVServerServiceGrpc.getStreamVideoMethod) == null) {
      synchronized (CCTVServerServiceGrpc.class) {
        if ((getStreamVideoMethod = CCTVServerServiceGrpc.getStreamVideoMethod) == null) {
          CCTVServerServiceGrpc.getStreamVideoMethod = getStreamVideoMethod =
              io.grpc.MethodDescriptor.<com.mingyan.smartClassroom.CCTVService.VideoFrame, com.mingyan.smartClassroom.CCTVService.StreamVideoResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "StreamVideo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.CCTVService.VideoFrame.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.CCTVService.StreamVideoResponse.getDefaultInstance()))
              .setSchemaDescriptor(new CCTVServerServiceMethodDescriptorSupplier("StreamVideo"))
              .build();
        }
      }
    }
    return getStreamVideoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static CCTVServerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CCTVServerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CCTVServerServiceStub>() {
        @java.lang.Override
        public CCTVServerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CCTVServerServiceStub(channel, callOptions);
        }
      };
    return CCTVServerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static CCTVServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CCTVServerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CCTVServerServiceBlockingStub>() {
        @java.lang.Override
        public CCTVServerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CCTVServerServiceBlockingStub(channel, callOptions);
        }
      };
    return CCTVServerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static CCTVServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<CCTVServerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<CCTVServerServiceFutureStub>() {
        @java.lang.Override
        public CCTVServerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new CCTVServerServiceFutureStub(channel, callOptions);
        }
      };
    return CCTVServerServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * Streams video frames from the client to the server
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.CCTVService.VideoFrame> streamVideo(
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.CCTVService.StreamVideoResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getStreamVideoMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service CCTVServerService.
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static abstract class CCTVServerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return CCTVServerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service CCTVServerService.
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CCTVServerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<CCTVServerServiceStub> {
    private CCTVServerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CCTVServerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CCTVServerServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Streams video frames from the client to the server
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.CCTVService.VideoFrame> streamVideo(
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.CCTVService.StreamVideoResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getStreamVideoMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service CCTVServerService.
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CCTVServerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<CCTVServerServiceBlockingStub> {
    private CCTVServerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CCTVServerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CCTVServerServiceBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service CCTVServerService.
   * <pre>
   * The greeter service definition.
   * </pre>
   */
  public static final class CCTVServerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<CCTVServerServiceFutureStub> {
    private CCTVServerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected CCTVServerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new CCTVServerServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_STREAM_VIDEO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STREAM_VIDEO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.streamVideo(
              (io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.CCTVService.StreamVideoResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getStreamVideoMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.mingyan.smartClassroom.CCTVService.VideoFrame,
              com.mingyan.smartClassroom.CCTVService.StreamVideoResponse>(
                service, METHODID_STREAM_VIDEO)))
        .build();
  }

  private static abstract class CCTVServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    CCTVServerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.mingyan.smartClassroom.CCTVService.CCTVServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("CCTVServerService");
    }
  }

  private static final class CCTVServerServiceFileDescriptorSupplier
      extends CCTVServerServiceBaseDescriptorSupplier {
    CCTVServerServiceFileDescriptorSupplier() {}
  }

  private static final class CCTVServerServiceMethodDescriptorSupplier
      extends CCTVServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    CCTVServerServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (CCTVServerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new CCTVServerServiceFileDescriptorSupplier())
              .addMethod(getStreamVideoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
