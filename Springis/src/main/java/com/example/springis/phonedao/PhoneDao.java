package com.example.springis.phonedao;

import com.example.springis.myclass.PhoneBook;

import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

  public List<PhoneBook> getAllPhones() {
    List<PhoneBook> phoneList = new ArrayList<>();
    PhoneBook phone1 = new PhoneBook(1, "Gurra", "Gurris");
    PhoneBook phone2 = new PhoneBook(2, "Malle", "Mallis");
    PhoneBook phone3 = new PhoneBook(3, "Drulle", "Drullis");
    PhoneBook phone4 = new PhoneBook(4, "Mongo", "Mongis");
    PhoneBook phone5 = new PhoneBook(5, "Hejja", "Hejjis");

    phoneList.add(phone1);
    phoneList.add(phone2);
    phoneList.add(phone3);
    phoneList.add(phone4);
    phoneList.add(phone5);

    return phoneList;
  }
}
