package com.example.springis.myclass;

//64114978
public class Drivers {

  String[] animals = {"Dog", "Cat", "Parrot", "Pig"};

  int randomNumber;
  int min = 0, max = 3;

  public Drivers() {}

  public String animalReturn() {return animals[randomNumber()];}

  public String numberReturn() {
    String canonical = null;
    return canonical = Integer.toString(randomNumber());
  }

  private int randomNumber() {
    return this.randomNumber = (int)(Math.random()*(max-min+1)+min);
  }
}
