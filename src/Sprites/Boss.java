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
public class Boss extends Enemy {

    public static String imageName = "bossTank.png";
    
    public Boss(double x, double y) {
        
        super(Boss.imageName, x, y);
        
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
