package com.e.costingapp;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper1 extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Project.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME= "proj1";
    private static final String COLUMN_ID= "_id";
    private static final String COLUMN_NAME= "product_name";
    private static final String COLUMN_PRICE= "product_price";
    private static final String COLUMN_DESC= "product_qtt";



    MyDatabaseHelper1(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_DESC + " TEXT);";
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addProject(String name, int price, String desc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_DESC, desc);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();

        }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        if(db!=null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String name_U, String cost_U, String description_U){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(COLUMN_NAME, name_U);
        cv.put(COLUMN_PRICE, cost_U);
        cv.put(COLUMN_DESC, description_U);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "failed to update", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully updated", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db =this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if( result == -1){
            Toast.makeText(context, "Error: Failed to Delete!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Successfully Deleted!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}

