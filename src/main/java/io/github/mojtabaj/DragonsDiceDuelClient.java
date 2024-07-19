package io.github.mojtabaj;

import io.github.mojtabaj.data.DiceData;
import io.github.mojtabaj.data.DragonsDiceDuelGrpc;
import io.github.mojtabaj.data.Faction;
import io.github.mojtabaj.data.GameRequest;
import io.github.mojtabaj.data.GameResponse;
import io.github.mojtabaj.data.PlayerData;
import io.github.mojtabaj.header.Constants;
import io.grpc.ClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * DragonsDiceDuelClient interacts with the Dragons' Dice Duel gRPC server.
 * It handles playing the game by sending requests and processing responses.
 */
public class DragonsDiceDuelClient {

    private static final Metadata.Key<String> API_KEY = Metadata.Key.of(Constants.API_KEY, Metadata.ASCII_STRING_MARSHALLER);
    private final DragonsDiceDuelGrpc.DragonsDiceDuelStub asyncStub;
    public static CountDownLatch latch = new CountDownLatch(1);
    private StreamObserver<GameRequest> requestObserver;

    /**
     * Constructs a DragonsDiceDuelClient with a managed channel.
     *
     * @param channel the managed channel for communication with the gRPC server.
     */
    public DragonsDiceDuelClient(ManagedChannel channel) {
        asyncStub = DragonsDiceDuelGrpc.newStub(channel);
    }

    /**
     * Starts the game by creating a request observer and sending join requests.
     */
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

    /**
     * Sends join requests for each player to the gRPC server.
     *
     * @param requestObserver the observer used to send requests to the server.
     */
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

    /**
     * Sends a dice roll request for the specified player.
     *
     * @param player the player whose turn it is to roll the dice.
     */
    private void playTurn(PlayerData player) {
        int diceValue = new Random().nextInt(6)+1;
        GameRequest playRequest = GameRequest.newBuilder()
                .setDice(DiceData.newBuilder().setDice(diceValue).setPlayer(player).build())
                .build();
        requestObserver.onNext(playRequest);
    }

    /**
     * Configures the client interceptors, including attaching API key headers.
     *
     * @return a list of client interceptors.
     */
    private static List<ClientInterceptor> getClientIntercept() {

        var keySecretMetadata = new Metadata();
        keySecretMetadata.put(API_KEY, Constants.API_SECRET);

        return List.of(
                MetadataUtils.newAttachHeadersInterceptor(keySecretMetadata)
        );
    }

    /**
     * The main method to initialize the client, start the game, and wait for completion.
     *
     * @param args command-line arguments (not used in this implementation).
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .intercept(getClientIntercept())
                .build();

        DragonsDiceDuelClient client = new DragonsDiceDuelClient(channel);
        client.playGame();

        channel.shutdown();
        latch.await();
    }


}
