/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.menus.gamesingleplayer;

import zombiegamealpha.data.Game;
import zombiegamealpha.data.Avatar;
import zombiegamealpha.data.items.*;
import zombiegamealpha.data.PlayerHuman;
import zombiegamealpha.ui.*;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.util.Random;
import zombiegamealpha.menus.MenuBase;

/**
 *
 * @author spex
 */
public class GameSinglePlayer extends MenuBase {


    private int curserX;//The X location of the curser
    private int curserY;//The Y location of the curser

    private int screenX;//The Screen's X location when looking at the map
    private int screenY;//The Screen's Y location when looking at the map
    private int screenZ;//What floor the screen is currently looking at

    private int lastX, lastY;//The last location of the mouse

    private Avatar myAvatar;

//    static private final int TURN_LEFT = 0;//These are to pass data between methods
//    static private final int TURN_RIGHT = 1;
//    static private final int FOWARD_CROSS = 2;
//    static private final int FOWARD_EDGE = 3;



    //Time units
    //private int timeUnits;

    private Random r = new Random();

    public GameSinglePlayer(Game game){
        g = game;
    }

    @Override
    public void set(){

        r.setSeed(System.currentTimeMillis());

        g.currentMap.resetAvatarManagers();



        g.currentMap.getZombieAvatars().addAvatar(null);
        curserX = 0;
        curserY = 0;
        screenX = -(g.ui.myWIDTH/2);
        screenY = 0;
        screenZ = 0;
        lastX = 0;
        lastY = 0;


        //Define the avatar
        myAvatar = new Avatar();
        
        //Place the player
        if(g.currentMap.getAmountOfAvailableSpawnPoints(true) > 0){
            int tmpLoc[] = g.currentMap.getRandomAvaliableSpawn(true);
            myAvatar.setX(tmpLoc[0]);
            myAvatar.setY(tmpLoc[1]);
            myAvatar.setZ(tmpLoc[2]);
            screenZ = tmpLoc[2];
        }else{
            myAvatar.setX(0);
            myAvatar.setY(0);
            myAvatar.setZ(0);
        }
        
        myAvatar.setHealth(100);
        myAvatar.setMaxHealth(100);
        myAvatar.setMaxTimeUnits(35);
        myAvatar.setTimeUnits(35);
        myAvatar.setDirection(3);


        //Set up the viewing seen and visible angle
        g.currentMap.setSeen(false);

        g.currentMap.addVisible(myAvatar, true);




        //Find the total amount of human
        int totalHumans = g.currentMap.getAmountOfAvailableSpawnPoints(true);
        Avatar tmpAvatar = new Avatar();


        for(int i = 0; i < totalHumans; i++){
            int loc[] = g.currentMap.getRandomAvaliableSpawn(true);
            tmpAvatar.setX(loc[0]);
            tmpAvatar.setY(loc[1]);
            tmpAvatar.setZ(loc[2]);
            int health = r.nextInt(70) + 30;
            tmpAvatar.setMaxHealth(100);
            tmpAvatar.setHealth(health);
            tmpAvatar.setDirection(r.nextInt(8));

            g.currentMap.getHumanAvatars().addAvatar(tmpAvatar);
        }

        //Find the total amount of zombie spawns
        int totalZombies = g.currentMap.getAmountOfAvailableSpawnPoints(false);


        for(int i = 0; i < totalZombies; i++){
            int loc[] = g.currentMap.getRandomAvaliableSpawn(false);
            tmpAvatar.setX(loc[0]);
            tmpAvatar.setY(loc[1]);
            tmpAvatar.setZ(loc[2]);
            int health = r.nextInt(100);
            tmpAvatar.setMaxHealth(100);
            tmpAvatar.setHealth(health);
            tmpAvatar.setDirection(r.nextInt(8));

            g.currentMap.getZombieAvatars().addAvatar(tmpAvatar);
        }


        lastX = g.ui.mouse.getX();
        lastY = g.ui.mouse.getY();
    }

