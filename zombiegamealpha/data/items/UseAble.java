/*
 * An item that can be used by the player
 */

package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class UseAble extends Item{

    private int timeUnits;

    public UseAble(){
        //Set previous variables
        super();

        //Set current Variables
        timeUnits = 0;
    }

    public UseAble(UseAble newUseAble){
        //Set previous variables
        super(newUseAble);

        //Set the current variables
        timeUnits = newUseAble.getTimeUnits();
    }

    //Set functions
    public void setTimeUnits(int newTimeUnits){
        timeUnits = newTimeUnits;
    }

    //Get functions
    public int getTimeUnits(){
        return timeUnits;
    }
}
