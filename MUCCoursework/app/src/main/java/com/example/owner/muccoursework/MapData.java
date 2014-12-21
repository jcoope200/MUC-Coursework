package com.example.owner.muccoursework;

/**
 * Created by owner on 19/12/2014.
 */

import java.io.Serializable;

public class MapData implements Serializable{

    private int entryID;
    private String countryName;
    private String circuitName;
    private float Latitude;
    private float Longitude;

    private static final long serialVersionUID = 0L;

    public int getEntryID(){
        return entryID;
    }

    public void setEntryID(int entryID){
        this.entryID = entryID;
    }

    public String getCountryName(){
        return countryName;
    }

    public void setCountryName(String countryName){
        this.countryName = countryName;
    }

    public String getCircuitName(){
        return circuitName;
    }

    public void setCircuitName(String circuitName){
        this.circuitName = circuitName;
    }

    public float getLatitude()
    {
        return Latitude;
    }

    public void setLatitude(float Lat)
    {
        this.Latitude = Lat;
    }

    public float getLongitude()
    {
        return Longitude;
    }

    public void setLongitude(float fLongitude)
    {
        this.Longitude = fLongitude;
    }

    @Override
    public String toString(){
        //combine the circuit database's field names with the data they relate to in one long concatenated serializable string
        String mapData;
        mapData = "MapData [entryID=" + entryID;
        mapData = ", CountryName=" + countryName;
        mapData = ", CircuitName=" + circuitName;
        mapData = ", Latitude" + Latitude;
        mapData = ", Longitude" + Longitude + "]";
        return mapData;
    }
}
