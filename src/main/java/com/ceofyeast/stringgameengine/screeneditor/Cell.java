/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceofyeast.stringgameengine.screeneditor;

/**
 * Defines a JTextField subclass that represents a cell in the cellsMatrix. As an abstract class, Cell can only
 * be instantiated by its two subclasses, {@link CellEditMode CellEditMode} and {@link CellViewMode CellViewMode}.
 * These subclasses are built to support two subclasses of {@link CellsMatrix CellsMatrix}, which are 
 * {@link CellsMatrixEditMode CellsMatrixEditMode} and {@link CellsMatrixViewMode CellsMatrixViewMode} 
 * respectively. The Cell subclasses are protected members of these classes, so they can only be used in the 
 * context of their particular implementation.
 * 
 * <p>As for what a Cell actually is, the cellsMatrix is an editable representation of the console, which means 
 *    that the cells inside the cellsMatrix are editable representations of the "console cells" in a console. 
 *    Therefore, to understand the functionality of the cell object, one must understand the functionality of 
 *    the "console cell". 
 * 
 * <p>Every console cell inside the console is the same size and can contain, at most, a single character of the 
 *    same font. If a console cell doesn't contain a character, it's sized as if it does. Since the console can 
 *    only use mono-spaced fonts, the console cells' widths and heights are determined by the max width and height 
 *    of a character from the font being used because the characters are all the same size. The console cells are 
 *    then scaled up or down based on the font size.
 *    
 * <p>The Cell class seeks to replicate all of these behaviors. The individual cells are sized automatically by 
 *    java swing as long as they're initialized containing a single character from a mono-spaced font with zero 
 *    margin.
 * 
 * @author Benton Diebold (ceofyeast)
 */
abstract class Cell extends javax.swing.JTextField {}
