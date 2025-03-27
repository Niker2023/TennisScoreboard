package com.project.servlet;

import com.project.exception.HibernateException;
import com.project.service.FinishedMatchesPersistenceService;
import com.project.service.MatchScoreCalculationService;
import com.project.service.OngoingMatchesService;
import com.project.util.MatchScoreViewUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID currentUuid = UUID.fromString(request.getParameter("uuid"));
        var currentMatch = OngoingMatchesService.getMatch(currentUuid);
        HttpServletRequest filledRequest = MatchScoreViewUtil.getFilledRequest(request, currentMatch);
        filledRequest.setAttribute("uuid", currentUuid);
        request.getRequestDispatcher("/WEB-INF/match-score.jsp")
                .forward(filledRequest, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UUID currentUuid = UUID.fromString(request.getParameter("uuid"));
        String winner = request.getParameter("winner");
        var currentMatch = OngoingMatchesService.getMatch(currentUuid);
        MatchScoreCalculationService.changeMatchScore(currentMatch, winner);
        HttpServletRequest filledRequest = MatchScoreViewUtil.getFilledRequest(request, currentMatch);
        filledRequest.setAttribute("uuid", currentUuid);
        request.getRequestDispatcher("/WEB-INF/match-score.jsp")
                .forward(filledRequest, response);
        if (currentMatch.isMatchOver()) {
            try {
                var finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
                finishedMatchesPersistenceService.save(currentMatch);
            } catch (HibernateException e) {
                log.error(e.getMessage(), e);
            }
            OngoingMatchesService.removeMatch(currentUuid);
        }
    }
}