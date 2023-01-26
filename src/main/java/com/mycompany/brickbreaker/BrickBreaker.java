/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.brickbreaker;

/**
 *
 * @author Shilpi Mazumdar
 */

import javax.swing.JFrame;

public class BrickBreaker {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        GamePlay gameplay = new GamePlay();
        
        // To display the frame at the center of the screen
        //frame.setLocationRelativeTo(null); 
        
        // Setting Fixed Bounds
        frame.setBounds(10,10,700,600);
        
        // Setting Resizeable as false
        frame.setResizable(false);
        
        // Specifiying the title of the JFrame
        frame.setTitle("Ninja Brick Breaker Game");
        
        frame.setVisible(true);
        
        // To make the frame closeable
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Adding gameplay class on frame
        frame.add(gameplay);
    }
}
