/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus.gamesingleplayer;

import zombiegamealpha.data.Game;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.ui.*;
import java.util.Random;
import zombiegamealpha.menus.MenuBase;

/**
 *
 * @author spex
 */
public class GameSinglePlayerZombieTurn extends MenuBase {

    private int screenX;//The Screen's X location when looking at the map
    private int screenY;//The Screen's Y location when looking at the map
    private int screenZ;//What floor the screen is currently looking at

    static private final int TURN_LEFT = 0;//These are to pass data between methods
    static private final int TURN_RIGHT = 1;
    static private final int FOWARD_CROSS = 2;
    static private final int FOWARD_EDGE = 3;

    private Avatar myAvatar;


    private Random r = new Random();

    public GameSinglePlayerZombieTurn(Game game, Avatar newMyAvatar){
        g = game;
        myAvatar = newMyAvatar;
    }

    @Override
    public void set(){

        r.setSeed(System.currentTimeMillis());
        screenX = -(g.ui.myWIDTH/2);
        screenY = 0;
        screenZ = 0;
        
    }

    @Override
    public void print(){

    }
    
    public void print(Avatar z){

        
        if(g.currentMap.getZombieAvatars().getAllAvatars() == null){
            done = true;
            return;
        }
        
        g.ui.myPrinter.cls();

        g.currentMap.setVisibleAll(false);

        //For all of the avatars...
        for(Avatar a : g.currentMap.getHumanAvatars().getAllAvatars()){

            g.currentMap.addVisible(a, true);
        }

        //Add the visible for our player
        g.currentMap.addVisible(myAvatar, true);
        
        if(g.currentMap.getMapSpot(z.getX(),
                z.getY(),
                z.getZ()).getVisible()){


            PrintManagerVariables pmv = new PrintManagerVariables();
            pmv.setMap(g.currentMap);
            pmv.setScreenXYZ(screenX, screenY, screenZ);
            pmv.setZombies(g.currentMap.getZombieAvatars());
            pmv.setHumans(g.currentMap.getHumanAvatars());
            pmv.setPrintAvatarHealth(true);


            g.ui.myPrinter.printSeen(pmv);
            g.ui.myPrinter.fade();
            g.ui.myPrinter.printVisible(pmv);
        }else{
            g.ui.myPrinter.printText(200, 300, "HIDDEN MOVEMENT");
        }

        g.ui.myPrinter.printText(200, 200, "Zombie Turn");

        g.ui.repaint();

        long time = System.currentTimeMillis();
        while(System.currentTimeMillis() < time + 16){
            //Wait 16 millisecs.....
        }
        
    }

