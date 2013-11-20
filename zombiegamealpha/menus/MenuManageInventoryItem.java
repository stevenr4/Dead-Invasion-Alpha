/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus;

import zombiegamealpha.data.Game;
import zombiegamealpha.data.PlayerHuman;
import zombiegamealpha.data.items.*;
/**
 *
 * @author spex
 *
 * [location] : [itemName] [xAmount]
 *
 * -Equip/UnEquip/Reload (for ARMOR and PROJECTILE ONLY)
 * -Put in hand
 * -put in Backpack
 * -Drop
 * -Cancel
 *
 */
public class MenuManageInventoryItem extends MenuBase {

    public static final int LOC_HEAD = 0;
    public static final int LOC_TORSO = 1;
    public static final int LOC_PANTS = 2;
    public static final int LOC_LEFTHAND = 3;
    public static final int LOC_RIGHTHAND = 4;
    public static final int LOC_BACKPACK = 5;

    public static final int ACT_CANCLE = 0;
    public static final int ACT_HAND = 1;
    public static final int ACT_ARMOR = 2;
    public static final int ACT_SWITCHHAND = 3;
    public static final int ACT_REPLACEHAND = 4;
    public static final int ACT_REPLACEARMOR = 5;
    public static final int ACT_RELOAD = 6;

    private int action;
    private int itemLocation;
    private int itemBackpackLocation;

    Item item;


    public MenuManageInventoryItem(Game game){

        g = game;

    }

    public void setItemVariables(Item inputItem, int inputItemLocation, int inputItemBackpackLocation){

        item = inputItem;
        itemLocation = inputItemLocation;
        itemBackpackLocation = inputItemBackpackLocation;
    }

    public int getAction(){
        return action;
    }

    @Override

    public void set(){

        if(item == null){
            done = true;
            selected = true;
            return;
        }

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        done = false;
        selected = false;
        
        String itemLocationName = "BROKENLOC";
        switch(itemLocation){
            case LOC_HEAD:
                itemLocationName = "Head";
                break;
            case LOC_TORSO:
                itemLocationName = "Torso";
                break;
            case LOC_PANTS:
                itemLocationName = "Pants";
                break;
            case LOC_LEFTHAND:
                itemLocationName = "Left Hand";
                break;
            case LOC_RIGHTHAND:
                itemLocationName = "Right Hand";
                break;
            case LOC_BACKPACK:
                itemLocationName = "Backpack";
                break;
        }
        
        menuName = itemLocationName + ": " + item.getName();
        
        
        if((item instanceof Armor) ||
                (item instanceof Projectile)){
            amountOfMenuItems = 5;

        }else{
            amountOfMenuItems = 4;
        }
        menuOptions = new String[amountOfMenuItems];

        //Special Thing
        if(item instanceof Armor){
            if((itemLocation == 0) ||
                    (itemLocation == 1) ||
                    (itemLocation == 2)){
                menuOptions[0] = "Un-Equip";
            }else{
                menuOptions[0] = "Equip";
            }
        }else if(item instanceof Projectile){
            menuOptions[0] = "Reload N/A";
        }

        menuOptions[amountOfMenuItems - 4] = "Put in Hand";
        menuOptions[amountOfMenuItems - 3] = "Put in Backpack";
        menuOptions[amountOfMenuItems - 2] = "Throw Away";
        menuOptions[amountOfMenuItems - 1] = "Cancel";

    }

