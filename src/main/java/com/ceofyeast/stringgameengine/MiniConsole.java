/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ceofyeast.stringgameengine;

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point; // class representing an (x, y) coordinate

/**
* static class representing a mini-console that can be printed from any (x, y) coordinate on the screen and can be any width/height
* has capability to display possible choices for the user and take input to decide between these choices
* the console's border is constant as only the Strings inside the console are cleared
*/
class MiniConsole 
{
    //// MiniConsole members

  // width of the display section of the mini-console, including the width offset
  // doesn't include:
  //   - the right-most column because its part of the border
  //   - the left-most column because its part of the border
  private static int width;

  // height of the display section of the mini console, including the height offset
  // doesn't include:
  //   - the first line because its part of the border
  //   - the last line because its part of the border
  //   - the input line
  //   - the line above the input line because its part of the border
  private static int height;

  // filled with # of spaces on initialization equating to width of console display
  // used to clear the console by printing line of spaces over existing characters
  private static String spaces;

  // used to specify the number of blank lines that should be printed in between lines in the console
  private static int spacingBetweenLines = 0;

  // Used to specify whether spacingBetweenLines should be included between pieces of the same line (a single line is split across multiple lines in the display if its too long to fit within the width of the display. This is done in addLineToLines)
  private static boolean includeSpacingBetweenLinePieces = false;

  // contains lines to be printed in mini-console
  private static ArrayList<String> lines = new ArrayList<>(); 

  // point to print contents of mini console display from
  // will be top-left corner of display
  private static Point printDisplayFrom;

  // static scanner available to all mini methods
  private static Scanner scanner = new Scanner( System.in ); 

  // distance from the right and left sides of mini-console display to print contents of lines
  private static final int WIDTH_OFFSET = 1;

  // distance from the top and bottom lines of mini-console display to print contents of lines
  private static final int HEIGHT_OFFSET = 0;
  

    
    //// main method
  
  public static void main(String[] args) {}
  
  
    //// MiniConsole "constructor"

  /**
  * Prints the menu console after recieving a width, height and top left corner to print the mini console from  
  * Input: 
    - width: width of mini console
    - height: height of mini console
    - printConsoleFrom: left corner of mini console 
  * Output: N/A
  */
  public static void initializeMiniConsole( int width, int height, Point printConsoleFrom )
  {
      // initializes member variables
    MiniConsole.width = width;
    MiniConsole.height = height;
    
    printDisplayFrom = new Point(
      printConsoleFrom.x + 1,
      printConsoleFrom.y + 1
    );
    
    // fills spaces member var with spaces that will be printed over the old lines in the mini console's display
    spaces = " ".repeat( width - ( WIDTH_OFFSET * 2 ) ); // fills a string (spaces) with the number of spaces equating to the width of miniConsole minus the width offset
    
    String[] miniConsoleBorder = new String[height + 4];

      // fills console border
    for( int i = 0; i < miniConsoleBorder.length; i++ ) // iterates for length of miniConsoleBorder
    {
        // decides what to fill miniConsoleBorder[i] (current line of miniConsoleBorder) with based on the line's index
      if ( i == 0 ) // if on first line
      {
        miniConsoleBorder[i] = "┌" + "─".repeat(width)  + "┐";
      }
      else if ( i == height + 1 ) // if on line above input line
      {
        miniConsoleBorder[i] = "│" + "-".repeat(width)  + "│";
      }
      else if ( i == height + 3 ) // if on last line
      {
        miniConsoleBorder[i] = "└" + "─".repeat(width)  + "┘";
      }
      else // if on any other line
      {
        miniConsoleBorder[i] = "│" + " ".repeat(width)  + "│";
      } 
    } 

    Screen.moveCursorToPoint( printConsoleFrom ); 
    
    Screen.printStringArrayFromCursor( miniConsoleBorder );
  }

  /**
  * initializeMiniConsole overload
  * Input: 
    - original initializeMiniConsole input
    - spacingBetweenLines: used to set spacingBetweenLines member var
  * Output: N/A
  */
  public static void initializeMiniConsole( int width, int height, int spacingBetweenLines, Point printConsoleFrom )
  {
    initializeMiniConsole( width, height, printConsoleFrom );

    MiniConsole.spacingBetweenLines = spacingBetweenLines;
  }

