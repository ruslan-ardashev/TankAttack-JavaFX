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
    private double[] newXY;
    
    
    // Setters, Getters
    public void addSprite(Sprite s) {
        
        if (sprites == null) {
            
            sprites = new ArrayList();
            
        }
        
        sprites.add(s);
        root.getChildren().add(s);
        
    }
    
    public void removeSprite(Sprite s) {
        
        if (sprites == null) {
            
            return;
            
        }
        
        sprites.remove(s);
        root.getChildren().remove(s);
        
    }
        
    public void setPlayerSprite(Player player) {
        
        playerSprite = player;
        
    }
    
    public Player getPlayerSprite() {
        
        return playerSprite;
        
    }
    
    public Group getGroup() {
        
        return this.root;
        
    }
    
    public void setGroup(Group root) {
        
        this.root = root;
        
    }
    
    public Scene getScene() {
        
        return this.scene;
        
    }
    
    public void setScene(Scene scene) {
        
        this.scene = scene;
        
    }
    
    // Real Methods
        // Constructors
        // Create Scene, Then Init Animation. Rest of methods are helpers.
    
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
        scene.setOnKeyReleased(e -> handleKeyRelease(e));
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
                
        Player player = new Player(TankAttack.gameWidth/2 , TankAttack.gameHeight / 2);
                
        setPlayerSprite(player);
        addSprite(player);
    
    }

    private void updateSprites() {

//        System.out.println("All is well. Printing animation 60 times a second.");
        
        ////// DONE ////////////////////////////
        updatePlayerLocation();

        ////// DONE ////////////////////////////
        
        ////// IMPLEMENT ////////////////////////////
        // Other Updates
//        updateEnemySprites();
        
        // Handle Firing
        handleFiring();
        
        // Register Collisions / Hits
//        handleCollision();
        
        // Check for win
        checkForWin();
        ////// IMPLEMENT ////////////////////////////
        
    }
    
    private void updatePlayerLocation() {
                
        if (playerLocation == null) {
            
            playerLocation = new double[2];
            
        }
        
        playerLocation[0] = playerSprite.getTranslateX();
        playerLocation[1] = playerSprite.getTranslateY();            
                
        double playerWidth  = playerSprite.width();
        double playerHeight = playerSprite.height();

        if (newXY == null) {
            
            newXY = new double[2];
            
        }
        
        newXY = DirController.getNewXY(playerLocation, playerWidth, playerHeight, TankAttack.PLAYER_SPEED);
        
        playerSprite.setTranslateX(newXY[0]);
        playerSprite.setTranslateY(newXY[1]);    
    
    }

    private void endOfLevel() {
        
        timeline.pause();
        
        // TODO: Display level complete.
        showEndOfLevelText();
        
        // Tell TankAttack to put up the next world.
        signalEndOfLevel();
        
    }
    
    private void showEndOfLevelText() {
        
        System.out.println("TODO: Animate text over this level's end saying END OF LEVEL.");
        
    }
    
    public abstract void signalEndOfLevel();
    
    public void handleKeyInput(KeyEvent e) {
        
        modifyDirControllerState(e, true);
        
    }

    public void handleKeyRelease(KeyEvent e) {

        modifyDirControllerState(e, false);
        
    }
    
    private void modifyDirControllerState(KeyEvent key, boolean newState) {
        
        KeyCode keyCode = key.getCode();
                
        if (keyCode == KeyCode.RIGHT) {
            
            DirController.rightPressed = newState;
            
        }
        
        else if (keyCode == KeyCode.LEFT) {
            
            DirController.leftPressed = newState;
            
        }
        
        else if (keyCode == KeyCode.UP) {
            
            DirController.upPressed = newState;
            
        }
        
        else if (keyCode == KeyCode.DOWN) {
            
            DirController.downPressed = newState;
            
        }
        
        else if (keyCode == KeyCode.SPACE) {
            
            DirController.spacePressed = newState;
            
        }
        
        // TODO: Implement space bar to shoot, and cheat codes, here.
        
    }

    private void checkForWin() {
        
        // Temporary end to game
        if (playerSprite.getTranslateX() < 10) {
            
            System.out.println("updateSprites calling finish.");
            endOfLevel();
            
            // TODO Implement this.
            // Player is left all alone. Stop animation. Level defeated.
            
            
        }
        
    }

            
            // Implement.
            System.out.println("PEW PEW"); 
            
            
            
        }
        
    }


}






