package com.example.owner.muccoursework;

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
import java.sql.SQLException;
import java.util.Locale;

/**
 * Created by owner on 11/12/2014.
 */
public class FormulaOneDatabaseManager extends SQLiteOpenHelper {

    //define database version, path, name, and fields
    //these must match the names in the database file itself
    //store application context in a context variable as well
    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/com.example.owner.muccoursework/databases/";
    private static final String DB_NAME = "formula1.s3db";
    private static final String TBL_DATABASEINFO = "Formula1";

    public static final String COL_DRIVERID = "DriverID";
    public static final String COL_DRIVERNAME = "DriverName";
    public static final String COL_DRIVERTEAM = "DriverTeam";
    public static final String COL_DRIVERIMAGE = "DriverImage";
    public static final String COL_AUSTRALIARESULT = "AustraliaResult";
    public static final String COL_MALAYSIARESULT = "MalaysiaResult";
    public static final String COL_BAHRAINRESULT = "BahrainResult";
    public static final String COL_CHINARESULT = "ChinaResult";
    public static final String COL_SPAINRESULT = "SpainResult";
    public static final String COL_MONACORESULT = "MonacoResult";
    public static final String COL_CANADARESULT = "CanadaResult";
    public static final String COL_AUSTRIARESULT = "AustriaResult";
    public static final String COL_GREATBRITAINRESULT = "GreatBritainResult";
    public static final String COL_GERMANYRESULT = "GermanyResult";
    public static final String COL_HUNGARYRESULT = "HungaryResult";
    public static final String COL_BELGIUMRESULT = "BelgiumResult";
    public static final String COL_ITALYRESULT = "ItalyResult";
    public static final String COL_SINGAPORERESULT = "SingaporeResult";
    public static final String COL_JAPANRESULT = "JapanResult";
    public static final String COL_RUSSIARESULT = "RussiaResult";
    public static final String COL_UNITEDSTATESRESULT = "UnitedStatesResult";
    public static final String COL_BRAZILRESULT = "BrazilResult";
    public static final String COL_ABUDHABIRESULT = "AbuDhabiResult";

    private final Context appContext;

