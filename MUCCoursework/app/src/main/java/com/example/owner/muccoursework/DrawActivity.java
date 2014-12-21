package com.example.owner.muccoursework;

/**
 * Created by owner on 18/12/2014.
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;

public class DrawActivity extends Activity{

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        //initialises the XML view draw_screen to the content view associated with the activity
        //forces a change to landscape orientation
        //passes the context to a new instance of the DrawingSurfaceView class
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(new DrawingSurfaceView(this));
    }
}
