/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

import Sprites.*;
import java.awt.image.*;
import java.io.*;
import java.util.logging.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javax.imageio.*;


/**
 *
 * @author Ruslan
 */
public class FirstWorld extends World {

    FirstWorld(Stage primaryStage) {

        super(primaryStage);
        
    }

    @Override
    public void createInitialSprites() {
        
        createPlayerSprite();
        // Other sprites
        createEnemies();
        
        
    }

    @Override
    public void signalEndOfLevel() {

        TankAttack.sharedInstance.transitionFromFirstWorldToSecondWorld();
    
    }

    private void createEnemies() {

        // Enemy 1
        Minion one = new Minion(50, 40.0, this);
        
        
        // Enemy 2
        Minion two = new Minion(245.0, 80.0, this);
        
        
        // Enemy 3
        Minion three = new Minion(540.0, 60.0, this);
        
        
        
    }
    
    
    
}
