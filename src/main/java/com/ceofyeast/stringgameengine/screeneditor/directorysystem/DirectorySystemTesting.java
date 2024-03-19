/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor.directorysystem;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.awt.Point;

import java.io.File;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystemAlreadyExistsException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;

import javax.swing.JFrame;

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
  public static final String GAMES_DIRECTORY_PATH = "src/main/java/com/ceofyeast/stringgameengine/screeneditor/games/";
  
  public static final Gson GSON = new Gson();
  
  public static String loadedGameFilePath = null;
  public static JsonObject loadedGame = null;
  public static JsonObject loadedScreen = null;
  
  private static final java.lang.reflect.Type GAME_TYPE = new TypeToken< HashMap<String, Object> >(){}.getType();
  private static final java.lang.reflect.Type SCREEN_TYPE = new TypeToken< HashMap<String, HashMap<String, Object>> >(){}.getType();
  private static final java.lang.reflect.Type META_DATA_TYPE = new TypeToken< HashMap<String, String> >(){}.getType();
  private static final java.lang.reflect.Type SIZING_DATA_TYPE = new TypeToken< HashMap<String, Integer> >(){}.getType();
  private static final java.lang.reflect.Type CELLS_DATA_TYPE = new TypeToken< Map<Point, Map<String, Object>> >(){}.getType();
  
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
   *
   * @throws Exception Potential exceptions aren't caught within the method.
   */
  public static void loadGame(String toLoadName)
    throws Exception 
  {
    
    String path = GAMES_DIRECTORY_PATH + toLoadName + ".json";
    
    loadedGameFilePath = path;  
      
    // Read JSON file content into a String
    String toLoadJsonString = new String(
      Files.readAllBytes(
        Paths.get( path )
      )
    );

    JsonElement toLoadJsonElement = JsonParser.parseString(toLoadJsonString);

    loadedGame = toLoadJsonElement.getAsJsonObject();
  }

  /**
   * Attempts to load the given JSON game file into a JsonObject.
   *
   * @param toLoad The JSON file to load.
   *
   * @throws Exception Potential exceptions aren't caught within the method.
   */
  public static void loadGame( File toLoad )
    throws Exception {
    
    loadedGameFilePath = toLoad.getPath();
    
    // Read JSON file content into a String
    String toLoadJsonString = new String(
      Files.readAllBytes(
        toLoad.toPath()
      )
    );

    JsonElement toLoadJsonElement = JsonParser.parseString(toLoadJsonString);

    loadedGame = toLoadJsonElement.getAsJsonObject();
  }
  
  /**
   * Loads a screen with the given name into the loadedScreen member variable; the screen is grabbed from 
   * loadedGame.
   * 
   * @param toLoadName The name of the screen to load.
   * 
   * @throws Exception Potential exceptions aren't caught within the method.
   */
  public static void loadScreen( String toLoadName )
    throws Exception
  {
    
    if( toLoadName == null || toLoadName.equals( "" ) )
    {
      throw( new IllegalArgumentException( "Supplied Screen Name Is Invalid" ) );
    }
    
      // grabs java object representation of screen
    loadedScreen = loadedGame.get( toLoadName ).getAsJsonObject();
  }

  /**
   * Attempts to create a new game file using the given game name, which is then
   * placed in the directory located at
   * {@link DirectorySystemTesting:GAMES_DIRECTORY_PATH GAMES_DIRECTORY_PATH}.
   *
   * @param name The name of the game, which will be set to the name of the JSON
   * file representing the game.
   *
   * @throws Exception Potential exceptions aren't caught within the method.
   */
  public static void createNewGame(String name)
    throws Exception 
  {
    
    if( name == null || name.equals( "" ) )
    {
      throw( new IllegalArgumentException( "Supplied Game Name Is Invalid" ) );
    }
    
    File gameFile = new File( GAMES_DIRECTORY_PATH + name + ".json" );

    if ( gameFile.createNewFile() ) {
      System.out.println( "Game File Created w/ Name: " + name + ".json" );

      Files.write( gameFile.toPath(), "{}".getBytes( StandardCharsets.UTF_8 ) );
      
    } else {
      throw ( new FileSystemAlreadyExistsException( "Game File w/ Name: " + name + ".json Already Exists" ) );
    }
  }
  
  /**
   * Creates a new, empty screen and appends said screen to loadedGame.
   *   
   * @param name The name of the new screen.
   * @param rowCount The row count of the new screen.
   * @param columnCount The column count of the new screen.
   * 
   * @throws Exception Potential exceptions aren't caught within the method.
   */
  public static void createNewScreen( String name, int rowCount, int columnCount )
    throws Exception
  {
    if( name == null || name.equals( "" ) )
    {
      throw( new IllegalArgumentException("Invalid Name") );
    }
    
    if( rowCount <= 0 || columnCount <= 0 )
    {
      throw( new IllegalArgumentException("Invalid Row Or Col. Count") );
    }
    
    if( Arrays.asList( getScreenNames() ).contains( name ) )
    {
      throw( new IllegalArgumentException("Screen With Name: " + name + " Already Exists In loadedGame") );
    }
    
    HashMap newScreen = new HashMap<String, Object>();
    
      // initializes the meta-data hash map
    HashMap metaDataHashMap = new HashMap<String, String>();
    metaDataHashMap.put( "name", name );
    
      // initializes the sizing hash map
    HashMap sizingHashMap = new HashMap<String, Integer>();
    sizingHashMap.put( "rowCount", rowCount );
    sizingHashMap.put( "columnCount", columnCount );

      // initializes the cells hash map
    HashMap cellsHashMap = new HashMap<Point, HashMap<String, Object>>();
    
    newScreen.put( "metaData", metaDataHashMap );
    newScreen.put( "sizingData", sizingHashMap );
    newScreen.put( "cellsData", cellsHashMap );
    
    JsonElement jsonElement = GSON.toJsonTree( newScreen );
    JsonObject jsonObject = ( JsonObject ) jsonElement;
    
    loadedGame.add( name, jsonObject );
  }
  
  /**
   * Writes the contents of loadedGame to its game file.
   * 
   * @throws Exception Potential exceptions aren't caught within the method.
   */
  public static void writeGame()
    throws Exception
  {
        // writes loadedGame to json String using Gsonbuilder
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson( loadedGame );
    
    FileWriter writer = new FileWriter( 
      loadedGameFilePath
    );

      // performs the overwrite action
    writer.write( json );
    writer.flush();
    writer.close();

    System.out.println( "Write Successful" );
  }
  
  /**
   * Gets all the names of the screens inside loadedGame.
   * 
   * @return A String array containing the names of every screen inside loadedGame.
   */
  public static String[] getScreenNames()
  {
    if( loadedGame == null )
    {
      return null;
    }
    
    Set<String> availableScreenNames = DirectorySystemTesting.loadedGame.keySet();
    
    return availableScreenNames.toArray( 
      new String[availableScreenNames.size()] 
    );
  }
  
  @FunctionalInterface
  public interface JsonObjectParser<T, R> {
    public R apply(T t);
  }
  
  public static JsonObjectParser< Boolean, ? > getMetaData = 
  ( asJsonObject ) -> {
    
    JsonObject jsonObject = loadedScreen.get( "metaData" ).getAsJsonObject();
    
    if( asJsonObject ) { return jsonObject; }
    
    return GSON.fromJson( jsonObject, META_DATA_TYPE );
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
    loadScreen = new javax.swing.JMenuItem();
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
    NewGameDialog.showDialog();
  }//GEN-LAST:event_newGameActionPerformed

  private void newScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newScreenActionPerformed
    NewScreenDialog.showDialog();
  }//GEN-LAST:event_newScreenActionPerformed

  private void loadGameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadGameActionPerformed
    LoadGameDialog.showDialog();
  }//GEN-LAST:event_loadGameActionPerformed

  private void loadScreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadScreenActionPerformed
    LoadScreenDialog.showDialog();
  }//GEN-LAST:event_loadScreenActionPerformed

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
  private javax.swing.JMenuItem loadScreen;
  private javax.swing.JMenuBar menuBar;
  private javax.swing.JMenuItem newGame;
  private javax.swing.JMenuItem newScreen;
  private javax.swing.JMenuItem saveScreen;
  // End of variables declaration//GEN-END:variables
}
