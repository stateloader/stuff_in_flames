package com.example.springis.controllers;

import com.example.springis.phonebook.PhoneBook;
import com.example.springis.phonebookdao.PhoneBookDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneBookController {

  PhoneBookDao phoneDao = new PhoneBookDao();

  @RequestMapping("/phonebook/search_id/{id}")
  public PhoneBook parseById(@PathVariable int id) {
    return phoneDao.getById(id);
  }

  @RequestMapping("/phonebook/search_firstname/{firstname}")
  public PhoneBook parseByFirstName(@PathVariable String firstname) {
    return phoneDao.getByFirstName(firstname);
  }

  @RequestMapping("/phonebook/search_lastname/{lastname}")
  public PhoneBook parseByLastName(@PathVariable String lastname) {
    return phoneDao.getByLastName(lastname);
  }
}