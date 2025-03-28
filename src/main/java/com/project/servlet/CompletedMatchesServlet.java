package com.project.servlet;

import com.project.dto.FinishedMatchDto;
import com.project.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class CompletedMatchesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

        var filterByPlayerName = request.getParameter("playerName");
        var page = request.getParameter("page");

        List<FinishedMatchDto> finishedMatches;
        Long numberOfPages;

        try {
            if (filterByPlayerName == null || filterByPlayerName.isBlank()) {
                finishedMatches = finishedMatchesPersistenceService.getFinishedMatches(page);
                numberOfPages = finishedMatchesPersistenceService.getNumberOfPages();
            } else {
                finishedMatches = finishedMatchesPersistenceService.getFinishedMatchesByPlayerName(filterByPlayerName, page);
                numberOfPages = finishedMatchesPersistenceService.getNumberOfPagesByName(filterByPlayerName);
                request.setAttribute("filterByPlayerName", filterByPlayerName);
            }
            request.setAttribute("currentPage", finishedMatchesPersistenceService.getCurrentPageNumber(page));
            request.setAttribute("totalPages", numberOfPages);
            request.setAttribute("initialDigitOfNumbering", finishedMatchesPersistenceService.getInitialDigitOfNumber(page));
            request.setAttribute("matches", finishedMatches);

        } catch (Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.setAttribute("new_match_url", "home-page");
        request.getRequestDispatcher("/WEB-INF/completed-matches.jsp")
                .forward(request, response);
    }
}