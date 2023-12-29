/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

//<editor-fold defaultstate="collapsed" desc="Imports">

package com.ceofyeast.stringgameengine.screeneditor;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.*;

import java.lang.reflect.Type;

import java.awt.Point;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

//</editor-fold>

/**
 *
 * @author bento
 */
public class HashMapJsonTesting {
  
  //<editor-fold defaultstate="collapsed" desc="member vars">
  
  /**
   * relative file path to file containing screens Json Object
   */
  public static String screensFilePath = "src/main/java/learn/hashmapjsontesting/screens.json";

  /**
   * Json Object representing a screen and all its data; is the screen currently loaded into the program
   * A screen contains three categories of information: meta, sizing, cells
   * Reference screen type for more information
   */
  public static JsonObject screenJsonObject;
 
  /**
   * represents the json object of screens. screens is pulled from this
   * screens contains all the screens in a given json file
   * reference screens type for more information
   */
  public static JsonObject screensJsonObject;

  /**
   * scanner is initialized as a member var so it can be used throughout the class
   */
  public static Scanner s = new Scanner( System.in );
  
  /**
   * gson is initialized as a member var so it can be used throughout the class
   * used to convert Json Objects to their Object counterparts (mainly hash maps)
   */
  public static Gson gson = new Gson();
  
  /**
   * Contains type of screen hash map, used to convert screenJsonObject to a hash map
   * String key represents the name of a category of information pertaining to the screen
   * Inside Object value represents the actual information referenced by the key. This information is stored in a dictionary
   */
  public static Type screenType = new TypeToken< HashMap<String, Object> >(){}.getType();
  
  /**
   * Contains type of screens hash map, used to convert screensJsonObject to a hash map
   * Outer String key refers to the screen name of an individual screen, pulled from the screen's meta data
   */
  public static Type screensType = new TypeToken< HashMap<String, HashMap<String, Object>> >(){}.getType();
  
  /**
   * Contains type of metaData hash map, used to convert metaData Json Object to a hash map
   */
  public static Type metaDataType = new TypeToken< HashMap<String, String> >(){}.getType();
  
    // Types of  frequently used HashMap objects
  // screenType = Map<String, Object>
  // screensType = Map<String, Map<String, Object>>
  // metaDataType = Map<String, String>
  // sizingDataType = Map<String, Integer>
  // cellsDataType = Map<Point, Map<String, Object>>
  // cellType = Map<String, Object>
  
  //</editor-fold>

  
    // main method
  public static void main( String[] args ) {
    loadScreens();
    
    loadScreen( "chuzz" );
    
    JsonObject metaDataJsonObject = ( JsonObject ) getMetaData_AsJsonOrJavaObject.apply( true );
    
    System.out.println( screensJsonObject );
    
    metaDataJsonObject.remove( "name" );
    
    System.out.println( screensJsonObject );
    
    //System.out.println( getMetaData_AsJsonOrJavaObject.apply( false ).getClass().toString() );
  }
  
  /**
   * Initializes cell with point key and appends cell to cell data of loaded screen
   *
  public static void initializeCell()
  {
    Map<String, Object> cellToInitialize = new HashMap<>();
    
    System.out.print( "Enter cell coord x: " );
    int cellCoordX = s.nextInt();
    
    System.out.print( "Enter cell coord y: " );
    int cellCoordY = s.nextInt();
    
    Point cellCoord = new Point(
      cellCoordX, cellCoordY
    );
    
    getCellsData.apply( screen )
      .put( cellCoord, cellToInitialize );
  }
  */
  
  /**
   * Initializes a screen by collecting the information necessary to load the screen into the editor
   * Necessary information includes meta and sizing data
  *
  public static void initializeScreen()
  { 
      // fills the meta hash map
    Map<String, String> metaHashMap = new HashMap<>();
    for( String x : new String[]{ "name", "category" } )
    {
      System.out.print("Enter Screen " + x + ": ");
      metaHashMap.put( x, s.nextLine() );
    }

      // fills the sizing hash map
    Map<String, Integer> sizingHashMap = new HashMap<>();
    for( String x : new String[]{ "fontSize", "rowCount", "colCount" } )
    {
      System.out.print("Enter Screen " + x + ": ");
      sizingHashMap.put( x, s.nextInt() );
    }

      // declares the cells hash map
    Map<Point, Map<String, Object>> cellsHashMap = new HashMap<>();

      // fills the screen hash map
    screen.put( "metaData", metaHashMap );
    screen.put( "sizingData", sizingHashMap );
    screen.put( "cellsData", cellsHashMap );
  }
  */
  
