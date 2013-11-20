/*
 * Extra stuff i dont want to delete, but aswell dont even need right now
 */

package zombiegamealpha;

/**
 *
 * @author spex
 */
public class extraStuffaaaaDontAsk {


    
    
    

//        g.myLoadSaver = new LoadSaveManager();
//
//        //Reset the printing variables
//        g.showWalls = true;
//        g.openMenu = false;
//        g.openInventory = false;
//        g.paused = false;
//
//        //Reset the paintbrush
//        g.paintBrush = 0;
//
//        g.menuOptions = new String[0];
//        g.menuName = "";
//
//        g.inputString = "";




    //PRINTING


//        this.g.myPrinter.cls();
//
//        switch(this.g.printState){
//            case 0://UI menu
//                this.g.myPrinter.printMainMenu(this.g.selection);
//                break;
//            case 1://Edit test
//            case 2://UI Options
//                //myPrinter.printMainOptions(selection);
//                this.g.myPrinter.printMenu(this.g.menuName, this.g.menuOptions, this.g.selection);
//                break;
//        }
//
//        if(this.g.paused){
//            if((this.g.printState == 1) || (this.g.printState == 3) || (this.g.printState == 4)){
//                this.g.myPrinter.printFadeBacking();
//                this.g.myPrinter.printMenu(this.g.menuName, this.g.menuOptions, this.g.selection);
//            }
//        }


    //////////////////////////////////


    ///////////////////////////////



//
//    //Options menu
    public void mainOptions(){
//
//        boolean done = false;
//
//
//        while(!done){
//
//
//            //Reset all the variables
//            g.printState = 2;
//            g.selection = 0;
//            g.maxSelection = 4;
//            g.selected = false;
//            g.keyType = 0;
//            g.menuOptions = new String[g.maxSelection];
//            g.menuOptions[0] = "Something";
//            g.menuOptions[1] = "Something";
//            g.menuOptions[2] = "MapEditor";
//            g.menuOptions[3] = "Return";
//            g.menuName = "Main Options";
//
//            repaint();
//            do{//Repeat until a selection has been made
//
//                if(g.needRepaint){
//                    repaint();
//                }
//
//            }while(!g.selected) ;
//
//            switch(g.selection){
//                case 0:
//                    break;
//                case 1:
//                    break;
//                case 2:
//                    editMap();
//                    break;
//                case 3:
//                    done = true;
//                    break;
//            }
//        }
    }


    ////////////////////////////////////



//
//    //The Pause menu for the editor
//    public void editPause(){
//
//        int tmpKeyType = g.keyType;
//        //Reset all the variables
//        g.paused = true;
//        g.selection = 0;
//        g.maxSelection = 5;
//        g.selected = false;
//        g.keyType = 0;
//        repaint();
//        g.openMenu = false;
//        g.menuName = "Editor";
//        g.menuOptions = new String[g.maxSelection];
//        g.menuOptions[0] = "Physics/Visual";
//        g.menuOptions[1] = "Toggle Test";
//        g.menuOptions[2] = "Options";
//        g.menuOptions[3] = "Return";
//        g.menuOptions[4] = "Quit";
//        do{//Repeat until a selection has been made
//
//            if(g.needRepaint){
//                repaint();
//            }
//
//        }while(!g.selected);
//
//        switch(g.selection){
//            case 0://Physics/Visual
//                if((g.printState == 1) || (g.printState == 4)){
//                    g.printState = 3;
//                    tmpKeyType = 3;
//                }else{
//                    g.printState = 4;
//                    tmpKeyType = 2;
//                }
//                g.paintBrush = 0;
//                break;
//            case 1://Edit/Test
//                if(tmpKeyType == 1){
//                    g.printState = 4;
//                    tmpKeyType = 2;
//                }else{
//                    tmpKeyType = 1;
//                    g.printState = 1;
//                    g.currentMap.setVisibleAll(false);
//                    g.currentMap.addVisible(g.myAvatar.getX(), g.myAvatar.getY(), g.myAvatar.getZ(), g.myAvatar.getDirection());
//                }
//                break;
//
//            case 2://Options
//                editOptions();
//                break;
//
//            case 3://Return
//                break;
//
//            case 4: //QUIT
//                g.stillPlaying = false;
//                break;
//        }
//
//        //Set back the key type
//        g.keyType = tmpKeyType;
//        g.paused = false;
//        repaint();
//    }

