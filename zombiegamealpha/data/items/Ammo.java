/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Ammo extends Item{

    public final static int HANDGUN = 0;
    public final static int RIFLE = 1;
    public final static int SHOTGUN = 2;


    private int ammoType;//The type of ammo it is
    private int damage;//The amount of damage it does

    public Ammo(){

        //Reseting all the ammo types
        ammoType = 0;
        damage = 10;
    }

    public Ammo(Ammo newAmmo){

        super(newAmmo);
        
        //Setting all types to new type
        ammoType = newAmmo.getAmmoType();
        damage = newAmmo.getDamage();


    }



    //Set funcitons
    public void setAmmoType(int newAmmoType){
        ammoType = newAmmoType;
    }
    public void setDamage(int newDamage){
        damage = newDamage;
    }


    //Get functions
    public int getAmmoType(){
        return ammoType;
    }
    public int getDamage(){
        return damage;
    }

    //Special functions
    public void incDamage(int toAdd){
        damage += toAdd;
    }

    public static String getAmmoTypeName(int inputType){
        switch(inputType){
            case HANDGUN:
                return "Handgun";
            case RIFLE:
                return "Rifle";
            case SHOTGUN:
                return "Shotgun";
            default:
                return "BROKEN?!";

        }
    }

}
