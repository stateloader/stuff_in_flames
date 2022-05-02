package com.example.springis.controllers;

import com.example.springis.myclass.Drivers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

  @RequestMapping("/enterid")
  public List<Integer> enterid(@RequestParam(required = false) int length) {
    List<Integer> listis = createList(length);
    return listis;
  }

  public List<Integer> createList(int length) {

    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      list.add(i);
    }
    return list;
  }
}