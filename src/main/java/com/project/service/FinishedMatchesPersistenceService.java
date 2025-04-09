package com.project.service;

import com.project.dao.MatchDao;
import com.project.dao.PlayerDao;
import com.project.entity.Matches;
import com.project.entity.match_state.Score;

public class FinishedMatchesPersistenceService {

    public void save(Score matchScore) {

        MatchDao matchDao = new MatchDao();
        PlayerDao playerDao = new PlayerDao();

        Matches match = new Matches();

        match.setPlayer1(playerDao.getPlayerById(matchScore.getPlayer1Id()));
        match.setPlayer2(playerDao.getPlayerById(matchScore.getPlayer2Id()));
        match.setWinner(playerDao.getPlayerById(matchScore.getWinnerId()));

        matchDao.save(match);
    }
}
