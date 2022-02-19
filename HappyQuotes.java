import java.io.*;
import java.util.Scanner;
import java.util.Random;
public class HappyQuotes{
   
   public String quote = null;
   
   public HappyQuotes(){
      
      Scanner list = quoteFile();
      Random rng = new Random();
      
      String message = quoteGrab(list, rng);
      
   }
   
   
   //takes the number of quotes, generates a number within it then brings back the quote.
   public String quoteGrab(Scanner list, Random rng){
      
      int i = 0;
      while(list.hasNextLine()){
         i++;
      }
      
      String[] quoteArray = new String[i];
      
      this.quote = null;
      
      System.out.println(i);
      
      return(quote);
   }
   
   
      //exception for the quotes file
      public static Scanner quoteFile(){
      
      Scanner inputStream = null;
      
      try{
         inputStream = new Scanner(new FileReader("quotes.txt"));
      }
      catch (FileNotFoundException e){
         System.out.println("File not found, program to Close.");
         System.exit(0);
      }
      
      return(inputStream);
   }

   
}