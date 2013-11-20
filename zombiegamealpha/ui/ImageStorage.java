
/*
 *
 * A class to hold an array of all images used in the game.
 *
 */

package zombiegamealpha.ui;



import java.awt.Image;//Import images so they can be held
import javax.swing.ImageIcon;//Used to pull images from the directories
/*
 * @author spex
 */
public class ImageStorage {

    private String directory;//The directory the images will be read from

    public static final int TOTALFLOORIMAGES = 14;//The total floor images soo far
    private Image floorImages[];//The array to store the floor images

    public static final int TOTALWALLIMAGES = 15;//The total wall images soo far
    private Image leftWallImages[];//Array to store the left wall images
    private Image rightWallImages[];//Array to store the right wall images

    public static final int TOTALOBJECTIMAGES = 2;//The total amount of objects
    private Image objectImages[];
    
    public static final int TOTALITEMIMAGES = 0;//The total item images
    private Image itemImages[];

    private Image curserBlue;//The curser images
    private Image curserImageFront;
    private Image curserImageBack;

    private Image blankScreen;//Misc images
    private Image mainMenuScreen;

    private Image editHud;//Editor images
    private Image noWalls;

    private Image healthBarCase;//HUD images
    private Image humanHudLeft;
    private Image humanHudRight;


    
    private final int totalHeadImages = 1;
    private Image headImages[][];
    private final int totalBodyImages = 1;
    private Image bodyImages[][];
    private final int totalLegsImages = 1;
    private Image legsImages[][];
    private final int totalWeaponImages = 1;
    private Image weaponImages[][];
    
    private Image humanPieceImages[];
    private Image zombiePieceImages[];
    

    //Load up all the images when class is created
    ImageStorage(){

        //Assign something to the directory
        directory = "";

        //Allocate memory for the Image Arrays.
        floorImages = new Image[TOTALFLOORIMAGES];
        leftWallImages = new Image[TOTALWALLIMAGES];
        rightWallImages = new Image[TOTALWALLIMAGES];
        headImages = new Image[totalHeadImages][8];
        bodyImages = new Image[totalBodyImages][8];
        legsImages = new Image[totalLegsImages][8];
        weaponImages = new Image[totalWeaponImages][8];
        objectImages = new Image[TOTALOBJECTIMAGES];
        itemImages = new Image[TOTALITEMIMAGES];
        humanPieceImages = new Image[8];
        zombiePieceImages = new Image[8];


        System.out.println("Loading floor images...");
        setFloorImages();//Load up all the Floor images
        System.out.println("Loading wall images...");
        setWallImages();//Load up all the wall images
        System.out.println("Loading curser images...");
        setCurserImage();//Load up the Curser Image
        System.out.println("Loading blank images...");
        setBlankScreen();//Load up the Blank Screen
        System.out.println("Loading head images...");
        setHeadImages();
        System.out.println("Loading body images...");
        setBodyImages();
        System.out.println("Loading leg images...");
        setLegsImages();
        System.out.println("Loading weapon images...");
        setWeaponImages();
        System.out.println("Loading menu images...");
        setMainMenuScreen();
        System.out.println("Loading hud images...");
        setEditHud();
        System.out.println("Loading object images...");
        setObjectImages();
        System.out.println("Loading misc images...");
        setItemImages();
        setNoWalls();
        setPieceImages();
        setHudImages();
    }

    //Load up each individual floor images
    private void setFloorImages(){

        //Set the directory for the tile directory
        directory = "ZombieImages/Tile/";

        //Get the pictures from the directory
        floorImages[0] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Green-redux.gif")).getImage();
        floorImages[1] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Blue.gif")).getImage();
        floorImages[2] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Green.gif")).getImage();
        floorImages[3] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Yellow.gif")).getImage();
        floorImages[4] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Red.gif")).getImage();
        floorImages[5] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Purple.gif")).getImage();
        floorImages[6] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Tile_1.gif")).getImage();
        floorImages[7] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Grass_1.gif")).getImage();
        floorImages[8] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Grass_2.gif")).getImage();
        floorImages[9] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Grass_3.gif")).getImage();
        floorImages[10] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Cement1.gif")).getImage();
        floorImages[11] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Water.gif")).getImage();
        floorImages[12] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Cement_Tile1.gif")).getImage();
        floorImages[13] = new ImageIcon(UI.class.getResource(directory + "80X40_Tile_Cement_Tile2.gif")).getImage();
    }

