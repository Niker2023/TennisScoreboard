package com.project.service;

import com.project.dto.PlayerDto;
import com.project.entity.match_state.Score;
import com.project.util.ValidationUtil;

import java.util.UUID;

public class MatchCreationService {

    public UUID createMatch(String player1, String player2) {

        ValidationUtil.namesValidation(player1, player2);

        var playersPersistenceService = new PlayersPersistenceService();

        PlayerDto player1Dto = playersPersistenceService.save(player1);
        PlayerDto player2Dto = playersPersistenceService.save(player2);

        Score matchScore = new Score(player1Dto, player2Dto);

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

        return ongoingMatchesService.addMatch(matchScore);
    }
}
