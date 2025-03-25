package com.project.servlet;

import com.project.dto.FinishedMatchDto;
import com.project.service.FinishedMatchesPersistenceService;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/matches")
public class CompletedMatchesServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNumber;

        var filterByPlayerName = request.getParameter("playerName");

        var page = request.getParameter("page");

        if (page == null || page.isBlank() || Integer.parseInt(page) < 1) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(request.getParameter("page"));
        }

        FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();

        List<FinishedMatchDto> finishedMatches = new ArrayList<>();

        Long numberOfPages = 0L;

        if (filterByPlayerName == null || filterByPlayerName.isBlank()) {
            finishedMatches = finishedMatchesPersistenceService.getFinishedMatches(pageNumber);
            numberOfPages = finishedMatchesPersistenceService.getNumberOfPages();
        } else {
            try {
                finishedMatches = finishedMatchesPersistenceService.getFinishedMatchesByPlayerName(filterByPlayerName, pageNumber);
                numberOfPages = finishedMatchesPersistenceService.getNumberOfPagesByName(filterByPlayerName);
                request.setAttribute("filterByPlayerName", filterByPlayerName);
            } catch (PersistenceException e) {
                request.setAttribute("NoResultException", true);
            }
        }

        request.setAttribute("currentPage", pageNumber);
        request.setAttribute("totalPages", numberOfPages);
        request.setAttribute("numberOfLinesPerPage", finishedMatchesPersistenceService.getNumberOfLinesPerPage());
        request.setAttribute("matches", finishedMatches);

        request.getRequestDispatcher("/WEB-INF/completed-matches.jsp")
                .forward(request, response);
    }
}
