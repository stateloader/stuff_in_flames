package com.example.webbis;

import jackebank.BankClient;

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

    //System.out.println("Inside doGet, ClientMain");
    client.setFirstname(request.getParameter("login_first_name"));
    client.setLastname(request.getParameter("login_last_name"));

    PrintWriter printer = response.getWriter();
    printer.println("<h1>Hello " + client.getFirstname() + " " + client.getLastname() + "</h1>");
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

   // System.out.println("Inside doPost, ClientMain");
    String firstname = request.getParameter("login_first_name");
    String lastname = request.getParameter("login_last_name");

    PrintWriter printer = response.getWriter();
    printer.println("<h1>Hello "+firstname+" "+lastname+"</h1>");
  }
}
