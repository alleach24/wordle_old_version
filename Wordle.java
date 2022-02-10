/* Created by: Andrea Leach
   Date: 09 FEB 2022
   Detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Wordle {
   
   // main method
   public static void main(String[] args) throws Exception {

      // CREATE THE FIRST LIST OF POSSIBLE LETTERS USING THE PROVIDED LIST
      // initiate a new string to hold each word
      String word;
      // initiate a new Set to hold all the possible words
      Set<String> possibleWords = new HashSet<String>();
      // create a file reader using the argument input
      FileReader file = new FileReader(args[0]);
      // create a buffered reader
      BufferedReader br = new BufferedReader(file);
      // run through each line of the file
      while ((word = br.readLine()) != null) {
         // add the word to the Set of possible words if the word has 5 letters
         if (word.length() == 5) {
            possibleWords.add(line);
         }
      }
         
      
      
      
      
   }
   
   
   /* 
   // create a list of possible words
   public Set<String> createPossilbeWords() {
      
      
   }
   */ 
}

