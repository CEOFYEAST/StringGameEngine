/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Singleton class representing a dialog used to display a screen object creation menu.
 * 
 * <p>The class itself represents a JPanel with various fields, which is then
 *    displayed inside JOptionPane upon calling showDialog.
 *
 * @author Benton Diebold (ceofyeast)
 */
public class NewScreenDialog extends JPanel {

  /**
   * The singleton instance of the dialog used in the showDialog method. 
   */
  private static NewScreenDialog singletonInstance = null;

  /**
   * Used to set the name of the new screen being created.
   */
  private final JTextField screenNameField = new JTextField("");

  /**
   * Used to set the column count for the new screen being created.
   */
  private final JSpinner columnCountField = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

  /**
   * Used to set the row count for the new screen being created.
   */
  private final JSpinner rowCountField = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

  /**
   * Constructs a NewScreenDialog.
   */
  private NewScreenDialog() {
    super(new GridLayout(0, 1));

    add(new JLabel("Screen Name:"));
    add(screenNameField);

    add(new JLabel("Column Count:"));
    add(columnCountField);

    add(new JLabel("Row Count:"));
    add(rowCountField);
  }

  public static void showDialog()
  {
    if( singletonInstance == null )
    {
      singletonInstance = new NewScreenDialog();
    }
    
    singletonInstance.handleShowDialog();
    
    singletonInstance = null;
  }
  
  /**
   * Shows the NewScreenDialog, calling HandleShowResult upon the dialog
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
    if (showResult == JOptionPane.OK_OPTION) 
    {
      String screenName = screenNameField.getText();
      int rowCount = (Integer) rowCountField.getValue();
      int columnCount = (Integer) columnCountField.getValue();
      
      try {
        DirectorySystemTesting.createNewScreen( screenName, rowCount, columnCount );
      }
      catch( IllegalArgumentException e ) {
        e.printStackTrace();
          
        JOptionPane.showMessageDialog(
          this,
          e.getMessage()
        );

        handleShowDialog();
      }
      catch( Exception e ) {
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
