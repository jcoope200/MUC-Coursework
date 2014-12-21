package com.example.owner.muccoursework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity{

    private Spinner driverSpin, raceSpin;
    private Button btnSubmit;
    SaveData myStoredPrefs;
    SharedPreferences mySharedPrefs;
    FragmentManager AboutDialog;
    String OutputMsg;
    DatabaseInfo userDatabaseInfo;

    @Override
    public void onCreate(Bundle savedInstanceState){
        //constrain application to portrait orientation and attach the main_activity.xml file to the view
        //call the methods to add the races to the second spinner
        //give each item on the spinners a listener
        //get the default preferences for the spinner and store it as a variable
        //call fragment manager to set the About dialogue
        //create an instance of DatabaseInfo to handle the information of selected driver
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
        addItemsOnRaceSpinner();
        addListener();
        addListenerOnSelectedItem();
        mySharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        myStoredPrefs = new SaveData(mySharedPrefs);
        myStoredPrefs.setDefaultPrefs();

        AboutDialog = this.getFragmentManager();
        Log.e("n", "message");
        userDatabaseInfo = new DatabaseInfo();
    }

    public void addItemsOnRaceSpinner(){
        //populate race spinner with the races in an array list
        //set up an array adapter to use these values from a drop down value
        raceSpin = (Spinner) findViewById(R.id.raceSpin);
        List<String> listRace = new ArrayList<String>();
        listRace.add("Australia");
        listRace.add("Malaysia");
        listRace.add("Bahrain");
        listRace.add("China");
        listRace.add("Spain");
        listRace.add("Monaco");
        listRace.add("Canada");
        listRace.add("Austria");
        listRace.add("Great Britain");
        listRace.add("Germany");
        listRace.add("Hungary");
        listRace.add("Belgium");
        listRace.add("Italy");
        listRace.add("Singapore");
        listRace.add("Japan");
        listRace.add("Russia");
        listRace.add("United States");
        listRace.add("Brazil");
        listRace.add("Abu Dhabi");
        ArrayAdapter<String> dataAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listRace);
        dataAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        raceSpin.setAdapter(dataAdapt);
    }

    public void addListenerOnSelectedItem(){
        //give each driver a listener to handle events when they are chosen
        driverSpin = (Spinner) findViewById(R.id.driverSpin);
        driverSpin.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListener(){
        //create objects of Driver and Summary based on the selected driver
        driverSpin = (Spinner) findViewById(R.id.driverSpin);
        final String driverString = driverSpin.getSelectedItem().toString();
        raceSpin = (Spinner) findViewById(R.id.raceSpin);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        //btnNews = (Button) findViewById(R.id.btnNews);
        final Driver selectedDriver = new Driver(driverSpin.getSelectedItemPosition());
        final Summary selectedDriverSum = new Summary(driverSpin.getSelectedItemPosition(), driverSpin.getSelectedItemPosition()+1);
       // myStoredPrefs.savePreferences("Driver", selectedDriver.getDriverNumber());
       // myStoredPrefs.savePreferences("Driver", selectedDriverSum.getSummaryDriver());
       // final MainActivity that = this;

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //show a toast message with the chosen Driver and Race
                //determine the proper summary and link the mainactivity to DriverPage using an intent
                //create an instance of the FormulaOneDatabaseManager to handle database interactions
                //find the proper driver data based on the selected item on the spinner
                //put forward the database information and the RSS feed to the new intent and start DriverPage
                Toast.makeText(MainActivity.this, "Driver: "+String.valueOf(driverSpin.getSelectedItem())
                + "\nRace: " + String.valueOf(raceSpin.getSelectedItem()), Toast.LENGTH_SHORT).show();
                selectedDriverSum.determineDriverSummary(driverSpin.getSelectedItemPosition(), driverSpin.getSelectedItemPosition()+1);
                //selectedDriverSum.setChosenDriver(driverSpin.getSelectedItemPosition());
                //selectedDriverSum.setSummaryDriver(driverSpin.getSelectedItemPosition()+1);
                final Intent driverPage = new Intent(MainActivity.this, DriverPage.class);
                final FormulaOneDatabaseManager dbFormulaOneMgr = new FormulaOneDatabaseManager(getApplicationContext(),"formula1.s3db",null,1);
                try{
                    dbFormulaOneMgr.dbCreate();
                } catch (IOException e){
                    e.printStackTrace();
                }
                userDatabaseInfo = dbFormulaOneMgr.findDriverData(selectedDriverSum.getSummaryDriver());

                driverPage.putExtra("Formula1", userDatabaseInfo);
                OutputMsg = selectedDriver.getOutputString();
                driverPage.putExtra("RSS Feed", OutputMsg);


                //driverPage.putExtra("Formula1", userDatabaseInfo);
                //driverPage.putExtra("Value", driverString);
                //OutputMsg = "Your chosen driver is " + selectedDriverSum.getSummaryDriver();
                //driverPage.putExtra("Value", OutputMsg);
                //driverPage.putExtra("Summary", selectedDriverSum.getSummaryDriver());
                startActivity(driverPage);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //create the menu across the top of the app's screen
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //start different activities based on the ID of the selected menu item
        //Map and Draw start their corresponding activities
        //About creates the DialogFragment and displays it
        //Quit closes the app
        switch(item.getItemId()){
            case R.id.Map:
                Intent mapHere = new Intent(this, MapActivity.class);
                this.startActivity(mapHere);
                return true;
            case R.id.Draw:
                Intent drawHere = new Intent(this, DrawActivity.class);
                this.startActivity(drawHere);
                return true;
            case R.id.Quit:
                finish();
                return true;
            case R.id.About:
                DialogFragment AboutDlg = new AboutDialogue();
                AboutDlg.show(AboutDialog, "About_Dlg");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}