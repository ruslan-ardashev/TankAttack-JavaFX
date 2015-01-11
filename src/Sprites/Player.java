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
    
    public Player(double x, double y) {
        
        super(Player.imageName, x, y);
                
        this.healthBar = null;
        System.out.println("null healthbar for now for player in constructor");
                
    } 
    
}
