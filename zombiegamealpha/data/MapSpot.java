/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data;
import zombiegamealpha.data.items.Item;
import java.io.Serializable;

import java.util.Random;

/**
 *
 * @author spex
 */
public class MapSpot implements Serializable{

    private boolean floor;//If there is a floor or not;
    private boolean visible;//If the spot is currently being looked at
    private boolean seen;//If the spot has been looked at before
    private int graphics;//The ID of the graphics
    private boolean passable, transparent;
    private boolean leftWall, rightWall;//If there is a wall or not
    private int wallGraphicsLeft, wallGraphicsRight;//Id for the wall graphics
    private boolean passableLeft, transparentLeft;//Id for the left wall physics
    private boolean passableRight, transparentRight;//Id for the right wall physics
    private int objectGraphic;//The id of the graphic of the object that is int the current spot
    private boolean object;
    private boolean climb;//If the person can climb up from this spot or down onto it
//    private boolean teleporter;//If the spot is a teleporter.
//    private int teleporterLoc[];//the ending point of the teleporter
    private boolean spawnPoint;//If the human can start in this spot
    private boolean humanSpawn;//If a zombie can start in this spot
    private Inventory floorItems;//The inventory for the floor
                                //(items that have been thrown or drop)

//    private boolean activatiable;//If the spot can be activated
//    private MapSpotStorage mapSpotStorage;//The saved spots if this spot is activated
    
    //Setting all the variables when the class is created
    MapSpot(){
        visible = false;
        seen = false;
        floor = true;

        Random r = new Random();
        graphics = r.nextInt(3) + 7;
        
        passable = true;
        transparent = true;
        leftWall = false;
        rightWall = false;
        wallGraphicsLeft = 0;
        wallGraphicsRight = 0;
        passableLeft = true;
        passableRight = true;
        transparentLeft = true;
        transparentRight = true;
        objectGraphic = 0;
        object = false;
        climb = false;
        floorItems = new Inventory();
        spawnPoint = false;
        humanSpawn = false;
//        activatiable = false;
//        mapSpotStorage = new MapSpotStorage();
//        teleporter = false;
//        teleporterLoc = new int[3];
//        teleporterLoc[0] = 0;
//        teleporterLoc[1] = 1;
//        teleporterLoc[2] = 2;
    }

    //Creating a map spot and new one into it
    MapSpot(MapSpot newMap){
        this.mapSpotCopy(newMap);
    }

    //Copy the variables if it is copied over
    public void mapSpotCopy(MapSpot mapSpotCopy){
        if(mapSpotCopy == null){
            this.mapSpotCopy(new MapSpot());
        }else{
            visible = mapSpotCopy.getVisible();
            seen = mapSpotCopy.getSeen();
            floor = mapSpotCopy.getFloor();
            graphics = mapSpotCopy.getGraphics();
            passable = mapSpotCopy.getPassable();
            transparent = mapSpotCopy.getTransparent();
            leftWall = mapSpotCopy.getLeftWall();
            rightWall = mapSpotCopy.getRightWall();
            wallGraphicsLeft = mapSpotCopy.getWallGraphicsLeft();
            wallGraphicsRight = mapSpotCopy.getWallGraphicsRight();
            passableLeft = mapSpotCopy.getPassableLeft();
            passableRight = mapSpotCopy.getPassableRight();
            transparentLeft = mapSpotCopy.getTransparentLeft();
            transparentRight = mapSpotCopy.getTransparentRight();
            objectGraphic = mapSpotCopy.getObjectGraphic();
            object = mapSpotCopy.getObject();
            climb = mapSpotCopy.getClimb();
            floorItems = new Inventory();
            floorItems.copyInventory(mapSpotCopy.getFloorItems());
            spawnPoint = mapSpotCopy.getSpawnPoint();
            humanSpawn = mapSpotCopy.getHumanSpawn();
//            activatiable = mapSpotCopy.getActivatiable();
//            mapSpotStorage = new MapSpotStorage(mapSpotCopy.getMapSpotStorage());
//            teleporter = mapSpotCopy.getTeleporter();
//            teleporterLoc = new int[3];
//            int tmp[] = new int[3];
//            tmp = mapSpotCopy.getTeleporterLoc();
//            for(int x = 0; x < 3; x++){
//                teleporterLoc[x] = tmp[x];
//            }
        }
    }

