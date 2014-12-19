package com.example.owner.muccoursework;

import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by owner on 01/12/2014.
 */
public class SaveData extends MainActivity {

    SharedPreferences SharedPrefs;

    private String storedDriver;
    private String storedTrack;
    private String storedSummary;

    private void setStoredDriver(String isDriver)
    {
        this.storedDriver = isDriver;
    }

    public String getStoredDriver()
    {
        return storedDriver;
    }

    private void setStoredTrack(String isTrack)
    {
        this.storedTrack = isTrack;
    }

    public String getStoredTrack()
    {
        return storedTrack;
    }

    public void setStoredSummary(String storedSummary)
    {
        this.storedSummary = storedSummary;
    }

    public String getStoredSummary()
    {
        return storedSummary;
    }

    public SaveData(SharedPreferences StoredPrefs)
    {
        setStoredDriver("Nico Rosberg");
        setStoredTrack("Australia");
        setStoredSummary("Rosberg");
        try{
            this.SharedPrefs = StoredPrefs;
        }
        catch (Exception e)
        {
            Log.e("n", "Pref Manager is NULL");
        }
        setDefaultPrefs();
    }

    public void savePreferences(String key, boolean value){
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void savePreferences(String key, String value){
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void savePreferences(String key, int value){
        SharedPreferences.Editor editor = SharedPrefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void setDefaultPrefs(){
        savePreferences("tvDriver", "Nico Rosberg");
        savePreferences("tvTrack", "Australia");
        savePreferences("tvSummary", "Rosberg");
    }
}
