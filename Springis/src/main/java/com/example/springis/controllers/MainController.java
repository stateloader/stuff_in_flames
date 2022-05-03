package com.example.springis.controllers;

import com.example.springis.phonebook.PhoneEntry;
import com.example.springis.phonebook.PhoneBook;
import com.example.springis.response.ResponseJSON;
import com.example.springis.response.ResponseHTML;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Boolean.*;

@RestController
public class MainController {

  PhoneBook phoneDao = new PhoneBook();

  @RequestMapping(value = "/")
  public String index() {
    return ResponseHTML.sayWhat("Hello?");
  }

  @RequestMapping("/phonebook")
  public List<PhoneEntry> readPhoneBook() {
    return phoneDao.getPhoneBook();
  }

  @RequestMapping("/phonebook/search_id/{id}")
  public PhoneEntry readById(@PathVariable int id) {
    return phoneDao.getById(id);
  }

  @RequestMapping("/phonebook/search_firstname/{firstname}")
  public PhoneEntry readByFirstName(@PathVariable String firstname) {
    return phoneDao.getByFirstName(firstname);
  }

  @RequestMapping("/phonebook/search_lastname/{lastname}")
  public PhoneEntry readByLastName(@PathVariable String lastname) {
    return phoneDao.getByLastName(lastname);
  }

  @PostMapping("phonebook/add")
  public ResponseJSON addEntry(@RequestBody PhoneEntry book) {
   // System.out.println(book.getId() + " " + book.getFirstname() + " " + book.getLastname() + " " + book.getPhoneNumber().toString());
    ResponseJSON responseJSON = new ResponseJSON("Added new entry", FALSE);
    phoneDao.setBookEntry(book);
    responseJSON.setStatus(TRUE);
    return responseJSON;
  }
}