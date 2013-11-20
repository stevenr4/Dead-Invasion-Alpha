/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus;

import zombiegamealpha.data.Game;
import zombiegamealpha.data.*;

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


public class MenuLogin extends MenuBase {

    public MenuLogin(Game game){

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
        menuName = "Dead Invasion";
        menuOptions = new String[amountOfMenuItems];

        menuOptions[0] = "New Character TESTING";
        menuOptions[1] = "Load Character N/A";
        menuOptions[2] = "Extras";
        menuOptions[3] = "Quit";
    }

    @Override
    public void doMenuOption(){
        switch(currentSelection){
            case 0:
                
                if(newCharacter()){
                    MenuMain mm = new MenuMain(g);
                    mm.run();
                }
//                GameSinglePlayer gsp = new GameSinglePlayer(g);
//                gsp.run();
                break;
            case 1:
                System.err.println("enter code for loading of a character>>>");
                break;
            case 2:
                System.out.println("Accessing 'Extras' menu..");
                MenuExtras me = new MenuExtras(g);
                me.run();
                break;
            case 3:
                System.out.println("Quitting...");
                done = true;
        }
    }

    private boolean newCharacter(){

        MenuGetBoolean mgb = new MenuGetBoolean(g);
        MenuGetString mgs = new MenuGetString(g);
        MenuGetIntegers mgi = new MenuGetIntegers(g);

        if(mgb.getBoolean("Survivor or Zombie Lord?", "Survivor", "Zombie Lord N/A")){
            g.myPlayer = new PlayerHuman();
        }else{
            g.myPlayer = new PlayerZombie();
            return false;
        }

        g.myPlayer.setName(mgs.getString("Enter Username:"));

        if(g.myPlayer.getName().equals("")){
            return false;
        }



        if(g.myPlayer instanceof PlayerHuman){
            int tmpStart[],tmpMin[],tmpMax[],tmpInput[];
            String tmpStats[];

            tmpStart = new int[4];
            tmpMin = new int[4];
            tmpMax = new int[4];
            tmpStats = new String[4];

            for(int i = 0; i < 4; i++){
                tmpStart[i] = 3;
                tmpMin[i] = 3;
                tmpMax[i] = 20;

            }
            PlayerHuman hp = (PlayerHuman)g.myPlayer;

            tmpStats = hp.getAllStatNames();

            mgi.setLetFinish(false);
            tmpInput = mgi.getIntegers(tmpStart, "EnterStats", tmpStats, tmpMin, tmpMax, 15);

            System.arraycopy(tmpInput, 0, tmpStart, 0, 4);

            hp.setAllStats(tmpStart);
            hp.setPointsLeft(tmpInput[tmpInput.length - 1]);

        }

        if(mgb.getBoolean(g.myPlayer.getName() + " Okay?", "Yes", "Cancel")){
            return true;
        }else{
            return false;
        }
    }

}
