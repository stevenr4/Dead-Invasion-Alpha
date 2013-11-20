/*
 *
 * This class holds and manages the variables for the keyboard input
 * into the game.
 *
 */

package zombiegamealpha.ui;

/**
 *
 * @author spex
 */
public class KeyVariables {

    private boolean keyHit;//If a key is hit or not
    private int key;//The key that was hit last
    private char letter;//The character of the key, for typing stuff.

    //Set the default variables
    KeyVariables(){
        keyHit = false;
        key = 0;
        letter = ' ';
    }

    //Setting the key that was hit
    public void setKeyHit(boolean newKeyHit){
        keyHit = newKeyHit;
    }
    public void setKey(int newKey){
        key = newKey;
    }
    public void setLetter(char newChar){
        letter = newChar;
    }

    //Returning the information that was
    public boolean getKeyHit(){
        return keyHit;
    }
    public int getKey(){
        return key;
    }
    public char getLetter(){
        return letter;
    }

    //Used for inputing the game, gets that the key was hit and sets it back.
    public boolean keyHit(){
        if(keyHit){
            keyHit = false;
            return true;
        }
        return false;
    }


}