    @Override
    public void extraStuff(){

        //For all of the zombies
        for(Avatar z : g.currentMap.getZombieAvatars().getAllAvatars()){

            //Set the time units
            z.setTimeUnits(z.getMaxTimeUnits());

            //find the visible of this zombie.....
            g.currentMap.setVisibleAll(false);
            g.currentMap.addVisible(z, false);



            //While it cannot find a human......
            while((z.getTimeUnits() >= 7) && (g.currentMap.findVisibleAvatar(true) == null)){

                //Pick a random movement (0 = turn left, 1 = move foward, 2 = turn right
                int randomMovement = r.nextInt(3);
                switch(randomMovement){
                    //Turn Left
                    case 0:
                        //Decrement Time Units
                        z.incTimeUnits(-1);
                        //Turn
                        z.incDirectionClockWise(-1);
                        break;

                    //Move Foward
                    case 1:
                        //Decrement Time Units Accordingly
                        if(z.getDirection() % 2 == 0){
                            z.incTimeUnits(-7);
                        }else{
                            z.incTimeUnits(-5);
                        }

                        //Move
                        move(true,z);
                        break;

                    //Turn Right
                    case 2:
                        //Decrement Time Units
                        z.incTimeUnits(-1);
                        //Turn
                        z.incDirectionClockWise(1);
                        break;


                }


                //////////////////////////////////////////////////////////////////////
                
                
                print(z);

                //////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////
                //////////////////////////////////////////////////////////////////////


                //find the visible of this zombie.....
                g.currentMap.setVisibleAll(false);
                g.currentMap.addVisible(z, false);

            }

            //While it does see a human and it has time
            while((z.getTimeUnits() >= 7) && (g.currentMap.findVisibleAvatar(true) != null)){




                //Get the locaitons
                int locations[][] = g.currentMap.findVisibleAvatar(true);

                //Declare variable to hold index for closest human
                int closestHuman = 0;
                int closestDistanceSquared = 9999999;

                //Find the closest human
                for(int index = 0; index < locations.length; index++){

                    //If the distance is closer than the closest one...
                    if(closestDistanceSquared >
                            (z.getX() - locations[index][0]) *
                            (z.getX() - locations[index][0]) +

                            (z.getY() - locations[index][1]) *
                            (z.getY() - locations[index][1])){

                        //Set this human as closest
                        closestHuman = index;

                        //Set this distance as closest
                        closestDistanceSquared =(z.getX() - locations[index][0]) *
                            (z.getX() - locations[index][0]) +
                            (z.getY() - locations[index][1]) *
                            (z.getY() - locations[index][1]);
                    }
                }





                //Set it as target
                int xTarget = locations[closestHuman][0];
                int yTarget = locations[closestHuman][1];
                int moveType = -1;

                //Run towards/ attack human uuntil time units are spent
                do{
                    moveType = changeAvatarPosition(z, xTarget, yTarget);

                    switch(moveType){
                        case TURN_LEFT:
                            z.incTimeUnits(-1);
                            break;
                        case TURN_RIGHT:
                            z.incTimeUnits(-1);
                            break;
                        case FOWARD_CROSS:
                            z.incTimeUnits(-7);
                            break;
                        case FOWARD_EDGE:
                            z.incTimeUnits(-5);
                            break;
                        case -1:


                            z.incTimeUnits(-10);

                            Avatar avatarInFrontOf = g.currentMap.getAvatarInFrontOf(z);

                            //If there is a person there
                            if(avatarInFrontOf != null){

                                //If the person is a human
                                if(g.currentMap.avatarHereHuman(avatarInFrontOf.getX(),
                                        avatarInFrontOf.getY(),
                                        avatarInFrontOf.getZ())){
                                    avatarInFrontOf.incHealth(-5);
                                }
                            }
                    }


                    //////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////

                    print(z);

                    //////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////////////////


                }while(z.getTimeUnits() >= 7);
            }
        }

        //All of the zombies have moved
        done = true;
        
    }




    @Override
    public void input(){
    }





    private void move(boolean foward, Avatar avatarToMove){
        int direction;

        if(foward){
            direction = avatarToMove.getDirection();
        }else{
            direction = (avatarToMove.getDirection() - 4);
            if(direction < 0){
                direction += 8;
            }
        }

        if (g.currentMap.isClear(avatarToMove.getX(),
                avatarToMove.getY(),
                avatarToMove.getZ(),direction)) {
            switch (direction) {
                case 0:
                    avatarToMove.incY(-1);
                    avatarToMove.incX(-1);
                    break;
                case 1:
                    avatarToMove.incY(-1);
                    break;
                case 2:
                    avatarToMove.incY(-1);
                    avatarToMove.incX(1);
                    break;
                case 3:
                    avatarToMove.incX(1);
                    break;
                case 4:
                    avatarToMove.incY(1);
                    avatarToMove.incX(1);
                    break;
                case 5:
                    avatarToMove.incY(1);
                    break;
                case 6:
                    avatarToMove.incY(1);
                    avatarToMove.incX(-1);
                    break;
                case 7:
                    avatarToMove.incX(-1);
                    break;
            }
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////

    public int changeAvatarPosition(Avatar myAvatar, int xTarget, int yTarget){


        if(getDirectionToTarget(myAvatar, xTarget, yTarget) != myAvatar.getDirection()){

            int directionToTurn = getDirectionToTarget(myAvatar, xTarget, yTarget) - myAvatar.getDirection();




            if(directionToTurn < -4){
                directionToTurn += 8;
            }else if(directionToTurn > 4){
                directionToTurn -= 8;
            }

            if(directionToTurn < 0){
                myAvatar.incDirectionClockWise(-1);
                return TURN_LEFT;
            }else{
                myAvatar.incDirectionClockWise(1);
                return TURN_RIGHT;
            }

        }else{

            if(g.currentMap.isClear(myAvatar)){
                move(true,myAvatar);
                if(myAvatar.getDirection() % 2 == 0){
                    return FOWARD_CROSS;
                }else{
                    return FOWARD_EDGE;
                }
            }else{
                return -1;
            }
        }

    }

    //Return the integer in the direction that the target is
    public int getDirectionToTarget(Avatar myAvatar, int xTarget, int yTarget){

        int x = myAvatar.getX();
        int y = myAvatar.getY();

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
