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
 * <p>The cellsMatrix resides in {@link CellsMatrix#scrollPaneView scrollPaneView}, a JPanel
 *    with a GridBag layout manager that allows the cellsMatrix to be centered within the scroll pane.
 *    
 * <p>The scrollPaneView then resides in 
 *    {@link CellsMatrix#cellsMatrixScrollPane cellsMatrixScrollPane},
 *    a JScrollPane designed to provide a scrolling function to the cellsMatrix when it's bounds exceed 
 *    those of the content pane it resides in. This is the top-level parent of the cellsMatrix,
 *    and is therefore what's added/removed from the content pane. Two methods are included in CellsMatrix
 *    to facilitate the removal and addition of the cellsMatrix from the content pane due to this added 
 *    complexity, and it's highly recommended that they be used. The methods in question are
 *    {@link CellsMatrix#removeFromParent() removeFromParent} and 
 *    {@link CellsMatrix#addToContentPane(javax.swing.JPanel) addToContentPane}.
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
   * Parent of {@link CellsMatrix#scrollPaneView scrollPaneView}, and allows the cellMatrix to be scrolled if it 
   * takes up more space than the window; is also the top-level parent of the cellsMatrix, and is therefore what's 
   * added/removed from the content pane.
   */
  private javax.swing.JScrollPane cellsMatrixScrollPane;
  
  /**
   * Parent of the cellsMatrix, and whose sole purpose is to center the cellsMatrix inside the scroll pane; is
   * also the view of the cellsMatrixScrollPane, as the name implies.
   */
  private javax.swing.JPanel scrollPaneView;

  /**
   * Specifies thickness of border between cells; has a default value of 3.
   * 
   * The "border" referred to is actually just the horizontal and vertical gap between the cells in cellsMatrix; 
   * this space is specified by BORDER_THICKNESS. Therefore, the border's color is set by changing the color of 
   * the cellsMatrix.
   */
  private final int BORDER_THICKNESS = 3;
  
  

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
  public CellsMatrix( int columnCount, int rowCount )
  {
    this.columnCount = columnCount;
    this.rowCount = rowCount;

    this.setLayout( new java.awt.GridLayout( rowCount, columnCount, BORDER_THICKNESS, BORDER_THICKNESS ) );

    for( int i = 0; i < rowCount * columnCount; i++ )
    {
      Cell toAdd = new Cell( 'R', font );
      this.add( toAdd );
    }

    //char[] toFillWith = new char[]{ ' ','c','e','o','f','y','e','a','s','t','@','L','A','P','T','O','P'};

    scrollPaneView = new javax.swing.JPanel( new java.awt.GridBagLayout() );
    
    scrollPaneView.add( this );
    
    cellsMatrixScrollPane = new javax.swing.JScrollPane( scrollPaneView );
  };

  /**
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
    initializeFont();

    this.setLayout( new java.awt.GridLayout( rowCount, columnCount, 0, 0 ) );

    for( int i = 0; i < rowCount * columnCount; i++ )
    {
      Cell toAdd = new Cell( 'R', font );
      this.add( toAdd );
    }

    //char[] toFillWith = new char[]{ ' ','c','e','o','f','y','e','a','s','t','@','L','A','P','T','O','P'};
    
    scrollPaneView = new javax.swing.JPanel( new java.awt.GridBagLayout() );
    
    scrollPaneView.add( this );
    
    cellsMatrixScrollPane = new javax.swing.JScrollPane( scrollPaneView );
  }
  
  /**
   * Returns cellsMatrixScrollPane.
   * 
   * @return the cellsMatrixScrollPane
   */
  public javax.swing.JScrollPane getcellsMatrixScrollPane()
  {
    return cellsMatrixScrollPane;
  }

  /**
   * Removes cellsMatrix from its parent by removing it's top-level parent, in this case the 
   * {@link CellsMatrix#cellsMatrixScrollPane cellsMatrixScrollPane} from it's parent.
   */
  public void removeFromParent( )
  {
    cellsMatrixScrollPane.getParent().remove( cellsMatrixScrollPane );
  }

  /**
   * Adds cellsMatrix to the given content pane toAddTo by adding it's top-level parent, in this case the 
   * {@link CellsMatrix#cellsMatrixScrollPane cellsMatrixScrollPane} to toAddTo. This method
   * also takes the liberty of calling the {@link CellsMatrix#setSize(javax.swing.JPanel) setSize} method,
   * which properly size the cellsMatrix so it can actually be displayed after being added.
   * 
   * @param toAddTo content pane to add cellsMatrix to
   */
  public void addToContentPane( javax.swing.JPanel toAddTo )
  {
    toAddTo.add( cellsMatrixScrollPane );

      // gets the Jframe associated with the content pane
    javax.swing.JFrame toAddToJFrame = ( javax.swing.JFrame ) javax.swing.SwingUtilities.getWindowAncestor( toAddTo );

      // packs the JFrame associated with the content pane
    toAddToJFrame.pack();
    
    setSize( toAddTo );
  }

  /**
   * Sizes cellsMatrix by sizing its parent, {@link CellsMatrix#scrollPaneView scrollPaneView}, as
   * well as the {@link CellsMatrix#cellsMatrixScrollPane scroll pane} that the 
   * scrollPaneView resides in. This method should be called right after adding the cellsMatrix to the 
   * content pane; this is because the size of the cellsMatrix must be set before it can be displayed.
   */
  public void setSize( javax.swing.JPanel cellsMatrixContentPane )
  {
      /*
      Sizes the cellsMatrix non-explicitly by forcing it to re-calculate it's own preferred size, which is 
      now based on the preferred size of its children cells.
      */
    this.setPreferredSize( this.getPreferredSize() );
    
      /*
      Code block sizes the cellsMatrixScrollPane to be the size of the content pane, minus the widths of 
      the scrollbars
      */
    int vertScollBarWidth = cellsMatrixScrollPane.getVerticalScrollBar().getWidth();
    int horizontalScollBarWidth = cellsMatrixScrollPane.getHorizontalScrollBar().getWidth();
    cellsMatrixScrollPane.setSize(
      cellsMatrixContentPane.getSize().width - vertScollBarWidth,
      cellsMatrixContentPane.getSize().height - horizontalScollBarWidth
    );
    
      /* 
      Ensures the scrollPaneView is at-least as large as the cellsMatrixScrollPane. This in turn ensures that the 
      cellsMatrix will always appear centered in the scrollPaneView as long as the cellsMatrix is smaller than the 
      scrollPaneView
      */
    scrollPaneView.setMinimumSize( cellsMatrixScrollPane.getSize() );
    
      /*
      Code block ensures that using the scrollbar arrows, as well as clicking within the scrollbar area, both 
      increment the scroll bar by the width/height (depends on the scrollbar's orientation) of a single cell 
      */
    java.awt.FontMetrics fontMetrics = getToolkit().getFontMetrics( font );
    int cellWidth = (int) Math.floor( fontMetrics.getMaxAdvance() + 1 );
    int cellHeight = (int) Math.floor( fontMetrics.getAscent() + fontMetrics.getDescent() + 1 );
    cellsMatrixScrollPane.getHorizontalScrollBar().setUnitIncrement( cellWidth );
    cellsMatrixScrollPane.getHorizontalScrollBar().setBlockIncrement( cellWidth );
    cellsMatrixScrollPane.getVerticalScrollBar().setUnitIncrement( cellHeight );
    cellsMatrixScrollPane.getVerticalScrollBar().setBlockIncrement( cellHeight );
  }

  /**
   * Initializes font using the fontName and fontSize member variables.
   */
  public void initializeFont()
  {
    this.font = new Font( fontName, Font.PLAIN, fontSize );
  }
}
