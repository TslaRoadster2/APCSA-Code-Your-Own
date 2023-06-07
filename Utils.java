package APCSA.APCSA_Code_Your_Own;

import java.lang.Math;
import java.util.Scanner;

public class Utils {
  private static Scanner scanner = new Scanner(System.in);

  public static int randInt(int min, int max) {
    return (int) (Math.random() * (max - min + 1)) + min;
  }

  public static String inputString(String question) {
    System.out.print(question);
    return scanner.nextLine();
  }

  public static String inputString(String question, String[] options) {
    boolean inputContained = false;
    String input = "";
    while (!inputContained) {
      System.out.print(question);
      input = scanner.nextLine();
      for (String option : options) {
        if (input.equals(option)) {
          inputContained = true;
        }
      }
    }
    return input;
  }

  public static String inputString(String question, String[] options, String invalidOption, boolean caseSensitive) {
    boolean inputContained = false;
    System.out.print(question);
    String input = "";
    input = scanner.nextLine();
    if (!caseSensitive) {
      input = input.toLowerCase();
    }
    for (String option : options) {
      if (input.equals(option.toLowerCase())) {
        inputContained = true;
      }
    }
    while (!inputContained) {
      System.out.println(invalidOption);
      System.out.print(question);
      input = scanner.nextLine();
      for (String option : options) {
        if (input.equals(option)) {
          inputContained = true;
        }
      }
    }
    return input;
  }

  public static int inputInt(String question) {
    System.out.print(question);
    while (true) {
      try {
        String input = scanner.nextLine();
        return Integer.parseInt(input);
      } catch (Exception e) {
        System.out.print("Please enter an integer: ");
      }
    }
  }

  public static int inputInt(String question, int lowLimit, int highLimit) {
    System.out.print(question);
    while (true) {
      try {
        String input = scanner.nextLine();
        if (Integer.parseInt(input) < lowLimit || Integer.parseInt(input) > highLimit) {
          throw new Exception("Please enter a valid number.");
        }
        return Integer.parseInt(input);
      } catch (Exception e) {
        System.out.print("Please enter an integer between " + lowLimit + " and " + highLimit + ": ");
      }
    }
  }

  public static double inputDouble(String question) {
    System.out.print(question);
    while (true) {
      try {
        String input = scanner.nextLine();
        return Double.parseDouble(input);
      } catch (Exception e) {
        System.out.print("Please enter a number: ");
      }
    }
  }

  public static boolean twoDimensionalArrayInBounds(int row1, int column1, int row2, int column2, int outerLength,
      int innerLength) {
    if (row1 < 0 || column1 < 0 || row2 < 0 || column2 < 0) {
      return false;
    }

    if (row1 >= outerLength || row2 >= outerLength) {
      return false;
    }

    if (column1 >= innerLength || column2 >= innerLength) {
      return false;
    }

    return true;
  }
}