    /////////////////////////////////


//    //Tme options for the editor
//    public void editOptions(){
//
//        int tmpPrintState = g.printState;
//        //Reset all the variables
//        g.printState = 2;
//        g.selection = 0;
//        g.maxSelection = 5;
//        g.selected = false;
//        g.keyType = 0;
//        repaint();
//        g.openMenu = false;
//        g.menuName = "EditOptions";
//        g.menuOptions = new String[g.maxSelection];
//        g.menuOptions[0] = "Save";
//        g.menuOptions[1] = "Load";
//        g.menuOptions[2] = "Resize";
//        g.menuOptions[3] = "Toggle Walls";
//        g.menuOptions[4] = "Return";
//        do{//Repeat until a selection has been made
//
//            if(g.needRepaint){
//                repaint();
//            }
//
//        }while(!g.selected);
//
//        switch(g.selection){
//            case 0://Save
//                save((Object)g.currentMap,".map");
//                break;
//            case 1://Load
//                editLoadMap();
//                break;
//
//            case 2://Resize
//                String[] tmpOptions;
//                tmpOptions = new String[3];
//                tmpOptions[0] = "Width  [X]";
//                tmpOptions[1] = "Length [Y]";
//                tmpOptions[2] = "Height [Z]";
//                int min[] = new int[3];
//                min[0] = 10;
//                min[1] = 10;
//                min[2] = 1;
//                int max[] = new int[3];
//                max[0] = 100;
//                max[1] = 100;
//                max[2] = 4;
//                int[] tmpInput;
//                tmpInput = getIntegers("GetSize", tmpOptions, min, max, 300);
//                if(tmpInput == null){
//                    break;
//                }else{
//                    g.curserX = 0;
//                    g.curserY = 0;
//                    g.screenZ = 0;
//                    g.myAvatar.setX(0);
//                    g.myAvatar.setY(0);
//                    g.myAvatar.setZ(0);
//                    g.currentMap.resize(tmpInput[0], tmpInput[1], tmpInput[2]);
//                }
//                break;
//            case 3://Toggle walls
//                g.showWalls = !g.showWalls;
//                break;
//            case 4://return;
//                break;
//        }
//
//        //Set back the print state
//        g.printState = tmpPrintState;
//        g.needRepaint = true;
//
//    }


    ///////////////////////////////////////////////////////////



//    //Returns a string that the user inputs
//    public String getInputName(){
//
//        int tmpKeyType = g.keyType;
//        g.keyType = 4;
//        int tmpPrintState = g.printState;
//        //Reset all the variables
//        g.printState = 2;
//        g.selection = 0;
//        g.maxSelection = 3;
//        g.selected = false;
//        g.openMenu = false;
//        g.menuName = "< Enter Name >";
//        repaint();
//        g.menuOptions = new String[g.maxSelection];
//        g.menuOptions[0] = "BackSpace";
//        g.menuOptions[1] = "Enter";
//        g.menuOptions[2] = "Cancel";
//        g.inputString = "";
//        g.needRepaint = false;
//        boolean done = false;
//        do {//Repeat until done
//            g.selected = false;
//            do {//Repeat until a selection has been made
//
//                if (g.needRepaint) {
//                    g.menuName = g.inputString;
//                    repaint();
//                }
//
//            } while (!g.selected);
//
//            switch (g.selection) {
//                case 0://BackSpace
//                    if(g.inputString.length() > 0){
//                        g.inputString = g.inputString.substring(0, g.inputString.length() - 1);
//                    }
//                    break;
//                case 1://Enter
//                    g.keyType = tmpKeyType;
//                    g.printState = tmpPrintState;
//                    g.needRepaint = true;
//                    return g.inputString;
//
//                case 2://Cancel
//                    done = true;
//                    break;
//            }
//
//        } while (!done);
//
//        //Set back the print state
//        g.keyType = tmpKeyType;
//        g.printState = tmpPrintState;
//        g.needRepaint = true;
//        return null;
//    }

