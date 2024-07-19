package io.github.mojtabaj.service.handler;

import io.github.mojtabaj.data.Faction;
import io.github.mojtabaj.data.GameData;
import io.github.mojtabaj.data.PlayerData;
import io.github.mojtabaj.repository.PlayerRepository;

import java.util.List;

/**
 * GameHandler handles the main game logic for the Dragons' Dice Duel game.
 * It includes methods for joining the game, rolling the dice, checking game readiness,
 * determining the winner, and managing player turns.
 */
public class GameHandler {
    private int currentTurnIndex = 0;

    /**
     * Allows a player to join the game.
     *
     * @param playerName the name of the player joining the game.
     * @param faction the faction the player is joining (The Greens or The Blacks).
     * @return the PlayerData of the newly joined player.
     */
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

    /**
     * Rolls the dice for a player and updates their score and turn information.
     *
     * @param playerId the ID of the player rolling the dice.
     * @param diceValue the value rolled on the dice.
     * @return the updated PlayerData after rolling the dice.
     */
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

    /**
     * Checks if the game is ready to start.
     * The game is ready if both factions (The Greens and The Blacks) have at least one player.
     *
     * @return true if the game is ready, false otherwise.
     */
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

    /**
     * Checks if there is a winner in the game.
     * A player wins by reaching a score of 131 or more.
     *
     * @return the GameData containing the winner information if there is a winner, null otherwise.
     */
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

    /**
     * Gets the player whose turn it is currently.
     *
     * @return the current PlayerData.
     */
    public synchronized PlayerData getCurrentPlayer() {
        List<PlayerData> players = PlayerRepository.getAll();
        return players.get(currentTurnIndex);
    }

}
