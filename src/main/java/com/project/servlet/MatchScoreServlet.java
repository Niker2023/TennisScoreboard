package com.project.servlet;

import com.project.service.FinishedMatchesPersistenceService;
import com.project.service.OngoingMatchesService;
import com.project.util.MatchScoreViewUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    private OngoingMatchesService ongoingMatchesService;

    @Override
    public void init() {
        ongoingMatchesService = new OngoingMatchesService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpServletRequest filledRequest;

        try {
            String uuid = request.getParameter("uuid");

            var currentMatch = ongoingMatchesService.getMatch(uuid);

            filledRequest = MatchScoreViewUtil.getFilledRequest(request, currentMatch);

            filledRequest.setAttribute("uuid", uuid);
        } catch (Exception e) {
            filledRequest = request;

            request.setAttribute("error", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/match-score.jsp")
                .forward(filledRequest, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpServletRequest filledRequest;

        try {
            String uuid = request.getParameter("uuid");

            String playerWinnerOrder = request.getParameter("winner");

            var currentMatch = ongoingMatchesService.getMatch(uuid);

            currentMatch.playerWinsPointByPlayerOrder(playerWinnerOrder);

            filledRequest = MatchScoreViewUtil.getFilledRequest(request, currentMatch);
            filledRequest.setAttribute("uuid", uuid);

            if (currentMatch.isMatchOver()) {
                var finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

                finishedMatchesPersistenceService.save(currentMatch);
                ongoingMatchesService.removeMatch(uuid);
            }
        } catch (Exception e) {
            filledRequest = request;

            request.setAttribute("error", true);
            request.setAttribute("errorMessage", e.getMessage());
        }

        request.getRequestDispatcher("/WEB-INF/match-score.jsp")
                .forward(filledRequest, response);
    }
}