    ///////////////////////////////////////////////




//    //Loads up the user's selected map
//    public void editLoadMap(){
//
//        int tmpPrintState = g.printState;
//        //Reset all the variables
//        String[] dirNames = g.myLoadSaver.getFileNames(".map");
//        if(dirNames != null){
//            g.maxSelection = dirNames.length + 1;
//            g.menuOptions = new String[g.maxSelection];
//            System.arraycopy(dirNames, 0, g.menuOptions, 0, dirNames.length);
//            g.menuOptions[dirNames.length] = "Return";
//        }else{
//            g.maxSelection = 1;
//            g.menuOptions = new String[g.maxSelection];
//            g.menuOptions[0] = "Return";
//        }
//
//        g.printState = 2;
//        g.selection = 0;
//        g.selected = false;
//        g.keyType = 0;
//        repaint();
//        g.menuName = "Load:";
//        g.openMenu = false;
//        do{//Repeat until a selection has been made
//
//            if(g.needRepaint){
//                repaint();
//            }
//
//        }while(!g.selected);
//
//        //Set back the print state
//        g.printState = tmpPrintState;
//        g.needRepaint = true;
//        if(g.selection < g.menuOptions.length - 1){
//            g.currentMap = (Map)g.myLoadSaver.loadFile(g.menuOptions[g.selection], ".map");
//        }
//    }

    ///////////////////////////////////////////////////////


//    //Gets a set of integers, must send title and names of intergers (displayed to user)
//    public int[] getIntegers(String title, String[] options, int[] min, int[] max, int toAdd){
//        if(options.length == 0){
//            return null;
//        }else{
//            g.minInputVariables = min;
//            g.maxInputVariables = max;
//            g.totalAdd = toAdd;
//
//            int tmpKeyType = g.keyType;
//            g.keyType = 5;
//            int tmpPrintState = g.printState;
//            //Reset all the variables
//            g.printState = 2;
//            g.selection = 0;
//            g.maxSelection = options.length;
//            g.inputValues = new int[g.maxSelection];
//            g.selected = false;
//            g.menuName = title;
//            g.menuOptions = new String[g.maxSelection + 2];
//            System.arraycopy(g.minInputVariables, 0, g.inputValues, 0, g.maxSelection);
//            g.maxSelection++;
//            g.menuOptions[g.maxSelection - 1] = "Enter";
//            g.maxSelection++;
//            g.menuOptions[g.maxSelection - 1] = "Cancel";
//            g.needRepaint = true;
//            boolean done = false;
//
//            do {//Repeat until done
//                g.selected = false;
//                do {//Repeat until a selection has been made
//
//                    if (g.needRepaint) {
//                        String tmpString = new String();//Create a variable to combine all the pieces of the option
//                        for(int i = 0; i < g.maxSelection - 2; i++){
//
//                            tmpString = options[i] + " : ";//Set the option name and the spacer
//
//                            if(g.inputValues[i] > g.minInputVariables[i]){//if you can go down, show a down arrow
//                                tmpString += "< ";
//                            }else{
//                                tmpString += "   ";
//                            }
//
//                            tmpString += g.inputValues[i];//Add in the name (between the arrows)
//
//                            if((g.inputValues[i] < g.maxInputVariables[i]) && //Add in the up arrow if it can go up
//                            (g.totalAdd > 0)){
//                                tmpString += " >";
//                            }else{
//                                tmpString += "   ";
//                            }
//
//                            g.menuOptions[i]  = tmpString;//Make the option all the combined strings
//                        }
//                        repaint();
//                    }
//
//                } while (!g.selected);
//
//                if(g.selection == g.maxSelection - 2){
//
//                    //Set back the print state
//                    g.keyType = tmpKeyType;
//                    g.printState = tmpPrintState;
//                    g.needRepaint = true;
//                    return g.inputValues;
//                }
//                if(g.selection == g.maxSelection - 1){
//                    //Set back the print state
//                    g.keyType = tmpKeyType;
//                    g.printState = tmpPrintState;
//                    g.needRepaint = true;
//                    return null;
//                }
//
//            } while (!done);
//        }
//        return null;
//    }

