/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * Class representing a dialog used to display a screen object loading menu.
 * 
 * <p>The class itself represents a JPanel with various fields, which is then
 *    displayed inside JOptionPane upon calling showDialog.
 *
 * @author Benton Diebold (ceofyeast)
 */
public class LoadScreenDialog extends JPanel {
  
  /**
   * The parent of the dialog, properly initialized by the constructor.
   */
  private Container parent = null;
  
  /**
   * Contains the names of the screens in the loaded game in button form.
   */
  private JList availableScreens = new JList();
  
  /**
   * Constructs a NewGameDialog.
   * 
   * @param parent The parent of the dialog.
   * 
   * @throws IllegalArgumentException If the supplied parent is null.
   * @throws NullPointerException If no game has been loaded.
   */
  public LoadScreenDialog(Container parent) 
    throws IllegalArgumentException, NullPointerException  
  {
    
    super(new GridLayout(0, 1));

    if (parent == null) {
      throw (new IllegalArgumentException());
    }

    this.parent = parent;
   
    availableScreens = new JList<>( DirectorySystemTesting.getScreenNames() );
    availableScreens.setVisibleRowCount( 10 );
    availableScreens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
      // constructs scroll pane, used to scroll vertically through fontsList
    javax.swing.JScrollPane availableScreensScrollPane = new javax.swing.JScrollPane( availableScreens );
    
      // removes horizontal scroll bar from fontsListScrollPane
    availableScreensScrollPane.setHorizontalScrollBarPolicy( 
      javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER 
    );
    
    add(availableScreensScrollPane);
  }
  
  /**
   * Shows the NewScreenDialog, calling HandleShowResult upon the dialog
   * returning.
   */
  public void showDialog() {
    int showResult = JOptionPane.showConfirmDialog(
      parent,
      this,
      "Load Screen Menu",
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
  private void handleShowResult(int showResult)
  {
    if (showResult == JOptionPane.OK_OPTION) 
    {
      String selectedScreenName = (String) availableScreens.getSelectedValue();

      try {
        DirectorySystemTesting.loadScreen( selectedScreenName );
      } 
      catch( IllegalArgumentException e ) {
        e.printStackTrace();
        
        JOptionPane.showMessageDialog(
          this,
          "No screen was selected."
        );
  
        showDialog();
      }
      catch( Exception e ) {
        e.printStackTrace();

        JOptionPane.showMessageDialog(
          this,
          e.getMessage(),
          "Error", JOptionPane.ERROR_MESSAGE
        );

        showDialog();
      }
    }
  }
}
