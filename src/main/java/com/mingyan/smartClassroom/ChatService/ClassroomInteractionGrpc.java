package com.mingyan.smartClassroom.ChatService;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.61.1)",
    comments = "Source: ChatServerService.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ClassroomInteractionGrpc {

  private ClassroomInteractionGrpc() {}

  public static final java.lang.String SERVICE_NAME = "com.mingyan.smartClassroom.ClassroomInteraction";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.mingyan.smartClassroom.ChatService.ClassroomMessage,
      com.mingyan.smartClassroom.ChatService.ClassroomMessage> getLiveSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "LiveSession",
      requestType = com.mingyan.smartClassroom.ChatService.ClassroomMessage.class,
      responseType = com.mingyan.smartClassroom.ChatService.ClassroomMessage.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.mingyan.smartClassroom.ChatService.ClassroomMessage,
      com.mingyan.smartClassroom.ChatService.ClassroomMessage> getLiveSessionMethod() {
    io.grpc.MethodDescriptor<com.mingyan.smartClassroom.ChatService.ClassroomMessage, com.mingyan.smartClassroom.ChatService.ClassroomMessage> getLiveSessionMethod;
    if ((getLiveSessionMethod = ClassroomInteractionGrpc.getLiveSessionMethod) == null) {
      synchronized (ClassroomInteractionGrpc.class) {
        if ((getLiveSessionMethod = ClassroomInteractionGrpc.getLiveSessionMethod) == null) {
          ClassroomInteractionGrpc.getLiveSessionMethod = getLiveSessionMethod =
              io.grpc.MethodDescriptor.<com.mingyan.smartClassroom.ChatService.ClassroomMessage, com.mingyan.smartClassroom.ChatService.ClassroomMessage>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "LiveSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.ChatService.ClassroomMessage.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.mingyan.smartClassroom.ChatService.ClassroomMessage.getDefaultInstance()))
              .setSchemaDescriptor(new ClassroomInteractionMethodDescriptorSupplier("LiveSession"))
              .build();
        }
      }
    }
    return getLiveSessionMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClassroomInteractionStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClassroomInteractionStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClassroomInteractionStub>() {
        @java.lang.Override
        public ClassroomInteractionStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClassroomInteractionStub(channel, callOptions);
        }
      };
    return ClassroomInteractionStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClassroomInteractionBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClassroomInteractionBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClassroomInteractionBlockingStub>() {
        @java.lang.Override
        public ClassroomInteractionBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClassroomInteractionBlockingStub(channel, callOptions);
        }
      };
    return ClassroomInteractionBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClassroomInteractionFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClassroomInteractionFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClassroomInteractionFutureStub>() {
        @java.lang.Override
        public ClassroomInteractionFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClassroomInteractionFutureStub(channel, callOptions);
        }
      };
    return ClassroomInteractionFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * Streams messages between students and teacher
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.ChatService.ClassroomMessage> liveSession(
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.ChatService.ClassroomMessage> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getLiveSessionMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ClassroomInteraction.
   */
  public static abstract class ClassroomInteractionImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ClassroomInteractionGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ClassroomInteraction.
   */
  public static final class ClassroomInteractionStub
      extends io.grpc.stub.AbstractAsyncStub<ClassroomInteractionStub> {
    private ClassroomInteractionStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClassroomInteractionStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClassroomInteractionStub(channel, callOptions);
    }

    /**
     * <pre>
     * Streams messages between students and teacher
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.ChatService.ClassroomMessage> liveSession(
        io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.ChatService.ClassroomMessage> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getLiveSessionMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ClassroomInteraction.
   */
  public static final class ClassroomInteractionBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ClassroomInteractionBlockingStub> {
    private ClassroomInteractionBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClassroomInteractionBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClassroomInteractionBlockingStub(channel, callOptions);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ClassroomInteraction.
   */
  public static final class ClassroomInteractionFutureStub
      extends io.grpc.stub.AbstractFutureStub<ClassroomInteractionFutureStub> {
    private ClassroomInteractionFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClassroomInteractionFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClassroomInteractionFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_LIVE_SESSION = 0;

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
        case METHODID_LIVE_SESSION:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.liveSession(
              (io.grpc.stub.StreamObserver<com.mingyan.smartClassroom.ChatService.ClassroomMessage>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getLiveSessionMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.mingyan.smartClassroom.ChatService.ClassroomMessage,
              com.mingyan.smartClassroom.ChatService.ClassroomMessage>(
                service, METHODID_LIVE_SESSION)))
        .build();
  }

  private static abstract class ClassroomInteractionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClassroomInteractionBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.mingyan.smartClassroom.ChatService.ChatServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClassroomInteraction");
    }
  }

  private static final class ClassroomInteractionFileDescriptorSupplier
      extends ClassroomInteractionBaseDescriptorSupplier {
    ClassroomInteractionFileDescriptorSupplier() {}
  }

  private static final class ClassroomInteractionMethodDescriptorSupplier
      extends ClassroomInteractionBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ClassroomInteractionMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ClassroomInteractionGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClassroomInteractionFileDescriptorSupplier())
              .addMethod(getLiveSessionMethod())
              .build();
        }
      }
    }
    return result;
  }
}