    ////////////////////////////////////////////////////


//    //Returns a boolean depending on if the user is sure or not
//    public boolean youSure(String title){
//        int tmpKeyType = g.keyType;
//        g.keyType = 5;
//        int tmpPrintState = g.printState;
//        //Reset all the variables
//        g.printState = 2;
//        g.selection = 0;
//        g.maxSelection = 2;
//        g.inputValues = new int[g.maxSelection];
//        g.selected = false;
//        g.menuName = title;
//        g.menuOptions = new String[g.maxSelection];
//        g.menuOptions[0] = "No";
//        g.menuOptions[1] = "Yes";
//        repaint();
//        g.selected = false;
//        do {//Repeat until a selection has been made
//
//            if (g.needRepaint) {
//                repaint();
//            }
//
//        } while (!g.selected);
//
//        if(g.selection == 0){
//            return false;
//        }else{
//            return true;
//        }
//    }


    ///////////////////////////////////////////////



//    //The saving function
    private void save(Object file, String extension){
//
//        String name;
//        name = getInputName();
//
//        if(name != null){
//            if(name.contains(".")){
//                name = name.substring(0, name.lastIndexOf("."));
//            }
//            if(name.length() == 0){
//
//            }else{
//                name = name.toLowerCase();
//                String[] tmpFileNames = g.myLoadSaver.getFileNames(extension);
//                boolean nameFound = false;
//                if(tmpFileNames != null){
//                    if(tmpFileNames.length > 0){
//                        for(int i = 0; i < tmpFileNames.length; i++){
//                            if(name.equals(tmpFileNames[i])){
//                                nameFound = true;
//                                if(youSure("Overwrite " + name + "?")){
//                                    g.myLoadSaver.saveFile(file, name, extension);
//                                }else{
//                                    break;
//                                }
//                            }
//                        }
//                    }
//                }
//
//                System.out.println("Name found? : " + nameFound);
//                if(nameFound == false){
//                    g.myLoadSaver.saveFile(g.currentMap, name, extension);
//                }
//            }
//        }
    }


    //////////////////////////////////////////////////////////////

    //GENERIC KEY CODES!!!

//        if(keyCode < 256){
//        }
//        switch(g.keyType){
//            case 0://Menu or Options
//                testMenuKeys();
//                break;
//            case 1:
//                editGameKeys();
//                screenMovement();
//                break;
//            case 2:
//                editCurserKeysVisual();
//                curserMovement();
//                screenMovement();
//                break;
//            case 3:
//                editCurserKeysPhysics();
//                curserMovement();
//                screenMovement();
//                break;
//            case 4:
//                if((keyCode >= 65) && (keyCode <= 90)){
//                    g.inputString = g.inputString + k.getKeyChar();
//                    System.out.println(g.inputString);
//                    System.out.println(g.inputString.length());
//                }
//                testMenuKeys();
//                break;
//            case 5:
//                testGetValueKeys();
//                testMenuKeys();
//                break;
//        }
//        g.needRepaint = true;



    //////////////////////////////////////////////////////////////




