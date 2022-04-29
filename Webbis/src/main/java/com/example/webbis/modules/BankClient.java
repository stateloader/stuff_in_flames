package com.example.webbis.modules;

public class BankClient {

  private boolean online;

  private String firstname;
  private String lastname;
  private int password;

  public BankClient() {};

  public String getFirstname() {return firstname;}
  public String getLastname() {return lastname;}
  public int getPassword() {return password;}

  public void setPassword(int password) {this.password = password;}
  public void setFirstname(String firstname) {this.firstname = firstname;}
  public void setLastname(String lastname) {this.lastname = lastname;}
}
