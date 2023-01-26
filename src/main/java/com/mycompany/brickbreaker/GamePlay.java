/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.brickbreaker;

/**
 *
 * @author Shilpi Mazumdar
 */

// Panel is over which we design the game 
import javax.swing.JPanel;
import javax.swing.Timer;

//To get Graphics and Heavy Objects we are using awt 
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

//To capture movement in the screen we need Action
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//KEYEVENT, KEYLISTENER, ACTIONEVENT, ACTIONLISTENER

public class GamePlay extends JPanel implements KeyListener, ActionListener{
    
   //DETERMINES IF GAME HAS STARTED OR NOT 
   private boolean play = false;
   private int score = 0;
   private Timer timer;
   private int totalBricks = 21;
   private int delay = 8;
   
   // Pre-known ball and slider postions initialized here
   private int player_x = 310;
   private int ballPos_x = 120;
   private int ballPos_y = 350;
   
   // To facilitate movement of the ball in x and y directions
   private int ballDir_x = -1;
   private int ballDir_y = -2;
   
   // MapGenerator Class
   private MapGenerator map;
   
   public GamePlay () {
       map = new MapGenerator(3,7);
       addKeyListener(this);
       
       // Makes the app focusable/visible on the screen - stays on the screen
       setFocusable(true);
       
       //CHECK WHAT THIS DOES !!!
       setFocusTraversalKeysEnabled(false);
       
       // Providing delay to the Timer 
       timer = new Timer(delay, this);
       
       //To start the timer
       timer.start();
   }
   
   @Override
   public void paint(Graphics graphic) {
       graphic.setColor(Color.black);
       //Fills the color of the rectangle with black 
       graphic.fillRect(1, 1, 692, 592);

       //Drawing the graphics of the game 
       map.draw((Graphics2D) graphic);

        // YELLOW BOUNDARY ON 2 SIDES 
        graphic.setColor(Color.yellow);
        graphic.fillRect(0, 0, 3, 592);
        graphic.fillRect(0, 0, 692, 3);
        graphic.fillRect(691, 0, 3, 592);

        //CREATING SCORE ON TOP RIGHT CORNER 
        graphic.setColor(Color.white);
        graphic.setFont(new Font("serif", Font.BOLD, 25));
        graphic.drawString("" + score, 590, 30);

        //CREATING A SLIDER AT THE BOTTOM 
        graphic.setColor(Color.yellow);
        graphic.fillRect(player_x, 550, 100, 8);
        
        //CREATING A BALL 
        graphic.setColor(Color.GREEN);
        graphic.fillOval(ballPos_x, ballPos_y, 20, 20);
        
        /* If the ball bypasses the slider, i.e., disappears from the screen
        the game stops */
        if(ballPos_y > 570) {
            play = false;
            // The game has stopped and you cannot proceed 
            ballDir_x = 0;
            ballDir_y = 0;
            
            graphic.setColor(Color.red);
            graphic.setFont(new Font("serif", Font.BOLD, 30));
            graphic.drawString("Game Over : Your Score - " + score, 190, 300);
            
            graphic.setFont(new Font("serif", Font.BOLD, 30));
            graphic.drawString("Press Enter to Restart", 190, 340);
        }
        /* If the player hits all the bricks then also the game ends */
        if(totalBricks == 0) {
            play = false;
            // Initialized values
            ballDir_y = -2;
            ballDir_x = -1;
            
            graphic.setColor(Color.red);
            graphic.setFont(new Font("serif", Font.BOLD, 30));
            graphic.drawString("Game Over : Your Score - " + score, 190, 300);
            
            graphic.setFont(new Font("serif", Font.BOLD, 30));
            graphic.drawString("Press Enter to Restart", 190, 340);
        }
        graphic.dispose();
   }
   
   // Action provided to the Game 
   @Override
   public void actionPerformed(ActionEvent e) {
       timer.start();
       
       if(play) {
           if(new Rectangle(ballPos_x, ballPos_y, 20, 20).intersects(new Rectangle(player_x, 550, 100, 8)))
               ballDir_y = -ballDir_y;
           
           Loop_label:
           for(int i = 0 ; i < map.map.length ; i++) {
               for(int j = 0 ; j < map.map[0].length ; j++) {
                   if(map.map[i][j] > 0) {
                       int brick_x = j * map.brickWidth + 80;
                       int brick_y = i * map.brickHeight + 50;
                       int bricksWidth = map.brickWidth;
                       int bricksHeight = map.brickHeight;
                       
                       Rectangle rectangle = new Rectangle(brick_x, brick_y, bricksWidth, bricksHeight);
                       Rectangle ball_rectangle = new Rectangle(ballPos_x, ballPos_y, 20, 20);
                       Rectangle brick_rectangle = rectangle;
                        
                       if (ball_rectangle.intersects(brick_rectangle)) {
                            map.setBricksValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if (ballPos_x + 19 <= brick_rectangle.x || ballPos_x + 1 >= brick_rectangle.x + bricksWidth)
                                ballDir_x = -ballDir_x;
                            else 
                                ballDir_y = -ballDir_y;
                            break Loop_label; 
                       }
                   }
               }
           }
           
           ballPos_x += ballDir_x;
           ballPos_y += ballDir_y;
           
           if (ballPos_x < 0)
                ballDir_x = -ballDir_x;
           if (ballDir_y < 0)
                ballDir_y = -ballDir_y;
           if (ballDir_x > 670) 
                ballDir_x = -ballDir_x;
       }
       repaint();
   }
   
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
   
   @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (player_x >= 600)
                player_x = 600;
            else
                moveRight();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (player_x < 10)
                player_x = 10;
            else
                moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                
                ballPos_x = 120;
                ballPos_y = 350;
                
                ballDir_x = -1;
                ballDir_y = -2;
                
                score = 0;
                
                player_x = 310;
                totalBricks = 21;
                
                map = new MapGenerator(3, 7);

                repaint();
            }
        }
    }
    
    public void moveRight (){
        play = true;
        player_x += 20;
    }
    public void moveLeft () {
        play = true;
        player_x -= 20;
    }
}
