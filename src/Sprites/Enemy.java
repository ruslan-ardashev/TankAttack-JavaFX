/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import javafx.animation.*;
import javafx.event.*;
import javafx.util.*;
import tankattack.*;

/**
 *
 * @author Ruslan
 */
public abstract class Enemy extends Sprite {
    
    public boolean isAlive;
    
    public Enemy(String image, double x, double y, World world) {
        
        super(image, x, y, world);
        this.isAlive = true;
                
    }
    
    public abstract void updateEnemyXY();
    public abstract boolean isFiring();
    
    public double getBulletOffsetX() {
        
        return (this.getTranslateX() + this.width()/2 );
        
    }
    
    public double getBulletOffsetY() {
        
        return (this.getTranslateY() + this.height());
        
    }
    
    public void checkForDeathAndReactAppropriately() {

        if (this.health <= 0) {

            displayDeath();
            addSelfToSpritesToRemove();
            removeSelfFromWorldAfterDelay();

        }

    }
    
    public void removeSelfFromWorldAfterDelay() {
        
        Timeline fiveSecondDelay = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {

            // Remove Self From World
            World.sharedInstance.removeSpritesToRemove();

        }
            
        }));
        
        fiveSecondDelay.setCycleCount(1);
        fiveSecondDelay.play();
        
    }
}
