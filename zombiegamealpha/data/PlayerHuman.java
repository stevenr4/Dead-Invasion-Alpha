/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package zombiegamealpha.data;

import zombiegamealpha.data.items.*;

/**
 *
 * @author spex
 */

public class PlayerHuman extends Player{

    private int strength;//How strong the character is
    private int speed;//How fast the character is
    private int accuracy;//How accurate the character is
    private int endurance;//How well they use their armor
    private Armor head;//The helmet the character is wearing
    private Armor torso;//The armor the character is wearing
    private Armor pants;//The pants the character is wearing
    private Item leftHand;//The weapon the character is holding
    private Item rightHand;//The ammo the character has
    private Inventory inventory;//The inventory that the character is holding
    private Inventory safeBox;//The safebox that holds items in base
    private int money;//The amount of money the character has

    public PlayerHuman(){

        strength = 0;
        speed = 0;
        accuracy = 0;
        endurance = 0;
        head = null;
        torso = null;
        pants = null;
        leftHand = null;
        rightHand = null;
        inventory = new Inventory();
        safeBox = new Inventory();
        money = 0;


        

        ///////////////////////////////////////////////////////////////////////////////////////////////////
        //SETTING FAKE INVENTORY FOR INVENTORY TESTING


        head = new Armor();
        head.setName("Swat Helmet");
        head.setDefenceMax(13);
        head.setType(Armor.HEAD);
        head.setValue(60);

        torso = new Armor();
        torso.setName("Kevlar");
        torso.setDefenceMax(40);
        torso.setType(Armor.TORSO);
        torso.setValue(400);


        pants = new Armor();
        pants.setName("Jeans");
        pants.setDefenceMax(21);
        pants.setType(Armor.PANTS);
        pants.setValue(120);

        leftHand = new Projectile();
        ((Projectile)leftHand).setName("Revolver");
        ((Projectile)leftHand).setValue(9001);
        ((Projectile)leftHand).setAccuracy(100);
        ((Projectile)leftHand).setAmmoCapacity(99);
        ((Projectile)leftHand).setAmmoType(Ammo.HANDGUN);
        ((Projectile)leftHand).setAmount(1);
        ((Projectile)leftHand).setDamage(200);
        ((Projectile)leftHand).setReloadingTimeUnits(1);
        ((Projectile)leftHand).setStackable(false);
        ((Projectile)leftHand).setTimeUnits(1);


        Ammo a = new Ammo();
        a.setAmount(99);
        a.setName(".45 'Nades");
        a.setDamage(9999);
        a.setAmmoType(Ammo.HANDGUN);
        a.setStackable(true);
        a.setValue(20000);

        ((Projectile)leftHand).setCurrentAmmo(a);

        rightHand = new Melee();
        ((Melee)rightHand).setBlade(true);
        ((Melee)rightHand).setDamage(30);
        ((Melee)rightHand).setName("Butcher Knife");
        ((Melee)rightHand).setTimeUnits(7);
        ((Melee)rightHand).setValue(180);


        a.setAmount(60);
        inventory.addItem(a);//0
        
        a = new Ammo(a);
        a.setName("Hollow Point .45 Rounds");
        a.setDamage(8);
        a.setAmount(38);
        inventory.addItem(a);//1
        
        Misc mi = new Misc();
        mi.setName("Blue Key Card");
        mi.setQuestItem(true);
        mi.setValue(0);
        inventory.addItem(mi);//2

        Healing h = new Healing();
        h.setName("Duct tape");
        h.setPower(10);
        h.setTimeUnits(6);
        h.setValue(40);
        inventory.addItem(h);//3

        
    }

    //Set functions
    public void setStrength(int newStrength){
        strength = newStrength;
    }
    public void setSpeed(int newSpeed){
        speed = newSpeed;
    }
    public void setAccuracy(int newAccuracy){
        accuracy = newAccuracy;
    }
    public void setEndurance(int newEndurance){
        endurance = newEndurance;
    }
    public void setHead(Armor newHelmet){
        head = newHelmet;
    }
    public void setTorso(Armor newArmor){
        torso = newArmor;
    }
    public void setPants(Armor newPants){
        pants = newPants;
    }
    public void setLeftHand(Item newWeapon){
        leftHand = newWeapon;
    }
    public void setRightHand(Item newAmmo){
        rightHand = newAmmo;
    }
    public void setInventory(Inventory newInventory){
        inventory = newInventory;
    }
    public void setSafeBox(Inventory newSafeBox){
        safeBox = newSafeBox;
    }
    public void setMoney(int newMoney){
        money = newMoney;
    }

    //The GET functions
    public int getStrength(){
        return strength;
    }
    public int getSpeed(){
        return speed;
    }
    public int getAccuracy(){
        return accuracy;
    }
    public int getEndurance(){
        return endurance;
    }
    public Armor getHead(){
        return head;
    }
    public Armor getTorso(){
        return torso;
    }
    public Armor getPants(){
        return pants;
    }
    public Item getLeftHand(){
        return leftHand;
    }
    public Item getRightHand(){
        return rightHand;
    }
    public Inventory getInventory(){
        return inventory;
    }
    public Inventory getSafeBox(){
        return safeBox;
    }
    public int getMoney(){
        return money;
    }

    //Increment functions
    public void incStrength(int addStrength){
        strength += addStrength;
    }
    public void incSpeed(int addSpeed){
        speed += addSpeed;
    }
    public void incAccuracy(int addAccuracy){
        accuracy += addAccuracy;
    }
    public void incEndurance(int addEndurance){
        endurance += addEndurance;
    }
    public void incMoney(int addMoney){
        money += addMoney;
    }

    //Extra get functions
    public int[] getAllStats(){
        int tmpStorage[] = new int[4];
        tmpStorage[0] = strength;
        tmpStorage[1] = speed;
        tmpStorage[2] = accuracy;
        tmpStorage[3] = endurance;

        return tmpStorage;
    }

    public void setAllStats(int newStats[]){
        if(newStats.length != 4){
            return;
        }
        strength = newStats[0];
        speed = newStats[1];
        accuracy = newStats[2];
        endurance = newStats[3];
    }

    public String[] getAllStatNames(){
        String tmpStorage[] = new String[4];

        tmpStorage[0] = "Str.";
        tmpStorage[1] = "Spd.";
        tmpStorage[2] = "Acc.";
        tmpStorage[3] = "End.";

        return tmpStorage;
    }
}
