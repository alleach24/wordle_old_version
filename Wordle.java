/* Created by: Andrea Leach
   Date: 09 FEB 2022
   Detailed description goes here
*/

// import statements go here
import java.io.*;
import java.util.Set;
import java.util.HashMap;

public class Wordle {
   
   // main method
   public static void main(String[] args) {
      
      // read in the list of all possible words that exist
      // take in a file name from entered arguments
      File file = new File(args[0]);
      // create a new reader for the file
      BufferedReader buffReader = new BufferedReader(new FileReader(file));
      // initiate a string
      String str;
      // initiate a Set
      Set<String> possilbeWords = new HashSet<String> ();
      // read through all the strings in the file and add them to the set if they contain 5 letters
      while ((str = buffReader.readLine()) != null) {
         if (str.length() == 5) {
            possibleWords.add(str);
         }
      }     
      
      
      
      
   }
   
   
   
   // create a list of possible words
   public Set<String> createPossilbeWords() {
      
      
   }
   
}

