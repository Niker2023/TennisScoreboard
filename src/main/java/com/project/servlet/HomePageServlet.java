package com.project.servlet;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/home-page")
public class HomePageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("new_match_url", "new-match");
        request.setAttribute("matches_url", "matches");
        request.getRequestDispatcher("/WEB-INF/home-page.jsp")
                .forward(request, response);
    }
}