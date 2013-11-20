/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus;

import zombiegamealpha.data.Game;
/**
 *
 * @author spex
 */
public class MenuGetBoolean extends MenuBase{

    public MenuGetBoolean(Game game){
        g = game;
    }

    public boolean getBoolean(String setMenuName, String option1, String option2){

        set();

        menuOptions = new String[2];
        
        menuName = setMenuName;
        menuOptions[0] = option1;
        menuOptions[1] = option2;

        run();

        if(currentSelection == 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void set(){

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 2;
        done = false;
        selected = false;
    }

    @Override
    public void doMenuOption(){
        done = true;
    }

}
