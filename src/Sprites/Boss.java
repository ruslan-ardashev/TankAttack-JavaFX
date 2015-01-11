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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFiring() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    
    
}
