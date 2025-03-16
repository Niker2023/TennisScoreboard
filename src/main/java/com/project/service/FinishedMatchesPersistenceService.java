package com.project.service;

import com.project.dao.MatchDao;
import com.project.dao.PlayerDao;
import com.project.entity.MatchScore;
import com.project.entity.Matches;

import java.util.List;

public class FinishedMatchesPersistenceService {


    private final MatchDao matchDao = MatchDao.getInstance();


    private static final FinishedMatchesPersistenceService INSTANCE = new FinishedMatchesPersistenceService();


    public static FinishedMatchesPersistenceService getInstance() { return INSTANCE; }


    public void save(MatchScore matchScore) {
        Matches match = new Matches();
        match.setPlayer1(PlayerDao.getInstance().getPlayerById(matchScore.getPlayer1Id()));
        match.setPlayer2(PlayerDao.getInstance().getPlayerById(matchScore.getPlayer2Id()));
        match.setWinner(PlayerDao.getInstance().getPlayerById(matchScore.getWinnerId()));
        matchDao.save(match);
    }


    public List<Matches> getMatches() {
        return matchDao.getMatches();
    }
}
