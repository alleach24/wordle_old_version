/* Created by: Andrea Leach
   Date: 09 FEB 2022
   Detailed description goes here
*/

// import statements go here
import java.io.*;
import java.util.*;

public class Wordle {
   
   // main method
   public static void main(String[] args) {
      
      // read in the list of all possible words that exist
      // take in a file name from entered arguments
      File file = new File(args[0]);
      // create a new reader for the file
      Scanner scanner = new Scanner(file);
      // create a new Set
      Set<String> possibleWords = new HashSet<String>();
      // read through all the strings in the file and add them to the set if they contain 5 letters
      while (scanner.hasNextLine()) {
         if (scanner.next() == 5) {
            possibleWords.add(scanner.next());
         }
      }     
      
      
      
      
   }
   
   
   /* 
   // create a list of possible words
   public Set<String> createPossilbeWords() {
      
      
   }
   */ 
}

