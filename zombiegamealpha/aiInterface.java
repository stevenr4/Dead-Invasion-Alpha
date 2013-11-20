/*
 * AI interface that is currently not used,
 * was designed to be the interface for ROBERT's AI module
 */

package zombiegamealpha;

import zombiegamealpha.data.Game;

/**
 *
 * @author spex
 */
public interface aiInterface {

    int DONE = 0;
    int FOWARD = 1;
    int TURNLEFT = 2;
    int TURNRIGHT = 3;
    int ATTACK = 4;


    public void setGame(Game iG);

    public int getMove();

}
