package com.example.springis.controllers;

import com.example.springis.myclass.PhoneBook;
import com.example.springis.phonedao.PhoneDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PhoneBookController {

  PhoneDao phoneDao = new PhoneDao();

  @RequestMapping(value="/phonebook", produces = "application/JSON")
  public List<PhoneBook> index() {
    return phoneDao.getAllPhones();
  }
}