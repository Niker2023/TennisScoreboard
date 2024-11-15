package com.project.servlet;

import com.project.service.PlayersPersistenceService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("new_match_url", "new-match");
        request.setAttribute("matches_url", "matches");

        request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var player1 = request.getParameter("player1");
        var player2 = request.getParameter("player2");

        if (player1 != null && player1.equals(player2)) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имена игроков одинаковы!");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        var playersPersistenceService = PlayersPersistenceService.getInstance();
        try {
            playersPersistenceService.save(player1);
        } catch (PersistenceException e) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имя первого игрока уже существует в базе данных! Введите другое.");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        try {
            playersPersistenceService.save(player2);
        } catch (PersistenceException e) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имя второго игрока уже существует в базе данных! Введите другое.");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }



        request.setAttribute("new_match_url", "new-match");
        request.setAttribute("matches_url", "matches");

        request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                .forward(request, response);
    }
}