  /**
  * initializeMiniConsole overload
  * Input: 
    - original initializeMiniConsole input
    - spacingBetweenLines: used to set spacingBetweenLines member var
    - includeSpacingBetweenLinePieces: used to set includeSpacingBetweenLinePieces member var
  * Output: N/A
  */
  public static void initializeMiniConsole( int width, int height, int spacingBetweenLines, boolean includeSpacingBetweenLinePieces, Point printConsoleFrom )
  {
    initializeMiniConsole( width, height, spacingBetweenLines, printConsoleFrom );

    MiniConsole.includeSpacingBetweenLinePieces = includeSpacingBetweenLinePieces;
  }
  

  
    //// MiniConsole getter methods

  /**
  * Used to get String at index indexToGet in the lines arraylist
  * Input:
    - indexToGet: index of String in lines to get
  * Output:
    - String value at index indexToGet in lines
  */
  public static String getLineInLines( int indexToGet )
  {
    return lines.get( indexToGet );
  }

  
  
    //// MiniConsole setter methods

  /**
  * Used to change spacing between lines post console-initialization
  * Input: 
    - spacingBetweenLines: used to set spacingBetweenLines member var
  * Output: N/A
  */
  public static void setSpacingBetweenLines( int spacingBetweenLines )
  {
    MiniConsole.spacingBetweenLines = spacingBetweenLines;
  }

  
  
    //// MiniConsole public utility methods
  
  /**
  * Adds given line to lines as long as lines isn't full (size doesn't equal height of mini console)
  * Is capable of adding lines that are longer than the width of the mini console. If this is the case, the line is split into lines that are the width of the mini console, which are then added to lines individually.
  * Input:
    - lineToAdd: specifies line to be appended to lines
  * Output:
    - indicesOfAddedLines: contains all the indices in lines of the line pieces (or the index of lineToAdd only if its not split up) added to lines during the course of the method's execution
  */
  public static ArrayList<Integer> addLineToLines( String lineToAdd )
  { 
    ArrayList<Integer> indicesOfAddedLines = new ArrayList<Integer>();
    
      // used to chop up lineToAdd if it's too long to fit in the console display
    int maxWidthOfLineToAdd = width - ( WIDTH_OFFSET * 2 );

    ArrayList<String> piecesOfLine = cutLineIntoPieces( lineToAdd, maxWidthOfLineToAdd );

    for( int i = 0; i < piecesOfLine.size(); i++ )
    {
      // returns before adding line if lines is full
      if( lines.size() == height - ( HEIGHT_OFFSET * 2 ) )
      {
        return indicesOfAddedLines;
      }

      lines.add( piecesOfLine.get( i ) );

      indicesOfAddedLines.add( lines.size() - 1 );

        // adds spacing beneath pieceOfLine if includeSpacingBetweenLinePieces is true or if on last line
      if( includeSpacingBetweenLinePieces || i == piecesOfLine.size() - 1 )
      {
        for( int l = 0; l < spacingBetweenLines; l++ )
        {
            // returns before adding line if lines is full
          if( lines.size() == height - ( HEIGHT_OFFSET * 2 ) )
          {
            return indicesOfAddedLines;
          }
          
          lines.add( spaces );    
        }
      }
    }

    return indicesOfAddedLines;
  }

  /**
  * Prints every line in lines within the confines of the mini-console
  * Input: N/A
  * Output: N/A
  */
  public static void printLinesInMiniConsole( )
  {
      // prints out every line in lines inside the console in order
    for( int i = 0; i < lines.size(); i++ )
    {
      traverseToLineInMiniConsole( i ); 
      
      System.out.print( lines.get(i) );
    } 
  } 

  /**
  * Clears every line in the display of the mini console by printing over it with spaces
  * Input: N/A
  * Output: N/A
  */
  public static void clearLinesInMiniConsole()
  {
      // makes room for spaces lines
    lines.clear(); 
    
    for( int i = 0; i < height; i++ ) // iterates for the height of the console
    {
      lines.add( spaces ); 
    } 
      
    printLinesInMiniConsole();

    clearInputLineInMiniConsole();

      // clears spaces lines, leaving lines empty
    lines.clear(); 
  } 

