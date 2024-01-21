/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import java.awt.Font;

/**
 * Defines a JPanel subclass that represents a grid of text fields, referred to as cells; the grid itself 
 * is referred to as the cellsMatrix. The purpose of the cellsMatrix is to emulate the look and feel of a 
 * console, whilst also allowing the individual cells/characters to be edited.
 * 
 * <p>The cellsMatrix resides in a cellsMatrixContainer, a JPanel with a FlowLayout manager that allows 
 *    the cellsMatrix to be sized by the size of its contents. This container serves further use, such as 
 *    controlling the border color of the cellsMatrix, and is therefore stored as a member  of cellsMatrix.
 * 
 * <p>The CellsMatrix has two modes, which are defined as constructors; these modes are edit mode, and view mode.
 *    Edit mode is designed for the editing of a screen, while view mode is designed for the viewing of a screen
 *    as it will look in the console. 
 */
class CellsMatrix extends javax.swing.JPanel {
  /**
   * Specifies number of columns of cells in cellsMatrix; can't be negative or zero.
   */
  private int columnCount;

  /**
   * Specifies number of rows of cells in cellsMatrix; can't be negative or zero.
   */
  private int rowCount;

  /**
   * Specifies thickness of border between cells, and between cellsMatrix and 
   * cellsContainer (acts as sides/top/bottom border); has a default value of 3.
   * 
   * The "border" referred to is actually just the horizontal and vertical gap between the cells 
   * in cellsMatrix, as well as the margin between cellsMatrix and its container; this space is 
   * specified by borderThickness. Therefore, the border's color is set by changing the color of 
   * the cellsMatrixContainer; the container is opaque, while the cellsMatrix is translucent.
   */
  public int borderThickness = 3;

  /**
   * Specifies the font size of the cells (text fields) in cellsMatrix; has a default value of 20.
   */
  private int fontSize = 20;

  /**
   * Specifies the name of the font to be used.
   */
  private String fontName = "DejaVu Sans Mono";

  /**
   * Contains the font to be applied to the cells in the cellsMatrix.
   */
  private Font font = new Font( "DejaVu Sans Mono", Font.PLAIN, 20 );

  /**
   * Parent of the cellsMatrix, and allows the cellsMatrix to be sized by it's contents. 
   */
  public javax.swing.JPanel cellsMatrixContainer;
  
  /**
   * Parent of the cellsMatrixContainer, and allows the cellMatrix to be scrolled if it takes up more space
   * than the window.
   */
  public javax.swing.JScrollPane cellsMatrixContainerScrollPane;

  /**
   * Edit mode.
   * 
   * This constructor initializes the cellsMatrix in edit mode. Edit mode is the intended mode for 
   * editing the cellsMatrix due to three factors. One factor is the addition of borders between the cells,
   * as well as between the cellsMatrix and its container; this change allows for a clear separation 
   * between cells. Another factor is the widening of the cells. This allows for more space inside the 
   * cells themselves so the characters they contain are more legible. The final change is the 
   * implementation of a constant, large font size for increased legibility. 
   * 
   * @param columnCount initializes columnCount member
   * @param rowCount initializes rowCount member
   * @param borderThickness initializes borderThickness member
   */
  public CellsMatrix( int columnCount, int rowCount )
  {
    this.columnCount = columnCount;
    this.rowCount = rowCount;

    cellsMatrixContainer = new javax.swing.JPanel();
    cellsMatrixContainer.setLayout( new java.awt.FlowLayout( java.awt.FlowLayout.CENTER, 0, 
      borderThickness // Vertical gap is required in order to get margin above cellsMatrix, which 
                      // is a problem that doesn't exist for the horizontal gap
    ) );

    this.setLayout( new java.awt.GridLayout( rowCount, columnCount, borderThickness, borderThickness ) );

    for( int i = 0; i < rowCount * columnCount; i++ )
    {
      Cell toAdd = new Cell( 'R', fontSize, font );
      this.add( toAdd );
    }

    //char[] toFillWith = new char[]{ ' ','c','e','o','f','y','e','a','s','t','@','L','A','P','T','O','P'};

    cellsMatrixContainer.add( this );
  };

  /**
   * View Mode.
   * 
   * This constructor initializes the cellsMatrix in view mode. View mode is the intended mode for viewing a 
   * representation of screensMatrix as it will appear in the console; this is accomplished using three tricks.
   * For one, the width and height of the cells are set to the max width and height of a character from the font 
   * being used. Seeing as only mono-spaced fonts are allowed, and that no more or less than one character can 
   * exist in a cell, this ensures that the cells are packed as tightly as possible. This reflects the behavior
   * of a console, where the cells in the console are each the size of one mono-spaced character. The second
   * way is the setting of the font size by the user. This ensures that the cellsMatrix replicates the user's
   * console environment as thoroughly as possible. The third and final way is the absence of borders between
   * the cells. This also serves to ensure that the cells are packed together.
   * 
   * @param columnCount initializes columnCount member
   * @param rowCount initializes rowCount member
   * @param faceSize initializes faceSize member
   */
  public CellsMatrix( int columnCount, int rowCount, int fontSize )
  {
    this.columnCount = columnCount;
    this.rowCount = rowCount;
    this.fontSize = fontSize;
    
    cellsMatrixContainer = new javax.swing.JPanel();
    cellsMatrixContainer.setLayout( new java.awt.FlowLayout( java.awt.FlowLayout.CENTER, 0, 0 ) );

    this.setLayout( new java.awt.GridLayout( rowCount, columnCount, 0, 0 ) );

    for( int i = 0; i < rowCount * columnCount; i++ )
    {
      Cell toAdd = new Cell( 'R', fontSize, font );
      this.add( toAdd );
    }

    //char[] toFillWith = new char[]{ ' ','c','e','o','f','y','e','a','s','t','@','L','A','P','T','O','P'};

    cellsMatrixContainer.add( this );
    
    cellsMatrixContainerScrollPane = new javax.swing.JScrollPane( cellsMatrixContainer );
  }

