package com.project.service;

import com.project.dao.PlayerDao;
import com.project.dto.PlayerDto;
import com.project.entity.Players;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PlayersPersistenceService {

    public PlayerDto save(String player) {
        PlayerDao playerDao = new PlayerDao();
        Players newPlayer = new Players(player);

        playerDao.save(newPlayer);

        return new PlayerDto(newPlayer.getId(), newPlayer.getName());
    }
}
