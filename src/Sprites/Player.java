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

    
    public Player() {
        
        super();
        this.setImage(new Image(getClass().getResourceAsStream("testTank.png")));
        
        this.setWidth(this.getImage().getWidth());
        this.setHeight(this.getImage().getHeight());
        
        this.setTranslateX((TankAttack.gameWidth  - width())/2);
        this.setTranslateY((TankAttack.gameHeight - height())/2);
        
        this.healthBar = null;
        System.out.println("null healthbar for now for player in constructor");
                
    } 
    
}
