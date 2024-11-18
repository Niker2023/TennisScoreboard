package com.project.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("player1", "new-match");
        request.setAttribute("player2", "matches");

        request.getRequestDispatcher("/WEB-INF/match-score.jsp")
                .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
