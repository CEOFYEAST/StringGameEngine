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
import javax.swing.JTextField;

/**
 * Class representing a dialog used to display a screen object creation menu.
 * The class itself represents a JPanel with various fields, which is then
 * displayed inside JOptionPane upon calling showDialog.
 *
 * @author Benton Diebold (ceofyeast)
 */
public class NewGameDialog extends JPanel {

  /**
   * The parent of the dialog, properly initialized by the constructor.
   */
  private Container parent = null;

  /**
   * Used to set name information for the new game being created.
   */
  private JTextField gameNameField = new JTextField("");

  /**
   * Constructs a NewGameDialog.
   *
   * @param parent The parent of the dialog.
   *
   * @Throws ArgumentException If parent is null.
   */
  public NewGameDialog(Container parent) {
    super(new GridLayout(0, 1));

    if (parent == null) {
      throw (new IllegalArgumentException());
    }

    this.parent = parent;

    add(new JLabel("Game Name:"));
    add(gameNameField);
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
    if (showResult == JOptionPane.OK_OPTION) {
      String gameName = gameNameField.getText();

      gameNameField.setText("");

      if (gameName == null || gameName.equals("")) {
        JOptionPane.showMessageDialog(
          parent,
          "Supplied Name Is Invalid"
        );

        showDialog();
      } else {
        boolean creationSuccess = false;

        try {
          creationSuccess = DirectorySystemTesting.createNewGame(gameName);
        } catch (Exception e) {
          e.printStackTrace();
          
          JOptionPane.showMessageDialog(
            this,
            e.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE
          );
        }

        if (creationSuccess == false) {
          showDialog();
        }
      }
    }
  }
}
