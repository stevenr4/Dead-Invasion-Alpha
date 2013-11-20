/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.ui;

/**
 *
 * @author spex
 */
import java.awt.image.*;//Used for the Back Buffer
import java.awt.Graphics;//Used for Graphics and Images
import java.awt.Graphics2D;//Used for Graphics and Images
import java.awt.Color;//Used for Graphics and Images
import java.awt.Font;//Font
import zombiegamealpha.data.MapSpot;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.data.items.*;
import zombiegamealpha.data.PlayerHuman;


//import java.util.Iterator;
public class PrintManager {

    private ImageStorage loadedImages;//Load up the images.

    private Graphics2D g2d;//Graphics used to paint the backbuffer

    //use this as a double buffer
    private BufferedImage backBuffer;

    //Use this to print transparent images onto the buffer
    private BufferedImage alphaBuffer;

    private Graphics2D alpha;
    private int Width;
    private int Height;

    private final int spacing = 20;//Spacing used for calculating space.


    private int xPrint;//The X location for the spot being printed
    private int yPrint;//The Y location for the spot being printed

    private RescaleOp rop;
    private RescaleOp backgroundRop;
    private RescaleOp dop;

    private int xPlus;
    private int yPlus;
    private boolean xturn;

    PrintManager(int width, int height){
        
        //Set the width and height
        Width = width;
        Height = height;

        //Load up the images
        loadedImages = new ImageStorage();

        //create the back buffer for smooth graphics
        backBuffer = new BufferedImage(Width,Height,BufferedImage.TYPE_INT_RGB);

        //create the alpha buffer
        alphaBuffer = new BufferedImage(80,120,BufferedImage.TYPE_INT_ARGB);
        //Set the graphics to draw to the back buffer
        g2d = backBuffer.createGraphics();

        //Set the graphics to draw on the alpha buffer
        alpha = alphaBuffer.createGraphics();

           /* Create a rescale filter op that makes the image 50% opaque */
        float[] scales = { 1f, 1f, 1f, 0.7f };
        float[] offsets = new float[4];
        backgroundRop = new RescaleOp(scales, offsets, null);
        scales[3] = 0.5f;
        rop = new RescaleOp(scales, offsets, null);
        scales[0] = 0.3f;
        scales[1] = 0.3f;
        scales[2] = 0.3f;
        dop = new RescaleOp(scales, offsets, null);
    }
    
