package com.ip;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

//Works even less than Login I'd say. The url is important as you're always forced to move there on a proper action call.
//Seems like only <form> submissions properly call things. <button>s won't and it doesnt get called automatically.
@WebServlet("/hello")
public class Hello extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("Hello, World!");
        req.getRequestDispatcher("/WEB-INF/coffee_preferences.html").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("user", username);
        PrintWriter out = resp.getWriter();
        out.println("<p>ghfhgf</p>");
        //resp.sendRedirect("login.html");
    }
}