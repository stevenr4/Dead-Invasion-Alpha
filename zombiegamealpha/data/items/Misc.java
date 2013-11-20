/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data.items;

/**
 *
 * @author spex
 */
public class Misc extends Item{

    private boolean questItem;

    public Misc(){
        super();
        questItem = true;
    }

    public Misc(Misc newMisc){
        super(newMisc);
        questItem = newMisc.getQuestItem();
    }

    //Set functions
    public void setQuestItem(boolean newQuestItem){
        questItem = newQuestItem;
    }

    //Get functions
    public boolean getQuestItem(){
        return questItem;
    }
}
