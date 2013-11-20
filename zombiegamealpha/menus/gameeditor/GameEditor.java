/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus.gameeditor;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import zombiegamealpha.data.Game;
import zombiegamealpha.data.Map;
import zombiegamealpha.menus.MenuBase;
import zombiegamealpha.ui.ImageStorage;
import zombiegamealpha.ui.PrintManagerVariables;
/**
 *
 * @author spex
 */
public class GameEditor extends MenuBase {

    private int paintBrush;//The graphic that is being printed or the physics
    private boolean physicsMode;//Weither it is physics mode or not

    private int curserX;//The X location of the curser
    private int curserY;//The Y location of the curser

    private int screenX;//The Screen's X location when looking at the map
    private int screenY;//The Screen's Y location when looking at the map
    private int screenZ;//What floor the screen is currently looking at

    private int lastX;//The last X position of the mouse
    private int lastY;//The last Y position of the mouse

    private int objectSelection;//Weither it is selecting a floor, object, left wall, and right wall

    private boolean eraseMode;//If you are erasing or not

    public boolean showWalls;//If the walls should be shown or not

    public GameEditor(Game game){
        g = game;
    }

    @Override
    public void set(){

        g.currentMap = new Map();
        paintBrush = 0;
        physicsMode = false;
        curserX = 0;
        curserY = 0;
        screenX = -(g.ui.myWIDTH/2);
        screenY = 0;
        screenZ = 0;
        showWalls = true;

        lastX = g.ui.mouse.getX();
        lastY = g.ui.mouse.getY();

        objectSelection = 0;

        eraseMode = false;

        showWalls = true;
    }

    @Override
    public void print(){

        PrintManagerVariables pmv = new PrintManagerVariables();
        pmv.setCurserXY(curserX, curserY);
        pmv.setMap(g.currentMap);
        pmv.setScreenXYZ(screenX, screenY, screenZ);
        pmv.setWalls(showWalls);


        g.ui.myPrinter.cls();
        if(physicsMode){
            g.ui.myPrinter.printPhysics(pmv);
            g.ui.myPrinter.printEditHudPhysics(paintBrush,objectSelection);
        }else{
            g.ui.myPrinter.printMap(pmv);
            g.ui.myPrinter.fade();
            g.ui.myPrinter.printFloor(pmv);
            g.ui.myPrinter.printEditHudVisual(paintBrush,objectSelection);
        }

        if(eraseMode){
            g.ui.myPrinter.printEraseMode();
        }

        g.ui.repaint();
    }

