



package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Weapon extends UseAble{

    private int damage;//The amount that the healing item heals...

    public Weapon(){
        //Set the previous variables
        super();

        //Set the current Variables
        damage = 0;
    }

    public Weapon(Weapon newWeapon){
        //Copy over the previous variables
        super(newWeapon);

        //Copy over the current variables
        damage = newWeapon.getDamage();
    }

    //Set functions
    public void setDamage(int newDamage){
        damage = newDamage;
    }

    //Get functions
    public int getDamage(){
        return damage;
    }
}
