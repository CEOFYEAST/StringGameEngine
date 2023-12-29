/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.ceofyeast.stringgameengine;

import java.util.ArrayList;
import java.awt.Point; // class representing an (x, y) coordinate

/**
* class that represents a screen with positions to print String
arrays at and associated methods to print said String arrays
* used to represent screens such as the main menu or dice roll screen
  - these screens will have set positions that certain elements such as buttons or displays will appear at every time the screen is used, hence the positions. The elements themselves will be represented by String arrays printed at said positions
*/
class Screen 
{
    //// Screen members
  
  // List containing String arrays to be printed at Points specified in pointsToPrintArraysFrom
  // Each list item corresponds to a Point in pointsToPrintArraysFrom and a boolean in directionsToPrintArraysIn at the same index
  // The inner arrays contains the Strings to be printed, and the outer arrays contain collections of these arrays. Each collection of arrays will be printed in order
  private ArrayList<String[][]> arrayCollectionsToPrint = new ArrayList<>();
  
  // List containing (x, y) coords to print contents of arrayCollectionsToPrint from
  // Each list item corresponds to a 2d array in arrayCollectionsToPrint and a boolean in directionsToPrintArraysIn at the same index
  private Point[] pointsToPrintArraysFrom;

  // List containing booleans representing the direction in which to print the arrays in arrayCollectionsToPrint
  // Each list item corresponds to a 2d array in arrayCollectionsToPrint and a Point in pointsToPrintArraysFrom at the same index
  private ArrayList<Boolean> directionsToPrintArraysIn = new ArrayList<>();


    
    //// main method
  
  public static void main(String[] args) {}
  
  
  
    //// Screen constructor
  
  /**
  * Paramaterized Constructor
  * Params: list of (x, y) positions to print String arrays at
  */
 public Screen( Point[] pointsToPrintArraysFrom )
  {
    this.pointsToPrintArraysFrom = pointsToPrintArraysFrom; 
  } // constructor end

  
    //// Screen mutators
  
  /**
  * method used to add a given collection of arrays to print (a 2d array) to arrayCollectionsToPrint and a given direction to directionsToPrintArraysIn
  * Input: 
    - array: 2d array to be added to arrayCollectionsToPrint
    - directionToPrint: boolean representing the direction in which to print the given array to be added to directionsToPrintArraysIn
      > True/1 to print succesive arrays to the right of original array
      > False/0 to print succesive arrays downwards
  * Output: N/A
  */
  public void addArrayCollectionToPrint( String[][] arrayCollection, Boolean directionToPrintIn )
  {
    arrayCollectionsToPrint.add( arrayCollection ); 
    directionsToPrintArraysIn.add( directionToPrintIn ); 
  }

  /**
  * method used to reset parent screen's runtime data
  * Input: N/A
  * Output: N/A
  */
  public void clearScreenInformation()
  {
    arrayCollectionsToPrint.clear();
    directionsToPrintArraysIn.clear();
  }

  
    //// Screen accessors
  
  /**
  * method used to print parent screen
  * Input: N/A
  * Output: printed screen
  */
  public void printScreen()
  {
      // prints all arrays in arrayCollectionsToPrint from their respective positions
    for( int i = 0; i < pointsToPrintArraysFrom.length; i++ ) // iterates for length of positions/arrayCollectionsToPrint/directionsToPrintArraysIn (same length)
    {
      printStringArraysFromPoint( arrayCollectionsToPrint.get(i), pointsToPrintArraysFrom[i], directionsToPrintArraysIn.get(i) ); // prints array at i in arraystoPrint at position i in positions
    } 
  }

  
    //// Screen utility methods

  /**
  * method that moves the cursor to the given x,y point in the console
  * Input:
    - pointToMoveCursorTo: x,y point on the console to move the cursor to
  */
  public static void moveCursorToPoint( Point pointToMoveCursorTo ) 
  {
      // moves cursor to pointToMoveCursorTo
    String moveCursorToPoint = String.format // initializes escape code that will move the cursor to pointToMoveCursorTo
      (
        "\033[%2$s;%1$sf", 
        pointToMoveCursorTo.x, 
        pointToMoveCursorTo.y
      ); 
      System.out.print( moveCursorToPoint ); // prints escape code, moving cursor to pointToMoveCursorTo
  }

  /**
  * method used to print a single given string array, with the top left corner of the array being located at the cursor
  * mainly used in conjunction with printStringArraysFromPoint to print several String arrays in succesion in a given direction
  * also used to print the border of the mini console
  * Input: 
    - arrayToPrint: String array to be printed
  * Output: N/A
  */
  public static void printStringArrayFromCursor( String[] arrayToPrint )
  {
      // prints arrayToPrint at cursor
    for(int i = 0; i < arrayToPrint.length; i++) // iterates over every line of arrayToPrint
    {
        // used to move back to beggining of line when printing next line
      System.out.print("\033[s"); // saves cursor position at beginning of line
      
      System.out.print(arrayToPrint[i]); // prints current line of arrayToPrint

        // moves cursor to start of next line if not on the last line
      if(i != arrayToPrint.length - 1) // if not on the last line of arrayToPrint
      {
        System.out.print("\033[u"); // moves cursor to beginning of line by loading saved position
        System.out.print("\033[1B"); // moves cursor down one line
      } 
    }
  }
  
  /**
  * method used to print several String arrays in succesion from a given point in a given direction (right or down)
  * Input:
    - arrayCollectionsToPrint: ArrayList of String arrays to be printed
    - coordToPrintFrom: Point to print arrays from (will be top left corner of printed arrays)
    - printDirection: direction to print arrays in, 
      > True/1 to print succesive arrays to the right of original array
      > False/0 to print succesive arrays downwards
  * Output: N/A
  */
  public static void printStringArraysFromPoint( String[][] arrayCollectionsToPrint, Point coordToPrintFrom, boolean printDirection )
  {
      // moves cursor to coordToPrintFrom
    moveCursorToPoint( coordToPrintFrom );
    
      // prints every array in arrayCollectionsToPrint in succesion in a given direction
    for( String[] arrayToPrint : arrayCollectionsToPrint ) // loops for each array in arrayCollectionsToPrint
    { 
      printStringArrayFromCursor( arrayToPrint ); // prints arrayToPrint in printDirection

        // positions the cursor so that the next array will print to the right of the current array
      if(printDirection) // if the printDirection is to the right
      {
        System.out.print("\033[1C"); // moves cursor one column to the right

          // moves cursor up by height of arrayToPrint
        String moveUp = String.format( // initializes escape code that will move the cursor up by the height of arrayToPrint
          "\033[%sA", 
          String.valueOf(arrayToPrint.length - 1)
        );
        System.out.print(moveUp); // prints escape code, moving cursor up by height of arrayToPrint in the process
      } // end of if conditional

        // positions the cursor so that the next array will print below the current array
      else // if the printDirection is down
      {
          // moves cursor to the left by the length of the final string in arrayToPrint
        String moveUp = String.format( // initializes escape code that will move the cursor up by the height of arrayToPrint
          "\033[%sD", 
          String.valueOf(arrayToPrint[arrayToPrint.length - 1].length())
        );
        System.out.print(moveUp); // prints escape code, moving cursor the left by the length of the last line printed
        
        System.out.print("\033[1B"); // moves cursor down one line
      }
    }
  }  
} 