    //Load up each individual wall images
    private void setWallImages() {

        //Set the directory for the wall directory
        directory = "ZombieImages/Wall/";

        //Set the wall images
        leftWallImages[0] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Template_L.gif")).getImage();
        rightWallImages[0] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Template_R.gif")).getImage();
        leftWallImages[1] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Blue_L.gif")).getImage();
        rightWallImages[1] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Blue_R.gif")).getImage();
        leftWallImages[2] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Green_L.gif")).getImage();
        rightWallImages[2] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Green_R.gif")).getImage();
        leftWallImages[3] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Yellow_L.gif")).getImage();
        rightWallImages[3] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Yellow_R.gif")).getImage();
        leftWallImages[4] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Red_L.gif")).getImage();
        rightWallImages[4] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Red_R.gif")).getImage();
        leftWallImages[5] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Purple_L.gif")).getImage();
        rightWallImages[5] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Purple_R.gif")).getImage();
        leftWallImages[6] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Blue-Grey_L.gif")).getImage();
        rightWallImages[6] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Blue-Grey_R.gif")).getImage();
        leftWallImages[7] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Fence_L.gif")).getImage();
        rightWallImages[7] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Fence_R.gif")).getImage();
        leftWallImages[8] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_White_Fence_L.gif")).getImage();
        rightWallImages[8] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_White_Fence_R.gif")).getImage();
        leftWallImages[9] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Chain_L_2.gif")).getImage();
        rightWallImages[9] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Chain_R_2.gif")).getImage();
        leftWallImages[10] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Cement1_L.gif")).getImage();
        rightWallImages[10] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Cement1_R.gif")).getImage();
        leftWallImages[11] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Cement2_L.gif")).getImage();
        rightWallImages[11] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Cement2_R.gif")).getImage();
        leftWallImages[12] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Cement_Bunker1_L.gif")).getImage();
        rightWallImages[12] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_Cement_Bunker1_R.gif")).getImage();
        leftWallImages[13] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_MossyBrick_L.gif")).getImage();
        rightWallImages[13] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_MossyBrick_R.gif")).getImage();
        leftWallImages[14] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_MossyBrick_Pipe_L.gif")).getImage();
        rightWallImages[14] = new ImageIcon(UI.class.getResource(directory + "40X120_Wall_MossyBrick_Pipe_R.gif")).getImage();
    }

    //Load up each individual head images
    private void setHeadImages() {

        //Set the directory for the human head images
        directory = "ZombieImages/Human/Head/";

        //Get the images for the head
        headImages[0][0] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_0.png")).getImage();
        headImages[0][1] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_1.png")).getImage();
        headImages[0][2] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_2.png")).getImage();
        headImages[0][3] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_3.png")).getImage();
        headImages[0][4] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_4.png")).getImage();
        headImages[0][5] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_5.png")).getImage();
        headImages[0][6] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_6.png")).getImage();
        headImages[0][7] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Head_Template_7.png")).getImage();
    }

    //Load up each individual body images
    private void setBodyImages() {

        //Set the directory for the human body images
        directory = "ZombieImages/Human/Body/";

        //Get the human body images
        bodyImages[0][0] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_0.png")).getImage();
        bodyImages[0][1] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_1.png")).getImage();
        bodyImages[0][2] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_2.png")).getImage();
        bodyImages[0][3] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_3.png")).getImage();
        bodyImages[0][4] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_4.png")).getImage();
        bodyImages[0][5] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_5.png")).getImage();
        bodyImages[0][6] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_6.png")).getImage();
        bodyImages[0][7] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Body_Template_7.png")).getImage();
    }

    //Load up each individual legs images
    private void setLegsImages() {

        //Set the directory for the human leg images
        directory = "ZombieImages/Human/Legs/";

        //Get the leg images
        legsImages[0][0] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_0.png")).getImage();
        legsImages[0][1] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_1.png")).getImage();
        legsImages[0][2] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_2.png")).getImage();
        legsImages[0][3] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_3.png")).getImage();
        legsImages[0][4] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_4.png")).getImage();
        legsImages[0][5] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_5.png")).getImage();
        legsImages[0][6] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_6.png")).getImage();
        legsImages[0][7] = new ImageIcon(UI.class.getResource(directory + "80X120_Human_Legs_Template_7.png")).getImage();
    }

    //Load up each individual weapon images
    private void setWeaponImages() {
    }
    
    //Load up each individual object images
    private void setObjectImages() {

        //Set the directory for the objects
        directory = "ZombieImages/Objects/";

        //Get the object images
        objectImages[0] = new ImageIcon(UI.class.getResource(directory + "80X140_Objects_Ladder.gif")).getImage();
        objectImages[1] = new ImageIcon(UI.class.getResource(directory + "80X140_Objects_Desk1.gif")).getImage();
    }

