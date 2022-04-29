package com.example.webbis;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginForm", value = "/LoginForm")
public class LoginForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      response.setContentType("text/html;charset=UTF-8");
      RequestDispatcher rs = request.getRequestDispatcher("login_form.html");
      rs.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}