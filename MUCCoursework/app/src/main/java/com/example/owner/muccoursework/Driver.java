package com.example.owner.muccoursework;

/**
 * Created by owner on 18/12/2014.
 */
public class Driver {

    private int driverNumber;

    private String driverName;
    private String driverBrief;
    private String[] driverNames;
    private String[] driverBriefs;
    private String OutputString;

    public int getDriverNumber() {
        return driverNumber;
    }

    public void setDriverNumber(int driverNumber) {
        this.driverNumber = driverNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(int driverName) {
        this.driverName = driverNames[driverName];
    }

    public String getDriverBrief() {
        return driverBrief;
    }

    public void setDriverBrief(int driverBrief) {
        this.driverBrief = driverBriefs[driverBrief];
    }

    public String[] getDriverNames() {
        return driverNames;
    }

    public void setDriverNames() {
        driverNames = new String[22];
        driverNames[0] = "Nico Rosberg";
        driverNames[1] = "Lewis Hamilton";
        driverNames[2] = "Sebastian Vettel";
        driverNames[3] = "Daniel Ricciardo";
        driverNames[4] = "Felipe Massa";
        driverNames[5] = "Valtteri Bottas";
        driverNames[6] = "Kimi Raikkonen";
        driverNames[7] = "Fernando Alonso";
        driverNames[8] = "Kevin Magnussen";
        driverNames[9] = "Jenson Button";
        driverNames[10] = "Sergio Perez";
        driverNames[11] = "Nico Hulkenberg";
        driverNames[12] = "Jean-Eric Vergne";
        driverNames[13] = "Daniil Kvyat";
        driverNames[14] = "Romain Grosjean";
        driverNames[15] = "Pastor Maldonado";
        driverNames[16] = "Max Chilton";
        driverNames[17] = "Jules Bianchi";
        driverNames[18] = "Esteban Gutierrez";
        driverNames[19] = "Adrian Sutil";
        driverNames[20] = "Marcus Ericsson";
        driverNames[21] = "Kamui Kobayashi";
    }

    public String[] getDriverBriefs() {
        return driverBriefs;
    }

    public void setDriverBriefs() {
        driverBriefs = new String[22];
        driverBriefs[1] = "Nico Rosberg's driver number is 6.";
        driverBriefs[2] = "Lewis Hamilton's driver number is 44.";
        driverBriefs[3] = "Sebastian Vettel's driver number is 1.";
        driverBriefs[4] = "Daniel Ricciardo's driver number is 3.";
        driverBriefs[5] = "Felipe Massa's driver number is 19.";
        driverBriefs[6] = "Valtteri Bottas' driver number is 77.";
        driverBriefs[7] = "Kimi Raikkonen's driver number is 7.";
        driverBriefs[8] = "Fernando Alonso's driver number is 14.";
        driverBriefs[9] = "Kevin Magnussen's driver number is 20.";
        driverBriefs[10] = "Jenson Button's driver number is 22.";
        driverBriefs[10] = "Sergio Perez's driver number is 11.";
        driverBriefs[11] = "Nico Hulkenberg's driver number is 27.";
        driverBriefs[12] = "Jean-Eric Vergne's driver number is 25.";
        driverBriefs[13] = "Daniil Kvyat's driver number is 26.";
        driverBriefs[14] = "Romain Grosjean's driver number is 8.";
        driverBriefs[15] = "Pastor Maldonado's driver number is 13.";
        driverBriefs[16] = "Max Chilton's driver number is 4.";
        driverBriefs[17] = "Jules Bianchi's driver number is 17.";
        driverBriefs[18] = "Esteban Gutierrez's driver number is 21.";
        driverBriefs[19] = "Adrian Sutil's driver number is 99.";
        driverBriefs[20] = "Marcus Ericsson's driver number is 9.";
        driverBriefs[21] = "Kamui Kobayashi's driver number is 10.";
    }

    public String getOutputString() {
        return OutputString;
    }

    public void setOutputString(String outputString) {
        OutputString = outputString;
    }

    public Driver()
    {
        setDriverNames();
        setDriverBriefs();
    }

    public Driver(int driverNum)
    {
        setDriverNames();
        setDriverBriefs();
        setDriverNumber(driverNum);
        setDriverName(getDriverNumber());
        setDriverBrief(getDriverNumber());
        String temp = "Here is your driver: " + getDriverName() + "\n" + getDriverBrief();
        setOutputString(temp);

    }
}
