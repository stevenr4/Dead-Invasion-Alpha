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
public class MenuBase {


    //////////////////////////////////////////////////////////////////////////////////////
    int fps;
    long lastSecond;


    public final int MAXITEMS = 7;
    public final int itemYSize = 40;
    public int currentSelection;
    public int amountOfMenuItems;
    public int menuScroll;
    public boolean done;
    public boolean selected;
    public String menuName;
    public String menuOptions[];
    public Game g;

    public MenuBase(){}

    public MenuBase(Game game){
        g = game;
    }

    public void run(){
        
        set();

        do{
            selected = false;
            fps = 0;
            lastSecond = System.currentTimeMillis();
            
            do{

                long time = System.currentTimeMillis();

                input();

                extraStuff();

                
                
                while(System.currentTimeMillis() < time + 16){
                    //Wait 16 millisecs.....
                }
                
                print();

                fps++;
                if(System.currentTimeMillis() >= (lastSecond + 1000)){
                    lastSecond = System.currentTimeMillis();
                    System.out.println("Frames per second: " + fps);
                    fps = 0;
                }

            }while((selected != true) && (done != true));

            checkScroll();//WILL CALL 'DO MENU OPTION'
        }while(done != true);

    }

    public void input(){

        inputMod();

        if(g.ui.mouse.moved()){
            currentSelection = (g.ui.mouse.getY() - 200)/55;

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

        g.ui.repaint();
        
    }

    public void set(){

        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 16;
        done = false;
        selected = false;
        menuName = "I AM ERROR";
        menuOptions = new String[amountOfMenuItems];
        for(int i = 0; i < amountOfMenuItems; i++){
            menuOptions[i] = "I: " + i * i;
        }
    }

    public void checkScroll(){
        if((menuScroll > 0) && (currentSelection == 0)){
            menuScroll--;
        }else if((menuScroll + MAXITEMS < amountOfMenuItems) && (currentSelection == MAXITEMS - 1)){
            menuScroll++;
        }else{
            doMenuOption();
        }
    }


    public void inputMod(){}

    public void doMenuOption(){}

    public void extraStuff(){}
}
