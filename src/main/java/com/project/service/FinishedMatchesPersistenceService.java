package com.project.service;

import com.project.dao.MatchDao;
import com.project.dao.PlayerDao;
import com.project.dto.FinishedMatchDto;
import com.project.entity.Matches;
import com.project.entity.match_state.Score;
import com.project.mapper.MatchMapper;
import com.project.util.ValidationUtil;

import java.util.List;
import java.util.stream.Collectors;

public class FinishedMatchesPersistenceService {

    private static final int NUMBER_OF_LINES_PER_PAGE = 5;

    private final MatchDao matchDao = new MatchDao();
    private final PlayerDao playerDao = new PlayerDao();

    public void save(Score matchScore) {

        Matches match = new Matches();

        match.setPlayer1(playerDao.getPlayerById(matchScore.getPlayer1Id()));
        match.setPlayer2(playerDao.getPlayerById(matchScore.getPlayer2Id()));
        match.setWinner(playerDao.getPlayerById(matchScore.getWinnerId()));

        matchDao.save(match);
    }


    public List<FinishedMatchDto> getFinishedMatches(String currentPage) {

        int currentPageNumber = ValidationUtil.checkPage(currentPage);
        var matches = matchDao.getMatches(currentPageNumber, NUMBER_OF_LINES_PER_PAGE);

        return matches.stream()
                .map(MatchMapper.INSTANCE::DtoFromMatch)
                .collect(Collectors.toList());
    }


    public Long getNumberOfPages() {

        var matchesCount = matchDao.getMatchesCount();

        return (matchesCount + NUMBER_OF_LINES_PER_PAGE - 1) / NUMBER_OF_LINES_PER_PAGE;
    }


    public Long getNumberOfPagesByName(String playerName) {

        ValidationUtil.checkName(playerName);
        var player = playerDao.getPlayerByName(playerName);
        var matchesCountByPlayer = matchDao.getMatchesCountByPlayer(player);

        return (matchesCountByPlayer + NUMBER_OF_LINES_PER_PAGE - 1) / NUMBER_OF_LINES_PER_PAGE;
    }


    public List<FinishedMatchDto> getFinishedMatchesByPlayerName(String playerName, String currentPage) {

        ValidationUtil.checkName(playerName);
        int currentPageNumber = ValidationUtil.checkPage(currentPage);
        var player = playerDao.getPlayerByName(playerName);

        var matches = matchDao.getMatchesByPlayer(player, currentPageNumber, NUMBER_OF_LINES_PER_PAGE);

        return matches.stream()
                .map(MatchMapper.INSTANCE::DtoFromMatch)
                .collect(Collectors.toList());
    }


    public int getInitialDigitOfNumber(String currentPage) {

        int currentPageNumber = ValidationUtil.checkPage(currentPage);

        return NUMBER_OF_LINES_PER_PAGE * (currentPageNumber - 1) + 1;
    }


    public int getCurrentPageNumber(String currentPage) {

        return ValidationUtil.checkPage(currentPage);
    }
}