  /**
  * Displays given list of decisions and returns user's choice in the form of an index to find the decision they chose
  * Input:
    - decisionsToDisplay: array of Strings that represent the decisions the player can make
  * Output: 
    - decision: represents index of player's decision given the decisions in decisionsToDisplay
  */
  public static int displayDecisionsInMiniConsole( String[] decisionsToDisplay )
  {
    ArrayList<ArrayList<Integer>> indicesOfDecisionsInDisplay = new ArrayList<ArrayList<Integer>>();
    
      // adds all decisions in decisionsToDisplay to lines along with their corresponding index
    for( int i = 0; i < decisionsToDisplay.length; i++ ) // loops for length of decisionsToDisplay
    {
      String toAdd = "[" + i + "] " + decisionsToDisplay[i]; // adds index of decison next to decision so user knows which number to input to choose that decision
      
      indicesOfDecisionsInDisplay.add( addLineToLines( toAdd ) );
    }

    printLinesInMiniConsole();

      // loop that runs until a proper input is submitted 
      // the input must be able to be converted to an int, and must be in the proper range of decisions
    while( true )
    {
      clearInputLineInMiniConsole();
      
      int decision = -1;
      
      try  
      {
        decision = Integer.valueOf( takeInputInMiniConsole() );
      }
      catch( NumberFormatException e ) 
      {
        continue;
      } 

        // returns decision if its in the proper range of decisions
      if( decision < decisionsToDisplay.length && decision >= 0 ) // if decision is within range of indices of possible decisions
      {
        blinkLinesInMiniConsole( indicesOfDecisionsInDisplay.get( decision ), 750, 4 );

        MiniConsole.clearLinesInMiniConsole();
        
        return decision;
      }
    }
  } 

  

    //// MiniConsole private utility methods

  /**
  * Method used to traverse to the beggining of a line in the mini console's display
  * Input:
    - indexToTraverseTo: index of line to traverse to beggining of
  * Output: N/A
  */
  private static void traverseToLineInMiniConsole( int indexToTraverseTo )
  {
    Screen.moveCursorToPoint( // moves cursor to beggining of line at indexToTraverseTo
      new Point(
      printDisplayFrom.x + WIDTH_OFFSET,
      printDisplayFrom.y + HEIGHT_OFFSET + indexToTraverseTo
      )
    );
  }

  /**
  * Method used to traverse to the beggining of the input line in the mini console's display
  * Input: N/A
  * Output: N/A
  */
  private static void traverseToInputLineInMiniConsole()
  {
    Screen.moveCursorToPoint( // moves cursor to beginning of input line
      new Point(
        printDisplayFrom.x + WIDTH_OFFSET,
        printDisplayFrom.y + height + 1
      )
    );
  }
  
  /**
  * Navigates to user input line in mini console and asks for user input in the form of a string before returning said string
  * Input: N/A
  * Output: 
    - toReturn: represents user input in the form of a String
  */
  private static String takeInputInMiniConsole()
  {
    traverseToInputLineInMiniConsole();

    System.out.print( "> " ); // prints arrow indicating input is required

    System.out.print( "\033[?25h" ); // makes the cursor visible
    
    String toReturn = scanner.next(); // grabs user input as String

    System.out.print( "\033[?25l" ); // hides the cursor

    return toReturn; // returns user input
  }

  /**
  * used to clear (print spaces over) input line
  * Input: N/A
  * Output: N/A
  */
  private static void clearInputLineInMiniConsole()
  {
    traverseToInputLineInMiniConsole();
    
    System.out.print( spaces ); // prints spaces on input line, clearing it
  }

