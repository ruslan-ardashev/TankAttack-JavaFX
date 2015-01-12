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
public abstract class Enemy extends Sprite {
    
    public Enemy(String image, double x, double y, World world) {
        
        super(image, x, y, world);
                
    }
    
    public abstract void updateEnemyXY();
    public abstract boolean isFiring();
    
    public double getBulletOffsetX() {
        
        return (this.getTranslateX() + this.width()/2 );
        
    }
    
    public double getBulletOffsetY() {
        
        return (this.getTranslateY() + this.height());
        
    }
    
}
