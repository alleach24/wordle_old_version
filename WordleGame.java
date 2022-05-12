/* Created by: Andrea Leach
   Date: 10 FEB 2022
   Short Description: class that creates a Wordle Game
   
   Detailed Description: Detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;
import java.io.*;

public class WordleGame {
   public static Set<String> guessWordList;
   public static Set<String> solutionWordList;
   public static String answer;

  
   
   public static void main(String[] args) {
      
   }
  
   
   
   public void setWordLists() throws IOException {
      // CREATE THE LIST OF POSSIBLE GUESSES USING THE PROVIDED FILE
      // initiate a new string to hold each word
      String word;
      // initiate a new Set to hold all the possible words
      solutionWordList = new HashSet<String>();
      // create a keyboard scanner and prompt for the filename
      /////////////Scanner keyboard = new Scanner(System.in);
      /////////////System.out.print("Enter the filename for the solution word bank: ");
      String solutionfilename = "wordle_solutions.txt";
      FileReader file1;
      try {
         // create a file reader using the argument input
         file1 = new FileReader(solutionfilename);
      } catch (FileNotFoundException fnfe) {
         System.out.print("This file does not exist. Please enter a valid file or the game will crash: ");
         solutionfilename = keyboard.nextLine();
         file1 = new FileReader(solutionfilename);
      }
      // create a buffered reader
      BufferedReader br1 = new BufferedReader(file1);
      // run through each line of the file
      ////////////////////////System.out.println("These are the possible words: ");
      while ((word = br1.readLine()) != null) {
         // add the word to the Set of possible words if the word has 5 letters
         if (word.length() == 5) {
            solutionWordList.add(word);
            ////////////////////////System.out.println(word);
         }
      }   
            
      guessWordList = new HashSet<String>();
      // System.out.println();
      ///////////////System.out.print("Enter the filename for the guesses word bank: ");
      String guessfilename = "wordle_guesses.txt";
      FileReader file2;
      try {
         file2 = new FileReader(guessfilename);
      } catch (FileNotFoundException fnfe) {
         System.out.print("This file does not exist. Please enter a valid file or the game will crash: ");
         guessfilename = keyboard.nextLine();
         file2 = new FileReader(guessfilename);
      }
      BufferedReader br2 = new BufferedReader(file2);
      while ((word = br2.readLine()) != null) {
         if (word.length() == 5) {
            guessWordList.add(word);
         }
      }
      
   }
   
   
   
   
   public void setKnownAnswer() {
     // take in the end word and set it as answer
      System.out.println();
      System.out.print("Enter the word to be optimized: ");
      Scanner keyboard = new Scanner(System.in);
      answer = keyboard.nextLine();
      // check to make sure the answer is included in the provided list
      while (true) {
         if (!solutionWordList.contains(answer)) {
            System.out.print("This word is not a valid solution. Please enter a different word: ");
            answer = keyboard.nextLine();
         }
         else {
            break; }
      }
  }
  
   
  public void setUnknownAnswer() {
      // pick a random word as the answer
     int size = solutionWordList.size();
     
     int currindx = 0;
     int rndmindx = new Random().nextInt(size);
     
     for (String word : solutionWordList) {
         if (currindx == rndmindx) {
            answer = word;
            System.out.println(answer);
            return;
         }            
         currindx++;
     }
     answer = "zzzzz";
  }
   
   
   
  public String getAnswer() {
    return answer;
  }
  
  public Set<String> getSolutionWordList() {
    return solutionWordList;
  }
  
   public Set<String> getGuessWordList() {
    return guessWordList;
  }
  
}
