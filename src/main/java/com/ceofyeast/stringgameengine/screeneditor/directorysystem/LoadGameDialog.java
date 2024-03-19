/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Singleton class representing a dialog used to display a game file loading menu.
 * 
 * <p>The dialog consists of a JFileChooser.
 *
 * @author Benton Diebold (ceofyeast)
 */
public class LoadGameDialog {
  /**
   * The singleton instance of the dialog used in the showDialog method. 
   */
  private static LoadGameDialog singletonInstance = null;
  
  /**
   * The file chooser used to select the proper game file to load, initialized by the constructor.
   */
  JFileChooser chooser;
  
  /**
   * Constructs a LoadGameDialog.
   */
  private LoadGameDialog()
  {
    UIManager.put( "FileChooser.readOnly", Boolean.TRUE );
    chooser = new JFileChooser(DirectorySystemTesting.GAMES_DIRECTORY_PATH);

    FileNameExtensionFilter filter = new FileNameExtensionFilter( "JSON", "json" );
    chooser.setFileFilter( filter );

    chooser.setAcceptAllFileFilterUsed( false );
  }
  
  /**
   * Shows the LoadGameDialogue.
   */
  public static void showDialog()
  {
    if( singletonInstance == null )
    {
      singletonInstance = new LoadGameDialog();
    }
    
    singletonInstance.handleShowDialog();
    
    singletonInstance = null;
  }
  
  /**
   * Performs the actual work of showing the LoadGameDialogue, and calls handleShowResult upon the dialog
   * returning.
   */
  private void handleShowDialog()
  {
    handleShowResult( chooser.showOpenDialog( null ) );
  }
  
  /**
   * Handles the result of showing the dialogue.
   * 
   * @param showResult Provides information on the way the dialogue was closed. 
   */
  private void handleShowResult( int showResult )
  {
    if( showResult == JFileChooser.APPROVE_OPTION ) 
    {
      File gameFile = chooser.getSelectedFile();

      try {
        DirectorySystemTesting.loadGame(gameFile);
      } 
      catch (Exception e) {
        JOptionPane.showMessageDialog(
          null,
          e.getMessage(),
          "Error", JOptionPane.ERROR_MESSAGE
        );
        
        handleShowDialog();
      }
    }
  }
}
