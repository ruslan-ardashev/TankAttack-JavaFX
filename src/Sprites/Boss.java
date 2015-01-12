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
public class Boss extends Enemy {

    public static String imageName = "boss.png";
    
    public Boss(double x, double y, World world) {
        
        super(Boss.imageName, x, y, world);
        this.setHealthBar(new HealthBar(500.0));
        
        System.out.println("TODO: boss healthbar");
        
    }

    @Override
    public void updateEnemyXY() {

        if (!isAlive) {
            return;
        }
        
        double playerLocation = this.world.playerSprite.getTranslateX();
        
        boolean isPlayerToTheRightOfBoss = (playerLocation > this.getTranslateX());
        
        // TODO
        if (isPlayerToTheRightOfBoss) {
            
            goRight();
            
        }
        
        else {
            
            goLeft();
            
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
    
    @Override
    public void displayDeath() {

        this.setImage(new Image(getClass().getResourceAsStream("bossDeath.png")));
        this.isAlive = false;
    
    }

    private void goRight() {

        this.setTranslateX(this.getTranslateX() + TankAttack.BOSS_SPEED);
    
    }

    private void goLeft() {

        this.setTranslateX(this.getTranslateX() - TankAttack.BOSS_SPEED);
    
    }

}
