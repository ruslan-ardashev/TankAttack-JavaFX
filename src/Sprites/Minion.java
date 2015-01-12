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
public class Minion extends Enemy {

    public static String imageName = "minion.png";
    
    private double leftXLimit, rightXLimit;
    private boolean goingRight;

    public Minion(double x, double y, World world, double leftXLimit, double rightXLimit, boolean goingRight) {
        
        super(Minion.imageName, x, y, world);
        this.health = 100.0;
        this.healthBar = null;
        System.out.println("in minion constructor, need to create HealthBar");
        
        this.leftXLimit = leftXLimit;
        this.rightXLimit = rightXLimit;
        
        this.goingRight = goingRight;
        
    }
    
    @Override
    public void updateEnemyXY() {
        
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
        
        // TODO
        double randomChance = Math.random();
                
        if (randomChance <= .13) {
            
            return true;
            
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
    
    
    
}
