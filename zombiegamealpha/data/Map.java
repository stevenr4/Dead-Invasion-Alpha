/*
 *
 * The class to store the map's data.
 *
 *
 * NOT SURE IF I SHOULD INCLUDE AVATARS AS MAPSPOT DATA!
 *
 *
 */

package zombiegamealpha.data;

/**
 *
 * @author spex
 */

//Import the random function for making random grass to start with
import java.util.Random;

import java.io.Serializable;

public class Map implements Serializable{

    private int xSize, ySize, zSize;//The size of the Map
    private int tmpX, tmpY, tmpZ;//Temporary variables for FOR loops
    private MapSpot mapArray[][][];//The array of Map Spots
    private MapSpot tmpArray[][][];//A Temporary array for resizing the main array
    private AvatarManager zombieAvatars;//The manager of all avatars
    private AvatarManager humanAvatars;

    public Map(){
        xSize = 50;//Set the base x size
        ySize = 50;//Set the base y size
        zSize = 1;//Set the base z size
        mapArray = new MapSpot[xSize][ySize][zSize];//Allocate space for memory
        tmpArray = new MapSpot[xSize][ySize][zSize];//Allocate space
        tmpZ = 0;//Instead of making traveledLength for loop for z, just use the one.

        //For all the Ys
        for(tmpY = 0; tmpY < ySize; tmpY++){

            //For all the Xs
            for(tmpX = 0; tmpX < xSize; tmpX++){

                //Allocate each spot individually
                mapArray[tmpX][tmpY][tmpZ] = new MapSpot();



//                if((((tmpX - (xSize/2)) * (tmpX - (xSize/2))) / (4*4)) - (((tmpY - (ySize/2)) * (tmpY - (ySize/2))) / (2*2)) <= 1){
//                    mapArray[tmpX][tmpY][tmpZ].setGraphics(1);
//                }
//
////                if(tmpY <= ( -0.2 * ((tmpX - (xSize/2)) * (tmpX - (xSize/2))) + ySize/2)){
////                    mapArray[tmpX][tmpY][tmpZ].setGraphics(10);
////                }
//
////                if(18*18 >= (tmpX - xSize/2) * (tmpX - xSize/2) + (tmpY - ySize/2) * (tmpY - ySize/2)){
////                    mapArray[tmpX][tmpY][tmpZ].setGraphics(11);
////                }
//
//                if((tmpY == ySize/2) && (tmpX % 2 == 1)){
//                    mapArray[tmpX][tmpY][tmpZ].setGraphics(4);
//                }
//
//                if((tmpX == xSize/2) && (tmpY % 2 == 1)){
//                    mapArray[tmpX][tmpY][tmpZ].setGraphics(3);
//                }






            }
        }

        zombieAvatars = new AvatarManager();
        humanAvatars = new AvatarManager();
        
    }

    public Map(int X, int Y, int Z){
        xSize = X;//Set the base x size
        ySize = Y;//Set the base y size
        zSize = Z;//Set the base z size
        mapArray = new MapSpot[xSize][ySize][zSize];//Allocate space for memory
        tmpArray = new MapSpot[xSize][ySize][zSize];//Allocate space

        //For all Zs...
        for(tmpZ = 0; tmpZ < zSize; tmpZ++){

            //For all Ys...
            for(tmpY = 0; tmpY < ySize; tmpY++){

                //For all Xs...
                for(tmpX = 0; tmpX < xSize; tmpX++){

                    //Allocate each spot individually
                    mapArray[tmpX][tmpY][tmpZ] = new MapSpot();

                    //If it is above the ground, make the floor non-existant
                    if(tmpZ > 0){
                        mapArray[tmpX][tmpY][tmpZ].setFloor(false);
                        mapArray[tmpX][tmpY][tmpZ].setTransparent(false);
                        mapArray[tmpX][tmpY][tmpZ].setPassable(false);

                    }
                }
            }
        }

        zombieAvatars = new AvatarManager();
        humanAvatars = new AvatarManager();
    }

