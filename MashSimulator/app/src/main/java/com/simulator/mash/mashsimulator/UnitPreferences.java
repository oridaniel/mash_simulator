package com.simulator.mash.mashsimulator;

import android.content.Context;
import android.content.SharedPreferences;

public class UnitPreferences{

    /** This application's preferences label */
    private static final String PREFS_NAME = "com.simulator.mash.UnitPreferences";

    /** This application's preferences */
    private static SharedPreferences settings;

    /** This application's settings editor*/
    private static SharedPreferences.Editor editor;

    /** Constructor takes an android.content.Context argument*/
    public UnitPreferences(Context ctx){
        if(settings == null){
            settings = ctx.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE );
        }

        editor = settings.edit();
    }

    /** The prefix for flattened user keys */
    public static final String KEY_PREFIX = "com.simulator.mash.KEY";

    private String getFieldKey(String name, String fieldKey) {
        return  KEY_PREFIX + name + "_" + fieldKey;
    }

    /** generic field keys */
    private static final String KEY_NAME = "com.simulator.mash.KEY_NAME";
    private static final String KEY_RAISEEXPERIENCE = "com.simulator.mash.KEY_RAISEEXPERIENCE";
    private static final String KEY_BUTTONRAISEBALANCE = "com.simulator.mash.KEY_BUTTONRAISEBALANCE";
    private static final String KEY_AUTORAISEBALANCE = "com.simulator.mash.KEY_AUTORAISEBALANCE";
    private static final String KEY_PRICE = "com.simulator.mash.KEY_PRICE";
    private static final String KEY_AMOUNT = "com.simulator.mash.KEY_AMOUNT";

    /** Store or Update */
    public void setUnit(Unit unit){
        if(unit == null)
            return;

        String name = unit.getName();
        editor.putString(
                getFieldKey(name, KEY_NAME),
                unit.getName() );
        editor.putInt(
                getFieldKey(name, KEY_RAISEEXPERIENCE),
                unit.getRaiseExperience() );
        editor.putInt(
                getFieldKey(name, KEY_BUTTONRAISEBALANCE),
                unit.getButtonRaiseBalance() );
        editor.putInt(
                getFieldKey(name, KEY_AUTORAISEBALANCE),
                unit.getAutoRaiseBalance() );
        editor.putInt(
                getFieldKey(name, KEY_PRICE),
                unit.getPrice() );
        editor.putInt(
                getFieldKey(name, KEY_AMOUNT),
                unit.getAmount() );

        editor.commit();
    }

    /** Retrieve */
    public void getUnit(String name){
        int raisedExperience = settings.getInt(
                getFieldKey(name, KEY_RAISEEXPERIENCE),
                0);
        int buttonRaiseBalance = settings.getInt(
                getFieldKey(name, KEY_BUTTONRAISEBALANCE),
                0);
        int autoRaiseBalance = settings.getInt(
                getFieldKey(name, KEY_AUTORAISEBALANCE),
                0);
        int price = settings.getInt(
                getFieldKey(name, KEY_PRICE),
                0);
        int amount = settings.getInt(
                getFieldKey(name, KEY_AMOUNT),
                0);

       // return new Unit(name, raisedExperience, buttonRaiseBalance, autoRaiseBalance, price, amount);
        return;
    }

    /** Delete */
    public void deleteUnit(Unit unit){
        if(unit == null)
            return;

        String name = unit.getName();
        editor.remove( getFieldKey(name, KEY_NAME) );
        editor.remove( getFieldKey(name, KEY_RAISEEXPERIENCE) );
        editor.remove( getFieldKey(name, KEY_BUTTONRAISEBALANCE) );
        editor.remove( getFieldKey(name, KEY_AUTORAISEBALANCE) );
        editor.remove( getFieldKey(name, KEY_PRICE) );
        editor.remove( getFieldKey(name, KEY_AMOUNT) );

        editor.commit();
    }
}
