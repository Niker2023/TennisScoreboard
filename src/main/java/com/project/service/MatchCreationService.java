package com.project.service;

import com.project.dto.PlayerDto;
import com.project.entity.match_state.Score;
import com.project.util.ValidationUtil;

import java.util.UUID;

public class MatchCreationService {

    public UUID createMatch(String player1Name, String player2Name) {

        checkNames(player1Name, player2Name);
        
        PlayerDto player1Dto = savePlayerToDB(player1Name);
        PlayerDto player2Dto = savePlayerToDB(player2Name);

        Score matchScore = new Score(player1Dto, player2Dto);

        OngoingMatchesService ongoingMatchesService = new OngoingMatchesService();

        return ongoingMatchesService.addMatch(matchScore);
    }


    private PlayerDto savePlayerToDB(String playerName) {
        var playersPersistenceService = new PlayersPersistenceService();
        return playersPersistenceService.save(playerName);
    }


    private void checkNames(String name1, String name2) {
        ValidationUtil.isName(name1);
        ValidationUtil.isName(name2);
        ValidationUtil.namesComparing(name1, name2);
    }
}
