/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sprites;

import javafx.scene.*;
import javafx.scene.image.*;
import tankattack.*;

/**
 *
 * @author Ruslan
 */
public abstract class Sprite extends Group {
    
    public World world;
    
    public static String imageName;
    
    public ImageView image;
    
    private double width;
    private double height;
    
    private HealthBar healthBar;
       
    public HealthBar getHealthBar() {
        
        return healthBar;
        
    }
    
    public void setHealthBar(HealthBar bar) {
        
        this.healthBar = bar;
        
    }
    
    public void setImage(Image i) {
        
        this.image.setImage(i);
    
    }
    
    public Sprite(String nameImage, double x, double y, World world) {
        
        this.world = world;
        this.initImageAndSize(nameImage);
                
        this.setTranslateX( x -  width/2 );
        this.setTranslateY( y - height/2 );
        
        this.initSetWorld(world);
        
    }
        if (world == null) {
            
            System.out.println("FORGOT TO SET WORLD, [new Sprite()]");
            
        }
        
        else {
            
            world.addSprite(this);
            
        }
        
    }
    
    public double height() {
        
        return this.height;
        
    }
    
    public double width() {
        
        return this.width;
        
    }
    
    public void setHeight(double height) {
        
        this.height = height;
        
    }
    
    public void setWidth(double width) {
        
        this.width = width;
        
    }

    public void checkForDeathAndReactAppropriately() {

        System.out.println("Calling checkForDeathAndReactAppropriately on a Sprite that doesn't support it.");
    
    }
    
    
    
}
