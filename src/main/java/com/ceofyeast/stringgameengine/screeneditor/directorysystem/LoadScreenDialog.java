/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import java.util.Set;
import javax.swing.JOptionPane;

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
   * Displays the name of the screen currently selected to be loaded. 
   */
  private JTextField screenSelected = new JTextField("");
  
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
   
    String[] availableScreenNames = (String[]) DirectorySystemTesting.loadedGame.keySet().toArray();
    availableScreens = new JList<>( availableScreenNames );
    availableScreens.setVisibleRowCount( 10 );
    
      // constructs scroll pane, used to scroll vertically through fontsList
    javax.swing.JScrollPane availableScreensScrollPane = new javax.swing.JScrollPane( availableScreens );
    
      // removes horizontal scroll bar from fontsListScrollPane
    availableScreensScrollPane.setHorizontalScrollBarPolicy( 
      javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER 
    );
    
    
    add(new JLabel("Available Screens"));
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

    //handleShowResult(showResult);
  }
}
