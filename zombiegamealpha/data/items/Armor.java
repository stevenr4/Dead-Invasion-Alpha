/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Armor extends Item{

    private int defenceMax;//The amount of defence the armor can have
    private int defenceCurrent;//The amount that remains
    private byte type;//The type of armor it is

    public final static byte HEAD = 0;
    public final static byte TORSO = 1;
    public final static byte PANTS = 2;

    //Main creation function
    public Armor(){
        
        //Call the previous functions
        super();

        //Set default variables
        defenceMax = 0;
        defenceCurrent = 0;
        type = HEAD;
    }

    public Armor(Armor newArmor){

        //Set the previous functions
        super(newArmor);

        //Set the current variables
        defenceMax = newArmor.getDefenceMax();
        defenceCurrent = newArmor.getDefenceCurrent();
        type = newArmor.getType();
    }

    //Set functions
    public void setDefenceMax(int newDefenceMax){
        defenceMax = newDefenceMax;
    }
    public void setDefenceCurrent(int newDefenceCurrent){
        defenceCurrent = newDefenceCurrent;
    }
    public void setType(byte newType){
        type = newType;
    }

    //Get functions
    public int getDefenceMax(){
        return defenceMax;
    }
    public int getDefenceCurrent(){
        return defenceCurrent;
    }
    public byte getType(){
        return type;
    }

    //Special functions
    public void incDefenceMax(int toAdd){
        defenceMax += toAdd;
    }
    public void incDefenceCurrent(int toAdd){
        defenceCurrent += toAdd;
    }

    public static String getArmorTypeName(int type){
        switch(type){
            case HEAD:
                return "Head";
            case TORSO:
                return "Torso";
            case PANTS:
                return "Pants";
            default:
                return "ERROR";
        }
    }
}
