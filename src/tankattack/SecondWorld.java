
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
public class SecondWorld extends World {

    @Override
    public void createInitialSprites() {
        
        createPlayerSprite();
        // Other sprites
        createBossSprite();
        
    }

    @Override
    public void signalEndOfLevel() {

        TankAttack.sharedInstance.transitionFromSecondWorldToThirdWorld();
    
    }
    
    @Override
    public Scene createScene() {
        
        this.setGroup(new Group());
        createInitialSprites();
        
        this.setScene(new Scene(this.group(), TankAttack.gameWidth, TankAttack.gameHeight, Color.PURPLE));
        this.scene().setOnKeyPressed(e -> handleKeyInput(e));
        this.scene().setOnKeyReleased(e -> handleKeyRelease(e));
        return this.scene();
        
    }

    private void createBossSprite() {

        new Boss(TankAttack.gameWidth/2, 80.0, this);
    
    }
    
}
