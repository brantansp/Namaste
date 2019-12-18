package com.namaste;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.namaste.CopyDatabase;

import java.util.ArrayList ;


public class DbHelper extends CopyDatabase{


    String Table_Name = "maintable";
    String Column_Name = "NSMC_TERM";
    Context mcontext;


    public DbHelper(Context context, int version, String databaseName) {
        super(context, version, databaseName);
        mcontext = context;

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
    }

    public ArrayList<String> GetAllWordsnsmcterm(String query){

        ArrayList<String> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                Table_Name,
                new String[] {"NSMC_TERM"},
                "NSMC_TERM" + " LIKE ?",
                new String[] {query + "%"},
                null,null,null);

        int index = cursor.getColumnIndex("NSMC_TERM");

        while(cursor.moveToNext()){
            arrayList.add(cursor.getString(index));

        }

        return arrayList;

    }

    public ArrayList<String> GetAllWordsnsmccode(String query){

        ArrayList<String> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                Table_Name,
                new String[] {"NSMC_CODE"},
                "NSMC_CODE" + " LIKE ?",
                new String[] {query + "%"},
                null,null,null);

        int index = cursor.getColumnIndex("NSMC_CODE");

        while(cursor.moveToNext()){

            Log.e("CHECK",cursor.getString(index));
            arrayList.add(cursor.getString(index));

        }

        return arrayList;

    }

    public ArrayList<String> GetAllWordstamilterm(String query){

        ArrayList<String> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(
                Table_Name,
                new String[] {"TAMIL_TERM"},
                "TAMIL_TERM" + " LIKE ?",
                new String[] {query + "%"},
                null,null,null);

        int index = cursor.getColumnIndex("TAMIL_TERM");

        while(cursor.moveToNext()){
            arrayList.add(cursor.getString(index));

        }

        return arrayList;

    }

    public String getnsmcterm(String word){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String mean = null;

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + Table_Name + " where " + "NSMC_TERM" + "=  '"+word+"'",null);

        while(cursor.moveToNext()){
            mean = "NAMC ID : " +cursor.getString(cursor.getColumnIndex("NAMC_ID")) +"\n";
            mean = mean + "NSMC Term : " +cursor.getString(cursor.getColumnIndex("NSMC_TERM")) +"\n";
            mean = mean + "NSMC Code : "+ cursor.getString(cursor.getColumnIndex("NSMC_CODE")) +"\n";
            mean = mean + "Tamil Term : "+ cursor.getString(cursor.getColumnIndex("TAMIL_TERM")) +"\n";
            mean = mean + "Short Definition : "+ cursor.getString(cursor.getColumnIndex("SHORT_DEFINITION")) +"\n";
            mean = mean + "Long Definition : "+ cursor.getString(cursor.getColumnIndex("LONG_DEFINITION")) +"\n";
            mean = mean + "Reference : "+ cursor.getString(cursor.getColumnIndex("REFERENCE"));
        }
        return mean;
    }

    public String getnsmccode(String word){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String mean = null;

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + Table_Name + " where " + "NSMC_CODE" + "=  '"+word+"'",null);

        while(cursor.moveToNext()){
            mean = "NAMC ID : " +cursor.getString(cursor.getColumnIndex("NAMC_ID")) +"\n";
            mean = mean + "NSMC Term : " +cursor.getString(cursor.getColumnIndex("NSMC_TERM")) +"\n";
            mean = mean + "NSMC Code : "+ cursor.getString(cursor.getColumnIndex("NSMC_CODE")) +"\n";
            mean = mean + "Tamil Term : "+ cursor.getString(cursor.getColumnIndex("TAMIL_TERM")) +"\n";
            mean = mean + "Short Definition : "+ cursor.getString(cursor.getColumnIndex("SHORT_DEFINITION")) +"\n";
            mean = mean + "Long Definition : "+ cursor.getString(cursor.getColumnIndex("LONG_DEFINITION")) +"\n";
            mean = mean + "Reference : "+ cursor.getString(cursor.getColumnIndex("REFERENCE"));
        }
        return mean;
    }

    public String gettamilterm(String word){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String mean = null;

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + Table_Name + " where " + "TAMIL_TERM" + "=  '"+word+"'",null);

        while(cursor.moveToNext()){
            mean = "NAMC ID : " +cursor.getString(cursor.getColumnIndex("NAMC_ID")) +"\n";
            mean = mean + "NSMC Term : " +cursor.getString(cursor.getColumnIndex("NSMC_TERM")) +"\n";
            mean = mean + "NSMC Code : "+ cursor.getString(cursor.getColumnIndex("NSMC_CODE")) +"\n";
            mean = mean + "Tamil Term : "+ cursor.getString(cursor.getColumnIndex("TAMIL_TERM")) +"\n";
            mean = mean + "Short Definition : "+ cursor.getString(cursor.getColumnIndex("SHORT_DEFINITION")) +"\n";
            mean = mean + "Long Definition : "+ cursor.getString(cursor.getColumnIndex("LONG_DEFINITION")) +"\n";
            mean = mean + "Reference : "+ cursor.getString(cursor.getColumnIndex("REFERENCE"));
        }
        return mean;
    }

}