    @Override
    public void input(){

        if(g.ui.key.keyHit()){

            if(g.ui.key.getKey() == KeyEvent.VK_LEFT){
                if(paintBrush > 0){
                    paintBrush--;
                }else{
                    //ENTER SOUND FOR "CANNOT DO THIS"
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_RIGHT){
                if(physicsMode){
                    if(paintBrush < 4){
                        paintBrush++;
                    }else{
                        //ENTER SOUND FOR "CANNOT DO THIS"
                    }
                }else{
                    if((paintBrush < ImageStorage.TOTALFLOORIMAGES) ||
                    (paintBrush < ImageStorage.TOTALOBJECTIMAGES) ||
                    (paintBrush < ImageStorage.TOTALWALLIMAGES)){
                        paintBrush++;
                    }else{
                        //ENTER SOUND FOR "CANNOT DO THIS"
                    }
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_UP){
                objectSelection++;
                if(objectSelection > 3){
                    objectSelection = 0;
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_DOWN){
                objectSelection--;
                if(objectSelection < 0){
                    objectSelection = 3;
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_X){
                eraseMode = !eraseMode;
            }else if(g.ui.key.getKey() == KeyEvent.VK_W){
                showWalls = !showWalls;
            }else if(g.ui.key.getKey() == KeyEvent.VK_P){
                physicsMode = !physicsMode;
                paintBrush = 0;
            }else if(g.ui.key.getKey() == KeyEvent.VK_O){
                if(screenZ < g.currentMap.getZSize() - 1){
                    screenZ++;
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_L){
                if(screenZ > 0){
                    screenZ--;
                }
            }else if (g.ui.key.getKey() == KeyEvent.VK_ESCAPE) {
                GameEditorPause gep = new GameEditorPause(g,screenX,screenY,screenZ);

                gep.run();
                if(gep.currentSelection == 6){
                    done = true;
                    selected = true;
                }
            }
        }

        if(g.ui.mouse.clicked()){
            
            lastX = g.ui.mouse.getX();
            lastY = g.ui.mouse.getY();

            paintSpot();
        }

        if(g.ui.mouse.moved()){


            if(g.ui.mouse.dragged()){

                if(g.ui.mouse.getMouseButton() == MouseEvent.BUTTON1){
                    paintSpot();
                }else{
                    screenX += lastX - g.ui.mouse.getX();
                    screenY += lastY - g.ui.mouse.getY();
                    
                }
                lastX = g.ui.mouse.getX();
                lastY = g.ui.mouse.getY();
            }

            curserX = ((g.ui.mouse.getX() + screenX) + (g.ui.mouse.getY() + screenY) * 2)/80;
            curserY = ((g.ui.mouse.getY() + screenY) - (g.ui.mouse.getX() + screenX)/2)/40;
        }
    }

    private void paintSpot(){
        if((g.ui.mouse.getMouseButton() == MouseEvent.BUTTON1) &&
        (paintBrush >= 0) &&
        (curserX >= 0) &&
        (curserY >= 0) &&
        (screenZ >= 0) &&
        (curserX < g.currentMap.getXSize()) &&
        (curserY < g.currentMap.getYSize()) &&
        (screenZ < g.currentMap.getZSize())){
            if(physicsMode){
                switch (objectSelection){
                    case 0:
                        g.currentMap.getMapSpot(curserX, curserY, screenZ).setGroundPhysicsByNumber(paintBrush);
                        break;
                    case 1:
                        if(g.currentMap.getMapSpot(curserX, curserY, screenZ).getLeftWall()){
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setLeftWallPhysicsByNumber(paintBrush);
                        }
                        break;
                    case 2:
                        if(g.currentMap.getMapSpot(curserX, curserY, screenZ).getRightWall()){
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setRightWallPhysicsByNumber(paintBrush);
                        }
                        break;
                    case 3:
                        if(eraseMode){
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setSpawn(false);
                        }else{
                            if(paintBrush == 0){
                                g.currentMap.getMapSpot(curserX, curserY, screenZ).setSpawn(true);
                                g.currentMap.getMapSpot(curserX, curserY, screenZ).setHumanSpawn(true);
                            }else if(paintBrush == 1){
                                g.currentMap.getMapSpot(curserX, curserY, screenZ).setSpawn(true);
                                g.currentMap.getMapSpot(curserX, curserY, screenZ).setHumanSpawn(false);
                            }else{
                                g.currentMap.getMapSpot(curserX, curserY, screenZ).setSpawn(false);
                            }
                        }
                        break;
                }

            } else{
                if(eraseMode){
                    switch (objectSelection){
                        case 0:
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setFloor(false);
                            break;
                        case 1:
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setLeftWall(false);
                            break;
                        case 2:
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setRightWall(false);
                            break;
                        case 3:
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setObject(false);
                            break;
                    }
                }else{
                    if((objectSelection == 0) && (paintBrush < ImageStorage.TOTALFLOORIMAGES)){

                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setGraphics(paintBrush);
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setFloor(true);
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setTransparent(true);
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setPassable(true);


                    }else if((objectSelection == 1) && (paintBrush < ImageStorage.TOTALWALLIMAGES)){

                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setWallGraphicsLeft(paintBrush);
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setLeftWall(true);

                    }else if((objectSelection == 2) && (paintBrush < ImageStorage.TOTALWALLIMAGES)){

                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setWallGraphicsRight(paintBrush);
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setRightWall(true);

                    }else if((objectSelection == 3) && (paintBrush < ImageStorage.TOTALOBJECTIMAGES)){

                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setObjectGraphic(paintBrush);
                            g.currentMap.getMapSpot(curserX, curserY, screenZ).setObject(true);
                    }
                }
            }
        }
    }



    //    //The main edit game function
    private void editMap() {
//        g.currentMap = new Map(25,25,4);
//        g.myAvatar = new Avatar();
//        g.myAvatar.setX(10);
//        g.myAvatar.setY(10);
//        g.myAvatar.setDirection(4);
//        g.keyType = 2;
//        g.paintBrush = 0;
//        g.stillPlaying = true;
//        repaint();
//        do{
//
//            if(g.needRepaint){
//                repaint();
//            }
//            if(g.openMenu){
//                editPause();
//            }
//        }while(g.stillPlaying);
    }





}
