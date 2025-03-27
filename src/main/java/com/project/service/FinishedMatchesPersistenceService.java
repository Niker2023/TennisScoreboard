package com.project.service;

import com.project.dao.MatchDao;
import com.project.dao.PlayerDao;
import com.project.dto.FinishedMatchDto;
import com.project.entity.MatchScore;
import com.project.entity.Matches;
import jakarta.persistence.PersistenceException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class FinishedMatchesPersistenceService {

    @Getter
    private static final int NUMBER_OF_LINES_PER_PAGE = 5;
    private final MatchDao matchDao = new MatchDao();
    private final PlayerDao playerDao = new PlayerDao();

    public void save(MatchScore matchScore) {
        Matches match = new Matches();
        match.setPlayer1(playerDao.getPlayerById(matchScore.getPlayer1Id()));
        match.setPlayer2(playerDao.getPlayerById(matchScore.getPlayer2Id()));
        match.setWinner(playerDao.getPlayerById(matchScore.getWinnerId()));
        matchDao.save(match);
    }

    public List<FinishedMatchDto> getFinishedMatches(Integer CurrentPage) {
        var matches = matchDao.getMatches(CurrentPage, NUMBER_OF_LINES_PER_PAGE);
        return MatchesListToDtoList(matches);
    }

    public Long getNumberOfPages() {
        var matchesCount = matchDao.getMatchesCount();
        return (matchesCount + NUMBER_OF_LINES_PER_PAGE - 1) / NUMBER_OF_LINES_PER_PAGE;
    }

    public Long getNumberOfPagesByName(String playerName) {
        var player = playerDao.getPlayerByName(playerName);
        var matchesCountByPlayer = matchDao.getMatchesCountByPlayer(player);
        return (matchesCountByPlayer + NUMBER_OF_LINES_PER_PAGE - 1) / NUMBER_OF_LINES_PER_PAGE;
    }

    public List<FinishedMatchDto> getFinishedMatchesByPlayerName(String playerName, Integer currentPage) throws PersistenceException {
        var player = playerDao.getPlayerByName(playerName);
        var matches = matchDao.getMatchesByPlayer(player, currentPage, NUMBER_OF_LINES_PER_PAGE);
        return MatchesListToDtoList(matches);
    }

    private List<FinishedMatchDto> MatchesListToDtoList(List<Matches> matchesList) {
        List<FinishedMatchDto> finishedMatches = new ArrayList<>();
        for (Matches match : matchesList) {
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
