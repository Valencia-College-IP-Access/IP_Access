package com.ip;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

//Seems like only <form> submissions with action= and method= properly call things. <button>s won't and it doesnt get called automatically.
@WebServlet("/IP_Access/IPAccess/Login.html")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Suspect this is what makes the writer output html.
        // I don't know how to get it to output to even the inspect element console.
        //This is proper formatting, as far as I can tell.
        HttpSession session = req.getSession();
        session.setAttribute("user", "nothing was input");
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<html><p>sdfsdfsdf</p></html>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //Copied from Hello. Managed to save attributes to the url.
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        session.setAttribute("user", username);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<br><p>rdfg</p><br>");
    }
}
