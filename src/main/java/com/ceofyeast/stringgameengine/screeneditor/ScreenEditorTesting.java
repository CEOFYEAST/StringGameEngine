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
    enableWindowedFullscreen();
    
    initComponents();
    
    CellsMatrix cellsMatrix = new CellsMatrix( 10, 10 );
    
    cellsMatrix.addToContentPane( ( javax.swing.JPanel ) getContentPane() );
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
    // potentially useful method of constructing a font 
  Map< AttributedCharacterIterator.Attribute, String > toReturnAttributes = new HashMap<>(); // Contains extra attributes to add to font
  toReturnAttributes.put( TextAttribute.FAMILY, "Monospaced" ); // Adds monospaced attribute to attributes
  toReturnAttributes.put( TextAttribute.FAMILY, "SansSerif" );
  toReturnAttributes.put( TextAttribute.FAMILY, "sans_serif" ); // Adds sans serif attribute to attributes
  toReturn = toReturn.deriveFont( toReturnAttributes ); // Updates cellsFont with attributes in attributes 
  
  */

  public void enableWindowedFullscreen()
  { 
    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    
    this.setMinimumSize( screenSize );
    
    this.getContentPane().setMinimumSize( screenSize );
  }
  
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
    catch( Exception e ){ e.printStackTrace(); }
  }
  
  /**
   * Placeholder method to save code for later.
   */
  public void displayAvailableFonts()
  {
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
