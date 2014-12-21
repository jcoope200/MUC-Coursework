package com.example.owner.muccoursework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * Created by owner on 27/11/2014.
 */
public class DriverPage extends MainActivity implements View.OnClickListener{

    Button backButton;
    Button savedButton;
    ImageView driverImg;
    TextView tvDriver;
    TextView tvTeam;
    TextView tvAustralia;
    TextView tvMalaysia;
    TextView tvBahrain;
    TextView tvChina;
    TextView tvSpain;
    TextView tvMonaco;
    TextView tvCanada;
    TextView tvAustria;
    TextView tvGreatBritain;
    TextView tvGermany;
    TextView tvHungary;
    TextView tvBelgium;
    TextView tvItaly;
    TextView tvSingapore;
    TextView tvJapan;
    TextView tvRussia;
    TextView tvUnitedStates;
    TextView tvBrazil;
    TextView tvAbuDhabi;
    TextView tvNewsOutput;
    TextView tvNewsItem;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState){
        //set content view to the driver_page output screen
        //define variables to represent each of the various XML elements
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_page);
        backButton = (Button) findViewById(R.id.buttonBack);
        backButton.setOnClickListener(this);
        savedButton = (Button) findViewById(R.id.btnSavedData);
        savedButton.setOnClickListener(this);

        tvDriver = (TextView) findViewById(R.id.tvDriver);
        tvTeam = (TextView) findViewById(R.id.tvTeam);
        tvAustralia = (TextView) findViewById(R.id.tvAustralia);
        tvMalaysia = (TextView) findViewById(R.id.tvMalaysia);
        tvBahrain = (TextView) findViewById(R.id.tvBahrain);
        tvChina = (TextView) findViewById(R.id.tvChina);
        tvSpain = (TextView) findViewById(R.id.tvSpain);
        tvMonaco = (TextView) findViewById(R.id.tvMonaco);
        tvCanada = (TextView) findViewById(R.id.tvCanada);
        tvAustria = (TextView) findViewById(R.id.tvAustria);
        tvGreatBritain = (TextView) findViewById(R.id.tvGreatBritain);
        tvGermany = (TextView) findViewById(R.id.tvGermany);
        tvHungary = (TextView) findViewById(R.id.tvHungary);
        tvBelgium = (TextView) findViewById(R.id.tvBelgium);
        tvItaly = (TextView) findViewById(R.id.tvItaly);
        tvSingapore = (TextView) findViewById(R.id.tvSingapore);
        tvJapan = (TextView) findViewById(R.id.tvJapan);
        tvRussia = (TextView) findViewById(R.id.tvRussia);
        tvUnitedStates = (TextView) findViewById(R.id.tvUnitedStates);
        tvBrazil = (TextView) findViewById(R.id.tvBrazil);
        tvAbuDhabi = (TextView) findViewById(R.id.tvAbuDhabi);

        driverImg = (ImageView)findViewById(R.id.imgDriver);


        //create an intent object to correlate the output with the driver selected in the main activity
        Intent iMainAct = getIntent();

        //create instance of the DatabaseInfo object using the serializable data from the database
        DatabaseInfo mydatabaseInfo = (DatabaseInfo) iMainAct.getSerializableExtra("Formula1");

        //populate the text views and the image view with the chosen driver's information
        tvDriver.setText(mydatabaseInfo.getDriverName());
        tvTeam.setText(mydatabaseInfo.getDriverTeam());
        tvAustralia.setText("Australian Grand Prix: " + mydatabaseInfo.getAustraliaResult());
        tvMalaysia.setText("Malaysian Grand Prix: " + mydatabaseInfo.getMalaysiaResult());
        tvBahrain.setText("Bahrain Grand Prix: " + mydatabaseInfo.getBahrainResult());
        tvChina.setText("Chinese Grand Prix: " + mydatabaseInfo.getChinaResult());
        tvSpain.setText("Spanish Grand Prix: " + mydatabaseInfo.getSpainResult());
        tvMonaco.setText("Monaco Grand Prix: " + mydatabaseInfo.getMonacoResult());
        tvCanada.setText("Canadian Grand Prix: " + mydatabaseInfo.getCanadaResult());
        tvAustria.setText("Austrian Grand Prix: " + mydatabaseInfo.getAustriaResult());
        tvGreatBritain.setText("British Grand Prix: " + mydatabaseInfo.getGreatbritainResult());
        tvGermany.setText("German Grand Prix: " + mydatabaseInfo.getGermanyResult());
        tvHungary.setText("Hungarian Grand Prix: " + mydatabaseInfo.getHungaryResult());
        tvBelgium.setText("Belgian Grand Prix: " + mydatabaseInfo.getBelgiumResult());
        tvItaly.setText("Italian Grand Prix: " + mydatabaseInfo.getItalyResult());
        tvSingapore.setText("Singapore Grand Prix: " + mydatabaseInfo.getSingaporeResult());
        tvJapan.setText("Japanese Grand Prix: " + mydatabaseInfo.getJapanResult());
        tvRussia.setText("Russian Grand Prix: " + mydatabaseInfo.getRussiaResult());
        tvUnitedStates.setText("United States Grand Prix: " + mydatabaseInfo.getUnitedstatesResult());
        tvBrazil.setText("Brazilian Grand Prix: " + mydatabaseInfo.getBrazilResult());
        tvAbuDhabi.setText("Abu Dhabi Grand Prix: " + mydatabaseInfo.getAbudhabiResult());

        //tvNewsOutput.setText(iMainAct.getStringExtra("RSSNewsOutput"));


        //set the image path to get the right image depending on the resource ID
        String imagePath = "drawable" + mydatabaseInfo.getDriverImage();
        Context appContext = getApplicationContext();
        int imgResID = appContext.getResources().getIdentifier(imagePath, "drawable", getPackageName());
        driverImg.setImageResource(imgResID);

        //create an instance of the RSSDataItem class with the URL being set to the RSS feed's URL
        //create an instance of AsyncRSSParser using this app context and the URL
        //handle any exceptions such as the parser being interrupted or unable to find the feed
        //display the RSS entry's description to the textView
        RSSDataItem newsEntries = new RSSDataItem();
        String RSSFeedURL = "http://www.autosport.com/rss/f1news.xml";
        AsyncRSSParser rssAsyncParse = new AsyncRSSParser(this,RSSFeedURL);
        try{
            newsEntries = rssAsyncParse.execute("").get();
        } catch (InterruptedException e){
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        }

        tvNewsItem = (TextView) findViewById(R.id.tvNewsItem);
        tvNewsItem.setText(newsEntries.getItemDescription());
    }

    public void onClick(View view)
    {
        //all code has been completed if the back button has been hit, so end the intent and return to main activity
        setResult(Activity.RESULT_OK);
        finish();
    }



}
