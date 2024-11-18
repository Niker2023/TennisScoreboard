package com.project.service;

import com.project.dto.MatchDto;
import com.project.entity.Players;

import java.util.UUID;

public class MatchCreationService {

    public MatchDto createMatch(Players player1, Players player2) {
        UUID uuid = UUID.randomUUID();
        return new MatchDto(uuid, player1, player2);
    }
}
