package com.ip.servlet;

import com.ip.DBHelper;
import com.ip.entity.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/create-user")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String zipCode = request.getParameter("zipCode");

        User user = new User(firstName, lastName, email, phoneNumber);
        user.setZipCode(zipCode);

        if (DBHelper.add(user)) {
            response.getWriter().println("User created successfully!");
        } else {
            response.getWriter().println("Failed to create user");
        }
    }
}