    public void cls(){
        //erase the background (print a big black box on the screen)
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, Width, Height);
    }

    //Get the x location to print a map spot
    public int getXPrint(int x, int y, int screenX){
        return ((x * spacing * 2)
                    - (y * spacing * 2)
                    - (2 * spacing)
                    - screenX);
    }

    public int getYPrint(int x, int y, int z, int screenY, int screenZ){
        return((x * spacing)
                    + (y * spacing)
                    - (z * spacing * 5)
                    - screenY) + (screenZ * spacing * 5);
    }

    //Print the map.
    public void printMap(PrintManagerVariables pmv){


        for(int z = 0; z <= pmv.screenZ; z++){//For all floors from bottom to top
            for(int y = 0; y < pmv.currentMap.getYSize(); y++){//For all Ys
                for(int x = 0; x < pmv.currentMap.getXSize(); x++){//For all Xs
                    if(pmv.currentMap.getMapSpot(x, y, z) == null){
                        break;
                    }

                    //Get the x location of the X tile in isometric view
                    xPrint = getXPrint(x,y,pmv.screenX);

                    //Get the y location of the Y tile in isometric view
                    yPrint = getYPrint(x,y,z,pmv.screenY,pmv.screenZ);


                    //If were printing outside the bounds
                    if((xPrint < -spacing * 4) || (xPrint > Width) ||
                            (yPrint < -spacing * 2) || (yPrint > Height + spacing * 5)){
                        continue;
                    }
                    
                    //If it has a floor...
                    if (pmv.currentMap.getMapSpot(x, y, z).getFloor()) {
                            //Draw the Image. Floor Images is an array of images and
                            //we are geting the one that matches the spot's graphics
                            g2d.drawImage(
                                    loadedImages.getFloorImages(pmv.currentMap.getMapSpot(x, y, z).getGraphics()),
                                    xPrint, yPrint, null);
                    }

                    //If there is an object in the spot we are drawing
                    if(pmv.currentMap.getMapSpot(x,y,z).getObject()){
                        g2d.drawImage(
                                loadedImages.getObjectImage(
                                pmv.currentMap.getMapSpot(x,y,z).getObjectGraphic())
                                , xPrint, yPrint - (spacing * 5)
                                , null);
                    }

                    //If the Curser is in the spot we are drawing
                    if ((pmv.cursorX == x) && (pmv.cursorY == y)) {
                        //Draw the curser's front
                        drawCurser(xPrint,yPrint - (spacing*5), false);
                    }
                    
                    //If the user wants to print the walls
                    if(pmv.walls){
                        //If there is a Left Wall in the spot we are drawing..
                        if(pmv.currentMap.getMapSpot(x,y,z).getLeftWall()){
                            //Draw the wall
                            g2d.drawImage(
                                    loadedImages.getLeftWallImages
                                    (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsLeft())
                                    ,xPrint, yPrint - (spacing * 4)
                                    , null);
                        }

                        //If there is a Right Wall int the spot we are drawing
                        if(pmv.currentMap.getMapSpot(x,y,z).getRightWall()){
                            //Draw the wall
                            g2d.drawImage(
                                    loadedImages.getRightWallImages
                                    (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsRight())
                                    ,xPrint + (spacing * 2), yPrint - (spacing * 4),null);
                        }
                    }else{
                        printWallAsLine(pmv.currentMap.getMapSpot(x,y,z), xPrint, yPrint);
                    }


                    //If the Curser is in the spot we are drawing
                    if ((pmv.cursorX == x) && (pmv.cursorY == y)) {
                        if(z == pmv.screenZ){
                            //Draw the curser's back
                            drawCurser(xPrint,yPrint - (spacing*5), true);
                        }else{
                            //Draw the curser's bottom
                            g2d.drawImage(loadedImages.getCurserBlue(), xPrint, yPrint - (spacing * 5), null);
                        }
                    }
                }
            }
        }

        if(!pmv.walls){
            g2d.drawImage(loadedImages.getNoWalls(), 10, Height - 240, null);
        }
    }

    //Print the floor
    public void printFloor(PrintManagerVariables pmv)
    {

        int z = pmv.screenZ;
        for(int y = 0; y < pmv.currentMap.getYSize(); y++){//For all Ys
            for(int x = 0; x < pmv.currentMap.getXSize(); x++){//For all Xs
                if(pmv.currentMap.getMapSpot(x, y, z) == null){
                    break;
                }

                //Get the x location of the X tile in isometric view
                xPrint = getXPrint(x,y,pmv.screenX);

                //Get the y location of the Y tile in isometric view
                yPrint = getYPrint(x,y,z,pmv.screenY,pmv.screenZ);

                //If were printing outside the bounds
                if((xPrint < -spacing * 4) || (xPrint > Width) ||
                        (yPrint < -spacing * 2) || (yPrint > Height + spacing * 5)){
                    continue;
                }

                //If it has a floor...
                if (pmv.currentMap.getMapSpot(x, y, z).getFloor()) {
                        //Draw the Image. Floor Images is an array of images and
                        //we are geting the one that matches the spot's graphics
                        g2d.drawImage(
                                loadedImages.getFloorImages(pmv.currentMap.getMapSpot(x, y, z).getGraphics()),
                                xPrint, yPrint, null);
                }

                //If there is an object in the spot we are drawing
                if(pmv.currentMap.getMapSpot(x,y,z).getObject()){
                    g2d.drawImage(
                            loadedImages.getObjectImage(
                            pmv.currentMap.getMapSpot(x,y,z).getObjectGraphic())
                            , xPrint, yPrint - (spacing * 5)
                            , null);
                }

                //If the Curser is in the spot we are drawing
                if ((pmv.cursorX == x) && (pmv.cursorY == y)) {
                    //Draw the curser's back
                    drawCurser(xPrint,yPrint - (spacing*5), false);
                }

                //If the user wants to print the walls
                if(pmv.walls){
                    //If there is a Left Wall in the spot we are drawing..
                    if(pmv.currentMap.getMapSpot(x,y,z).getLeftWall()){
                        //Draw the wall
                        g2d.drawImage(
                                loadedImages.getLeftWallImages
                                (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsLeft())
                                ,xPrint, yPrint - (spacing * 4)
                                , null);
                    }

                    //If there is a Right Wall int the spot we are drawing
                    if(pmv.currentMap.getMapSpot(x,y,z).getRightWall()){
                        //Draw the wall
                        g2d.drawImage(
                                loadedImages.getRightWallImages
                                (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsRight())
                                ,xPrint + (spacing * 2), yPrint - (spacing * 4),null);
                    }
                }else{
                    printWallAsLine(pmv.currentMap.getMapSpot(x,y,z), xPrint, yPrint);
                }


                //If the Curser is in the spot we are drawing
                if ((pmv.cursorX == x) && (pmv.cursorY == y)) {
                    //Draw the curser's front
                    drawCurser(xPrint,yPrint - (spacing*5), true);
                }
            }
        }

        if(!pmv.walls){
            g2d.drawImage(loadedImages.getNoWalls(), 10, Height - 240, null);
        }
    }



    //Print the map.
    public void printSeen(PrintManagerVariables pmv){

        for(int z = 0; z <= pmv.screenZ; z++){//For all floors from bottom to top
            for(int y = 0; y < pmv.currentMap.getYSize(); y++){//For all Ys
                for(int x = 0; x < pmv.currentMap.getXSize(); x++){//For all Xs

                    //Get the x location of the X tile in isometric view
                    xPrint = getXPrint(x,y,pmv.screenX);

                    //Get the y location of the Y tile in isometric view
                    yPrint = getYPrint(x,y,z,pmv.screenY,pmv.screenZ);

                    //If the spot has been seen before.
                    if (pmv.currentMap.getMapSpot(x,y,z).getSeen()) {


                        //If were printing outside the bounds
                        if((xPrint < -spacing * 4) || (xPrint > Width) ||
                                (yPrint < -spacing * 2) || (yPrint > Height + spacing * 5)){
                            continue;
                        }

                        //If it is currently beign looked at.....
                        if (pmv.currentMap.getMapSpot(x, y, z).getFloor()) {
                                //Draw the Image. Floor Images is an array of images and
                                //we are geting the one that matches the spot's graphics
                                g2d.drawImage(
                                        loadedImages.getFloorImages(pmv.currentMap.getMapSpot(x, y, z).getGraphics()),
                                        xPrint, yPrint, null);
                        }

                        //If there is an Object in the spot we are drawing
                        if(pmv.currentMap.getMapSpot(x,y,z).getObject()){
                            g2d.drawImage(
                                    loadedImages.getObjectImage(
                                    pmv.currentMap.getMapSpot(x,y,z).getObjectGraphic())
                                    , xPrint, yPrint - (spacing * 5)
                                    , null);
                        }

                        //If there is a Left Wall in the spot we are drawing..
                        if(pmv.currentMap.getMapSpot(x,y,z).getLeftWall()){
                            //Draw the wall
                            g2d.drawImage(
                                    loadedImages.getLeftWallImages
                                    (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsLeft())
                                    ,xPrint, yPrint - (spacing * 4)
                                    , null);
                        }

                        //If there is a Right Wall in the spot we are drawing
                        if(pmv.currentMap.getMapSpot(x,y,z).getRightWall()){
                            //Draw the wall
                            g2d.drawImage(
                                    loadedImages.getRightWallImages
                                    (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsRight())
                                    ,xPrint + (spacing * 2), yPrint - (spacing * 4),null);
                        }


                        //This part is for walls above the spots we have seen...
                    }else{

                        //If there is a spot to the left of the spot we are drawing...
                        if(y < pmv.currentMap.getYSize() - 1){
                            //If there is a Left Wall in the spot we are drawing and the spot in front of it has been seen
                            if((pmv.currentMap.getMapSpot(x,y,z).getLeftWall()) &&
                                    (pmv.currentMap.getMapSpot(x, y + 1, z).getSeen())){
                                //Draw the wall
                                g2d.drawImage(
                                        loadedImages.getLeftWallImages
                                        (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsLeft())
                                        ,xPrint, yPrint - (spacing * 4)
                                        , null);
                            }
                        }


                        //If there is a spot to the Right of the spot we are drawing...
                        if(x < pmv.currentMap.getXSize() - 1){
                            //If there is a Right Wall in the spot we are drawing and the spot in front has been seen
                            if((pmv.currentMap.getMapSpot(x,y,z).getRightWall()) &&
                                    (pmv.currentMap.getMapSpot(x + 1, y, z)).getSeen()){
                                //Draw the wall
                                g2d.drawImage(
                                        loadedImages.getRightWallImages
                                        (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsRight())
                                        ,xPrint + (spacing * 2), yPrint - (spacing * 4),null);
                            }
                        }
                    }
                }
            }
        }
    }

    //Dark out the background
    public void fade(){

        alphaBuffer = new BufferedImage(Width, Height, BufferedImage.TYPE_INT_ARGB);
        alpha = alphaBuffer.createGraphics();
        alpha.setColor(Color.BLACK);
        alpha.fillRect(0, 0, Width, Height);
        g2d.drawImage(alphaBuffer, backgroundRop, 0, 0);
        alpha.dispose();
    }