    /////////////////////////////////////////////////////////////////////



    //The keyStates for the curser editor (Physics mode)
//    public void editCurserKeysPhysics(){
//        if(keys.keyStates[keys.ESCAPE]){
//            openMenu = true;
//        }
//        if(keys.keyStates[keys.SPACE]){
//            if(paintBrush < 4){
//                if(currentMap.getMapSpot(curserX, curserY, screenZ).getFloor()){
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setTransparent(transparentPhysicsFromNumber(paintBrush));
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setPassable(passablePhysicsFromNumber(paintBrush));
//                    currentMap.getMapSpot(curserX,curserY,screenZ).setClimb(false);
//                }
//            }else{
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setTransparent(true);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setPassable(true);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setClimb(true);
//            }
//            needRepaint = true;
//        }
//        if(keys.keyStates[keys.Z]){
//            if (showWalls) {
//                if (paintBrush < 4) {
//                    if (currentMap.getMapSpot(curserX, curserY, screenZ).getLeftWall()) {
//                        currentMap.getMapSpot(curserX, curserY, screenZ).setTransparentLeft(transparentPhysicsFromNumber(paintBrush));
//                        currentMap.getMapSpot(curserX, curserY, screenZ).setPassableLeft(passablePhysicsFromNumber(paintBrush));
//                    }
//                } else {
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setTransparentLeft(true);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setPassableLeft(true);
//                }
//            }
//        }
//        if(keys.keyStates[keys.C]){
//            if (showWalls) {
//                if (paintBrush < 4) {
//                    if (currentMap.getMapSpot(curserX, curserY, screenZ).getRightWall()) {
//                        currentMap.getMapSpot(curserX, curserY, screenZ).setTransparentRight(transparentPhysicsFromNumber(paintBrush));
//                        currentMap.getMapSpot(curserX, curserY, screenZ).setPassableRight(passablePhysicsFromNumber(paintBrush));
//                    }
//                } else {
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setTransparentRight(true);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setPassableRight(true);
//                }
//            }
//        }
//        if(keys.keyStates[keys.X]){
//            //////////<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
//        }
//        if(keys.keyStates[keys.Q]){
//            if(paintBrush > 0){
//                paintBrush--;
//            }
//        }
//        if(keys.keyStates[keys.E]){
//            if(paintBrush < 4){
//                paintBrush++;
//            }
//        }
//    }



    //////////////////////////////////////////////////////////////////////





