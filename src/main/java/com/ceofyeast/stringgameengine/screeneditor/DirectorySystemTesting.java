/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

import java.io.File;  
import java.io.IOException;

import java.awt.GridLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * Temporary test class representing the file system structure that will be put in place for the project.
 * 
 * @author Benton Diebold (ceofyeast)
 */
public class DirectorySystemTesting extends JFrame {

  /**
   * Path to the directory all game files are stored in.
   */
  private final String GAMES_DIRECTORY_PATH = "src/main/java/com/ceofyeast/stringgameengine/screeneditor/games/";
  
  /**
   * Creates new form ScreenEditorJframeTesting.
   */
  public DirectorySystemTesting() {
    initComponents();
  }
  
  /**
   * Class representing a dialog used to display a screen object creation menu. The class itself represents
   * a JPanel with various fields, which is then displayed inside JOptionPane upon calling showDialog.
   * 
   * @author Benton Diebold (ceofyeast)
   */
  private class NewScreenDialog extends JPanel {
    /**
     * The parent of the dialog, properly initialized by the constructor.
     */
    private Container parent = null;
    
    /**
     * Used to set the name of the new screen being created.
     */
    private JTextField screenNameField = new JTextField( "" );
    
    /**
     * Used to set the column count for the new screen being created.
     */
    private JSpinner columnCountField = new JSpinner( new SpinnerNumberModel( 1, 1, 1000, 1 ) ); 
    
    /**
     * Used to set the row count for the new screen being created.
     */
    private JSpinner rowCountField = new JSpinner( new SpinnerNumberModel( 1, 1, 1000, 1 ) );
    
    /**
     * Constructs a NewScreenDialog.
     * 
     * @param parent The parent of the dialog.
     * 
     * @Throws ArgumentException If parent is null.
     */
    public NewScreenDialog( Container parent )
    {
      super( new GridLayout( 0, 1 ) );
      
      if( parent == null )
      {
        throw( new IllegalArgumentException() );
      }
      
      this.parent = parent;
      
      add( new JLabel( "Screen Name:" ) );
      add( screenNameField );
      
      add( new JLabel( "Column Count:" ) );
      add( columnCountField );
      
      add( new JLabel( "Row Count:" ) );
      add( rowCountField );
    }
    
    /**
     * Shows the NewScreenDialog, calling HandleShowResult upon the dialog returning.
     */
    public void showDialog()
    { 
      int showResult = JOptionPane.showConfirmDialog( 
        parent, 
        this, 
        "New Game Menu",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
      );
      
      handleShowResult( showResult );
    }
    
    /**
     * Handles the cases after the dialog is closed. 
     * 
     * @param showResult The way the dialog was closed, is a JOptionPane option constant.
     */
    private void handleShowResult( int showResult )
    {
      if( showResult == JOptionPane.OK_OPTION ) 
      {
        String screenName = screenNameField.getText();
        int columnCount = (Integer) columnCountField.getValue();
        int rowCount = (Integer) rowCountField.getValue();
        
        screenNameField.setText("");
        columnCountField.setValue(0);
        rowCountField.setValue(0);
        
        if( screenName == null || screenName.equals("") )
        {
          JOptionPane.showMessageDialog(
            parent, 
            "Supplied Name Is Invalid"
          );
          
          showDialog();
        }
        
        else
        {
          
        }
      }
    }
  }
  
  /**
   * Class representing a dialog used to display a screen object creation menu. The class itself represents
   * a JPanel with various fields, which is then displayed inside JOptionPane upon calling showDialog.
   * 
   * @author Benton Diebold (ceofyeast)
   */
  private class NewGameDialog extends JPanel {
    /**
     * The parent of the dialog, properly initialized by the constructor.
     */
    private Container parent = null;
    
    /**
     * Used to set name information for the new game being created.
     */
    private JTextField gameNameField = new JTextField( "" );
    
    /**
     * Constructs a NewGameDialog.
     * 
     * @param parent The parent of the dialog.
     * 
     * @Throws ArgumentException If parent is null.
     */
    public NewGameDialog( Container parent )
    {
      super( new GridLayout( 0, 1 ) );
      
      if( parent == null )
      {
        throw( new IllegalArgumentException() );
      }
      
      this.parent = parent;
      
      add( new JLabel( "Game Name:" ) );
      add( gameNameField );
    }
    
    /**
     * Shows the NewScreenDialog, calling HandleShowResult upon the dialog returning.
     */
    public void showDialog()
    { 
      int showResult = JOptionPane.showConfirmDialog( 
        parent, 
        this, 
        "New Game Menu",
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
      );
      
      handleShowResult( showResult );
    }
    
