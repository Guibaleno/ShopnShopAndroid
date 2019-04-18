package com.example.a533.shopnshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class SQLite extends SQLiteOpenHelper {
    public static final String DATABASENAME = "ShopnShopAndroid.db";

    public static final String TBLUSERS = "tblUsers";
    public static final String IDUSER = "IDUser";
    public static final String USERNAME = "Username";
    public static final String PASSWORD = "Password";

    public static final String TBLORDERS = "tblOrders";
    public static final String IDORDER = "IDorder";
    public static final String ORDERNAME = "orderName";
    public static final String COMPLETED = "completed";

    public static final String TBLOBJECTS = "tblObjects";
    public static final String IDOBJECT = "IDObjet";
    public static final String OBJECTNAME = "objectName";
    public static final String QUANTITYLEFT = "quantityLeft";

    public static final String TBLOBJECT_ORDER = "tblObject_Order";


    public SQLite(Context context) {
        super(context, DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TBLUSERS + "("+ IDUSER + " INTEGER PRIMARY KEY," + USERNAME + " TEXT NOT NULL UNIQUE, " + PASSWORD  +" TEXT NOT NULL);");
        db.execSQL("CREATE TABLE " + TBLORDERS + "("+ IDORDER + " INTEGER PRIMARY KEY," + ORDERNAME + " TEXT NOT NULL , " + COMPLETED  +" INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE " + TBLOBJECTS + "("+ IDOBJECT + " INTEGER PRIMARY KEY," + OBJECTNAME + " TEXT NOT NULL ," + QUANTITYLEFT + " INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE " + TBLOBJECT_ORDER + "("+ IDORDER + " INTEGER NOT NULL," + IDOBJECT + " INTEGER NOT NULL, " +
                   "CONSTRAINT FK_IDorder FOREIGN KEY("+ IDORDER + ")" + "REFERENCES " + TBLORDERS + "(" + IDORDER + "), " +
                   "CONSTRAINT FK_IDOBJET FOREIGN KEY(" + IDOBJECT + ")" + "REFERENCES " + TBLOBJECTS + "(" + IDOBJECT + "))");
        CreateItems();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBLUSERS);
        db.execSQL("DROP TABLE IF EXISTS " + TBLOBJECT_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TBLORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TBLOBJECTS);
        onCreate(db);
    }

    public boolean InsertUser(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        long result = db.insert(TBLUSERS, null, contentValues);
        return result != -1;
    }
    public boolean InsertOrder(String orderName, boolean completed)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ORDERNAME, orderName);
        contentValues.put(PASSWORD, (completed) ? 1 : 0);
        long result = db.insert(TBLORDERS, null, contentValues);
        return result != -1;
    }
    public void InsertObject(String objectName, int quantityLeft)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(OBJECTNAME, objectName);
        contentValues.put(QUANTITYLEFT, quantityLeft);
        db.insert(TBLOBJECTS, null, contentValues);
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TBLUSERS,null);
        return res;
    }

    public boolean verifyUsername(String Username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TBLUSERS + " WHERE Username = \"" + Username + "\"",null);
        return res.getCount() > 0 ;
    }

    public boolean VerifyCredentials(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor user = db.rawQuery("SELECT * FROM " + TBLUSERS + " WHERE Username = \"" + username + "\" AND Password = \"" + password + "\"", null);
        return user.getCount() > 0;
    }

    public boolean verifyLoginUsername(String Username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TBLUSERS + " WHERE Username = \"" + Username + "\"",null);
        return res.getCount() > 0 ;
    }

    private void CreateItems()
    {
        InsertObject("Potatoes", 10);
        InsertObject("Ketchup bottles", 18);
        InsertObject("Ceasar salad", 12);
        InsertObject("Garlic bread", 50);
    }

    public Cursor GetItemsToSell()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor items = db.rawQuery("SELECT " + OBJECTNAME + ", " + QUANTITYLEFT + " FROM " + TBLOBJECTS , null);
        return items;
    }

}
