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
    
    public static World sharedInstance;
    public static boolean isListeningForInput = true;
    
    private Scene scene;
    private Group root;
    private Random myGenerator = new Random();
    private Timeline timeline;
    private Player playerSprite;
    
    // Sprite-Related Instance Vars
    private ArrayList<Sprite> sprites;
    private ArrayList<Sprite> spritesToRemove;
    private ArrayList<Bullet> bullets;
    private ArrayList<Bullet> bulletsToRemove;
    
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
        
    public Player playerSprite() {
        
        return playerSprite;
        
    }
    
    public void setPlayerSprite(Player player) {
        
        playerSprite = player;
        
    }
    
    public Group group() {
        
        return this.root;
        
    }
    
    public void setGroup(Group root) {
        
        this.root = root;
        
    }
    
    public Scene scene() {
        
        return this.scene;
        
    }
    
    public void setScene(Scene scene) {
        
        this.scene = scene;
        
    }
    
    // Real Methods
        // Constructors
        // Create Scene, Then Init Animation. Rest of methods are helpers.
    public World() {
        
        World.sharedInstance = this;
        
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
                
        playerSprite = new Player(TankAttack.gameWidth/2 , TankAttack.gameHeight / 2, this);
    
    }

    // Very important method.
    private void updateSprites() {
        
        playerSprite.updateLocation();
        handleFiring();             // Handle Player Firing
        updateEnemySprites();       // also handles enemy fire
        updateBulletMovements();    // Bullet Movement
        handleCollision();          // Register Collisions With Tanks
        handleCollisionBullets();   // Register Collisions Between Sprites & Bullets
        updateAllSpritesToCheckForDeath();
        checkForWin();              // Check for win
        
    }

    private void endOfLevelSuccess() {
        
        timeline.pause();
        
        // TODO: Display level success.
        showEndOfLevelTextSuccess();
        
        Timeline fiveSecondDelay = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                // Tell TankAttack to put up the next world.
                signalEndOfLevel();
        
            }
            
        }));
        
        fiveSecondDelay.setCycleCount(1);
        fiveSecondDelay.play();
        
    }
    
    public void endOfLevelFailure() {
        
        timeline.pause();
        
        // TODO: Display level failure.
        showEndOfLevelFailure();
        
        // http://stackoverflow.com/questions/9966136/javafx-periodic-background-task
        Timeline fiveSecondDelay = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                // Display Main Menu
                TankAttack.sharedInstance.displayStartMenu();
        
            }
            
        }));
        
        fiveSecondDelay.setCycleCount(1);
        fiveSecondDelay.play();
        
        //// //// //// //// //// //// //// //// //// //// //// //// //// //// //// //
        
        
        
    }
    
    public void showEndOfLevelTextSuccess() {
        
        Label l = new Label("SUCCESS");
        
        // http://docs.oracle.com/javafx/2/ui_controls/label.htm
        l.setFont(new Font("Arial", 60));
        l.setTextFill(Color.GREEN);
        
        l.setTranslateY(TankAttack.gameHeight / 2.5);
        
        // TODO The x is hardcoded. fix it in case game dimensions change
        l.setTranslateX(180);
        
        root.getChildren().add(l);
        
        
    }
    
    private void showEndOfLevelFailure() {
                
        Label l = new Label("FAILURE");
        
        // http://docs.oracle.com/javafx/2/ui_controls/label.htm
        l.setFont(new Font("Arial", 60));
        l.setTextFill(Color.RED);
        
        l.setTranslateY(TankAttack.gameHeight / 2.5);
        
        // TODO The x is hardcoded. fix it in case game dimensions change
        l.setTranslateX(180);
        
        root.getChildren().add(l);
        
    }
    
    public abstract void signalEndOfLevel();
    
    public void handleKeyInput(KeyEvent e) {
        
        modifyDirControllerState(e, true);
        
    }

    public void handleKeyRelease(KeyEvent e) {

        modifyDirControllerState(e, false);
        
    }
    
    private void modifyDirControllerState(KeyEvent key, boolean newState) {
        
        // newState reflects press(true) or release(false)
        
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
        
        // Cheat Codes
        else if (newState && keyCode == KeyCode.Y) {
            
            this.endOfLevelSuccess();
            
        }
        
        else if (newState && keyCode == KeyCode.I) {
            
            playerSprite.getHealthBar().infiniteHealth();
            
        }
        
        
    }

    private void checkForWin() {
        
        // Temporary end to game
        if (sprites.size() == 1) {
            
            endOfLevelSuccess();
            
            // TODO Implement this.
            // Player is left all alone. Stop animation. Level defeated.
            
            
        }
        
    }

    private void handleCollision() {

        for (Sprite s : sprites) {
            
            if (!s.equals(playerSprite)) {
                
                if (playerSprite.getBoundsInParent().intersects(s.getBoundsInParent())){
                    
                    handleCollisionWithEnemy((Enemy)s);
                    
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

        for (Sprite s : sprites) {
            
            for (Bullet b : bullets) {
                
                if (s.getBoundsInParent().intersects(b.getBoundsInParent())) {
                    
                    b.addSelfToRemoveFromWorldArray();
                    
                    s.getHealthBar().decrementHealth(TankAttack.BULLET_DAMAGE);
                                                            
                }
                
            }
            
        }
    
    }

    private void handleCollisionWithEnemy(Enemy s) {
        
        playerSprite.getHealthBar().instantDeath();
        s.getHealthBar().instantDeath();
        
    }

    private void updateAllSpritesToCheckForDeath() {
        
        for (Sprite s : sprites) {
            
            s.checkForDeathAndReactAppropriately();
            
        }
    
    }

    public void addSpriteToRemove(Sprite s) {

        if (spritesToRemove == null) {
            
            spritesToRemove = new ArrayList<Sprite>();
            
        }
        
        spritesToRemove.add(s);
    
    }
    
    public void removeSpritesToRemove() {
     
        if (spritesToRemove == null) {
            
            return;
            
        }
        
        for (Sprite s : spritesToRemove) {
            
            sprites.remove(s);
            root.getChildren().remove(s);
            
        }
        
        spritesToRemove.clear();
        
    }

}




