package com.project.service;

import com.project.dao.MatchDao;
import com.project.dao.PlayerDao;
import com.project.dto.FinishedMatchDto;
import com.project.entity.MatchScore;
import com.project.entity.Matches;

import java.util.ArrayList;
import java.util.List;


public class FinishedMatchesPersistenceService {

    public void save(MatchScore matchScore) {
        MatchDao matchDao = MatchDao.getInstance();
        Matches match = new Matches();
        match.setPlayer1(PlayerDao.getInstance().getPlayerById(matchScore.getPlayer1Id()));
        match.setPlayer2(PlayerDao.getInstance().getPlayerById(matchScore.getPlayer2Id()));
        match.setWinner(PlayerDao.getInstance().getPlayerById(matchScore.getWinnerId()));
        matchDao.save(match);
    }

    public List<FinishedMatchDto> getFinishedMatches() {

        var matches = MatchDao.getInstance().getMatches();
        List<FinishedMatchDto> finishedMatches = new ArrayList<>();

        for (var match : matches) {
            finishedMatches.add(toDto(match));
        }
        return finishedMatches;
    }

    private FinishedMatchDto toDto(Matches match) {

        String player1Name = match.getPlayer1().getName();
        String player2Name = match.getPlayer2().getName();
        String winnerName = match.getWinner().getName();

        return new FinishedMatchDto(player1Name, player2Name, winnerName);
    }
}
