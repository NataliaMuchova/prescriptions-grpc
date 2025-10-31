package via.pro3.prescriptionsgrpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class PrescriptionServiceGrpc {

  private PrescriptionServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "prescriptions.PrescriptionService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest,
      via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse> getCreatePrescriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePrescription",
      requestType = via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest.class,
      responseType = via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest,
      via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse> getCreatePrescriptionMethod() {
    io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest, via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse> getCreatePrescriptionMethod;
    if ((getCreatePrescriptionMethod = PrescriptionServiceGrpc.getCreatePrescriptionMethod) == null) {
      synchronized (PrescriptionServiceGrpc.class) {
        if ((getCreatePrescriptionMethod = PrescriptionServiceGrpc.getCreatePrescriptionMethod) == null) {
          PrescriptionServiceGrpc.getCreatePrescriptionMethod = getCreatePrescriptionMethod =
              io.grpc.MethodDescriptor.<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest, via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePrescription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PrescriptionServiceMethodDescriptorSupplier("CreatePrescription"))
              .build();
        }
      }
    }
    return getCreatePrescriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest,
      via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse> getGetPrescriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPrescription",
      requestType = via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest.class,
      responseType = via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest,
      via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse> getGetPrescriptionMethod() {
    io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest, via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse> getGetPrescriptionMethod;
    if ((getGetPrescriptionMethod = PrescriptionServiceGrpc.getGetPrescriptionMethod) == null) {
      synchronized (PrescriptionServiceGrpc.class) {
        if ((getGetPrescriptionMethod = PrescriptionServiceGrpc.getGetPrescriptionMethod) == null) {
          PrescriptionServiceGrpc.getGetPrescriptionMethod = getGetPrescriptionMethod =
              io.grpc.MethodDescriptor.<via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest, via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPrescription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PrescriptionServiceMethodDescriptorSupplier("GetPrescription"))
              .build();
        }
      }
    }
    return getGetPrescriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest,
      via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse> getCheckCredentialsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckCredentials",
      requestType = via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest.class,
      responseType = via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest,
      via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse> getCheckCredentialsMethod() {
    io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest, via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse> getCheckCredentialsMethod;
    if ((getCheckCredentialsMethod = PrescriptionServiceGrpc.getCheckCredentialsMethod) == null) {
      synchronized (PrescriptionServiceGrpc.class) {
        if ((getCheckCredentialsMethod = PrescriptionServiceGrpc.getCheckCredentialsMethod) == null) {
          PrescriptionServiceGrpc.getCheckCredentialsMethod = getCheckCredentialsMethod =
              io.grpc.MethodDescriptor.<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest, via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckCredentials"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse.getDefaultInstance()))
              .setSchemaDescriptor(new PrescriptionServiceMethodDescriptorSupplier("CheckCredentials"))
              .build();
        }
      }
    }
    return getCheckCredentialsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PrescriptionServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceStub>() {
        @java.lang.Override
        public PrescriptionServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrescriptionServiceStub(channel, callOptions);
        }
      };
    return PrescriptionServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static PrescriptionServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceBlockingV2Stub>() {
        @java.lang.Override
        public PrescriptionServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrescriptionServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return PrescriptionServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PrescriptionServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceBlockingStub>() {
        @java.lang.Override
        public PrescriptionServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrescriptionServiceBlockingStub(channel, callOptions);
        }
      };
    return PrescriptionServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PrescriptionServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<PrescriptionServiceFutureStub>() {
        @java.lang.Override
        public PrescriptionServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new PrescriptionServiceFutureStub(channel, callOptions);
        }
      };
    return PrescriptionServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createPrescription(via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePrescriptionMethod(), responseObserver);
    }

    /**
     */
    default void getPrescription(via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPrescriptionMethod(), responseObserver);
    }

    /**
     */
    default void checkCredentials(via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckCredentialsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service PrescriptionService.
   */
  public static abstract class PrescriptionServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return PrescriptionServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service PrescriptionService.
   */
  public static final class PrescriptionServiceStub
      extends io.grpc.stub.AbstractAsyncStub<PrescriptionServiceStub> {
    private PrescriptionServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrescriptionServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrescriptionServiceStub(channel, callOptions);
    }

    /**
     */
    public void createPrescription(via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePrescriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPrescription(via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPrescriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkCredentials(via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckCredentialsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service PrescriptionService.
   */
  public static final class PrescriptionServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<PrescriptionServiceBlockingV2Stub> {
    private PrescriptionServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrescriptionServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrescriptionServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse createPrescription(via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreatePrescriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse getPrescription(via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetPrescriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse checkCredentials(via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCheckCredentialsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service PrescriptionService.
   */
  public static final class PrescriptionServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<PrescriptionServiceBlockingStub> {
    private PrescriptionServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrescriptionServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrescriptionServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse createPrescription(via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePrescriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse getPrescription(via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPrescriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse checkCredentials(via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckCredentialsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service PrescriptionService.
   */
  public static final class PrescriptionServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<PrescriptionServiceFutureStub> {
    private PrescriptionServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PrescriptionServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new PrescriptionServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse> createPrescription(
        via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePrescriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse> getPrescription(
        via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPrescriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse> checkCredentials(
        via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckCredentialsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_PRESCRIPTION = 0;
  private static final int METHODID_GET_PRESCRIPTION = 1;
  private static final int METHODID_CHECK_CREDENTIALS = 2;

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
        case METHODID_CREATE_PRESCRIPTION:
          serviceImpl.createPrescription((via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse>) responseObserver);
          break;
        case METHODID_GET_PRESCRIPTION:
          serviceImpl.getPrescription((via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse>) responseObserver);
          break;
        case METHODID_CHECK_CREDENTIALS:
          serviceImpl.checkCredentials((via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse>) responseObserver);
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
          getCreatePrescriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest,
              via.pro3.prescriptionsgrpc.generated.CreatePrescriptionResponse>(
                service, METHODID_CREATE_PRESCRIPTION)))
        .addMethod(
          getGetPrescriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.prescriptionsgrpc.generated.GetPrescriptionRequest,
              via.pro3.prescriptionsgrpc.generated.GetPrescriptionResponse>(
                service, METHODID_GET_PRESCRIPTION)))
        .addMethod(
          getCheckCredentialsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest,
              via.pro3.prescriptionsgrpc.generated.CheckCredentialsResponse>(
                service, METHODID_CHECK_CREDENTIALS)))
        .build();
  }

  private static abstract class PrescriptionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PrescriptionServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return via.pro3.prescriptionsgrpc.generated.Prescriptions.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PrescriptionService");
    }
  }

  private static final class PrescriptionServiceFileDescriptorSupplier
      extends PrescriptionServiceBaseDescriptorSupplier {
    PrescriptionServiceFileDescriptorSupplier() {}
  }

  private static final class PrescriptionServiceMethodDescriptorSupplier
      extends PrescriptionServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    PrescriptionServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (PrescriptionServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PrescriptionServiceFileDescriptorSupplier())
              .addMethod(getCreatePrescriptionMethod())
              .addMethod(getGetPrescriptionMethod())
              .addMethod(getCheckCredentialsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