    @Override
    public void print(){

        g.ui.myPrinter.cls();


        g.currentMap.setVisibleAll(false);

        //For all of the avatars...
        drawVisibleSpots();


        PrintManagerVariables pmv = new PrintManagerVariables();
        pmv.setCurserXY(curserX, curserY);
        pmv.setMap(g.currentMap);
        pmv.setScreenXYZ(screenX, screenY, screenZ);
        pmv.setZombies(g.currentMap.getZombieAvatars());
        pmv.setHumans(g.currentMap.getHumanAvatars());
        pmv.setAvatar(myAvatar);
        pmv.setPrintAvatarHealth(true);


        g.ui.myPrinter.printSeen(pmv);
        g.ui.myPrinter.fade();
        g.ui.myPrinter.printVisible(pmv);
        g.ui.myPrinter.printSinglePlayerHud(myAvatar, (PlayerHuman)g.myPlayer);
        g.ui.repaint();
    }

    @Override
    public void extraStuff(){
    }

    @Override
    public void input(){

        if(g.ui.key.keyHit()){

            if(g.ui.key.getKey() == KeyEvent.VK_LEFT){
                if(myAvatar.getTimeUnits() >= 1){
                    myAvatar.incDirectionClockWise(-1);
                    myAvatar.incTimeUnits(-1);
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_RIGHT){
                if(myAvatar.getTimeUnits() >= 1){
                    myAvatar.incDirectionClockWise(1);
                    myAvatar.incTimeUnits(-1);
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_UP){

                //If were clear
                if(g.currentMap.isClear(myAvatar)){

                    //If we are traveling across....
                    if(myAvatar.getDirection() % 2 == 0){

                        //If we have time
                        if(myAvatar.getTimeUnits() >= 7){
                            //Foward Cross
                            move(true,myAvatar);

                            myAvatar.incTimeUnits(-7);
                        }
                    }else{
                        //Foward Edge
                        //If we have time
                        if(myAvatar.getTimeUnits() >= 5){
                            //Foward Cross
                            move(true,myAvatar);

                            myAvatar.incTimeUnits(-5);
                        }
                    }
                }

            }else if(g.ui.key.getKey() == KeyEvent.VK_DOWN){

                //Get the opposite of the facing direction...
                int direction = (myAvatar.getDirection() - 4);

                if(direction < 0){
                    direction += 8;
                }

                //If we're clear
                if(g.currentMap.isClear(myAvatar.getX(),
                    myAvatar.getY(),
                    myAvatar.getZ(),direction)){

                    //If we are traveling across....
                    if(myAvatar.getDirection() % 2 == 0){

                        //If we have time
                        if(myAvatar.getTimeUnits() >= 7){
                            //Foward Cross
                            move(false,myAvatar);

                            myAvatar.incTimeUnits(-7);
                        }
                    }else{
                        //Foward Edge
                        //If we have time
                        if(myAvatar.getTimeUnits() >= 5){
                            //Foward Cross
                            move(false,myAvatar);

                            myAvatar.incTimeUnits(-5);
                        }
                    }
                }

            }else if(g.ui.key.getKey() == KeyEvent.VK_O){
                if((g.currentMap.getMapSpot(myAvatar.getX(),
                        myAvatar.getY(),
                        myAvatar.getZ()).getClimb()) &&
                        (myAvatar.getZ() < g.currentMap.getZSize() - 1)){

                    myAvatar.incZ(1);
                    if(screenZ < g.currentMap.getZSize()){
                        screenZ++;
                    }
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_L){
                if(myAvatar.getZ() > 0){
                    if(g.currentMap.getMapSpot(myAvatar.getX(),
                            myAvatar.getY(),
                            myAvatar.getZ() - 1).getClimb()){

                        myAvatar.incZ(-1);
                        if(screenZ < g.currentMap.getZSize()){
                            screenZ--;
                        }
                    }
                }
            }else if(g.ui.key.getKey() == KeyEvent.VK_W){
                
                screenY -= 100;
                
            }else if(g.ui.key.getKey() == KeyEvent.VK_A){
                
                screenX -= 100;
                
            }else if(g.ui.key.getKey() == KeyEvent.VK_S){
                
                
                screenY += 100;
                
            }else if(g.ui.key.getKey() == KeyEvent.VK_D){
                
                screenX += 100;
                
            }else if (g.ui.key.getKey() == KeyEvent.VK_SPACE) {

                GameSinglePlayerZombieTurn gspzt = new GameSinglePlayerZombieTurn(g, myAvatar);
                gspzt.run();
                myAvatar.resetTimeUnits();


            }else if (g.ui.key.getKey() == KeyEvent.VK_ENTER){



            } else if (g.ui.key.getKey() == KeyEvent.VK_ESCAPE) {
                done = true;
                selected = true;
            }
        }




        if(g.ui.mouse.clicked()){

            lastX = g.ui.mouse.getX();
            lastY = g.ui.mouse.getY();

            if(g.ui.mouse.getMouseButton() == MouseEvent.BUTTON1){

                //If the mouse is within the hand buttons
                if((g.ui.mouse.getY() <= g.ui.myHEIGHT - 10) &&
                    (g.ui.mouse.getY() >= g.ui.myHEIGHT - 92)){

                    //If the mouse is within the Left hand button
                    if((g.ui.mouse.getX() >= 36) &&
                        (g.ui.mouse.getX() <= 236)){

                        //Do the left hand
                        GameSinglePlayerHandClicked gsphc = new GameSinglePlayerHandClicked(g,true,screenX,screenY,screenZ,myAvatar);
                        gsphc.run();

                        doHandAction(true, gsphc.getAction());


                        //If the right hand button is clicked
                    }else if((g.ui.mouse.getX() >= 262) &&
                        (g.ui.mouse.getX() <= 464)){


                        //Do the right hand
                        GameSinglePlayerHandClicked gsphc = new GameSinglePlayerHandClicked(g,false,screenX,screenY,screenZ,myAvatar);
                        gsphc.run();

                        doHandAction(false, gsphc.getAction());

                    }

                    //If the mouse is withen the limits of the backpack and pause menu
                    if((g.ui.mouse.getX() <= g.ui.myWIDTH - 26) &&
                            (g.ui.mouse.getX() >= g.ui.myWIDTH - 164)){

                        //If it is within the "PauseMenu"
                        if((g.ui.mouse.getY() <= g.ui.myHEIGHT - 18) &&
                                (g.ui.mouse.getY() >= g.ui.myHEIGHT - 74)){


                            System.out.println("Opening pause menu...");

                            GameSinglePlayerPause gspp = new GameSinglePlayerPause(g,screenX,screenY,screenZ,myAvatar);
                            gspp.run();

                            if(gspp.currentSelection == 3){
                                done = true;
                            }

                            //If the mouse button is within the backpack
                        }else if((g.ui.mouse.getY() <= g.ui.myHEIGHT - 18) &&
                                (g.ui.mouse.getY() >= g.ui.myHEIGHT - 74)){


                            System.err.println("OPENING BACKPACK NOT DONE YET");
                        }
                    }
                }
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

        checkDeaths();
        drawVisibleSpots();
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

//    public int changeAvatarPosition(Avatar myAvatar, int xTarget, int yTarget){
//
//
//        if(getDirectionToTarget(myAvatar, xTarget, yTarget) != myAvatar.getDirection()){
//
//            int directionToTurn = getDirectionToTarget(myAvatar, xTarget, yTarget) - myAvatar.getDirection();
//
//
//
//
//            if(directionToTurn < -4){
//                directionToTurn += 8;
//            }else if(directionToTurn > 4){
//                directionToTurn -= 8;
//            }
//
//            if(directionToTurn < 0){
//                myAvatar.incDirectionClockWise(-1);
//                return TURN_LEFT;
//            }else{
//                myAvatar.incDirectionClockWise(1);
//                return TURN_RIGHT;
//            }
//
//        }else{
//
//            if(g.currentMap.isClear(myAvatar)){
//                move(true,myAvatar);
//                if(myAvatar.getDirection() % 2 == 0){
//                    return FOWARD_CROSS;
//                }else{
//                    return FOWARD_EDGE;
//                }
//            }else{
//                return -1;
//            }
//        }
//
//    }

    //Return the integer in the direction that the target is
//    public int getDirectionToTarget(Avatar myAvatar, int xTarget, int yTarget){
//
//        int x = myAvatar.getX();
//        int y = myAvatar.getY();
//
//        if(x < xTarget){
//            if(y < yTarget){
//                return 4;
//            }else if(y == yTarget){
//                return 3;
//            }else{
//                return 2;
//            }
//        }else if(x == xTarget){
//            if(y < yTarget){
//                return 5;
//            }else if(y == yTarget){
//                return -1;
//            }else{
//                return 1;
//            }
//        }else{
//            if(y < yTarget){
//                return 6;
//            }else if(y == yTarget){
//                return 7;
//            }else{
//                return 0;
//            }
//        }
//    }

    //Method to check if something is dead, and does the actions
    private void checkDeaths(){
        if(myAvatar.getHealth() <= 0){
            done = true;
            //DISPLAY DEATH MESSAGE!
            return;
        }

        //For all of the zombies
        for(int i = 0; i < g.currentMap.getZombieAvatars().getTotal(); i++){


            //If the zombie has no health
            if(g.currentMap.getZombieAvatars().getAvatar(i).getHealth() <= 0){
                //Remove the dead zombie
                g.currentMap.getZombieAvatars().takeOutAvatar(i);

                //Decrement I for the dead zombie and continue loop
                i--;
                continue;
            }
        }

        //For all the humans
        for(int i = 0; i < g.currentMap.getHumanAvatars().getTotal(); i++){

            //If the human has no health
            if(g.currentMap.getHumanAvatars().getAvatar(i).getHealth() <= 0){
                //Remove the dead human
                g.currentMap.getHumanAvatars().takeOutAvatar(i);

                //Decrement I for the dead human and continue loop
                i--;
                continue;
            }
        }

        if(g.currentMap.getZombieAvatars().getTotal() <= 0){
            done = true;
        }
    }



    //A function to do the action of a hand
    private void doHandAction(boolean leftHand, int action){


        //Create an item to store the hand item, and assign it to the item
        Item handItem;
        if(leftHand){
            handItem = ((PlayerHuman)g.myPlayer).getLeftHand();
        }else{
            handItem = ((PlayerHuman)g.myPlayer).getRightHand();
        }


        //If we are shooting an aimed shot..
        if(action == GameSinglePlayerHandClicked.AIMEDSHOT){
            //Make sure we have ammo
            if(((Projectile)handItem).getAmountOfAmmo() > 0){
                //Make sure we have enough time units
                if(myAvatar.getTimeUnits() >= ((Projectile)handItem).getTimeUnits()){

                    //Get the X and Y target
                    GameSinglePlayerTarget gspt = new GameSinglePlayerTarget(g);
                    int target[] = gspt.getCurserTarget(screenX, screenY, screenZ, myAvatar);

                    //If we are targeting a visible spot
                    if(g.currentMap.getMapSpot(target[0],target[1],target[2]).getVisible()){

                        //If there is an avatar there
                        if(g.currentMap.getZombieAvatars().avatarHere(target[0], target[1], target[2])){

                            //Get the damage caused to the avatar
                            int damage = getRandomDamage(((Projectile)handItem).getAccuracy(), ((Projectile)handItem).getDamage());

                            //Get the avatar and subtract health
                            int avatarIndex = g.currentMap.getZombieAvatars().getAvatarIndex(target[0], target[1], target[2]);
                            g.currentMap.getZombieAvatars().getAvatar(avatarIndex).incHealth(-damage);

                            //Subtract time and Ammo
                            ((Projectile)handItem).removeOneAmmo();
                            myAvatar.incTimeUnits(-((Projectile)handItem).getTimeUnits());
                        }else{
                            //PRINT YOU DID NOT TARGET ANYTHING!////////////////
                        }
                    }else{
                        //YOU CANNOT SEE THERE/////////////////////////////
                    }
                }else{
                    //PRINT YOU DO NOT HAVE ENOUGH TIME!////////////////////////////////
                }
            }else{
                //PRINT YOU DO NOT HAVE ENOUGH AMMO//////////////////////
            }



        }else if(action == GameSinglePlayerHandClicked.QUICKSHOT){
            //Make sure we have ammo
            if(((Projectile)handItem).getAmountOfAmmo() > 0){
                //Make sure we have enough time units
                if(myAvatar.getTimeUnits() >= ((Projectile)handItem).getTimeUnits()/2){

                    //Get the X and Y target
                    GameSinglePlayerTarget gspt = new GameSinglePlayerTarget(g);
                    int target[] = gspt.getCurserTarget(screenX, screenY, screenZ, myAvatar);

                    //If we are targeting a visible spot
                    if(g.currentMap.getMapSpot(target[0],target[1],target[2]).getVisible()){
                        //If there is an avatar there
                        if(g.currentMap.getZombieAvatars().avatarHere(target[0], target[1], target[2])){

                            //Get the damage caused to the avatar
                            int damage = getRandomDamage(((Projectile)handItem).getAccuracy()/2, ((Projectile)handItem).getDamage());

                            //Get the avatar and subtract health
                            int avatarIndex = g.currentMap.getZombieAvatars().getAvatarIndex(target[0], target[1], target[2]);
                            g.currentMap.getZombieAvatars().getAvatar(avatarIndex).incHealth(-damage);

                            //Subtract time and Ammo
                            ((Projectile)handItem).removeOneAmmo();
                            myAvatar.incTimeUnits(-(((Projectile)handItem).getTimeUnits() / 2));
                        }else{
                            //THERE IS NOBODY THERE
                        }
                    }else{
                        //THAT SPOT IS NOT VISIBLE
                    }
                }else{
                    //PRINT YOU DO NOT HAVE ENOUGH TIME!////////////////////////////////
                }
            }else{
                //PRINT YOU DO NOT HAVE ENOUGH AMMO//////////////////////
            }
        }else if(action == GameSinglePlayerHandClicked.MELEE){

            System.out.println("Melee Selected");
            //If there is somebody infront of the user
            if(g.currentMap.getAvatarInFrontOf(myAvatar) != null){


                System.out.println("Person in front of ours");
                //If there is enough time
                if(myAvatar.getTimeUnits() >= ((Melee)handItem).getTimeUnits()){


                    System.out.println("Enough time,... attacking....");

                    //Get the damage from a melee weapon
                    int damage = getRandomDamage(100, ((Melee)handItem).getDamage());

                    //Apply the damage to the avatar
                    g.currentMap.getAvatarInFrontOf(myAvatar).incHealth(-damage);

                    //Subtract the time
                    myAvatar.incTimeUnits(-((Melee)handItem).getTimeUnits());
                }else{
                    ////PRINT YOU DO NOT HAVE ENOUGH TIME//////////
                }
            }else{
                //PRINT NO TARGET!//////////////////////////////////////
            }


        }else if(action == GameSinglePlayerHandClicked.HEALSELF){

            //If there is enough time units to use
            if(myAvatar.getTimeUnits() >= ((UseAble)handItem).getTimeUnits()){

                //Heal self by time units
                myAvatar.incHealth(((Healing)handItem).getPower());

                //If it goes over, bring it down to max
                if(myAvatar.getHealth() > myAvatar.getMaxHealth()){
                    myAvatar.setHealth(myAvatar.getMaxHealth());
                }

                //Decrement or delete hand item
                if(handItem.getStackable() == true){

                    //If it is on its last item
                    if(handItem.getAmount() <= 1){

                        //Delete the item
                        if(leftHand){
                            ((PlayerHuman)g.myPlayer).setLeftHand(null);
                        }else{
                            ((PlayerHuman)g.myPlayer).setRightHand(null);
                        }
                    }else{

                        //Decrement the item
                        handItem.incAmount(-1);
                    }
                }else{

                    //Delete the item
                    if(leftHand){
                        ((PlayerHuman)g.myPlayer).setLeftHand(null);
                    }else{
                        ((PlayerHuman)g.myPlayer).setRightHand(null);
                    }
                }

                //Decrement the time untis
                myAvatar.incTimeUnits(-((UseAble)handItem).getTimeUnits());
            }

        }else if(action == GameSinglePlayerHandClicked.HEALOTHER){

            //Get the avatar in front of the player, if there is any
            Avatar inFrontOf = g.currentMap.getAvatarInFrontOf(myAvatar);


            //If there is a person in front of the player
            if(inFrontOf != null){

                //If there is enough time units to use
                if(myAvatar.getTimeUnits() >= ((UseAble)handItem).getTimeUnits()){

                    //Heal other by time units
                    inFrontOf.incHealth(((Healing)handItem).getPower());

                    //If it goes over, bring it down to max
                    if(inFrontOf.getHealth() >
                            inFrontOf.getMaxHealth()){
                        inFrontOf.setHealth(inFrontOf.getMaxHealth());
                    }

                    //Decrement or delete hand item
                    if(handItem.getStackable() == true){

                        //If it is on its last item
                        if(handItem.getAmount() <= 1){

                            //Delete the item
                            if(leftHand){
                                ((PlayerHuman)g.myPlayer).setLeftHand(null);
                            }else{
                                ((PlayerHuman)g.myPlayer).setRightHand(null);
                            }
                        }else{

                            //Decrement the item
                            handItem.incAmount(-1);
                        }
                    }else{

                        //Delete the item
                        if(leftHand){
                            ((PlayerHuman)g.myPlayer).setLeftHand(null);
                        }else{
                            ((PlayerHuman)g.myPlayer).setRightHand(null);
                        }
                    }

                    //Decrement the time untis
                    myAvatar.incTimeUnits(-((UseAble)handItem).getTimeUnits());
                    
                }

            }

        }else if(action == GameSinglePlayerHandClicked.THROW){


                            //Delete the item
                            if(leftHand){
                                ((PlayerHuman)g.myPlayer).setLeftHand(null);
                            }else{
                                ((PlayerHuman)g.myPlayer).setRightHand(null);
                            }

        }else if(action == GameSinglePlayerHandClicked.DROP){

            //Fix this
                            //Delete the item
                            if(leftHand){
                                ((PlayerHuman)g.myPlayer).setLeftHand(null);
                            }else{
                                ((PlayerHuman)g.myPlayer).setRightHand(null);
                            }

        }else if(action == GameSinglePlayerHandClicked.CANCEL){
            //DO NOTHING
        }

    }

    //Function to get damage from a weapon
    private int getRandomDamage(int percentage, int maxDamage){


        //Get the random for miss/hit
        int randomPerc = r.nextInt(100);

        //Get the damage of the gun from Random
        int damage;

        //if it is within the hit percentage
        if(randomPerc <= percentage){
            //If it is a critical hit
            if(randomPerc <= (percentage / 10)){
                return maxDamage * 2;
            }

            //Get damage Percent between 80% to 100%
            double percDamage = (((double)randomPerc / (double)percentage) * 0.2) + 0.8;
            
            //Get the damage from the percentage
            damage = (int)(percDamage * (double)maxDamage);
            return damage;
        }else{
            return 0;
        }
    }

    //Function to re-draw all the visible spots
    private void drawVisibleSpots(){

        //Set all the previous to false
        g.currentMap.setVisibleAll(false);

        //For all the humans
        for(Avatar a : g.currentMap.getHumanAvatars().getAllAvatars()){

            g.currentMap.addVisible(a, true);
        }

        //Set out human's visible to true
        g.currentMap.addVisible(myAvatar, true);
    }
}
