/* Created by: Andrea Leach
Date: 17 FEB 2022
Short Description: class that lets the user play a wordle game

Detailed Description: detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.*;

public class WordlePlay {
  
  public static void main(String[] args) {
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
    int count;
    
    while (play) {
      mygame.setUnknownAnswer();
      answer = mygame.getAnswer();
      
      
      System.out.print("Enter your first guess: ");
      guess = keyboard.nextLine();
      count++;
      
      eval = evaluateGuess(answer, guess);
      printResults(guess, eval);
      
      while (eval != [2, 2, 2, 2, 2]) {
        System.out.print("Enter your next guess: ");
        guess = keyboard.nextLine();
        count++;
        eval = evaluateGuess(answer,guess);
        printResults(guess,eval);
      }
      
      System.out.println();
      System.out.println("Congrats, you guessed the word! It took you " + count + " guesses to guess the word " + answer + "!");
  }
  
  
  public static int[] evaluateGuess(String answer, String guess) {
    char[] answerArr = answer.toArray();
    char[] guessArr = guess.toArray();
    int[] eval = new int[5];
      
    for (int i = 0; i<5; i++) {
      if (answerArr[i] == guessArr[i]) {
        eval[i] = 2;
      }
      else if (answer.contains(Character.valueOf(guessArr[i]))) {
        eval[i] = 1;
      }
      else {
        eval[i] = 0;
      }
    }
    
    return eval;
  }
  
  
  public static void printResults(String guess, int[] eval) {
   
    for (number : eval) {
      if (number == 0) {
        System.out.print("x  ");
      }
      else if (number == 1) {
        System.out.print("○  ");
      }
      else {
        System.out.print("♥  ");
      }
      
      System.out.println();
      
      for (letter : guess) {
      System.out.print(letter + "  ");
    }
  }
  
  
  
  
}
