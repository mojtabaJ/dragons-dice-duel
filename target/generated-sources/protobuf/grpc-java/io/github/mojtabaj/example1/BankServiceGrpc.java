package io.github.mojtabaj.example1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: example1/bank-service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class BankServiceGrpc {

  private BankServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "sec06.BankService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.github.mojtabaj.example1.BalanceCheckRequest,
      io.github.mojtabaj.example1.AccountBalance> getGetAccountBalanceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountBalance",
      requestType = io.github.mojtabaj.example1.BalanceCheckRequest.class,
      responseType = io.github.mojtabaj.example1.AccountBalance.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.github.mojtabaj.example1.BalanceCheckRequest,
      io.github.mojtabaj.example1.AccountBalance> getGetAccountBalanceMethod() {
    io.grpc.MethodDescriptor<io.github.mojtabaj.example1.BalanceCheckRequest, io.github.mojtabaj.example1.AccountBalance> getGetAccountBalanceMethod;
    if ((getGetAccountBalanceMethod = BankServiceGrpc.getGetAccountBalanceMethod) == null) {
      synchronized (BankServiceGrpc.class) {
        if ((getGetAccountBalanceMethod = BankServiceGrpc.getGetAccountBalanceMethod) == null) {
          BankServiceGrpc.getGetAccountBalanceMethod = getGetAccountBalanceMethod =
              io.grpc.MethodDescriptor.<io.github.mojtabaj.example1.BalanceCheckRequest, io.github.mojtabaj.example1.AccountBalance>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountBalance"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.BalanceCheckRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.AccountBalance.getDefaultInstance()))
              .setSchemaDescriptor(new BankServiceMethodDescriptorSupplier("GetAccountBalance"))
              .build();
        }
      }
    }
    return getGetAccountBalanceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      io.github.mojtabaj.example1.AccountListResponse> getGetAllAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAllAccounts",
      requestType = com.google.protobuf.Empty.class,
      responseType = io.github.mojtabaj.example1.AccountListResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      io.github.mojtabaj.example1.AccountListResponse> getGetAllAccountsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, io.github.mojtabaj.example1.AccountListResponse> getGetAllAccountsMethod;
    if ((getGetAllAccountsMethod = BankServiceGrpc.getGetAllAccountsMethod) == null) {
      synchronized (BankServiceGrpc.class) {
        if ((getGetAllAccountsMethod = BankServiceGrpc.getGetAllAccountsMethod) == null) {
          BankServiceGrpc.getGetAllAccountsMethod = getGetAllAccountsMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, io.github.mojtabaj.example1.AccountListResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAllAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.AccountListResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BankServiceMethodDescriptorSupplier("GetAllAccounts"))
              .build();
        }
      }
    }
    return getGetAllAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.mojtabaj.example1.WithdrawRequest,
      io.github.mojtabaj.example1.WithdrawResponse> getWithdrawMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Withdraw",
      requestType = io.github.mojtabaj.example1.WithdrawRequest.class,
      responseType = io.github.mojtabaj.example1.WithdrawResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.mojtabaj.example1.WithdrawRequest,
      io.github.mojtabaj.example1.WithdrawResponse> getWithdrawMethod() {
    io.grpc.MethodDescriptor<io.github.mojtabaj.example1.WithdrawRequest, io.github.mojtabaj.example1.WithdrawResponse> getWithdrawMethod;
    if ((getWithdrawMethod = BankServiceGrpc.getWithdrawMethod) == null) {
      synchronized (BankServiceGrpc.class) {
        if ((getWithdrawMethod = BankServiceGrpc.getWithdrawMethod) == null) {
          BankServiceGrpc.getWithdrawMethod = getWithdrawMethod =
              io.grpc.MethodDescriptor.<io.github.mojtabaj.example1.WithdrawRequest, io.github.mojtabaj.example1.WithdrawResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.WithdrawRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.WithdrawResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BankServiceMethodDescriptorSupplier("Withdraw"))
              .build();
        }
      }
    }
    return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.mojtabaj.example1.DepositRequest,
      io.github.mojtabaj.example1.AccountBalance> getDepositMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Deposit",
      requestType = io.github.mojtabaj.example1.DepositRequest.class,
      responseType = io.github.mojtabaj.example1.AccountBalance.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.mojtabaj.example1.DepositRequest,
      io.github.mojtabaj.example1.AccountBalance> getDepositMethod() {
    io.grpc.MethodDescriptor<io.github.mojtabaj.example1.DepositRequest, io.github.mojtabaj.example1.AccountBalance> getDepositMethod;
    if ((getDepositMethod = BankServiceGrpc.getDepositMethod) == null) {
      synchronized (BankServiceGrpc.class) {
        if ((getDepositMethod = BankServiceGrpc.getDepositMethod) == null) {
          BankServiceGrpc.getDepositMethod = getDepositMethod =
              io.grpc.MethodDescriptor.<io.github.mojtabaj.example1.DepositRequest, io.github.mojtabaj.example1.AccountBalance>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Deposit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.DepositRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.AccountBalance.getDefaultInstance()))
              .setSchemaDescriptor(new BankServiceMethodDescriptorSupplier("Deposit"))
              .build();
        }
      }
    }
    return getDepositMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.github.mojtabaj.example1.TransferRequest,
      io.github.mojtabaj.example1.TransferResponse> getTransferMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Transfer",
      requestType = io.github.mojtabaj.example1.TransferRequest.class,
      responseType = io.github.mojtabaj.example1.TransferResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<io.github.mojtabaj.example1.TransferRequest,
      io.github.mojtabaj.example1.TransferResponse> getTransferMethod() {
    io.grpc.MethodDescriptor<io.github.mojtabaj.example1.TransferRequest, io.github.mojtabaj.example1.TransferResponse> getTransferMethod;
    if ((getTransferMethod = BankServiceGrpc.getTransferMethod) == null) {
      synchronized (BankServiceGrpc.class) {
        if ((getTransferMethod = BankServiceGrpc.getTransferMethod) == null) {
          BankServiceGrpc.getTransferMethod = getTransferMethod =
              io.grpc.MethodDescriptor.<io.github.mojtabaj.example1.TransferRequest, io.github.mojtabaj.example1.TransferResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Transfer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.TransferRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.github.mojtabaj.example1.TransferResponse.getDefaultInstance()))
              .setSchemaDescriptor(new BankServiceMethodDescriptorSupplier("Transfer"))
              .build();
        }
      }
    }
    return getTransferMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BankServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BankServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BankServiceStub>() {
        @java.lang.Override
        public BankServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BankServiceStub(channel, callOptions);
        }
      };
    return BankServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BankServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BankServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BankServiceBlockingStub>() {
        @java.lang.Override
        public BankServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BankServiceBlockingStub(channel, callOptions);
        }
      };
    return BankServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BankServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<BankServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<BankServiceFutureStub>() {
        @java.lang.Override
        public BankServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new BankServiceFutureStub(channel, callOptions);
        }
      };
    return BankServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     * <pre>
     * unary
     * </pre>
     */
    default void getAccountBalance(io.github.mojtabaj.example1.BalanceCheckRequest request,
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountBalance> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAccountBalanceMethod(), responseObserver);
    }

    /**
     */
    default void getAllAccounts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountListResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetAllAccountsMethod(), responseObserver);
    }

    /**
     * <pre>
     *server streaming
     * </pre>
     */
    default void withdraw(io.github.mojtabaj.example1.WithdrawRequest request,
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.WithdrawResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     *client streaming
     * </pre>
     */
    default io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.DepositRequest> deposit(
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountBalance> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getDepositMethod(), responseObserver);
    }

    /**
     * <pre>
     *bi-directional
     * </pre>
     */
    default io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.TransferRequest> transfer(
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.TransferResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getTransferMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service BankService.
   */
  public static abstract class BankServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return BankServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service BankService.
   */
  public static final class BankServiceStub
      extends io.grpc.stub.AbstractAsyncStub<BankServiceStub> {
    private BankServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BankServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary
     * </pre>
     */
    public void getAccountBalance(io.github.mojtabaj.example1.BalanceCheckRequest request,
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountBalance> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAccountBalanceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAllAccounts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountListResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetAllAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *server streaming
     * </pre>
     */
    public void withdraw(io.github.mojtabaj.example1.WithdrawRequest request,
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.WithdrawResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *client streaming
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.DepositRequest> deposit(
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountBalance> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getDepositMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     *bi-directional
     * </pre>
     */
    public io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.TransferRequest> transfer(
        io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.TransferResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getTransferMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service BankService.
   */
  public static final class BankServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<BankServiceBlockingStub> {
    private BankServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BankServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary
     * </pre>
     */
    public io.github.mojtabaj.example1.AccountBalance getAccountBalance(io.github.mojtabaj.example1.BalanceCheckRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAccountBalanceMethod(), getCallOptions(), request);
    }

    /**
     */
    public io.github.mojtabaj.example1.AccountListResponse getAllAccounts(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetAllAccountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *server streaming
     * </pre>
     */
    public java.util.Iterator<io.github.mojtabaj.example1.WithdrawResponse> withdraw(
        io.github.mojtabaj.example1.WithdrawRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service BankService.
   */
  public static final class BankServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<BankServiceFutureStub> {
    private BankServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BankServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new BankServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * unary
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.mojtabaj.example1.AccountBalance> getAccountBalance(
        io.github.mojtabaj.example1.BalanceCheckRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAccountBalanceMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.github.mojtabaj.example1.AccountListResponse> getAllAccounts(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetAllAccountsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ACCOUNT_BALANCE = 0;
  private static final int METHODID_GET_ALL_ACCOUNTS = 1;
  private static final int METHODID_WITHDRAW = 2;
  private static final int METHODID_DEPOSIT = 3;
  private static final int METHODID_TRANSFER = 4;

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
        case METHODID_GET_ACCOUNT_BALANCE:
          serviceImpl.getAccountBalance((io.github.mojtabaj.example1.BalanceCheckRequest) request,
              (io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountBalance>) responseObserver);
          break;
        case METHODID_GET_ALL_ACCOUNTS:
          serviceImpl.getAllAccounts((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountListResponse>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((io.github.mojtabaj.example1.WithdrawRequest) request,
              (io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.WithdrawResponse>) responseObserver);
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
        case METHODID_DEPOSIT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.deposit(
              (io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.AccountBalance>) responseObserver);
        case METHODID_TRANSFER:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.transfer(
              (io.grpc.stub.StreamObserver<io.github.mojtabaj.example1.TransferResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGetAccountBalanceMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              io.github.mojtabaj.example1.BalanceCheckRequest,
              io.github.mojtabaj.example1.AccountBalance>(
                service, METHODID_GET_ACCOUNT_BALANCE)))
        .addMethod(
          getGetAllAccountsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              io.github.mojtabaj.example1.AccountListResponse>(
                service, METHODID_GET_ALL_ACCOUNTS)))
        .addMethod(
          getWithdrawMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              io.github.mojtabaj.example1.WithdrawRequest,
              io.github.mojtabaj.example1.WithdrawResponse>(
                service, METHODID_WITHDRAW)))
        .addMethod(
          getDepositMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              io.github.mojtabaj.example1.DepositRequest,
              io.github.mojtabaj.example1.AccountBalance>(
                service, METHODID_DEPOSIT)))
        .addMethod(
          getTransferMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              io.github.mojtabaj.example1.TransferRequest,
              io.github.mojtabaj.example1.TransferResponse>(
                service, METHODID_TRANSFER)))
        .build();
  }

  private static abstract class BankServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BankServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.github.mojtabaj.example1.BankServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BankService");
    }
  }

  private static final class BankServiceFileDescriptorSupplier
      extends BankServiceBaseDescriptorSupplier {
    BankServiceFileDescriptorSupplier() {}
  }

  private static final class BankServiceMethodDescriptorSupplier
      extends BankServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    BankServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (BankServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BankServiceFileDescriptorSupplier())
              .addMethod(getGetAccountBalanceMethod())
              .addMethod(getGetAllAccountsMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getDepositMethod())
              .addMethod(getTransferMethod())
              .build();
        }
      }
    }
    return result;
  }
}
