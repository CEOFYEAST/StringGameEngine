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
 * <p>The CellsMatrix has two modes, which are defined as subclasses; these modes are edit mode, and view mode
 *    ({@link CellsMatrixEditMode CellsMatrixEditMode}, {@link CellsMatrixViewMode CellsMatrixViewMode}).
 *    These subclasses are the only way to instantiate a cellsMatrix. Edit mode is designed for the editing of 
 *    a screen, while view mode is designed for the viewing of a screen as it will look in the console. These
 *    classes then have their own protected Cell implementations, {@link CellsMatrixViewMode.CellViewMode CellViewMode} and 
 *    {@link CellsMatrixEditMode.CellEditMode CellEditMode}; these classes are tailor built to aid their respective console mode.
 * 
 * <p>The cellsMatrix resides in {@link CellsMatrix#cellsMatrixScrollPaneView cellsMatrixScrollPaneView}, a JPanel
 *    with a GridBag layout manager that allows the cellsMatrix to be centered within the scroll pane. This JPanel 
 *    also serves as the view for the cellsMatrixScrollPane.
 *    
 * <p>The cellsMatrixScrollPaneView then resides in 
 *    {@link CellsMatrix#cellsMatrixScrollPane cellsMatrixScrollPane},
 *    a JScrollPane designed to provide a scrolling function to the cellsMatrix when it's bounds exceed 
 *    those of the content pane it resides in. This is the top-level parent of the cellsMatrix,
 *    and is therefore what's added/removed from the content pane. Two methods are included in CellsMatrix
 *    to facilitate the removal and addition of the cellsMatrix from the content pane due to this added 
 *    complexity, and it's highly recommended that they be used. The methods in question are
 *    {@link CellsMatrix#removeFromParent() removeFromParent} and 
 *    {@link CellsMatrix#addToContentPane(javax.swing.JPanel) addToContentPane}.
 * 
 * @author Benton Diebold (ceofyeast)
 */
public abstract class CellsMatrix extends javax.swing.JPanel {
  /**
   * Specifies number of columns of cells in cellsMatrix; can't be negative or zero.
   */
  protected int columnCount;

  /**
   * Specifies number of rows of cells in cellsMatrix; can't be negative or zero.
   */
  protected int rowCount;

  /**
   * Specifies the font size of the cells (text fields) in cellsMatrix; has a default value of 20.
   */
  protected int fontSize = 20;

  /**
   * Specifies the name of the font to be used.
   */
  protected String fontName = "DejaVu Sans Mono";

  /**
   * Contains the font to be applied to the cells in the cellsMatrix.
   */
  protected Font font = new Font( "DejaVu Sans Mono", Font.PLAIN, 19 );
  
  /**
   * Parent of {@link CellsMatrix#cellsMatrixScrollPaneView cellsMatrixScrollPaneView}, and allows the cellMatrix to 
   * be scrolled if it takes up more space than the window; is also the top-level parent of the cellsMatrix, 
   * and is therefore what's added/removed from the content pane.
   */
  protected javax.swing.JScrollPane cellsMatrixScrollPane;
  
  /**
   * Parent of the cellsMatrix, and whose sole purpose is to center the cellsMatrix inside the scroll pane; is
   * also the view of the cellsMatrixScrollPane, as the name implies.
   */
  protected javax.swing.JPanel cellsMatrixScrollPaneView;

  /**
   * Specifies thickness of border between cells; has a default value of 3.
   * 
   * The "border" referred to is actually just the horizontal and vertical gap between the cells in cellsMatrix; 
   * this space is specified by BORDER_THICKNESS. Therefore, the border's color is set by changing the background 
   * color of the cellsMatrix.
   */
  protected final int BORDER_THICKNESS = 3;
  
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
   * Sizes cellsMatrix by sizing its parent, {@link CellsMatrix#cellsMatrixScrollPaneView cellsMatrixScrollPaneView}, 
   * as well as the {@link CellsMatrix#cellsMatrixScrollPane scroll pane} that the 
   * cellsMatrixScrollPaneView resides in. This method should be called right after adding the cellsMatrix to the 
   * content pane; this is because the size of the cellsMatrix must be set before it can be displayed.
   * 
   * @param cellsMatrixContentPane The content pane that the cellsMatrix resides in.
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
      Ensures the cellsMatrixScrollPaneView is at-least as large as the cellsMatrixScrollPane. This in turn ensures 
      that the cellsMatrix will always appear centered in the cellsMatrixScrollPaneView as long as the cellsMatrix 
      is smaller than the cellsMatrixScrollPaneView
      */
    cellsMatrixScrollPaneView.setMinimumSize( cellsMatrixScrollPane.getSize() );
    
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
  protected void initializeFont()
  {
    this.font = new Font( fontName, Font.PLAIN, fontSize );
  }
}