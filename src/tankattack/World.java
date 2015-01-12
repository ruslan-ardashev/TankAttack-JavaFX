/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

import Sprites.*;
import java.util.*;
import javafx.animation.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.stage.*;
import javafx.util.*;

/**
 *
 * @author Ruslan
 */
public abstract class World {
    
    public static boolean isListeningForInput = true;
    
    private Stage myStage;
    
    public static World sharedInstance;
    
    private Scene scene;
    private Group root;
    private Circle myEnemy;
    private Point2D myEnemyVelocity;
    private Random myGenerator = new Random();
    
    private ArrayList<Sprite> sprites;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> bulletsToRemove;
    private Player playerSprite;
        
    private Timeline timeline;

    
    // Setters, Getters
    public void addSprite(Sprite s) {
                
        if (s instanceof Bullet) {
            
            if (bullets == null) {

                bullets = new ArrayList();

            }
            
            bullets.add((Bullet)s);
            
        }
        
        else {
            
            if (sprites == null) {

                sprites = new ArrayList();

            }
            
            sprites.add(s);
            
        }
        
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
                
        Player player = new Player(TankAttack.gameWidth/2 , TankAttack.gameHeight / 2, this);
                
        setPlayerSprite(player);
    
    }

    private void updateSprites() {

//        System.out.println("All is well. Printing animation 60 times a second.");
        
        ////// DONE ////////////////////////////
        playerSprite.updateLocation();
        
        // Handle Player Firing
        handleFiring();
        
        // Other Updates
        updateEnemySprites();       // also handles enemy fire
        
        // Bullet Movement
        updateBulletMovements();

        ////// DONE ////////////////////////////
        
        ////// IMPLEMENT ////////////////////////////
        
        // Register Collisions With Tanks
        handleCollision();
        
        // Register Collisions Between Sprites & Bullets
        handleCollisionBullets();
        
        // Check for win
        checkForWin();
        ////// IMPLEMENT ////////////////////////////
        
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

    private void handleCollision() {

        for (Sprite s : sprites) {
            
            if (!s.equals(playerSprite)) {
                
                if (playerSprite.getBoundsInParent().intersects(s.getBoundsInParent())){
                    
                    System.out.println("COLLISION WITH SPRITE: " + s);
                    System.out.println("TODO: Implement player dying here");
                    
                }
                
            }
            
        }
    
    }

    private void updateEnemySprites() {

        Enemy enemy;
        
        for (Sprite s : sprites) {
            
            if (s instanceof Enemy) {
                
                enemy = (Enemy)s;
                
                // Movement
                enemy.updateEnemyXY();
                
                // Firing
                if (enemy.isFiring()) {
                    
                    handleEnemyFiring(enemy);
                    
                }
                
            }
            
        }
        
    }

    // Player firing, NOT enemy firing.
    private void handleFiring() {

        // Check if space bar pressed, create new bullets for Player
        if (DirController.spacePressed) {
            
            new Bullet(playerSprite.getBulletOffsetX(), playerSprite.getBulletOffsetY(), this, true);
            
        }
        
    }

    private void handleEnemyFiring(Enemy enemy) {

        new Bullet(enemy.getBulletOffsetX(), enemy.getBulletOffsetY(), this, false);
    
    }

    private void updateBulletMovements() {

        Bullet b;

        if (bullets == null) {
            
            bullets = new ArrayList<Bullet>();
            return;
            
        }
        
        for (Sprite s : bullets) {

            if (s instanceof Bullet) {

                b = (Bullet)s;
                b.updateXY();

            }

        }
        
        removeOutOfBoundaryBullets();

    }

    private void removeOutOfBoundaryBullets() {

        if (bulletsToRemove == null) {
            
            return;
            
        }
        
        for (Bullet b : bulletsToRemove) {
            
            bullets.remove(b);
            root.getChildren().remove(b);
            
        }
        
        bulletsToRemove.clear();
        
    }
    
    public void addToOutOfBoundaryBulletsArray(Bullet b) {
        
        if (bulletsToRemove == null) {
            
            bulletsToRemove = new ArrayList<Bullet>();
            
        }
        
        bulletsToRemove.add(b);
        
    }

    private void handleCollisionBullets() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}




