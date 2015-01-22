
// This entire file is part of my masterpiece.
// Ruslan Ardashev

package tankattack;

import Sprites.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;

/**
 *
 * @author Ruslan
 */
public class FourthWorld extends World {

    @Override
    public void createInitialSprites() {
        
        createPlayerSprite();
        // Other sprites
        createSprites();
        
    }

    @Override
    public void signalEndOfLevel() {

        TankAttack.sharedInstance.displayStartMenu();
    
    }
    
    @Override
    public Scene createScene() {
        
        this.setGroup(new Group());
        createInitialSprites();
        
        this.setScene(new Scene(this.group(), TankAttack.gameWidth, TankAttack.gameHeight, Color.BURLYWOOD));
        this.scene().setOnKeyPressed(e -> handleKeyInput(e));
        this.scene().setOnKeyReleased(e -> handleKeyRelease(e));
        return this.scene();
        
    }

    private void createSprites() {

        // EvilMinion(double x, double y, World world)
        
        //         Enemy 1
        new KamikadzeeMinion(TankAttack.gameWidth/3, 40.0, this);  
        
    }
    
}
