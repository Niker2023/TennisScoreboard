package com.project.servlet;

import com.project.dto.PlayerDto;
import com.project.entity.MatchScore;
import com.project.entity.Players;
import com.project.service.OngoingMatchesService;
import com.project.service.PlayersPersistenceService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        Integer playerId1;
        Integer playerId2;
        try {
            playerId1 = playersPersistenceService.save(player1);
        } catch (PersistenceException e) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имя первого игрока уже существует в базе данных! Введите другое.");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        try {
            playerId2 = playersPersistenceService.save(player2);
        } catch (PersistenceException e) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имя второго игрока уже существует в базе данных! Введите другое.");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        MatchScore matchScore = new MatchScore(playerId1, playerId2);
        UUID newMatchUuid = OngoingMatchesService.addMatch(matchScore);
//        request.setAttribute("matchScore", "%s".formatted(matchScore.getUuid()));

        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + newMatchUuid);
    }
}
