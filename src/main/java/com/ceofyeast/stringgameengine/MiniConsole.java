/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ceofyeast.stringgameengine;

import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Point; // class representing an (x, y) coordinate

/**
 * Static class representing a mini-console designed to present a series of choices to the user and
 * prompt a response.
 * 
 * <p>Can be printed from any (x, y) coordinate in the console, and can be any width or height. The 
 *    mini-console is designed to be placed wherever the developer desires; usually this is where it will be
 *    most noticeable to the end user.
 * 
 * <p>The lines inside the mini-console can be cleared to make room for new lines, or to simply appear empty;
 *    however, the mini-console's border is never cleared/re-printed. This makes for a more seamless experience.
 * 
 * <p>The user can specify that spacing lines be added to the mini-console, as well as the number of spacing 
 *    lines added. These spacing lines go between printed lines to give the mini-console more breathing room.
 *    The {@link MiniConsole#spacingBetweenLines spacingBetweenLines} variable dictates how many lines should 
 *    be placed between two printed lines.
 *     
 * <p>A single line is split across multiple lines in the display, called line pieces, if its too long to fit  
 *    inside the width of the display; this is done in {@link MiniConsole#addLineToLines(String) addLineToLines}. 
 *    This makes it so the developer doesn't have to be concerned about the width when adding a line to the 
 *    mini-console.
 * 
 * <p>Only as many lines can be added to lines as will fit in {@link  MiniConsole#lines  lines}; this includes
 *    spacing lines as well as line pieces. If an extra line is attempted to be added, a warning will be 
 *    thrown and addLineToLines will return.
 * 
 * @author Benton Diebold
 */
class MiniConsole 
{ 
  /**
   * Width of the display section of the mini-console, including the width offset; the border isn't included.
   */
  private static int width;

  /**
   * Height of the display section of the mini-console, including the height offset; the border, and the input
   * line aren't included.
   */
  private static int height;

  /**
   * Initialized with # of spaces on construction equating to width of console display; used to clear the
   * mini-console by printing a line of spaces over existing characters.
   */
  private static String spaces;

  /**
   * Used to specify the number of blank lines that should be printed in-between lines in the console.
   */
  private static int spacingBetweenLines = 0;

  /**
   * Used to specify whether spacingBetweenLines should be included between pieces of the same line.
   */
  private static boolean includeSpacingBetweenLinePieces = false;

  /**
   * Contains the lines to be printed in the mini-console.
   */
  private static ArrayList<String> lines = new ArrayList<>(); 

  /**
   * The point to print the contents of the mini-console display from; will be the top-left corner of the display,
   * disregarding the border.
   */
  private static Point printDisplayFrom;

  /**
   * Scanner initialization.
   */
  private static Scanner scanner = new Scanner( System.in ); 

  /**
   * Distance from the right and left sides of mini-console display to print contents of lines.
   */
  private static final int WIDTH_OFFSET = 1;

  /**
   * Distance from the top and bottom lines of mini-console display to print contents of lines.
   */
  private static final int HEIGHT_OFFSET = 0;

  /**
   * Prints the mini-console after receiving a width, height and top-left corner to print the mini-console from;
   * is the base constructor that the overloaded constructors call.
   * 
   * @param width used to initialize the width member
   * @param height used to initialize the height member
   * @param printConsoleFrom used to initialize the printConsoleFrom member
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
   * Overloaded constructor that additionally allows the user to specify the amount of spacing between lines; the
   * default amount is zero.
   * 
   * @param width used to initialize the width member
   * @param height used to initialize the height member
   * @param printConsoleFrom used to initialize the printConsoleFrom member
   * @param spacingBetweenLines used to initialize the spacingBetweenLines member
   */
  public static void initializeMiniConsole( int width, int height, Point printConsoleFrom, int spacingBetweenLines )
  {
    initializeMiniConsole( width, height, printConsoleFrom );

    MiniConsole.spacingBetweenLines = spacingBetweenLines;
  }

  /**
   * Overloaded constructor that additionally allows the user to specify the amount of spacing between lines as
   * well as whether spacing should be included between line pieces.
   * 
   * @param width used to initialize the width member
   * @param height used to initialize the height member
   * @param printConsoleFrom used to initialize the printConsoleFrom member
   * @param spacingBetweenLines used to initialize the spacingBetweenLines member
   * @param includeSpacingBetweenLinePieces used to initialize the includeSpacingBetweenLinePieces member
   */
  public static void initializeMiniConsole( int width, int height, Point printConsoleFrom, int spacingBetweenLines, boolean includeSpacingBetweenLinePieces )
  {
    initializeMiniConsole( width, height, printConsoleFrom, spacingBetweenLines );

    MiniConsole.includeSpacingBetweenLinePieces = includeSpacingBetweenLinePieces;
  }

