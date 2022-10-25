/* Created by: Andrea Leach
   Date: 09 FEB 2022
   Detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;

public class DelineateFile {
   
   // main method
   public static void main(String[] args) throws Exception {
	
	String filename = "words_wordle_list.txt";
	FileReader file = new FileReader(filename);
	BufferedReader br = new BufferedReader(file);
	String listofwords = br.readLine();

	String[] words = listofwords.split(" ");
	
	for (String word : words) {
		System.out.println(word);
	}

      
   
   }
}