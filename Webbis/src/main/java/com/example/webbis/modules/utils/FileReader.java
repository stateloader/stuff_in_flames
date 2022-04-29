package com.example.webbis.modules.utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileReader {

  Scanner fileScan;

  List<String> lines;
  List<String> items;

  FileReader(String filename) {

    try {
      File file = new File(filename);
      Scanner scanFile = new Scanner(file);

      lineParser();
      itemParser();

    } catch (FileNotFoundException exception) {
      System.out.println("Couldn't open file: " + filename);
      exception.printStackTrace();
    }
  }
  private void lineParser() {
    while (fileScan.hasNextLine())
      this.lines.add(fileScan.nextLine());
  }
  private void itemParser() {
    StringTokenizer tokenizer = new StringTokenizer(",");
    while (tokenizer.hasMoreTokens()) {
      this.items.add(tokenizer.nextToken());
    }
  }
}
