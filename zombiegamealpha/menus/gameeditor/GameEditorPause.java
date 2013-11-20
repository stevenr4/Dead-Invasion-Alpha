/*
 * The pause menu for the game editor
 */

package zombiegamealpha.menus.gameeditor;

import zombiegamealpha.data.Game;
import zombiegamealpha.menus.MenuBase;
import zombiegamealpha.menus.MenuGetBoolean;
import zombiegamealpha.menus.MenuGetIntegers;
import zombiegamealpha.menus.MenuGetString;
import zombiegamealpha.ui.PrintManagerVariables;
/**
 *
 * @author spex
 */

/*
 * - Pause -
 * 
 * -Test
 * -Controls Help
 * -Save
 * -Load
 * -Resize
 * -Return to editor
 * -Done editing
 */
public class GameEditorPause extends MenuBase {

    int screenX, screenY, screenZ;


    public GameEditorPause(Game game, int x, int y, int z){
        g = game;
        screenX = x;
        screenY = y;
        screenZ = z;

    }

    @Override
    public void set(){
        //Set all the functions
        menuScroll = 0;
        currentSelection = 0;
        amountOfMenuItems = 7;
        done = false;
        selected = false;
        menuName = "Editor Pause Menu";
        menuOptions = new String[amountOfMenuItems];
        
        menuOptions[0] = "Test";
        menuOptions[1] = "Controls Help";
        menuOptions[2] = "Resize Map";
        menuOptions[3] = "Save";
        menuOptions[4] = "Load";
        menuOptions[5] = "Return to Editor";
        menuOptions[6] = "Done Editing";
    }


    @Override
    public void print(){

        PrintManagerVariables pmv = new PrintManagerVariables();
        pmv.setScreenXYZ(screenX, screenY, screenZ);
        pmv.setMap(g.currentMap);



        g.ui.myPrinter.cls();
        g.ui.myPrinter.printMap(pmv);
        g.ui.myPrinter.fade();
        g.ui.myPrinter.printMenu(menuName, menuOptions, currentSelection, itemYSize);

        g.ui.repaint();
    }
    
    @Override
    public void doMenuOption(){
        switch(currentSelection){
            case 0:
                System.out.println("Testing the map...");
                GameEditorTest get = new GameEditorTest(g);
                get.run();
                break;
            case 1:
                System.out.println("Showint the controls...");
                GameEditorControls gec = new GameEditorControls(g);
                gec.run();
                break;
            case 2:
                System.out.println("Resizing the map...");

                //Set initial inputs
                int inputs[] = new int[3];
                inputs[0] = g.currentMap.getXSize();
                inputs[1] = g.currentMap.getYSize();
                inputs[2] = g.currentMap.getZSize();

                //Set options
                String options[] = new String[3];
                options[0] = "X size";
                options[1] = "Y size";
                options[2] = "Z size";

                //Set minimum numbers
                int min[] = new int[3];
                min[0] = 10;
                min[1] = 10;
                min[2] = 1;

                //Set max numbers
                int max[] = new int[3];
                max[0] = 100;
                max[1] = 100;
                max[2] = 4;

                //Create new get integers class
                MenuGetIntegers mgi = new MenuGetIntegers(g);
                //Get the integers
                inputs = mgi.getIntegers(inputs, "Map Size", options, min, max, -1);
                
                g.currentMap.resize(inputs[0], inputs[1], inputs[2]);

                break;
            case 3:

                System.out.println("Saving the map...");

                String name;
                MenuGetString mgs = new MenuGetString(g);
                name = mgs.getString("Name map file");

                if(name != null){
                    if(name.contains(".")){
                        name = name.substring(0, name.lastIndexOf("."));
                    }
                    if(name.length() == 0){
                        System.err.println("String is not a valid length");
                    }else{
                        name = name.toLowerCase();
                        String[] tmpFileNames = g.myLoadSaver.getFileNames(".map");
                        boolean nameFound = false;
                        if(tmpFileNames != null){
                            if(tmpFileNames.length > 0){
                                for(int i = 0; i < tmpFileNames.length; i++){
                                    if(name.equals(tmpFileNames[i])){
                                        nameFound = true;

                                        MenuGetBoolean mgb = new MenuGetBoolean(g);

                                        if(mgb.getBoolean("Overwrite: " + name + ".map?", "Overwrite", "Cancle")){
                                            g.myLoadSaver.saveMap(g.currentMap, name, g);
                                        }else{
                                            break;
                                        }
                                    }
                                }
                            }
                        }

                        System.out.println("Name " + name + " found?: " + nameFound);
                        if(nameFound == false){
                            g.myLoadSaver.saveMap(g.currentMap, name, g);
                        }
                    }
                }
                break;
            case 4:
                System.out.println("Loading the map...");
                GameEditorLoadMap gelm = new GameEditorLoadMap(g);
                gelm.run();
                break;
            case 5:
                System.out.println("Returning to editor");
            case 6:
                System.out.println("Done editing");
                done = true;
                break;
        }
    }
}
