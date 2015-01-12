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
        this.health = 300.0;
        
        this.healthBar = null;
        System.out.println("TODO: boss healthbar");
        
    }

    @Override
    public void updateEnemyXY() {

        // TODO
    
    }

    @Override
    public boolean isFiring() {
        
        // TODO
        return false;
    
    }
 
    
    
}