    //The keyStates for the curser editor (Visual mode)
//    public void editCurserKeysVisual(){
//        if(g.keys.keyStates[g.keys.ESCAPE]){
//            g.openMenu = true;
//        }
//        if(g.keys.keyStates[g.keys.SPACE]){
//            if((g.paintBrush >= 0) && (g.paintBrush < new ImageStorage().getTotalFloorImages())){
//                g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setGraphics(g.paintBrush);
//            }
//            g.needRepaint = true;
//        }
//        if(g.keys.keyStates[g.keys.Z]){
//            if (g.showWalls) {
//                if ((g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).getLeftWall())
//                        && (g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).getWallGraphicsLeft() == g.paintBrush)) {
//                    g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setLeftWall(false);
//                    g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setPassableLeft(true);
//                    g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setTransparentLeft(true);
//                } else if (!g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).getLeftWall()) {
//                    g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setLeftWall(true);
//                    g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setPassableLeft(false);
//                    g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setTransparentLeft(false);
//                }
//                if ((g.paintBrush >= 0) && (g.paintBrush < new ImageStorage().getTotalWallImages())) {
//                    g.currentMap.getMapSpot(g.curserX, g.curserY, g.screenZ).setWallGraphicsLeft(g.paintBrush);
//                }
//            }
//        }
//        if(keys.keyStates[keys.C]){
//            if (showWalls) {
//                if ((currentMap.getMapSpot(curserX, curserY, screenZ).getRightWall())
//                        && (currentMap.getMapSpot(curserX, curserY, screenZ).getWallGraphicsRight() == paintBrush)) {
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setRightWall(false);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setPassableRight(true);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setTransparentRight(true);
//                } else if (!currentMap.getMapSpot(curserX, curserY, screenZ).getRightWall()) {
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setRightWall(true);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setPassableRight(false);
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setTransparentRight(false);
//                }
//                if ((paintBrush >= 0) && (paintBrush < new ImageStorage().getTotalWallImages())) {
//                    currentMap.getMapSpot(curserX, curserY, screenZ).setWallGraphicsRight(paintBrush);
//                }
//            }
//        }
//        if(keys.keyStates[keys.X]){
//            if((currentMap.getMapSpot(curserX, curserY, screenZ).getFloor()) &&
//                    (currentMap.getMapSpot(curserX,curserY,screenZ).getGraphics() == paintBrush)){
//                currentMap.getMapSpot(curserX, curserY, screenZ).setFloor(false);
//                currentMap.getMapSpot(curserX, curserY, screenZ).setPassable(false);
//                currentMap.getMapSpot(curserX, curserY, screenZ).setTransparent(true);
//                currentMap.getMapSpot(curserX,curserY,screenZ).setClimb(false);
//            }else if(!currentMap.getMapSpot(curserX, curserY, screenZ).getFloor()){
//                currentMap.getMapSpot(curserX, curserY, screenZ).setFloor(true);
//                currentMap.getMapSpot(curserX, curserY, screenZ).setPassable(true);
//                currentMap.getMapSpot(curserX, curserY, screenZ).setTransparent(true);
//                currentMap.getMapSpot(curserX,curserY,screenZ).setClimb(false);
//            }
//            if((paintBrush >= 0) && (paintBrush < new ImageStorage().getTotalWallImages())){
//                currentMap.getMapSpot(curserX, curserY, screenZ).setGraphics(paintBrush);
//            }
//        }
//        if(keys.keyStates[keys.Q]){
//            if(paintBrush > 0){
//                paintBrush--;
//            }
//        }
//        if(keys.keyStates[keys.E]){
//            if((paintBrush < new ImageStorage().getTotalFloorImages() - 1) || (paintBrush < new ImageStorage().getTotalWallImages() - 1)){
//                paintBrush++;
//            }
//        }
//        if(keys.keyStates[keys.F]){
//            if((currentMap.getMapSpot(curserX, curserY, screenZ).getObject()) &&
//                    (currentMap.getMapSpot(curserX,curserY,screenZ).getObjectGraphic() == paintBrush)){
//                currentMap.getMapSpot(curserX, curserY, screenZ).setObject(false);
//            }else if(!currentMap.getMapSpot(curserX, curserY, screenZ).getObject()){
//                currentMap.getMapSpot(curserX, curserY, screenZ).setObject(true);
//            }
//            if((paintBrush >= 0) && (paintBrush < new ImageStorage().getTotalObjectImages())){
//                currentMap.getMapSpot(curserX, curserY, screenZ).setObjectGraphic(paintBrush);
//            }else{
//                currentMap.getMapSpot(curserX, curserY, screenZ).setObject(false);
//            }
//        }
//    }




    /////////////////////////////////////////////////////////////////////





    //The curser movement (foward, back, left, right, up floor, down floor)
//    private void curserMovement(){
//        if(keys.keyStates[keys.UP]){
//            if(curserY > 0){
//                curserY--;
//                testOutsideScreen(curserX,curserY,screenZ);
//            }
//        }
//        if(keys.keyStates[keys.DOWN]){
//            if(curserY < currentMap.getYSize() - 1){
//                curserY++;
//                testOutsideScreen(curserX,curserY,screenZ);
//            }
//        }
//        if(keys.keyStates[keys.LEFT]){
//            if(curserX > 0){
//                curserX--;
//                testOutsideScreen(curserX,curserY,screenZ);
//            }
//        }
//        if(keys.keyStates[keys.RIGHT]){
//            if(curserX < currentMap.getXSize() - 1){
//                curserX++;
//                testOutsideScreen(curserX,curserY,screenZ);
//            }
//        }
//    }





