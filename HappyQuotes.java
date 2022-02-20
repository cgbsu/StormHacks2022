import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class HappyQuotes {
   
   public String quoteFile;
   ArrayList< String > lines;
   Random rng;
   public final String DefaultQuoteFileFinal = "quotes.txt";
   public final int DefaultAmountOfCharactersPerLine = 20;
   protected int amountOfCharactersToDisplayPerLine;


   protected void initalize( String quoteFile, int amountOfCharactersToDisplayPerLine )
   {
      this.quoteFile = quoteFile;
      this.amountOfCharactersToDisplayPerLine = amountOfCharactersToDisplayPerLine;
      rng = new Random();
      quoteFile(this.quoteFile);
   }
   public HappyQuotes(String quoteFile, int amountOfCharactersToDisplayPerLine) {
      initalize(quoteFile, amountOfCharactersToDisplayPerLine);
   }
   public HappyQuotes( int amountOfCharactersToDisplayPerLine ) {
      initalize(DefaultQuoteFileFinal, amountOfCharactersToDisplayPerLine);
   }
   public HappyQuotes() {
      initalize(DefaultQuoteFileFinal, DefaultAmountOfCharactersPerLine);
   }
   
   //takes the number of quotes, generates a number within it then brings back the quote.
   public String quoteGrab() {
      return lines.get(rng.nextInt(lines.size()));
   }
   
   public ArrayList< String > quoteFile() {
      return quoteFile(quoteFile);
   }

   //exception for the quotes file
   public ArrayList< String > quoteFile(String file)
   {
      Scanner inputStream = null;
      
      try
      {
         inputStream = new Scanner(new FileReader(file));
         lines = new ArrayList< String >();
         while(inputStream.hasNextLine())
         {
            String line = inputStream.nextLine();
            for( int i = 0; i < line.length(); ++i )
            {
               if( ( i % amountOfCharactersToDisplayPerLine ) == 0 )
               {
                  final String PostfixFinal = line.substring( i );
                  final String PrefixFinal = line.substring( 0, i );
                  line = PrefixFinal + "\n" + PostfixFinal;
               }
            }
            lines.add( line );
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("File not found, program to Close.");
         System.exit(0);
      }
      
      return lines;
   }

   
}