package com.example.owner.muccoursework;

/**
 * Created by owner on 03/12/2014.
 */
public class Summary {

    private int chosenDriver;
    private String summaryDriver;

    public void setChosenDriver(int chosenDriver)
    {
        //use the chosen driver's ID to determine which driver has been selected and therefore which database entry to access later
        this.chosenDriver = chosenDriver;

    }

    public int getChosenDriver()
    {
        return chosenDriver;
    }

    public void setSummaryDriver(String summaryDriver)
    {
        this.summaryDriver = summaryDriver;
    }

    public String getSummaryDriver()
    {
        return summaryDriver;
    }

    public Summary()
    {
        determineDriverSummary(1, 1);
    }

    public Summary(int aDriver, int aSummary)
    {
        determineDriverSummary(aDriver, aSummary);
    }

    public void determineDriverSummary(int thatDriver, int thatSummary)
    {
        //make sure the numbers of the ID and case statement from the MainActivity call to this class return the proper driver
        switch(thatSummary){
            case 1:
                if (thatDriver == 0){
                    summaryDriver = "Nico Rosberg";
                }
                break;
            case 2:
                if (thatDriver == 1){
                    summaryDriver = "Lewis Hamilton";
                }
                break;
            case 3:
                if (thatDriver == 2){
                    summaryDriver = "Sebastian Vettel";
                }
                break;
            case 4:
                if (thatDriver == 3){
                    summaryDriver = "Daniel Ricciardo";
                }
                break;
            case 5:
                if (thatDriver == 4){
                    summaryDriver = "Felipe Massa";
                }
                break;
            case 6:
                if (thatDriver == 5){
                    summaryDriver = "Valtteri Bottas";
                }
                break;
            case 7:
                if (thatDriver == 6){
                    summaryDriver = "Kimi Raikkonen";
                }
                break;
            case 8:
                if (thatDriver == 7){
                    summaryDriver = "Fernando Alonso";
                }
                break;
            case 9:
                if (thatDriver == 8){
                    summaryDriver = "Kevin Magnussen";
                }
                break;
            case 10:
                if (thatDriver == 9){
                    summaryDriver = "Jenson Button";
                }
                break;
            case 11:
                if (thatDriver == 10){
                    summaryDriver = "Sergio Perez";
                }
                break;
            case 12:
                if (thatDriver == 11){
                    summaryDriver = "Nico Hulkenberg";
                }
                break;
            case 13:
                if (thatDriver == 12){
                    summaryDriver = "Jean-Eric Vergne";
                }
                break;
            case 14:
                if (thatDriver == 13){
                    summaryDriver = "Daniil Kvyat";
                }
                break;
            case 15:
                if (thatDriver == 14){
                    summaryDriver = "Romain Grosjean";
                }
                break;
            case 16:
                if (thatDriver == 15){
                    summaryDriver = "Pastor Maldonado";
                }
                break;
            case 17:
                if (thatDriver == 16){
                    summaryDriver = "Max Chilton";
                }
                break;
            case 18:
                if (thatDriver == 17){
                    summaryDriver = "Jules Bianchi";
                }
                break;
            case 19:
                if (thatDriver == 18){
                    summaryDriver = "Esteban Gutierrez";
                }
                break;
            case 20:
                if (thatDriver == 19){
                    summaryDriver = "Adrian Sutil";
                }
                break;
            case 21:
                if (thatDriver == 20){
                    summaryDriver = "Marcus Ericsson";
                }
                break;
            case 22:
                if (thatDriver == 21){
                    summaryDriver = "Kamui Kobayashi";
                }
                break;
            default:
                summaryDriver = "I'm sorry, the driver you have chosen has not been recognised.";
        }
    }
}
