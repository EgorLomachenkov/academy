package com.example.webapp;

import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "LoginServlet", value = "/login1")
public class LoginServlet extends HttpServlet {
    private UserDAOImpl userDAO = new UserDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Login page request " + new Date());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String email = request.getParameter("email");
        final String pwd = request.getParameter("pwd");
        User user = null;
        if ((user = userDAO.findUserByEmail(email)) != null) {
            if (user.getPassword().equals(pwd)) {
                //forward
                return;
            } else {
                response.getWriter().println("Bad credentials");
                return;
            }
        }

    }
}
