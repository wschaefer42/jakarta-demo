package com.example.frontendweb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/greeting") // Do not forget the /
public class GreetingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("name");
        String message = name != null ? name : "Nobody";
        req.setAttribute("message", message);
        req.getRequestDispatcher("welcome.jsp").forward(req, res);
    }
}
