package com.project.servlet;

import com.project.dto.PlayerDto;
import com.project.entity.MatchScore;
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
import java.util.regex.Pattern;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                .forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var player1 = request.getParameter("player1");
        var player2 = request.getParameter("player2");

        Pattern patternName = Pattern.compile("^[A-Za-zА-Яа-яЁё\\s-.]+$");

        if (player1 == null || player2 == null || player1.isBlank() || player2.isBlank()
                || !patternName.matcher(player1).matches() || !patternName.matcher(player2).matches()) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", """
                    Имя введено некорректно! <br>
                                    Допустимы для ввода: буквы, пробел, точка, дефис.<br>
                                    Максимальное длина имени 20 символов.""");
            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        if (player1.equals(player2)) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имена игроков одинаковы!");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        var playersPersistenceService = new PlayersPersistenceService();
        PlayerDto player1Dto;
        PlayerDto player2Dto;
        try {
            player1Dto = playersPersistenceService.save(player1);
        } catch (PersistenceException e) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имя первого игрока уже существует в базе данных! Введите другое.");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        try {
            player2Dto = playersPersistenceService.save(player2);
        } catch (PersistenceException e) {
            request.setAttribute("error", true);
            request.setAttribute("error_message", "Имя второго игрока уже существует в базе данных! Введите другое.");

            request.getRequestDispatcher("/WEB-INF/new-match.jsp")
                    .forward(request, response);
            return;
        }

        MatchScore newMatch = new MatchScore(player1Dto, player2Dto);
        UUID newMatchUuid = OngoingMatchesService.addMatch(newMatch);

        response.sendRedirect(request.getContextPath() + "/match-score?uuid=" + newMatchUuid);
    }
}
