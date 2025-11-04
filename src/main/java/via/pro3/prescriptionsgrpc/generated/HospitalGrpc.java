package via.pro3.prescriptionsgrpc.generated;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.58.0)",
    comments = "Source: prescriptions.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HospitalGrpc {

  private HospitalGrpc() {}

  public static final java.lang.String SERVICE_NAME = "prescriptions.Hospital";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest,
      via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply> getGetPrescriptionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPrescriptions",
      requestType = via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest.class,
      responseType = via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest,
      via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply> getGetPrescriptionsMethod() {
    io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest, via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply> getGetPrescriptionsMethod;
    if ((getGetPrescriptionsMethod = HospitalGrpc.getGetPrescriptionsMethod) == null) {
      synchronized (HospitalGrpc.class) {
        if ((getGetPrescriptionsMethod = HospitalGrpc.getGetPrescriptionsMethod) == null) {
          HospitalGrpc.getGetPrescriptionsMethod = getGetPrescriptionsMethod =
              io.grpc.MethodDescriptor.<via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest, via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPrescriptions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply.getDefaultInstance()))
              .setSchemaDescriptor(new HospitalMethodDescriptorSupplier("GetPrescriptions"))
              .build();
        }
      }
    }
    return getGetPrescriptionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest,
      via.pro3.prescriptionsgrpc.generated.PrescriptionReply> getCreatePrescriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreatePrescription",
      requestType = via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest.class,
      responseType = via.pro3.prescriptionsgrpc.generated.PrescriptionReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest,
      via.pro3.prescriptionsgrpc.generated.PrescriptionReply> getCreatePrescriptionMethod() {
    io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest, via.pro3.prescriptionsgrpc.generated.PrescriptionReply> getCreatePrescriptionMethod;
    if ((getCreatePrescriptionMethod = HospitalGrpc.getCreatePrescriptionMethod) == null) {
      synchronized (HospitalGrpc.class) {
        if ((getCreatePrescriptionMethod = HospitalGrpc.getCreatePrescriptionMethod) == null) {
          HospitalGrpc.getCreatePrescriptionMethod = getCreatePrescriptionMethod =
              io.grpc.MethodDescriptor.<via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest, via.pro3.prescriptionsgrpc.generated.PrescriptionReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreatePrescription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.PrescriptionReply.getDefaultInstance()))
              .setSchemaDescriptor(new HospitalMethodDescriptorSupplier("CreatePrescription"))
              .build();
        }
      }
    }
    return getCreatePrescriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest,
      via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply> getCheckCredentialsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckCredentials",
      requestType = via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest.class,
      responseType = via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest,
      via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply> getCheckCredentialsMethod() {
    io.grpc.MethodDescriptor<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest, via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply> getCheckCredentialsMethod;
    if ((getCheckCredentialsMethod = HospitalGrpc.getCheckCredentialsMethod) == null) {
      synchronized (HospitalGrpc.class) {
        if ((getCheckCredentialsMethod = HospitalGrpc.getCheckCredentialsMethod) == null) {
          HospitalGrpc.getCheckCredentialsMethod = getCheckCredentialsMethod =
              io.grpc.MethodDescriptor.<via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest, via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckCredentials"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply.getDefaultInstance()))
              .setSchemaDescriptor(new HospitalMethodDescriptorSupplier("CheckCredentials"))
              .build();
        }
      }
    }
    return getCheckCredentialsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HospitalStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HospitalStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HospitalStub>() {
        @java.lang.Override
        public HospitalStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HospitalStub(channel, callOptions);
        }
      };
    return HospitalStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HospitalBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HospitalBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HospitalBlockingStub>() {
        @java.lang.Override
        public HospitalBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HospitalBlockingStub(channel, callOptions);
        }
      };
    return HospitalBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HospitalFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HospitalFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HospitalFutureStub>() {
        @java.lang.Override
        public HospitalFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HospitalFutureStub(channel, callOptions);
        }
      };
    return HospitalFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void getPrescriptions(via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPrescriptionsMethod(), responseObserver);
    }

    /**
     */
    default void createPrescription(via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.PrescriptionReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreatePrescriptionMethod(), responseObserver);
    }

    /**
     */
    default void checkCredentials(via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckCredentialsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Hospital.
   */
  public static abstract class HospitalImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return HospitalGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Hospital.
   */
  public static final class HospitalStub
      extends io.grpc.stub.AbstractAsyncStub<HospitalStub> {
    private HospitalStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HospitalStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HospitalStub(channel, callOptions);
    }

    /**
     */
    public void getPrescriptions(via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPrescriptionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createPrescription(via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.PrescriptionReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreatePrescriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkCredentials(via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request,
        io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckCredentialsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Hospital.
   */
  public static final class HospitalBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HospitalBlockingStub> {
    private HospitalBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HospitalBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HospitalBlockingStub(channel, callOptions);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply getPrescriptions(via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPrescriptionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.PrescriptionReply createPrescription(via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreatePrescriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply checkCredentials(via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckCredentialsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Hospital.
   */
  public static final class HospitalFutureStub
      extends io.grpc.stub.AbstractFutureStub<HospitalFutureStub> {
    private HospitalFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HospitalFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HospitalFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply> getPrescriptions(
        via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPrescriptionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.prescriptionsgrpc.generated.PrescriptionReply> createPrescription(
        via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreatePrescriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply> checkCredentials(
        via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckCredentialsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_PRESCRIPTIONS = 0;
  private static final int METHODID_CREATE_PRESCRIPTION = 1;
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
        case METHODID_GET_PRESCRIPTIONS:
          serviceImpl.getPrescriptions((via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply>) responseObserver);
          break;
        case METHODID_CREATE_PRESCRIPTION:
          serviceImpl.createPrescription((via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.PrescriptionReply>) responseObserver);
          break;
        case METHODID_CHECK_CREDENTIALS:
          serviceImpl.checkCredentials((via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest) request,
              (io.grpc.stub.StreamObserver<via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply>) responseObserver);
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
          getGetPrescriptionsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.prescriptionsgrpc.generated.PrescriptionsRequest,
              via.pro3.prescriptionsgrpc.generated.GetPrescriptionsReply>(
                service, METHODID_GET_PRESCRIPTIONS)))
        .addMethod(
          getCreatePrescriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.prescriptionsgrpc.generated.CreatePrescriptionRequest,
              via.pro3.prescriptionsgrpc.generated.PrescriptionReply>(
                service, METHODID_CREATE_PRESCRIPTION)))
        .addMethod(
          getCheckCredentialsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              via.pro3.prescriptionsgrpc.generated.CheckCredentialsRequest,
              via.pro3.prescriptionsgrpc.generated.CheckCredentialsReply>(
                service, METHODID_CHECK_CREDENTIALS)))
        .build();
  }

  private static abstract class HospitalBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HospitalBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return via.pro3.prescriptionsgrpc.generated.Prescriptions.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Hospital");
    }
  }

  private static final class HospitalFileDescriptorSupplier
      extends HospitalBaseDescriptorSupplier {
    HospitalFileDescriptorSupplier() {}
  }

  private static final class HospitalMethodDescriptorSupplier
      extends HospitalBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    HospitalMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (HospitalGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HospitalFileDescriptorSupplier())
              .addMethod(getGetPrescriptionsMethod())
              .addMethod(getCreatePrescriptionMethod())
              .addMethod(getCheckCredentialsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