    //Get the Curser's Image
    private void setCurserImage() {

        curserImageFront = new ImageIcon(UI.class.getResource("ZombieImages/Cursor/80X140_CurserFront.png")).getImage();
        
        curserImageBack = new ImageIcon(UI.class.getResource("ZombieImages/Cursor/80X140_CurserBack.png")).getImage();

        curserBlue = new ImageIcon(UI.class.getResource("ZombieImages/Cursor/80X140_Under_Curser.gif")).getImage();
        
    }

    private void setBlankScreen(){
        blankScreen = new ImageIcon(UI.class.getResource("ZombieImages/800X600_Blank_Screen.gif")).getImage();
    }

    private void setMainMenuScreen(){
        mainMenuScreen = new ImageIcon(UI.class.getResource("ZombieImages/800X600_Title_Screen.gif")).getImage();
    }

    private void setEditHud() {
        editHud = new ImageIcon(UI.class.getResource("ZombieImages/800X600_Editor_Hud.gif")).getImage();
    }

    private void setNoWalls(){
        noWalls = new ImageIcon(UI.class.getResource("ZombieImages/60X60_No_Walls.gif")).getImage();
    }

    //Return the selected floor image
    public Image getFloorImages(int location){
        if ((location < 0) || (location >= TOTALFLOORIMAGES)){
            return null;
        }else{
            return floorImages[location];
        }
    }
    
    //Load up each individual item images
    private void setItemImages() {
    }

    //Load up all the zombie and human images
    private void setPieceImages(){
        String base = "ZombieImages/Piece Markers/";
        for(int i = 1; i <= 8; i++){
            humanPieceImages[i - 1] = new ImageIcon(UI.class.getResource(base + "Human/000" + i + ".png")).getImage();
            zombiePieceImages[i - 1] = new ImageIcon(UI.class.getResource(base + "Zombie/000" + i + ".png")).getImage();
        }
    }

    //Load up the health bar case
    private void setHudImages(){
        healthBarCase = new ImageIcon(UI.class.getResource("ZombieImages/80X10_Health_Bar_Case.gif")).getImage();
        humanHudLeft = new ImageIcon(UI.class.getResource("ZombieImages/500X180_Human_Hud.gif")).getImage();
        humanHudRight = new ImageIcon(UI.class.getResource("ZombieImages/200X180_Mini_Menu.gif")).getImage();
    }











    

    //Return the piece images
    public Image getPieceImage(int dir, boolean human){
        if(human){
            return humanPieceImages[dir];
        }else{
            return zombiePieceImages[dir];
        }
    }


    //Return the selected left wall image
    public Image getLeftWallImages(int location){
        if((location < 0) || (location > TOTALWALLIMAGES)){
            return null;
        }else{
            return leftWallImages[location];
        }
    }

    //Return the selected right wall image
    public Image getRightWallImages(int location){
        if((location < 0) || (location > TOTALWALLIMAGES)){
            return null;
        }else{
            return rightWallImages[location];
        }
    }

    //Return the blank screen
    public Image getBlankScreen(){
        return blankScreen;
    }

    //Return the Curser's Image
    public Image getCurserImage(boolean front){
        if(front){
            return curserImageFront;
        }else{
            return curserImageBack;
        }
    }

    //Return the under-curser's Image
    public Image getCurserBlue(){
        return curserBlue;
    }

    //Return the Head's Image
    public Image getHeadImages(int location, int direction){
        return headImages[location][direction];
    }

    //Return the Body's Image
    public Image getBodyImages(int location, int direction){
        return bodyImages[location][direction];
    }

    //Return the Legs's Image
    public Image getLegsImages(int location, int direction){
        return legsImages[location][direction];
    }

    //Return the Weapon's Image
    public Image getWeaponImages(int location, int direction){
        return weaponImages[location][direction];
    }

    //Return the ObjectImage
    public Image getObjectImage(int location){
        return objectImages[location];
    }

    //Return the length of the arrays
    public int getTotalFloorImages(){
        return TOTALFLOORIMAGES;
    }
    public int getTotalWallImages(){
        return TOTALWALLIMAGES;
    }

    public Image getMainMenuScreen(){
        return mainMenuScreen;
    }

    public int getTotalObjectImages(){
        return TOTALOBJECTIMAGES;
    }

    //Return the edit hud screen
    public Image getEditHud(){
        return editHud;
    }

    public Image getNoWalls(){
        return noWalls;
    }

    public Image getHealthBarCase(){
        return healthBarCase;
    }

    public Image getHumanHudLeft(){
        return humanHudLeft;
    }

    public Image getHumanHudRight(){
        return humanHudRight;
    }
}
