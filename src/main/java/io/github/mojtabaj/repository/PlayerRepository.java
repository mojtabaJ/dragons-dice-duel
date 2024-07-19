package io.github.mojtabaj.repository;

import io.github.mojtabaj.data.PlayerData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * PlayerRepository is an in-memory repository for storing and retrieving PlayerData objects.
 * It provides methods to get, save, and retrieve all players, as well as to count the number of players.
 */
public class PlayerRepository {
    private final static Map<Integer, PlayerData> db = new HashMap<>();

    /**
     * Retrieves a player by their ID.
     *
     * @param id the ID of the player to retrieve.
     * @return the PlayerData for the given ID, or null if no player is found.
     */
    public static PlayerData get(Integer id) {
        return db.getOrDefault(id,null);
    }

    /**
     * Saves a player to the repository.
     * If the player's ID is 0, a new ID is assigned.
     *
     * @param player the PlayerData to save.
     */
    public static void save(PlayerData player){
        if(player.getId() == 0){
            int id = db.size() + 1;
            db.put(id, PlayerData.newBuilder(player).setId(id).build());
            return;
        }
        db.put(player.getId(), PlayerData.newBuilder(player).build());
    }

    /**
     * Retrieves all players in the repository.
     *
     * @return a list of all PlayerData objects.
     */
    public static List<PlayerData> getAll(){
        return db.values().stream().toList();
    }

    /**
     * Returns the number of players in the repository.
     *
     * @return the count of players.
     */
    public static int count() {
        return db.size();
    }
}
