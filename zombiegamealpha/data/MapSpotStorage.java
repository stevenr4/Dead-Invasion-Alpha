/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data;
import java.io.Serializable;

/**
 *
 * @author spex
 */
public class MapSpotStorage implements Serializable {

    private int totalMapSpots;
    private MapSpot[] mapSpots;
    private int[] xLoc, yLoc, zLoc;

    MapSpotStorage(){
        totalMapSpots = 0;
        mapSpots = new MapSpot[0];
        xLoc = new int[0];
        yLoc = new int[0];
        zLoc = new int[0];
    }

    MapSpotStorage(MapSpotStorage copy){
        totalMapSpots = copy.getTotalMapSpots();
        mapSpots = copy.getAllMapSpots();
        xLoc = copy.getAllX(totalMapSpots);
        yLoc = copy.getAllY(totalMapSpots);
        zLoc = copy.getAllZ(totalMapSpots);
    }
    public void addMapSpot(int x, int y, int z){
        addMapSpot(x, y, z, new MapSpot());
    }

    public void addMapSpot(int x, int y, int z, MapSpot toAdd){

            totalMapSpots++;
        if(totalMapSpots == 0){
            xLoc = new int[totalMapSpots];
            yLoc = new int[totalMapSpots];
            zLoc = new int[totalMapSpots];
            mapSpots[0] = toAdd;
        }else{
            MapSpot[] tmpMapSpots = new MapSpot[totalMapSpots];
            int[] tmpX = new int[totalMapSpots];
            int[] tmpY = new int[totalMapSpots];
            int[] tmpZ = new int[totalMapSpots];
            System.arraycopy(mapSpots, 0, tmpMapSpots, 0, mapSpots.length);
            System.arraycopy(xLoc, 0, tmpX, 0, xLoc.length);
            System.arraycopy(yLoc, 0, tmpY, 0, yLoc.length);
            System.arraycopy(zLoc, 0, tmpZ, 0, zLoc.length);
            tmpMapSpots[totalMapSpots - 1] = new MapSpot(toAdd);
            tmpX[totalMapSpots - 1] = x;
            tmpY[totalMapSpots - 1] = y;
            tmpZ[totalMapSpots - 1] = z;
            
            
            mapSpots = new MapSpot[totalMapSpots];
            xLoc = new int[totalMapSpots];
            yLoc = new int[totalMapSpots];
            yLoc = new int[totalMapSpots];
            
            System.arraycopy(tmpMapSpots, 0, mapSpots, 0, mapSpots.length);
            System.arraycopy(tmpX, 0, xLoc, 0, xLoc.length);
            System.arraycopy(tmpY, 0, yLoc, 0, yLoc.length);
            System.arraycopy(tmpZ, 0, zLoc, 0, zLoc.length);
        }
    }

    
    public void setTotalMapSpots(int newTotal){
        totalMapSpots = newTotal;
    }
    public void setMapSpot(MapSpot newMapSpot, int location){
        if((location < 0) || (location >= totalMapSpots)){
        }else{
            mapSpots[location] = new MapSpot(newMapSpot);
        }
    }

    public int getTotalMapSpots(){
        return totalMapSpots;
    }
    public MapSpot[] getAllMapSpots(){
        return mapSpots;
    }


    public MapSpot getOneMapSpot(int location){
        if((location < 0) || (location >= totalMapSpots)){
            return null;
        }else{
            return mapSpots[location];
        }
    }
    public int getOneX(int location){
        if((location < 0) || (location >= totalMapSpots)){
            return 0;
        }else{
            return xLoc[location];
        }
    }
    public int getOneY(int location){
        if((location < 0) || (location >= totalMapSpots)){
            return 0;
        }else{
            return yLoc[location];
        }
    }
    public int getOneZ(int location){
        if((location < 0) || (location >= totalMapSpots)){
            return 0;
        }else{
            return zLoc[location];
        }
    }
    public int[] getAllX(int location){
        return xLoc;
    }
    public int[] getAllY(int location){
        return yLoc;
    }
    public int[] getAllZ(int location){
        return zLoc;
    }
}
