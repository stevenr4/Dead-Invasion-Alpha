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
public class MenuGetIntegers extends MenuBase {

    int inputs[], minInputs[], maxInputs[];
    int points;
    boolean letFinish;
    String inputOptions[];
    String modName;

    public MenuGetIntegers(Game game){

        g = game;
        
        minInputs = new int[0];
        maxInputs = new int[0];
        points = -1;
        letFinish = true;
    }

    public int[] getIntegers(int inputInputs[], String name, String options[], int min[], int max[], int inputPoints){
        if((options.length != inputInputs.length) ||
                (options.length != min.length) ||
                (options.length != max.length)){
            System.err.println("Calling for integers, but variables are not length");
            return inputInputs;
        }

        minInputs = new int[min.length];
        maxInputs = new int[max.length];
        
        for(int i = 0; i < min.length; i++){
            minInputs[i] = min[i];
            maxInputs[i] = max[i];
        }
        
        points = inputPoints;
        
        modName = name;
        
        return getIntegers(inputInputs, name, options);
    }


    
    public int[] getIntegers(int inputInputs[], String name, String options[]){

        if(options.length != inputInputs.length){
            return inputInputs;
        }

        inputs = new int[inputInputs.length];

        inputOptions = new String[inputInputs.length];
        
        for(int i = 0; i < inputInputs.length; i++){
            inputs[i] = inputInputs[i];
            inputOptions[i] = options[i];
        }
        
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = inputInputs.length + 1;
        menuOptions = new String[amountOfMenuItems];
        menuName = name;

        
        done = false;
        selected = false;

        run();
        
        return inputs;
        
    }

    @Override
    public void set(){

        if(points >= 0){
            menuName = modName + ": " + points;
        }else{
            menuName = modName;
        }

        for(int i = 0; i < amountOfMenuItems - 1; i++){

            String tmpString = inputOptions[i];

            if(inputs[i] > minInputs[i]){
                tmpString += "<";
            }else{
                tmpString += " ";
            }

            tmpString += "" + inputs[i];

            if((inputs[i] < maxInputs[i]) &&
            (points != 0)){
                tmpString += ">";
            }else{
                tmpString += " ";
            }

            menuOptions[i] = tmpString;

        }
        if((points > 0) && (letFinish == false)){
            menuOptions[amountOfMenuItems - 1] = "-enter all values-";
        }else{
            menuOptions[amountOfMenuItems - 1] = "Done";
        }
    }

    @Override
    public void doMenuOption(){
        if((currentSelection == amountOfMenuItems - 1) &&
        ((points == 0) || (letFinish))){

            done = true;
        }else{
            set();
        }
    }

    @Override

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
                if(currentSelection < amountOfMenuItems - 1){
                    currentSelection++;
                }
            }else if(g.ui.key.getKey() == java.awt.event.KeyEvent.VK_ENTER){
                selected = true;
            }else if(g.ui.key.getKey() == java.awt.event.KeyEvent.VK_LEFT){
                if(currentSelection < inputs.length){
                    if(inputs[currentSelection] > minInputs[currentSelection]){

                        inputs[currentSelection]--;
                        if(points >= 0){
                            points++;
                        }
                    }
                }
            }else if(g.ui.key.getKey() == java.awt.event.KeyEvent.VK_RIGHT){
                if(currentSelection < inputs.length){
                    if((inputs[currentSelection] < maxInputs[currentSelection]) &&
                            (points != 0)){

                        inputs[currentSelection]++;
                        if(points >= 0){
                            points--;
                        }
                    }
                }
            }
        }

        set();
    }

    @Override
    public void inputMod(){


        if(points < 0){
            if(g.ui.mouse.getClicked()){
                if(currentSelection < amountOfMenuItems - 1){
                    if(g.ui.mouse.getX() < g.ui.myWIDTH/2){
                        if(inputs[currentSelection] > minInputs[currentSelection]){
                            inputs[currentSelection]--;
                        }
                    }else{
                        if(inputs[currentSelection] < maxInputs[currentSelection]){
                            inputs[currentSelection]++;
                        }
                    }
                }
            }
        } else {
            if(g.ui.mouse.getClicked()){
                if(currentSelection < amountOfMenuItems - 1){
                    if((g.ui.mouse.getX() < g.ui.myWIDTH/2) &&
                            (inputs[currentSelection] > minInputs[currentSelection])){
                        inputs[currentSelection]--;
                        points++;
                    }else if((g.ui.mouse.getX() > g.ui.myWIDTH/2)  && 
                            (inputs[currentSelection] < maxInputs[currentSelection]) &&
                            (points > 0)){
                        inputs[currentSelection]++;
                        points--;
                    }
                }
            }
        }
    }

    public void setLetFinish(boolean newFinish){
        letFinish = newFinish;
    }
}
