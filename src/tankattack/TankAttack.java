/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ruslan
 */
public class TankAttack extends Application {
    
    public static final double gameWidth = 600;
    public static final double gameHeight = 600;
    
    private static final int NUM_FRAMES_PER_SECOND = 60;
    private Stage currStage;
    private World currWorld;
    private Scene currScene;
    
    @Override
    public void start(Stage primaryStage) {
        
        initTankAttack(primaryStage);
        
        displayCurrentScene();
        
        primaryStage.setTitle("TANK ATTACK");
        primaryStage.show();
                
        setupAndLaunchGameLoop();

    }
    
    private void setupAndLaunchGameLoop() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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




