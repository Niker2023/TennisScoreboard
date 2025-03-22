package com.project.servlet;

import com.project.dto.FinishedMatchDto;
import com.project.service.FinishedMatchesPersistenceService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/matches")
public class CompletedMatchesServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var filterByPlayerName = request.getParameter("playerName");

        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

        List<FinishedMatchDto> finishedMatches = new ArrayList<>();

        if (filterByPlayerName == null || filterByPlayerName.isBlank()) {
            finishedMatches = finishedMatchesPersistenceService.getFinishedMatches();
        } else {
            try {
                finishedMatches = finishedMatchesPersistenceService.getFinishedMatchesByPlayerName(filterByPlayerName);
            } catch (PersistenceException e) {
                request.setAttribute("NoResultException", true);
            }
        }

        request.setAttribute("matches", finishedMatches);

        request.getRequestDispatcher("/WEB-INF/completed-matches.jsp")
                .forward(request, response);
    }
}
