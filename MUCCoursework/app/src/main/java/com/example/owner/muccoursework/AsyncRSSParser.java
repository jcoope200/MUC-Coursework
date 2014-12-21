package com.example.owner.muccoursework;

/**
 * Created by owner on 18/12/2014.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;

public class AsyncRSSParser extends AsyncTask<String, Integer, RSSDataItem>
{
    private Context appContext;
    private String urlRSSToParse;

    public AsyncRSSParser(Context currentAppContext, String urlRSS)
    {
        appContext = currentAppContext;
        urlRSSToParse = urlRSS;
    }

    @Override
    protected void onPreExecute()
    {
        //display toast message to indicate the beginning of parsing
        Toast.makeText(appContext, "Parsing started!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected RSSDataItem doInBackground(String...params)
    {
        //create instances of the two parsing classes, RSSDataItem and RSSParser
        //parse the RSS feed's data and return it to the calling activity
        RSSDataItem parsedData;
        RSSParser rssParser = new RSSParser();
        try{
            rssParser.parseRSSData(urlRSSToParse);
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        parsedData = rssParser.getRSSData();
        return parsedData;
    }

    @Override
    protected void onPostExecute(RSSDataItem result){
        //display toast message on the screen to indicate end of parsing sequence
        Toast.makeText(appContext,"Parsing finished!", Toast.LENGTH_SHORT).show();
    }
}
