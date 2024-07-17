package io.github.mojtabaj.service.handler;

import io.github.mojtabaj.data.GameData;
import io.github.mojtabaj.data.GameRequest;
import io.github.mojtabaj.data.GameResponse;
import io.github.mojtabaj.data.PlayerData;
import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class GameRequestHandler implements StreamObserver<GameRequest> {

    private final Logger logger = Logger.getLogger(GameRequestHandler.class.getName());
    private final StreamObserver<GameResponse> responseObserver;
    private final GameHandler handler = new GameHandler();

    public GameRequestHandler(StreamObserver<GameResponse> responseObserver) {
        this.responseObserver = responseObserver;
    }

    @Override
    public void onNext(GameRequest gameRequest) {
        logger.info("onNext: " + gameRequest);
        GameResponse response = null;

        if (gameRequest.hasJoinRequest()) {
            PlayerData player = gameRequest.getJoinRequest();
            PlayerData joinedPlayer = handler.joinGame(player.getPlayerName(), player.getFaction());
            response = GameResponse.newBuilder()
                    .setJoined(joinedPlayer)
                    .build();
            responseObserver.onNext(response);

            if (handler.isGameReady()) {
                PlayerData currentPlayer = handler.getCurrentPlayer();
                response = GameResponse.newBuilder()
                        .setTurns(currentPlayer)
                        .build();
                responseObserver.onNext(response);

            }
        } else if (gameRequest.hasDice()) {
            int diceValue = gameRequest.getDice().getDice();
            int playerId = gameRequest.getDice().getPlayer().getId();
            PlayerData updatedPlayer = handler.rollDice(playerId, diceValue);

            GameData gameData = handler.checkForWinner();
            if (gameData != null) {
                response = GameResponse.newBuilder()
                        .setResult(gameData)
                        .build();
            } else {
                PlayerData currentPlayer = handler.getCurrentPlayer();
                response = GameResponse.newBuilder()
                        .setPlayed(updatedPlayer)
                        .setTurns(currentPlayer)
                        .build();
            }
            responseObserver.onNext(response);
        }


    }


    @Override
    public void onError(Throwable throwable) {
        logger.info("onError: " + throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        logger.info("onCompleted");
        responseObserver.onCompleted();
    }




}
