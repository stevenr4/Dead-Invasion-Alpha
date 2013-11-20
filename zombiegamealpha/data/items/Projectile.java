




package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Projectile extends Weapon{

    private int accuracy;//The weapon's accuracy (Scale of 0 to 100)
    private int ammoType;//The type of ammo it uses
    private int ammoCapacity;//The amount of ammo the gun can hold
    private int reloadingTimeUnits;//The amount of time it takes to reload
    private Ammo currentAmmo;//The ammo inside of the gun

    public Projectile(){
        super();

        accuracy = 0;
        ammoType = 0;
        ammoCapacity = 0;
        currentAmmo = null;
    }

    public Projectile(Projectile newProjectile){
        super(newProjectile);


        accuracy = newProjectile.getAccuracy();
        ammoType = newProjectile.getAmmoType();
        ammoCapacity = newProjectile.getAmmoCapacity();
        reloadingTimeUnits = newProjectile.getReloadingTimeUnits();
        currentAmmo = newProjectile.getCurrentAmmo();
    }


    //Set functions
    public void setAccuracy(int newAccuracy){
        accuracy = newAccuracy;
    }
    public void setAmmoType(int newAmmoType){
        ammoType = newAmmoType;
    }
    public void setAmmoCapacity(int newAmmoCapacity){
        ammoCapacity = newAmmoCapacity;
    }
    public void setReloadingTimeUnits(int newReloadingTimeUnits){
        reloadingTimeUnits = newReloadingTimeUnits;
    }
    public void setCurrentAmmo(Ammo newCurrentAmmo){
        currentAmmo = new Ammo(newCurrentAmmo);
    }

    //Get functions
    public int getAccuracy(){
        return accuracy;
    }
    public int getAmmoType(){
        return ammoType;
    }
    public int getAmmoCapacity(){
        return ammoCapacity;
    }
    public int getReloadingTimeUnits(){
        return reloadingTimeUnits;
    }
    public Ammo getCurrentAmmo(){
        return currentAmmo;
    }

    //Special functions
    public void incAccuracy(int toAdd){
        accuracy += toAdd;
    }
    public void incAmmoCapacity(int toAdd){
        ammoCapacity += toAdd;
    }

    public int getCombinedDamage(){
        if(currentAmmo == null){
            return 0;
        }else{
            return currentAmmo.getDamage() + this.getDamage();
        }
    }

    public int getAmountOfAmmo(){
        if(currentAmmo == null){
            return 0;
        }
        return currentAmmo.getAmount();
    }

    public void removeOneAmmo(){
        currentAmmo.incAmount(-1);
        if(currentAmmo.getAmount() == 0){
            currentAmmo = null;
        }
    }

}
