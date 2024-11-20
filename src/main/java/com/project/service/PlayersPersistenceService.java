package com.project.service;

import com.project.dao.PlayerDao;
import com.project.entity.Players;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayersPersistenceService {

    private final PlayerDao playerDao = PlayerDao.getInstance();

    private static final PlayersPersistenceService INSTANCE = new PlayersPersistenceService();

    public static PlayersPersistenceService getInstance() {
        return INSTANCE;
    }

    public Integer save(String player) {
        Players newPlayer = new Players(player);
        playerDao.save(newPlayer);
        return newPlayer.getId();
    }
}
