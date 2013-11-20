/*
 * The menu that pops up when you click a hand item.
 */

package zombiegamealpha.menus.gamesingleplayer;

import zombiegamealpha.data.Game;
import zombiegamealpha.data.PlayerHuman;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.menus.MenuBase;
import zombiegamealpha.ui.PrintManagerVariables;
import zombiegamealpha.data.items.*;
/**
 *
 * @author spex
 */

/*
 * - Pause -
 *
 * -
 *
 * (GUN)
 * -Quick Shot
 * -Aimed Shot
 * -Reload
 *
 * (BLADE)
 * -Stab
 *
 * (BLUNT)
 * -Hit
 * 
 * (HEALING)
 * -Heal Self
 * -Heal Other
 *
 * (ALL)
 * -Throw
 * -Drop
 * -Cancel
 */
public class GameSinglePlayerHandClicked extends MenuBase {

    private int screenX, screenY, screenZ;
    private Avatar myAvatar;
    private boolean leftHand;
    private Item item;

    public static final int QUICKSHOT = 0;
    public static final int AIMEDSHOT = 1;
    public static final int RELOAD = 2;
    public static final int MELEE = 3;
    public static final int HEALOTHER = 4;
    public static final int HEALSELF = 5;
    public static final int DROP = 6;
    public static final int THROW = 7;
    public static final int CANCEL = 8;



    public GameSinglePlayerHandClicked(Game game, boolean setLeftHand, int x, int y, int z, Avatar newAvatar){
        g = game;
        screenX = x;
        screenY = y;
        screenZ = z;
        myAvatar = newAvatar;
        leftHand = setLeftHand;

    }

    public int getAction(){

        //Do the item's Actions
        if(item instanceof Projectile){
            switch(currentSelection){
                case 0://Quick Shot
                    return QUICKSHOT;
                case 1://Aimed Shot
                    return AIMEDSHOT;
                case 2://Reload
                    return RELOAD;
            }
        }else if(item instanceof Melee){
            if(currentSelection == 0){

                System.err.println("RETURNING MELEE!!");
                return MELEE;
            }
        }else if(item instanceof Healing){
            if(currentSelection == 0){
                return HEALSELF;
            }else if(currentSelection == 1){
                return HEALOTHER;
            }
        }


        //Do the generic actions
        if(currentSelection == amountOfMenuItems - 3){
            return THROW;
        }else if(currentSelection == amountOfMenuItems - 2){
            return DROP;
        }else{
            return CANCEL;
        }

    }

    @Override
    public void set(){
        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;



        //Get the item from the hand
        if(leftHand){
            item = ((PlayerHuman)g.myPlayer).getLeftHand();
        }else{
            item = ((PlayerHuman)g.myPlayer).getRightHand();
        }

        amountOfMenuItems = 3;
        //If the item is a useable
        if(item instanceof UseAble){
            //If the item is a weapon
            if(item instanceof Weapon){
                //If the item is a gun
                if(item instanceof Projectile){
                    amountOfMenuItems = 6;
                }else if(item instanceof Melee){
                    amountOfMenuItems = 4;
                }
            }else if(item instanceof Healing){
                amountOfMenuItems = 5;
            }
        }
        
        done = false;
        selected = false;
        menuName = item.getName();
        menuOptions = new String[amountOfMenuItems];



        //SET THE MENU ITEMS

        //If the item is a useable
        if(item instanceof UseAble){
            //If the item is a weapon
            if(item instanceof Weapon){
                //If the item is a gun
                if(item instanceof Projectile){
                    menuOptions[0] = "Quick Shot:" + (((Projectile)item).getTimeUnits() * 0.5);
                    menuOptions[1] = "Aimed Shot:" + (((Projectile)item).getTimeUnits());
                    menuOptions[2] = "Reload:" + (((Projectile)item).getReloadingTimeUnits());
                }else if(item instanceof Melee){
                    if(((Melee)item).getBlade()){
                        menuOptions[0] = "Stab:" + (((Melee)item).getTimeUnits());
                    }else{
                        menuOptions[0] = "Hit:" + (((Melee)item).getTimeUnits());
                    }

                }
            }else if(item instanceof Healing){
                menuOptions[0] = "Heal Self:" + (((Healing)item).getTimeUnits());
                menuOptions[1] = "Heal Other:" + (((Healing)item).getTimeUnits());
            }
        }
        menuOptions[amountOfMenuItems - 3] = "Throw:2";
        menuOptions[amountOfMenuItems - 2] = "Drop:1";
        menuOptions[amountOfMenuItems - 1] = "Cancel";
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
        if(leftHand){
            g.ui.myPrinter.printItemActionMenu(menuName, menuOptions, currentSelection, 34, g.ui.myHEIGHT - 160);
        }else{
            g.ui.myPrinter.printItemActionMenu(menuName, menuOptions, currentSelection, 264, g.ui.myHEIGHT - 160);
        }
        g.ui.repaint();
    }

    @Override
    public void input(){

        inputMod();

        if(g.ui.mouse.moved()){
            currentSelection = (g.ui.mouse.getY() - (g.ui.myHEIGHT - 167))/20;

            if(currentSelection < 0){
                currentSelection = 0;
            }else if(currentSelection >= amountOfMenuItems){
                currentSelection = amountOfMenuItems - 1;
            }else if(currentSelection > MAXITEMS - 1) {
                currentSelection = MAXITEMS - 1;
            }

        }

        if(g.ui.mouse.clicked()){
            selected = true;
        }

        if(g.ui.key.keyHit()){
            if(g.ui.key.getKey() == java.awt.event.KeyEvent.VK_UP){
                if(currentSelection > 0){
                    currentSelection--;
                }
            }else if(g.ui.key.getKey() == java.awt.event.KeyEvent.VK_DOWN){
                if((currentSelection < amountOfMenuItems - 1) &&
                (currentSelection < MAXITEMS - 1)){
                    currentSelection++;
                }
            }else if(g.ui.key.getKey() == java.awt.event.KeyEvent.VK_ENTER){
                selected = true;
            }
        }
    }

    @Override
    public void doMenuOption(){
        done = true;

    }
}