  /**
   * Loads screen with name toLoadName into screen variable
   * 
   * @param toLoadName name of screen to load into screen variable
   */
  public static void loadScreen( String toLoadName )
  {
    screenJsonObject = 
      screensJsonObject.get( toLoadName )
      .getAsJsonObject();
  }
  
  /**
   * Loads JSON representation of screens object found in Screens.json into screens object
   */
  public static void loadScreens()
  {
    try {
        // Read JSON file content into a String
      String jsonString = new String( Files.readAllBytes( Paths.get( screensFilePath ) ) );
      
      JsonElement jsonElement = JsonParser.parseString( jsonString );
      
      screensJsonObject = jsonElement.getAsJsonObject();
      
    } catch( IOException e ){
      System.out.println( e );
    }
  }

  /**
   * Writes the screens object to the Screens json file, overwriting its previous contents completely
   *
  public static void writeScreens()
  {
      // writes screen to json String using Gsonbuilder
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson( screens );
    
      // attempts to write the json String to the Screens.json file
    try {
      FileWriter writer = new FileWriter( 
        "src/main/java/learn/hashmapjsontesting/screens.json"
      );
      
        // performs the overwrite action
      writer.write( json );
      writer.flush();
      writer.close();
      
      System.out.println("Write Successful");
      
    } catch( IOException e ){
      System.out.println( e );
    }
  }
  */
  
  /**
   * Appends screen object to screens object
   *
  public static void addScreenToScreens()
  {
      // grabs name of screen
    String screenName = 
      ( String ) 
        ( ( Map<String, Object> ) screen.get( "metaData" ) )
      .get( "name" );
    
      // appends screen to screens using screen's name as key
    screens.put( screenName, screen );
  }
  */
  
  /**
   * Clears data stored in screen, screens objects
   *
  public static void clearMemory()
  {
    screen = new HashMap<>();
    
    screens = new HashMap<>();
  }
  */
  
  //<editor-fold defaultstate="collapsed" desc="_AsJavaOrJsonObject">
  
  public static Function< Boolean, ? > getMetaData_AsJsonOrJavaObject = 
  ( asJsonObject ) -> {
      // grabs java object representation of metaData 
    JsonObject metaDataJsonObject = screenJsonObject.get( "metaData" ).getAsJsonObject();
    
      // simply returns JsonObject representation if asJsonObject is true
    if( asJsonObject ) { return metaDataJsonObject; }
    
      // returns meta data as java object, type casted, after converting it from a Json Object
    return gson.fromJson( metaDataJsonObject, metaDataType );
  };
  
  /**
  public static Function< Map<String, Integer> > getSizingData_AsJavaObject = 
  ( screenToGetFrom ) -> {
      // grabs java object representation of sizingData 
    JsonObject sizingDataJsonObject = screenToGetFrom.get( "sizingData" ).getAsJsonObject();
    
      // returns meta data as java object, type casted, after converting it from a Json Object
    return gson.fromJson( sizingDataJsonObject, sizingDataType ) ( Map<String, Integer> );
  };
  
  public static Function< JavaObject, Map<Point, Map<String, Object>> > getCellsData_AsJavaObject = 
  ( screenToGetFrom ) -> {
      // grabs java object representation of cellsData 
    JsonObject cellsDataJsonObject = screenToGetFrom.get( "cellsData" ).getAsJsonObject();
    
      // returns meta data as java object, type casted, after converting it from a Json Object
    return gson.fromJson( cellsDataJsonObject, cellsDataType ) ( Map<Point, Map<String, Object>> );
  };
  
  public static BiFunction< Point, Map<Point, Map<String, Object>>, Map<String, Object> > getCellFromPosition_AsJavaObject = 
  ( toGetPosition, cellsDataToGetFrom ) -> 
  ( Map<String, Object> ) cellsDataToGetFrom.get( toGetPosition );
  */
  
  //</editor-fold>
  
}
