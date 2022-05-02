import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileReader {

  private int lineCount = 0;
  private int itemCount = 0;

  private List<String> lines = new ArrayList<>();
  private List<String> items = new ArrayList<>();

  Scanner scanner;

  FileReader(String filename) {

    try {
      File file = new File(filename);
      scanner = new Scanner(file);
      lineParser();
      itemParser();
      scanner.close();

    } catch (FileNotFoundException exception) {
      System.out.println("Couldn't read file: " + filename);
      exception.printStackTrace();
    }
  }
  public int getLineCount() {return lineCount;}
  public int getItemCount() {return itemCount;}

  private void lineParser() {

    while (scanner.hasNextLine()) {
      this.lines.add(scanner.nextLine().trim());
      this.lineCount++;
    }
    linePrinter();
  }
  private void itemParser() {

    for (int i = 0; i < this.lines.size(); i++) {
      StringTokenizer token = new StringTokenizer(this.lines.get(i), ",");
      while (token.hasMoreTokens())
        this.items.add(token.nextToken());
    }
    itemPrinter();
  }

  private void linePrinter() {
    System.out.print("lines read: " + getLineCount() + "\n\n");
    for (int i = 0; i < this.lines.size(); i++)
      System.out.println(this.lines.get(i));
  }

  private void itemPrinter() {
    System.out.print("items read: " + getItemCount() + "\n\n");
    for (int i = 0; i < this.items.size(); i++)
      System.out.println(this.items.get(i));
  }


  public static void main(String[] args) {
    FileReader reader = new FileReader("src\\database.txt");
  }
}