/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Melee extends Weapon{

    //Declare the variables
    private boolean blade;

    public Melee(){
        super();

        blade = false;
    }

    public Melee(Melee newMelee){
        super(newMelee);

        blade = newMelee.getBlade();
    }


    //Set functions
    public void setBlade(boolean newBlade){
        blade = newBlade;
    }

    //Get functions
    public boolean getBlade(){
        return blade;
    }
}