    @Override
    public void doMenuOption(){
        PlayerHuman ph = (PlayerHuman)g.myPlayer;

        if((currentSelection == 0) &&
                (item instanceof Armor)){
            Armor a = (Armor)item;
            System.out.println("ITEM IS ARMOR");
            switch(itemLocation){
                case LOC_HEAD:
                    ph.getInventory().addItem(ph.getHead());
                    ph.setHead(null);
                    break;
                case LOC_TORSO:
                    ph.getInventory().addItem(ph.getTorso());
                    ph.setTorso(null);
                    break;
                case LOC_PANTS:
                    ph.getInventory().addItem(ph.getPants());
                    ph.setPants(null);
                    break;
                case LOC_LEFTHAND:
                    switch(a.getType()){
                        case Armor.HEAD:
                            if(ph.getHead() == null){
                                ph.setHead(a);
                                ph.setLeftHand(null);
                            }else{
                                ph.setLeftHand(ph.getHead());
                                ph.setHead(a);
                            }
                            break;
                        case Armor.TORSO:
                            if(ph.getTorso() == null){
                                ph.setTorso(a);
                                ph.setLeftHand(null);
                            }else{
                                ph.setLeftHand(ph.getTorso());
                                ph.setTorso(a);
                            }
                            break;
                        case Armor.PANTS:
                            if(ph.getPants() == null){
                                ph.setPants(a);
                                ph.setLeftHand(null);
                            }else{
                                ph.setLeftHand(ph.getPants());
                                ph.setPants(a);
                            }
                            break;
                    }
                    break;
                case LOC_RIGHTHAND:
                    switch(a.getType()){
                        case Armor.HEAD:
                            if(ph.getHead() == null){
                                ph.setHead(a);
                                ph.setRightHand(null);
                            }else{
                                ph.setRightHand(ph.getHead());
                                ph.setHead(a);
                            }
                            break;
                        case Armor.TORSO:
                            if(ph.getTorso() == null){
                                ph.setTorso(a);
                                ph.setRightHand(null);
                            }else{
                                ph.setRightHand(ph.getTorso());
                                ph.setTorso(a);
                            }
                            break;
                        case Armor.PANTS:
                            if(ph.getPants() == null){
                                ph.setPants(a);
                                ph.setRightHand(null);
                            }else{
                                ph.setRightHand(ph.getPants());
                                ph.setPants(a);
                            }
                            break;
                    }
                    break;
                case LOC_BACKPACK:
                    switch(a.getType()){
                        case Armor.HEAD:
                            if(ph.getHead() == null){
                                ph.setHead(a);
                                ph.getInventory().takeOutItem(itemBackpackLocation);
                            }else{
                                ph.getInventory().takeOutItem(itemBackpackLocation);
                                ph.getInventory().addItem(ph.getHead());
                                ph.setHead(a);
                            }
                            break;
                        case Armor.TORSO:
                            if(ph.getTorso() == null){
                                ph.setTorso(a);
                                ph.getInventory().takeOutItem(itemBackpackLocation);
                            }else{
                                ph.getInventory().takeOutItem(itemBackpackLocation);
                                ph.getInventory().addItem(ph.getTorso());
                                ph.setTorso(a);
                            }
                            break;
                        case Armor.PANTS:
                            if(ph.getPants() == null){
                                ph.setPants(a);
                                ph.getInventory().takeOutItem(itemBackpackLocation);
                            }else{
                                ph.getInventory().takeOutItem(itemBackpackLocation);
                                ph.getInventory().addItem(ph.getPants());
                                ph.setPants(a);
                            }
                            break;
                    }
            }
        }else if((currentSelection == 0) &&
                (item instanceof Projectile)){


            System.err.println("RELOADING STILL NOT YET IMPLEMENTED,SAVING FOR LAST");////////////////////////////////////





        }else if(currentSelection == amountOfMenuItems - 4){//PUT IN HAND

            MenuGetBoolean mgb = new MenuGetBoolean(g);

            boolean leftHand = mgb.getBoolean("Which Hand?", "Left hand", "Right hand");


            //PUT THE LEFT HAND IN THE LEFT HAND, DOES NOTHING!
            if((leftHand) &&
                    (itemLocation == LOC_LEFTHAND)){
                done = true;
                return;
            }else if((!leftHand) &&
                    (itemLocation == LOC_RIGHTHAND)){
                done = true;
                return;////////////////////////////////////////////////////////////////////// << ADD SELECTION TYPE
            }

            if(leftHand){
                if((ph.getLeftHand() != null) &&
                        (itemLocation != LOC_RIGHTHAND)){
                    ph.getInventory().addItem(ph.getLeftHand());
                }
            }else{
                if((ph.getRightHand() != null) &&
                        (itemLocation != LOC_LEFTHAND)){
                    ph.getInventory().addItem(ph.getRightHand());
                }
            }

            switch(itemLocation){
                case LOC_HEAD:
                    if(leftHand){
                        ph.setLeftHand(ph.getHead());
                    }else{
                        ph.setRightHand(ph.getHead());
                    }
                    ph.setHead(null);
                    break;
                case LOC_TORSO:
                    if(leftHand){
                        ph.setLeftHand(ph.getTorso());
                    }else{
                        ph.setRightHand(ph.getTorso());
                    }
                    ph.setTorso(null);
                    break;
                case LOC_PANTS:
                    if(leftHand){
                        ph.setLeftHand(ph.getPants());
                    }else{
                        ph.setRightHand(ph.getPants());
                    }
                    ph.setPants(null);
                    break;
                case LOC_LEFTHAND:
                    if(!leftHand){
                        ph.setLeftHand(ph.getRightHand());
                        ph.setRightHand(item);
                    }
                    break;
                case LOC_RIGHTHAND:
                    if(leftHand){
                        ph.setRightHand(ph.getLeftHand());
                        ph.setLeftHand(item);
                    }
                    break;
                case LOC_BACKPACK:
                    if(leftHand){
                        ph.setLeftHand(ph.getInventory().getItem(itemBackpackLocation));
                    }else{
                        ph.setRightHand(ph.getInventory().getItem(itemBackpackLocation));
                    }
                    ph.getInventory().takeOutItem(itemBackpackLocation);
                    break;
            }
        }else if(currentSelection == amountOfMenuItems - 3){//PUT IN BACKPACK

            switch(itemLocation){
                case LOC_HEAD:
                    ph.getInventory().addItem(ph.getHead());
                    ph.setHead(null);
                    break;
                case LOC_TORSO:
                    ph.getInventory().addItem(ph.getTorso());
                    ph.setTorso(null);
                    break;
                case LOC_PANTS:
                    ph.getInventory().addItem(ph.getPants());
                    ph.setPants(null);
                    break;
                case LOC_LEFTHAND:
                    ph.getInventory().addItem(ph.getLeftHand());
                    ph.setLeftHand(null);
                    break;
                case LOC_RIGHTHAND:
                    ph.getInventory().addItem(ph.getRightHand());
                    ph.setRightHand(null);
                    break;
                case LOC_BACKPACK:
                    //DO NOTHING
                    break;
            }
        }else if(currentSelection == amountOfMenuItems - 2){//Throw Away

            MenuGetBoolean mgb = new MenuGetBoolean(g);
            boolean decision = mgb.getBoolean("Throw away " + item.getName() + "?", "No", "Yes");

            if(decision == false){
                switch(itemLocation){
                    case LOC_HEAD:
                        ph.setHead(null);
                        break;
                    case LOC_TORSO:
                        ph.setTorso(null);
                        break;
                    case LOC_PANTS:
                        ph.setPants(null);
                        break;
                    case LOC_LEFTHAND:
                        ph.setLeftHand(null);
                        break;
                    case LOC_RIGHTHAND:
                        ph.setRightHand(null);
                        break;
                    case LOC_BACKPACK:
                        ph.getInventory().takeOutItem(itemBackpackLocation);
                        break;
                }

            }
        }else if(currentSelection == amountOfMenuItems - 1){//CANCEL
            done = true;
        }
        done = true;
        
        
    }
}
