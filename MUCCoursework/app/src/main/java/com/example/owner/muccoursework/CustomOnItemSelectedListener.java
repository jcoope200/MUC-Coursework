package com.example.owner.muccoursework;

/**
 * Created by owner on 27/11/2014.
 */

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class CustomOnItemSelectedListener implements OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
        //test the toast functionality by displaying the value in the driver spinner as the app starts
        Toast.makeText(parent.getContext(), "Driver: " + parent.getItemAtPosition(pos).toString(),
        Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
