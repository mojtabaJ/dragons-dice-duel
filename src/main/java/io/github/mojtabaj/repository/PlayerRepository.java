package io.github.mojtabaj.repository;

import io.github.mojtabaj.data.Faction;
import io.github.mojtabaj.data.PlayerData;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerRepository {
    private final static Map<Integer, PlayerData> db = new HashMap<>();

    public static PlayerData get(Integer side) {
        return db.getOrDefault(side,null);
    }

    public static PlayerData save(PlayerData player){
        if(player.getId() == 0){
            int id = db.size() + 1;
            return db.put(id,PlayerData.newBuilder(player).setId(id).build());
        }
        return db.put(player.getId(),PlayerData.newBuilder(player).build());
    }

    public static List<PlayerData> getAll(){
        return db.entrySet().stream().map(m->m.getValue()).toList();
    }

    public static int count() {
        return db.size();
    }
}
