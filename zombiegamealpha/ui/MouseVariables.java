/*
 * This class holds and manages the variables
 * for the mouse's inputs to the game
 */

package zombiegamealpha.ui;

/**
 *
 * @author spex
 */

//The mouse events for inputing the variables
import java.awt.event.MouseEvent;

public class MouseVariables {

    private int x;//Mouse x Location
    private int y;//Mouse y Location

    private boolean clicked;//If it was clicked
    private boolean moved;//If the mouse has moved
    private boolean dragged;//If it is dragged
    private int mouseButton;//What button was clicked

    //Set all initial variables
    public MouseVariables(){
        x = 0;
        y = 0;
        clicked = false;
        moved = false;
        dragged = false;
        mouseButton = MouseEvent.BUTTON1;
    }

    //All get methods
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public boolean getClicked(){
        return clicked;
    }
    public boolean getMoved(){
        return moved;
    }
    public int getMouseButton(){
        return mouseButton;
    }
    public boolean getDragged(){
        return dragged;
    }

    //All set methods
    public void setLoc(int newX, int newY){
        x = newX;
        y = newY;
    }
    public void setClicked(boolean newClicked){
        clicked = newClicked;
    }
    public void setMoved(boolean newMoved){
        moved = newMoved;
    }
    public void setMouseButton(int newMouseButton){
        mouseButton = newMouseButton;
    }
    public void setDragged(boolean newDragged){
        dragged = newDragged;
    }


    //If it was clicked, change click to false and return true;
    public boolean clicked(){
        if(clicked){
            clicked = false;
            return true;
        }

        return false;
    }

    //If it was moved, change move and return true
    public boolean moved(){
        if(moved){
            moved = false;
            return true;
        }

        return false;
    }

    //If it was moved, change move and return true
    public boolean dragged(){
        if(dragged){
            dragged = false;
            return true;
        }

        return false;
    }
}
