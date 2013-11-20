/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus;

import zombiegamealpha.data.Game;
import zombiegamealpha.data.PlayerHuman;
/**
 *
 * @author spex
 *
 * 0 - Head:-something
 * 1 - Torso:-something
 * 2 - Pants:-something
 * 3 - LeftHand:-something
 * 4 - RightHand:-something
 * 5 - -- BackPack --
 * -something
 * -something
 */
public class MenuManageInventory extends MenuBase {


    private final int statXLoc = 430;
    private final int statYLoc = 300;


    public MenuManageInventory(Game game){

        g = game;
    }

    @Override

    public void set(){

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        
        if(!(g.myPlayer instanceof PlayerHuman)){
            System.err.println("SENT A ZOMBIE TO A HUMAN MENU!");
            return;

        }
        PlayerHuman myHuman = (PlayerHuman)g.myPlayer;

        //+6 for the "Hand, Head" ect... + 1 for the "Cancle" at the end
        amountOfMenuItems = myHuman.getInventory().getTotal() + 6 + 1;
        done = false;
        selected = false;

        menuName = "Manage Inventory: " + g.myPlayer.getName();

        menuOptions = new String[amountOfMenuItems];

        for(int index = 0; index < amountOfMenuItems; index++){
            switch(index){

                //Head
                case 0:
                    if(myHuman.getHead() == null){
                        menuOptions[index] = "Head: " + "-none-";
                    }else{
                        menuOptions[index] = "Head: " + myHuman.getHead().getName();
                        if(myHuman.getHead().getStackable()){
                            menuOptions[index] += " x" + myHuman.getHead().getAmount();
                        }
                    }
                    break;

                //Torso
                case 1:
                    if(myHuman.getTorso() == null){
                        menuOptions[index] = "Torso: " + "-none-";
                    }else{
                        menuOptions[index] = "Torso: " + myHuman.getTorso().getName();

                        if(myHuman.getTorso().getStackable()){
                            menuOptions[index] += " x" + myHuman.getTorso().getAmount();
                        }
                    }
                    break;

                //Pants
                case 2:
                    if(myHuman.getPants() == null){
                        menuOptions[index] = "Pants: " + "-none-";
                    }else{
                        menuOptions[index] = "Pants: " + myHuman.getPants().getName();

                        if(myHuman.getPants().getStackable()){
                            menuOptions[index] += " x" + myHuman.getPants().getAmount();
                        }
                    }
                    break;

                //LeftHand
                case 3:
                    if(myHuman.getLeftHand() == null){
                        menuOptions[index] = "Left Hand: " + "-none-";
                    }else{
                        menuOptions[index] = "Left Hand: " + myHuman.getLeftHand().getName();

                        if(myHuman.getLeftHand().getStackable()){
                            menuOptions[index] += " x" + myHuman.getLeftHand().getAmount();
                        }
                    }
                    break;

                //RightHand
                case 4:
                    if(myHuman.getRightHand() == null){
                        menuOptions[index] = "Right Hand: " + "-none-";
                    }else{
                        menuOptions[index] = "Right Hand: " + myHuman.getRightHand().getName();

                        if(myHuman.getRightHand().getStackable()){

                            System.err.println("ITS FUCKING STACKINBALE BITHCHCH!!!");
                            ///////////////////////////////////////////////////////////////////////////////////////////////////
                            menuOptions[index] = menuOptions[index] + " x" +
                                    myHuman.getRightHand().getAmount();
                        }
                    }
                    break;

                //BackPack
                case 5:
                    //If there is nothing in the backpack
                    if(myHuman.getInventory().getTotal() == 0){
                        menuOptions[index] = "Backpack: -none-";
                    }else{
                        menuOptions[index] = "- -- Backpack -- -";
                    }
                    break;

                default:
                    if(index < amountOfMenuItems - 1){
                        menuOptions[index] = myHuman.getInventory().getItem(index - 6).getName();

                        if(myHuman.getInventory().getItem(index - 6).getStackable()){
                            menuOptions[index] += " x" + myHuman.getInventory().getItem(index - 6).getAmount();
                        }
                    }else{
                        menuOptions[index] = "- Back -";
                    }

            }
        }
    }