//
//    public void printVisible(Map currentMap, int screenX, int screenY, int screenZ){
//        this.currentMap = currentMap;
//        this.screenX = screenX;
//        this.screenY = screenY;
//        this.screenZ = screenZ;
//        printVisible(false,false,false);
//    }
//
//    public void printVisible(Map currentMap, int screenX, int screenY, int screenZ, Avatar myAvatar){
//        this.currentMap = currentMap;
//        this.screenX = screenX;
//        this.screenY = screenY;
//        this.screenZ = screenZ;
//        this.myAvatar = myAvatar;
//        printVisible(true,false,false);
//    }
//
//    public void printVisible(Map currentMap, int screenX, int screenY, int screenZ, int curserX, int curserY){
//        this.currentMap = currentMap;
//        this.screenX = screenX;
//        this.screenY = screenY;
//        this.screenZ = screenZ;
//        this.curserX = curserX;
//        this.curserY = curserY;
//        printVisible(false,true,false);
//    }
//
//    public void printVisible(Map currentMap, int screenX, int screenY, int screenZ, int curserX, int curserY, Avatar myAvatar){
//        this.currentMap = currentMap;
//        this.screenX = screenX;
//        this.screenY = screenY;
//        this.screenZ = screenZ;
//        this.curserX = curserX;
//        this.curserY = curserY;
//        this.myAvatar = myAvatar;
//        printVisible(true,true,false);
//    }

    public void printVisible(PrintManagerVariables pmv){

        boolean rightDone = true;
        boolean leftDone = true;
        boolean objectDone = true;
        boolean visible = false;
        boolean seen = false;
        
        for(int z = 0; z <= pmv.screenZ; z++){//For all floors from bottom to top
            for(int y = 0; y < pmv.currentMap.getYSize(); y++){//For all Ys
                for(int x = 0; x < pmv.currentMap.getXSize(); x++){//For all Xs



                    xPlus = 0;
                    yPlus = 0;
                    leftDone = !pmv.currentMap.getMapSpot(x,y,z).getLeftWall();
                    rightDone = !pmv.currentMap.getMapSpot(x,y,z).getRightWall();
                    objectDone = !pmv.currentMap.getMapSpot(x,y,z).getObject();
                    visible = pmv.currentMap.getMapSpot(x,y,z).getVisible();
                    seen = pmv.currentMap.getMapSpot(x,y,z).getSeen();

                    xturn = true;

                    //Get the x location of the X tile in isometric view
                    xPrint = getXPrint(x,y,pmv.screenX);

                    //Get the y location of the Y tile in isometric view
                    yPrint = getYPrint(x,y,z,pmv.screenY,pmv.screenZ);


                    //If were printing outside the bounds
                    if((xPrint < -spacing * 4) || (xPrint > Width) ||
                            (yPrint < -spacing * 2) || (yPrint > Height + spacing * 5)){
                        continue;
                    }

                    //If the spot is currently being looking at..
                    if (visible) {
                        //If it is currently beign looked at.....
                            if (pmv.currentMap.getMapSpot(x, y, z).getFloor()) {
                                //Draw the Image. Floor Images is an array of images and
                                //we are geting the one that matches the spot's graphics
                                g2d.drawImage(
                                        loadedImages.getFloorImages(pmv.currentMap.getMapSpot(x, y, z).getGraphics()),
                                        xPrint, yPrint, null);
                        }
                    

                        if (pmv.printAvatar) {
                            //If the avatar is in the spot we are drawing...
                            if ((pmv.myAvatar.getX() == x) && (pmv.myAvatar.getY() == y) && (pmv.myAvatar.getZ() == z)) {
    //                            //Draw the avatar image above the tile.
    //                            g2d.drawImage(loadedImages.getLegsImages(pmv.myAvatar.getLegsId(), pmv.myAvatar.getDirection()),
    //                                    xPrint, yPrint - (spacing * 4), null);
    //                            g2d.drawImage(loadedImages.getBodyImages(pmv.myAvatar.getBodyId(), pmv.myAvatar.getDirection()),
    //                                    xPrint, yPrint - (spacing * 4), null);
    //                            g2d.drawImage(loadedImages.getHeadImages(pmv.myAvatar.getHeadId(), pmv.myAvatar.getDirection()),
    //                                    xPrint, yPrint - (spacing * 4), null);

                                g2d.drawImage(loadedImages.getPieceImage(pmv.myAvatar.getDirection(), true),
                                        xPrint, yPrint - (spacing * 4), null);

                                if (pmv.myAvatar.getWeaponId() == 0) {

                                } else {
                                    //Print the weapon
                                }

                                //Print the avatar's Health
                                if(pmv.printAvatarHealth){
                                    printAvatarHealth(pmv.myAvatar);
                                }
                            }
                        }

                        ////////////////////////////////////////////////////////////////////////////

                        if (pmv.printZombies) {
                            for(Avatar myAvatar : pmv.zombies.getAllAvatars()){
                                //If the avatar is in the spot we are drawing...
                                if ((myAvatar.getX() == x) && (myAvatar.getY() == y) && (myAvatar.getZ() == z)) {
        //                            //Draw the avatar image above the tile.
        //                            g2d.drawImage(loadedImages.getLegsImages(pmv.myAvatar.getLegsId(), pmv.myAvatar.getDirection()),
        //                                    xPrint, yPrint - (spacing * 4), null);
        //                            g2d.drawImage(loadedImages.getBodyImages(pmv.myAvatar.getBodyId(), pmv.myAvatar.getDirection()),
        //                                    xPrint, yPrint - (spacing * 4), null);
        //                            g2d.drawImage(loadedImages.getHeadImages(pmv.myAvatar.getHeadId(), pmv.myAvatar.getDirection()),
        //                                    xPrint, yPrint - (spacing * 4), null);

                                    g2d.drawImage(loadedImages.getPieceImage(myAvatar.getDirection(), false),
                                            xPrint, yPrint - (spacing * 4), null);

                                    if (myAvatar.getWeaponId() == 0) {

                                    } else {
                                        //Print the weapon
                                    }

                                    //Print the avatar's Health
                                    if(pmv.printAvatarHealth){
                                        printAvatarHealth(myAvatar);

                                    }
                                }
                            }
                        }
                        if (pmv.printHumans) {
                            for(Avatar myAvatar : pmv.humans.getAllAvatars()){
                                //If the avatar is in the spot we are drawing...
                                if ((myAvatar.getX() == x) && (myAvatar.getY() == y) && (myAvatar.getZ() == z)) {
        //                            //Draw the avatar image above the tile.
        //                            g2d.drawImage(loadedImages.getLegsImages(pmv.myAvatar.getLegsId(), pmv.myAvatar.getDirection()),
        //                                    xPrint, yPrint - (spacing * 4), null);
        //                            g2d.drawImage(loadedImages.getBodyImages(pmv.myAvatar.getBodyId(), pmv.myAvatar.getDirection()),
        //                                    xPrint, yPrint - (spacing * 4), null);
        //                            g2d.drawImage(loadedImages.getHeadImages(pmv.myAvatar.getHeadId(), pmv.myAvatar.getDirection()),
        //                                    xPrint, yPrint - (spacing * 4), null);

                                    g2d.drawImage(loadedImages.getPieceImage(myAvatar.getDirection(), true),
                                            xPrint, yPrint - (spacing * 4), null);

                                    if (myAvatar.getWeaponId() <= 0) {

                                    } else {
                                        //Print the weapon
                                    }

                                    //Print the avatar's Health
                                    if(pmv.printAvatarHealth){
                                        printAvatarHealth(myAvatar);
                                    }
                                }
                            }
                        }
                    }

                    //If the Curser is in the spot we are drawing
                    if ((pmv.cursorX == x) && (pmv.cursorY == y) && pmv.cursor) {
                        //Draw the curser
                        drawCurser(xPrint,yPrint - (spacing*5), false);
                    }

                    if(visible){
                        if(pmv.currentMap.getMapSpot(x,y,z).getObject()){
                            g2d.drawImage(
                                    loadedImages.getObjectImage(
                                    pmv.currentMap.getMapSpot(x,y,z).getObjectGraphic())
                                    , xPrint, yPrint - (spacing * 5)
                                    , null);
                        }

                        
                        //If there is a Left Wall in the spot we are drawing..
                        if (!leftDone) {
                            //Draw the wall

                            alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                            alpha = alphaBuffer.createGraphics();
                            alpha.drawImage(
                                    loadedImages.getLeftWallImages(pmv.currentMap.getMapSpot(x, y, z).getWallGraphicsLeft()), 0, 0//yPrint - (spacing * 4)
                                    , null);
                            g2d.drawImage(alphaBuffer, rop, xPrint, yPrint - (spacing * 4));
                            alpha.dispose();
                        }
                        
                        //The Left wall has been printed
                        leftDone = true;

                    } else if(pmv.currentMap.getMapSpot(x,(y+1),z) != null) {
                        if ((pmv.currentMap.getMapSpot(x,(y+1),z).getVisible()) && (!leftDone)) {
                            g2d.drawImage(
                                    loadedImages.getLeftWallImages(pmv.currentMap.getMapSpot(x, y, z).getWallGraphicsLeft()), xPrint, yPrint - (spacing * 4), null);
                            pmv.currentMap.getMapSpot(x,y,z).setSeen(true);

                            //The Left wall has been printed
                            leftDone = true;
                        }
                    }


                    if (visible) {

                        //If there is a Right Wall int the spot we are drawing
                        if(!rightDone){
                            //Draw the wall

                            alphaBuffer = new BufferedImage(80,120,BufferedImage.TYPE_INT_ARGB);
                            alpha = alphaBuffer.createGraphics();
                            alpha.drawImage(
                                    loadedImages.getRightWallImages
                                    (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsRight())
                                    ,spacing * 2, 0/*yPrint - (spacing * 4)*/,null);//xPrint + spacing * 2 , yPrint - (spacing * 4), null);

                            g2d.drawImage(alphaBuffer, rop, xPrint, yPrint - (spacing * 4));
                            alpha.dispose();
                            //The Right wall has been printed
                            rightDone = true;
                        }

                    } else if(pmv.currentMap.getMapSpot((x+1),y,z) != null) {
                        if ((pmv.currentMap.getMapSpot((x+1),y,z).getVisible()) && (!rightDone)) {
                        //Draw the wall
                        g2d.drawImage(
                                loadedImages.getRightWallImages
                                (pmv.currentMap.getMapSpot(x,y,z).getWallGraphicsRight())
                                ,xPrint + (spacing * 2), yPrint - (spacing * 4),null);
                                pmv.currentMap.getMapSpot(x,y,z).setSeen(true);
                            //The Right wall has been printed
                            rightDone = true;
                        }

                    }

                    if (seen) {
                        //a for loop to test if the spots above selected wall is true
                        for (int i = 0; i < 6; i++) {
                            if (xturn) {
                                xPlus--;
                                xturn = false;
                            } else {
                                yPlus--;
                                xturn = true;
                            }
                            if ((x + yPlus > 0) && (y + xPlus > 0)) {
                                if (pmv.currentMap.getMapSpot(x + yPlus, y + xPlus, z).getVisible() &&
                                        !objectDone &&
                                        !pmv.currentMap.getMapSpot(x, y, z).getVisible()) {

                                    //Draw the object

                                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                                    alpha = alphaBuffer.createGraphics();
                                    alpha.drawImage(
                                            loadedImages.getObjectImage(pmv.currentMap.getMapSpot(x, y, z).getObjectGraphic()), 0, 0, null);

                                    g2d.drawImage(alphaBuffer, dop, xPrint, yPrint - (spacing * 5));
                                    alpha.dispose();

                                    //Set the object done to true, cause it is done

                                    objectDone = true;

                                }
                            }
                            if ((x + yPlus > 0) && (y + xPlus > 0) && (!rightDone)) {
                                if (pmv.currentMap.getMapSpot(x + yPlus, y + xPlus, z).getVisible()) {

                                    rightDone = true;

                                    //Draw the wall

                                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                                    alpha = alphaBuffer.createGraphics();
                                    alpha.drawImage(
                                            loadedImages.getRightWallImages(pmv.currentMap.getMapSpot(x, y, z).getWallGraphicsRight()), spacing * 2, 0, null);

                                    g2d.drawImage(alphaBuffer, dop, xPrint, yPrint - (spacing * 4));
                                    alpha.dispose();

                                }
                            }
                            if ((x + xPlus > 0) && (y + yPlus > 0) && (!leftDone)) {
                                if (pmv.currentMap.getMapSpot(x + xPlus, y + yPlus, z).getVisible()) {

                                    leftDone = true;

                                    //Draw the wall

                                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                                    alpha = alphaBuffer.createGraphics();
                                    alpha.drawImage(
                                            loadedImages.getLeftWallImages(pmv.currentMap.getMapSpot(x, y, z).getWallGraphicsLeft()), 0, 0, null);

                                    g2d.drawImage(alphaBuffer, dop, xPrint, yPrint - (spacing * 4));
                                    alpha.dispose();

                                }
                            }
                        }
                    }

                    //If the Curser is in the spot we are drawing
                    if ((pmv.cursorX == x) && (pmv.cursorY == y) && pmv.cursor) {
                        //Draw the curser
                        drawCurser(xPrint,yPrint - (spacing*5), true);
                    }
                }
            }
        }
    }

