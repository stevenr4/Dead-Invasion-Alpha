/*
 * The user interface class for the game,
 * connects the sounds, buttons, graphics,
 * and all output/input of the game to the
 * user
 */

package zombiegamealpha.ui;

/**
 * @author spex
 */

//Import all of the files needed
//Not sure what a display mode is, but its used to define the screen's x and y
import java.awt.DisplayMode;
import java.awt.Graphics;//The UI gets the graphics to the screen
import javax.swing.JFrame;//Gives a frame that we can draw to
import java.awt.event.KeyListener;//Enables us to use Key Events
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;//Enables us to use mouse events
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class UI extends JFrame  implements KeyListener, MouseListener, MouseMotionListener{


    public int myWIDTH;//The width of the screen
    public int myHEIGHT;//The height of the screen
    public PrintManager myPrinter;//The class that handles printing
    public Screen myScreen;//The class that deals with the screen
    private DisplayMode dm;//The display mode of the screen
    public MouseVariables mouse;//A class to hold and manage the mouse variables
    public KeyVariables key;//A class to hold and manage the keyboard variables
    
    //Initialize all the variables
    public UI(int x, int y){

        //Print out that we are setting the screen size
        System.out.println("Setting the width: " + x);
        System.out.println("Setting the height: " + y);

        //Set the width and the height to the inputs
        myWIDTH = x;
        myHEIGHT = y;

        //Print out that we are setting the display mode
        System.out.println("Setting the display mode...");

        //Display mode is the screen resolution and all that jazz
        dm = new DisplayMode(myWIDTH,myHEIGHT,16,
                DisplayMode.REFRESH_RATE_UNKNOWN);

        //Print out that we are setting the display mode
        System.out.println("Setting the listeners...");

        //start the user input listener
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);

        //Set the printer
        myPrinter = new PrintManager(myWIDTH,myHEIGHT);

        //Set the screen
        myScreen = new Screen();

        //Set the mouse
        mouse = new MouseVariables();

        //Set the key
        key = new KeyVariables();
    }


    
    //The function that starts up the screen
    public void run(){
        
        //Set to full screen
        myScreen.setFullScreen(dm, this);
    }

    //Closes the screen
    public void close(){

        //Restore the screen
        myScreen.restoreScreen();
    }



    //Paint images to the screen.
    @Override
    public void paint(Graphics g){
        myPrinter.printScreen(g);
    }

    //Key actions
    public void keyReleased(KeyEvent k){}
    public void keyTyped(KeyEvent k){
        key.setLetter(k.getKeyChar());
    }
    public void keyPressed(KeyEvent k){

        key.setKey(k.getKeyCode());
        key.setKeyHit(true);
    }

    //Mouse actions
    public void mouseExited(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseClicked(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mousePressed(MouseEvent e){
        mouse.setClicked(true);
        mouse.setMouseButton(e.getButton());
    }
    public void mouseMoved(MouseEvent e){
        mouse.setLoc(e.getX(), e.getY());
        mouse.setMoved(true);
    }
    public void mouseDragged(MouseEvent e){
        mouse.setLoc(e.getX(), e.getY());
        mouse.setMoved(true);
        mouse.setDragged(true);
    }
}