    @Override

    public void print(){


        g.ui.myPrinter.cls();

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



        if(((currentSelection == 6) &&
                (menuScroll + MAXITEMS < amountOfMenuItems)) ||
                ((menuScroll > 0) && (currentSelection == 0))){
            //Dont print item stats
        }else{
            PlayerHuman myHuman = (PlayerHuman)g.myPlayer;

            switch((currentSelection) + (menuScroll)){

                //Head
                case 0:
                    g.ui.myPrinter.printItemStats(myHuman.getHead(), statXLoc, statYLoc);

                    break;
                //Torso
                case 1:
                    g.ui.myPrinter.printItemStats(myHuman.getTorso(), statXLoc, statYLoc);

                    break;
                //Pants
                case 2:
                    g.ui.myPrinter.printItemStats(myHuman.getPants(), statXLoc, statYLoc);

                    break;
                //LeftHand
                case 3:
                    g.ui.myPrinter.printItemStats(myHuman.getLeftHand(), statXLoc, statYLoc);

                    break;
                //RightHand
                case 4:
                    g.ui.myPrinter.printItemStats(myHuman.getRightHand(), statXLoc, statYLoc);

                    break;

                    //Backpack Marker
                case 5:
                    //Dont do anything here, just taking it out
                    break;

                default:
                    if(currentSelection + menuScroll < amountOfMenuItems - 1){

                        g.ui.myPrinter.printItemStats(
                                myHuman.getInventory().getItem(
                                currentSelection + menuScroll - 6),
                                statXLoc, statYLoc);
                    }

            }
        }


        g.ui.repaint();

    }

    @Override
    public void doMenuOption(){


        MenuManageInventoryItem mmii = new MenuManageInventoryItem(g);


        switch((currentSelection) + (menuScroll)){

            //Head
            case 0:
                System.err.println("ITEM SELECTION NOT YET IMPLEMENTED");
                mmii.setItemVariables(((PlayerHuman)g.myPlayer).getHead(), mmii.LOC_HEAD, 0);

                mmii.run();
                break;

            //Torso
            case 1:
                System.err.println("ITEM SELECTION NOT YET IMPLEMENTED");
                mmii.setItemVariables(((PlayerHuman)g.myPlayer).getTorso(), mmii.LOC_TORSO, 0);

                mmii.run();
                break;

            //Pants
            case 2:
                System.err.println("ITEM SELECTION NOT YET IMPLEMENTED");
                mmii.setItemVariables(((PlayerHuman)g.myPlayer).getPants(), mmii.LOC_PANTS, 0);

                mmii.run();
                break;
            //LeftHand
            case 3:
                System.err.println("ITEM SELECTION NOT YET IMPLEMENTED");
                mmii.setItemVariables(((PlayerHuman)g.myPlayer).getLeftHand(), mmii.LOC_LEFTHAND, 0);

                mmii.run();

                break;
            //RightHand
            case 4:
                System.err.println("ITEM SELECTION NOT YET IMPLEMENTED");
                mmii.setItemVariables(((PlayerHuman)g.myPlayer).getRightHand(), mmii.LOC_RIGHTHAND, 0);

                mmii.run();
                break;
            //---BackPack---
            case 5:
                System.err.println("ITEM SELECTION NOT YET IMPLEMENTED");

                //NO ITEM IN THIS INVENTORY SLOT

                break;
                
            default:
                if(currentSelection + menuScroll < amountOfMenuItems - 1){
                System.err.println("ITEM SELECTION NOT YET IMPLEMENTED");
                    //menuOptions[index] = myHuman.getInventory().getItem(index - 6).getName();
                mmii.setItemVariables(
                        ((PlayerHuman)g.myPlayer).getInventory().getItem((menuScroll + currentSelection - 6)),
                        mmii.LOC_BACKPACK, menuScroll + currentSelection - 6);

                mmii.run();

                }else{
                    done = true;
                    return;
                }

        }
        
        //Setting up all of the changes
        set();
    }
}
