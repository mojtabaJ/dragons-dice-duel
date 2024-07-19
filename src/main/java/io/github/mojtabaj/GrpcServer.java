package io.github.mojtabaj;

import io.github.mojtabaj.header.HeaderServerInterceptor;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * GrpcServer sets up and manages the lifecycle of a gRPC server.
 * It handles server creation, starting, termination, and shutdown.
 */
public class GrpcServer {
    private static final Logger log = LoggerFactory.getLogger(GrpcServer.class);
    private final Server server;

    /**
     * Creates a new GrpcServer instance with a default port (6565) and specified services.
     *
     * @param services the gRPC services to add to the server.
     * @return a new GrpcServer instance.
     */
    public static GrpcServer create(BindableService... services) {
        return create(6565, services);
    }

    private GrpcServer(Server server) {
        this.server = server;
    }

    /**
     * Creates a new GrpcServer instance with a specified port and services.
     *
     * @param port the port on which the server will listen for incoming connections.
     * @param services the gRPC services to add to the server.
     * @return a new GrpcServer instance.
     */
    public static GrpcServer create(int port, BindableService... services) {
        var builder = ServerBuilder.forPort(port);
        Arrays.asList(services).forEach(service ->
                builder
                        .addService(service)
                        .intercept(new HeaderServerInterceptor())
        );
        return new GrpcServer(builder.build());
    }

    /**
     * Starts the gRPC server and logs the server's port and services.
     *
     * @return the current GrpcServer instance.
     * @throws RuntimeException if an I/O error occurs while starting the server.
     */
    public GrpcServer start() {
        List<String> services = server.getServices().stream()
                .map(m -> m.getServiceDescriptor().getName())
                .toList();
        try {
            server.start();
            log.info("Server started on port {}, services: {}", server.getPort(), services);
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Waits for the server to terminate.
     *
     * @throws RuntimeException if the server is interrupted while waiting for termination.
     */
    public void awaitTermination() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Shuts down the gRPC server and logs the shutdown event.
     */
    public void shutdown() {
        server.shutdown();
        log.info("Server shut down");
    }
}
