/* Created by: Andrea Leach
Date: 17 FEB 2022
Short Description: class that lets the user play a wordle game

Detailed Description: detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.*;

public class WordlePlay {
  
  public static void main(String[] args) throws IOException {
      // create a boolean that you want to play a game
      boolean play = false;
      
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Do you want to play Wordle? y/n: ");
      String playString = keyboard.nextLine();
      if (playString.equals("y") || playString.equals("Y")) {
         play = true;
      } else {
         System.exit(0);
      } // maybe eventually add functionality for someone typing something other than "y" or "n"
    
    WordleGame mygame = new WordleGame();
    mygame.setWordLists();
    String answer;
    String guess;
    int[] eval;
    int count = 0;
    int[] gotIt = new int[]{2,2,2,2,2};
    
    while (play) {
      mygame.setUnknownAnswer();
      answer = mygame.getAnswer();
      
      
      System.out.print("Enter your first guess: ");
      guess = keyboard.nextLine();
      count++;
      
      eval = evaluateGuess(answer, guess);
      printResults(guess, eval);
      
      while (!(eval.equals(gotIt))) {
        System.out.print("Enter your next guess: ");
        guess = keyboard.nextLine();
        count++;
        eval = evaluateGuess(answer,guess);
        printResults(guess,eval);
      }
      
      System.out.println();
      System.out.println("Congrats, you guessed the word! It took you " + count + " guesses to guess the word " + answer + "!");
      System.out.println();
      System.out.println();
      
      System.out.print("Do you want to play again? y/n: ");
      if (!(keyboard.nextLine().equals("y")) || !(keyboard.nextLine().equals("Y"))) {
        play = false;    
      }
      else {
        System.out.println();
        System.out.println("Okay let's play again!");
      }
    }
      
  }
  
  
  public static int[] evaluateGuess(String answer, String guess) {
    //char[] answerArr = answer.toArray();
    //char[] guessArr = guess.toArray();
    int[] eval = new int[5];
      
    for (int i = 0; i<5; i++) {
      if (answer.charAt(i) == guess.charAt(i)) {
        //////////////System.out.println(answer.charAt(i));
        ///////////////System.out.println(guess.charAt(i));
        //////////////System.out.println(answer.charAt(i) == guess.charAt(i));
        eval[i] = 2;
      }
      else if (answer.contains(Character.toString(guess.charAt(i)))) {
        eval[i] = 1;
      }
      else {
        eval[i] = 0;
      }
    }
    
    
    ///////////////////System.out.println(eval);
    
    return eval;
  }
  
  
  public static void printResults(String guess, int[] eval) {
    System.out.println();
    for (int number : eval) {
      if (number == 0) {
        System.out.print("x  ");
      }
      else if (number == 1) {
        System.out.print("o  ");
      }
      else {
        System.out.print("^  ");
      }
    }  
    System.out.println();
      
    for (int i = 0; i < 5; i++) {
      System.out.print(guess.charAt(i) + "  ");
    }
    System.out.println();
    System.out.println();
  }
  
  
  
  
}
