/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

/**
 *
 * @author Shilpi Mazumdar
 */

// Outliner between 2 consecutive bricks 
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
    // The bricks are in the form of 2D array
    public int map[][];
    
    //Setting width and height of the bricks
    public int brickWidth;
    public int brickHeight;
    
    public MapGenerator(int row, int col) {
        map = new int[row][col];
        // Initializing a BrickMap
        for(int[] brickmap : map) {
            for(int j = 0 ; j < map[0].length ; j++)
                brickmap[j] = 1;
        }
        // PREDETERMINED VALUES : SO WE ARE USING THESE
        brickWidth = 540/col;
        brickHeight = 150/row;
    }
    
    // Drawing BrickMap on the Screen
    public void draw(Graphics2D graphic) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                // BRICK IS PRESENT 
                if (map[i][j] > 0) {
                    graphic.setColor(Color.red);
                    graphic.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                    
                    // SETTING THE BOUNDARY TO DISTINGUISH EACH BRICK 
                    graphic.setStroke(new BasicStroke(3));
                    graphic.setColor(Color.black);
                    //STROKES TO ALIGN WITH THE BOXES 
                    graphic.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
                }
            }
        }
    }
    
    //
    public void setBricksValue(int value, int row, int col) {
        map[row][col] = value;
    }
}
