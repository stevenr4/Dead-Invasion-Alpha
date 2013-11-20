/*
 *
 *
 * A class that works as each item. It mainly stores the variables, name,
 * id, ect.
 *
 *
 *
 * /////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *
 *
 *
 *
 * Need to split this into a higharchy of items:
 *
 *
 *                           ITEM
 *                         /  |   \
 *                        /   |    \
 *                  UseAble  Misc   AMMO
 *                   /   \
 *                  /     \
 *              Weapon   Healing
 *               /   \
 *              /     \
 *           Melee   Projectile
 *
 *
 * Because not all variables are used in all instances of items,
 * and we can use the 'instance of' to identify the type of
 * item we are dealing with when printing or gameplay..
 *
 *
 * 
 * /////////////////////////////////////////////////////////////////////////////////////
 *
 */

package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Item{

    
    private String name;//The name of the item.
    private double value;//The money value of the item.
    private int amount;//The amount of items in this one item (example 9mm rounds x35)
    private boolean stackable;//If it can be stacked (if so, amount is used)


    //Creating a new item with no previous variables
    public Item(){
        name = "NOT INITIALIZED";
        value = 0;
        amount = 1;
        stackable = false;
    }

    public Item(Item itemToCopy){
        name = itemToCopy.getName();
        amount = itemToCopy.getAmount();
        stackable = itemToCopy.getStackable();
        value = itemToCopy.getValue();
    }

    //Set functions
    public void setName(String newName){
        name = newName;
    }
    public void setValue(int newValue){
        value = newValue;
    }
    public void setAmount(int newAmount){
        amount = newAmount;
    }
    public void setStackable(boolean newStackable){
        stackable = newStackable;
    }

    //Get functions
    public String getName(){
        return name;
    }
    public int getAmount(){
        return amount;
    }
    public boolean getStackable(){
        return stackable;
    }
    public double getValue(){
        return value;
    }

    //Increment variable by number given
    public void incValue(int toAdd){
        value += toAdd;
    }
    public void incAmount(int toAdd){
        amount += toAdd;
    }
}
