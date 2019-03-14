package com.example.assignment4_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "people_detail";
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";
    private static final String COL3 = "EMAIL";
    private static final String COL4 = "TV";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

//        db.execSQL("DROP TABLE "+ TABLE_NAME);
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL1 + " TEXT ," + COL2+ " TEXT ," + COL3+ " TEXT ," + COL4+ " TEXT , PRIMARY KEY(ID, NAME));";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
    }

    public boolean addData(String name, String email, String tv, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        if (id.isEmpty())
            contentValues.put(COL1,"1");
        else
            contentValues.put(COL1, id);
        contentValues.put(COL2, name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, tv);
        Log.d(TAG, "addData: Adding "+name+ " to "+ TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getData(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +COL2+ " = ? ";
        Cursor data = db.rawQuery(query, new String[]{name});
        return data;
    }
    public Cursor getData(String name, String ID){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " +COL2+ " = ? AND " +COL1+ " =? ";
        Cursor data = db.rawQuery(query, new String[]{name, ID});
        return data;
    }

    public boolean updateData(String name, String email, String tv, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL3, email);
        contentValues.put(COL4, tv);
        Log.d(TAG, "addData: Adding "+name+ " to "+ TABLE_NAME);

        String strFilter = COL2 + " = ? AND " +COL1+ " = ? ";

        long result = db.update(TABLE_NAME,contentValues,strFilter,new String[]{name, id});


//        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean deleteData(String name, String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String strFilter = COL2 + " = ? AND " +COL1+ " = ? ";

        db.delete(TABLE_NAME,strFilter,new String[]{name, id});
        return true;
    }
}
