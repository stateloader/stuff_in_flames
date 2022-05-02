package com.example.springis.phonebookdao;

import com.example.springis.phonebook.PhoneBook;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookDao {

  List<PhoneBook> phoneBook;

  public PhoneBookDao() {setPhoneBook();}

  private void setPhoneBook () {

    this.phoneBook = new ArrayList<>();

    PhoneBook phone1 = new PhoneBook(1, "Mongo1", "Mongo1sson", "07012345678");
    PhoneBook phone2 = new PhoneBook(2, "Mongo2", "Mongo2sson", "07012345678");
    PhoneBook phone3 = new PhoneBook(3, "Mongo3", "Mongo3sson", "07012345678");
    PhoneBook phone4 = new PhoneBook(4, "Mongo4", "Mongo4sson", "07012345678");
    PhoneBook phone5 = new PhoneBook(5, "Mongo5", "Mongo5sson", "07012345678");

    this.phoneBook.add(phone1);
    this.phoneBook.add(phone2);
    this.phoneBook.add(phone3);
    this.phoneBook.add(phone4);
    this.phoneBook.add(phone5);
  }

  public PhoneBook getById(int i) {return phoneBook.get(i);}

  public PhoneBook getByFirstName(String firstname) {

    for (int i = 0; i < phoneBook.size(); i++) {
      if (phoneBook.get(i).getFirstname().equals(firstname))
        return phoneBook.get(i);
    }
    return null;
  }

  public PhoneBook getByLastName(String lastname) {

    for (int i = 0; i < phoneBook.size(); i++) {
      if (phoneBook.get(i).getLastname().equals(lastname))
        return phoneBook.get(i);
    }
    return null;
  }

  public PhoneBook getByPhoneNumber(String phoneNumber) {

    for (int i = 0; i < phoneBook.size(); i++) {
      if (phoneBook.get(i).getLastname().equals(phoneNumber))
        return phoneBook.get(i);
    }
    return null;
  }
}
