package com.example.owner.muccoursework;

/**
 * Created by owner on 19/12/2014.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapDatabaseManager extends SQLiteOpenHelper{
    //define the database's version, path, name and each of the fields, making sure they match those in the circuit database file
    //store the app's context in a context variable
    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/com.example.owner.muccoursework/databases";
    private static final String DB_NAME = "circuitsMap.s3db";
    private static final String TBL_CIRCUITSINFO = "circuitsInfo";

    public static final String COL_ENTRYID = "entryID";
    public static final String COL_COUNTRYNAME = "countryName";
    public static final String COL_CIRCUITNAME = "circuitName";
    public static final String COL_LATITUDE = "Latitude";
    public static final String COL_LONGITUDE = "Longitude";

    private final Context appContext;

    public MapDatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        //call the constructor for the superclass and assign the context variable the application's context
        super(context, name, factory, version);
        this.appContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //create an SQL query string based on the database's fields and types
        //execute the query to ensure that the correct database can be found
        String CREATE_CIRCUITSINFO_TABLE = "CREATE TABLE IF NOT EXISTS " + TBL_CIRCUITSINFO + "("
                + COL_ENTRYID + " INTEGER PRIMARY KEY," + COL_COUNTRYNAME
                + " TEXT," + COL_CIRCUITNAME + " TEXT," + COL_LATITUDE
                + " FLOAT" + COL_LONGITUDE + " FLOAT" + ")";
        db.execSQL(CREATE_CIRCUITSINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //upgrade the database if it has been changed since making the initial call
        if (newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_CIRCUITSINFO);
            onCreate(db);
        }
    }

    public void dbCreate() throws IOException {
        //copy the database from the assets folder to the proper location
        //create an empty database in the path if the database is not found
        //the copyDBFromAssets method is called to copy the database information
        boolean dbExist = dbCheck();
        if (!dbExist){
            this.getReadableDatabase();
            try{
                copyDBFromAssets();
            } catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean dbCheck(){
        //ensure the database exists by trying to open it
        //if a database is opened, close it to allow for further processing
        SQLiteDatabase db = null;
        try{
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READONLY);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);
        } catch(SQLiteException e){
            Log.e("SQLHelper","Database not Found!");
        }
        if(db != null){
            db.close();
        }
        return db != null ? true : false;
    }

    private void copyDBFromAssets() throws IOException{
        //provide the newly created empty database with the database from the assets folder in order to handle data
        //to do this, stream the data across input and output streams as a byte array
        InputStream dbInput = null;
        OutputStream dbOutput = null;
        String dbFileName = DB_PATH + DB_NAME;
        try{
            dbInput = appContext.getAssets().open(DB_NAME);
            dbOutput = new FileOutputStream(dbFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0){
                dbOutput.write(buffer, 0, length);
            }
            dbOutput.flush();
            dbOutput.close();
            dbInput.close();
        } catch (IOException e)
        {
            throw new Error("Problems copying DB!");
        }
    }

    public void addaCircuitEntry(MapData aCircuit){
        //populate a new database entry with the relevant data from each of the fields
        ContentValues values = new ContentValues();
        values.put(COL_COUNTRYNAME, aCircuit.getCountryName());
        values.put(COL_CIRCUITNAME, aCircuit.getCircuitName());
        values.put(COL_LATITUDE, aCircuit.getLatitude());
        values.put(COL_LONGITUDE, aCircuit.getLongitude());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_CIRCUITSINFO, null, values);
        db.close();
    }

    public MapData getCircuitEntry(String aCircuitEntry){
        //find each circuit's specific data
        //create a query string based on the string of the circuit's country
        //open the database and carry out the query, using a cursor to move through the records to find the matching circuit
        //store the data in an object of type MapData
        String query = "Select * FROM " + TBL_CIRCUITSINFO + " WHERE " + COL_COUNTRYNAME + " =\"" + aCircuitEntry + "\"";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        MapData mapEntry = new MapData();

        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            mapEntry.setEntryID(Integer.parseInt(cursor.getString(0)));
            mapEntry.setCountryName(cursor.getString(1));
            mapEntry.setCircuitName(cursor.getString(2));
            mapEntry.setLatitude(Float.parseFloat(cursor.getString(3)));
            mapEntry.setLongitude(Float.parseFloat(cursor.getString(4)));
            cursor.close();
        } else {
            mapEntry = null;
        }
        db.close();
        return mapEntry;
    }

    public boolean removeCircuitEntry(String aCircuitEntry){
        //use the same methodology if we were ever to remove a circuit from the database
        boolean result = false;
        String query = "Select * FROM " + TBL_CIRCUITSINFO + " WHERE " + COL_COUNTRYNAME + " =\"" + aCircuitEntry + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        MapData mapEntry = new MapData();

        if(cursor.moveToFirst()){
            mapEntry.setEntryID(Integer.parseInt(cursor.getString(0)));
            db.delete(TBL_CIRCUITSINFO, COL_ENTRYID + " = ?",
                    new String[] { String.valueOf(mapEntry.getEntryID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public List<MapData> allMapData()
    {
        //move the cursor through each MapData item in the list to populate the database in the same way
        //this is included as the map markers aren't dependent on user input, and all show up on the map
        String query = "Select * FROM " + TBL_CIRCUITSINFO;
        List<MapData> mapDataList = new ArrayList<MapData>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            while (cursor.isAfterLast()==false){
                MapData currentEntry = new MapData();
                currentEntry.setEntryID(Integer.parseInt(cursor.getString(0)));
                currentEntry.setCountryName(cursor.getString(1));
                currentEntry.setCircuitName(cursor.getString(2));
                currentEntry.setLatitude(Float.parseFloat(cursor.getString(3)));
                currentEntry.setLongitude(Float.parseFloat(cursor.getString(4)));
                mapDataList.add(currentEntry);
                cursor.moveToNext();
            }

        } else {
            mapDataList.add(null);
        }
        db.close();
        return mapDataList;
    }
}
