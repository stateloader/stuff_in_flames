package com.example.springis.phonebook;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

  private int bookLength;
  private List<PhoneEntry> phoneBook;

  public PhoneBook() {setPhoneBook();}

  private void setPhoneBook () {
    this.phoneBook = new ArrayList<>();
    this.bookLength = 0;
  }

  public List<PhoneEntry> getPhoneBook() {return phoneBook;}
  public int getBookLength() {return bookLength;}

  public PhoneEntry getById(int i) {return phoneBook.get(i);}

  public PhoneEntry getByFirstName(String firstname) {

    for (int i = 0; i < phoneBook.size(); i++) {
      if (phoneBook.get(i).getFirstname().equals(firstname))
        return phoneBook.get(i);
    }
    return null;
  }

  public PhoneEntry getByLastName(String lastname) {

    for (int i = 0; i < phoneBook.size(); i++) {
      if (phoneBook.get(i).getLastname().equals(lastname))
        return phoneBook.get(i);
    }
    return null;
  }

  public PhoneEntry getByPhoneNumber(String phoneNumber) {

    for (int i = 0; i < phoneBook.size(); i++) {
      if (phoneBook.get(i).getLastname().equals(phoneNumber))
        return phoneBook.get(i);
    }
    return null;
  }

  public void setBookEntry(PhoneEntry book) {
    this.phoneBook.add(book);
    this.bookLength++;
  }
}
