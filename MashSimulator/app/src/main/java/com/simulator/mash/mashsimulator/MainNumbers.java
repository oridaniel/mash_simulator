package com.simulator.mash.mashsimulator;

/**
 * Created by User on 2017. 12. 13..
 */

public class MainNumbers{

    private int mainBalance;
    private int mainExperience;
    private int mainButtonRaiseBalance;
    private int mainAutoRaiseBalance;

    /* Constructor */
    public MainNumbers(int mainBalance, int mainExperience, int mainButtonRaiseBalance, int mainAutoRaiseBalance){
        this.mainBalance = mainBalance;
        this.mainExperience = mainExperience;
        this.mainButtonRaiseBalance = mainButtonRaiseBalance;
        this.mainAutoRaiseBalance = mainAutoRaiseBalance;
    }

    /* Getters */
    public int getMainBalance(){
        return this.mainBalance;
    }

    public int getMainExperience(){
        return this.mainExperience;
    }

    public int getMainButtonRaiseBalance(){
        return this.mainButtonRaiseBalance;
    }

    public int getMainAutoRaiseBalance(){
        return this.mainAutoRaiseBalance;
    }

    /* Setters */
    public void setMainBalance(int mainBalance){
        this.mainBalance = this.mainBalance + mainBalance;
    }

    public void setMainExperience(int mainExperience){
        this.mainExperience = this.mainExperience + mainExperience;
    }

    public void setMainButtonRaiseBalance(int mainButtonRaiseBalance){
        this.mainButtonRaiseBalance = this.mainButtonRaiseBalance + mainButtonRaiseBalance;
    }

    public void setMainAutoRaiseBalance(int mainAutoRaiseBalance){
        this.mainAutoRaiseBalance = this.mainAutoRaiseBalance + mainAutoRaiseBalance;
    }
}
