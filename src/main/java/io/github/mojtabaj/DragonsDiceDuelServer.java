package io.github.mojtabaj;

import io.github.mojtabaj.service.DragonsDiceDuelService;

public class DragonsDiceDuelServer {
    public static void main(String[] args) {
        GrpcServer
                .create(new DragonsDiceDuelService())
                .start()
                .awaitTermination();
    }
}
