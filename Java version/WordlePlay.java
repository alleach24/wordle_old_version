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
    Set<String> validGuesses = mygame.getGuessWordList();
    
    System.out.printf("%n%nAnswer Key:%nx = this letter does not appear in the answer%no = this letter appears in the answer in a different spot%n^ = this letter appears in the correct spot");
    
    while (play) {
      mygame.setUnknownAnswer();
      answer = mygame.getAnswer();
      
      
      System.out.printf("%n%nEnter your first guess: ");
      guess = keyboard.nextLine();
      guess = checkWord(guess, validGuesses);
      count++;
      
      eval = evaluateGuess(answer, guess);
      printResults(guess, eval);
      
      while (!(Arrays.equals(eval,gotIt))) {
        System.out.print("Enter your next guess: ");
        guess = keyboard.nextLine();
        guess = checkWord(guess, validGuesses);
        count++;
        eval = evaluateGuess(answer,guess);
        printResults(guess,eval);
      }
      

      System.out.printf("%nCongrats, you guessed the word! It took you " + count + " guesses to guess the word " + answer + "!%n%n");

      
      System.out.print("Do you want to play again? y/n: ");
      playString = keyboard.nextLine();
      if (playString.equals("n") || playString.equals("N")) {
        play = false;    
      }
      else {
        System.out.println();
        System.out.println("Okay let's play again!");
      }
    }
  }
  
  
  public static String checkWord(String guess, Set<String> validGuesses) {
    boolean valid = validGuesses.contains(guess);
    Scanner keyboard = new Scanner(System.in);
    while (!valid) {
      System.out.println("This is not a valid guess.");
      System.out.print("Please enter a new guess: ");
      guess = keyboard.nextLine();
      valid = validGuesses.contains(guess);
    }  
    return guess;
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
    
    for (int i = 0; i < 5; i++) {
      System.out.print(guess.charAt(i) + "  ");
    }
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
    System.out.println();
  }
  
  
  
  
}
