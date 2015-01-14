/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankattack;

/**
 *
 * @author Ruslan
 */
public class DirController {
    
    public static boolean upPressed, downPressed, leftPressed, rightPressed;
    public static boolean spacePressed;
    
    // Improvements in speed. No new objects @ 60Hz
    public static double[] returnArray;    

    public static double[] getNewXY(double[] currPosition, double itemWidth, double itemHeight, double multiplier) {
        
        // Always returns double[2], array[0] = x, array[1]
                
        if (returnArray == null) {
            
            returnArray = new double[2];
            
        }

        double[] returnArray = new double[2];
        
        if (!upPressed && !downPressed && !leftPressed && !rightPressed) {
            // 0000
            returnArray[0] = 0.0;
            returnArray[1] = 0.0;
        }
        
        else if (!upPressed && !downPressed && !leftPressed && rightPressed) {
            // 0001
            returnArray[0] = 1.0;
            returnArray[1] = 0.0;
        }
        
        else if (!upPressed && !downPressed && leftPressed && !rightPressed) {
            // 0010
            returnArray[0] = -1.0;
            returnArray[1] = 0.0;
        }
        
        else if (!upPressed && !downPressed && leftPressed && rightPressed) {
            // 0011
            returnArray[0] = 0.0;
            returnArray[1] = 0.0;
        }
        
        else if (!upPressed && downPressed && !leftPressed && !rightPressed) {
            // 0100
            returnArray[0] = 0.0;
            returnArray[1] = 1.0;
        }
        
        else if (!upPressed && downPressed && !leftPressed && rightPressed) {
            // 0101
            returnArray[0] = 0.70710678118;
            returnArray[1] = 0.70710678118;
        }
        
        else if (!upPressed && downPressed && leftPressed && !rightPressed) {
            // 0110
            returnArray[0] = -0.70710678118;
            returnArray[1] = 0.70710678118;
        }
        
        else if (!upPressed && downPressed && leftPressed && rightPressed) {
            // 0111
            returnArray[0] = 0.0;
            returnArray[1] = 0.70710678118;
        }
        
        else if (upPressed && !downPressed && !leftPressed && !rightPressed) {
            // 1000
            returnArray[0] = 0.0;
            returnArray[1] = -1.0;
        }
        
        else if (upPressed && !downPressed && !leftPressed && rightPressed) {
            // 1001
            returnArray[0] =  0.70710678118;
            returnArray[1] = -0.70710678118;
        }
        
        else if (upPressed && !downPressed && leftPressed && !rightPressed) {
            // 1010
            returnArray[0] = -0.70710678118;
            returnArray[1] = -0.70710678118;
        }
        
        else if (upPressed && !downPressed && leftPressed && rightPressed) {
            // 1011
            returnArray[0] = 0.0;
            returnArray[1] = -1.0;
        }
        
        else if (upPressed && downPressed && !leftPressed && !rightPressed) {
            // 1100
            returnArray[0] = 0.0;
            returnArray[1] = 0.0;
        }
        
        else if (upPressed && downPressed && !leftPressed && rightPressed) {
            // 1101
            returnArray[0] = 1.0;
            returnArray[1] = 0.0;
        }
        
        else if (upPressed && downPressed && leftPressed && !rightPressed) {
            // 1110
            returnArray[0] = -1.0;
            returnArray[1] =  0.0;
        }
        
        else if (upPressed && downPressed && leftPressed && rightPressed) {
            // 1111
            returnArray[0] = 0.0;
            returnArray[1] = 0.0;
        }
        
        // Speed
        returnArray[0] = returnArray[0] * multiplier;
        returnArray[1] = returnArray[1] * multiplier;
        
//        System.out.println("Before checkBounds, up change: "+returnArray[1] +", horiz change: "+returnArray[0]);
        
        returnArray = DirController.checkBounds(returnArray, currPosition, itemWidth, itemHeight);
        
        return returnArray;
        
    }
    
    public static double[] checkBounds(double[] increments, double[] currPosition, double itemWidth, double itemHeight) {
                
//        System.out.println("increments: x"+increments[0]+", y: "+increments[1]);
        
        // Up Boundary
        if (currPosition[1] + increments[1] <= 0) {
            
            increments[1] = 0;
            
        }
        
        // Down Boundary
        if (currPosition[1] + increments[1] + itemHeight >= TankAttack.gameHeight) {
            
            increments[1] = 0;
            
        }
        
        // Left Boundary
        if (currPosition[0] + increments[0] <= 0) {
            
            increments[0] = 0;
            
        }
        
        // Right Boundary
        if (currPosition[0] + increments[0] + itemWidth >= TankAttack.gameWidth) {
            
            increments[0] = 0;
            
        }
        
//        System.out.println("increments: x"+increments[0]+", y: "+increments[1]);
        
        // Appropriately update
        currPosition[0] = currPosition[0] + increments[0];
        currPosition[1] = currPosition[1] + increments[1];
        
//        System.out.println("after change, y change: "+currPosition[1]+", x change: "+currPosition[0]);
        
        return currPosition;
        
    }
    
}
