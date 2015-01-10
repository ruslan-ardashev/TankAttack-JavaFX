/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

import javafx.animation.*;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.*;

/**
 *
 * @author Ruslan
 */
public class TankAttack extends Application {
    
    public static final double gameWidth = 600;
    public static final double gameHeight = 600;
    
    public static final int NUM_FRAMES_PER_SECOND = 60;
    
    public static double buttonWidth = gameWidth / 5;
    public static double buttonHeight = gameWidth / 10;
    
    private Stage currStage;
    private World currWorld;
    private Scene currScene;
    
    @Override
    public void start(Stage primaryStage) {
        
        currStage = primaryStage;
        displayStartMenu(primaryStage);

    }
    
    private void displayStartMenu(Stage stage) {
        
        stage.setTitle("Main Menu");
        Group menuRoot = new Group();
        final Scene mainMenu = new Scene(menuRoot, TankAttack.gameWidth, TankAttack.gameHeight);
        
        // Launch Background Animation
        launchAnimationForDisplayMenu(stage, menuRoot);
        
        // Create Buttons
        createButtonsForDisplayMenu(stage, menuRoot);
        
        stage.setScene(mainMenu);
        stage.show();
        
    }
        
        displayCurrentScene();
        
        primaryStage.setTitle("TANK ATTACK");
        primaryStage.show();
                
        setupAndLaunchGameLoopForCurrWorld(currWorld);

    }
    
    public void setupAndLaunchGameLoopForCurrWorld(World world) {

        world.initAnimation();
    
    }
    
    private void transitionFromFirstWorldToSecondWorld() {
        
        
        
    }

    private void initTankAttack(Stage stage) {
        
        currStage = stage;
        currWorld = new FirstWorld(stage);
        currScene = currWorld.createScene();
        
    }
    
    private void displayCurrentScene() {
        
        currStage.setScene(currScene);
        
    }
    
    public void tellStageToShowScene(Scene scene) {
        
        currStage.setScene(scene);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}




