/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import java.awt.Font;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.EmptyBorder;

/**
* This subclass defines an edit-mode implementation of CellsMatrix. Edit mode is the intended mode 
* for editing the cellsMatrix, and certain changes are made within the constructor to better facilitate this.
* 
* <p>A protected, edit-mode implementation of Cell is also included within this class 
*    ({@link CellEditMode CellEditMode}); this class is tailor-made for edit-mode.
* 
* @author Benton Diebold (ceofyeast)
*/
public class CellsMatrixEditMode extends CellsMatrix {
   /**
   * To support edit-mode, the constructor employs three techniques. One technique is the addition of borders 
   * between the cells; this change allows for a clear separation between cells. Another technique is the widening 
   * of the cells. This allows for more space inside the cells themselves so the characters they contain are more 
   * legible. The final technique is the implementation of a constant, large font size for increased legibility. 
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
     * @param cellFont fills the cellFont member variable
     */
    public CellEditMode( char cellText, Font cellFont )
    { 
     this.setHorizontalAlignment(CENTER);

     this.setText( String.valueOf( cellText ) );

     this.setFont( cellFont );

     this.setOpaque( true );

     this.setBorder( new EmptyBorder( 0,0,0,0 ) );

     this.setColumns( 1 );
     
     this.setMargin( new java.awt.Insets( 0,5,0,5 ) );
    }
  }
}
