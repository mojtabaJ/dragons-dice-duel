package io.github.mojtabaj;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class GrpcServer {
    private static final Logger log = LoggerFactory.getLogger(GrpcServer.class);
    private final Server server;

    public static GrpcServer create(BindableService... services){
        return create(9090, services);
    }
    private GrpcServer(Server server) {
        this.server = server;
    }
    public static GrpcServer create(int port, BindableService... services) {
        var builder = ServerBuilder.forPort(port);
        Arrays.asList(services).forEach(builder::addService);
        return new GrpcServer(builder.build());
    }
    public GrpcServer start(){
        var services = server.getServices().stream().map(m->m.getServiceDescriptor()).map(m->m.getName()).toList();
        try {
            server.start();
            log.info("Server started on port {}, services: {}", server.getPort(), services) ;
            return this;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void awaitTermination() {
        try {
            server.awaitTermination();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        server.shutdown();
        log.info("Server shut down");
    }

}
