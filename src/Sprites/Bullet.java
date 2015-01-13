/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import tankattack.*;

/**
 *
 * @author Ruslan
 */
public class Bullet extends Sprite {

    public static String imageName = "bullet.png";
    
    
    private boolean goingUp;
    
    
    public Bullet(double x, double y, World world, boolean goingUp) {
        
        super(Bullet.imageName, x, y, world);
        this.goingUp = goingUp;
        
    }
    
    public void updateXY() {
        
        boolean outOfBounds = 
                (this.getTranslateY() > TankAttack.gameHeight) 
                || 
                (this.getTranslateY() < 0.0);
                
        // Out of bounds check. Free up memory.
        if (outOfBounds) {
            
            addSelfToRemoveFromWorldArray();
            return;
            
        }
        
        else {
                        
            if (goingUp) {

                // Subtract y. y is actually up. Player.
                this.setTranslateY(this.getTranslateY() - TankAttack.BULLET_SPEED);

            }

            else {

                // Add y. Enemies code. "Down" is +y
                this.setTranslateY(this.getTranslateY() + TankAttack.BULLET_SPEED);

            }
            
        }
        
    }

    public void addSelfToRemoveFromWorldArray() {

        this.world().addToOutOfBoundaryBulletsArray(this);
        
    }
    
    
    
}
