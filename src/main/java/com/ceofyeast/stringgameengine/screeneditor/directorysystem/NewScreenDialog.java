/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

/**
 * Class representing a dialog used to display a screen object creation menu.
 * 
 * <p>The class itself represents a JPanel with various fields, which is then
 *    displayed inside JOptionPane upon calling showDialog.
 *
 * @author Benton Diebold (ceofyeast)
 */
public class NewScreenDialog extends JPanel {

  /**
   * The parent of the dialog, properly initialized by the constructor.
   */
  private Container parent = null;

  /**
   * Used to set the name of the new screen being created.
   */
  private JTextField screenNameField = new JTextField("");

  /**
   * Used to set the column count for the new screen being created.
   */
  private JSpinner columnCountField = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

  /**
   * Used to set the row count for the new screen being created.
   */
  private JSpinner rowCountField = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));

  /**
   * Constructs a NewScreenDialog.
   *
   * @param parent The parent of the dialog.
   *
   * @Throws ArgumentException If parent is null.
   */
  public NewScreenDialog(Container parent) {
    super(new GridLayout(0, 1));

    if (parent == null) {
      throw (new IllegalArgumentException());
    }

    this.parent = parent;

    add(new JLabel("Screen Name:"));
    add(screenNameField);

    add(new JLabel("Column Count:"));
    add(columnCountField);

    add(new JLabel("Row Count:"));
    add(rowCountField);
  }

  /**
   * Shows the NewScreenDialog, calling HandleShowResult upon the dialog
   * returning.
   */
  public void showDialog() {
    int showResult = JOptionPane.showConfirmDialog(
      parent,
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

        showDialog();
      }
      catch( Exception e ) {
        JOptionPane.showMessageDialog(
          parent,
          e.getMessage(),
          "Error", JOptionPane.ERROR_MESSAGE
        );

        showDialog();
      }
    }
  }
}
