/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import java.awt.GridLayout;
import java.nio.file.FileSystemAlreadyExistsException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Singleton class representing a dialog used to display a screen object creation menu.
 * 
 * <p>The class itself represents a JPanel with various fields, which is then
 *    displayed inside JOptionPane upon calling showDialog.
 *
 * @author Benton Diebold (ceofyeast)
 */
public class NewGameDialog extends JPanel {

  /**
   * The singleton instance of the dialog used in the showDialog method. 
   */
  private static NewGameDialog singletonInstance = null;

  /**
   * Used to set name information for the new game being created.
   */
  private final JTextField gameNameField = new JTextField("");

  /**
   * Constructs a NewGameDialog.
   */
  private NewGameDialog() {
    super(new GridLayout(0, 1));

    add(new JLabel("Game Name:"));
    add(gameNameField);
  }

  /**
   * Shows the NewGameDialog.
   */
  public static void showDialog()
  {
    if( singletonInstance == null )
    {
      singletonInstance = new NewGameDialog();
    }
    
    singletonInstance.handleShowDialog();
    
    singletonInstance = null;
  }
  
  /**
   * Performs the actual work of showing the NewGameDialog, and calls handleShowResult upon the dialog
   * returning.
   */
  private void handleShowDialog() {
    int showResult = JOptionPane.showConfirmDialog(
      null,
      this,
      "New Game Menu",
      JOptionPane.OK_CANCEL_OPTION,
      JOptionPane.PLAIN_MESSAGE
    );

    handleShowResult(showResult);
  }

  /**
   * Handles the cases after the dialog is closed.
   *
   * @param showResult The way the dialog was closed, is a JOptionPane option
   * constant.
   */
  private void handleShowResult(int showResult) {
    if ( showResult == JOptionPane.OK_OPTION ) 
    {
      String gameName = gameNameField.getText();

      try {
        DirectorySystemTesting.createNewGame(gameName);
      }
      catch( FileSystemAlreadyExistsException | IllegalArgumentException e) {
        e.printStackTrace();
          
        JOptionPane.showMessageDialog(
          this,
          e.getMessage()
        );

        handleShowDialog();
      }
      catch( Exception e ) {
        e.printStackTrace();
          
        JOptionPane.showMessageDialog(
          this,
          e.getMessage(),
          "Error", JOptionPane.ERROR_MESSAGE
        );

        handleShowDialog();
      }
    }
  }
}
