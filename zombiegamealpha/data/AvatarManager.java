/*
 * An array to manage all of the avatars in play
 */

package zombiegamealpha.data;


/**
 *
 * @author spex
 */
public class AvatarManager {

    private int total;  //The total amount of avatars that are stored in this array
    private Avatar[] avatarArray;  //The array that holds the avatars.
    private Avatar[] tmpArray;  //The array that holds the temporary avatars.

    //If created with nothing, apply the base nothing information
    public AvatarManager(){
        total = 0;
        avatarArray = new Avatar[total];
        tmpArray = new Avatar[total];
    }

    //clear function
    public void clearAll(){
        total = 0;
        avatarArray = new Avatar[total];
        tmpArray = new Avatar[total];
    }

    //If given another AvatarManager
    public AvatarManager(AvatarManager amCopy){

        //If it is null, then apply the base information
        if(amCopy == null){
            total = 0;
            avatarArray = new Avatar[total];
            tmpArray = new Avatar[total];
        }else{
            //Copy over the total
            total = amCopy.getTotal();

            //Size the arrays to the total
            avatarArray = new Avatar[total];
            tmpArray = new Avatar[total];

            //Copy over all of the avatars into these arrays
            for(int i = 0; i < total; i++){
                avatarArray[i] = amCopy.getAvatar(i);
                tmpArray[i] = amCopy.getAvatar(i);
            }
        }
    }

    //Function to return the total
    public int getTotal(){
        return total;
    }

    //Get the avatar
    public Avatar getAvatar(int index){
        if((index < 0) || (index >= total)){
            return null;
        }else{
            return avatarArray[index];
        }
    }

    //Function to return the whole avatar array...
    public Avatar[] getAllAvatars(){
        return avatarArray;
    }


    //A function to add an item to the array
    public void addAvatar(Avatar avatarToAdd){

        if(total <= 0)//If no items are in the inventory...
        {
            total = 1;//make the length 1
            avatarArray = new Avatar[total];//create a new item array
            avatarArray[0] = new Avatar(avatarToAdd);//Add the item to the new array
            return;//end the function
        }

        total++;//increment length

        tmpArray = new Avatar[total];//Make TmpArray the size of the new array

        //Copy all items over to tmpArray
        System.arraycopy(avatarArray, 0, tmpArray, 0, total - 1);

        //Add the new item to the end of tmpArray
        tmpArray[total - 1] = new Avatar(avatarToAdd);

        //Now size our real array
        avatarArray = new Avatar[total];

        //Copy all items (including add-on) back into our used array
        System.arraycopy(tmpArray, 0, avatarArray, 0, total);
    }

    //Take out selected avatar
    public void takeOutAvatar(int location){
        if((location < 0) || (location >= total)){
            return;//If it is out of bounds, then return and ignore :D
        }else{

            //Bring the total down one (before we forget :3)
            total--;

            //Resize the tmpArray
            tmpArray = new Avatar[total];

            //Copy over all that we can...
            System.arraycopy(avatarArray, 0, tmpArray, 0, total);

            //Copy everything above the location over what we copied
            for(int i = location; i < total; i++){
                tmpArray[i] = avatarArray[i + 1];
            }

            /*
             * Example what were doing:
             * Taking out #5
             * ourArray:123456789
             * tmpArray:12345678 (last one cut cause of length
             *
             * ourArray:xxxx678 << copies over VV
             * tmpArray:1234678
             *
             * x = not copied
             */

            //Resized the used array
            avatarArray = new Avatar[total];

            //Copies back what were finally keeping
            System.arraycopy(tmpArray, 0, avatarArray, 0, total);
        }
    }


    //See if there is an avatar here
    public boolean avatarHere(int x, int y, int z){

        //If there are no avatars at all, of course there cant be an avatar
        //in the spot were are looking for, duh!
        if(total == 0){
            return false;
        }

        //For all of the avatars...
        for(int i = 0; i < total; i++){
            
            //If an avatar's X, Y, AND Z match, then return true, we got it.
            if((avatarArray[i].getX() == x) &&
                    (avatarArray[i].getY() == y) &&
                    (avatarArray[i].getZ() == z)){
                return true;
            }
        }

        //if we get here, none of the avatars are in the spot we are looking for
        return false;
    }

    //Get the index of the avater from a location
    public int getAvatarIndex(int x, int y, int z){

        //For all of the avatars...
        for(int i = 0; i < total; i++){

            //If our avatar is in this spot
            if((avatarArray[i].getX() == x) &&
                    (avatarArray[i].getY() == y) &&
                    (avatarArray[i].getZ() == z)){

                //Return the Index of this avatar....
                return i;
            }
        }

        //If we could not find anything, then return -1 as a sort of null....
        return -1;
    }


}
