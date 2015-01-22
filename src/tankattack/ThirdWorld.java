
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
public class ThirdWorld extends World {

    @Override
    public void createInitialSprites() {
        
        createPlayerSprite();
        // Other sprites
        createEvilMinionSprites();
        
    }

    @Override
    public void signalEndOfLevel() {

        TankAttack.sharedInstance.transitionFromThirdWorldToFourthWorld();
    
    }
    
    @Override
    public Scene createScene() {
        
        this.setGroup(new Group());
        createInitialSprites();
        
        this.setScene(new Scene(this.group(), TankAttack.gameWidth, TankAttack.gameHeight, Color.SEAGREEN));
        this.scene().setOnKeyPressed(e -> handleKeyInput(e));
        this.scene().setOnKeyReleased(e -> handleKeyRelease(e));
        return this.scene();
        
    }

    private void createEvilMinionSprites() {

        // EvilMinion(double x, double y, World world, double leftXLimit, double rightXLimit, boolean goingRight)
        
        //         Enemy 1
        EvilMinion one = new EvilMinion(50.0, 40.0, this, 30.0, 350.0, true);
        
        //         Enemy 2
        EvilMinion two = new EvilMinion(100.0, 60.0, this, 330.0, 540.0, true);
    
    }
    
}
