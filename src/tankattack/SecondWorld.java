/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

import Sprites.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;

/**
 *
 * @author Ruslan
 */
public class SecondWorld extends World {

    SecondWorld(Stage stage) {

        super(stage);
    
    }

    @Override
    public void createInitialSprites() {
        
        createPlayerSprite();
        // Other sprites
        System.out.println("TODO FOR WORLD 2: make other sprites on createInitialSprites!");
        
    }

    @Override
    public void signalEndOfLevel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Scene createScene() {
        
        this.setGroup(new Group());
        createInitialSprites();
        
        
        this.setScene(new Scene(this.getGroup(), TankAttack.gameWidth, TankAttack.gameHeight, Color.PURPLE));
        this.getScene().setOnKeyPressed(e -> handleKeyInput(e));
        return this.getScene();
        
    }
    
}
