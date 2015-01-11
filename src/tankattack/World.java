/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

import Sprites.*;
import java.util.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.util.*;

/**
 *
 * @author Ruslan
 */
public abstract class World {
    
    public static boolean isListeningForInput = true;
    
    private Stage myStage;
    
    private Scene scene;
    private Group root;
    private Circle myEnemy;
    private Point2D myEnemyVelocity;
    private Random myGenerator = new Random();
    
    private ArrayList<Sprite> sprites;
    private Player playerSprite;
        
    private Timeline timeline;
    
    // Performance-Improving Variables
    // created to not create objects @ 60Hz
    private double[] playerLocation;   
    
    
    
    // Setters, Getters
    public void addSprite(Sprite s) {
        
        if (sprites == null) {
            
            sprites = new ArrayList();
            
        }
        
        sprites.add(s);
        
        root.getChildren().add(s);
        
    }
    
    public ArrayList<Sprite> getSprites() {
        
        return sprites;
        
    }
    
    public void setPlayerSprite(Player player) {
        
        playerSprite = player;
        
    }
    
    public Player getPlayerSprite() {
        
        return playerSprite;
        
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
        createInitialSprites();
        
        scene = new Scene(root, TankAttack.gameWidth, TankAttack.gameHeight, Color.CORNFLOWERBLUE);
        scene.setOnKeyPressed(e -> handleKeyInput(e));
        return scene;
        
    }
    
    public void initAnimation() {
        
        KeyFrame frame = new KeyFrame(Duration.millis(1000 / TankAttack.NUM_FRAMES_PER_SECOND), e -> updateSprites());
        
        if (timeline == null) {
            
            timeline = new Timeline();
            
        }        
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(frame);
        timeline.play();
        
    }

    public abstract void createInitialSprites();
    
    public void createPlayerSprite() {
                
        Player player = new Player();
        
        player.setImage(new Image(getClass().getResourceAsStream("testTank.png")));
        player.setTranslateX(TankAttack.gameWidth/2);
        player.setTranslateY(TankAttack.gameHeight/2);
        
        
        
        setPlayerSprite(player);
        addSprite(player);
    
    }

    private void updateSprites() {

//        System.out.println("All is well. Printing animation 60 times a second.");
        
        
        
    }

    public abstract void signalEndOfLevel();

    private void handleKeyInput(KeyEvent e) {
        
        // Obtained from Prof. Duvall's example
        KeyCode keyCode = e.getCode();
                
        if (keyCode == KeyCode.RIGHT) {
            playerSprite.setTranslateX(playerSprite.getTranslateX() + TankAttack.PLAYER_SPEED);
        }
        else if (keyCode == KeyCode.LEFT) {
            playerSprite.setTranslateX(playerSprite.getTranslateX() - TankAttack.PLAYER_SPEED);
        }
        else if (keyCode == KeyCode.UP) {
            playerSprite.setTranslateY(playerSprite.getTranslateY() - TankAttack.PLAYER_SPEED);
        }
        else if (keyCode == KeyCode.DOWN) {
            playerSprite.setTranslateY(playerSprite.getTranslateY() + TankAttack.PLAYER_SPEED);
        }
        
    }

    
    
}
