package io.github.mojtabaj.service;

import io.github.mojtabaj.data.DragonsDiceDuelGrpc;
import io.github.mojtabaj.data.GameRequest;
import io.github.mojtabaj.data.GameResponse;
import io.github.mojtabaj.service.handler.GameRequestHandler;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class DragonsDiceDuelService extends DragonsDiceDuelGrpc.DragonsDiceDuelImplBase {

    private static final Logger logger = Logger.getLogger(DragonsDiceDuelService.class.getName());

    @Override
    public StreamObserver<GameRequest> playGame(StreamObserver<GameResponse> responseObserver) {
        return new GameRequestHandler(responseObserver);
    }
}
