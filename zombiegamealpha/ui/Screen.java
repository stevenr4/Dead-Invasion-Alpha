/*
 * 
 * Class to manage the screen fullscreen
 * and settings.
 *
 */

package zombiegamealpha.ui;

/**
 *
 * @author spex
 */

//import java.awt.*;
import java.awt.GraphicsDevice;//The graphics card
import java.awt.GraphicsEnvironment;//The environment that
import java.awt.DisplayMode;
import java.awt.Window;
import javax.swing.JFrame;

public class Screen {

    private GraphicsDevice myGraphicsDevice;//Get a graphics card

    //Setting up the screen class
    public Screen(){
        //Get the local graphics environment
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        myGraphicsDevice = env.getDefaultScreenDevice();//Set the graphics card to the screen

    }

    //Sets the window to full screen
    public void setFullScreen(DisplayMode dm, JFrame window){

        window.setUndecorated(true);//No buttons on the window
        window.setResizable(false);//Cannot resize

        myGraphicsDevice.setFullScreenWindow(window);//Put window in the full screen mode

        //If the display mode is not null, and the graphics devise supports change
        if(dm != null && myGraphicsDevice.isDisplayChangeSupported()){

            //Try to set the display mode
            try{
                myGraphicsDevice.setDisplayMode(dm);
            }catch(Exception ex){}
        }
    }

    //Function to return the winow
    public Window getFullScreenWindow(){
        return myGraphicsDevice.getFullScreenWindow();
    }

    //Restores the screen to close
    public void restoreScreen(){
        Window w = myGraphicsDevice.getFullScreenWindow();
        if(w != null){
            w.dispose();
        }
        myGraphicsDevice.setFullScreenWindow(null);
    }
}
