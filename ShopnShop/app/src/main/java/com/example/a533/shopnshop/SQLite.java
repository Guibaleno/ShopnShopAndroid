package com.example.a533.shopnshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


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
    public static final String DB_TABLE = "table_image";
    public static final String KEY_NAME = "image_name";
    public static final String KEY_IMAGE = "image_data";
    public static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + DB_TABLE + "("+
            KEY_NAME + " TEXT," +
            KEY_IMAGE + " BLOB);";


    public SQLite(Context context) {
        super(context, DATABASENAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TBLUSERS + "("+ IDUSER + " INTEGER PRIMARY KEY," + USERNAME + " TEXT NOT NULL UNIQUE, " + PASSWORD  +" TEXT NOT NULL);");
        db.execSQL("CREATE TABLE " + TBLORDERS + "("+ IDORDER + " INTEGER PRIMARY KEY," + ORDERNAME + " TEXT NOT NULL , " + COMPLETED  +" INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE " + TBLOBJECTS + "("+ IDOBJECT + " INTEGER PRIMARY KEY," + OBJECTNAME + " TEXT NOT NULL ," + QUANTITYLEFT + " INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE " + TBLOBJECT_ORDER + "("+ IDORDER + " INTEGER NOT NULL," + IDOBJECT + " INTEGER NOT NULL, " +
                   "CONSTRAINT FK_IDorder FOREIGN KEY("+ IDORDER + ")" + "REFERENCES " + TBLORDERS + "(" + IDORDER + "), " +
                   "CONSTRAINT FK_IDOBJET FOREIGN KEY(" + IDOBJECT + ")" + "REFERENCES " + TBLOBJECTS + "(" + IDOBJECT + "))");
        db.execSQL(CREATE_TABLE_IMAGE);
        //CreateItems();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBLUSERS);
        db.execSQL("DROP TABLE IF EXISTS " + TBLOBJECT_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TBLORDERS);
        db.execSQL("DROP TABLE IF EXISTS " + TBLOBJECTS);
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(db);
        CreateItems();
    }

    public void InsertUser(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME, username);
        contentValues.put(PASSWORD, password);
        db.insert(TBLUSERS, null, contentValues);
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
        contentValues.put(QUANTITYLEFT, String.valueOf(quantityLeft));
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

    public void CreateItems()
    {
        InsertObject("Potatoes", 10);
        InsertObject("Ketchup bottles", 18);
        InsertObject("Ceasar salad", 12);
        InsertObject("Garlic bread", 50);
    }

    public List<ItemToSell> getItemsToSell()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor items = db.rawQuery("SELECT " + IDOBJECT + "," + OBJECTNAME + ", " + QUANTITYLEFT + " FROM " + TBLOBJECTS + " ORDER BY " + OBJECTNAME , null);
        List<ItemToSell> itemsToSell = new ArrayList<>();
        if (items.getCount() != 0)
        {
            while (items.moveToNext())
            {
                itemsToSell.add(new ItemToSell(items.getString(1),items.getString(2), items.getString(0)));
            }
        }
        return itemsToSell;
    }

    public int getQuantity(int position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor items = db.rawQuery("SELECT " + QUANTITYLEFT + " FROM " + TBLOBJECTS + " WHERE " + IDOBJECT + " = " + String.valueOf(position) , null);
        return items.getInt(position);
    }

    public String getObject(int position)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor items = db.rawQuery("SELECT " + OBJECTNAME + " FROM " + TBLOBJECTS + " WHERE " + IDOBJECT + " = " + String.valueOf(position) , null);
        items.moveToFirst();
        return items.getString(0);
    }
    public void InsertPhoto(String name, String bitmap)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, name);
        contentValues.put(KEY_IMAGE, bitmap);
        db.insert(DB_TABLE, null, contentValues);
       // Log.d("work","Inseryt");
    }
    public Cursor getPhoto(String NomPhoto){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor items = db.rawQuery("SELECT " + KEY_IMAGE + " FROM " + DB_TABLE + " WHERE image_name = \"" + NomPhoto + "\"",null);
        return items ;
    }

    public Cursor getIdUser(String USer){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT " + USERNAME + " FROM " + TBLUSERS + " WHERE Username = \"" + USer + "\"",null);
        return res;
    }

}
