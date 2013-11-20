/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data;

/**
 *
 * @author spex
 */
import java.io.Serializable;

public class Player implements Serializable {
    private int xp;//The Player's current Xp
    private int xpRequired;//The xp required to level up
    private int level;//The current level
    private int pointsLeft;
    private String name;//The name of te character

    //Initialise the beginning variables
    public Player(){
        xp = 0;
        xpRequired = 100;
        level = 1;
        name = "Bob";
        pointsLeft = 0;
    }

    //Functions to set the data
    public void setXP(int newXP){
        xp = newXP;
    }
    public void setXPRequired(int newXPRequired){
        xpRequired = newXPRequired;
    }
    public void setLevel(int newLevel){
        level = newLevel;
    }
    public void setName(String newName){
        name = newName;
    }
    public void setPointsLeft(int newPoints){
        pointsLeft = newPoints;
    }


    //Functions to get the data
    public int getXP(){
        return xp;
    }
    public int getXPRequired(){
        return xpRequired;
    }
    public int getLevel(){
        return level;
    }
    public String getName(){
        return name;
    }
    public int getPointsLeft(){
        return pointsLeft;
    }


    //Functions to increment the data
    public void incXP(int addXP){
        xp += addXP;
    }
    public void incXPRequired(int addXPRequired){
        xpRequired += addXPRequired;
    }
    public void incLevel(int addLevel){
        level += addLevel;
    }
    public void incPointsLeft(int addPointsLeft){
        pointsLeft += addPointsLeft;
    }
}
