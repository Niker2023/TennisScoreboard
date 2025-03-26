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


@Getter
public class FinishedMatchesPersistenceService {

    private final Integer numberOfLinesPerPage = 5;

    public void save(MatchScore matchScore) {
        MatchDao matchDao = MatchDao.getInstance();
        Matches match = new Matches();
        match.setPlayer1(PlayerDao.getInstance().getPlayerById(matchScore.getPlayer1Id()));
        match.setPlayer2(PlayerDao.getInstance().getPlayerById(matchScore.getPlayer2Id()));
        match.setWinner(PlayerDao.getInstance().getPlayerById(matchScore.getWinnerId()));
        matchDao.save(match);
    }


    public List<FinishedMatchDto> getFinishedMatches(Integer CurrentPage) {

        var matches = MatchDao.getInstance().getMatches(CurrentPage, numberOfLinesPerPage);

        return MatchesListToDtoList(matches);
    }


    public Long getNumberOfPages() {
        var matchesCount = MatchDao.getInstance().getMatchesCount();
        return (matchesCount + numberOfLinesPerPage - 1) / numberOfLinesPerPage;
    }


    public Long getNumberOfPagesByName(String playerName) {
        var player = PlayerDao.getInstance().getPlayerByName(playerName);
        var matchesCountByPlayer = MatchDao.getInstance().getMatchesCountByPlayer(player);
        return (matchesCountByPlayer + numberOfLinesPerPage - 1) / numberOfLinesPerPage;
    }


    public List<FinishedMatchDto> getFinishedMatchesByPlayerName(String playerName, Integer currentPage) throws PersistenceException {

        var player = PlayerDao.getInstance().getPlayerByName(playerName);
        var matches = MatchDao.getInstance().getMatchesByPlayer(player, currentPage, numberOfLinesPerPage);

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
