package com.project.servlet;

import com.project.dto.PlayerDto;
import com.project.entity.MatchScore;
import com.project.entity.Players;
import com.project.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var player1 = request.getParameter("player1");
        var player2 = request.getParameter("player2");

        request.setAttribute("player1", "%s".formatted(player1));
        request.setAttribute("player2", "%s".formatted(player2));

        request.getRequestDispatcher("/WEB-INF/match-score.jsp")
                .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
