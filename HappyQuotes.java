import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class HappyQuotes{
   
   public String quote = null;
   ArrayList< String > lines;   
   Random rng;
   public HappyQuotes(){
      rng = new Random();
      quoteFile();
   }
   
   
   //takes the number of quotes, generates a number within it then brings back the quote.
   public String quoteGrab(){
      this.quote = lines.get(rng.nextInt(lines.size()));
      return(quote);
   }
   
   
      //exception for the quotes file
   public Scanner quoteFile(){
      
      Scanner inputStream = null;
      
      try{
         inputStream = new Scanner(new FileReader("quotes.txt"));
         lines = new ArrayList< String >();
         while(inputStream.hasNextLine()){
            lines.add(inputStream.nextLine());
         }
      }
      catch (FileNotFoundException e){
         System.out.println("File not found, program to Close.");
         System.exit(0);
      }
      
      return(inputStream);
   }

   
}