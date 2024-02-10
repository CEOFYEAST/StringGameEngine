/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import java.awt.Font;
import javax.swing.border.EmptyBorder;

/**
 * Defines a JTextField subclass that represents a cell in the cellsMatrix. The cellsMatrix is an editable
 * representation of the console, which means that the cells inside the cellsMatrix are editable representations 
 * of the "console cells" in a console. Therefore, to understand the functionality of the cell object, one must
 * understand the functionality of the "console cell". 
 * 
 * <p>Every console cell inside the console is the same size and can contain, at most, a single character of the 
 *    same font. If a console cell doesn't contain a character, it's sized as if it does. Since the console can 
 *    only use mono-spaced fonts, the console cells' widths and heights are determined by the max width and height 
 *    of a character from the font being used because the characters are all the same size. The console cells are 
 *    then scaled up or down based on the font size.
 *    
 * <p>The Cell class seeks to replicate all of these behaviors. The individual cells are sized automatically by 
 *    java swing, as long as they're initialized containing a single character from a mono-spaced font with zero 
 *    margin.
 */
class Cell extends javax.swing.JTextField {
  private static int scrollCount = 0;
  
  /*
  @Override public void scrollRectToVisible( java.awt.Rectangle r)
  {
    
    scrollCount += 1;
    
    System.out.println("Scrolling (" + scrollCount + ")");
    System.out.println("Rectangle (" + r.toString() + ")");
    
    return;
  }
  */
  
  /**
   * Constructs a cell using a given cellText char and cellFont. 
   * 
   * @param cellText fills the cellText member variable
   * @param cellFont fills the cellFont member variable
   */
  public Cell( char cellText, Font cellFont )
  { 
    this.setHorizontalAlignment(CENTER);
    
    this.setText( String.valueOf( cellText ) );
    
    this.setFont( cellFont );
    
    this.setOpaque( true );
    
    this.setBorder( new EmptyBorder( 0,0,0,0 ) );
    
    this.setMargin( new java.awt.Insets( 0,0,0,0 ) );
    
    this.setColumns( 1 );
  }
}
