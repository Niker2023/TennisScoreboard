package com.project.service;

import com.project.dao.PlayerDao;
import com.project.entity.Players;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayersPersistenceService {

    public Integer save(String player) {
        PlayerDao playerDao = new PlayerDao();
        Players newPlayer = new Players(player);
        playerDao.save(newPlayer);
        return newPlayer.getId();
    }
}
