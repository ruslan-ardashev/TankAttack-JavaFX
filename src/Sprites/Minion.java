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
public class Minion extends Enemy {

    public static String imageName = "minionTank.png";

    public Minion(double x, double y) {
        
        super(Minion.imageName, x, y);
        
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
