/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.ui;
import zombiegamealpha.data.Map;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.data.AvatarManager;

/**
 *
 * @author spex
 */
public class PrintManagerVariables {

    public boolean walls;//If we should print the walls

    public int screenX;//The X, Y, and Z of the screen's location
    public int screenY;
    public int screenZ;

    public boolean cursor;//If we should print the cursor
    public int cursorX, cursorY;//The X and Y coords of the cursor
    public int cursorType;

    public Map currentMap;

    public boolean printAvatar;//If we should print the avatar
    public Avatar myAvatar;//The single avatar

    public boolean printZombies;//If we should print the zombies
    public AvatarManager zombies;//An array of zombies

    public boolean printHumans;//If we should print the humans
    public AvatarManager humans;//The humans
    
    public boolean printAvatarHealth;//If we should print the health bars

    static public final int CURSER_NORMAL = 0;//The type of the cursors
    static public final int CURSER_TARGET = 1;



    //Set the default variables
    public PrintManagerVariables(){

        walls = true;
        cursor = false;
        screenX = 0;
        screenY = 0;
        screenZ = 0;
        cursorX = -1;
        cursorY = -1;
        cursorType = CURSER_NORMAL;
        currentMap = new Map();
        printAvatar = false;
        myAvatar = new Avatar();
        printAvatarHealth = false;

        printZombies = false;
        zombies = new AvatarManager();
    }

    //One function to set the XYZ
    public void setScreenXYZ(int x, int y, int z){
        screenX = x;
        screenY = y;
        screenZ = z;
    }

    //One function to set the curser XY
    public void setCurserXY(int x, int y){
        cursorX = x;
        cursorY = y;

        cursor = true;
    }

    //One function to set the curser type
    public void setCurserType(int newCurser){
        cursorType = newCurser;
    }

    //Setting the walls  
    public void setWalls(boolean newWalls){
        walls = newWalls;
    }

    //Setting the map
    public void setMap(Map newMap){
        currentMap = new Map(newMap);
    }


    public void setAvatar(Avatar newAvatar){
        myAvatar = newAvatar;

        printAvatar = true;
    }

    public void setZombies(AvatarManager newAM){
        zombies = new AvatarManager(newAM);

        printZombies = true;
    }

    public void setHumans(AvatarManager newAM){
        humans = new AvatarManager(newAM);

        printHumans = true;
    }

    public void setPrintAvatarHealth(boolean newPAH){
        printAvatarHealth = newPAH;
    }

}