  /**
   * Returns cellsMatrixContainer. It's possible to get the cellsMatrixContainer using cellsMatrix.getParent(), 
   * but using this method makes it more clear what action is being taken.
   * 
   * @return the cellsMatrixContainer
   */
  public javax.swing.JPanel getCellsMatrixContainer()
  {
    return cellsMatrixContainer;
  }

  /**
   * Removes cellsMatrix from its parent.
   */
  public void removeFromParent( )
  {
    cellsMatrixContainerScrollPane.getParent().remove( cellsMatrixContainerScrollPane );
  }

  /**
   * Adds cellsMatrix to the given JFrame.
   * 
   * @param toAddTo JFrame to add cellsMatrix to
   */
  public void addToContentPane( javax.swing.JPanel toAddTo )
  {
    toAddTo.add( cellsMatrixContainerScrollPane );

      // gets the Jframe associated with the content panel
    javax.swing.JFrame toAddToJFrame = ( javax.swing.JFrame ) javax.swing.SwingUtilities.getWindowAncestor( toAddTo );

      // packs the JFrame associated with the content panel
    toAddToJFrame.pack();
    
    resize( toAddToJFrame );
  }

  /**
   * Sizes cellsMatrix and its parent, cellsMatrixContainer. This  operation must only be carried
   * out on a cellsMatrix after said cellsMatrix has been added to the content pane. This is because 
   * the sizing is based on the size of a single cell in the cellsMatrix, and the individual cells are only
   * assigned sizes when their parent is added to the content pane.
   */
  private void resize( javax.swing.JFrame cellsMatrixContainerScrollPanelJFrame )
  {
      // Code block gathers sizing data about a single cell in the cellsMatrix
    javax.swing.JTextField cellInCellMatrix = ( javax.swing.JTextField ) this.getComponent( 0 );
    java.awt.Dimension cellDimensions = cellInCellMatrix.getSize();
    int cellWidth = (int) cellDimensions.getWidth();
    int cellHeight = (int) cellDimensions.getHeight();

      /*
      Code block utilizes single-cell sizing data initialized above to set the bounds of the cellsMatrixContainer based
      on the number of cells inside the cellsMatrix, as well as the thickness of the borders within the
      cellsMatrix
      */
    int cellsMatrixWidth = 
      ( cellWidth * columnCount ) + // accounts for added width from cells
      ( borderThickness * ( columnCount - 1 ) ) + // accounts for added width from borders between cells
      ( borderThickness * 2 ); // accounts for added width from outside borders
    int cellsMatrixHeight = 
      ( cellHeight * rowCount ) + // accounts for added height from cells
      ( borderThickness * ( rowCount - 1 ) ) + // accounts for added height from borders between cells
      ( borderThickness * 2 ); // accounts for added height from outside borders
    cellsMatrixContainer.setSize( cellsMatrixWidth, cellsMatrixHeight );
    
    cellsMatrixContainerScrollPane.setSize(
      cellsMatrixContainerScrollPanelJFrame.getSize().width - 10,
      cellsMatrixContainerScrollPanelJFrame.getSize().height - 100
    );
    
    /**
    java.awt.Dimension cellsMatrixContainerScrollPaneParentSize = cellsMatrixContainerScrollPanelJFrame.getSize();
    
    cellsMatrixContainerScrollPane.setMaximizedBounds(  );
    
    cellsMatrixContainerScrollPane.revalidate();
    cellsMatrixContainerScrollPane.repaint();
    
    cellsMatrixContainerScrollPane.setSize( cellsMatrixWidth, cellsMatrixHeight );
    
    cellsMatrixContainerScrollPane.revalidate();
    cellsMatrixContainerScrollPane.repaint();
    
         /*
      Code block initializes variables containing the width and height of the cellsMatrixContainerScrollPanel's  
      JFrame parent
      
    int cellsMatrixContainerScrollPanelParentWidth = 
      ( int ) cellsMatrixContainerScrollPanelJFrame.getSize().getWidth();
    int cellsMatrixSContainerScrollPanelParentHeight = 
      ( int ) cellsMatrixContainerScrollPanelJFrame.getSize().getHeight();
    
    java.awt.Dimension cellsMatrixContainerScrollPaneBounds = new java.awt.Dimension( cellsMatrixContainer.getSize() );
    
      /*
      Code block ensures that the bounds of cellsMatrixContainerScrollPane don't exceed the bounds of the JFrame it 
      resides in. This, in turn, ensures that if the bounds of cellsMatrixContainer exceed the bounds of said JFrame, 
      a scroll bar will appear.
      
    if( cellsMatrixWidth > cellsMatrixContainerScrollPanelParentWidth )
    {
      cellsMatrixContainerScrollPaneBounds.width = cellsMatrixContainerScrollPanelParentWidth ;
    }
    if( cellsMatrixHeight > cellsMatrixSContainerScrollPanelParentHeight )
    {
      cellsMatrixContainerScrollPaneBounds.height = cellsMatrixSContainerScrollPanelParentHeight ;
    }
    
      /*
      Sets the bounds of the cellsMatrixContainer scroll pane to 
      */
    //cellsMatrixContainerScrollPane.setSize( cellsMatrixContainerScrollPaneBounds );
  }

  /**
   * Initializes font using the fontName and fontSize member variables.
   */
  public void initializeFont()
  {
    this.font = new Font( fontName, Font.PLAIN, fontSize );
  }
}
