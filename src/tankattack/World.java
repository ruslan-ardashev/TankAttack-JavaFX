/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

import Sprites.Sprite;
import java.util.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;

/**
 *
 * @author Ruslan
 */
public abstract class World {
    
    private Stage myStage;
    
    private Scene scene;
    private Group root;
    private ImageView myPlayer;
    private Circle myEnemy;
    private Point2D myEnemyVelocity;
    private Random myGenerator = new Random();
    
    private ArrayList<Sprite> sprites;
    
    private Timeline timeline;
    
    // Setters, Getters
    public void addSprite(Sprite s) {
        
        if (sprites == null) {
            
            sprites = new ArrayList();
            
        }
        
        sprites.add(s);
        
    }
    
    public ArrayList<Sprite> getSprites() {
        
        return sprites;
        
    }
    
    
    // Real Methods
    
    
    public World() {
        throw new UnsupportedOperationException("need to pass in a stage"); 
    }
    
    public World(Stage stage) {
        
        this.myStage = stage;
        
    }
    
    public Scene createScene() {
        
        root = new Group();
        createInitialSprites(root);
        
        scene = new Scene(root, TankAttack.gameWidth, TankAttack.gameHeight, Color.CORNFLOWERBLUE);
        return scene;
        
    }
    
    public KeyFrame createKeyFrame(int NUM_FRAMES_PER_SECOND) {
        
        throw new UnsupportedOperationException("Not supported yet.");
        
    }
    
    public void initAnimation() {
        
        
        
    }

    private void createInitialSprites(Group root) {
        
        throw new UnsupportedOperationException("Not supported yet."); 
        
    }

    

    
}
