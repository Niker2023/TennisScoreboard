package com.project.service;

import com.project.dto.PlayerDto;
import com.project.entity.matchState.Score;
import com.project.exception.ValidationException;
import com.project.util.ValidationUtil;

import java.util.UUID;

public class MatchCreationService {

    public UUID createMatch(String player1, String player2) {

        namesValidation(player1, player2);

        var playersPersistenceService = new PlayersPersistenceService();

        PlayerDto player1Dto = playersPersistenceService.save(player1);
        PlayerDto player2Dto = playersPersistenceService.save(player2);

        Score matchScore = new Score(player1Dto, player2Dto);

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

        return ongoingMatchesService.addMatch(matchScore);
    }

    private void namesValidation(String player1, String player2) {

        ValidationUtil validationUtil = new ValidationUtil();

        validationUtil.checkName(player1);
        validationUtil.checkName(player2);

        if (player1.equals(player2)) {
            throw new ValidationException("Имена игроков одинаковы!");
        }
    }
}
