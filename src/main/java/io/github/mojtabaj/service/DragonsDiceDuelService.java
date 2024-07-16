package io.github.mojtabaj.service;

import io.github.mojtabaj.data.*;
import io.github.mojtabaj.data.DragonsDiceDuelGrpc;
import io.github.mojtabaj.data.Faction;
import io.github.mojtabaj.data.GameResponse;
import io.github.mojtabaj.data.JoinRequest;
import io.github.mojtabaj.data.JoinResponse;
import io.github.mojtabaj.data.PlayerAction;
import io.github.mojtabaj.data.PlayerInfo;
import io.github.mojtabaj.data.RollRequest;
import io.github.mojtabaj.data.RollResponse;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DragonsDiceDuelService extends DragonsDiceDuelGrpc.DragonsDiceDuelImplBase {

    private static final Logger logger = Logger.getLogger(DragonsDiceDuelService.class.getName());

    private ConcurrentMap<String, Integer> playerPoints = new ConcurrentHashMap<>();
    private List<PlayerInfo> players = new ArrayList<>();
    private boolean gameStarted = false;
    private int currentPlayerIndex = -1;

    @Override
    public StreamObserver<PlayerAction> playGame(StreamObserver<GameResponse> responseObserver) {
        return new StreamObserver<PlayerAction>() {
            @Override
            public void onNext(PlayerAction action) {
                try {
                    if (action.getActionCase() == PlayerAction.ActionCase.JOIN_REQUEST) {
                        handleJoinRequest(action.getJoinRequest(), responseObserver);
                    } else if (action.getActionCase() == PlayerAction.ActionCase.ROLL_REQUEST) {
                        handleRollRequest(action.getRollRequest(), responseObserver);
                    } else {
                        String errorMessage = "Unknown action received.";
                        logger.warning(errorMessage);
                        responseObserver.onError(new IllegalArgumentException(errorMessage));
                    }
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Error processing action: " + e.getMessage(), e);
                    responseObserver.onError(e);
                }
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.SEVERE, "Error in game play: " + t.getMessage(), t);
            }

            @Override
            public void onCompleted() {
                logger.info("Game session completed.");
                responseObserver.onCompleted();
            }
        };
    }

    private synchronized void handleJoinRequest(JoinRequest request, StreamObserver<GameResponse> responseObserver) {
        String playerName = request.getPlayerName();
        Faction faction = request.getFaction();

        logger.info(playerName + " joining the game...");

        if (!gameStarted) {
            players.add(PlayerInfo.newBuilder()
                    .setPlayerName(playerName)
                    .setFaction(faction)
                    .build());

            String message = "Welcome " + playerName + " of " + faction.name();
            if (players.size() == 0) {
                JoinResponse joinResponse = JoinResponse.newBuilder()
                        .setGameStarted(false)
                        .setMessage(message + ". Waiting for more players to join...")
                        .build();

                GameResponse gameResponse = GameResponse.newBuilder()
                        .setJoinResponse(joinResponse)
                        .build();

                responseObserver.onNext(gameResponse);
            } else if (players.size() == 1) {
                gameStarted = true;
                message += ". The game has started!";
                logger.info("Game started with " + players.size() + " players.");

                JoinResponse joinResponse = JoinResponse.newBuilder()
                        .setGameStarted(true)
                        .setMessage(message)
                        .build();

                GameResponse gameResponse = GameResponse.newBuilder()
                        .setJoinResponse(joinResponse)
                        .build();

                responseObserver.onNext(gameResponse);

                // Notify the first player to roll the dice
                notifyFirstPlayerToRoll(responseObserver);
            }
        } else {
            String message = "Cannot join. Game has already started.";
            JoinResponse joinResponse = JoinResponse.newBuilder()
                    .setGameStarted(false)
                    .setMessage(message)
                    .build();

            GameResponse gameResponse = GameResponse.newBuilder()
                    .setJoinResponse(joinResponse)
                    .build();

            responseObserver.onNext(gameResponse);
            responseObserver.onCompleted();
        }
    }

    private void notifyFirstPlayerToRoll(StreamObserver<GameResponse> responseObserver) {
        currentPlayerIndex = 0;
        String firstPlayerName = players.get(currentPlayerIndex).getPlayerName();

        RollResponse rollResponse = RollResponse.newBuilder()
                .setPlayerName(firstPlayerName)
                .setRollValue(0) // Roll value irrelevant for notification
                .setPoints(playerPoints.getOrDefault(firstPlayerName, 0))
                .setExtraTurn(false)
                .setIsWinner(false)
                .setNextTurn(firstPlayerName)
                .build();

        GameResponse gameResponse = GameResponse.newBuilder()
                .setRollResponse(rollResponse)
                .build();

        responseObserver.onNext(gameResponse);
    }

    private synchronized void handleRollRequest(RollRequest request, StreamObserver<GameResponse> responseObserver) {
        String playerName = request.getPlayerName();

        logger.info(playerName + " is rolling the dice...");

        if (!gameStarted) {
            String errorMessage = "Cannot roll. Game has not started yet.";
            logger.warning(errorMessage);
            responseObserver.onError(new IllegalStateException(errorMessage));
            return;
        }

        PlayerInfo currentPlayer = players.get(currentPlayerIndex);
        if (!currentPlayer.getPlayerName().equals(playerName)) {
            String errorMessage = "Cannot roll. It's not your turn, current turn: " + currentPlayer.getPlayerName();
            logger.warning(errorMessage);
            responseObserver.onError(new IllegalStateException(errorMessage));
            return;
        }

        int rollValue = (int) (Math.random() * 6) + 1;
        int currentPoints = playerPoints.getOrDefault(playerName, 0);
        boolean extraTurn = false;
        boolean isWinner = false;

        if (rollValue == 6) {
            currentPoints += 1;
            extraTurn = true;
            if (currentPoints >= 100) {
                isWinner = true;
                gameStarted = false; // End the game
                logger.info(playerName + " wins the game!");
            }
        }

        playerPoints.put(playerName, currentPoints);

        String nextPlayer = "";
        if (!extraTurn && !isWinner) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            nextPlayer = players.get(currentPlayerIndex).getPlayerName();
            logger.info("Next turn: " + nextPlayer);
        } else {
            nextPlayer = playerName; // The same player rolls again
            logger.info("Extra turn for player: " + playerName);
        }

        RollResponse rollResponse = RollResponse.newBuilder()
                .setPlayerName(playerName)
                .setRollValue(rollValue)
                .setPoints(currentPoints)
                .setExtraTurn(extraTurn)
                .setIsWinner(isWinner)
                .setNextTurn(nextPlayer)
                .build();

        GameResponse gameResponse = GameResponse.newBuilder()
                .setRollResponse(rollResponse)
                .build();

        responseObserver.onNext(gameResponse);

        if (isWinner) {
            //reset
            playerPoints = new ConcurrentHashMap<>();
            players = new ArrayList<>();
            gameStarted = false;
            currentPlayerIndex = -1;
//            responseObserver.onCompleted();
        }
    }


}