  /**
   * Used to get the String at the index indexToGet in {@link  MiniConsole#lines  lines}.
   * 
   * @param indexToGet the index of the String in lines to get
   * @return the String in lines at indexToGet
   */
  public static String getLineInLines( int indexToGet )
  {
    return lines.get( indexToGet );
  }

  /**
   * Sets {@link  MiniConsole#spacingBetweenLines  spacingBetweenLines}.
   * 
   * @param spacingBetweenLines the int to set spacingBetweenLines to
   */
  public static void setSpacingBetweenLines( int spacingBetweenLines )
  {
    MiniConsole.spacingBetweenLines = spacingBetweenLines;
  }
  
  /**
   * Adds given line to lines as long as {@link  MiniConsole#lines  lines} isn't full, meaning it's size doesn't
   * equal the {@link  MiniConsole#height  height} of the mini console.
   * 
   * <p>Is capable of adding lines that are longer than the {@link  MiniConsole#width  width} of the mini-console; 
   *    in this case, lineToAdd is split into smaller lines (called line pieces) that are less than or equal to 
   *    the width of the mini-console.
   * 
   * @param lineToAdd
   * @return an ArrayList of integers corresponding to the indices of any lines added to lines. There will be more
   *         than one index in the ArrayList if lineToAdd was split into multiple line pieces, only one index in 
   *         the ArrayList if lineToAdd wasn't split, and no index if no lines were added.
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
   * Prints every String present in {@link  MiniConsole#lines  lines} within the confines of the mini-console. 
   * There's no handling of the case where the size of lines exceeds the {@link  MiniConsole#height  height} of
   * the mini-console because {@link MiniConsole#addLineToLines(String) addLineToLines} simply returns upon
   * an attempt to exceed this limit.
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
   * Clears every line in the display of the mini console by printing over it with spaces.
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
  /**
   * Displays a list of decisions, specified in decisionsToDisplay, that the user can make. Each decision 
   * corresponds to a number that the user can type into the mini-console to select said decision; this number
   * in turn corresponds to the index of the chosen decision in decisionsToDisplay. This number is then 
   * returned.
   * 
   * @param decisionsToDisplay a list of decisions for the user to choose between
   * @return the index of the chosen decision in decisionsToDisplay
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

  /**
   * Used to move the cursor to the beginning of a line in the mini-console's display.
   * 
   * @param indexToTraverseTo the index of the line in the mini-console to traverse to
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
   * Used to move the cursor to the beginning of the input line in the mini-console's display.
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
  /**
   * Used to take input from the user inside the mini-console by navigating to the input line, prompting the user
   * for input in form of a String, and returning said String.
   * 
   * @return the String input by the user when prompted
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
   * Used to clear the input line by printing spaces over it.
   */
  private static void clearInputLineInMiniConsole()
  {
    traverseToInputLineInMiniConsole();
    
    System.out.print( spaces ); // prints spaces on input line, clearing it
  }

  /**
   * Used to chop a String into pieces (called line pieces) with lengths less than or equal to MaxLengthOfPieces;
   * Extra logic is employed to hyphenate words that are split between two line pieces.
   * 
   * @param lineToCut the line to cut into line pieces with lengths less than or equal to MaxLengthOfPieces
   * @param maxLengthOfPieces the max length a line piece can be
   * @return an ArrayList of the line pieces derived from lineToCut
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
   * Used to blink a line in the mini-console; this consists of rapidly erasing and re-printing the line in order
   * to provide a blinking effect. This is intended to provide visual feedback to a user that the decision
   * they chose was acknowledged by the system.
   * 
   * @param indexOfLineToBlink the index of the line in {@link  MiniConsole#lines  lines} to blink
   * @param durationOfBlink the amount of time, in milliseconds, that one blink cycle takes; a cycle consists of a
   *                        single line erasure and re-printing
   * @param numBlinks the number of blink cycles that should occur
   * @see MiniConsole#blinkLinesInMiniConsole(java.util.ArrayList, int, int) blinkLinesInMiniConsole
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
   * Used to blink multiple lines in the mini-console within the same blink cycle; this consists of rapidly 
   * erasing and re-printing the lines in order to provide a blinking effect. This is intended to provide 
   * visual feedback to a user that the decision they chose was acknowledged by the system. 
   * 
   * @param indicesToBlink the indices of the lines in {@link  MiniConsole#lines  lines} to blink
   * @param durationOfBlink the amount of time, in milliseconds, that one blink cycle takes; a cycle consists of a
   *                        single erasure and re-printing
   * @param numBlinks the number of blink cycles that should occur
   * @see MiniConsole#blinkLineInMiniConsole(int, int, int) blinkLineInMiniConsole
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
}