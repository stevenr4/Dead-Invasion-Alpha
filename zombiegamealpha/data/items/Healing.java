/*
 *
 * An item that is used to heal yourself or other players
 *
 */
package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Healing extends UseAble{

    private int power;//The amount that the healing item heals...

    public Healing(){
        //Set the previous variables
        super();

        //Set the current Variables
        power = 0;
    }

    public Healing(Healing newHealing){
        //Copy over the previous variables
        super(newHealing);

        //Copy over the current variables
        power = newHealing.getPower();
    }

    //Set functions
    public void setPower(int newPower){
        power = newPower;
    }

    //Get functions
    public int getPower(){
        return power;
    }
}
