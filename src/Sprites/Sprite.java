
// This entire file is part of my masterpiece.
// Ruslan Ardashev

package Sprites;

import javafx.scene.*;
import javafx.scene.image.*;
import tankattack.*;

/**
 *
 * @author Ruslan
 */
public abstract class Sprite extends Group {
    
    public static String imageName;
    
    private World world;
    private ImageView image;
    private HealthBar healthBar;
    private double width;
    private double height;
    
    // Setters & Getters (ala Obj. C properties)
    public World world() {
        
        return this.world;
        
    }
    
    public HealthBar getHealthBar() {
        
        return healthBar;
        
    }
    
    public void setHealthBar(HealthBar bar) {
        
        this.healthBar = bar;
        
    }
    
    public void setImage(Image i) {
        
        this.image.setImage(i);
    
    }
    
    public double height() {
        
        return this.height;
        
    }
    
    public void setHeight(double height) {
        
        this.height = height;
        
    }
    
    public double width() {
        
        return this.width;
        
    }
    
    public void setWidth(double width) {
        
        this.width = width;
        
    }
    
    // Constructor & Other Methods
    public Sprite(String nameImage, double x, double y, World world) {
        
        this.world = world;
        this.initImageAndSize(nameImage);
                
        this.setTranslateX( x -  width/2 );
        this.setTranslateY( y - height/2 );
        
        this.initSetWorld(world);
        
    }
    
    private void initSetWorld(World world) {
        
        if (world == null) {
            
            System.out.println("FORGOT TO SET WORLD, [new Sprite()]");
            
        }
        
        else {
            
            world.addSprite(this);
            
        }
        
    }
    
    private void initImageAndSize(String nameImage) {
        
        this.image = new ImageView();
        
        if (image == null) {
            System.out.println("Forgot to pass image into sprite being created!");
        }
                                   
        this.image.setImage(new Image(getClass().getResourceAsStream(nameImage)));
        this.getChildren().add(this.image);
                
        this.width = this.image.getImage().getWidth();
        this.height = this.image.getImage().getHeight();
        
    }
    
    public void initHealthBar(double health, double width, double height, boolean isEnemy) {
                
        double HbarX,HbarY,HbarWidth,HbarHeight;
        
        HbarX = .15 *  width();
        HbarWidth = (.85 - .15) *  width();
                
        if (isEnemy) {
            
            HbarY = .1 * height();
            
        }
        
        else {
            
            HbarY = .8 * height();
            
        }
        
        HbarHeight = (.9 - .8) * height();
                
        this.healthBar = new HealthBar(health, HbarX, HbarY, HbarWidth, HbarHeight);
                
        this.getChildren().add(healthBar);
        
    }

    public void checkForDeathAndReactAppropriately() {

        // This is a virtual function.
        // The sprites that need this and use it overwrite it.
            // player, enemy (minion & boss)
        System.out.println("Calling checkForDeathAndReactAppropriately on a Sprite that doesn't support it.");
    
    }
    
    
    
}
