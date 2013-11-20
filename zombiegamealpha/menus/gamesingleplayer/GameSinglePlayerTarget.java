/*
 * This is a seperate menu because the targeting is so different from
 * the normal input that i decided to make it's own seperate section.
 *
 */

package zombiegamealpha.menus.gamesingleplayer;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import zombiegamealpha.data.Game;
import zombiegamealpha.data.PlayerHuman;
import zombiegamealpha.menus.MenuBase;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.ui.PrintManagerVariables;
/**
 *
 * @author spex
 */
public class GameSinglePlayerTarget extends MenuBase {

    private int curserX;//The X location of the curser
    private int curserY;//The Y location of the curser

    private int screenX;//The Screen's X location when looking at the map
    private int screenY;//The Screen's Y location when looking at the map
    private int screenZ;//What floor the screen is currently looking at

    private int lastX;//The last X position of the mouse
    private int lastY;//The last Y position of the mouse

    private Avatar myAvatar;

    //Setting the variables
    public GameSinglePlayerTarget(Game game){
        g = game;
    }

    //Return the curser's target when clicked
    public int[] getCurserTarget(int screenXInput, int screenYInput, int screenZInput, Avatar newMyAvatar){
        screenX = screenXInput;
        screenY = screenYInput;
        screenZ = screenZInput;

        myAvatar = newMyAvatar;

        run();

        int[] returnXYZ = new int[3];

        returnXYZ[0] = curserX;
        returnXYZ[1] = curserY;
        returnXYZ[2] = screenZ;

        return returnXYZ;
    }

    @Override
    public void set(){

        curserX = 0;
        curserY = 0;

        lastX = g.ui.mouse.getX();
        lastY = g.ui.mouse.getY();
    }

    @Override
    public void print(){


        PrintManagerVariables pmv = new PrintManagerVariables();
        pmv.setCurserXY(curserX, curserY);
        pmv.setMap(g.currentMap);
        pmv.setScreenXYZ(screenX, screenY, screenZ);
        pmv.setZombies(g.currentMap.getZombieAvatars());
        pmv.setPrintAvatarHealth(true);


        g.ui.myPrinter.printSeen(pmv);
        g.ui.myPrinter.fade();
        g.ui.myPrinter.printVisible(pmv);
        g.ui.myPrinter.printSinglePlayerHud(myAvatar, (PlayerHuman)g.myPlayer);
        g.ui.myPrinter.printText(40, 200, "TARGET MODE: Click on target...");
        g.ui.repaint();
    }

    @Override
    public void input(){

        if(g.ui.key.keyHit()){

            if (g.ui.key.getKey() == KeyEvent.VK_ESCAPE) {

                done = true;
                curserX = -1;
                curserY = -1;
            }
        }

        if((g.ui.mouse.clicked()) && (g.ui.mouse.getMouseButton() == MouseEvent.BUTTON1)){
            done = true;
        }

        if((g.ui.mouse.moved()) && (done != true)){


            if(g.ui.mouse.dragged()){

                if(g.ui.mouse.getMouseButton() == MouseEvent.BUTTON1){

                }else{
                    screenX += lastX - g.ui.mouse.getX();
                    screenY += lastY - g.ui.mouse.getY();

                }
                lastX = g.ui.mouse.getX();
                lastY = g.ui.mouse.getY();
            }

            curserX = ((g.ui.mouse.getX() + screenX) + (g.ui.mouse.getY() + screenY) * 2)/80;
            curserY = ((g.ui.mouse.getY() + screenY) - (g.ui.mouse.getX() + screenX)/2)/40;
        }
    }
}
