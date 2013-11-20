/*
 *
 * The pause menu in the singleplayer game.
 *
 * 
 */

package zombiegamealpha.menus.gamesingleplayer;

import zombiegamealpha.data.Game;
import zombiegamealpha.menus.MenuBase;
import zombiegamealpha.ui.PrintManagerVariables;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.data.PlayerHuman;
/**
 *
 * @author spex
 */

public class GameSinglePlayerPause extends MenuBase {

    private int screenX, screenY, screenZ;
    private Avatar myAvatar;


    public GameSinglePlayerPause(Game game, int x, int y, int z, Avatar newMyAvatar){
        g = game;
        screenX = x;
        screenY = y;
        screenZ = z;
        myAvatar = newMyAvatar;

    }

    @Override
    public void set(){
        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 4;
        done = false;
        selected = false;
        menuName = "Pause Menu";
        menuOptions = new String[amountOfMenuItems];

        menuOptions[0] = "ReservedN/A";
        menuOptions[1] = "Stats N/A";
        menuOptions[2] = "Resume";
        menuOptions[3] = "Give up";
    }


    @Override
    public void print(){


        g.ui.myPrinter.cls();


        g.currentMap.setVisibleAll(false);

        //For all of the avatars...
        for(Avatar a : g.currentMap.getHumanAvatars().getAllAvatars()){

            g.currentMap.addVisible(a, true);
        }

        //Add the visible for our main avatar
        g.currentMap.addVisible(myAvatar, true);


        PrintManagerVariables pmv = new PrintManagerVariables();
        pmv.setMap(g.currentMap);
        pmv.setScreenXYZ(screenX, screenY, screenZ);
        pmv.setZombies(g.currentMap.getZombieAvatars());
        pmv.setPrintAvatarHealth(true);


        g.ui.myPrinter.printVisible(pmv);
        g.ui.myPrinter.printSinglePlayerHud(myAvatar, (PlayerHuman)g.myPlayer);
        g.ui.myPrinter.fade();
        g.ui.myPrinter.printMenu(menuName, menuOptions, currentSelection, itemYSize);

        g.ui.repaint();
    }

    @Override
    public void doMenuOption(){
        switch(currentSelection){
            case 0:
                System.err.println("RESERVED MENU SPOT");
                break;
            case 1:
                System.err.println("The stats not set up yet");
                break;
            case 2:
                done = true;
                break;
            case 3:
                done = true;
                break;
        }
    }
}
