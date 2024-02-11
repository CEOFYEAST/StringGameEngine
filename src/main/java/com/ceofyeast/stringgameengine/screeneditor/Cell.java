/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.text.AbstractDocument;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

/**
 * Defines a JTextField subclass that represents a cell in the cellsMatrix. As an abstract class, Cell can only
 * be instantiated by its two subclasses, {@link CellEditMode CellEditMode} and {@link CellViewMode CellViewMode}.
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
 * @author Benton Diebold (ceofyeast)
 */
abstract class Cell extends javax.swing.JTextField 
{ 
  /**
   * When set as a Cell's document, this class acts as a filter for text being added to the Cell.
   */
  protected class TextFilteredDocument extends PlainDocument {
    private final int MAX_CELL_TEXT_LENGTH = 1;
    
    protected TextFilteredDocument()
    {
      addDocumentListener( new InsertListener() );
    }
    
    /**
     * Overrides remove, 
     * 
     * @param offs
     * @param len
     * @throws BadLocationException 
     */
    /*
    @Override
    public void remove(int offs, int len) throws BadLocationException
    {
      System.out.println( "Starting offset: " + offs );
      //super.remove(offs, len);
      replace( 0, 1, " ", getDefaultRootElement().getAttributes() );
    }
    */
    
    @Override 
    public void replace( int offs, int length, String text, AttributeSet a ) 
      throws BadLocationException {
      
      if( text.length() > MAX_CELL_TEXT_LENGTH )
      {
        length = MAX_CELL_TEXT_LENGTH;
        
        text = text.substring( 0, MAX_CELL_TEXT_LENGTH );
      }
      
      super.replace( offs, length, text, a );
    }
    
    /**
     * Overrides insertString, turning it into a text filter that prevents a Cell from containing any more 
     * text than one character.
     * 
     * @param offs the offset at which to insert the string
     * @param toInsert the string to be inserted
     * @param a the text attributes of the string to be inserted
     * 
     * @throws BadLocationException 
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
    
    protected class InsertListener implements DocumentListener 
    {
      public void changedUpdate( DocumentEvent event ){}
      
      public void removeUpdate( DocumentEvent event ){}
      
      public void insertUpdate( DocumentEvent event )
      {
        try
        {
          AbstractDocument callingDocument = (AbstractDocument) event.getDocument();
          
          String text = callingDocument.getText(0, callingDocument.getLength());
          
          if( callingDocument.getLength() == 0 )
          {
            System.out.println( "Empty Insert Attempt" );
            
            callingDocument.insertString( 0, " ", callingDocument.getDefaultRootElement().getAttributes());
          }
        }
        catch( BadLocationException e )
        {
          e.printStackTrace();
        }
      }
    }
  }
}
