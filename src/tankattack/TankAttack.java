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
    
    public static final int NUM_FRAMES_PER_SECOND = 30;
    
    public static double buttonWidth = gameWidth / 5;
    public static double buttonHeight = gameWidth / 10;
    
    public static double PLAYER_SPEED = 2.5;
    public static double MINION_SPEED = 2.1;
    public static double BULLET_SPEED = 5.5;
    
    public static double BULLET_DAMAGE = 10;
    
    public static TankAttack sharedInstance;
    private Stage currStage;
    private World currWorld;
    private Scene currScene;
    
    @Override
    public void start(Stage primaryStage) {
        
        sharedInstance = this;
        currStage = primaryStage;
        displayStartMenu();

    }
    
    public void displayStartMenu() {
        
        currStage.setTitle("Main Menu");
        Group menuRoot = new Group();
        final Scene mainMenu = new Scene(menuRoot, TankAttack.gameWidth, TankAttack.gameHeight, Color.CORNFLOWERBLUE);
        
        // Launch Background Animation
        launchAnimationForDisplayMenu(currStage, menuRoot);
        
        // Create Buttons
        createButtonsForDisplayMenu(currStage, menuRoot);
        
        currStage.setScene(mainMenu);
        currStage.show();
        
    }
    
    private void launchAnimationForDisplayMenu(Stage stage, Group root) {
        
        // Most of this method was obtained from stackoverflow.com
        
        Group circles = new Group();

        for(int cont = 0 ; cont < 30 ; cont++) {  
            
            Circle circle = new Circle();  
            circle.setFill(Color.CRIMSON);  
            circle.setEffect(new GaussianBlur(Math.random() * 8 + 2));  
            circle.setOpacity(Math.random());  
            circle.setRadius(Math.random()*30);  
            circle.setCenterX(TankAttack.gameWidth * Math.random());
            circle.setCenterY(TankAttack.gameHeight * Math.random());
             
            circles.getChildren().add(circle);  
             
            double randScale = (Math.random() * 4) + 1;  
             
            KeyValue kValueX = new KeyValue(circle.scaleXProperty() , randScale);  
            KeyValue kValueY = new KeyValue(circle.scaleYProperty() , randScale);  
            KeyFrame kFrame = new KeyFrame(Duration.millis(5000 + (Math.random() * 5000)) , kValueX , kValueY);  
             
            Timeline timeL = new Timeline();  
            timeL.getKeyFrames().add(kFrame);  
            timeL.setAutoReverse(true);  
            timeL.setCycleCount(Animation.INDEFINITE);  
            timeL.play();  
            
        }  
        
        root.getChildren().add(circles);
        
    }
    
    private void createButtonsForDisplayMenu(Stage stage, Group root) {
        
        VBox v = new VBox(TankAttack.gameHeight/20);
        v.setTranslateY(TankAttack.gameHeight / 2);
        
        v.setTranslateX(TankAttack.gameWidth/2 - TankAttack.buttonWidth/2);
        
        Button start = this.createButton("START");
        
        start.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                stage.setTitle("TANK ATTACK");
                currWorld = new FirstWorld(stage);
                initCurrWorld();
                
            }
            
        });
        
        Button quit = this.createButton("QUIT");
        
        quit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                System.out.println("Quit button pressed.");
                Platform.exit();
                
            }
            
        });
                
        v.getChildren().addAll(start, quit);
        
        root.getChildren().add(v);
        
    }
    
    private Button createButton(String text) {
        
        Button returnButton = new Button();
        
        if (!text.isEmpty()) {
            
            returnButton.setText(text);
            
        }
        
        returnButton.setMinSize(buttonWidth, buttonHeight);
        
        return returnButton;
        
    }
    
    public void transitionFromFirstWorldToSecondWorld() {
        
        System.out.println("[transitionFromFirstWorldToSecondWorld] called");
        currWorld = new SecondWorld(currStage);
        initCurrWorld();
        
    }

    private void initCurrWorld() {
        
        // Prior to this method, set currWorld. 
        // rest is good to go.
        
        currScene = currWorld.createScene();
        currStage.setScene(currScene);
        currWorld.initAnimation();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

}




