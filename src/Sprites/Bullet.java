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
    
    public Bullet(double x, double y, World world) {
        
        super(Bullet.imageName, x, y, world);
        
    }
    
    
    
}
