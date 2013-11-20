/*
 *
 * The base avatar class for the characters in the field.
 *
 * An avatar is like a blank chess piece.....
 *
 */

package zombiegamealpha.data;

/**
 *
 * @author spex
 */
public class Avatar {

    private int x;//The x location of the avatar
    private int y;//The y location of the avatar
    private int z;//The z location of the avatar
    private int direction;//The direction the avatar is facing
    private int headId;//The ID of the image of the head
    private int bodyId;//The ID of the image of the body
    private int legsId;//The ID of the image of the legs
    private int weaponId;//The ID of the image of the weapon
    private int health;//The current health of the avatar
    private int maxHealth;//The max health of the avatar
    //private boolean human;//If it is a human or not
    private int timeUnits;//The amount of time the avatar has
    private int maxTimeUnits;//The maximum amount of time units

    //New blank avatar, set all variables.....
    public Avatar(){
        reset();
    }

    //If input another avatar at creation
    public Avatar(Avatar toCopy){

        //If a null is passed, then apply default variables..
        if(toCopy == null){
            reset();
        }else{
            x = toCopy.getX();
            y = toCopy.getY();
            z = toCopy.getZ();
            direction = toCopy.getDirection();
            headId = toCopy.getHeadId();
            bodyId = toCopy.getBodyId();
            legsId = toCopy.getLegsId();
            weaponId = toCopy.getWeaponId();
            health = toCopy.getHealth();
            maxHealth = toCopy.getMaxHealth();
            timeUnits = toCopy.getTimeUnits();
            maxTimeUnits = toCopy.getTimeUnits();
        }
    }

    //Function to set all base variables.
    public void reset(){
            x = 0;
            y = 0;
            direction = 0;
            headId = 0;
            bodyId = 0;
            legsId = 0;
            weaponId = 0;
            health = 0;
            maxHealth = 0;
            timeUnits = 30;
            maxTimeUnits = 30;
    }

    
    //Functions to set any of the values
    public void setX(int newX){
        x = newX;
    }
    public void setY(int newX){
        y = newX;
    }
    public void setZ(int newZ){
        z = newZ;
    }
    public void setDirection(int newDirection){
        direction = newDirection;
    }
    public void setHeadId(int newHeadId){
        headId = newHeadId;
    }
    public void setBodyId(int newBodyId){
        bodyId = newBodyId;
    }
    public void setLegsId(int newLegsId){
        legsId = newLegsId;
    }
    public void setWeaponId(int newWeaponId){
        weaponId = newWeaponId;
    }
    public void setHealth(int newHealth){
        health = newHealth;
    }
    public void setMaxHealth(int newMaxHealth){
        maxHealth = newMaxHealth;
    }
    public void setTimeUnits(int newTimeUnits){
        timeUnits = newTimeUnits;
    }
    public void setMaxTimeUnits(int newMaxTimeUnits){
        maxTimeUnits = newMaxTimeUnits;
    }

    //Functions to get any of the values
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }
    public int getDirection(){
        return direction;
    }
    public int getHeadId(){
        return headId;
    }
    public int getBodyId(){
        return bodyId;
    }
    public int getLegsId(){
        return legsId;
    }
    public int getWeaponId(){
        return weaponId;
    }
    public int getHealth(){
        return health;
    }
    public int getMaxHealth(){
        return maxHealth;
    }
    public int getTimeUnits(){
        return timeUnits;
    }
    public int getMaxTimeUnits(){
        return maxTimeUnits;
    }

    //Functions to increase any of the values
    public void incX(int toAdd){
        x += toAdd;
    }
    public void incY(int toAdd){
        y += toAdd;
    }
    public void incZ(int toAdd){
        z += toAdd;
    }
    public void incDirectionClockWise(int toAdd){
        direction += toAdd;
        if(direction > 7){
            direction = 0;
        }if(direction < 0){
            direction = 7;
        }
    }
    public void incHealth(int toAdd){
        health += toAdd;
    }
    public void incMaxHealth(int toAdd){
        maxHealth += toAdd;
    }
    public void incTimeUnits(int toAdd){
        timeUnits += toAdd;
    }

    //Special Functions
    public void resetTimeUnits(){
        timeUnits = maxTimeUnits;
    }

    public boolean enoughTimeUnits(int amount){
        if(timeUnits > amount){
            return true;
        }else{
            return false;
        }
    }
}
