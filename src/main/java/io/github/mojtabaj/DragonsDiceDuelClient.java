package io.github.mojtabaj;

import io.github.mojtabaj.data.*;
import io.github.mojtabaj.data.DragonsDiceDuelGrpc;
import io.github.mojtabaj.data.Faction;
import io.github.mojtabaj.data.GameResponse;
import io.github.mojtabaj.data.JoinRequest;
import io.github.mojtabaj.data.JoinResponse;
import io.github.mojtabaj.data.PlayerAction;
import io.github.mojtabaj.data.RollRequest;
import io.github.mojtabaj.data.RollResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DragonsDiceDuelClient {

    private static final Logger logger = Logger.getLogger(DragonsDiceDuelClient.class.getName());
    static CountDownLatch latch = new CountDownLatch(1); // Adjust the count based on the number of clients

    private final DragonsDiceDuelGrpc.DragonsDiceDuelStub asyncStub;
    private final String playerName;
    private final Faction faction;

    public DragonsDiceDuelClient(String host, int port, String playerName, Faction faction) {
        this.playerName = playerName;
        this.faction = faction;

        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        asyncStub = DragonsDiceDuelGrpc.newStub(channel);
    }

    public void playGame() {
        logger.info(playerName + " joining the game...");

        JoinRequest joinRequest = JoinRequest.newBuilder()
                .setPlayerName(playerName)
                .setFaction(faction)
                .build();

        PlayerAction playerAction = PlayerAction.newBuilder()
                .setJoinRequest(joinRequest)
                .build();

        StreamObserver<GameResponse> responseObserver = new StreamObserver<GameResponse>() {
            @Override
            public void onNext(GameResponse response) {
                if (response.getResponseCase() == GameResponse.ResponseCase.JOIN_RESPONSE) {
                    handleJoinResponse(response.getJoinResponse());
                } else if (response.getResponseCase() == GameResponse.ResponseCase.ROLL_RESPONSE) {
                    handleRollResponse(response.getRollResponse(), this);
                } else {
                    logger.log(Level.WARNING, "Unknown response.");
                }
            }

            @Override
            public void onError(Throwable t) {
                logger.log(Level.SEVERE, "Error occurred in game play: " + t.getMessage());
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                logger.info("Game over.");
            }
        };

        asyncStub.playGame(responseObserver).onNext(playerAction);
    }


    private void handleJoinResponse(JoinResponse response) {
        logger.info("Join Response: " + response.getMessage());

        if (response.getGameStarted()) {
            logger.info("Game has started. Waiting for your turn...");
        } else {
            logger.info("Waiting for more players to join...");
        }
    }

    private void handleRollResponse(RollResponse response, StreamObserver<GameResponse> responseObserver) {
        logger.info("Roll Response:");
        logger.info("- Player: " + response.getPlayerName());
        logger.info("- Roll: " + response.getRollValue());
        logger.info("- Points: " + response.getPoints());
        logger.info("- Extra Turn: " + response.getExtraTurn());
        logger.info("- Winner: " + response.getIsWinner());
        logger.info("- Next Turn: " + response.getNextTurn());

        if (response.getIsWinner()) {
            logger.info(response.getPlayerName() + " wins the game!");
            responseObserver.onCompleted();
            latch.countDown();
            return;
        }

        if (response.getNextTurn().equals(playerName)) {


            RollRequest rollRequest = RollRequest.newBuilder()
                    .setPlayerName(playerName)
                    .build();

            PlayerAction playerAction = PlayerAction.newBuilder()
                    .setRollRequest(rollRequest)
                    .build();

            asyncStub.playGame(responseObserver).onNext(playerAction);
        }
    }

    public static void main(String[] args) {
        String playerName1 = "Player1 (THE_GREENS)";
        Faction faction1 = Faction.THE_GREENS;

        String playerName2 = "Player2 (THE_BLACKS)";
        Faction faction2 = Faction.THE_BLACKS;

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        // Player 1
        executorService.submit(() -> {
            DragonsDiceDuelClient client1 = new DragonsDiceDuelClient("localhost", 9090, playerName1, faction1);
            client1.playGame();
        });

        // Player 2
        executorService.submit(() -> {
            DragonsDiceDuelClient client2 = new DragonsDiceDuelClient("localhost", 9090, playerName2, faction2);
            client2.playGame();
        });

        // Shutdown executor after game completion
        // Wait for all clients to complete
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
