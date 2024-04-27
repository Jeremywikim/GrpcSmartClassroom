package com.mingyan.smartClassroom.attendanceTrackingService;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.1)",
    comments = "Source: AttendanceServerService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class StreamingServerServiceGrpc {

  private StreamingServerServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.mingyan.smartClassroom.StreamingServerService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest,
      com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse> getSendUnaryRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendUnaryRequest",
      requestType = com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest.class,
      responseType = com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest,
      com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse> getSendUnaryRequestMethod() {
    io.grpc.MethodDescriptor<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest, com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse> getSendUnaryRequestMethod;
    if ((getSendUnaryRequestMethod = StreamingServerServiceGrpc.getSendUnaryRequestMethod) == null) {
      synchronized (StreamingServerServiceGrpc.class) {
        if ((getSendUnaryRequestMethod = StreamingServerServiceGrpc.getSendUnaryRequestMethod) == null) {
          StreamingServerServiceGrpc.getSendUnaryRequestMethod = getSendUnaryRequestMethod =
              io.grpc.MethodDescriptor.<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest, com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendUnaryRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StreamingServerServiceMethodDescriptorSupplier("sendUnaryRequest"))
              .build();
        }
      }
    }
    return getSendUnaryRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest,
      com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse> getStreamServerRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "streamServerRequest",
      requestType = com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest.class,
      responseType = com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest,
      com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse> getStreamServerRequestMethod() {
    io.grpc.MethodDescriptor<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest, com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse> getStreamServerRequestMethod;
    if ((getStreamServerRequestMethod = StreamingServerServiceGrpc.getStreamServerRequestMethod) == null) {
      synchronized (StreamingServerServiceGrpc.class) {
        if ((getStreamServerRequestMethod = StreamingServerServiceGrpc.getStreamServerRequestMethod) == null) {
          StreamingServerServiceGrpc.getStreamServerRequestMethod = getStreamServerRequestMethod =
              io.grpc.MethodDescriptor.<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest, com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "streamServerRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse.getDefaultInstance()))
              .setSchemaDescriptor(new StreamingServerServiceMethodDescriptorSupplier("streamServerRequest"))
              .build();
        }
      }
    }
    return getStreamServerRequestMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StreamingServerServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingServerServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingServerServiceStub>() {
        @java.lang.Override
        public StreamingServerServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingServerServiceStub(channel, callOptions);
        }
      };
    return StreamingServerServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StreamingServerServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingServerServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingServerServiceBlockingStub>() {
        @java.lang.Override
        public StreamingServerServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingServerServiceBlockingStub(channel, callOptions);
        }
      };
    return StreamingServerServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StreamingServerServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StreamingServerServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StreamingServerServiceFutureStub>() {
        @java.lang.Override
        public StreamingServerServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StreamingServerServiceFutureStub(channel, callOptions);
        }
      };
    return StreamingServerServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    default void sendUnaryRequest(com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest request,
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSendUnaryRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     * Server-side streaming RPC
     * </pre>
     */
    default void streamServerRequest(com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest request,
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getStreamServerRequestMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service StreamingServerService.
   */
  public static abstract class StreamingServerServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return StreamingServerServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service StreamingServerService.
   */
  public static final class StreamingServerServiceStub
      extends io.grpc.stub.AbstractAsyncStub<StreamingServerServiceStub> {
    private StreamingServerServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingServerServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingServerServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public void sendUnaryRequest(com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest request,
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSendUnaryRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Server-side streaming RPC
     * </pre>
     */
    public void streamServerRequest(com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest request,
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getStreamServerRequestMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service StreamingServerService.
   */
  public static final class StreamingServerServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<StreamingServerServiceBlockingStub> {
    private StreamingServerServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingServerServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingServerServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse sendUnaryRequest(com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSendUnaryRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Server-side streaming RPC
     * </pre>
     */
    public java.util.Iterator<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse> streamServerRequest(
        com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getStreamServerRequestMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service StreamingServerService.
   */
  public static final class StreamingServerServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<StreamingServerServiceFutureStub> {
    private StreamingServerServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StreamingServerServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StreamingServerServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Unary RPC
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse> sendUnaryRequest(
        com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSendUnaryRequestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_UNARY_REQUEST = 0;
  private static final int METHODID_STREAM_SERVER_REQUEST = 1;

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
        case METHODID_SEND_UNARY_REQUEST:
          serviceImpl.sendUnaryRequest((com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest) request,
              (io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse>) responseObserver);
          break;
        case METHODID_STREAM_SERVER_REQUEST:
          serviceImpl.streamServerRequest((com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest) request,
              (io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSendUnaryRequestMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.mingyan.smartClassroom.attendanceTrackingService.AttendanceRequest,
              com.mingyan.smartClassroom.attendanceTrackingService.AttendanceResponse>(
                service, METHODID_SEND_UNARY_REQUEST)))
        .addMethod(
          getStreamServerRequestMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.mingyan.smartClassroom.attendanceTrackingService.StreamServerRequest,
              com.mingyan.smartClassroom.attendanceTrackingService.StreamServerResponse>(
                service, METHODID_STREAM_SERVER_REQUEST)))
        .build();
  }

  private static abstract class StreamingServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StreamingServerServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.mingyan.smartClassroom.attendanceTrackingService.AttendanceTrackingServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StreamingServerService");
    }
  }

  private static final class StreamingServerServiceFileDescriptorSupplier
      extends StreamingServerServiceBaseDescriptorSupplier {
    StreamingServerServiceFileDescriptorSupplier() {}
  }

  private static final class StreamingServerServiceMethodDescriptorSupplier
      extends StreamingServerServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    StreamingServerServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (StreamingServerServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StreamingServerServiceFileDescriptorSupplier())
              .addMethod(getSendUnaryRequestMethod())
              .addMethod(getStreamServerRequestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
