package zombiegamealpha.data;

/*
 * This file manages loading and saving maps ect....
 */

/**
 *
 * @author spex
 */
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;


public class LoadSaveManager {
    

    private final byte ENDMAPSPOT = -1;//The end of a map spot
    private final byte FLOOR = 0;//If there is a floor or not;
    private final byte VISIBLE = 1;//If the spot is currently being looked at
    private final byte SEEN = 2;//If the spot has been looked at before
    private final byte GRAPHICS = 3;//The ID of the graphics
    private final byte PASSABLE = 4;
    private final byte TRANSPARENT = 5;
    private final byte LEFTWALL = 6;
    private final byte RIGHTWALL = 7;//If there is a wall or not
    private final byte WALLGRAPHICSLEFT = 8;
    private final byte WALLGRAPHICSRIGHT = 9;//Id for the wall graphics
    private final byte PASSABLELEFT = 10;
    private final byte TRANSPARENTLEFT = 11;//Id for the left wall physics
    private final byte PASSABLERIGHT = 12;
    private final byte TRANSPARENTRIGHT = 13;//Id for the right wall physics
    private final byte OBJECTGRAPHIC = 14;//The id of the graphic of the object that is int the current spot
    private final byte OBJECT = 15;
    private final byte CLIMB = 16;//If the person can climb up from this spot or down onto it
    private final byte SPAWNPOINT = 17;//If the human can start in this spot
    private final byte HUMANSPAWN = 18;//If a zombie can start in this spot

    //The home directory
    private final File home;
    private String[] fileNames;//Stores all the file's names in the home dir

    
    public LoadSaveManager(){
        //String dir = Main.class.getResource(".").getFile();//Get the directory that the file is located in
        home = new File(".");//dir + "/");
        System.out.println("Home: " + home.getAbsolutePath());
    }

