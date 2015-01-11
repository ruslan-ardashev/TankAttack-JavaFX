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

    public Minion(double x, double y, World world) {
        
        super(Minion.imageName, x, y, world);
        this.health = 100.0;
        this.healthBar = null;
        System.out.println("in minion constructor, need to create HealthBar");
        
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
