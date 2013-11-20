/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus;

import zombiegamealpha.data.Game;
import zombiegamealpha.menus.gamesingleplayer.GameSinglePlayer;
/**
 *
 * @author spex
 *
 * Dead Invasion
 *
 * -Single-player
 * -Multi-player
 * -Manage Character
 * -Save
 * -Logout
 *
 */
public class MenuMain extends MenuBase {

    public MenuMain(Game game){

        g = game;
    }

    @Override

    public void set(){

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 5;
        done = false;
        selected = false;
        menuName = "Dead Invasion: " + g.myPlayer.getName();
        menuOptions = new String[amountOfMenuItems];

        menuOptions[0] = "Single-Player -TESTING-";
        menuOptions[1] = "Multi-Player N/A";
        menuOptions[2] = "Manage Character";
        menuOptions[3] = "Save N/A";
        menuOptions[4] = "Logout";
    }

    @Override
    public void doMenuOption(){
        switch(currentSelection){
            case 0:
                System.err.println("Single Player UNDER DEVELOPEMENT");
                GameSinglePlayer gsp = new GameSinglePlayer(g);
                gsp.run();
                break;
            case 1:
                System.err.println("MultiPlayer not yet implemented");
                break;
            case 2:
                System.out.println("Accesing Manage Character menu...");
                MenuManageCharacter mmc = new MenuManageCharacter(g);
                mmc.run();
                break;
            case 3:
                System.err.println("Save Character not yet Implemented");
                break;
            case 4:
                System.out.println("Logging out");
                done = true;
        }
    }
}
