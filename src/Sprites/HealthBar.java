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

    private boolean didCheatToIncreaseHealth;
    
    private double health, initialHealth;
    private Rectangle greenBar, redBar;
    private double widthBar;
    
    public double getHealth() {
        
        return health;
        
    }
    
    public HealthBar(double health, double x, double y, double width, double height) {
        
        super();
        this.health = health;
        this.initialHealth = health;
        this.widthBar = width;
        this.initRectangles(x, y, width, height);
        
    }
    
    private void initRectangles(double x, double y, double width, double height) {
                
        redBar = new Rectangle();
        redBar.setFill(Color.RED);
        redBar.setTranslateX(x);
        redBar.setTranslateY(y);
        redBar.setHeight(height);
        redBar.setWidth(width);
        
        greenBar = new Rectangle();       
        greenBar.setFill(Color.GREEN);
        greenBar.setTranslateX(x);
        greenBar.setTranslateY(y);
        greenBar.setHeight(height);
        greenBar.setWidth(width);
        
        // Done in this very specific order for a reason -
        // creates a static red bar. Green bar is overlayed.
        // As green shrinks, red grows withou need for gross math. yay!
        this.getChildren().add(redBar);
        this.getChildren().add(greenBar);
        
    }
    
    public void decrementHealth(double amount) {
        
        this.health -= amount;
        readjustSizeOfRectangles(amount);
                
    }
    
    public void instantDeath() {
        
        this.health = 0;
        this.readjustSizeOfRectangles(-1.0);
        
    }

    private void readjustSizeOfRectangles(double amountDecrement) {

        double percent;
        
        if (amountDecrement == -1.0) {
            
            // Collided with enemy, instant loss
            percent = 0.0;
            
        }
        
        else {
            
            percent = health / initialHealth;
            
        }

        greenBar.setWidth(percent * widthBar);
    
    }
    
    
    
}
