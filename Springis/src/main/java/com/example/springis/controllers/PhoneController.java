package com.example.springis.controllers;

import com.example.springis.phonebook.PhoneEntry;
import com.example.springis.phonebook.PhoneBook;
import com.example.springis.response.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//11327766
import static java.lang.Boolean.*;

@RestController
public class PhoneController {

  PhoneBook phoneBook = new PhoneBook();

  @RequestMapping("/phonebook")
  public List<PhoneEntry> readPhoneBook() {
    return phoneBook.getPhoneBook();
  }

  @RequestMapping("/phonebook/search_id/{id}")
  public PhoneEntry readById(@PathVariable int id) {
    return phoneBook.getById(id);
  }

  @RequestMapping("/phonebook/search_firstname/{firstname}")
  public PhoneEntry readByFirstName(@PathVariable String firstname) {
    return phoneBook.getByFirstName(firstname);
  }

  @RequestMapping("/phonebook/search_lastname/{lastname}")
  public PhoneEntry readByLastName(@PathVariable String lastname) {
    return phoneBook.getByLastName(lastname);
  }

  @PostMapping("phonebook/add")
  public Response addEntry(@RequestBody PhoneEntry book) {
    Response response = new Response("Added new entry", FALSE);
    book.setId(phoneBook.getBookLength() + 1);
    phoneBook.addEntry(book);
    response.setStatus(TRUE);
    return response;
  }
}