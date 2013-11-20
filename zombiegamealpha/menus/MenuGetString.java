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
public class MenuGetString extends MenuBase {

    String input;

    public MenuGetString(Game game){

        g = game;

        input = "";
    }

    public String getString(String name){

        menuName = name;

        run();

        return input;

    }

    @Override
    public void set(){


        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 2;
        done = false;
        selected = false;
        menuOptions = new String[amountOfMenuItems];

        menuOptions[0] = input;
        menuOptions[1] = "Done";
    }

    public void update(){
        menuOptions[0] = input;
    }

    @Override
    public void doMenuOption(){
        if(currentSelection == amountOfMenuItems - 1){

            done = true;
        }
    }

    @Override
    public void inputMod(){
        if(g.ui.key.getKeyHit()){
            int key = g.ui.key.getKey();
            if((((key >= 65) &&
                    (key <= 90)) ||
                    ((key >= 48) &&
                    (key <= 57))) &&
                    (input.length() < 20)){
                
                input += g.ui.key.getLetter();
            }else if((key == 8) && (input.length() > 0)){
                input = input.substring(0, input.length()-1);
            }
            update();
        }
    }
}
