/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

/**
 *
 * @author Ruslan
 */
public class HealthBar extends Group {

    private double health, initialHealth;
    private Rectangle greenBar, redBar;
    
    double widthBar;
    
    public double getHealth() {
        
        return health;
        
    }
    
    public HealthBar(double health) {
        
        super();
        this.health = health;
        this.initRectangles();
        
    }
    
    private void initRectangles() {
        
        this.greenBar = new Rectangle();       
        this.greenBar.setFill(Color.GREEN);
        
        this.redBar = new Rectangle();
        this.redBar.setFill(Color.RED);
        
        // Done in this very specific order for a reason -
        // creates a static red bar. Green bar is overlayed.
        // As green shrinks, red grows withou need for gross math. yay!
        this.getChildren().add(redBar);
        this.getChildren().add(greenBar);
        
    }
    
    // return true if dead
    public boolean decrementHealth(double amount) {
        
        this.health -= amount;
        readjustSizeOfRectangles();
        
        return (this.health == 0);
        
    }
    
    public void instantDeath() {
        
        this.health = 0;
        
    }

    private void readjustSizeOfRectangles() {

        
    
    }
    
    
    
}
