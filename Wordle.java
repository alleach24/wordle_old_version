/* Created by: Andrea Leach
   Date: 09 FEB 2022
   Detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.BubfferedReader;
import java.io.FileReader;

public class Wordle {
   
   // main method
   public static void main(String[] args) throws Exception {
      
      /*
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
      */
      
      String line;
      Set<String> possibleWords = new HashSet<String>();
      FileReader file = new FileReader(args[0]);
      BufferedReader br = new BufferedReader(file);
      while ((line = br.readLine()) != null) {
         if (line.length() == 5) {
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

