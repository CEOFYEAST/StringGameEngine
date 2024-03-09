/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;
import java.nio.file.FileSystemAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.UIManager;

/**
 * Temporary test class representing the file system structure that will be put
 * in place for the project.
 *
 * @author Benton Diebold (ceofyeast)
 */
public class DirectorySystemTesting extends JFrame {
  /**
   * Path to the directory all game files are stored in.
   */
  private static final String GAMES_DIRECTORY_PATH = "src/main/java/com/ceofyeast/stringgameengine/screeneditor/games/";
  
  private static final Gson GSON = new Gson();
  
  public static java.lang.reflect.Type gameType = new TypeToken< HashMap<String, Object> >(){}.getType();
  
  public static java.lang.reflect.Type screenType = new TypeToken< HashMap<String, HashMap<String, Object>> >(){}.getType();
  
  public static java.lang.reflect.Type metaDataType = new TypeToken< HashMap<String, String> >(){}.getType();
  
  /**
   * Creates new form ScreenEditorJframeTesting.
   */
  public DirectorySystemTesting() {
    initComponents();
  }

  /**
   * Attempts to load the JSON game file associated with the given game name
   * into a JsonObject.
   *
   * @param toLoadName The name of the JSON file to load.
   * @return The JsonObject representation of the file.
   *
   * @throws InvalidPathException If an error occurs when building a path using
   * toLoadName and GAMES_DIRECTORY_PATH.
   * @throws IOException If there's an error fetching/reading the file
   * associated with toLoadName.
   * @throws JsonParseException If the file associated with toLoadName cannot be
   * parsed into a JsonElement.
   * @throws IllegalStateException If the JsonElement representation of the file
   * associated with toLoadNamecannot be parsed into a JsonObject.
   */
  private static JsonObject loadGame(String toLoadName)
    throws IOException, IllegalStateException, JsonParseException, InvalidPathException {
    // Read JSON file content into a String
    String toLoadJsonString = new String(
      Files.readAllBytes(
        Paths.get(GAMES_DIRECTORY_PATH + toLoadName + ".json")
      )
    );

    JsonElement toLoadJsonElement = JsonParser.parseString(toLoadJsonString);

    return toLoadJsonElement.getAsJsonObject();
  }

  /**
   * Attempts to load the given JSON game file into a JsonObject.
   *
   * @param toLoadName The name of the JSON file to load.
   * @return The JsonObject representation of the file.
   *
   * @throws InvalidPathException If an error occurs when building a path using
   * toLoad.
   * @throws IOException If there's an error accessing/reading toLoad.
   * @throws JsonParseException If toLoad cannot be parsed into a JsonElement.
   * @throws IllegalStateException If toLoad's JsonElement representation cannot
   * be parsed into a JsonObject.
   */
  public static JsonObject loadGame(File toLoad)
    throws InvalidPathException, IOException, JsonParseException, IllegalStateException {
    // Read JSON file content into a String
    String toLoadJsonString = new String(
      Files.readAllBytes(
        toLoad.toPath()
      )
    );

    JsonElement toLoadJsonElement = JsonParser.parseString(toLoadJsonString);

    return toLoadJsonElement.getAsJsonObject();
  }
  
  public static JsonObject loadScreen(HashMap<String, Object> game, String toLoadName)
  {
    
  }

  /**
   * Attempts to create a new game file using the given game name, which is then
   * placed in the directory located at
   * {@link DirectorySystemTesting:GAMES_DIRECTORY_PATH GAMES_DIRECTORY_PATH}.
   *
   * @param name The name of the game, which will be set to the name of the JSON
   * file representing the game.
   * @return A boolean, with true representing a successful new game creation
   * and false representing a failed one.
   *
   * @throws IOException If there's an error during file creation.
   * @throws FileSystemAlreadyExistsException If a game file with the given name
   * already exists inside the game files directory.
   */
  public static boolean createNewGame(String name)
    throws IOException, FileSystemAlreadyExistsException {
    File gameFile = new File(GAMES_DIRECTORY_PATH + name + ".json");

    if (gameFile.createNewFile()) {
      System.out.println("Game File Created w/ Name: " + name + ".json");

      return true;
    } else {
      throw (new FileSystemAlreadyExistsException("Game File w/ Name: " + name + ".json Already Exists"));
    }
  }
  
  /**
   * Creates a new screen hash map, itself containing two hash maps that represent the screen's sizing
   * and cell data.
   * 
   * @return The new screen hash map.
   */
  public static Map createNewScreen()
  {
    HashMap toReturn = new HashMap<String, Object>();
    
      // initializes the meta-data hash map
    HashMap metaDataHashMap = new HashMap<String, String>();
    metaDataHashMap.put("name", null);
    
      // initializes the sizing hash map
    HashMap sizingHashMap = new HashMap<String, Integer>();
    sizingHashMap.put("rowCount", null);
    sizingHashMap.put("colCount", null);

      // initializes the cells hash map
    HashMap cellsHashMap = new HashMap<Point, HashMap<String, Object>>();
    
    toReturn.put( "metaData", metaDataHashMap );
    toReturn.put( "sizingData", sizingHashMap );
    toReturn.put( "cellsData", cellsHashMap );
    
    return toReturn;
  }
  
