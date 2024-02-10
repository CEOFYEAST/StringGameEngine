/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import java.awt.Font;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.EmptyBorder;

/**
* This subclass defines an edit mode version of implementation of CellsMatrix. Edit mode is the intended mode 
* for editing the cellsMatrix, and certain changes are made within the constructor to better facilitate this; 
* An inner-class implementation of Cell is also employed.
* @author bento
*/
public class CellsMatrixEditMode extends CellsMatrix {
  
   /**
   * This constructor initializes the cellsMatrix in edit mode. Edit mode is the intended mode for 
   * editing the cellsMatrix due to three factors. One factor is the addition of borders between the cells; 
   * this change allows for a clear separation between cells. Another factor is the widening of the cells.
   * This allows for more space inside the cells themselves so the characters they contain are more legible. 
   * The final change is the implementation of a constant, large font size for increased legibility. 
   * 
   * @param columnCount initializes columnCount member
   * @param rowCount initializes rowCount member
   * @param BORDER_THICKNESS initializes BORDER_THICKNESS member
   */
  public CellsMatrixEditMode( int columnCount, int rowCount )
  {
    this.columnCount = columnCount;
    this.rowCount = rowCount;

    this.setLayout( new java.awt.GridLayout( rowCount, columnCount, BORDER_THICKNESS, BORDER_THICKNESS ) );

    String toFillWith = "ceofyeast@LAPTOP-MPS827DS:~$";
    
    for( int i = 0; i < rowCount * columnCount; i++ )
    {
      Cell toAdd;
      try{
        toAdd = new CellEditMode( 
          toFillWith.charAt(i), 
          font );
      } catch(Exception e){
        toAdd = new CellEditMode( ' ', font );
      }
      
      this.add( toAdd );
    }

    cellsMatrixScrollPaneView = new javax.swing.JPanel( new java.awt.GridBagLayout() );
    
    cellsMatrixScrollPaneView.add( this );
    
    cellsMatrixScrollPane = new javax.swing.JScrollPane( cellsMatrixScrollPaneView );
  }
  
  protected class CellEditMode extends Cell
  {
    /**
     * Constructs a cell using a given cellText char and cellFont. 
     * 
     * @param cellText fills the cellText member variable
     * @param cellFont fills the cellFont member variable
     */
    public CellEditMode( char cellText, Font cellFont )
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
