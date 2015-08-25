package com.abdroid.wps2;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class DatabaseHandler extends SQLiteOpenHelper {

    SQLiteDatabase db = getWritableDatabase();

    DatabaseHandler obj;

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 3;

    // Database Name
    public static final String DATABASE_NAME = "WPSDB.db";

    // table name
    public static final String TABLE_NAME = "details";

    // Table Columns names
    public static final String mac = "mac";
    public static final String lat = "lat";
    public static final String longt = "long";
    public static final String key_id = "_id";


    public DatabaseHandler(Context context,String name,SQLiteDatabase.CursorFactory factory,int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + key_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + mac + " TEXT," + lat + " TEXT,"
                + longt + " TEXT" + ");";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create tables again
        onCreate(db);
    }



    void addap(Apinfo ai) {
        //Add access point

        ContentValues values = new ContentValues();
        SQLiteDatabase db = getWritableDatabase();
        values.put(mac, ai.getMac());
        values.put(lat, ai.getLat());
        values.put(longt, ai.getLongt());


        // Inserting Row
        db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection
    }


    public Cursor getAllRows() {
        //  String where = null;
        String ALL_KEYS[]=new String[]{key_id,mac,lat,longt};
        Cursor c=db.query(TABLE_NAME,null,null,null,null,null,key_id+" DESC");   //Recent to old order
       if(c!=null)
        {
            c.moveToFirst();
        }

        return c;

    }

}