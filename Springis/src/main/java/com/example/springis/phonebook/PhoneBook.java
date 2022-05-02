package com.example.springis.phonebook;

public class PhoneBook {

  private int id;
  private String firstname;
  private String lastname;
  private String phoneNumber;

  public PhoneBook(int id, String firstname, String lastname, String phoneNumber) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.phoneNumber = phoneNumber;
  }

  public int getId() {return id;}
  public String getFirstname() {return firstname;}
  public String getLastname() {return lastname;}
  public String getPhoneNumber() {return phoneNumber;}

  public void setId(int id) {this.id = id;}
  public void setFirstname(String firstname) {this.firstname = firstname;}
  public void setLastname(String lastname) {this.lastname = lastname;}
  public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}
}
