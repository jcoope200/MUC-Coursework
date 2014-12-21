package com.example.owner.muccoursework;

/**
 * Created by owner on 11/12/2014.
 */

import java.io.Serializable;

public class DatabaseInfo implements Serializable{

    private int driverID;
    private String driverName;
    private String driverTeam;
    private String driverImage;
    private String australiaResult;
    private String malaysiaResult;
    private String bahrainResult;
    private String chinaResult;
    private String spainResult;
    private String monacoResult;
    private String canadaResult;
    private String austriaResult;
    private String greatbritainResult;
    private String germanyResult;
    private String hungaryResult;
    private String belgiumResult;
    private String italyResult;
    private String singaporeResult;
    private String japanResult;
    private String russiaResult;
    private String unitedstatesResult;
    private String brazilResult;
    private String abudhabiResult;

    private static final long serialVersionUID = 0L;

    public int getDriverID(){
        return driverID;
    }

    public void setDriverID(int driverID){
        this.driverID = driverID;
    }

    public String getDriverName(){
        return driverName;
    }

    public void setDriverName(String driverName){
        this.driverName = driverName;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }


    public String getDriverImage(){
        return driverImage;
    }

    public void setDriverImage(String driverImage){
        this.driverImage = driverImage;
    }

    public String getAustraliaResult(){
        return australiaResult;
    }

    public void setAustraliaResult(String australiaResult){
        this.australiaResult = australiaResult;
    }

    public String getMalaysiaResult(){
        return malaysiaResult;
    }

    public void setMalaysiaResult(String malaysiaResult){
        this.malaysiaResult = malaysiaResult;
    }

    public String getBahrainResult(){
        return bahrainResult;
    }

    public void setBahrainResult(String bahrainResult){
        this.bahrainResult = bahrainResult;
    }

    public String getChinaResult(){
        return chinaResult;
    }

    public void setChinaResult(String chinaResult) {
        this.chinaResult = chinaResult;
    }

    public String getSpainResult() {
        return spainResult;
    }

    public void setSpainResult(String spainResult) {
        this.spainResult = spainResult;
    }

    public String getMonacoResult() {
        return monacoResult;
    }

    public void setMonacoResult(String monacoResult) {
        this.monacoResult = monacoResult;
    }

    public String getCanadaResult() {
        return canadaResult;
    }

    public void setCanadaResult(String canadaResult) {
        this.canadaResult = canadaResult;
    }

    public String getAustriaResult() {
        return austriaResult;
    }

    public void setAustriaResult(String austriaResult) {
        this.austriaResult = austriaResult;
    }

    public String getGreatbritainResult() {
        return greatbritainResult;
    }

    public void setGreatbritainResult(String greatbritainResult) {
        this.greatbritainResult = greatbritainResult;
    }

    public String getGermanyResult() {
        return germanyResult;
    }

    public void setGermanyResult(String germanyResult) {
        this.germanyResult = germanyResult;
    }

    public String getHungaryResult() {
        return hungaryResult;
    }

    public void setHungaryResult(String hungaryResult) {
        this.hungaryResult = hungaryResult;
    }

    public String getBelgiumResult() {
        return belgiumResult;
    }

    public void setBelgiumResult(String belgiumResult) {
        this.belgiumResult = belgiumResult;
    }

    public String getItalyResult() {
        return italyResult;
    }

    public void setItalyResult(String italyResult) {
        this.italyResult = italyResult;
    }

    public String getSingaporeResult() {
        return singaporeResult;
    }

    public void setSingaporeResult(String singaporeResult) {
        this.singaporeResult = singaporeResult;
    }

    public String getJapanResult() {
        return japanResult;
    }

    public void setJapanResult(String japanResult) {
        this.japanResult = japanResult;
    }

    public String getRussiaResult() {
        return russiaResult;
    }

    public void setRussiaResult(String russiaResult) {
        this.russiaResult = russiaResult;
    }

    public String getUnitedstatesResult() {
        return unitedstatesResult;
    }

    public void setUnitedstatesResult(String unitedstatesResult) {
        this.unitedstatesResult = unitedstatesResult;
    }

    public String getBrazilResult() {
        return brazilResult;
    }

    public void setBrazilResult(String brazilResult) {
        this.brazilResult = brazilResult;
    }

    public String getAbudhabiResult() {
        return abudhabiResult;
    }

    public void setAbudhabiResult(String abudhabiResult) {
        this.abudhabiResult = abudhabiResult;
    }

    @Override
    public String toString(){
        //combine database's field names with the data they relate to for each driver
        //return this long concatenated string as a serialized string
        String driverData;
        driverData = "DatabaseInfo [DriverID=" + driverID;
        driverData = ", DriverName=" + driverName;
        driverData = ", DriverTeam=" + driverTeam;
        driverData = ", DriverImage=" + driverImage;
        driverData = ", AustraliaResult=" + australiaResult;
        driverData = ", MalaysiaResult=" + malaysiaResult;
        driverData = ", BahrainResult=" + bahrainResult;
        driverData = ", ChinaResult=" + chinaResult;
        driverData = ", SpainResult=" + spainResult;
        driverData = ", MonacoResult=" + monacoResult;
        driverData = ", CanadaResult=" + canadaResult;
        driverData = ", AustriaResult=" + austriaResult;
        driverData = ", GreatBritainResult=" + greatbritainResult;
        driverData = ", GermanyResult=" + germanyResult;
        driverData = ", HungaryResult=" + hungaryResult;
        driverData = ", BelgiumResult=" + belgiumResult;
        driverData = ", ItalyResult=" + italyResult;
        driverData = ", SingaporeResult=" + singaporeResult;
        driverData = ", JapanResult=" + japanResult;
        driverData = ", RussiaResult=" + russiaResult;
        driverData = ", UnitedStatesResult=" + unitedstatesResult;
        driverData = ", BrazilResult=" + brazilResult;
        driverData = ", AbuDhabiResult=" + abudhabiResult + "]";
        return driverData;
    }
}
