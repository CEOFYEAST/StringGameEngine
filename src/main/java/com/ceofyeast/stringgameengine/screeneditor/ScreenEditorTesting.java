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
   * Number of columns of cells in the screen
   */
  static int columnCount = 60;
  
  /**
   * Number of rows of cells in the screen
   */
  static int rowCount = 18;
  
  /**
   * Scale to apply to base ratio of 6x13 pixels, also to font-size
   * Ex: Scale of two would result in cells with width 12 pixels and height 26 pixels (2 * 6, 13 )
   */
  static int faceSize = 20;
  
  /**
   * Used to control the size of the border between cells in the cell matrix
   */
  static int cellMatrixBorderSize = 0;
  
  /**
   * Grid JPanel used to represent a Screen object graphically
   */
  javax.swing.JPanel cellsMatrix;
  
  /**
   * Used to enable full-screen mode
   */
  static GraphicsDevice device = GraphicsEnvironment
        .getLocalGraphicsEnvironment().getScreenDevices()[0];
  
  /**
   * Creates new form ScreenEditorTesting
   */
  public ScreenEditorTesting() {
    /**
    String fonts[] = 
      GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
    for (int i = 0; i < fonts.length; i++) {
      System.out.println(fonts[i]);
    }
    */
    
    try {
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File(
        "src/main/java/com/ceofyeast/stringgameengine/screeneditor/DejaVuSansMono.ttf"
      )));
      
      System.out.println( "Success" );
      
    } catch (Exception e) {
        e.printStackTrace();
    } 

    
    initComponents();
    
    initializeCellsMatrix();
    
    device.setFullScreenWindow( this );
    
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
  }
  
  public void initializeCellsMatrix() {
      // Code block initializes an outer panel to contain the cellsMatrix panel. The Flow layout manager allows for the inner panel to be sized by the size of its contents, despite being a grid.
    javax.swing.JPanel cellsMatrixContainer = new javax.swing.JPanel();
    cellsMatrixContainer.setLayout( new java.awt.FlowLayout( java.awt.FlowLayout.CENTER, 0, cellMatrixBorderSize ) );
    
    cellsMatrix = new javax.swing.JPanel();
    
    cellsMatrix.setLayout( new java.awt.GridLayout( rowCount, columnCount, cellMatrixBorderSize, cellMatrixBorderSize ) );
    
    for( int i = 1; i <= rowCount * columnCount; i++ )
    { 
      try{
        char[] toFillWith = new char[]{ ' ','c','e','o','f','y','e','a','s','t','@','L','A','P','T','O','P'};
        cellsMatrix.add( initializeCell( toFillWith[i] ) );
      } catch( Exception  e ){
        cellsMatrix.add( initializeCell( ' ' ) );
      }
    }
    
    cellsMatrixContainer.add( cellsMatrix );
 
    getContentPane().add( cellsMatrixContainer );
  }
  
  public javax.swing.JTextField initializeCell( char cellText ){
    javax.swing.JTextField toReturn = new javax.swing.JTextField();
      
    toReturn.setOpaque( true );

    toReturn.setBorder( new EmptyBorder( 0,0,0,0 ) );

    toReturn.setHorizontalAlignment( javax.swing.JTextField.CENTER );

    Font cellsFont = initializeFont();
    
    toReturn.setFont( cellsFont );

    toReturn.setText( String.valueOf( cellText ) );

    toReturn.setMargin( new java.awt.Insets( 0,0,0,0 ) );

    return toReturn;
  }
  
  public Font initializeFont(){
    Font toReturn = new Font( "DejaVu Sans Mono", Font.PLAIN, faceSize ); // Initializes the font
    
    //Map< AttributedCharacterIterator.Attribute, String > toReturnAttributes = new HashMap<>(); // Contains extra attributes to add to font
    
    //toReturnAttributes.put( TextAttribute.FAMILY, "Monospaced" ); // Adds monospaced attribute to attributes
    
    //toReturnAttributes.put( TextAttribute.FAMILY, "SansSerif" );
    
    //toReturnAttributes.put( TextAttribute.FAMILY, "sans_serif" ); // Adds sans serif attribute to attributes
    
    //toReturn = toReturn.deriveFont( toReturnAttributes ); // Updates cellsFont with attributes in attributes 
    
    System.out.println(toReturn.getFamily() + ", " + toReturn.getName() + ", " + toReturn.getFontName());
    
    return toReturn;
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
    jTextField1 = new javax.swing.JTextField();
    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    closeMenuItem = new javax.swing.JMenuItem();

    jTextArea1.setColumns(20);
    jTextArea1.setRows(5);
    jScrollPane1.setViewportView(jTextArea1);

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    getContentPane().setLayout(null);

    jTextField1.setText("jTextField1");
    getContentPane().add(jTextField1);
    jTextField1.setBounds(340, 80, 70, 22);

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
  private javax.swing.JMenu fileMenu;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea jTextArea1;
  private javax.swing.JTextField jTextField1;
  private javax.swing.JMenuBar menuBar;
  // End of variables declaration//GEN-END:variables
}
