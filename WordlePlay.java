/* Created by: Andrea Leach
Date: 17 FEB 2022
Short Description: class that lets the user play a wordle game

Detailed Description: detailed description goes here
*/

// import statements go here
import java.util.*;
import java.io.*;

public class WordlePlay {
  
  public static void main(String[] args) {
      // create a boolean that you want to play a game
      boolean play = false;
      
      Scanner keyboard = new Scanner(System.in);
      System.out.print("Do you want to play Wordle? y/n: ");
      String playString = keyboard.nextLine();
      if (playString.equals("y") || playString.equals("Y")) {
         play = true;
      } else {
         System.exit(0);
      } // maybe eventually add functionality for someone typing something other than "y" or "n"
    
    WordleGame mygame = new WordleGame();
    mygame.setWordLists();
    String answer;
    
    while (play) {
        mygame.setUnknownAnswer();
        answer = mygame.getAnswer();
      
      
      
    }

    
  }
  
  
  
  
  
  
  
}
