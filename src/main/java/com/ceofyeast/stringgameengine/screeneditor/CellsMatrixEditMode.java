/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Color;

import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
* This subclass defines an edit-mode implementation of CellsMatrix. Edit mode is the intended mode 
* for editing the cellsMatrix, and the entire class is built around reinforcing this.
* 
* <p>A protected, edit-mode implementation of Cell is also included within this class 
*    ({@link CellEditMode CellEditMode}); this class is tailor-made to support edit-mode.
* 
* @author Benton Diebold (ceofyeast)
*/
public class CellsMatrixEditMode extends CellsMatrix {
   /**
   * To support edit-mode, the constructor employs two techniques. One technique is the addition of borders 
   * between the cells; this change allows for a clear separation between cells. The other technique is the 
   * implementation of a constant, large font size for increased legibility.
   * 
   * @param columnCount initializes columnCount member
   * @param rowCount initializes rowCount member
   * 
   * @throws IllegalArgumentException if column or row count are >= 0
   */
  public CellsMatrixEditMode( int columnCount, int rowCount )
  {
    if(columnCount <= 0 || rowCount <= 0 )
    {
      throw new IllegalArgumentException();
    }
    
    this.columnCount = columnCount;
    this.rowCount = rowCount;

    String toFillWith = "ceofyeast@LAPTOP-MPS827DS:~$";
    
    for( int i = 0; i < rowCount * columnCount; i++ )
    {
      Cell toAdd;
      try{
        toAdd = new CellEditMode( toFillWith.charAt(i) );
      } catch(Exception e){
        toAdd = new CellEditMode( ' ' );
      }
      
      toAdd.setText(toFillWith);
      
      this.add( toAdd );
    }
    
    this.setLayout( new GridLayout( rowCount, columnCount, BORDER_THICKNESS, BORDER_THICKNESS ) );
    
    cellsMatrixScrollPaneView = new JPanel( new GridBagLayout() );
    
    cellsMatrixScrollPaneView.add( this );
    
    cellsMatrixScrollPane = new JScrollPane( cellsMatrixScrollPaneView );
  }
  
  /**
   * This subclass defines an edit-mode implementation of Cell to aid the edit-mode implementation of its
   * container class, CellsMatrixEditMode.
   * 
   * @author Benton Diebold (ceofyeast)
   */
  protected class CellEditMode extends Cell
  {
    /**
     * To reinforce edit-mode, the constructor adds padding to the inside of the cell to make its contents more
     * legible.
     * 
     * @param cellText fills the cellText member variable
     */
    public CellEditMode( char cellText )
    { 
      setDocument( new TextFilteredDocument() );
      
      setColumns( 1 );
      
      setHorizontalAlignment( CENTER );
      
      setText( String.valueOf( cellText ) );

      setFont( font );

      setOpaque( true );

        /*
        Code block adds margin to the cell in order to make the width of the cell equivalent to the height.
        This involves adding margin to the width, because its generally smaller than the height.
        */
      Border line = BorderFactory.createLineBorder( Color.WHITE );
      FontMetrics fm = getFontMetrics( font );
      int height = fm.getAscent() + fm.getDescent();
      int width = fm.getMaxAdvance();
      int sideMargin = ( height - width ) / 2;
      Border empty = new EmptyBorder( 0, sideMargin, 0, sideMargin );
      CompoundBorder marginBorder = new CompoundBorder( line, empty );
      setBorder( marginBorder );
    }
  }
}
