package io.github.mojtabaj.header;

import io.grpc.*;

import java.util.Objects;
import java.util.logging.Logger;

/**
 * interceptor to handle server header.
 * used for api key
 */
public class HeaderServerInterceptor implements ServerInterceptor {

  private static final Logger logger = Logger.getLogger(HeaderServerInterceptor.class.getName());
  private static final Metadata.Key<String> API_KEY = Metadata.Key.of(Constants.API_KEY, Metadata.ASCII_STRING_MARSHALLER);

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

  private boolean isValid(String apiKey){
    return Objects.nonNull(apiKey) && apiKey.equals(Constants.API_SECRET);
  }


}