    //Functions for setting the variables
    public void setVisible(boolean newVisible, boolean andSeen){
        visible = newVisible;
        if((andSeen) && (newVisible)){
            seen = true;
        }
    }
    public void setSeen(boolean newSeen){
        seen = newSeen;
    }
    public void setFloor(boolean exists){
        floor = exists;
        passable = exists;
        transparent = exists;
    }
    public void setGraphics(int newGraphics){
        graphics = newGraphics;
    }
    public void setPassable(boolean newPhysics){
        passable = newPhysics;
    }
    public void setTransparent(boolean newPhysics){
        transparent = newPhysics;
    }
    public void setWallGraphicsLeft(int newWallGraphicsLeft){
        wallGraphicsLeft = newWallGraphicsLeft;
    }
    public void setWallGraphicsRight(int newWallGraphicsRight){
        wallGraphicsRight = newWallGraphicsRight;
    }
    public void setPassableLeft(boolean newPhysics){
        passableLeft = newPhysics;
    }
    public void setPassableRight(boolean newPhysics){
        passableRight = newPhysics;
    }
    public void setTransparentLeft(boolean newPhysics){
        transparentLeft = newPhysics;
    }
    public void setTransparentRight(boolean newPhysics){
        transparentRight = newPhysics;
    }
    public void setFloorItems(Inventory newFloorItems){
        floorItems.copyInventory(newFloorItems);
    }
    public void setLeftWall(boolean exists){
        leftWall = exists;
        transparentLeft = !exists;
        passableLeft = !exists;
    }
    public void setRightWall(boolean exists){
        rightWall = exists;
        transparentRight = !exists;
        passableRight = !exists;
    }
    public void setObjectGraphic(int newGraphic){
        objectGraphic = newGraphic;
    }
    public void setObject(boolean newObject){
        object = newObject;
    }
    public void setClimb(boolean newClimb){
        climb = newClimb;
    }
    public void setSpawn(boolean newSpawn){
        spawnPoint = newSpawn;
    }
    public void setHumanSpawn(boolean newStart){
        humanSpawn = newStart;
    }
//    public void setActivatiable(boolean newActivate){
//        activatiable = newActivate;
//    }
//    public void setTeleporter(boolean newTeleporter){
//        teleporter = newTeleporter;
//    }
//    public void setTeleporterLoc(int x, int y, int z){
//        teleporterLoc[0] = x;
//        teleporterLoc[1] = y;
//        teleporterLoc[2] = z;
//    }

    //Functions for returning the variables
    public boolean getVisible(){
        return visible;
    }
    public boolean getSeen(){
        return seen;
    }
    public boolean getFloor(){
        return floor;
    }
    public int getGraphics(){
        return graphics;
    }
    public boolean getPassable(){
        return passable;
    }
    public boolean getTransparent(){
        return transparent;
    }
    public int getWallGraphicsLeft(){
        return wallGraphicsLeft;
    }
    public int getWallGraphicsRight(){
        return wallGraphicsRight;
    }
    public boolean getPassableLeft(){
        return passableLeft;
    }
    public boolean getTransparentLeft(){
        return transparentLeft;
    }
    public boolean getPassableRight(){
        return passableRight;
    }
    public boolean getTransparentRight(){
        return transparentRight;
    }
    public Inventory getFloorItems(){
        return floorItems;
    }
    public boolean getLeftWall(){
        return leftWall;
    }
    public boolean getRightWall(){
        return rightWall;
    }
    public int getObjectGraphic(){
        return objectGraphic;
    }
    public boolean getObject(){
        return object;
    }
    public boolean getClimb(){
        return climb;
    }
    public boolean getSpawnPoint(){
        return spawnPoint;
    }
    public boolean getHumanSpawn(){
        return humanSpawn;
    }
//    public boolean getActivatiable(){
//        return activatiable;
//    }
//    public MapSpotStorage getMapSpotStorage(){
//        return mapSpotStorage;
//    }
//    public boolean getTeleporter(){
//        return teleporter;
//    }
//    public int[] getTeleporterLoc(){
//        return teleporterLoc;
//    }


    //Functions for increasing and changing the variables.
    public void incGraphics(int toAdd){
        graphics += toAdd;
    }
    public void incWallGraphicsLeft(int toAdd){
        wallGraphicsLeft += toAdd;
    }
    public void incWallGraphicsRight(int toAdd){
        wallGraphicsRight += toAdd;
    }
    public void addItemToFloorItems(Item toAdd){
        floorItems.addItem(toAdd);
    }
    public void changeLeftWall(){
        if(leftWall){
            leftWall = false;
        }else{
            leftWall = true;
        }
    }
    public void changeRightWall(){
        if(rightWall){
            rightWall = false;
        }else{
            rightWall = true;
        }
    }
    public void changeFloor(){
        if(floor){
            floor = false;
        }else{
            floor = true;
        }
    }
    public void incObjectGraphic(int toAdd){
        objectGraphic += toAdd;
    }

    //Gets the physics from a code number
    public void setGroundPhysicsByNumber(int newPhysics){

        if((newPhysics < 0) || (newPhysics > 4)){
            return;
        }else if(newPhysics == 4){

            System.out.println(climb);
            transparent = true;
            passable = true;
            climb = true;


        }else{
            
            climb = false;

            passable = getPassableByNumber(newPhysics);
            transparent = getTransparentByNumber(newPhysics);

        }
    }

    public void setLeftWallPhysicsByNumber(int newPhysics){

        if((newPhysics < 0) || (newPhysics > 4)){
            return;
        }else if(newPhysics == 4){


            climb = true;
        }else{

            passableLeft = getPassableByNumber(newPhysics);
            transparentLeft = getTransparentByNumber(newPhysics);

        }
    }

    public void setRightWallPhysicsByNumber(int newPhysics){

        if((newPhysics < 0) || (newPhysics > 4)){
            return;
        }else if(newPhysics == 4){
            climb = true;
        }else{

            passableRight = getPassableByNumber(newPhysics);
            transparentRight = getTransparentByNumber(newPhysics);

        }
    }

    
    private boolean getPassableByNumber(int physics){
      
            //Set the passable
            if(physics < 2){
                return true;
            }else{
                return false;
            }  
    }

    private boolean getTransparentByNumber(int physics){

            //Set the passable
            if(physics % 2 == 0){
                return true;
            }else{
                return false;
            }
    }
}
