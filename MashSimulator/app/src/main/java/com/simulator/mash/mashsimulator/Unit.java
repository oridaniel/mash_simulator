package com.simulator.mash.mashsimulator;

public class Unit {

    private int id;
    private String name;
    private int raiseExperience;
    private int buttonRaiseBalance;
    private int autoRaiseBalance;
    private int price;
    private int amount;

    /* Constructor */
    public Unit(int id, String name, int raiseExperience, int buttonRaiseBalance, int autoRaiseBalance, int price, int amount){
        this.id = id;
        this.name = name;
        this.raiseExperience = raiseExperience;
        this.buttonRaiseBalance = buttonRaiseBalance;
        this.autoRaiseBalance = autoRaiseBalance;
        this.price = price;
        this.amount = amount;
    }

    /* Getters */
    public int getId() { return this.id; }
    public String getName(){
        return this.name;
    }

    public int getRaiseExperience(){
        return this.raiseExperience;
    }

    public int getButtonRaiseBalance(){
        return this.buttonRaiseBalance;
    }

    public int getAutoRaiseBalance(){
        return this.autoRaiseBalance;
    }

    public int getPrice(){
        return this.price;
    }

    public int getAmount(){
        return this.amount;
    }

    /* Setters */
    public void setName(String name){
        this.name = name;
    }

    public void setRaiseExperience(int raiseExperience){
        this.raiseExperience = raiseExperience;
    }

    public void setButtonRaiseBalance(int buttonRaiseBalance){
        this.buttonRaiseBalance = buttonRaiseBalance;
    }

    public void setAutoRaiseBalance(int autoRaiseBalance){
        this.autoRaiseBalance = autoRaiseBalance;
    }

    public void setPrice(int price){
        this.price = price;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }
}
