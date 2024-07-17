package io.github.mojtabaj;

import io.github.mojtabaj.data.*;
import io.github.mojtabaj.data.DiceData;
import io.github.mojtabaj.data.DragonsDiceDuelGrpc;
import io.github.mojtabaj.data.DragonsDiceDuelProto.*;
import io.github.mojtabaj.data.Faction;
import io.github.mojtabaj.data.GameRequest;
import io.github.mojtabaj.data.GameResponse;
import io.github.mojtabaj.data.PlayerData;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class DragonsDiceDuelClient {

    private final DragonsDiceDuelGrpc.DragonsDiceDuelStub asyncStub;
    public static CountDownLatch latch = new CountDownLatch(1);

    private StreamObserver<GameRequest> requestObserver;
    public DragonsDiceDuelClient(ManagedChannel channel) {
        asyncStub = DragonsDiceDuelGrpc.newStub(channel);
    }

    public void playGame() {
        requestObserver = asyncStub.playGame(new StreamObserver<GameResponse>() {
            @Override
            public void onNext(GameResponse gameResponse) {
                if (gameResponse.hasJoined()) {
                    PlayerData joinedPlayer = gameResponse.getJoined();
                    System.out.println("Joined the game: " + joinedPlayer);
                } else if (gameResponse.hasPlayed()) {
                    PlayerData played = gameResponse.getPlayed();
                    System.out.println("Player action: " + played);
                } else if (gameResponse.hasTurns()) {
                    PlayerData currentTurnPlayer = gameResponse.getTurns();
                    System.out.println("It's " + currentTurnPlayer.getPlayerName() + "'s turn.");
                    playTurn(currentTurnPlayer);
                } else if (gameResponse.hasResult()) {
                    System.out.println("Game result: " + gameResponse.getResult());
                    latch.countDown();
                }
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("Game completed.");
            }
        });

        joinGame(requestObserver);
    }

    private void joinGame(StreamObserver<GameRequest> requestObserver) {
        //Player list:
        List<PlayerData> player = List.of(
                PlayerData.newBuilder().setPlayerName("Queen Rhaenyra Targaryen").setFaction(Faction.THE_BLACKS).build(),
                PlayerData.newBuilder().setPlayerName("Queen Dowager Alicent Hightower").setFaction(Faction.THE_GREENS).build(),
                PlayerData.newBuilder().setPlayerName("Prince Daemon Targaryen").setFaction(Faction.THE_BLACKS).build(),
                PlayerData.newBuilder().setPlayerName("Ser Criston Cole").setFaction(Faction.THE_GREENS).build()
        );

        for (PlayerData joinRequest : player) {
            GameRequest gameRequest = GameRequest.newBuilder()
                    .setJoinRequest(joinRequest)
                    .build();
            requestObserver.onNext(gameRequest);
        }
    }

    private void playTurn(PlayerData player) {
        int diceValue = new Random().nextInt(6)+1;
        GameRequest playRequest = GameRequest.newBuilder()
                .setDice(DiceData.newBuilder().setDice(diceValue).setPlayer(player).build())
                .build();
        requestObserver.onNext(playRequest);
    }

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .build();

        DragonsDiceDuelClient client = new DragonsDiceDuelClient(channel);
        client.playGame();

        channel.shutdown();
        latch.await();
    }
}
