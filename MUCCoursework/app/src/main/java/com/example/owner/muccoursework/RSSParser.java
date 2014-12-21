package com.example.owner.muccoursework;

/**
 * Created by owner on 18/12/2014.
 */

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

public class RSSParser {

    private RSSDataItem RSSData;

    public void setRSSData(String ourItemData)
    {
        //each news item's title, description and link is given to an instance of the RSSDataItem class
        RSSData.setItemTitle(ourItemData);
        RSSData.setItemDescription(ourItemData);
        RSSData.setItemLink(ourItemData);
    }

    public RSSDataItem getRSSData()
    {
        return this.RSSData;
    }

    public RSSParser()
    {
        //each item is set to null to allow different headlines to populate a separate RSSDataItem object
        this.RSSData = new RSSDataItem();
        setRSSData(null);
    }

    public void parseRSSDataItem(XmlPullParser theParser, int theEventType)
    {
        //the document holding the RSS feed is thoroughly searched
        //if the line being searched has a starting tag, the text is stored in the RSSDataItem object
        //nested ifs store the text in the proper places depending on what the start tag was
        //exceptions are logged, either due to invalid tags or being unable to find the feed

        RSSData.setItemTitle("");
        RSSData.setItemDescription("");
        RSSData.setItemLink("");

        try
        {

            while (theEventType != XmlPullParser.END_DOCUMENT)
            {
                if(theEventType == XmlPullParser.START_TAG)
                {
                    if(theParser.getName().equalsIgnoreCase("title"))
                    {
                        String temp = theParser.nextText();
                        RSSData.setItemTitle(RSSData.getItemTitle()+temp+"\n"+"\n");
                    }
                    else
                        if(theParser.getName().equalsIgnoreCase("description"))
                        {
                            String temp = theParser.nextText();
                            RSSData.setItemDescription(RSSData.getItemDescription()+temp+"\n"+"\n");
                        }
                    else
                            if(theParser.getName().equalsIgnoreCase("link"))
                            {
                                String temp = theParser.nextText();
                                RSSData.setItemLink(RSSData.getItemLink()+temp+"\n"+"\n");
                            }
                }
                theEventType = theParser.next();

            }

        }
        catch(XmlPullParserException parserExp1)
        {
            Log.e("MyTag","Parsing error" + parserExp1.toString());
        }
        catch(IOException parserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }
    }

    public void parseRSSData(String RSSItemstoParse) throws MalformedURLException{
        //points to the feed to be parsed and sets up the parser
        URL rssURL = new URL(RSSItemstoParse);
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            int eventType = RSSxmlPP.getEventType();
            parseRSSDataItem(RSSxmlPP,eventType);
        }
        catch(XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch(IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }
        Log.e("MyTag", "End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        //used in order to read the text from the internet feed
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while(-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }
}
