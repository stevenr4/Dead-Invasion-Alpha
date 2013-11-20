/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus;

import zombiegamealpha.data.Game;
/**
 *
 * @author spex
 *
 * -Inventory
 * -Shop
 * -Stats
 * -Back
 */
public class MenuManageCharacter extends MenuBase {

    public MenuManageCharacter(Game game){

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
        menuName = "Manage Character: " + g.myPlayer.getName();
        menuOptions = new String[amountOfMenuItems];

        menuOptions[0] = "Inventory [under developement]";
        menuOptions[1] = "Shop N/A";
        menuOptions[2] = "Stats N/A";
        menuOptions[3] = "Back";
    }

    @Override
    public void doMenuOption(){
        switch(currentSelection){
            case 0:
                System.err.println("Inventory managing is not done yet");
                MenuManageInventory mmi = new MenuManageInventory(g);
                mmi.run();
                break;
            case 1:
                System.err.println("Shop not implemented yet");
                break;
            case 2:
                System.out.println("Stats not implemented yet");
                break;
            case 3:
                System.out.println("exiting 'Manage Character' menu....");
                done = true;
        }
    }
}
