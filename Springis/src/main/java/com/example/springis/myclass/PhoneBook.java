package com.example.springis.myclass;

public class PhoneBook {

  private int id;
  private String first;
  private String last;

  public PhoneBook() {}

  public PhoneBook(int id, String first, String last) {
    this.id = id;
    this.first = first;
    this.last = last;
  }

  public int getId() {return id;}
  public String getFirst() {return first;}
  public String getLast() {return last;}

  public void setId(int id) {this.id = id;}
  public void setFirst(String first) {this.first = first;}
  public void setLast(String last) {this.last = last;}

}
