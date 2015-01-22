
// This entire file is part of my masterpiece.
// Ruslan Ardashev

package Sprites;

import javafx.scene.image.*;
import tankattack.*;

/**
 *
 * @author Ruslan
 */
public class Minion extends Enemy {

    public static String imageName = "minion.png";
        
    private double leftXLimit, rightXLimit;
    private boolean goingRight;

    public Minion(double x, double y, World world, double leftXLimit, double rightXLimit, boolean goingRight) {
        
        super(Minion.imageName, x, y, world);
        
        this.leftXLimit = leftXLimit;
        this.rightXLimit = rightXLimit;
        
        this.goingRight = goingRight;
        
        // health, width parent, height parent, isEnemy
        if (TankAttack.DIFFICULTY_SETTING == 1) {
            
            this.initHealthBar(50.0, this.width(), this.height(), true);
            
        }
        
        else if (TankAttack.DIFFICULTY_SETTING == 2) {
         
            this.initHealthBar(100.0, this.width(), this.height(), true);
            
        }
        
        else if (TankAttack.DIFFICULTY_SETTING == 3) {
         
            this.initHealthBar(200.0, this.width(), this.height(), true);
            
        }  
                
    }
    
    @Override
    public void updateEnemyXY() {
        
        if (!isAlive) {
            
            return;
            
        }
        
        if (goingRight) {
            
            double rightDestination = this.getTranslateX() + this.width() + TankAttack.MINION_SPEED;
            
            if (rightDestination >= rightXLimit) {
                
                goingRight = false;
                goLeft();
                
            }
            // Update x ++
            
            else {
                
                goRight();
                
            }
            
        }
        
        else {
            
            // Update x --
            double leftDestination = this.getTranslateX() - TankAttack.MINION_SPEED;
            
            if (leftDestination <= leftXLimit) {
                
                goingRight = true;
                goRight();
                
            }
            
            else {
                
                goLeft();
                
            }
            
        }
        
    }

    @Override
    public boolean isFiring() {
        
        if (isAlive) {
            
            return (Math.random() <= .13);

        }
        
        else {
            
            return false;
            
        }
        
    }

    private void goLeft() {

        this.setTranslateX(this.getTranslateX() - TankAttack.MINION_SPEED);
    
    }

    private void goRight() {

        this.setTranslateX(this.getTranslateX() + TankAttack.MINION_SPEED);
    
    }
    
    @Override
    public void displayDeath() {

        this.setImage(new Image(getClass().getResourceAsStream("normalDeath.png")));
        this.isAlive = false;
    
    }
    
}
