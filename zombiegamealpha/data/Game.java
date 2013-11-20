/*
 *     ====    ========      ====    ========   ===    ===
 *   ========  ==========   ======   ========== ===   ===
 *  ===    === ===    ===   ==  ==   ===    === === ===
 *  ===     == ===    ===  ===  ===  ===    === ======
 *   ====      ==========  ===  ===  ========== ======
 *      =====  ========   ========== ========   === ===
 *  ==     === ===        ========== ===  ===   ===  ===
 *  ===    === ===        ===    === ===   ===  ===   ===
 *   ========  ===        ===    === ===    === ===    ===
 *     ====    ===        ===    === ===    === ===    ===
 *
 *
 *
 * This is the main game that is suppose to hold
 * all data that exists in the game
 */

package zombiegamealpha.data;

import zombiegamealpha.ui.UI;

/**
 *
 * @author spex
 */
public class Game {

    public UI ui;//The USER INTERFACE that is passed between the programs;
    public Map currentMap;//Variable to store the map

    static final int WIDTH = 800;//The pixel width of the screen.
    static final int HEIGHT = 600;//The pixel height of the screen

    public Player myPlayer;//The avatar of the user
    public LoadSaveManager myLoadSaver;//Loads up the load/saver class

    public Game(){

        myLoadSaver = new LoadSaveManager();
        reset();
    }

    public void reset(){
        //CODE TO RESET HERE!!!!

        //Create a new map
        currentMap = new Map(25,25,4);

        //Create new player
        myPlayer = new PlayerHuman();

        
    }
}
