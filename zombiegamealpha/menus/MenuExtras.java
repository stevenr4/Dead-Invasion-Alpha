/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus;

import zombiegamealpha.menus.gameeditor.GameEditor;
import zombiegamealpha.data.Game;
/**
 *
 * @author spex
 */

/*
 * MAIN MENU
 *
 * NEW
 * LOAD
 * EXTRAS
 * QUIT
 *
 */


public class MenuExtras extends MenuBase {

    public MenuExtras(Game game){

        g = game;
    }

    @Override
    public void set(){

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 4;
        done = false;
        selected = false;
        menuName = "Extras";
        menuOptions = new String[amountOfMenuItems];

        menuOptions[0] = "Options N/A";
        menuOptions[1] = "Map Editor -UNDER TESTING-";
        menuOptions[2] = "Credits N/A";
        menuOptions[3] = "Return";
    }

    @Override
    public void doMenuOption(){
        switch(currentSelection){
            case 0:
                System.err.println("Options not yet implemented");
                break;
            case 1:
                System.err.println("MapEditor still in testing....");
                GameEditor ge = new  GameEditor(g);

                ge.run();
                
                break;
            case 2:
                System.err.println("Credits not yet implemented");
                break;
            case 3:
                System.out.println("returning...");
                done = true;
        }
    }

}
