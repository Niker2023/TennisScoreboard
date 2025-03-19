package com.project.servlet;

import com.project.service.FinishedMatchesPersistenceService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class CompletedMatchesServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var matches = FinishedMatchesPersistenceService.getInstance().getMatches();

        request.setAttribute("matches", matches);

        request.getRequestDispatcher("/WEB-INF/completed-matches.jsp")
                .forward(request, response);
    }
}
