package com.example.owner.muccoursework;

/**
 * Created by owner on 18/12/2014.
 */

import java.io.Serializable;

public class RSSDataItem implements Serializable{

    private String itemTitle;
    private String itemDescription;
    private String itemLink;

    public String getItemTitle()
    {
        return this.itemTitle;
    }

    public void setItemTitle (String ourItemTitle)
    {
        this.itemTitle = ourItemTitle;
    }

    public String getItemDescription()
    {
        return this.itemDescription;
    }

    public void setItemDescription(String ourItemDescription)
    {
        this.itemDescription = ourItemDescription;
    }

    public String getItemLink()
    {
        return this.itemLink;
    }

    public void setItemLink(String ourItemLink)
    {
        this.itemLink = ourItemLink;
    }

    public RSSDataItem()
    {
        //construct an object based on the RSS feed's title, description and link
        this.itemTitle = "";
        this.itemDescription = "";
        this.itemLink = "";
    }

    public String toString(){
        //grab the top headline from the feed and store it in a concatenated, serializable string
        String FormulaOneNewsData;
        FormulaOneNewsData = "RSSDataItem [itemTitle=" + itemTitle;
        FormulaOneNewsData += ", itemDescription=" + itemDescription;
        FormulaOneNewsData += ", itemLink=" + itemLink + "]";
        return FormulaOneNewsData;
    }
}
