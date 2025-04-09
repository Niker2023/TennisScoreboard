package com.project.service;

import com.project.dao.MatchDao;
import com.project.dao.PlayerDao;
import com.project.dto.MatchesToDisplayDto;
import com.project.mapper.MatchMapper;
import com.project.util.ValidationUtil;

import java.util.stream.Collectors;

public class FinishedMatchesPaginationService {

    private final int NUMBER_OF_LINES_PER_PAGE = 5;

    private final MatchDao matchDao = new MatchDao();
    private final PlayerDao playerDao = new PlayerDao();


    private int getNumberOfPages() {

        var matchesCount = matchDao.getMatchesCount();

        return (matchesCount + NUMBER_OF_LINES_PER_PAGE - 1) / NUMBER_OF_LINES_PER_PAGE;
    }


    private int getNumberOfPagesByName(String playerName) {

        var player = playerDao.getPlayerByName(playerName);
        var matchesCountByPlayer = matchDao.getMatchesCountByPlayer(player);

        return (matchesCountByPlayer + NUMBER_OF_LINES_PER_PAGE - 1) / NUMBER_OF_LINES_PER_PAGE;
    }


    public MatchesToDisplayDto getFinishedMatches(String playerName, String currentPage) {

        if (ValidationUtil.isName(playerName)) {
            return getFinishedMatchesByPlayerName(playerName, currentPage);
        }

        return getAllFinishedMatches(currentPage);
    }


    private MatchesToDisplayDto getFinishedMatchesByPlayerName(String playerName, String currentPage) {

        int totalPages = getNumberOfPagesByName(playerName);
        int currentPageNumber = ValidationUtil.getCorrectPageNumber(currentPage, totalPages);

        var player = playerDao.getPlayerByName(playerName);

        var matches = matchDao.getMatchesByPlayer(player, currentPageNumber, NUMBER_OF_LINES_PER_PAGE);

        var finishedMatchesDto = matches.stream()
                .map(MatchMapper.INSTANCE::DtoFromMatch)
                .collect(Collectors.toList());

        return new MatchesToDisplayDto(currentPageNumber, totalPages, getInitialDigitOfNumber(currentPageNumber), finishedMatchesDto);
    }


    private MatchesToDisplayDto getAllFinishedMatches(String currentPage) {

        int totalPages = getNumberOfPages();
        int currentPageNumber = ValidationUtil.getCorrectPageNumber(currentPage, totalPages);

        var matches = matchDao.getMatches(currentPageNumber, NUMBER_OF_LINES_PER_PAGE);

        var finishedMatchesDto = matches.stream()
                .map(MatchMapper.INSTANCE::DtoFromMatch)
                .collect(Collectors.toList());

        return new MatchesToDisplayDto(currentPageNumber, totalPages, getInitialDigitOfNumber(currentPageNumber), finishedMatchesDto);
    }


    private int getInitialDigitOfNumber(int currentPageNumber) {
        return NUMBER_OF_LINES_PER_PAGE * (currentPageNumber - 1) + 1;
    }
}