  @FunctionalInterface
  public interface JsonObjectParser<T, U, V> {
    public V apply(T t, U u) throws IllegalStateException, NoSuchElementException;
  }
  
  public static JsonObjectParser< JsonObject, Boolean, ? > getSizingData = 
  ( container, asJsonObject ) -> {
      
      // grabs java object representation of screen 
    JsonObject sizingDataJsonObject = container.get( "sizingData" ).getAsJsonObject();
    
      // simply returns JsonObject representation if asJsonObject is true
    if( asJsonObject ) { return sizingDataJsonObject; }
    
      // returns meta data as java object, type casted, after converting it from a Json Object
    return GSON.fromJson( sizingDataJsonObject, sizingDataType );
  };
  
  public static JsonObjectParser< JsonObject, Boolean, ? > getMetaData = 
  ( container, asJsonObject ) -> {
      
      // grabs java object representation of metaData 
    JsonObject metaDataJsonObject = container.get( "metaData" ).getAsJsonObject();
    
      // simply returns JsonObject representation if asJsonObject is true
    if( asJsonObject ) { return metaDataJsonObject; }
    
      // returns meta data as java object, type casted, after converting it from a Json Object
    return GSON.fromJson( metaDataJsonObject, metaDataType );
  };
  
  public static JsonObjectParser< JsonObject, Boolean, ? > getCellsData = 
  ( container, asJsonObject ) -> {
      
      // grabs java object representation of screen 
    JsonObject cellsDataJsonObject = container.get( "cellsData" ).getAsJsonObject();
    
      // simply returns JsonObject representation if asJsonObject is true
    if( asJsonObject ) { return cellsDataJsonObject; }
    
      // returns meta data as java object, type casted, after converting it from a Json Object
    return GSON.fromJson( cellsDataJsonObject, cellsDataType );
  };

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
    newGame = new javax.swing.JMenuItem();
    loadGame = new javax.swing.JMenuItem();
    deleteGame = new javax.swing.JMenuItem();
    gameAndScreenSeperator = new javax.swing.JPopupMenu.Separator();
    loadScreen = new javax.swing.JMenu();
    newScreen = new javax.swing.JMenuItem();
    saveScreen = new javax.swing.JMenuItem();
    deleteScreen = new javax.swing.JMenuItem();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    fileMenu.setText("File");

    newGame.setText("New Game");
    newGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        newGameActionPerformed(evt);
      }
    });
    fileMenu.add(newGame);

    loadGame.setText("Load Game");
    loadGame.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadGameActionPerformed(evt);
      }
    });
    fileMenu.add(loadGame);

    deleteGame.setText("Delete Game");
    fileMenu.add(deleteGame);
    fileMenu.add(gameAndScreenSeperator);

    loadScreen.setText("Load Screen");
    loadScreen.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        loadScreenActionPerformed(evt);
      }
    });
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
      .addGap(0, 610, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGap(0, 359, Short.MAX_VALUE)
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

  /**
   * Shows a {@link NewGameDialog NewGameDialog} upon clicking the New Game menu
   * item.
   *
   * @param evt Contains information about the event.
   */
  private void newGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGameActionPerformed
    NewGameDialog dialog = new NewGameDialog(this);

    dialog.showDialog();
  }//GEN-LAST:event_newGameActionPerformed

  private void newScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newScreenActionPerformed
    NewScreenDialog dialog = new NewScreenDialog(this);

    dialog.showDialog();
  }//GEN-LAST:event_newScreenActionPerformed

  private void loadScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadScreenActionPerformed
    // TODO add your handling code here:
  }//GEN-LAST:event_loadScreenActionPerformed

  private void loadGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameActionPerformed
    UIManager.put("FileChooser.readOnly", Boolean.TRUE);
    JFileChooser chooser = new JFileChooser(GAMES_DIRECTORY_PATH);

    FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON", "json");
    chooser.setFileFilter(filter);

    chooser.setAcceptAllFileFilterUsed(false);

    int returnVal = chooser.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION) {
      File gameFile = chooser.getSelectedFile();

      try {
        loadGame(gameFile);
      } catch (Exception e) {
        JOptionPane.showMessageDialog(
          this,
          e.getMessage(),
          "Error", JOptionPane.ERROR_MESSAGE
        );
      }
    }
  }//GEN-LAST:event_loadGameActionPerformed

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
  private javax.swing.JMenuItem loadGame;
  private javax.swing.JMenu loadScreen;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem newGame;
  private javax.swing.JMenuItem newScreen;
  private javax.swing.JMenuItem saveScreen;
  // End of variables declaration//GEN-END:variables
}
