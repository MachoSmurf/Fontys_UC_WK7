package movingballsfx;

import java.util.Random;
import javafx.scene.paint.Color;

/**
 * Ball class
 * original @author Albert Lak
 * manages ball position within bounds, speed and role it fulfills.
 */
public class Ball {

    private int xPos, yPos, speed;
    private int minX, maxX;
    private Role role;
    private int minCsX;
    private int maxCsX;

    public Ball(int minX, int maxX, int minCsX, int maxCsX, int yPos, Role role) {
        this.xPos = minX;
        this.yPos = yPos;
        this.minX = minX;
        this.maxX = maxX;
        this.minCsX = minCsX;
        this.maxCsX = maxCsX;
        this.speed = 10 + (new Random()).nextInt(5);
        this.role = role;
    }

    public void move() {
        xPos++;
        if (xPos > maxX) {
            xPos = minX;
        }
    }

    public int getXPos() {
        return xPos;
    }

   public int getYPos() {
        return yPos;
    }

    public Role getRole(){
        return this.role;
    }
   
//    public Color getColor() {
//        return this.role.getColor();
//    }

    public int getSpeed() {
        return speed;
    }

    public boolean isEnteringCs() {
        return xPos == minCsX;
    }
    
    public boolean isLeavingCs() {
        return xPos == maxCsX;
    }
}


