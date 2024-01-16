/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

  // Used to enable full-screen mode
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;

  // Used to close window
import java.awt.event.WindowEvent;

  // Used to size cells based on font size
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;

import javax.swing.border.EmptyBorder;

import java.util.HashMap;
import java.util.Map;

import java.io.File;

/**
 *
 * @author bento
 */
public class ScreenEditorTesting extends javax.swing.JFrame {
  /**
   * Used to enable full-screen mode
   */
  static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];
  
  /**
   * Creates new form ScreenEditorTesting
   */
  public ScreenEditorTesting() 
  {
    initComponents();
    
    device.setFullScreenWindow( this );
  }
  
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
     * Specifies number of columns of cells in cellsMatrix. Can't be negative or zero, and has a default value
     * of 10.
     */
    private int columnCount = 10;
    
    /**
     * Specifies number of rows of cells in cellsMatrix. Can't be negative or zero, and has a default value
     * of 10.
     */
    private int rowCount = 10;
    
    /**
     * Specifies thickness of border between cells, and between cellsMatrix and 
     * cellsContainer (acts as sides/top/bottom border). Has a default value of 3.
     * 
     * The "border" referred to is actually just the horizontal and vertical gap between the cells 
     * in cellsMatrix, as well as the margin between cellsMatrix and its container; this space is 
     * specified by borderThickness. Therefore, the border's color is set by changing the color of 
     * the cellsMatrixContainer; the container is opaque, while the cellsMatrix is translucent.
     */
    private int borderThickness = 3;
    
    /**
     * Specifies the font size of the cells (text fields) in cellsMatrix. Has a default value of 20.
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
    private javax.swing.JPanel cellsMatrixContainer;
    
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
      }
      
      //char[] toFillWith = new char[]{ ' ','c','e','o','f','y','e','a','s','t','@','L','A','P','T','O','P'};

      cellsMatrixContainer.add( this );
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
     * Initializes font using the fontName and fontSize member variables
     */
    public void initializeFont()
    {
      this.font = new Font( fontName, Font.PLAIN, fontSize );
    }
  }
  
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
  
  /*
  Font Stuff:
    // makes a font available to the system
  try {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(
      "src/main/java/com/ceofyeast/stringgameengine/screeneditor/DejaVuSansMono.ttf"
    )));

    System.out.println( "Success" );

  } catch (Exception e) {
      e.printStackTrace();
  } 
  
    // potentially useful method of constructing a font 
  Map< AttributedCharacterIterator.Attribute, String > toReturnAttributes = new HashMap<>(); // Contains extra attributes to add to font
  toReturnAttributes.put( TextAttribute.FAMILY, "Monospaced" ); // Adds monospaced attribute to attributes
  toReturnAttributes.put( TextAttribute.FAMILY, "SansSerif" );
  toReturnAttributes.put( TextAttribute.FAMILY, "sans_serif" ); // Adds sans serif attribute to attributes
  toReturn = toReturn.deriveFont( toReturnAttributes ); // Updates cellsFont with attributes in attributes 
  
  */
  
  /*
  Sizing cellsMatrix after going full screen and adding cellsMatrixContainer to the displayComponent:
  
  
    // Code block sizes cellMatrixContainer, making cellsMatrix visible 
  javax.swing.JTextField cellInCellMatrix = ( javax.swing.JTextField ) cellsMatrix.getComponent( 0 );
  java.awt.Dimension cellDimensions = cellInCellMatrix.getSize();
  int cellWidth = (int) cellDimensions.getWidth();
  int cellHeight = (int) cellDimensions.getHeight();
  cellsMatrix.getParent().setBounds( 
    10, 
    10,
    ( cellWidth * columnCount ) + ( cellMatrixBorderSize * ( columnCount - 1 ) ) + ( cellMatrixBorderSize * 2 ), 
    ( cellHeight * rowCount ) + ( cellMatrixBorderSize * ( rowCount - 1 ) ) + ( cellMatrixBorderSize * 2 )
    // for last two args ( width/height )
      // 1st () accounts for added width/height from cells
      // 2nd () accounts for added width/height from borders between cells
      // 3rd () accounts for added width/height from outside borders
  );
  cellsMatrix.setBackground( Color.GRAY );
  cellsMatrix.getParent().setBackground( Color.GRAY );
  
  */

  public void addFontsInFontFile()
  {
    try
    {
      File dir = new File(
         "src/main/java/com/ceofyeast/stringgameengine/screeneditor/userfonts/"
      );
      
      File[] directoryListing = dir.listFiles();
      
      if( directoryListing != null )
      {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
       
          // for loop iterates for each file in the font folder
        for( File child : directoryListing )
        {
          ge.registerFont( Font.createFont( 
              Font.TRUETYPE_FONT, child
          ) );
        }
      }
      
    } 
    catch( Exception e){ e.printStackTrace(); }
  }
  
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    jTextArea1 = new javax.swing.JTextArea();
    displayFontsButton = new javax.swing.JButton();
    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    closeMenuItem = new javax.swing.JMenuItem();

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(null);

    displayFontsButton.setText("Fonts");
    displayFontsButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        displayFontsButtonActionPerformed(evt);
      }
    });
    getContentPane().add(displayFontsButton);
    displayFontsButton.setBounds(100, 330, 72, 23);

    fileMenu.setText("File");

    closeMenuItem.setText("Close");
    closeMenuItem.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        closeActionPerformed(evt);
      }
    });
    fileMenu.add(closeMenuItem);

    menuBar.add(fileMenu);

    setJMenuBar(menuBar);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
    // TODO add your handling code here:
    this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
  }//GEN-LAST:event_closeActionPerformed

  private void displayFontsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayFontsButtonActionPerformed
    System.out.println( "displaying fonts" );
    
      /*
      code block constructs JList, with a visible row count of 16, of available font -
      family names (called fontsList) 
      */
    String[] fontFamilyNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    javax.swing.JList<String> fontsList = new javax.swing.JList<>( fontFamilyNames );
    fontsList.setVisibleRowCount( 16 );
    
      // constructs scroll pane, used to scroll vertically through fontsList
    javax.swing.JScrollPane fontsListScrollPane = new javax.swing.JScrollPane( fontsList );
    
      // removes horizontal scroll bar from fontsListScrollPane
    fontsListScrollPane.setHorizontalScrollBarPolicy( javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
    
      /*
      information about the displayFontsButton (the button used to display the fonts list)

      this information is used to find the bounds of fontsList as well as the unit increment for the -
      vertical scroll bar
      */
    int displayFontsButtonXPos = displayFontsButton.getX();
    int displayFontsButtonYPos = displayFontsButton.getY();
    int displayFontsButtonWidth = displayFontsButton.getWidth();
    int displayFontsButtonHeight = displayFontsButton.getHeight();
    
      /*
      information about the fontsList

      this information is used to set the bounds of fontsList as well as the unit increment for the -
        vertical scroll bar
      */
    int fontsListWidth = fontsList.getPreferredScrollableViewportSize().width;
    int fontsListHeight = fontsList.getPreferredScrollableViewportSize().height;
    int fontsListXPos = displayFontsButtonXPos + displayFontsButtonWidth;
    int fontsListYPos = ( displayFontsButtonYPos + displayFontsButtonHeight ) - fontsListHeight;
    
    getContentPane().add( fontsListScrollPane );
    
    fontsListScrollPane.setBounds(
      fontsListXPos,
      fontsListYPos,
      fontsListWidth,
      fontsListHeight
    );
  }//GEN-LAST:event_displayFontsButtonActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
     */
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(ScreenEditorTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(ScreenEditorTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(ScreenEditorTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(ScreenEditorTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ScreenEditorTesting().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem closeMenuItem;
  private javax.swing.JButton displayFontsButton;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JMenuBar menuBar;
  // End of variables declaration//GEN-END:variables
}
