/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

/**
 *
 * @author Ruslan
 */
public abstract class Enemy extends Sprite {
    
    public abstract void updateEnemyXY();
    public abstract boolean isFiring();
    
    
}
