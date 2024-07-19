package io.github.mojtabaj.header;

import io.grpc.*;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * HeaderServerInterceptor is a gRPC server interceptor that checks for valid API key headers in incoming requests.
 * It intercepts each call, extracts the API key from the metadata, and validates it.
 * If the API key is valid, the call proceeds; otherwise, it is closed with an UNAUTHENTICATED status.
 */
public class HeaderServerInterceptor implements ServerInterceptor {

  private static final Logger logger = Logger.getLogger(HeaderServerInterceptor.class.getName());
  private static final Metadata.Key<String> API_KEY = Metadata.Key.of(Constants.API_KEY, Metadata.ASCII_STRING_MARSHALLER);


  /**
   * Intercepts the incoming gRPC call to check for valid API key in the metadata.
   *
   * @param serverCall the server call object.
   * @param metadata the metadata containing the API key.
   * @param serverCallHandler the handler for the server call.
   * @param <ReqT> the type of the request message.
   * @param <RespT> the type of the response message.
   * @return a listener for the incoming call.
   */
  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
          ServerCall<ReqT, RespT> serverCall,
          Metadata metadata,
          ServerCallHandler<ReqT, RespT> serverCallHandler
  ) {

    logger.info(String.format("Header received from client: %s", serverCallHandler));
    var apiKey = metadata.get(API_KEY);
    if(isValid(apiKey)){
      return serverCallHandler.startCall(serverCall, metadata);
    }
    serverCall.close(
            Status.UNAUTHENTICATED.withDescription("The request does not have valid authentication credentials for the operation."),
            metadata
    );
    return new ServerCall.Listener<ReqT>() {};
  }

  /**
   * Validates the provided API key.
   *
   * @param apiKey the API key to validate.
   * @return true if the API key is valid, false otherwise.
   */
  private boolean isValid(String apiKey){
    return Objects.nonNull(apiKey) && apiKey.equals(Constants.API_SECRET);
  }


}
