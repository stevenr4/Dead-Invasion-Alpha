/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus.gameeditor;

import zombiegamealpha.data.Game;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.ui.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import zombiegamealpha.menus.MenuBase;

/**
 *
 * @author spex
 */
public class GameEditorTest extends MenuBase{

    

    private int curserX;//The X location of the curser
    private int curserY;//The Y location of the curser

    private int screenX;//The Screen's X location when looking at the map
    private int screenY;//The Screen's Y location when looking at the map
    private int screenZ;//What floor the screen is currently looking at

    private int lastX, lastY;

    private int xTarget;//The x and y targets of the movement
    private int yTarget;

    public GameEditorTest(Game game){
        g = game;
    }

    @Override
    public void set(){

        g.currentMap.getZombieAvatars().clearAll();


        curserX = 0;
        curserY = 0;
        screenX = -(g.ui.myWIDTH/2);
        screenY = 0;
        screenZ = 0;
        lastX = 0;
        lastY = 0;

        g.currentMap.getHumanAvatars().clearAll();
        for(int i = 0; i < 3; i++){
            if(g.currentMap.getAmountOfAvailableSpawnPoints(true) > 0){
                g.currentMap.getHumanAvatars().addAvatar(null);
                int tmpLoc[] = g.currentMap.getRandomAvaliableSpawn(true);
                g.currentMap.getHumanAvatars().getAvatar(i).setX(tmpLoc[0]);
                g.currentMap.getHumanAvatars().getAvatar(i).setY(tmpLoc[1]);
                g.currentMap.getHumanAvatars().getAvatar(i).setZ(tmpLoc[2]);
                screenZ = tmpLoc[2];
            
                g.currentMap.getHumanAvatars().getAvatar(i).setDirection(3);
                g.currentMap.getHumanAvatars().getAvatar(i).setHealth(20);
                g.currentMap.getHumanAvatars().getAvatar(i).setMaxHealth(40);

                g.currentMap.setSeen(false);

                g.currentMap.addVisible(g.currentMap.getHumanAvatars().getAvatar(i), true);
            }
        }


        int totalZombies = g.currentMap.getAmountOfAvailableSpawnPoints(false);
        Avatar tmpAvatar = new Avatar();

        
        for(int i = 0; i < totalZombies; i++){
            int loc[] = g.currentMap.getRandomAvaliableSpawn(false);
            tmpAvatar.setX(loc[0]);
            tmpAvatar.setY(loc[1]);
            tmpAvatar.setZ(loc[2]);
            tmpAvatar.setHealth(60);
            tmpAvatar.setMaxHealth(60);
            
            g.currentMap.getZombieAvatars().addAvatar(tmpAvatar);
        }
    }

    @Override
    public void print(){

        g.ui.myPrinter.cls();


        PrintManagerVariables pmv = new PrintManagerVariables();
        pmv.setCurserXY(curserX, curserY);
        pmv.setMap(g.currentMap);
        pmv.setScreenXYZ(screenX, screenY, screenZ);
        pmv.setZombies(g.currentMap.getZombieAvatars());
        pmv.setHumans(g.currentMap.getHumanAvatars());
        pmv.setPrintAvatarHealth(true);


        g.ui.myPrinter.printSeen(pmv);
        g.ui.myPrinter.fade();
        g.ui.myPrinter.printVisible(pmv);
        g.ui.repaint();
    }

    @Override
    public void extraStuff(){

//        //if it is not at the target yet
//        if((g.currentMap.getZombieAvatars().getAvatar(0).getX() != xTarget) ||
//                (g.currentMap.getZombieAvatars().getAvatar(0).getY() != yTarget)){
//            changeAvatarPosition();
//        }
    }