    /**
     * Handles the cases after the dialog is closed. 
     * 
     * @param showResult The way the dialog was closed, is a JOptionPane option constant.
     */
    private void handleShowResult( int showResult )
    {
      if( showResult == JOptionPane.OK_OPTION ) 
      {
        String gameName = gameNameField.getText();
        
        gameNameField.setText("");
        
        if( gameName == null || gameName.equals("") )
        {
          JOptionPane.showMessageDialog(
            parent, 
            "Supplied Name Is Invalid"
          );
          
          showDialog();
        }
        
        else
        {
          boolean creationSuccess = createNewGame( gameName );
          
          if( creationSuccess == false )
          {
            showDialog();
          }
        }
      }
    }
    
    /**
     * Attempts to create a new game file using the given game name, which is then placed in the directory
     * located at {@link DirectorySystemTesting:GAMES_DIRECTORY_PATH GAMES_DIRECTORY_PATH}.
     * 
     * @param name The name of the game, which will be set to the name of the JSON file representing the game.
     * 
     * @return A boolean, with true representing a successful new game creation and false representing a 
     *         failed one. 
     */
    private boolean createNewGame( String name )
    {
      try 
      {
        File gameFile = new File( GAMES_DIRECTORY_PATH + name + ".json" );
        
        if ( gameFile.createNewFile() ) 
        {
          System.out.println( "Game File Created w/ Name: " + name + ".json" );

          return true;
        } 
        else 
        {
          JOptionPane.showMessageDialog(
            parent, 
            "Game File w/ Supplied Name: " + name + ".json Already Exists"
          );

          return false;
        }
      } 
      
      catch (IOException e) 
      {
        JOptionPane.showMessageDialog(
          parent,
          e.getMessage(), 
          "Error", JOptionPane.ERROR_MESSAGE
        );

        return false;
      }
    }
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    menuBar = new javax.swing.JMenuBar();
    fileMenu = new javax.swing.JMenu();
    loadGame = new javax.swing.JMenu();
    newGame = new javax.swing.JMenuItem();
    deleteGame = new javax.swing.JMenuItem();
    gameAndScreenSeperator = new javax.swing.JPopupMenu.Separator();
    loadScreen = new javax.swing.JMenu();
    newScreen = new javax.swing.JMenuItem();
    saveScreen = new javax.swing.JMenuItem();
    deleteScreen = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    fileMenu.setText("File");

    loadGame.setText("Load Game");
    fileMenu.add(loadGame);

    newGame.setText("New Game");
    newGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newGameActionPerformed(evt);
      }
    });
    fileMenu.add(newGame);

    deleteGame.setText("Delete Game");
    fileMenu.add(deleteGame);
    fileMenu.add(gameAndScreenSeperator);

    loadScreen.setText("Load Screen");
    fileMenu.add(loadScreen);

    newScreen.setText("New Screen");
    newScreen.setToolTipText("NewScreen");
    newScreen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newScreenActionPerformed(evt);
      }
    });
    fileMenu.add(newScreen);

    saveScreen.setText("Save Screen");
    fileMenu.add(saveScreen);

    deleteScreen.setText("Delete Screen");
    fileMenu.add(deleteScreen);

    menuBar.add(fileMenu);

    setJMenuBar(menuBar);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 372, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 273, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents
  
  /**
   * Shows a {@link NewGameDialog NewGameDialog} upon clicking the New Game menu item.   
   * 
   * @param evt Contains information about the event.
   */
  private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
    NewGameDialog dialog = new NewGameDialog( this );
    
    dialog.showDialog();
  }//GEN-LAST:event_newGameActionPerformed

  private void newScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newScreenActionPerformed
    NewScreenDialog dialog = new NewScreenDialog( this );
    
    dialog.showDialog();
  }//GEN-LAST:event_newScreenActionPerformed

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
      java.util.logging.Logger.getLogger(DirectorySystemTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(DirectorySystemTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(DirectorySystemTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(DirectorySystemTesting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new DirectorySystemTesting().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JMenuItem deleteGame;
  private javax.swing.JMenuItem deleteScreen;
  private javax.swing.JMenu fileMenu;
  private javax.swing.JPopupMenu.Separator gameAndScreenSeperator;
  private javax.swing.JMenu loadGame;
  private javax.swing.JMenu loadScreen;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem newGame;
  private javax.swing.JMenuItem newScreen;
  private javax.swing.JMenuItem saveScreen;
  // End of variables declaration//GEN-END:variables
}
