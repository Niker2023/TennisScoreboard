package com.project.servlet;

import com.project.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class CompletedMatchesServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

        var filterByPlayerName = request.getParameter("playerName");
        var page = request.getParameter("page");

        try {
            var finishedMatches = finishedMatchesPersistenceService.getFinishedMatches(filterByPlayerName, page);

            request.setAttribute("currentPage", finishedMatches.currentPage());
            request.setAttribute("totalPages", finishedMatches.totalPages());
            request.setAttribute("initialDigitOfNumbering", finishedMatches.initialDigitOfPageNumber());
            request.setAttribute("matches", finishedMatches.finishedMatchDto());

        } catch (Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.setAttribute("filterByPlayerName", filterByPlayerName);
        request.setAttribute("new_match_url", "home-page");
        request.getRequestDispatcher("/WEB-INF/completed-matches.jsp")
                .forward(request, response);
    }
}