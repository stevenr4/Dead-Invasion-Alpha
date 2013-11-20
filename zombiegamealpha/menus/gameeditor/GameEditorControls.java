
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus.gameeditor;

import zombiegamealpha.data.Game;
import zombiegamealpha.menus.MenuBase;
import zombiegamealpha.ui.PrintManagerVariables;
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


public class GameEditorControls extends MenuBase {

    public GameEditorControls(Game game){

        g = game;
    }

    @Override
    public void set(){

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 9;
        done = false;
        selected = false;
        menuName = "Editor Controls Help";
        menuOptions = new String[amountOfMenuItems];

        menuOptions[0] = "Left,Right:Switch graphic/physics";
        menuOptions[1] = "Up,Down:Switch object selection";
        menuOptions[2] = "X: Erase Mode ";
        menuOptions[3] = "W: Toggle Walls";
        menuOptions[4] = "P: Toggle Physics";
        menuOptions[5] = "O,L: UP/DOWN floors";
        menuOptions[6] = "Left Click: Paint Selection";
        menuOptions[7] = "Right Click: Drag Screen Around";
        menuOptions[8] = "Return";


    }



    @Override
    public void print(){

        PrintManagerVariables pmv = new PrintManagerVariables();
        pmv.setScreenXYZ(((g.currentMap.getXSize() - g.currentMap.getYSize()) * 40 / 2)/2,
                ((g.currentMap.getXSize() + g.currentMap.getYSize()) * 20 / 2)/2, 1);
        pmv.setMap(g.currentMap);



        g.ui.myPrinter.cls();
        g.ui.myPrinter.printMap(pmv);
        g.ui.myPrinter.fade();
        
        
        
        if(amountOfMenuItems < MAXITEMS){


            //Normally print the menu
            g.ui.myPrinter.printMenu(menuName, menuOptions, currentSelection, itemYSize);
        }else{

            String menuOutput[] = new String[MAXITEMS];

            for(int i = 0; i < MAXITEMS; i++){
                menuOutput[i] = menuOptions[i + menuScroll];
            }

            if(menuScroll > 0){
                menuOutput[0] = "^^^^^";
            }
            if(menuScroll + MAXITEMS < amountOfMenuItems){
                menuOutput[MAXITEMS - 1] = "VVVVV";
            }

            g.ui.myPrinter.printMenu(menuName, menuOutput, currentSelection, itemYSize);
        }

        g.ui.repaint();
    }

    @Override
    public void doMenuOption(){
        switch(currentSelection + menuScroll){
            case 8:
                System.out.println("returning...");
                done = true;
        }
    }

}
