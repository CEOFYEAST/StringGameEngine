/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * Defines a JTextField subclass that represents a cell in the cellsMatrix. As an abstract class, Cell can only
 * be instantiated by its two subclasses, {@link CellsMatrixEditMode.CellEditMode CellEditMode} and {@link CellsMatrixViewMode.CellViewMode CellViewMode}.
 * These subclasses are built to support two subclasses of {@link CellsMatrix CellsMatrix}, which are 
 * {@link CellsMatrixEditMode CellsMatrixEditMode} and {@link CellsMatrixViewMode CellsMatrixViewMode} 
 * respectively. The Cell subclasses are protected members of these classes, so they can only be used in the 
 * context of their particular implementation.
 * 
 * <p>As for what a Cell actually is, the cellsMatrix is an editable representation of the console, which means 
 *    that the cells inside the cellsMatrix are editable representations of the "console cells" in a console. 
 *    Therefore, to understand the functionality of the cell object, one must understand the functionality of 
 *    the "console cell". 
 * 
 * <p>Every console cell inside the console is the same size and can contain, at most, a single character of the 
 *    same font. If a console cell doesn't contain a character, it's sized as if it does. Since the console can 
 *    only use mono-spaced fonts, the console cells' widths and heights are determined by the max width and height 
 *    of a character from the font being used because the characters are all the same size. The console cells are 
 *    then scaled up or down based on the font size.
 *    
 * <p>The Cell class seeks to replicate all of these behaviors. The individual cells are sized automatically by 
 *    java swing as long as they're initialized containing a single character from a mono-spaced font with zero 
 *    margin.
 * 
 * <p>The Cell class also contains a protected implementation of PlainDocument, 
 *    {@link TextFilteredDocument TextFilteredDocument}, which filters inserted text to make sure the contents
 *    of a Cell never surpass a single character in length. Both Cell implementations set this as their 
 *    document upon construction.
 * 
 * @author Benton Diebold (ceofyeast)
 */
public abstract class Cell extends javax.swing.JTextField 
{ 
  /**
   * When set as a Cell's document, this class acts as a filter for text being added to the Cell.
   * 
   * @author Benton Diebold
   */
  protected class TextFilteredDocument extends PlainDocument {
    /**
     * Overrides insertString, turning it into a text filter that prevents a Cell from containing any more 
     * text than one character.
     * 
     * @param offs the offset at which to insert the string
     * @param toInsert the string to be inserted
     * @param a the text attributes of the string to be inserted
     * 
     * @throws BadLocationException if a bad location is supplied.
     */
    @Override
    public void insertString( int offs, String toInsert, AttributeSet a ) 
      throws BadLocationException {
      
      if( toInsert != null )
      { 
        if( getLength() > 0 )
        {
          return;
        }
        
        if( toInsert.length() > 1 )
        {
          // Trims toInsert down to it's first character before passing it to insertString
          toInsert = toInsert.substring( 0, 1 );
        }
        
        super.insertString( offs, toInsert, a );
      }
    }
  }
}
