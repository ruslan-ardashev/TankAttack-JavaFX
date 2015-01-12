/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import javafx.scene.image.*;
import tankattack.*;

/**
 *
 * @author Ruslan
 */
public class Player extends Sprite {
    
    public static String imageName = "testTank.png";
    
        
    // Performance-Improving Variables
    // created to not create objects @ 60Hz
    private double[] location; //x,y
    private double[] newXY; //x,y
    
    public Player(double x, double y, World world) {
        
        super(Player.imageName, x, y, world);
                
        this.health = 100.0;
        this.healthBar = null;
        System.out.println("null healthbar for now for player in constructor");
                
    }
     
    public void updateLocation() {
                
        if (location == null) {
            
            location = new double[2];
            
        }
        
        location[0] = this.getTranslateX();
        location[1] = this.getTranslateY();            
                
        double playerWidth  = this.width();
        double playerHeight = this.height();

        if (newXY == null) {
            
            newXY = new double[2];
            
        }
        
        newXY = DirController.getNewXY(location, playerWidth, playerHeight, TankAttack.PLAYER_SPEED);
        
        this.setTranslateX(newXY[0]);
        this.setTranslateY(newXY[1]);    
    
    }
 
    public double getBulletOffsetX() {
        
        return (this.getTranslateX() + this.width()/2 );
        
    }
    
    public double getBulletOffsetY() {
        
        return (this.getTranslateY());
        
    }
    
    @Override
    public void checkForDeathAndReactAppropriately() {

        if (this.health <= 0) {
            
            displayDeath();
            this.world.endOfLevelFailure();
            
        }

    }

    private void displayDeath() {

        this.setImage(new Image(getClass().getResourceAsStream("normalDeath.png")));
    
    }
    
}