    ///////////////////////////////////////////////////////////////////





//    private void testOutsideScreen(int x, int y, int z){
//        final int spacing = 20;
//
//        System.out.println("PING\nX: " + x + "\nY: " + y + "\nZ: " + z
//                + "\nResult:" + ((x * spacing)//If it goes across the top
//                + (y * spacing)
//                - (z * (spacing * 5))) + " > " + ((screenY) + (Height - (spacing * 11))));
//
//        if(((x * spacing * 2)//If it goes off the screen to the right
//                - (y * 2 * spacing))
//                > (screenX) + (Width / 2) - (spacing * 6)){
//            screenX = ((x * spacing * 2)
//                - (y * 2 * spacing))
//                + (spacing * 6)
//                - (Width / 2);
//        }
//
//        if(((x * spacing * 2)//If it goes off the screen to the left
//                - (y * 2 * spacing))
//                < (screenX) - (Width / 2) + (spacing * 6)){
//            screenX = ((x * spacing * 2)
//                - (y * 2 * spacing))
//                - (spacing * 6)
//                + (Width / 2);
//        }
//
//        if(((x * spacing)//If it goes across the bottom
//                + (y * spacing)
//                - (z * (spacing * 5)))
//                > (screenY) + (Height - (spacing * 13))){
//            screenY = ((x * spacing)
//                + (y * spacing)
//                - (z * (spacing * 5)))
//                - (Height - (spacing * 13));
//        }
//
//        if(((x * spacing)//If it goes across the top
//                + (y * spacing)
//                - (z * (spacing * 5)))
//                < (screenY) + (spacing * 7)){
//            screenY = ((x * spacing)
//                + (y * spacing)
//                - (z * (spacing * 5)
//                + (spacing * 7)));
//        }
//
//        screenZ = z;
//    }




    ////////////////////////////////////////////////////////////////////







    //The keystattes for moving the screen around
    private void screenMovement(){
//        if(keys.keyStates[keys.W]){
//            screenY -= 20;
//        }
//        if (keys.keyStates[keys.S]) {
//            screenY += 20;
//        }
//        if(keys.keyStates[keys.A]){
//            screenX -= 20;
//        }
//        if(keys.keyStates[keys.D]){
//            screenX += 20;
//        }
//        if(keys.keyStates[keys.O]){
//            if(screenZ < currentMap.getZSize() - 1){
//                screenZ++;
//                screenY -= 20 * 5;
//            }
//        }
//        if(keys.keyStates[keys.L]){
//            if(screenZ > 0){
//                screenZ--;
//                screenY += 20 * 5;
//            }
//        }

    }

    //The keyStates of the value input menu
    private void testGetValueKeys(){
//        if(keys.keyStates[keys.RIGHT]){
//            if((selection < inputValues.length) &&
//            (inputValues[selection] < g.maxInputVariables[selection])){
//                inputValues[selection]++;
//            }
//        }
//        if(keys.keyStates[keys.LEFT]){
//            if((selection < inputValues.length) &&
//            (inputValues[selection] > g.minInputVariables[selection])){
//                if(inputValues[selection] > 0){
//                    inputValues[selection]--;
//                }
//            }
//        }
    }

  


    //The keyStates for the menus
    public void testMenuKeys(){

//        if(keys.keyStates[keys.UP]){
//            if(selection > 0){
//                selection--;
//            }
//        }else if(keys.keyStates[keys.DOWN]){
//            if(selection < maxSelection - 1){
//                selection++;
//            }
//        }else if(keys.keyStates[keys.ENTER]){
//            selected = true;
//        }
    }
}
