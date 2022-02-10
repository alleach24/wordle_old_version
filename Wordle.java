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
            possibleWords.add(word);
         }
      }   
   }
   
   
   /* 
   // create a list of possible words
   public Set<String> createPossilbeWords() {
      
      
   }
   */ 
   
   // calculate the next word
   public String bestGuess(Set<String> possibleWords) {
      // create a map and instantiate it with the 26 letters and a count of 0 for each
      Map<Character, Double> letter_counts = new HashMap<Character, Double>();
      letter_counts.put('a', 0.0);
      letter_counts.put('b', 0.0);
      letter_counts.put('c', 0.0);
      letter_counts.put('d', 0.0);
      letter_counts.put('e', 0.0);
      letter_counts.put('f', 0.0);
      letter_counts.put('g', 0.0);
      letter_counts.put('h', 0.0);
      letter_counts.put('i', 0.0);
      letter_counts.put('j', 0.0);
      letter_counts.put('k', 0.0);
      letter_counts.put('l', 0.0);
      letter_counts.put('m', 0.0);
      letter_counts.put('n', 0.0);
      letter_counts.put('o', 0.0);
      letter_counts.put('p', 0.0);
      letter_counts.put('q', 0.0);
      letter_counts.put('r', 0.0);
      letter_counts.put('s', 0.0);
      letter_counts.put('t', 0.0);
      letter_counts.put('u', 0.0);
      letter_counts.put('v', 0.0);
      letter_counts.put('w', 0.0);
      letter_counts.put('x', 0.0);
      letter_counts.put('y', 0.0);
      letter_counts.put('z', 0.0);
      
      // create an int to count the total number of possible words in the argument
      int wordCount = possibleWords.size();
      
      Set<Character> lettersSet = new LinkedHashSet<Character>();
      for (String word : possibleWords) {
         Character[] wordArray = {word.charAt(0), word.charAt(1), word.charAt(2), word.charAt(3), word.charAt(4)};
         lettersSet.clear();
         lettersSet.addAll(Arrays.asList(wordArray));
         
         for (char letter : lettersSet) {
            letter_counts.put(letter, (letter_counts.get(letter) + 1));
         }
      }
      
      for (char key : letter_counts.ketSet()) {
         letter_counts.put(key, letter_counts.get(key) / wordCount * 100);
      }
      
      System.out.println(letter_counts);
      return "yay";
   }
}