//    public void printPhysics(Map currentMap, int screenX, int screenY, int screenZ, boolean walls){
//        this.currentMap = currentMap;
//        this.screenX = screenX;
//        this.screenY = screenY;
//        this.screenZ = screenZ;
//        this.curserX = -1;
//        this.curserY = -1;
//        printPhysicsMod(walls,false);
//    }
//
//    public void printPhysics(Map currentMap, int screenX, int screenY, int screenZ, int curserX, int curserY, boolean walls){
//        this.currentMap = currentMap;
//        this.screenX = screenX;
//        this.screenY = screenY;
//        this.screenZ = screenZ;
//        this.curserX = curserX;
//        this.curserY = curserY;
//        printPhysicsMod(walls,false);
//    }


    private int booleansToGraphic(boolean transparent, boolean passable, boolean climb){

        
        int tmp = 0;

        if(passable){
            if (transparent) {
                tmp = 1;
            } else {
                tmp = 2;
            }
            
            if(climb){
                tmp = 5;
            }
        }else{
            if (transparent) {
                tmp = 3;
            } else {
                tmp = 4;
            }
        }
                
        return tmp;
    }

    public void printPhysics(PrintManagerVariables pmv){
        int floorGraphic = 0;
        int wallGraphicLeft = 0;
        int wallGraphicRight = 0;
        for(int z = 0; z <= pmv.screenZ; z++){//For all floors from bottom to top
            for(int y = 0; y < pmv.currentMap.getYSize(); y++){//For all Ys
                for(int x = 0; x < pmv.currentMap.getXSize(); x++){//For all Xs

                    //Get the x location of the X tile in isometric view
                    xPrint = getXPrint(x,y, pmv.screenX);

                    //Get the y location of the Y tile in isometric view
                    yPrint = getYPrint(x,y,z,pmv.screenY, pmv.screenZ);



                    //If were printing outside the bounds
                    if((xPrint < -spacing * 4) || (xPrint > Width) ||
                            (yPrint < -spacing * 2) || (yPrint > Height + spacing * 5)){
                        continue;
                    }


                    //Get the graphics for the floor
                    floorGraphic = booleansToGraphic(pmv.currentMap.getMapSpot(x, y, z).getTransparent(),
                            pmv.currentMap.getMapSpot(x, y, z).getPassable(),
                            pmv.currentMap.getMapSpot(x, y, z).getClimb());

                    //Get the graphics for the left Wall
                    wallGraphicLeft = booleansToGraphic(pmv.currentMap.getMapSpot(x, y, z).getTransparentLeft(),
                            pmv.currentMap.getMapSpot(x, y, z).getPassableLeft(),
                            false);

                    //Get the graphics for the right wall
                    wallGraphicRight = booleansToGraphic(pmv.currentMap.getMapSpot(x, y, z).getTransparentRight(),
                            pmv.currentMap.getMapSpot(x, y, z).getPassableRight(),
                            false);

                    if (pmv.currentMap.getMapSpot(x, y, z).getFloor()) {
                        //Draw the Image. Floor Images is an array of images and
                        //we are geting the one that matches the spot's graphics
                        g2d.drawImage(
                                loadedImages.getFloorImages(floorGraphic),
                                xPrint, yPrint, null);
                    }

                    //If the Curser is in the spot we are drawing
                    if ((pmv.cursorX == x) && (pmv.cursorY == y) && pmv.cursor) {
                        //Draw the curser
                        drawCurser(xPrint,yPrint - (spacing*5), false);
                    }

                    //If there is a spawn point in the spot
                    if(pmv.currentMap.getMapSpot(x, y, z).getSpawnPoint()){
                        //If it is a human spawn
                        if(pmv.currentMap.getMapSpot(x, y, z).getHumanSpawn()){

                            //Draw the human spawn point
                            g2d.drawImage(loadedImages.getPieceImage(3, true),
                                    xPrint, yPrint - (spacing * 4), null);
                        }else{

                            //Draw the zombie spawn point
                            g2d.drawImage(loadedImages.getPieceImage(3, false),
                                    xPrint, yPrint - (spacing * 4), null);
                        }
                    }

                    //If walls are going to be shown
                    if (pmv.walls) {
                        //If there is a left wall
                        if (pmv.currentMap.getMapSpot(x, y, z).getLeftWall()) {
                            //Draw the left wall
                            g2d.drawImage(
                                    loadedImages.getLeftWallImages(wallGraphicLeft), xPrint, yPrint - (spacing * 4), null);
                        }

                        //If there is a right wall
                        if (pmv.currentMap.getMapSpot(x, y, z).getRightWall()) {
                            //Draw the right wall
                            g2d.drawImage(
                                    loadedImages.getRightWallImages(wallGraphicRight), xPrint + (spacing * 2), yPrint - (spacing * 4), null);
                        }
                    }else{
                        printWallAsLine(pmv.currentMap.getMapSpot(x,y,z), xPrint, yPrint);
                    }


                    //If there is an object in the spot, draw a rectangle
                    if(pmv.currentMap.getMapSpot(x,y,z).getObject()){
                        g2d.setColor(Color.BLACK);
                        g2d.fillRect(xPrint + (spacing * 1), yPrint + (spacing / 2)
                                , (spacing * 2), spacing);
                    }


                    //If the Curser is in the spot we are drawing
                    if ((pmv.cursorX == x) && (pmv.cursorY == y) && pmv.cursor) {
                        //Draw the curser
                        drawCurser(xPrint,yPrint - (spacing*5), true);
                    }
                    
                }
            }
        }

        if(!pmv.walls){
            g2d.drawImage(loadedImages.getNoWalls(), 10, Height - 240, null);
        }
    }

    public void printMenu(String name, String[] options, int selection, int itemYSize){

        //int itemYSize = 40;

        int redYLoc;
        redYLoc = (selection * (itemYSize + 15)) + 200;

        
        g2d.setColor(Color.RED);
        g2d.fill3DRect((Width/2 - ((options[selection].length())*24)/2) - 10, redYLoc,
                ((options[selection].length())*24) + 20, itemYSize + 10, true);

        g2d.setColor(Color.WHITE);

        g2d.setFont(new Font("Courier New", Font.PLAIN, 54));
        g2d.drawString(name,
                (Width/2 - ((name.length())*32)/2), 130);


        g2d.setFont(new Font("Courier New", Font.PLAIN, itemYSize));
        for(int i = 0; i < options.length; i++){
            g2d.drawString(options[i],
                    Width/2 - (options[i].length()*24)/2, 240 + ((itemYSize + 15) * i));
        }
    }


    public void printFadeBacking(){


        //Draw the backing
        alphaBuffer = new BufferedImage(Width - 200, Height - 100, BufferedImage.TYPE_INT_ARGB);
        alpha = alphaBuffer.createGraphics();
        alpha.setColor(Color.BLACK);
        alpha.fillRect(0, 0, Width - 200, Height - 100);
        g2d.drawImage(alphaBuffer, backgroundRop, 100, 50);
        alpha.dispose();
    }

    //Print the HUD for the physics
    public void printEditHudPhysics(int selection, int section){
        g2d.drawImage(loadedImages.getEditHud(), 0,0, null);
        g2d.setColor(Color.RED);
        int middle = (Width / 2);
        g2d.fillRect(middle - 82, Height - 150, 165, 145);

        //For all the seleciton spots
        for(int i = -2; i < 3; i++){

            //If it is OUTSIDE the range of the floor.....
            if((selection + i < 0) || (selection + i >= 5)){

                //Draw the curser
                g2d.drawImage(loadedImages.getCurserImage(false), middle - 80 + (165 * i), Height - (spacing * 7) - 10, null);
                g2d.drawImage(loadedImages.getCurserImage(true), middle - 80 + (165 * i),  Height - (spacing * 7) - 10, null);
            }else{

                //Get the floor image associated to the number
                g2d.drawImage(loadedImages.getFloorImages(selection + i + 1), middle - 80 + (165 * i), Height - (spacing * 2) - 10, null);

                //If it is not the currently selected one, fade it out
                if(section != 0){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();
                    alpha.drawImage(loadedImages.getFloorImages(selection + i + 1),
                            0, 0, null);
                    g2d.drawImage(alphaBuffer, dop, middle - 80 + (165 * i), Height - (spacing * 2) - 10);
                    alpha.dispose();
                }

                //Get the Spawn point image associated to the selection
                if(selection + i == 0){
                    g2d.drawImage(loadedImages.getPieceImage(3, true),
                            middle - 80 + (165 * i), Height - (spacing * 6) - 10, null);
                } else if(selection + i == 1){
                    g2d.drawImage(loadedImages.getPieceImage(3, false),
                            middle - 80 + (165 * i), Height - (spacing * 6) - 10, null);
                }

                //If it is not the currently selected one, fade it out
                if(section != 3){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();

                    //Get the Spawn point image associated to the selection
                    if(selection + i == 0){
                        alpha.drawImage(loadedImages.getPieceImage(3, true),
                                0, 0, null);
                    } else if(selection + i == 1){
                        alpha.drawImage(loadedImages.getPieceImage(3, false),
                                0, 0, null);
                    }
                    g2d.drawImage(alphaBuffer, dop, middle - 80 + (165 * i), Height - (spacing * 6) - 10);
                    alpha.dispose();
                }
            }

            //If the selecion is OUTSIDE of the wall space....
            if((selection + i < 0) || (selection + i >= 4)){
                //Draw the curser
                g2d.drawImage(loadedImages.getCurserImage(false), middle + (165 * i), Height - (spacing * 7) - 10, null);
                g2d.drawImage(loadedImages.getCurserImage(true), middle + (165 * i), Height - (spacing * 7) - 10, null);
            }else{

                //Get and draw the LEFT Wall image
                g2d.drawImage(loadedImages.getLeftWallImages(selection + i + 1),middle + (165 * i),Height - (spacing * 6) - 10,null);

                //If it is not the selection, fade it out
                if(section != 1){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();
                    alpha.drawImage(loadedImages.getLeftWallImages(selection + i + 1),
                            0, 0, null);
                    g2d.drawImage(alphaBuffer, dop, middle + (165 * i),Height - (spacing * 6) - 10);
                    alpha.dispose();
                }

                //Get and draw the RIGHT wall image
                g2d.drawImage(loadedImages.getRightWallImages(selection + i + 1),middle + 40 + (165 * i),Height - (spacing * 6) - 10,null);

                //If it is not the selected one, fade it out
                if(section != 2){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();
                    alpha.drawImage(loadedImages.getRightWallImages(selection + i + 1),
                            0, 0, null);
                    g2d.drawImage(alphaBuffer, dop, middle + 40 + (165 * i),Height - (spacing * 6) - 10);
                    alpha.dispose();
                }
            }

            if((selection + i >= 0) && (selection + i <= 4)){
                g2d.setColor(Color.BLACK);
                g2d.fillRect((Width / 2) - 80 + (i * 165), 525, 160, 20);
                g2d.fillRect((Width / 2) - 80 + (i * 165), 565, 160, 20);
                
                //If it is one of the spawn points
                if(selection + i < 2){
                    g2d.fillRect((Width / 2) - 80 + (i * 165), 485, 160, 20);
                }else if(selection + i == 4){
                    g2d.fillRect((Width / 2) - 80 + (i * 165), 485, 160, 20);
                }
            }

            g2d.setFont(new Font("Arial", Font.PLAIN, 20));
            g2d.setColor(Color.WHITE);
            switch(selection + i){
                case 0:
                    g2d.drawString("HumanSpawn", (Width / 2) - 80 + (i * 165), 500);
                    g2d.drawString("Passable", (Width / 2) - 80 + (i * 165), 540);
                    g2d.drawString("Transparent", (Width/2) - 80 + (i * 165), 580);
                    break;
                case 1:
                    g2d.drawString("ZombieSpawn", (Width / 2) - 80 + (i * 165), 500);
                    g2d.drawString("Passable", (Width / 2) - 80 + (i * 165), 540);
                    g2d.drawString("Not Transparent", (Width/2) - 80 + (i * 165), 580);
                    break;
                case 2:
                    g2d.drawString("Not Passable", (Width / 2) - 80 + (i * 165), 540);
                    g2d.drawString("Transparent", (Width/2) - 80 + (i * 165), 580);
                    break;
                case 3:
                    g2d.drawString("Not Passable", (Width / 2) - 80 + (i * 165), 540);
                    g2d.drawString("Not Transparent", (Width/2) - 80 + (i * 165), 580);
                    break;
                case 4:
                    g2d.setColor(Color.WHITE);
                    g2d.drawString("Can Climb", (Width / 2) - 80 + (i * 165), 500);
                    g2d.drawString("Passable", (Width / 2) - 80 + (i * 165), 540);
                    g2d.drawString("Transparent", (Width/2) - 80 + (i * 165), 580);
                    break;

            }
        }
    }

    //Print the hud for the map editor
    public void printEditHudVisual(int selection, int section){
        g2d.drawImage(loadedImages.getEditHud(), 0,0, null);
        g2d.setColor(Color.RED);
        int middle = (Width / 2);
        g2d.fillRect(middle - 82, Height - 150, 165, 145);

        for(int i = -2; i < 3; i++){
            if((selection + i < 0) || (selection + i >= loadedImages.getTotalFloorImages())){
                g2d.drawImage(loadedImages.getCurserImage(true), middle - 80 + (165 * i), Height - (spacing * 7) - 10, null);
                g2d.drawImage(loadedImages.getCurserImage(false), middle - 80 + (165 * i), Height - (spacing * 7) - 10, null);
            }else{

                g2d.drawImage(loadedImages.getFloorImages(selection + i),middle - 80 + (165 * i),Height - (spacing * 2) - 10,null);
                if(section != 0){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();
                    alpha.drawImage(loadedImages.getFloorImages(selection + i), 
                            0, 0, null);
                    g2d.drawImage(alphaBuffer, dop, middle - 80 + (165 * i),Height - (spacing * 2) - 10);
                    alpha.dispose();
                }
            }
            

            if((selection + i < 0) || (selection + i >= loadedImages.getTotalWallImages())){
                g2d.drawImage(loadedImages.getCurserImage(true), middle + (165 * i), Height - (spacing * 7) - 10, null);
                g2d.drawImage(loadedImages.getCurserImage(false), middle + (165 * i), Height - (spacing * 7) - 10, null);
            }else{
                
                g2d.drawImage(loadedImages.getLeftWallImages(selection + i), middle + (165 * i),Height - (spacing * 6) - 10,null);
                if(section != 1){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();
                    alpha.drawImage(loadedImages.getLeftWallImages(selection + i),
                            0, 0, null);
                    g2d.drawImage(alphaBuffer, dop, middle + (165 * i),Height - (spacing * 6) - 10);
                    alpha.dispose();
                }

                g2d.drawImage(loadedImages.getRightWallImages(selection + i),middle + 40 + (165 * i),Height - (spacing * 6) - 10,null);
                if(section != 2){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();
                    alpha.drawImage(loadedImages.getRightWallImages(selection + i),
                            0, 0, null);
                    g2d.drawImage(alphaBuffer, dop, middle + 40 + (165 * i),Height - (spacing * 6) - 10);
                    alpha.dispose();
                }
            }

            if((selection + i >= 0) && (selection + i < loadedImages.getTotalObjectImages())){
                g2d.drawImage(loadedImages.getObjectImage(selection + i), middle - 80 + (165 * i), Height - (spacing * 7) - 10, null);
                if(section != 3){
                    alphaBuffer = new BufferedImage(80, 120, BufferedImage.TYPE_INT_ARGB);
                    alpha = alphaBuffer.createGraphics();
                    alpha.drawImage(loadedImages.getObjectImage(selection + i),
                            0, 0, null);
                    g2d.drawImage(alphaBuffer, dop, middle - 80 + (165 * i), Height - (spacing * 7) - 10);
                    alpha.dispose();
                }
            }
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////

    //Print the singleplayer hud
    public void printSinglePlayerHud(Avatar player, PlayerHuman ph){

        //Print the images
        g2d.drawImage(loadedImages.getHumanHudLeft(),0,Height - 180,null);
        g2d.drawImage(loadedImages.getHumanHudRight(), Width - 200, Height - 180, null);

        //Print Health bar
        //Get the percentage of health left
        double perc = (double)player.getHealth() / (double)player.getMaxHealth();

        //Set the foreground color
        g2d.setColor(Color.getHSBColor((float)(perc * 0.35), (float)0.5, 1));
        //Print the bar
        g2d.fillRect(45, Height - 145,
                (int)(perc * (176)),1);

        g2d.setColor(Color.getHSBColor((float)(perc * 0.35), (float)0.9, (float)0.8));
        //Print the bar
        g2d.fillRect(43, Height - 144,
                (int)(perc * (180)),13);
        g2d.setColor(Color.getHSBColor((float)(perc * 0.35), (float)0.5, (float)0.5));
        //Print the bar
        g2d.fillRect(45, Height - 131,
                (int)(perc * (176)),1);



        //Print TimeUnit Bar
        perc = (double)player.getTimeUnits() / (double)player.getMaxTimeUnits();

        //Set the foreground color
        g2d.setColor(Color.getHSBColor((float)1 - (float)(perc * 0.4), (float)0.5, 1));
        //Print the bar
        g2d.fillRect(45, Height - 120,
                (int)(perc * (176)),1);

        g2d.setColor(Color.getHSBColor((float)1 - (float)(perc * 0.4), (float)0.9, (float)0.8));
        //Print the bar
        g2d.fillRect(43, Height - 119,
                (int)(perc * (180)),13);
        g2d.setColor(Color.getHSBColor((float)1 - (float)(perc * 0.4), (float)0.5, (float)0.5));
        //Print the bar
        g2d.fillRect(45, Height - 106,
                (int)(perc * (176)),1);



        //Print the time unit/Health

        g2d.setFont(new Font("Courier New", Font.PLAIN, 15));
        g2d.setColor(Color.BLACK);
        g2d.drawString(player.getHealth() + " / " + player.getMaxHealth(), 51, Height - 132);
        g2d.drawString(player.getTimeUnits() + " / " + player.getMaxTimeUnits(), 51, Height - 107);
        g2d.setColor(Color.WHITE);
        g2d.drawString(player.getHealth() + " / " + player.getMaxHealth(), 50, Height - 133);
        g2d.drawString(player.getTimeUnits() + " / " + player.getMaxTimeUnits(), 50, Height - 108);



        //Print the hand items
        g2d.setFont(new Font("Courier New", Font.PLAIN, 20));

        //If there is nothing in the left hand
        if(ph.getLeftHand() == null){
            //Draw nothing
            g2d.drawString("- Nothing -", 50, Height - 50);
        }else{
            //If it is stackable
            if(ph.getLeftHand().getStackable() == true){
                //Print the name with the amount
                g2d.drawString(ph.getLeftHand().getName() + " X" + ph.getLeftHand().getAmount(), 50, Height - 50);
            }else{
                //Print just the name
                g2d.drawString(ph.getLeftHand().getName(), 50, Height - 50);
            }

            //If the thing is a projectile
            if(ph.getLeftHand() instanceof Projectile){
                //If it has no ammo in it
                if(((Projectile)ph.getLeftHand()).getCurrentAmmo() == null){
                    //Print empty
                    g2d.drawString("- No Ammo! -", 50, Height - 20);
                }else{
                    //Print the ammo
                    g2d.drawString(((Projectile)ph.getLeftHand()).getCurrentAmmo().getName() +
                            " X" + ((Projectile)ph.getLeftHand()).getCurrentAmmo().getAmount(), 50, Height - 20);
                }
            }
        }

        //If there is nothing in the right hand
        if(ph.getRightHand() == null){
            //Draw nothing
            g2d.drawString("- Nothing -", 277, Height - 50);
        }else{
            //If it is stackable
            if(ph.getRightHand().getStackable() == true){
                g2d.drawString(ph.getRightHand().getName() + " X" + ph.getLeftHand().getAmount(), 277, Height - 50);
            }else{
                g2d.drawString(ph.getRightHand().getName(), 277, Height - 50);
            }

            //If the thing is a projectile
            if(ph.getRightHand() instanceof Projectile){
                //If it has no ammo in it
                if(((Projectile)ph.getRightHand()).getCurrentAmmo() == null){
                    //Print empty
                    g2d.drawString("- No Ammo! -", 277, Height - 20);
                }else{
                    //Print the ammo
                    g2d.drawString(((Projectile)ph.getRightHand()).getCurrentAmmo().getName() +
                            "  X " + ((Projectile)ph.getRightHand()).getCurrentAmmo().getAmount(), 277, Height - 20);
                }
            }
        }


    }

    private void printObject(int graphic, int xLoc, int yLoc){

    }

    private void printWallAsLine(MapSpot spotToPrint, int xPrint, int yPrint){
        for (int i = 0; i < 3; i++) {
            if(i == 0){
                g2d.setColor(Color.WHITE);
            }else if(i == 1){
                g2d.setColor(Color.RED);
            }else{
                g2d.setColor(Color.BLACK);
            }
            if (spotToPrint.getLeftWall()) {
                g2d.drawLine(xPrint, yPrint + spacing + i - 3, xPrint + (spacing * 2), yPrint + (spacing * 2) + i - 3);
            }
            if (spotToPrint.getRightWall()) {
                g2d.drawLine(xPrint + (spacing * 4), yPrint + spacing + i - 4, xPrint + (spacing * 2), yPrint + (spacing * 2) + i - 4);
            }
        }
    }

    public void printEraseMode(){
        g2d.setColor(Color.RED);
        g2d.drawString("ERASE MODE TURNED ON", 39, 99);
        g2d.setColor(Color.WHITE);
        g2d.drawString("ERASE MODE TURNED ON", 40, 100);
    }

    public void printScreen(Graphics g){
        //Print the screen
        g.drawImage(backBuffer,0,0,null);
    }

    private void drawCurser(int xLoc, int yLoc, boolean front){

        g2d.drawImage(loadedImages.getCurserImage(front),
            xPrint, yPrint - (spacing * 5), null);

    }

    public void printLoadingBar(String title, int number, int maxNumber){

        //Print the text
        printText(Width / 4, Height / 4, title);

        //Set the background color
        g2d.setColor(Color.GRAY);

        //Print the background
        g2d.fillRect(Width / 4, Height / 2,
                Width / 2,40);

        //Get the percentage of health left
        double perc = (double)number / (double)maxNumber;

        //Set the foreground color
        g2d.setColor(Color.getHSBColor((float)(perc * 0.35), 1, 1));

        //Print the foreground
        g2d.fillRect((Width / 4) + 2, (Height / 2) + 2,
                (int)(((Width / 2) - 4) * perc),36);
    }

    public void printText(int xLoc, int yLoc, String text){

        g2d.setColor(Color.WHITE);
        g2d.drawString(text, xLoc, yLoc);
    }

    public void printItemStats(Item item, int x, int y){



        int yLoc = 0;
        int size = 10;

        if(item == null){
            return;
        }

        g2d.setColor(Color.GRAY);

        g2d.fillRect(x,y - (size * 2),170, 200);

        
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Courier New", Font.PLAIN, size));

        g2d.drawString(item.getName(),
                x, y + yLoc);
        yLoc += size;

        if(item instanceof Ammo){
            g2d.drawString("Item type: Ammo",
                    x, y + yLoc);
            yLoc += size;
        }else if(item instanceof Misc){
            g2d.drawString("Item type: Miscellaneous",
                    x, y + yLoc);
            yLoc += size;
        }else if(item instanceof UseAble){
            if(item instanceof Healing){
                g2d.drawString("Item type: Healing",
                        x, y + yLoc);
                yLoc += size;
            }else if(item instanceof Weapon){
                if(item instanceof Melee){
                    g2d.drawString("Item type: Melee",
                            x, y + yLoc);
                    yLoc += size;
                }else if(item instanceof Projectile){
                    g2d.drawString("Item type: Projectile",
                            x, y + yLoc);
                    yLoc += size;
                }
            }
        }else if(item instanceof Armor){

            g2d.drawString("Item type: Armor",
                    x, y + yLoc);
            yLoc += size;
        }



        g2d.drawString("Value: " + item.getValue(),
                x, y + yLoc);
        yLoc += size;

        if(item.getStackable() == true){

            g2d.drawString("Amount: " + item.getAmount(),
                    x, y + yLoc);
            yLoc += size;
        }


        if(item instanceof Ammo){
            Ammo a = (Ammo)item;
            g2d.drawString("Ammo Type: " + Ammo.getAmmoTypeName(a.getAmmoType()),
                    x, y + yLoc);
            yLoc += size;
            g2d.drawString("Damage: " + a.getDamage(),
                    x, y + yLoc);
            yLoc += size;
        }else if(item instanceof Misc){
            Misc m = (Misc)item;
            if(m.getQuestItem()){
                g2d.drawString("-Quest Item-",
                        x, y + yLoc);
                yLoc += size;
            }
        }else if(item instanceof UseAble){

            UseAble u = (UseAble)item;
            g2d.drawString("TU Cost: " + u.getTimeUnits(),
                    x, y + yLoc);
            yLoc += size;


            if(item instanceof Healing){
                Healing h = (Healing)item;
                g2d.drawString("Healing Power: " + h.getPower(),
                        x, y + yLoc);
                yLoc += size;
            }else if(item instanceof Weapon){
                Weapon w = (Weapon)item;
                g2d.drawString("Base Damage: " + w.getDamage(),
                        x, y + yLoc);
                yLoc += size;

                if(item instanceof Melee){
                    Melee m = (Melee)item;
                    if(m.getBlade()){
                        g2d.drawString("Blade Weapon",
                                x, y + yLoc);
                        yLoc += size;
                    }else{
                        g2d.drawString("Blunt Weapon",
                                x, y + yLoc);
                        yLoc += size;
                    }
                }else if(item instanceof Projectile){
                    Projectile p = (Projectile)item;

                    g2d.drawString("Accuracy: " + p.getAccuracy() + "%",
                            x, y + yLoc);
                    yLoc += size;

                    g2d.drawString("Ammo Used: " + Ammo.getAmmoTypeName(p.getAmmoType()),
                            x, y + yLoc);
                    yLoc += size;

                    g2d.drawString("Ammo Capacity: " + p.getAmmoCapacity(),
                            x, y + yLoc);
                    yLoc += size;


                    g2d.drawString("TU Reload: " + p.getReloadingTimeUnits(),
                            x, y + yLoc);
                    yLoc += size;

                    if(p.getCurrentAmmo() == null){
                        g2d.drawString("Loaded Ammo: EMPTY",
                                x, y + yLoc);
                        yLoc += size;

                    }else{

                        g2d.drawString("Loaded Ammo: " + p.getCurrentAmmo().getName()
                                + " x" + p.getCurrentAmmo().getAmount(),
                                x, y + yLoc);
                        yLoc += size;

                        g2d.drawString("Ammo Damage: " + p.getCurrentAmmo().getDamage(),
                                x, y + yLoc);
                        yLoc += size;

                        g2d.drawString("Combined Damage: " + p.getCombinedDamage(),
                                x, y + yLoc);
                        yLoc += size;
                    }

                }
            }
        }else if(item instanceof Armor){

            Armor a = (Armor)item;

            g2d.drawString("Armor Type: " + Armor.getArmorTypeName(a.getType()),
                    x, y + yLoc);
            yLoc += size;
            
            g2d.drawString("Defence: " + a.getDefenceMax(),
                    x, y + yLoc);
            yLoc += size;
            
        }

    }


    private void printAvatarHealth(Avatar a){


        //Get the percentage of health left
        double perc = (double)a.getHealth() / (double)a.getMaxHealth();

        //Set the foreground color
        g2d.setColor(Color.getHSBColor((float)(perc * 0.35), 1, 1));

        //Print the bar
        g2d.fillRect(xPrint + 3, yPrint - (spacing * 4) + 1,
                (int)(perc * (spacing * 4 - 7)),8);


        //Print the Casing
        g2d.drawImage(loadedImages.getHealthBarCase(),
                xPrint, yPrint - (spacing * 4), null);
    }

    public void printItemActionMenu(String name, String[] options, int selection, int xLoc, int yLoc){

        int itemYSize = 15;
        int itemSpacing = 5;

        int redYLoc;
        redYLoc = (selection * ((itemYSize) + itemSpacing)) + yLoc - 15;


        g2d.setColor(Color.RED);
        g2d.fill3DRect(xLoc, redYLoc,
                200, itemYSize + itemSpacing, true);

        g2d.setColor(Color.WHITE);

        g2d.setFont(new Font("Courier New", Font.PLAIN, 20));

        g2d.drawString(name,
                xLoc + 3, yLoc - 20);


        g2d.setFont(new Font("Courier New", Font.PLAIN, itemYSize));

        for(int i = 0; i < options.length; i++){
            g2d.drawString(options[i],
                    xLoc + 3, yLoc + ((itemYSize + itemSpacing) * i));
        }
    }
}