    @Override
    public void input(){

        if(g.ui.key.keyHit()){

            if(g.ui.key.getKey() == KeyEvent.VK_LEFT){
                g.currentMap.getHumanAvatars().getAvatar(0).incDirectionClockWise(-1);
            }else if(g.ui.key.getKey() == KeyEvent.VK_RIGHT){
                g.currentMap.getHumanAvatars().getAvatar(0).incDirectionClockWise(1);
            }else if(g.ui.key.getKey() == KeyEvent.VK_UP){
                move(true);
            }else if(g.ui.key.getKey() == KeyEvent.VK_DOWN){
                move(false);
            }else if(g.ui.key.getKey() == KeyEvent.VK_O){
                if((g.currentMap.getMapSpot(g.currentMap.getHumanAvatars().getAvatar(0).getX(),
                        g.currentMap.getHumanAvatars().getAvatar(0).getY(),
                        g.currentMap.getHumanAvatars().getAvatar(0).getZ()).getClimb()) &&
                        (g.currentMap.getHumanAvatars().getAvatar(0).getZ() < g.currentMap.getZSize() - 1)){
                    g.currentMap.getHumanAvatars().getAvatar(0).incZ(1);
                    if(screenZ < g.currentMap.getZSize()){
                        screenZ++;
                    }
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_L){
                if(g.currentMap.getHumanAvatars().getAvatar(0).getZ() > 0){
                    if(g.currentMap.getMapSpot(g.currentMap.getHumanAvatars().getAvatar(0).getX(),
                            g.currentMap.getHumanAvatars().getAvatar(0).getY(),
                            g.currentMap.getHumanAvatars().getAvatar(0).getZ() - 1).getClimb()){
                        g.currentMap.getHumanAvatars().getAvatar(0).incZ(-1);
                        if(screenZ < g.currentMap.getZSize()){
                            screenZ--;
                        }
                    }
                }
            }else if (g.ui.key.getKey() == KeyEvent.VK_ESCAPE) {
                done = true;
                selected = true;
            }

            xTarget = g.currentMap.getHumanAvatars().getAvatar(0).getX();
            yTarget = g.currentMap.getHumanAvatars().getAvatar(0).getY();

            g.currentMap.setVisibleAll(false);
            for(Avatar a : g.currentMap.getHumanAvatars().getAllAvatars()){
                g.currentMap.addVisible(a, true);
            }
        }

        if(g.ui.mouse.clicked()){
            
            lastX = g.ui.mouse.getX();
            lastY = g.ui.mouse.getY();

            if(g.ui.mouse.getMouseButton() == MouseEvent.BUTTON1){
                xTarget = curserX;
                yTarget = curserY;
            }
            

        }

        if(g.ui.mouse.moved()){

            if(g.ui.mouse.dragged()){

                screenX += lastX - g.ui.mouse.getX();
                screenY += lastY - g.ui.mouse.getY();


                lastX = g.ui.mouse.getX();
                lastY = g.ui.mouse.getY();

            }

            curserX = ((g.ui.mouse.getX() + screenX) + (g.ui.mouse.getY() + screenY) * 2)/80;
            curserY = ((g.ui.mouse.getY() + screenY) - (g.ui.mouse.getX() + screenX)/2)/40;
        }
    }

    //A function to move the avatar foward
    private void move(boolean foward){
        int direction;

        if(foward){
            direction = g.currentMap.getHumanAvatars().getAvatar(0).getDirection();
        }else{
            direction = (g.currentMap.getHumanAvatars().getAvatar(0).getDirection() - 4);
            if(direction < 0){
                direction += 8;
            }
        }

        if (g.currentMap.isClear(g.currentMap.getHumanAvatars().getAvatar(0).getX(),
                g.currentMap.getHumanAvatars().getAvatar(0).getY(),
                g.currentMap.getHumanAvatars().getAvatar(0).getZ(),direction)) {
            switch (direction) {
                case 0:
                    g.currentMap.getHumanAvatars().getAvatar(0).incY(-1);
                    g.currentMap.getHumanAvatars().getAvatar(0).incX(-1);
                    break;
                case 1:
                    g.currentMap.getHumanAvatars().getAvatar(0).incY(-1);
                    break;
                case 2:
                    g.currentMap.getHumanAvatars().getAvatar(0).incY(-1);
                    g.currentMap.getHumanAvatars().getAvatar(0).incX(1);
                    break;
                case 3:
                    g.currentMap.getHumanAvatars().getAvatar(0).incX(1);
                    break;
                case 4:
                    g.currentMap.getHumanAvatars().getAvatar(0).incY(1);
                    g.currentMap.getHumanAvatars().getAvatar(0).incX(1);
                    break;
                case 5:
                    g.currentMap.getHumanAvatars().getAvatar(0).incY(1);
                    break;
                case 6:
                    g.currentMap.getHumanAvatars().getAvatar(0).incY(1);
                    g.currentMap.getHumanAvatars().getAvatar(0).incX(-1);
                    break;
                case 7:
                    g.currentMap.getHumanAvatars().getAvatar(0).incX(-1);
                    break;
            }
        }
    }


    public void changeAvatarPosition(){


        if(getDirectionToTarget() != g.currentMap.getHumanAvatars().getAvatar(0).getDirection()){

            int directionToTurn = getDirectionToTarget() - g.currentMap.getHumanAvatars().getAvatar(0).getDirection();




            if(directionToTurn < -4){
                directionToTurn += 8;
            }else if(directionToTurn > 4){
                directionToTurn -= 8;
            }

            if(directionToTurn < 0){
                g.currentMap.getHumanAvatars().getAvatar(0).incDirectionClockWise(-1);
            }else{
                g.currentMap.getHumanAvatars().getAvatar(0).incDirectionClockWise(1);
            }

        }else{

            if(g.currentMap.isClear(g.currentMap.getHumanAvatars().getAvatar(0))){
                move(true);
            }else{
                xTarget = g.currentMap.getHumanAvatars().getAvatar(0).getX();
                yTarget = g.currentMap.getHumanAvatars().getAvatar(0).getY();
            }
        }

        g.currentMap.setVisibleAll(false);

        //For all of the avatars...
        for(Avatar a : g.currentMap.getZombieAvatars().getAllAvatars()){
           
                
            g.currentMap.addVisible(a, true);
        }
        

    }

    //Return the integer in the direction that the target is
    public int getDirectionToTarget(){

        int x = g.currentMap.getHumanAvatars().getAvatar(0).getX();
        int y = g.currentMap.getHumanAvatars().getAvatar(0).getY();

        if(x < xTarget){
            if(y < yTarget){
                return 4;
            }else if(y == yTarget){
                return 3;
            }else{
                return 2;
            }
        }else if(x == xTarget){
            if(y < yTarget){
                return 5;
            }else if(y == yTarget){
                return -1;
            }else{
                return 1;
            }
        }else{
            if(y < yTarget){
                return 6;
            }else if(y == yTarget){
                return 7;
            }else{
                return 0;
            }
        }
    }
}
