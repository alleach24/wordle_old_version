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
   public static Set<String> wordList;
   public static String answer;

  
   public static void main(String[] args) {
      
   }
  
   public void setWordList() throws IOException {
      // CREATE THE FIRST LIST OF POSSIBLE LETTERS USING THE PROVIDED LIST
      // initiate a new string to hold each word
      String word;
      // initiate a new Set to hold all the possible words
      wordList = new HashSet<String>();
      // create a keyboard scanner and prompt for the filename
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter the filename for the solution word bank: ");
      String solutionfilename = keyboard.nextLine();
      FileReader file;
      try {
         // create a file reader using the argument input
         file = new FileReader(solutionfilename);
      } catch (FileNotFoundException fnfe) {
         System.out.print("This file does not exist. Please enter a valid file or the game will crash: ");
         solutionfilename = keyboard.nextLine();
         file = new FileReader(solutionfilename);
      }
      // create a buffered reader
      BufferedReader br = new BufferedReader(file);
      // run through each line of the file
      ////////////////////////System.out.println("These are the possible words: ");
      while ((word = br.readLine()) != null) {
         // add the word to the Set of possible words if the word has 5 letters
         if (word.length() == 5) {
            wordList.add(word);
            ////////////////////////System.out.println(word);
         }
      }   
      
   }
   
   
   
   public void setAnswer() {
     // take in the end word and set it as answer
      System.out.println();
      System.out.print("Enter the word to be optimized: ");
      Scanner keyboard = new Scanner(System.in);
      answer = keyboard.nextLine();
      // check to make sure the answer is included in the provided list
      while (true) {
         if (!wordList.contains(answer)) {
            System.out.print("This word is not valid. Please enter a different word: ");
            answer = keyboard.nextLine();
         }
         else {
            break; }
      }
  }
  
  public String getAnswer() {
    return answer;
  }
  
  public Set<String> getWordList() {
    return wordList;
  }
  
  
}
