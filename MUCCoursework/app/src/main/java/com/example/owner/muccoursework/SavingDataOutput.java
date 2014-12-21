package com.example.owner.muccoursework;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by owner on 01/12/2014.
 */
public class SavingDataOutput extends MainActivity implements View.OnClickListener{

    SharedPreferences SharedPrefs;
    Button btnBack;
    TextView tvFavDriver;
    TextView tvFavTrack;
    TextView tvFavSummary;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onCreate(Bundle savedInstanceState){
        //uses the context of the application in order to use the getDefaultSharedPreferences method
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_prefs);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        tvFavDriver = (TextView) findViewById(R.id.tvFavDriver);
        tvFavTrack = (TextView) findViewById(R.id.tvFavTrack);
        tvFavSummary = (TextView) findViewById(R.id.tvFavSummary);
        SharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        loadSavedPreferences();
        Log.e("n", "SDOutput msg");
    }

    private void loadSavedPreferences(){
        //the default values are placed in the shared preferences object and put into the text views
        tvFavDriver.setText(tvFavDriver.getText() + SharedPrefs.getString("tvDriver", "Nico Rosberg"));
        tvFavTrack.setText(tvFavTrack.getText() + SharedPrefs.getString("tvFavTrack", "Australia"));
        tvFavSummary.setText(tvFavSummary.getText() + SharedPrefs.getString("tvSummary", "Rosberg"));
    }

    public void onClick(View view) {
        setResult(Activity.RESULT_OK);
        finish();
    }
}