    //A function to save all the variables in a map
    public void saveMap(Map m, String name, Game g){

        //Start the try for the file input, possible chance of failure
        try {


            //Create a new output stream in the local directory
            FileOutputStream fileOut = new FileOutputStream(home.getCanonicalPath() + "/" + name + ".map");

            //Print out that it is writing
            System.out.println("Writing: " + name + ".map");



            //First, print out the size of the array
            fileOut.write(m.getXSize());
            fileOut.write(m.getYSize());
            fileOut.write(m.getZSize());

            //Then, go through all the map spots
            for(int tmpZ = 0; tmpZ < m.getZSize(); tmpZ++){
                for(int tmpY = 0; tmpY < m.getYSize(); tmpY++){
                    for(int tmpX = 0; tmpX < m.getXSize(); tmpX++){

                        fileOut.write(FLOOR);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getFloor()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(VISIBLE);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getVisible()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(SEEN);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getSeen()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(GRAPHICS);
                        fileOut.write(intToByteArray(m.getMapSpot(tmpX, tmpY, tmpZ).getGraphics()));

                        fileOut.write(PASSABLE);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getPassable()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(TRANSPARENT);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getTransparent()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(LEFTWALL);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getLeftWall()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(RIGHTWALL);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getRightWall()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(WALLGRAPHICSLEFT);
                        fileOut.write(intToByteArray(m.getMapSpot(tmpX, tmpY, tmpZ).getWallGraphicsLeft()));

                        fileOut.write(WALLGRAPHICSRIGHT);
                        fileOut.write(intToByteArray(m.getMapSpot(tmpX, tmpY, tmpZ).getWallGraphicsRight()));

                        fileOut.write(PASSABLELEFT);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getPassableLeft()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(TRANSPARENTLEFT);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getTransparentLeft()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(PASSABLERIGHT);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getPassableRight()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(TRANSPARENTRIGHT);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getTransparentRight()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(OBJECTGRAPHIC);
                        fileOut.write(intToByteArray(m.getMapSpot(tmpX, tmpY, tmpZ).getObjectGraphic()));

                        fileOut.write(OBJECT);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getObject()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(CLIMB);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getClimb()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(SPAWNPOINT);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getSpawnPoint()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }

                        fileOut.write(HUMANSPAWN);
                        if(m.getMapSpot(tmpX, tmpY, tmpZ).getHumanSpawn()){
                            fileOut.write(1);
                        }else{
                            fileOut.write(0);
                        }


                        fileOut.write(ENDMAPSPOT);
                    }

                    g.ui.myPrinter.cls();
                    g.ui.myPrinter.printLoadingBar("Saving Map",
                            tmpY + (tmpZ * m.getYSize()),  m.getYSize() * m.getZSize());
                    g.ui.repaint();
                }
            }


            //Close the file output
            fileOut.close();

        } catch(FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    //A function to load a map
    public Map loadMap(String name, Game g){

        //Make a map to store all of the data
        Map returnMap = new Map();

        //Inclose the loading in a try statement, in case we try to load something we cant
        try {


            //Create a file input stream
            FileInputStream fileIn = new FileInputStream(home.getCanonicalPath() + "/" + name + ".map");


            //Print out that we are loadint the file
            System.out.println("Loading: " + name + ".map");

            int x = fileIn.read();
            int y = fileIn.read();
            int z = fileIn.read();

            
            System.out.println("Map size: " + x + " ; " + y + " ; " + z);
            returnMap.resize(x, y, z);
            
            
            int variableInput;
            byte[] tmpInputArray = new byte[4];
            int tmpInput;
            boolean doneInputing;

            //For all of the map spots
            for(int tmpZ = 0; tmpZ < z; tmpZ++){
                for(int tmpY = 0; tmpY < y; tmpY++){
                    for(int tmpX = 0; tmpX < x; tmpX++){


                        doneInputing = false;

                        do{
                            variableInput = fileIn.read();
                            
                            switch(variableInput){

                                case FLOOR:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setFloor(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setFloor(false);
                                    }
                                    break;

                                case VISIBLE:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setVisible(true, false);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setVisible(false, false);
                                    }
                                    break;

                                case SEEN:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setSeen(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setSeen(false);
                                    }
                                    break;

                                case GRAPHICS:
                                    fileIn.read(tmpInputArray);
                                    returnMap.getMapSpot(tmpX, tmpY, tmpZ).setGraphics(byteArrayToInt(tmpInputArray));
                                    break;

                                case PASSABLE:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setPassable(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setPassable(false);
                                    }
                                    break;

                                case TRANSPARENT:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setTransparent(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setTransparent(false);
                                    }
                                    break;

                                case LEFTWALL:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setLeftWall(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setLeftWall(false);
                                    }
                                    break;

                                case RIGHTWALL:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setRightWall(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setRightWall(false);
                                    }
                                    break;

                                case WALLGRAPHICSLEFT:
                                    fileIn.read(tmpInputArray);
                                    returnMap.getMapSpot(tmpX, tmpY,
                                            tmpZ).setWallGraphicsLeft(byteArrayToInt(tmpInputArray));
                                    break;

                                case WALLGRAPHICSRIGHT:
                                    fileIn.read(tmpInputArray);
                                    returnMap.getMapSpot(tmpX, tmpY,
                                            tmpZ).setWallGraphicsRight(byteArrayToInt(tmpInputArray));
                                    break;

                                case PASSABLELEFT:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setPassableLeft(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setPassableLeft(false);
                                    }
                                    break;

                                case TRANSPARENTLEFT:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setTransparentLeft(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setTransparentLeft(false);
                                    }
                                    break;

                                case PASSABLERIGHT:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setPassableRight(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setPassableRight(false);
                                    }
                                    break;

                                case TRANSPARENTRIGHT:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setTransparentRight(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setTransparentRight(false);
                                    }
                                    break;

                                case OBJECTGRAPHIC:
                                    fileIn.read(tmpInputArray);
                                    returnMap.getMapSpot(tmpX, tmpY,
                                            tmpZ).setObjectGraphic(byteArrayToInt(tmpInputArray));
                                    break;

                                case OBJECT:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setObject(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setObject(false);
                                    }
                                    break;

                                case CLIMB:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setClimb(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setClimb(false);
                                    }
                                    break;

                                case SPAWNPOINT:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setSpawn(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setSpawn(false);
                                    }
                                    break;

                                case HUMANSPAWN:
                                    tmpInput = fileIn.read();
                                    if(tmpInput == 1){
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setHumanSpawn(true);
                                    }else{
                                        returnMap.getMapSpot(tmpX, tmpY, tmpZ).setHumanSpawn(false);
                                    }
                                    break;

                                case ENDMAPSPOT:
                                default:

                                    doneInputing = true;

                                    break;
                            
                            }

                        }while(doneInputing == false);


                    }

                    g.ui.myPrinter.cls();
                    g.ui.myPrinter.printLoadingBar("Loading Map",
                            tmpY + (tmpZ * y),  y * z);
                    g.ui.repaint();
                }
            }

            //Close the file input stream
            fileIn.close();

        } catch(FileNotFoundException e) {
            System.out.println("FileNotFound");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return returnMap;
    }

    //A function to get all file names of a certain string...
    public String[] getFileNames(String extension){
        
        //Get all of the names in the 'Home' Directory
        fileNames = home.list();
        
        //If it is null, print that there are no names and return nothing.
        if(fileNames == null){
            System.out.println("NO NAMES");
            return null;
        }


        
        //A string to hold all of the file names without the extension(s)
        String[] cutNames;

        //SO far we have no names, so set it to 0
        int totalNames = 0;
        
        
        //For all of the names in the list...
        for(String name : fileNames){

            //If it has the extension we are looking for, increment total
            if(name.endsWith(extension)){
                totalNames++;
            }
        }

        //If there are no names, return null
        if(totalNames == 0){
            return null;
        }

        //Size our cut names to the amount of names we know exist...
        cutNames = new String[totalNames];

        //An integer to save the location we are in of the array
        int arrayIndex = 0;

        //For all of the names (again)....
        for(int i = 0; i < fileNames.length; i++){
            //If the name matches what we are looking for....
            if(fileNames[i].endsWith(extension)){
                //Copy over the name without the extension (cutting by the location of the ".")
                cutNames[arrayIndex] = fileNames[i].substring(0, fileNames[i].lastIndexOf("."));
                arrayIndex++;
            }
        }

        //Return all of the names
        return cutNames;
    }

    /*
     *

    public void saveFile(Object file, String name, String extenion){

        //Start the try for the file input, possible chance of failure
        try {


            //Create a new output stream in the local directory
            FileOutputStream fileOut = new FileOutputStream(home.getCanonicalPath() + "/" + name + extenion);

            //Create a object output stream
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            //Print out that it is writing
            System.out.println("Writing: " + name + extenion);

            //Write the data to the new file
            out.writeObject(file);

            //Close the Object output Stream
            out.close();

            //Close the file output
            fileOut.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object loadFile(String name, String extension){

        //Create a temporary storage and fill it with null so we have somthing to return
        Object tmpObj = null;

        //Inclose the loading in a try statement, in case we try to load something we cant
        try {

            //Create a file input stream
            FileInputStream fileIn = new FileInputStream(home.getCanonicalPath() + "/" + name + extension);

            //Create an object input string from the file dir
            ObjectInputStream in = new ObjectInputStream(fileIn);

            //Print out that we are loadint the file
            System.out.println("Loading: " + name + extension);

            //Load in the object
            tmpObj = in.readObject();

            //Close the input object stream
            in.close();

            //Close the file input stream
            fileIn.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            System.out.println("FileNotFound");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmpObj;
    }
     */




    public byte[] intToByteArray(int value) {
            return new byte[] {
                    (byte)(value >>> 24),
                    (byte)(value >>> 16),
                    (byte)(value >>> 8),
                    (byte)value};
    }


    public final int byteArrayToInt(byte[] b)
    {
        int integer = 0;
        integer |= b[0] & 0xFF;
        integer <<= 8;
        integer |= b[1] & 0xFF;
        integer <<= 8;
        integer |= b[2] & 0xFF;
        integer <<= 8;
        integer |= b[3] & 0xFF;
        return integer;
    }
}