  /**
  * Method used to chop a String into pieces with lengths <= maxLengthOfPieces
  * Extra logic is used to hyphenate words that are split between two pieces
  * Input:
    - lineToCut: line to cut into pieces
    - maxLengthOfPieces: maximum length of various pieces of lineToCut
  * Output:
    - ArrayList<String> piecesOfLine: contains pieces of line cut from lineToCut with length <= maxLengthOfPieces
  */
  private static ArrayList<String> cutLineIntoPieces( String lineToCut, int maxLengthOfPieces )
  {
    ArrayList<String> piecesOfLine = new ArrayList<String>();
    
    while( lineToCut.length() > maxLengthOfPieces )
    {
      String pieceOfLine = lineToCut.substring( 0, maxLengthOfPieces ); // gets piece of lineToCut with length equal to max width of line to add

      // if pieceOfLine ends in a space, which means the next piece starts with a word, which entails that no hyphen is needed at the end of pieceOfLine because a word isn't being split between two sections
      if( lineToCut.charAt( 15 ) == ' ' ) 
      {
        lineToCut = lineToCut.substring( maxLengthOfPieces ); // cuts pieceOfLine from lineToCut
      }

      // if the next piece starts with a space, which entails the same thing as the if condition; however, the space at the start of the next piece needs to be removed
      else if( lineToCut.charAt( 16 ) == ' ' ) 
      {
        lineToCut = lineToCut.substring( maxLengthOfPieces + 1 ); // cuts pieceOfLine from lineToCut
      } 

      // if pieceOfLine ends with a letter, which means it ends with the first letter of the first word of the next piece, which entails that no hypen needs to be added to the end of pieceOfLine; instead, the letter simply needs to be moved to the start of the next line
      else if( lineToCut.charAt( 14 ) == ' ' ) 
      {
        pieceOfLine = pieceOfLine.substring( 0, maxLengthOfPieces - 2 ); // sets pieceOfLine to be itself minus the final letter

        lineToCut = lineToCut.substring( maxLengthOfPieces - 1 ); // cuts pieceOfLine from lineToCut, making sure to include the aforementioned final letter in lineToCut so it can be included in the next piece
      } 

      // base case
      // if lineToCut is being sliced in the middle of the word. This entails that a hyphen needs to be added to the end of pieceOfLine, and the last character of pieceOfLine needs to be added to the start of the next piece
      else 
      {
        pieceOfLine = pieceOfLine.substring( 0, maxLengthOfPieces - 1 ); // sets pieceOfLine to be itself minus the final letter

        pieceOfLine += "-";

        lineToCut = lineToCut.substring( maxLengthOfPieces - 1 ); // cuts pieceOfLine from lineToCut, making sure to include the final letter in lineToCut so it can be included in the next piece
      } 

      piecesOfLine.add( pieceOfLine );
    }

    piecesOfLine.add( lineToCut );

    return piecesOfLine;
  }
  
  /**
  * Method meant to "blink" a line in the mini console
  * Used to provide feedback to a user that the decision they chose was accepted by the system
  * Input:
    - indexOfLineToBlink: the index of the line in lines to blink
    - durationOfBlink: the amount of time one blink cycle takes
    - numBlinks: the number of times one blink cycle will occur
  * Output: N/A
  */
  private static void blinkLineInMiniConsole( int indexOfLineToBlink, int durationOfBlink, int numBlinks )
  {
    for( int i = 0; i < numBlinks; i++ )
    {
      for( int l = 0; l < 2; l++ )
      {
        traverseToLineInMiniConsole( indexOfLineToBlink );

        if( l == 0 )
        {
          System.out.print( spaces );
        }
        else
        {
          System.out.print( lines.get( indexOfLineToBlink ) );
        }
  
        try 
        {
          Thread.sleep( durationOfBlink / 2 );
        }
        catch( Exception e )
        {
          System.out.println( e );
        }
      }
    }
  }

  /**
  * Method meant to "blink" multiple lines in the console at the same time
  * Used to provide feedback to a user that the decision they chose was accepted by the system
  * Input:
    - indicesToblink: the indices of the various lines in lines to blink
    - durationOfBlink: the amount of time one blink cycle takes
    - numBlinks: the number of times one blink cycle will occur
  * Output: N/A
  */
  private static void blinkLinesInMiniConsole( ArrayList<Integer> indicesToBlink, int durationOfBlink, int numBlinks )
  {
    for( int i = 0; i < numBlinks; i++ ) // loops for number of blinks
    {
      for( int l = 0; l < 2; l++ ) // loops twice (two intervals during a blink)
      {
        for( int x = 0; x < indicesToBlink.size(); x++ ) // loops for lines to blink
        {
          traverseToLineInMiniConsole( indicesToBlink.get( x ) );
          
          if( l == 0 )
          {
            System.out.print( spaces );
          }
          else
          {
            System.out.print( lines.get( indicesToBlink.get( x ) ) );
          }
        }

        try 
        {
          Thread.sleep( durationOfBlink / 2 );
        }
        catch( Exception e )
        {
          System.out.println( e );
        }
      }
    }
  }
  
} // end of MiniConsole