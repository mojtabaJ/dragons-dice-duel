package io.github.mojtabaj.service;

import io.github.mojtabaj.data.DragonsDiceDuelGrpc;
import io.github.mojtabaj.data.GameRequest;
import io.github.mojtabaj.data.GameResponse;
import io.github.mojtabaj.service.handler.GameRequestHandler;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

/**
 * DragonsDiceDuelService is the gRPC service implementation for the Dragons' Dice Duel game.
 * It handles incoming game requests and delegates them to the GameRequestHandler.
 */
public class DragonsDiceDuelService extends DragonsDiceDuelGrpc.DragonsDiceDuelImplBase {

    /**
     * Starts a new game session by creating a GameRequestHandler to manage the stream of game requests and responses.
     *
     * @param responseObserver the observer for sending game responses back to the client.
     * @return a new GameRequestHandler to handle the stream of game requests.
     */
    @Override
    public StreamObserver<GameRequest> playGame(StreamObserver<GameResponse> responseObserver) {
        return new GameRequestHandler(responseObserver);
    }
}
