package com.project.servlet;

import com.project.exception.ValidationException;
import com.project.service.MatchCreationService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    private MatchCreationService matchCreationService;

    @Override
    public void init() {
        matchCreationService = new MatchCreationService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            var player1 = request.getParameter("player1");
            var player2 = request.getParameter("player2");

            if (player1 == null || player2 == null || player1.isBlank() || player2.isBlank()) {
                throw new ValidationException("Имя игрока не может быть пустым.");
            }

            UUID newMatchUUID = matchCreationService.createMatch(player1, player2);

            response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + newMatchUUID);

        } catch (Exception e) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
        }
    }
}