    public FormulaOneDatabaseManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        //call constructor for superclass and assign the context variable
        super(context, name, factory, version);
        this.appContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //create SQL query string based on the database's fields and types of those fields
        //execute the query to ensure that the correct database can be found
        String CREATE_DATABASEINFO_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_DATABASEINFO + "(" + COL_DRIVERID + " INTEGER PRIMARY KEY," + COL_DRIVERNAME
                + " TEXT," + COL_DRIVERTEAM + " TEXT," + COL_DRIVERIMAGE + " TEXT,"
                + COL_AUSTRALIARESULT + " TEXT" + COL_MALAYSIARESULT + " TEXT" + COL_BAHRAINRESULT
                + " TEXT" + COL_CHINARESULT + " TEXT" + COL_SPAINRESULT + " TEXT" + COL_MONACORESULT
                + " TEXT" + COL_CANADARESULT + " TEXT" + COL_AUSTRIARESULT + " TEXT" + COL_GREATBRITAINRESULT
                + " TEXT" + COL_GERMANYRESULT + " TEXT" + COL_HUNGARYRESULT + " TEXT" + COL_BELGIUMRESULT
                + " TEXT" + COL_ITALYRESULT + " TEXT" + COL_SINGAPORERESULT + " TEXT" + COL_JAPANRESULT
                + " TEXT" + COL_RUSSIARESULT + " TEXT" + COL_UNITEDSTATESRESULT + " TEXT" + COL_BRAZILRESULT
                + " TEXT" + COL_ABUDHABIRESULT + " TEXT" + ")";
        db.execSQL(CREATE_DATABASEINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //if the database has been changed after making the initial call, upgrade it
        if (newVersion > oldVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_DATABASEINFO);
            onCreate(db);
        }
    }

    public void dbCreate() throws IOException{
        //on creation of the database, attempt to copy it from the assets folder to the app's proper location
        //create an empty database if the database cannot be found
        //copy the database's information from assets
        boolean dbExist = dbCheck();

        if(!dbExist){
            this.getReadableDatabase();
            try{
                copyDBFromAssets();
            } catch (IOException e){
                throw new Error("Error copying database");
            }
        }
    }

    private boolean dbCheck(){
        //attempt to open the database to ensure it exists
        //if a database is found and opened, close it to allow for further processing
        SQLiteDatabase db = null;

        try{
            String dbPath = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            db.setLocale(Locale.getDefault());
            db.setVersion(1);
        }catch(SQLiteException e){
            Log.e("SQLHelper", "Database not Found!");
        }
        if(db!=null){
            db.close();
        }
        return db != null ? true : false;
    }

    private void copyDBFromAssets() throws IOException{
        //provide newly created empty database with the database from the assets folder
        //stream the data as a byte array across input and output streams

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

    public void addDatabaseInfo (DatabaseInfo theDatabaseInfo){
        //populate a new database entry with the relevant data from each of the fields

        ContentValues values = new ContentValues();
        values.put(COL_DRIVERID, theDatabaseInfo.getDriverID());
        values.put(COL_DRIVERNAME, theDatabaseInfo.getDriverName());
        values.put(COL_DRIVERTEAM, theDatabaseInfo.getDriverTeam());
        values.put(COL_DRIVERIMAGE, theDatabaseInfo.getDriverImage());
        values.put(COL_AUSTRALIARESULT, theDatabaseInfo.getAustraliaResult());
        values.put(COL_MALAYSIARESULT, theDatabaseInfo.getMalaysiaResult());
        values.put(COL_BAHRAINRESULT, theDatabaseInfo.getBahrainResult());
        values.put(COL_CHINARESULT, theDatabaseInfo.getChinaResult());
        values.put(COL_SPAINRESULT, theDatabaseInfo.getSpainResult());
        values.put(COL_MONACORESULT, theDatabaseInfo.getMonacoResult());
        values.put(COL_CANADARESULT, theDatabaseInfo.getCanadaResult());
        values.put(COL_GREATBRITAINRESULT, theDatabaseInfo.getGreatbritainResult());
        values.put(COL_GERMANYRESULT, theDatabaseInfo.getGermanyResult());
        values.put(COL_HUNGARYRESULT, theDatabaseInfo.getHungaryResult());
        values.put(COL_BELGIUMRESULT, theDatabaseInfo.getBelgiumResult());
        values.put(COL_ITALYRESULT, theDatabaseInfo.getItalyResult());
        values.put(COL_SINGAPORERESULT, theDatabaseInfo.getSingaporeResult());
        values.put(COL_JAPANRESULT, theDatabaseInfo.getJapanResult());
        values.put(COL_RUSSIARESULT, theDatabaseInfo.getRussiaResult());
        values.put(COL_UNITEDSTATESRESULT, theDatabaseInfo.getUnitedstatesResult());
        values.put(COL_BRAZILRESULT, theDatabaseInfo.getBrazilResult());
        values.put(COL_ABUDHABIRESULT, theDatabaseInfo.getAbudhabiResult());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TBL_DATABASEINFO, null, values);
        db.close();
    }

    public DatabaseInfo findDriverData (String thisDriverData) {
        //find the user's particular driver data
        //create a query string based on the driver's name
        //open the database and carry out the query using a cursor to move through the database's records to find the matching information
        //store the data in an object of type DatabaseInfo and return it to the main activity
        String query = "Select * FROM " + TBL_DATABASEINFO + " WHERE " + COL_DRIVERNAME + " = \"" + thisDriverData + "\"";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        DatabaseInfo databaseInfo = new DatabaseInfo();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            databaseInfo.setDriverID(Integer.parseInt(cursor.getString(0)));
            databaseInfo.setDriverName(cursor.getString(1));
            databaseInfo.setDriverTeam(cursor.getString(2));
            databaseInfo.setDriverImage(cursor.getString(3));
            databaseInfo.setAustraliaResult(cursor.getString(4));
            databaseInfo.setMalaysiaResult(cursor.getString(5));
            databaseInfo.setBahrainResult(cursor.getString(6));
            databaseInfo.setChinaResult(cursor.getString(7));
            databaseInfo.setSpainResult(cursor.getString(8));
            databaseInfo.setMonacoResult(cursor.getString(9));
            databaseInfo.setCanadaResult(cursor.getString(10));
            databaseInfo.setAustriaResult(cursor.getString(11));
            databaseInfo.setGreatbritainResult(cursor.getString(12));
            databaseInfo.setGermanyResult(cursor.getString(13));
            databaseInfo.setHungaryResult(cursor.getString(14));
            databaseInfo.setBelgiumResult(cursor.getString(15));
            databaseInfo.setItalyResult(cursor.getString(16));
            databaseInfo.setSingaporeResult(cursor.getString(17));
            databaseInfo.setJapanResult(cursor.getString(18));
            databaseInfo.setRussiaResult(cursor.getString(19));
            databaseInfo.setUnitedstatesResult(cursor.getString(20));
            databaseInfo.setBrazilResult(cursor.getString(21));
            databaseInfo.setAbudhabiResult(cursor.getString(22));
            cursor.close();

        } else {
            databaseInfo = null;
        }
        db.close();
        return databaseInfo;
    }

    public boolean removeDriverData(String thisDriverData){
        //similar to above, but used to delete a driver

        boolean result = false;

        String query = "Select * FROM "  + TBL_DATABASEINFO + " WHERE " + COL_DRIVERNAME + " = \"" + thisDriverData + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        DatabaseInfo databaseInfo = new DatabaseInfo();

        if(cursor.moveToFirst()){
            databaseInfo.setDriverID(Integer.parseInt(cursor.getString(0)));
            db.delete(TBL_DATABASEINFO, COL_DRIVERID + " = ?",
                    new String[] {String.valueOf(databaseInfo.getDriverID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
