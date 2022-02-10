/* Created by: Andrea Leach
   Date: 09 FEB 2022
   Detailed description goes here
*/

// import statements go here
import java.io.*;
import java.util.*;

public class Wordle {
   
   // main method
   public static void main(String[] args) throws Exception {
      
      // read in the list of all possible words that exist
      // take in a file name from entered arguments
      Path path = FileSystem.getDefault().getPath(args[0]);
      File file = new File(path);
      // create a new reader for the file
      Scanner scanner = new Scanner(file);
      // create a new Set
      Set<String> possibleWords = new HashSet<String>();
      // read through all the strings in the file and add them to the set if they contain 5 letters
      while (scanner.hasNextLine()) {
         if (scanner.nextLine().length() == 5) {
            possibleWords.add(scanner.nextLine());
         }
      }     
      
      
      
      
   }
   
   
   /* 
   // create a list of possible words
   public Set<String> createPossilbeWords() {
      
      
   }
   */ 
}

