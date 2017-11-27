package com.example.burke.medicalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Juwan on 11/3/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "medicine.db";
    public static final String TABLE_NAME = "medicine_table";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "NAME";
    public static final String COL_DOSAGE = "DOSAGE";
    public static final String COL_AMTBTL = "AMOUNT";
    public static final String COL_NUMREFILLS = "REFILLS";
    //public static final String COL_TIMESADAY = "TIMES";
    public static final String COL_ALARM = "ALARM";
    public static final String COL_DAY = "DAYS";

    public SQLiteHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (_id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,DOSAGE TEXT, AMOUNT TEXT,REFILLS INTEGER, ALARM TEXT, DAYS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String dosage,String amtbtl,String numrefills,String alarm,String day) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_DOSAGE,dosage);
        contentValues.put(COL_AMTBTL,amtbtl);
        contentValues.put(COL_NUMREFILLS,numrefills);
        contentValues.put(COL_ALARM,alarm);
        contentValues.put(COL_DAY,day);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getDataById(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where _id = "+id,null);
        return res;
    }

    public Cursor getDataByTime(String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" time = "+time,null);
        return res;
    }

    public boolean updateData(String id,String name,String dosage,String amtbtl,String numrefills,String alarm,String day) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_ID,id);
        contentValues.put(COL_NAME,name);
        contentValues.put(COL_DOSAGE,dosage);
        contentValues.put(COL_AMTBTL,amtbtl);
        contentValues.put(COL_NUMREFILLS,numrefills);
        //contentValues.put(COL_TIMESADAY,timesaday);
        contentValues.put(COL_ALARM,alarm);
        contentValues.put(COL_DAY,day);
        int result = db.update(TABLE_NAME, contentValues, "_id = ?",new String[] { id });
        if (result == 0) {
            return false;
        } else {
            return true;
        }
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "_id = ?",new String[] {id});
    }
}
