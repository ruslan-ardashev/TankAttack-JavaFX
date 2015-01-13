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
public class EvilMinion extends Enemy {

    public static String imageName = "minion.png";
        
    private double leftXLimit, rightXLimit;
    private boolean goingRight;

    public EvilMinion(double x, double y, World world, double leftXLimit, double rightXLimit, boolean goingRight) {
        
        super(Minion.imageName, x, y, world);
        
        this.leftXLimit = leftXLimit;
        this.rightXLimit = rightXLimit;
        
        this.goingRight = goingRight;
        
        // health, width parent, height parent, isEnemy
        this.initHealthBar(200.0, this.width(), this.height(), true);
                
    }
    
    @Override
    public void updateEnemyXY() {
        
        if (!isAlive) {
            
            return;
            
        }
        
        if (goingRight) {
            
            double rightDestination = this.getTranslateX() + this.width() + TankAttack.EVILMINION_SPEED;
            
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
            double leftDestination = this.getTranslateX() - TankAttack.EVILMINION_SPEED;
            
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
