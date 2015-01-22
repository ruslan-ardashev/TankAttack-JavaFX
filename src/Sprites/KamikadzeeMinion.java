
// This entire file is part of my masterpiece.
// Ruslan Ardashev

package Sprites;

import javafx.scene.image.*;
import tankattack.*;

/**
 *
 * @author Ruslan
 */
public class KamikadzeeMinion extends Enemy {

    public static String imageName = "minion.png";
        
    private double leftXLimit, rightXLimit;
    private boolean goingRight;

    public KamikadzeeMinion(double x, double y, World world) {
        
        super(Minion.imageName, x, y, world);
        
        this.leftXLimit = leftXLimit;
        this.rightXLimit = rightXLimit;
        
        this.goingRight = goingRight;
        
        // health, width parent, height parent, isEnemy
        if (TankAttack.DIFFICULTY_SETTING == 1) {
            
            this.initHealthBar(100.0, this.width(), this.height(), true);
            
        }
        
        else if (TankAttack.DIFFICULTY_SETTING == 2) {
         
            this.initHealthBar(200.0, this.width(), this.height(), true);
            
        }
        
        else if (TankAttack.DIFFICULTY_SETTING == 3) {
         
            this.initHealthBar(300.0, this.width(), this.height(), true);
            
        }  
                
    }
    
    @Override
    public void updateEnemyXY() {
        
        if (!isAlive) {
            
            return;
                    
        }
        
        double playerLocationX = this.world().playerSprite().getTranslateX();
        double playerLocationY = this.world().playerSprite().getTranslateY();
        
        boolean isPlayerToTheRightOfBoss = (playerLocationX > this.getTranslateX());
        boolean isPlayerToTheDownOfBoss = (playerLocationY > this.getTranslateY());
        
        // TODO
        if (!isPlayerToTheRightOfBoss && !isPlayerToTheDownOfBoss) {
            
            goLeft();
            goUp();
            
        }
        
        else if (!isPlayerToTheRightOfBoss && isPlayerToTheDownOfBoss) {
            
            goLeft();
            goDown();
            
        }
        
        else if (isPlayerToTheRightOfBoss && !isPlayerToTheDownOfBoss) {
            
            goRight();
            goUp();
            
        }
        
        else if (isPlayerToTheRightOfBoss && isPlayerToTheDownOfBoss) {
            
            goRight();
            goDown();
            
        }
        
    }

    @Override
    public boolean isFiring() {
        
        return false;
        
    }

    private void goLeft() {

        this.setTranslateX(this.getTranslateX() - TankAttack.KAMIKADZEEMINION_SPEED);
    
    }

    private void goRight() {

        this.setTranslateX(this.getTranslateX() + TankAttack.KAMIKADZEEMINION_SPEED);
    
    }
    
    private void goUp() {

        this.setTranslateY(this.getTranslateY() - TankAttack.KAMIKADZEEMINION_SPEED);
    
    }

    private void goDown() {

        this.setTranslateY(this.getTranslateY() + TankAttack.KAMIKADZEEMINION_SPEED);
    
    }
    
    @Override
    public void displayDeath() {

        this.setImage(new Image(getClass().getResourceAsStream("normalDeath.png")));
        this.isAlive = false;
    
    }
    
}