    public Map(Map toCopy){
        xSize = toCopy.getXSize();//Set the base x size
        ySize = toCopy.getYSize();//Set the base y size
        zSize = toCopy.getZSize();//Set the base z size
        mapArray = new MapSpot[xSize][ySize][zSize];//Allocate space for memory
        tmpArray = new MapSpot[xSize][ySize][zSize];//Allocate space
        zombieAvatars = new AvatarManager();
        humanAvatars = new AvatarManager();

        for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
            for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs
                    //Copy over the mapSpot...
                    mapArray[tmpX][tmpY][tmpZ] = new MapSpot(toCopy.getMapSpot(tmpX, tmpY, tmpZ));
                }
            }
        }
    }

    //Function for getting the MapSpot from the array.
    public MapSpot getMapSpot(int xLocation, int yLocation, int zLocation){

        //If it is outside of the bounds...
        if((xLocation < 0) || (xLocation >= xSize) 
                || (yLocation < 0) || (yLocation >= ySize)
                || (zLocation < 0) || (zLocation >= zSize))
        {
            //Return null
            return null;
        }else{
            //It is inside the bounds, give the correct mapSpot
            return mapArray[xLocation][yLocation][zLocation];
        }
    }

    //Function for getting the Avatar Manager
    public AvatarManager getZombieAvatars(){
        return zombieAvatars;
    }
    public AvatarManager getHumanAvatars(){
        return humanAvatars;
    }

    //Function for clearing the Avatar Manager
    public void resetAvatarManagers(){
        zombieAvatars = new AvatarManager();
        humanAvatars = new AvatarManager();
    }

    //Functions to return the sizes.
    public int getXSize(){
        return xSize;
    }
    public int getYSize(){
        return ySize;
    }
    public int getZSize(){
        return zSize;
    }

    //Function to set the whole map's seen values to traveledLength desired value
    public void setVisibleAll(boolean set){
        for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
            for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs
                    //Set the spot's visible to the desired value
                    mapArray[tmpX][tmpY][tmpZ].setVisible(set, false);
                }
            }
        }
    }

    //CANNOT UNSEE >.< OH NOES!
    public void setSeen(boolean set){
        for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
            for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs
                    mapArray[tmpX][tmpY][tmpZ].setSeen(set);
                }
            }
        }
    }


    public void addVisible(Avatar a, boolean addSeen){
        addVisible(a.getX(), a.getY(), a.getZ(), a.getDirection(), addSeen);
    }

    public void addVisible(int xLocation, int yLocation, int zLocation, int direction, boolean addSeen){


        double totalRays = 100;//The totalRays amount of rays
        int currentTotal = (int)totalRays;//The current totalRays amount
        int length = 25;//The length of the rays..
        int radius = 10;//The radius of the iew circle

        //Set the spot the visible point is at to true
        mapArray[xLocation][yLocation][zLocation].setVisible(true, addSeen);

        //Declare add variables..
        double xAdd;
        double yAdd;
        double xLoc;
        double yLoc;

        //Set the starting to 0
        int start = 0;

        //If the angle is diagonal, the do half of 2 quaters..
        if(direction % 2 == 1){
            start  = (int)(totalRays / 2);
        }

        //These mods determine the direction that the angle is going to go...
        int xMod;
        int yMod;
        xMod = 0;
        yMod = 0;
        switch(direction){
            case 1:
            case 0:
                xMod = -1;
                yMod = -1;
                break;
            case 3:
            case 2:
                xMod = 1;
                yMod = -1;
                break;
            case 5:
            case 4:
                xMod = 1;
                yMod = 1;
                break;
            case 7:
            case 6:
                xMod = -1;
                yMod = 1;
                break;
        }

        
        //For all is in the current total..
        for(int i = start; i <= currentTotal; i++){
            //If the direction is up left or down right...
            if(yMod * xMod == -1){
                //Apply the correct Y and X velocities
                xAdd = (i) / totalRays * xMod;
                yAdd = ((totalRays - i) / totalRays) * yMod;
            }else{
                //Apply the correct Y and X velocities
                xAdd = ((totalRays - i) / totalRays) * xMod;
                yAdd = (i) / totalRays * yMod;
            }

            //Reset the X location and Y location
            xLoc = (double)xLocation;
            yLoc = (double)yLocation;

            //If it is going to left, start at the right side of the square
            if(xMod == -1){
                xLoc += 0.9;
            }

            //If it is going down, start at the top of the square
            if(yMod == -1){
                yLoc += 0.9;
            }
            
            //For each spot down the array.....
            for(int traveledLength = 0; traveledLength < length; traveledLength++){

                //If it hits traveledLength wall going to the right...
                if((xAdd > 0) && !(mapArray[(int)xLoc][(int)yLoc][zLocation].getTransparentRight())){
                    mapArray[(int)xLoc][(int)yLoc][zLocation].setVisible(true, addSeen);
                    break;
                }

                //If it hits traveledLength wall going down
                if((yAdd > 0) && !mapArray[(int)xLoc][(int)yLoc][zLocation].getTransparentLeft()){
                    mapArray[(int)xLoc][(int)yLoc][zLocation].setVisible(true, addSeen);
                    if((int)xLoc < xSize - 1){
                        if((!mapArray[(int)xLoc + 1][(int)yLoc][zLocation].getTransparentLeft()) &&
                                !(mapArray[(int)xLoc + 1][(int)yLoc][zLocation].getTransparentRight())){
                            mapArray[(int)xLoc + 1][(int)yLoc][zLocation].setVisible(true, addSeen);
                        }
                    }
                    break;
                }

                //If it hits traveledLength wall going up
                if((yAdd < 0) && ((int) yLoc > 0)){
                    if(!mapArray[(int)xLoc][(int)yLoc - 1][zLocation].getTransparentLeft()){

                        //Not sure what this does..............
//                        if(((int)xLoc > 0 + 1) && ((int)yLoc > 0)){
//                            if((!mapArray[(int)xLoc - 2][(int)yLoc][zLocation].getTransparentRight()) &&
//                                    (!mapArray[(int)xLoc - 1][(int)yLoc - 1][zLocation].getTransparentLeft())){
//
//                                mapArray[(int)xLoc - 1][(int)yLoc][zLocation].setVisible(true, addSeen);
//                            }
//                        }
                        break;
                    }
                }
                
                //If it hits a wall going to the left.....
                if((xAdd < 0) && ((int) xLoc > 0)){
                    if(!mapArray[(int)xLoc - 1][(int)yLoc][zLocation].getTransparentRight()){
                        
                        break;
                    }
                }
                
                //Move the location due to the set velocities
                xLoc += xAdd;
                yLoc += yAdd;


                //If the ray reached outside the bounds of the map....
                if(((int)yLoc < 0) || ((int)yLoc > ySize - 1) || ((int)xLoc < 0) || ((int)xLoc > xSize - 1)) {
                    break;
                }


                /*
                 * WALL CORNER EXCEPTIONS
                 */

                //If it is not in the starting location due to light cutting when standing in a corner...
                if(((int)xLoc != xLocation) && ((int)yLoc != yLocation)){


                    if((xAdd > 0) && (yAdd > 0) && 
                            ((int)xLoc > 0) && ((int)yLoc > 0)){//Going down through a corner
                        if((!mapArray[(int)xLoc - 1][(int)yLoc][zLocation].getTransparentRight()) &&
                                (!mapArray[(int)xLoc][(int)yLoc - 1][zLocation].getTransparentLeft())){

                            break;
                        }
                    }else if((xAdd < 0) && (yAdd < 0)){//Going UP through a corner
                        if((!mapArray[(int)xLoc][(int)yLoc][zLocation].getTransparentRight()) &&
                                (!mapArray[(int)xLoc][(int)yLoc][zLocation].getTransparentLeft())){

                            break;
                        }
                    }else if((xAdd < 0) && (yAdd > 0) && ((int)yLoc > 0)){//Going Left through a corner
                        if((!mapArray[(int)xLoc][(int)yLoc][zLocation].getTransparentRight()) &&
                                (!mapArray[(int)xLoc][(int)yLoc - 1][zLocation].getTransparentLeft())){

                            break;
                        }
                    }else if((xAdd > 0) && (yAdd < 0) && ((int)xLoc > 0)){//Going Right through a corner
                        if((!mapArray[(int)xLoc - 1][(int)yLoc][zLocation].getTransparentRight()) &&
                                (!mapArray[(int)xLoc][(int)yLoc][zLocation].getTransparentLeft())){

                            break;
                        }
                    }
                }

                //If it is not transparent, set it so it is visible
                if(!mapArray[(int)xLoc][(int)yLoc][zLocation].getTransparent()){
                    mapArray[(int)xLoc][(int)yLoc][zLocation].setVisible(true, addSeen);
                    break;
                }

                //If it is outside the circle
                if(((xLoc - xLocation)*(xLoc - xLocation)) +
                        ((yLoc - yLocation)*(yLoc - yLocation)) >
                        radius * radius){
                    break;
                }

                //Set visible to true and move on down the ray
                mapArray[(int)xLoc][(int)yLoc][zLocation].setVisible(true, addSeen);
            }

            //If we hit the end of all the rays and we need to do a second half..
            if((i == (int)totalRays) && (direction % 2 == 1) && (start != 0)){
                //Reset I
                i = 0;

                //Set it currentTotal to start
                currentTotal = start;

                //Reset start to 0
                start = 0;

                //Change velocity direction
                switch(direction){
                    case 1:
                        xMod = 1;
                        yMod = -1;
                        break;
                    case 3:
                        xMod = 1;
                        yMod = 1;
                        break;
                    case 5:
                        xMod = -1;
                        yMod = 1;
                        break;
                    case 7:
                        xMod = -1;
                        yMod = -1;
                        break;

                }
            }
        }
    }

    //Is there a person right in front of....
    public Avatar getAvatarInFrontOf(Avatar inputAvatar){

        //Declare variables to find the direction
        int xMod = 0;
        int yMod = 0;

        //Get the position off of the direction
        switch (inputAvatar.getDirection()){
            case 0:
                xMod--;
                yMod--;
                break;
            case 1:
                yMod--;
                break;
            case 2:
                xMod++;
                yMod--;
                break;
            case 3:
                xMod++;
                break;
            case 4:
                xMod++;
                yMod++;
                break;
            case 5:
                yMod++;
                break;
            case 6:
                xMod--;
                yMod++;
                break;
            case 7:
                xMod--;
                break;
        }

        //If there is a zombie there...
        if(zombieAvatars.getAvatar(zombieAvatars.getAvatarIndex(
                inputAvatar.getX() + xMod,
                inputAvatar.getY() + yMod,
                inputAvatar.getZ())) != null){
            return zombieAvatars.getAvatar(zombieAvatars.getAvatarIndex(
                inputAvatar.getX() + xMod,
                inputAvatar.getY() + yMod,
                inputAvatar.getZ()));

            //Else if there is a zombie there
        }else if(humanAvatars.getAvatar(humanAvatars.getAvatarIndex(
                inputAvatar.getX() + xMod,
                inputAvatar.getY() + yMod,
                inputAvatar.getZ())) != null){
            return humanAvatars.getAvatar(humanAvatars.getAvatarIndex(
                inputAvatar.getX() + xMod,
                inputAvatar.getY() + yMod,
                inputAvatar.getZ()));
        }

        //Return null if there is nothing there
        return null;
    }

    //If there is a visible human
    public int[][] findVisibleAvatar(boolean human){

        int totalVisible = 0;

        for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
            for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs

                    if(mapArray[tmpX][tmpY][tmpZ].getVisible()){

                        if(human){
                            if(humanAvatars.avatarHere(tmpX, tmpY, tmpZ)){
                                totalVisible++;
                            }
                        }else{
                            if(zombieAvatars.avatarHere(tmpX, tmpY, tmpZ)){
                                totalVisible++;
                            }
                        }
                    }
                }
            }
        }

        //If we didnt find any
        if(totalVisible == 0){
            return null;
        }else{

            //Declare variable to send...
            int avatarLocations[][] = new int[totalVisible][3];

            totalVisible = 0;

            for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
                for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                    for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs
                        if(mapArray[tmpX][tmpY][tmpZ].getVisible()){

                            if(human){
                                if(humanAvatars.avatarHere(tmpX, tmpY, tmpZ)){
                                    //Save location
                                    avatarLocations[totalVisible][0] = tmpX;
                                    avatarLocations[totalVisible][1] = tmpY;
                                    avatarLocations[totalVisible][2] = tmpZ;
                                    totalVisible++;
                                }
                            }else{
                                if(zombieAvatars.avatarHere(tmpX, tmpY, tmpZ)){
                                    //Save location
                                    avatarLocations[totalVisible][0] = tmpX;
                                    avatarLocations[totalVisible][1] = tmpY;
                                    avatarLocations[totalVisible][2] = tmpZ;

                                    totalVisible++;
                                }
                            }
                        }
                    }
                }
            }

            return avatarLocations;

        }

    }

    //If the spot in front of an avatar is clear...
    public boolean isClear(Avatar a){
        return isClear(a.getX(),a.getY(),a.getZ(),a.getDirection());
    }

    //If the spot in front of this location is clear....
    public boolean isClear(int x, int y, int z, int direction){

        //Check the direction
        switch(direction){
            //Looking up (-X, -Y)
            case 0:
                if((x > 0) && (y > 0)){
                    if((getMapSpot(x - 1, y, z).getPassableRight()) &&//Right wall on left spot
                            (getMapSpot(x, y - 1, z).getPassableLeft())  &&//Left wall on right spot
                            (getMapSpot(x - 1, y - 1, z).getPassableRight())  &&//Right and left wall of spot above
                            (getMapSpot(x - 1, y - 1, z).getPassableLeft()) &&

                            (getMapSpot(x - 1,y, z).getPassable())  &&//Left spot passable
                            (getMapSpot(x, y - 1, z).getPassable())  &&//Right spot passable
                            (getMapSpot(x - 1, y - 1, z).getPassable()) &&//Above spot passable

                            (!zombieAvatars.avatarHere(x - 1, y, z)) &&//No avatars occupying the spots
                            (!zombieAvatars.avatarHere(x, y - 1, z)) &&
                            (!zombieAvatars.avatarHere(x - 1, y - 1, z)) &&

                            (!humanAvatars.avatarHere(x - 1, y, z)) &&//No avatars occupying the spots
                            (!humanAvatars.avatarHere(x, y - 1, z)) &&
                            (!humanAvatars.avatarHere(x - 1, y - 1, z))){
                        return true;
                    }
                }
                break;
                
                //Looking up-right (-Y)
            case 1:
                if(y > 0){
                    if(getMapSpot(x, y - 1, z).getPassableLeft() &&//Left wall on right spot
                            getMapSpot(x, y - 1, z).getPassable() &&//Right spot passable
                            (!zombieAvatars.avatarHere(x, y - 1, z)) &&
                            (!humanAvatars.avatarHere(x, y - 1, z))){//Right spot not occupied
                        return true;
                    }
                }
                break;

                //Looking right (X -Y)
            case 2:
                if((x < getXSize() - 1) && (y > 0)){
                    if((getMapSpot(x, y - 1, z).getPassableLeft())  &&
                            (getMapSpot(x, y, z).getPassableRight())  &&
                            (getMapSpot(x, y - 1, z).getPassableRight())  &&
                            (getMapSpot(x + 1, y - 1, z).getPassableLeft())  &&

                            (getMapSpot(x + 1, y, z).getPassable())  &&
                            (getMapSpot(x, y - 1, z).getPassable())  &&
                            (getMapSpot(x + 1, y - 1, z).getPassable()) &&

                            (!zombieAvatars.avatarHere(x + 1, y, z)) &&//No avatars occupying the spots
                            (!zombieAvatars.avatarHere(x, y - 1, z)) &&
                            (!zombieAvatars.avatarHere(x + 1, y - 1, z)) &&

                            (!humanAvatars.avatarHere(x + 1, y, z)) &&//No avatars occupying the spots
                            (!humanAvatars.avatarHere(x, y - 1, z)) &&
                            (!humanAvatars.avatarHere(x + 1, y - 1, z))){
                        return true;
                    }
                }
                break;

                //Looking down-right (X)
            case 3:
                if(x < getXSize() - 1){
                    if(getMapSpot(x, y, z).getPassableRight() &&
                            getMapSpot(x + 1, y, z).getPassable() &&
                            (!zombieAvatars.avatarHere(x + 1, y, z)) &&
                            (!humanAvatars.avatarHere(x + 1, y, z))){
                        return true;
                    }
                }
                break;

                //Looking down (X, Y)
            case 4:
                if((x < getXSize() - 1) && (y < getYSize() - 1)){
                    if((getMapSpot(x + 1, y, z).getPassableLeft())  &&
                            (getMapSpot(x, y + 1, z).getPassableRight())  &&
                            (getMapSpot(x, y, z).getPassableRight())  &&
                            (getMapSpot(x, y, z).getPassableLeft()) &&

                            (getMapSpot(x, y + 1, z).getPassable())  &&
                            (getMapSpot(x + 1, y, z).getPassable())  &&
                            (getMapSpot(x + 1, y + 1, z).getPassable()) &&

                            (!zombieAvatars.avatarHere(x + 1, y, z)) &&//No avatars occupying the spots
                            (!zombieAvatars.avatarHere(x, y + 1, z)) &&
                            (!zombieAvatars.avatarHere(x + 1, y + 1, z)) &&

                            (!humanAvatars.avatarHere(x + 1, y, z)) &&//No avatars occupying the spots
                            (!humanAvatars.avatarHere(x, y + 1, z)) &&
                            (!humanAvatars.avatarHere(x + 1, y + 1, z))){
                        return true;
                    }
                }
                break;

                //Looking down-left (Y)
            case 5:
                if(y < getYSize() - 1){
                    if(getMapSpot(x, y, z).getPassableLeft() &&
                            getMapSpot(x, y + 1, z).getPassable() &&
                            (!zombieAvatars.avatarHere(x, y + 1, z)) &&
                            (!humanAvatars.avatarHere(x, y + 1, z))){
                        return true;
                    }
                }
                break;

                //Looking left (-X, Y)
            case 6:
                if((x > 0) && (y < getYSize() - 1)){
                    if((getMapSpot(x - 1, y, z).getPassableRight())  &&
                            (getMapSpot(x, y, z).getPassableLeft())  &&
                            (getMapSpot(x - 1, y + 1, z).getPassableRight())  &&
                            (getMapSpot(x - 1, y, z).getPassableLeft())  &&

                            (getMapSpot(x, y + 1, z).getPassable())  &&
                            (getMapSpot(x - 1, y, z).getPassable())  &&
                            (getMapSpot(x - 1, y + 1, z).getPassable()) &&

                            (!zombieAvatars.avatarHere(x - 1, y, z)) &&//No avatars occupying the spots
                            (!zombieAvatars.avatarHere(x, y + 1, z)) &&
                            (!zombieAvatars.avatarHere(x - 1, y + 1, z)) &&

                            (!humanAvatars.avatarHere(x - 1, y, z)) &&//No avatars occupying the spots
                            (!humanAvatars.avatarHere(x, y + 1, z)) &&
                            (!humanAvatars.avatarHere(x - 1, y + 1, z))){
                        return true;
                    }
                }
                break;

                //Looking up-left (-X)
            case 7:
                if(x > 0){
                    if(getMapSpot(x - 1, y, z).getPassableRight() &&
                            getMapSpot(x - 1, y, z).getPassable() &&
                            (!zombieAvatars.avatarHere(x - 1, y, z)) &&
                            (!humanAvatars.avatarHere(x - 1, y, z))){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    //To change the size of the map
    public void resize(int x, int y, int z){

        //If it is smaller than minimun, set to minimum
        if (x < 10)
            x = 10;
        if(y < 10)
            y = 10;
        if(z < 1)
            z = 1;

        //Create and Size a temporary array to hold the data
        tmpArray = new MapSpot[x][y][z];


        //Copy over all of the data to the temporary array
        for(tmpZ = 0; ((tmpZ < zSize) && (tmpZ < z)); tmpZ++){//For all Zs
            for(tmpY = 0; ((tmpY < ySize) && (tmpY < y)); tmpY++){//For all Ys
                for(tmpX = 0; ((tmpX < xSize) && (tmpX < x)); tmpX++){//For all Xs
                    tmpArray[tmpX][tmpY][tmpZ] = new MapSpot(mapArray[tmpX][tmpY][tmpZ]);//copy each
                                                    //spot individually.
                }
            }
        }

        //Set the new sizes...
        xSize = x;
        ySize = y;
        zSize = z;
        //Size the new array
        mapArray = new MapSpot[xSize][ySize][zSize];

        //For all of the parts in the new array
        for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
            for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs

                    //If there is nothing there
                    if(tmpArray[tmpX][tmpY][tmpZ] == null){
                        //Make it a new Spot
                        mapArray[tmpX][tmpY][tmpZ] = new MapSpot();

                        //If it is not ground level
                        if(tmpZ != 0){
                            //Make the floor non-existant
                            mapArray[tmpX][tmpY][tmpZ].changeFloor();
                        }
                    }else{
                        //Copy the spot over to the used array
                        mapArray[tmpX][tmpY][tmpZ] = new MapSpot(tmpArray[tmpX][tmpY][tmpZ]);
                    }
                }
            }
        }
    }

    //Return the number of all available spawn points
    public int getAmountOfAvailableSpawnPoints(boolean human){

        int amount = 0;

        for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
            for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs
                    
                    //If it is traveledLength spawn point...
                    if(mapArray[tmpX][tmpY][tmpZ].getSpawnPoint()){


                        //If it is a human and we are checking for humans...
                        if((mapArray[tmpX][tmpY][tmpZ].getHumanSpawn()) &&
                            (human) && 
                            (!zombieAvatars.avatarHere(tmpX, tmpY, tmpZ)) &&
                            (!humanAvatars.avatarHere(tmpX, tmpY, tmpZ))){
                            amount++;
                        }else if((!mapArray[tmpX][tmpY][tmpZ].getHumanSpawn()) &&
                                (!human) && 
                                (!zombieAvatars.avatarHere(tmpX, tmpY, tmpZ)) &&
                                (!humanAvatars.avatarHere(tmpX, tmpY, tmpZ))){
                            amount++;
                        }
                    }
                }
            }
        }

        return amount;
    }
    
    //Return traveledLength random location of an available spawn point
    public int[] getRandomAvaliableSpawn(boolean human){

        Random r = new Random();
        int randomNumber = r.nextInt(getAmountOfAvailableSpawnPoints(human));
        
        int amount = -1;
        int loc[] = new int[3];
        loc[0] = 0;
        loc[1] = 0;
        loc[2] = 0;

        for(tmpZ = 0; tmpZ < zSize; tmpZ++){//For all Zs
            for(tmpY = 0; tmpY < ySize; tmpY++){//For all Ys
                for(tmpX = 0; tmpX < xSize; tmpX++){//For all Xs

                    //If it is traveledLength spawn point...
                    if(mapArray[tmpX][tmpY][tmpZ].getSpawnPoint()){


                        //If it is a human and we are checking for humans...
                        if((mapArray[tmpX][tmpY][tmpZ].getHumanSpawn()) &&
                            (human) &&
                            (!zombieAvatars.avatarHere(tmpX, tmpY, tmpZ)) &&
                            (!humanAvatars.avatarHere(tmpX, tmpY, tmpZ))){
                            amount++;
                        }else if((!mapArray[tmpX][tmpY][tmpZ].getHumanSpawn()) &&
                                (!human) &&
                                (!zombieAvatars.avatarHere(tmpX, tmpY, tmpZ)) &&
                                (!humanAvatars.avatarHere(tmpX, tmpY, tmpZ))){
                            amount++;
                        }
                    }

                    if(amount == randomNumber){
                        loc[0] = tmpX;
                        loc[1] = tmpY;
                        loc[2] = tmpZ;
                        System.out.println("SPAWN SECTION: X-" + tmpX + " Y-" + tmpY + " Z-" + tmpZ);
                        return loc;
                    }

                }
            }
        }

        return loc;
    }

    //Function for getting if an avatar is a human or not
    public boolean avatarHereHuman(int x, int y, int z){

        //If it is a human, return it is a human
        if(humanAvatars.avatarHere(x, y, z)){
            return true;
        }

        //If it is not a human, return it is not a human
        return false;
    }
}
