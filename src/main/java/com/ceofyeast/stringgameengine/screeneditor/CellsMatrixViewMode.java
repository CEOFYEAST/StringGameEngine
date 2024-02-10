/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import java.awt.Color;
import java.awt.Font;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.EmptyBorder;

/**
 * This subclass defines a view-mode implementation of CellsMatrix. View mode is the intended mode 
 * for viewing the cellsMatrix, and certain changes are made within the constructor to better facilitate this.
 * 
 * <p>A protected, view-mode implementation of Cell is also included within this class 
*    ({@link CellViewMode CellViewMode}); this class is tailor-made to support view-mode.
 * 
 * @author Benton Diebold (ceofyeast)
 */
public class CellsMatrixViewMode extends CellsMatrix {
   /**
   * To support view mode, the constructor employs three techniques. 
   * For one, the width and height of the cells are set to the max width and height of a character from the font 
   * being used. Seeing as only mono-spaced fonts are allowed, and that no more or less than one character can 
   * exist in a cell, this ensures that the cells are packed as tightly as possible. This reflects the behavior
   * of a console, where the cells in the console are each the size of one mono-spaced character. The second
   * way is the setting of the font size by the user. This ensures that the cellsMatrix replicates the user's
   * console environment as thoroughly as possible. The third and final way is the removal of borders between
   * the cells; this also serves to ensure that the cells are packed together.
   * 
   * @param columnCount initializes columnCount member
   * @param rowCount initializes rowCount member
   * @param faceSize initializes faceSize member
   */
  public CellsMatrixViewMode( int columnCount, int rowCount, int fontSize )
  {
    this.columnCount = columnCount;
    this.rowCount = rowCount;
    this.fontSize = fontSize;
    initializeFont();

    this.setLayout( new java.awt.GridLayout( rowCount, columnCount, 0, 0 ) );

    String toFillWith = "ceofyeast@LAPTOP-MPS827DS:~$";
    
    for( int i = 0; i < rowCount * columnCount; i++ )
    {
      Cell toAdd;
      try{
        toAdd = new CellViewMode( 
          toFillWith.charAt(i), 
          font );
      } catch(Exception e){
        toAdd = new CellViewMode( ' ', font );
      }
      
      toAdd.setBackground(Color.BLUE);
      
      if( i % 2 == 1)
      {
        toAdd.setBackground(Color.RED);
      }
      
      this.add( toAdd );
    }
    
    cellsMatrixScrollPaneView = new javax.swing.JPanel( new java.awt.GridBagLayout() );
    
    cellsMatrixScrollPaneView.add( this );
    
    cellsMatrixScrollPane = new javax.swing.JScrollPane( cellsMatrixScrollPaneView );
  }
  
  /**
   * This subclass defines a view-mode implementation of Cell to aid the view-mode implementation of its
   * container class, {@link CellsMatrixViewMode CellsMatrixViewMode}.
   * 
   * @author Benton Diebold (ceofyeast)
   */
  protected class CellViewMode extends Cell
  {
    /**
     * Override is used to prevent unnecessary scrolling; scrolling isn't required in view mode.
     * 
     * @param r Unused param.
     */
    @Override public void scrollRectToVisible( java.awt.Rectangle r)
    {
      return;
    }
    
    /**
     * Constructs a cell using a given cellText char and cellFont. 
     * 
     * @param cellText fills the cellText member variable
     * @param cellFont fills the cellFont member variable
     */
    public CellViewMode( char cellText, Font cellFont )
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
}
