import java.io.*;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class HappyQuotes {
   
   public String quoteFile;
   ArrayList< String[] > lines;
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
   public String[] quoteGrab() {
      return lines.get(rng.nextInt(lines.size()));
   }
   
   public ArrayList< String[] > quoteFile() {
      return quoteFile(quoteFile);
   }

   //exception for the quotes file
   public ArrayList< String[] > quoteFile(String file)
   {
      Scanner inputStream = null;
      
      ArrayList< String > lineFormingBuffer = new ArrayList< String >();
      ArrayList< String > lineBuffer = new ArrayList< String >();
      try
      {
         inputStream = new Scanner(new FileReader(file));
         lines = new ArrayList< String[] >();
         while(inputStream.hasNextLine())
         {
            lineBuffer.clear();
            lineFormingBuffer.clear();
            String line = inputStream.nextLine();
            String[] words = line.split( "\\W+" );
            int characterLength = 0;
            for( int i = 0; i < words.length; ++i )
            {
               characterLength += words[ i ].length();
               lineFormingBuffer.add( words[ i ] );
               if( characterLength >= amountOfCharactersToDisplayPerLine 
                     || ( i + 1 ) >= words.length )
               {
                  String buffer = "";
                  for( String word : lineFormingBuffer )
                     buffer = buffer + " " + word;
                     lineBuffer.add( buffer );
                  lineFormingBuffer.clear();
                  characterLength = 0;
               }
            }
            String[] newLine = new String[ lineBuffer.size() ];
            newLine = lineBuffer.toArray( newLine );
            lines.add( newLine );
         }
      }
      catch(FileNotFoundException e) {
         System.out.println("File not found, program to Close.");
         System.exit(0);
      }
      
      return lines;
   }

   
}