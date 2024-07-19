package io.github.mojtabaj;

import io.github.mojtabaj.service.DragonsDiceDuelService;

/**
 * DragonsDiceDuelServer is the entry point for starting the gRPC server for the Dragons' Dice Duel game.
 * It sets up and starts the gRPC server with the necessary game service and waits for its termination.
 */
public class DragonsDiceDuelServer {

    /**
     * The main method initializes and starts the gRPC server, then waits for the server to terminate.
     *
     * @param args command-line arguments (not used in this implementation).
     */
    public static void main(String[] args) {
        GrpcServer
                .create(new DragonsDiceDuelService()) // Create a new gRPC server with DragonsDiceDuelService.
                .start()                             // Start the gRPC server.
                .awaitTermination();                // Wait for the server to terminate.
    }
}
