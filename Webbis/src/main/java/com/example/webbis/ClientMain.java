package com.example.webbis;

import com.example.webbis.modules.BankClient;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ClientMain", value = "/ClientMain")
public class ClientMain extends HttpServlet {

  BankClient client = new BankClient();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    System.out.println("Inside doPost, ClientMain");

    client.setFirstname(request.getParameter("login_first_name"));
    client.setLastname(request.getParameter("login_last_name"));

    PrintWriter printer = response.getWriter();
    printer.println("<h1>Hello " + client.getFirstname() + " " + client.getLastname() + "</h1>");
  }
}
