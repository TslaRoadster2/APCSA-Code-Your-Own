package APCSA.APCSA_Code_Your_Own;

import java.lang.Math;
import java.util.Scanner;
  
public class Utils {
  private static Scanner scanner = new Scanner (System.in);
  
  public static int randInt(int min, int max) {
    return (int)(Math.random() * (max - min + 1)) + min;
  }

  public static String inputString (String question) {
    System.out.print (question);
    return scanner.nextLine();
  }

  public static String inputString(String question, String[] options){
    boolean inputContained = false;
    String input = "";
    while (!inputContained){
        System.out.print(question);
        input = scanner.nextLine();
        for (String option : options){
            if (input.equals(option)){
                inputContained = true;
            }
        }
    }
    return input;
  }

  public static String inputString(String question, String[] options, String invalidOption){
    boolean inputContained = false;
    System.out.print(question);
    String input = "";
    input = scanner.nextLine();
    for (String option : options){
        if (input.equals(option)){
            inputContained = true;
        }
    }
    while (!inputContained){
        System.out.println(invalidOption);
        System.out.print(question);
        input = scanner.nextLine();
        for (String option : options){
            if (input.equals(option)){
                inputContained = true;
            }
        }
    }
    return input;
  }


  public static int inputInt (String question) {
    System.out.print (question);
    while (true) {
      try {
        String input = scanner.nextLine();
        return Integer.parseInt(input);
      } catch (Exception e) {
        System.out.print ("Please enter an integer: ");
      }
    }
  }

  public static double inputDouble (String question) {
    System.out.print (question);
    while (true) {
      try {
        String input = scanner.nextLine();
        return Double.parseDouble(input);
      } catch (Exception e) {
        System.out.print ("Please enter a number: ");
      }
    }
  }
}