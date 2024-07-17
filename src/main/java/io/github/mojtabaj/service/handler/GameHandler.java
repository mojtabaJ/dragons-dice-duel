package io.github.mojtabaj.service.handler;

import io.github.mojtabaj.data.Faction;
import io.github.mojtabaj.data.GameData;
import io.github.mojtabaj.data.PlayerData;
import io.github.mojtabaj.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class GameHandler {
    private int currentTurnIndex = 0;

    public synchronized PlayerData joinGame(String playerName, Faction faction) {
        PlayerData player = PlayerData.newBuilder()
                .setPlayerName(playerName)
                .setFaction(faction)
                .setScore(0)
                .setAttempt(0)
                .setTurns(0)
                .build();
        PlayerRepository.save(player);
        return player;
    }

    public synchronized PlayerData rollDice(int playerId, int diceValue) {
        PlayerData player = PlayerRepository.get(playerId);
        if(player == null){
            return null;
        }
        PlayerData.Builder updatedPlayer = player.toBuilder();
        updatedPlayer.setTurns(player.getTurns() + 1);
        updatedPlayer.setAttempt(player.getAttempt() + 1);
        if (diceValue == 6) {
            updatedPlayer.setScore(player.getScore() + 1);
        }else{
            currentTurnIndex = (currentTurnIndex + 1) % PlayerRepository.count();
        }
        PlayerRepository.save(updatedPlayer.build());
        return updatedPlayer.build();
    }

    public synchronized boolean isGameReady() {
        boolean greensPresent = false;
        boolean blacksPresent = false;
        List<PlayerData> players = PlayerRepository.getAll();
        for (PlayerData player : players) {
            if (player.getFaction() == Faction.THE_GREENS) {
                greensPresent = true;
            } else if (player.getFaction() == Faction.THE_BLACKS) {
                blacksPresent = true;
            }
        }
        return greensPresent && blacksPresent;
    }

    public synchronized GameData checkForWinner() {
        List<PlayerData> players = PlayerRepository.getAll();
        for (PlayerData player : players) {
            if (player.getScore() >= 131) {
                return GameData.newBuilder()
                        .addAllPlayers(players)
                        .setWinner(player)
                        .build();
            }
        }
        return null;
    }

    public synchronized PlayerData getCurrentPlayer() {
        List<PlayerData> players = PlayerRepository.getAll();
        return players.get(currentTurnIndex);
    }

    public synchronized List<PlayerData> getPlayers() {
        return new ArrayList<>(PlayerRepository.getAll());
    }
}
