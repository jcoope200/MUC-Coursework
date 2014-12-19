package com.example.owner.muccoursework;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by owner on 19/12/2014.
 */
public class MapActivity extends FragmentActivity {
    List<MapData> mapDataList;
    private Marker[] mapDataMarkerList = new Marker[19];
    private GoogleMap mapCircuits;
    private float markerColours[] = {210.0f, 120.0f, 300.0f, 330.0f, 270.0f, 210.0f, 120.0f, 300.0f, 330.0f, 270.0f, 210.0f, 120.0f, 300.0f, 330.0f, 270.0f, 210.0f, 120.0f, 300.0f, 330.0f};

    private LatLng latlangCircuits = new LatLng(55.8672, 4.2502);

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.map_view);

        mapDataList = new ArrayList<MapData>();
        MapDatabaseManager mapDB = new MapDatabaseManager(this, "circuitsMap.s3db", null, 1);
        try
        {
            mapDB.dbCreate();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        mapDataList = mapDB.allMapData();
        SetUpMap();
        AddMarkers();
    }

    public void SetUpMap()
    {
        mapCircuits = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        if(mapCircuits != null){
            mapCircuits.moveCamera(CameraUpdateFactory.newLatLngZoom(latlangCircuits, 12));
            mapCircuits.setMyLocationEnabled(true);
            mapCircuits.getUiSettings().setCompassEnabled(true);
            mapCircuits.getUiSettings().setMyLocationButtonEnabled(true);
            mapCircuits.getUiSettings().setRotateGesturesEnabled(true);
        }
    }

    public void AddMarkers()
    {
        MarkerOptions marker;
        MapData mapData;
        String mrkTitle;
        String mrkText;

        for(int i = 0; i < mapDataList.size(); i++)
        {
            mapData = mapDataList.get(i);
            mrkTitle = mapData.getCircuitName();
            mrkText = mapData.getCountryName();
            marker = SetMarker(mrkTitle, mrkText, new LatLng(mapData.getLatitude(), mapData.getLongitude()), markerColours[i], true);
            mapDataMarkerList[i] = mapCircuits.addMarker(marker);
        }
    }

    public MarkerOptions SetMarker(String title, String snippet, LatLng position, float markerColour, boolean centreAnchor)
    {
        float anchorX;
        float anchorY;

        if(centreAnchor)
        {
            anchorX = 0.5f;
            anchorY = 0.5f;
        }
        else
        {
            anchorX = 0.5f;
            anchorY = 1f;
        }

        MarkerOptions marker = new MarkerOptions().title(title).snippet(snippet).icon(BitmapDescriptorFactory.defaultMarker(markerColour)).anchor(anchorX, anchorY).position(position);
        return marker;
    }
}
