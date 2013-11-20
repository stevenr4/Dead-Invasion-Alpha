

package zombiegamealpha;

import zombiegamealpha.data.Game;//Import the main game
import zombiegamealpha.menus.MenuLogin;//Import the Login Menu
import zombiegamealpha.ui.UI;//Import the User Interface (UI)

/**
 *
 * @author spex
 */
public class MainClass {


    //Create a new game to hold all the variables in the game.
    private Game g;
    public static final int x = 800;//NORMAL 800
    public static final int y = 600;// NORMAL 600


    //The Startup funcion (runs without making a new class)
    public static void main(String[] args) {

        //Create a new main class (*hint* this class)
        MainClass mc = new MainClass();

        //Run the function within this new class (right below this one)
        mc.run();

        //Quit the application
        System.exit(0);
    }


    //The function that runs from within this class
    public void run(){

        //Set the game variables
        g = new Game();

        //Set the User Interface
        g.ui = new UI(x,y);

        //Run the User Interface (opens full-screen)
        g.ui.run();

        //Create a new Main Menu (import the game vairables)
        MenuLogin mm = new MenuLogin(g);

        //Run the Main Menu
        mm.run();

        //** When the Main Menu Closes, the game is over and ended....

        //Close the full-screen
        g.ui.close();


        //Put a friendly "Goodbye" Message
        System.out.println("\n-- Goodbye --\n");


        //END HERE
        return;
    }

}
