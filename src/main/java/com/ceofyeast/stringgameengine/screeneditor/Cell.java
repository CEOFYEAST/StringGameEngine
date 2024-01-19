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
  /**
   * Initializes the char that the cell contains. Default value is a space.
   */
  char cellText = ' ';

  /**
   * Initializes the font size of the cell. Default value is 20.
   */
  int fontSize = 20;

  /**
   * Specifies the font of the cell. Default font is a plain DejaVu Sans Mono with a font size of 20.
   */
  Font cellFont = new Font( "DejaVu Sans Mono", Font.PLAIN, 20 );

  /**
   * Constructs a cell using a given cellText char, fontSize and cellFont. 
   * 
   * @param cellText fills the cellText member variable
   * @param fontSize fills the fontSize member variable
   * @param cellFont fills the cellFont member variable
   */
  public Cell( char cellText, int fontSize, Font cellFont )
  {
    this.setOpaque( true );

    this.setBorder( new EmptyBorder( 0,0,0,0 ) );

    this.setHorizontalAlignment( javax.swing.JTextField.CENTER );

    this.setFont( cellFont );

    this.setText( String.valueOf( cellText ) );

    this.setMargin( new java.awt.Insets( 0,0,0,0 ) );
  }
}
