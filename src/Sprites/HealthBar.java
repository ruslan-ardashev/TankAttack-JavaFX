/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import javafx.scene.*;
import javafx.scene.shape.*;

/**
 *
 * @author Ruslan
 */
public class HealthBar extends Group {
    
    private double health;
    private Rectangle greenBar, redBar;
    
    public HealthBar(double health) {
        
        super();
        this.health = health;
        
    }
    
    // return true if dead
    public boolean decrementHealth(double amount) {
        
        this.health -= amount;
        
        
        if (this.health == 0) {
            
            return true;
            
        }
        
        else {
            
            return false;
            
        }
        
    }
    
    
    
}
