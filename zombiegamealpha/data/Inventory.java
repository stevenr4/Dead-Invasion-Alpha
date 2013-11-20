/*
 * A class to hold and manage an array of items.
 */

package zombiegamealpha.data;

/**
 *
 * @author spex
 */
import zombiegamealpha.data.items.Item;
import java.io.Serializable;

public class Inventory  implements Serializable {

    //Declare the variables for the inventory
    int total;//Length of the arrays
    Item items[];//Array to hold all items
    Item tmpItems[];//Temporary array to hold when size is changed

    //Set the variables when a new class is created.
    public Inventory(){
        total = 0;
        items = new Item[total];
        tmpItems = new Item[total];
    }

    //Copy over the inventory if created a new one.
    public Inventory(Inventory tmpInventory){
        this.copyInventory(tmpInventory);
    }

    //A function to return the item
    public Item getItem(int location){
        if(location <= total){
            return items[location];
        }else{
            return null;
        }
    }

    //A function to add an item to the array
    public void addItem(Item itemToAdd){
        /*for(int i = 0; i <= total; i++){
            tmpItems[i] = items[i];
        }*/ //Copies over the items, same as System.arraycopy(*)

        if(total <= 0)//If no items are in the inventory...
        {
            total = 1;//make the total 1
            items = new Item[total];//create a new item array
            items[0] = itemToAdd;//Add the item to the new array
            return;//end the function
        }

        total++;//increment total

        tmpItems = new Item[total]; //add one to counter 0
        
        System.arraycopy(items, 0, tmpItems, 0, total - 1);//Copy over to tmp

        tmpItems[total - 1] = itemToAdd;//add the item to the tmp
        items = new Item[total];//already added 1 to total

        //Copies over all items even the one we added in
        System.arraycopy(tmpItems, 0, items, 0, total);
    }

    //Function to take out one items
    public void takeOutItem(int location){


        /*
         * SEE AvatarManager IN THE TAKE OUT ITEM SECTION
         */



        if((location < 0) || (location >= total)){
            return;//If it is out of bounds, then return
        }else{

            total--;//shorten the total

            tmpItems = new Item[total];//create a new array to hold data

            System.arraycopy(items, 0, tmpItems, 0, total);//copy all items

            for(int i = location; i < total; i++){
                tmpItems[i] = items[i + 1];//copy everything but the taken out
            }

            items = new Item[total];//make the current items the new total
            System.arraycopy(tmpItems, 0, items, 0, total);//copy all items bac
        }
    }

    //Function to get the total
    public int getTotal(){
        return total;
    }

    //Copy over the selected inventory
    public void copyInventory(Inventory tmpInventory) {
        
        if((tmpInventory.getTotal() <= 0) || (tmpInventory == null))
        {//If the source has no elements or source is NULL
            
            //Make this a blank inventory
            total = 0;
            items = new Item[total];
            tmpItems = new Item[total];
            return;
        }else{//else it has more than one element

            //Copy over the total
            total = tmpInventory.getTotal();

            //Make the item array the same size as the copied over one
            items = new Item[total];

            //Copy over all the items
            for(int i = 0; i <= total - 1; i++){
                items[i] = tmpInventory.getItem(i);
            }
        }
    }
}
