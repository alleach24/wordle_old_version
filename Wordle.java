/* Created by: Andrea Leach
   Date: 09 FEB 2022
   Detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;

public class Wordle {
   
   // main method
   public static void main(String[] args) throws Exception {

      // CREATE THE FIRST LIST OF POSSIBLE LETTERS USING THE PROVIDED LIST
      // initiate a new string to hold each word
      String word;
      // initiate a new Set to hold all the possible words
      Set<String> possibleWords = new HashSet<String>();
            // create a keyboard scanner and prompt for the filename
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Enter the filename for words to use: ");
      String filename = keyboard.nextLine();
            // create a file reader using the argument input
      FileReader file = new FileReader(filename);
      // create a buffered reader
      BufferedReader br = new BufferedReader(file);
      // run through each line of the file
      //////////////System.out.println("These are the possible words: ");
      while ((word = br.readLine()) != null) {
         // add the word to the Set of possible words if the word has 5 letters
         if (word.length() == 5) {
            possibleWords.add(word);
            ////////////////////System.out.println(word);
         }
      }   
      
      // take in the end word and set it as answer
      System.out.println();
      System.out.print("Enter the word to be optimized: ");
      String answer = keyboard.nextLine();
      // check to make sure the answer is included in the provided list
            while (true) {
         if (!possibleWords.contains(answer)) {
            System.out.print("This word is not valid. Please enter a different word: ");
            answer = keyboard.nextLine();
         }
         else {
            break; }
      }
      
           
      
      String nextGuess = bestGuess(possibleWords);
      
      System.out.println();
      System.out.println("The next best guess is: " + nextGuess);
      
      // match should be 0 if the letter is not in the answer, 1 if it's in the answer but not in the right spot, and 2 if it's in the answer and in the right spot
      int[] match = new int[5];
      for (int i = 0; i < 5; i++) {
         if (nextGuess.charAt(i) == answer.charAt(i)) {
            match[i] = 2; }
         else if (answer.contains(String.valueOf(nextGuess.charAt(i)))) {
            match[i] = 1; }
      }
      
            
      possibleWords = createPossibleWords(match, nextGuess, possibleWords);
      nextGuess = bestGuess(possibleWords);
      System.out.println();
      System.out.println("The next best guess is: " + nextGuess);
   }

   ///////////////////////////////////////////////////////////////////////////////////////////////////////////
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   public static Set<String> createPossibleWords(int[] match, String previousGuess, Set<String> possibleWords) {
      // create a new set of possible words
      Set<String> newPossibleWords = new HashSet<String>(possibleWords);
      // apply what you learned from the last word to the new set of words and remove words that can no longer be the answer
      for (String word : possibleWords) {
         for (int i = 0; i<5; i++) {
            if (match[i] == 0 && word.contains(String.valueOf(previousGuess.charAt(i)))) {
               newPossibleWords.remove(word);
            }
            else if (match[i] == 1 && !word.contains(String.valueOf(previousGuess.charAt(i)))) {
               newPossibleWords.remove(word);
            }
            else if (match[i] == 1 && word.charAt(i) == previousGuess.charAt(i)) {
               newPossibleWords.remove(word);
            }
            else if (match[i] == 2 && !(word.charAt(i) == previousGuess.charAt(i))) {
               newPossibleWords.remove(word);
            }
         }
      }
      
      System.out.println();
      System.out.println();
      ///////////System.out.println("new possible words after guessing " + previousGuess + ": ");
      ///////////////for (String word : newPossibleWords) {
         //////////////System.out.println(word);
      ///////////////}
      
      return newPossibleWords;
   }
   
   
   ///////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   // CALCULATE THE NEXT BEST WORD TO GUESS
   public static String bestGuess(Set<String> possibleWords) {
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
      
      // why the funny math? we're gonna eventually want the letter that will eliminate the most other words. This is defined by the letter that appears the closest to 50% 
      // of the time. So to find that, assuming you have the percentages of letters from 0 to 1.0, you find the closest to 0.5 by subtracting 0.5 from each value, taking the 
      // absolute valueof that, and then looking for the lowest numbers
      for (char key : letter_counts.keySet()) {
         letter_counts.put(key, Math.abs((letter_counts.get(key) / wordCount) - 0.5));
      }
      // and this sorts the map so that the lowest values are at the beginning
      letter_counts = sortByValues(letter_counts);
      
      
      // determine the words that contain the 5 most common letters
      // instantiate a new set of optimal guesses
      Set<String> optimalGuess = new LinkedHashSet<String>();
      // put the most common letters into an array for easy access
      Character[] commonLetters = new Character[(letter_counts.keySet()).size()];
      commonLetters = (letter_counts.keySet()).toArray(commonLetters);
      // determine which of all the possible words has the 5 most common letters           
      for (String word : possibleWords) {
         if ( word.contains(String.valueOf(commonLetters[0])) && word.contains(String.valueOf(commonLetters[1])) && word.contains(String.valueOf(commonLetters[2])) && 
             word.contains(String.valueOf(commonLetters[3])) && word.contains(String.valueOf(commonLetters[4])) ) {
            optimalGuess.add(word);  
         }
      }
      
      // NOW WHAT TO DO is try to narrow down "optimalGuess" to just one word. So if there's no values in "optimalGuess" at this point, we need to evaluate words with the 
      // 6th, 7th, etc. most common letter. If there's more than 1 value in "optimalGuess", we need to figure out how to narrow it down to one best guess.
      
      
      // this will eventually return the single most optimal guess. but until then just pick the first entry of optimalGuess, but first check to see if it's empty
      if (optimalGuess.isEmpty()) {
         optimalGuess.add("xxxxx");
      }
      return optimalGuess.iterator().next();
   }
  
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   // this method sorts a Map. specifically it's used to sort the letters by their likelihood of occuring in the word.
   public static HashMap<Character, Double> sortByValues(Map<Character, Double> letter_counts) {
      // this chunk of code sorts the letters by their value
      List<Map.Entry<Character, Double> > list = new LinkedList<Map.Entry<Character, Double> >(letter_counts.entrySet());
      Collections.sort(list, new Comparator<Map.Entry<Character, Double> >() {
         public int compare(Map.Entry<Character, Double> o1, Map.Entry<Character, Double> o2)
         {
            return (o1.getValue()).compareTo(o2.getValue());
         }
      });
      HashMap<Character, Double> temp = new LinkedHashMap<Character, Double>();
      for (Map.Entry<Character, Double> aa : list) {
         temp.put(aa.getKey(), aa.getValue());
      }  
      return temp;
   }
   
   
   
   
}
