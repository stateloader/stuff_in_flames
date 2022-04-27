public class Test {

  String test;

  public Test(String test) {
    this.test = test;
  }

  public void printTest() {
    System.out.println("My god it works printing " + this.test);
  }

  public static void main(String[] argv) {
    Test test = new Test("Test");
    test.printTest();
  }
}
