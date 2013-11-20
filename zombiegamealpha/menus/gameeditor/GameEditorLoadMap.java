/*
 * This class gets and displays all the map files
 * and lets the user pick one to load.
 */

package zombiegamealpha.menus.gameeditor;


import zombiegamealpha.data.Game;
import zombiegamealpha.data.Map;
import zombiegamealpha.menus.MenuBase;

/**
 *
 * @author spex
 */
public class GameEditorLoadMap extends MenuBase{

    public GameEditorLoadMap(Game game){

        g = game;
    }

    @Override

    public void set(){

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;

        String fileNames[] = g.myLoadSaver.getFileNames(".map");

        if(fileNames == null){
            amountOfMenuItems = 1;
        }else{
            amountOfMenuItems = fileNames.length + 1;
        }

        done = false;
        selected = false;
        menuName = "Load Map";
        menuOptions = new String[amountOfMenuItems];


        for(int i = 0; i < amountOfMenuItems; i++){
            if(i == amountOfMenuItems - 1){
                menuOptions[i] = "-- Return --";
            }else{
                menuOptions[i] = fileNames[i];
            }
        }
    }

    @Override
    public void doMenuOption(){
        if(currentSelection == amountOfMenuItems - 1){
            done = true;
        }else{
            g.currentMap = new Map(g.myLoadSaver.loadMap(menuOptions[currentSelection], g));
        }
    }

}
