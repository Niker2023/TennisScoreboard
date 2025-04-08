package com.project.servlet;

import com.project.service.FinishedMatchesPersistenceService;
import com.project.service.OngoingMatchesService;
import com.project.util.MatchScoreViewUtil;
import com.project.util.ValidationUtil;
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

    private OngoingMatchesService ongoingMatchesService;

    @Override
    public void init() {
        ongoingMatchesService = new OngoingMatchesService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpServletRequest filledRequest;

        try {
            ValidationUtil.isUUID(request.getParameter("uuid"));

            UUID currentUuid = UUID.fromString(request.getParameter("uuid"));

            var currentMatch = ongoingMatchesService.getMatch(currentUuid);
            filledRequest = MatchScoreViewUtil.getFilledRequest(request, currentMatch);

            filledRequest.setAttribute("uuid", currentUuid);
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
            ValidationUtil.isUUID(request.getParameter("uuid"));

            var currentUuid = UUID.fromString(request.getParameter("uuid"));
            String playerWinnerOrder = request.getParameter("winner");
            var currentMatch = ongoingMatchesService.getMatch(currentUuid);

            currentMatch.playerWinsPointByPlayerOrder(playerWinnerOrder);

            filledRequest = MatchScoreViewUtil.getFilledRequest(request, currentMatch);
            filledRequest.setAttribute("uuid", currentUuid);

            if (currentMatch.isMatchOver()) {
                var finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

                finishedMatchesPersistenceService.save(currentMatch);
                ongoingMatchesService.removeMatch(currentUuid);
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