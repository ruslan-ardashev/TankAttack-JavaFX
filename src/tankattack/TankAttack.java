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
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.effect.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.transform.*;
import javafx.stage.Stage;
import javafx.util.*;

/**
 *
 * @author Ruslan
 */
public class TankAttack extends Application {
    
    public static TankAttack sharedInstance;
    
    public static final int NUM_FRAMES_PER_SECOND = 30;
    public static final double gameWidth = 600;
    public static final double gameHeight = 600;
    
    public static double buttonWidth = gameWidth / 5;
    public static double buttonHeight = gameWidth / 10;
    
    public static double PLAYER_SPEED = 2.5;
    public static double MINION_SPEED = 2.1;
    public static double EVILMINION_SPEED = 3.5;
    public static double BOSS_SPEED = 1.4;
    public static double BULLET_SPEED = 5.5;
    public static double BULLET_DAMAGE = 10;
    
    public static int DIFFICULTY_SETTING = 2;
    
    private Stage stage;
    private World world;
    private Scene scene;
    private Group root;
    
    private VBox startQuitButtonsBox;
    private VBox difficultyButtonsBox;
    
    // Setters & Getters
    public Stage stage() {
        
        return this.stage;
        
    }
    
    // Actual Methods
    @Override
    public void start(Stage primaryStage) {
        
        sharedInstance = this;
        stage = primaryStage;
        displayStartMenu();

    }
    
    public void displayStartMenu() {
        
        stage.setTitle("Main Menu");
        root = new Group();
        final Scene mainMenu = new Scene(root, TankAttack.gameWidth, TankAttack.gameHeight, Color.SEAGREEN);
        
        // Launch Background Animation
        launchAnimationForDisplayMenu(stage);
        
        // Create Buttons
        createButtonsForDisplayMenu();
        
        stage.setScene(mainMenu);
        stage.show();
        
        slideInTitle();
        
    }
    
    private void launchAnimationForDisplayMenu(Stage stage) {

        animateTankGoingBackAndForth();
        animateCircleExplosions();
        
    }
    
    private void createButtonsForDisplayMenu() {
        
        VBox v = new VBox(TankAttack.gameHeight/20);
        startQuitButtonsBox = v;
        
        v.setTranslateY(TankAttack.gameHeight / 2);
        
        v.setTranslateX(TankAttack.gameWidth/2 - TankAttack.buttonWidth/2);
        
        Button start = this.createButton("START");
        
        start.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                stage.setTitle("TANK ATTACK");
                world = new FirstWorld();
                initCurrWorld();
                
            }
            
        });
        
        Button difficulty = this.createButton("DIFFICULTY");
        
        difficulty.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                displayDifficultyMenu();
                
            }
            
        });
        
        Button quit = this.createButton("QUIT");
        
        quit.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                System.out.println("Quit button pressed.");
                Platform.exit();
                
            }
            
        });
                
        v.getChildren().addAll(start, difficulty, quit);
        
        root.getChildren().add(v);
        
    }
    
    private void displayDifficultyMenu() {
        
        root.getChildren().remove(this.startQuitButtonsBox);
        
        VBox v = new VBox(TankAttack.gameHeight/20);
        difficultyButtonsBox = v;
        
        v.setTranslateY(TankAttack.gameHeight / 2);
        
        v.setTranslateX(TankAttack.gameWidth/2 - TankAttack.buttonWidth/2);
        
        Button easy = this.createButton("EASY");
        
        easy.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                TankAttack.setEasy();
                removeDifficultyButtonsAndDisplayMenuAgain();
                
            }
            
        });
        
        Button medium = this.createButton("MEDIUM");
        
        medium.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                TankAttack.setMedium();
                removeDifficultyButtonsAndDisplayMenuAgain();
                
            }
            
        });
        
        Button hard = this.createButton("HARD");
        
        hard.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                
                TankAttack.setHard();
                removeDifficultyButtonsAndDisplayMenuAgain();
                
            }
            
        });
                
        v.getChildren().addAll(easy, medium, hard);
        
        root.getChildren().add(v);
        
    }
    
    public static void setEasy() {
        
        MINION_SPEED = 1.4;
        EVILMINION_SPEED = 2.5;
        BOSS_SPEED = 1;
        DIFFICULTY_SETTING = 1;
        
    }
    
    public static void setMedium() {
        
        MINION_SPEED = 2.1;
        EVILMINION_SPEED = 3.5;
        BOSS_SPEED = 1.4;
        DIFFICULTY_SETTING = 2;
        
    }
    
    public static void setHard() {
        
        MINION_SPEED = 3;
        EVILMINION_SPEED = 4.2;
        BOSS_SPEED = 3;
        DIFFICULTY_SETTING = 3;
        
    }
    
    private void removeDifficultyButtonsAndDisplayMenuAgain() {
     
        root.getChildren().remove(difficultyButtonsBox);
        createButtonsForDisplayMenu();
        
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
        
        world = new SecondWorld();
        initCurrWorld();
        
    }
    
    public void transitionFromSecondWorldToThirdWorld() {
        
        world = new ThirdWorld();
        initCurrWorld();
        
    }

    private void initCurrWorld() {
        
        // Prior to this method, set currWorld. 
        // rest is good to go.
        
        scene = world.createScene();
        stage.setScene(scene);
        world.initAnimation();
        
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void slideInTitle() {

        BorderPane b = new BorderPane();
        b.setMinWidth(TankAttack.gameWidth);
        b.translateYProperty().set(-60.0);
        
        Label l = new Label("TANK ATTACK");
        l.setFont(new Font("Arial", 80));
        l.setTextFill(Color.GREEN);
        
        b.setCenter(l);
        
        KeyValue kVal = new KeyValue(b.translateYProperty(), TankAttack.gameHeight/3);
        KeyFrame k = new KeyFrame(Duration.millis(3000), kVal);
               
        Timeline timeL = new Timeline();  
        timeL.getKeyFrames().add(k);  
        timeL.setCycleCount(1);  
        timeL.play();  
        
        root.getChildren().add(b);
    
    }

    private void animateCircleExplosions() {
 
        // Most of this method was obtained from stackoverflow.com
        
        Group circles = new Group();

        for(int cont = 0 ; cont < 30 ; cont++) {  
            
            Circle circle = new Circle();  
            
            if (Math.random() <= 0.5) {
                circle.setFill(Color.CRIMSON); 
            }
            
            else {
                circle.setFill(Color.YELLOW);
            }
            
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

    private void animateTankGoingBackAndForth() {

        ImageView tank = new ImageView();
        tank.xProperty().set(-gameWidth/3);
        tank.translateYProperty().set(gameHeight*5/6);
        
        tank.setImage(new Image(getClass().getResourceAsStream("tank.png")));
        
        KeyValue kValueX = new KeyValue(tank.xProperty(), gameWidth+gameWidth/3);
        KeyFrame kFrame = new KeyFrame(Duration.millis(8000) , kValueX); 
        
        Timeline timeL = new Timeline();  
        timeL.getKeyFrames().add(kFrame);  
        timeL.setAutoReverse(true);
        timeL.setCycleCount(Animation.INDEFINITE);  
        timeL.play();  
        
        root.getChildren().add(tank);
        
    }

}




