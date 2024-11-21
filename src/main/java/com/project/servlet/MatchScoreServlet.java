package com.project.servlet;

import com.project.service.MatchScoreCalculationService;
import com.project.service.OngoingMatchesService;
import com.project.util.MatchScoreViewUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

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

        MatchScoreCalculationService.calculate(currentMatch, winner);

        HttpServletRequest filledRequest = MatchScoreViewUtil.getFilledRequest(request, currentMatch);

        filledRequest.setAttribute("uuid", currentUuid);

        request.getRequestDispatcher("/WEB-INF/match-score.jsp")
                .forward(filledRequest, response);
    }
}
