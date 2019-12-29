package com.namaste;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
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

    public Cursor GetAllListViewItems(){

        ArrayList<String> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT DISTINCT rowid _id, NAMC_ID, NSMC_TERM FROM maintable ORDER by cast (nsmc_term as signed) asc",null);

        return cursor1;

    }

    public Cursor GetMatchingListViewItems(String word){

        ArrayList<String> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT DISTINCT rowid _id, NSMC_TERM FROM maintable where nsmc_term like '%"+word+"%'",null);

        return cursor1;

    }

    public ArrayList<String> GetAllWordsnsmcterm(String query){

        ArrayList<String> arrayList = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        /*Cursor cursor = sqLiteDatabase.query(
                Table_Name,
                new String[] {"NSMC_TERM","NSMC_CODE","TAMIL_TERM"},
                "NSMC_TERM" + " LIKE ?",
                new String[] {query + "%"},
                null,null,null);*/
        Cursor cursor1 = sqLiteDatabase.rawQuery("SELECT DISTINCT nsmc_term FROM maintable where nsmc_term like '%"+query+"%'",null);
        Cursor cursor2 = sqLiteDatabase.rawQuery("SELECT DISTINCT nsmc_code FROM maintable where nsmc_code like '%"+query+"%'",null);
        Cursor cursor3 = sqLiteDatabase.rawQuery("SELECT DISTINCT tamil_term FROM maintable where tamil_term like '%"+query+"%'",null);

        int index1 = cursor1.getColumnIndex("NSMC_TERM");
        int index2 = cursor2.getColumnIndex("NSMC_CODE");
        int index3 = cursor3.getColumnIndex("TAMIL_TERM");

        while(cursor1.moveToNext()){
            arrayList.add(cursor1.getString(index1));
            //arrayList.add(cursor2.getString(index2));
            //arrayList.add(cursor3.getString(index3));
        }

        while(cursor2.moveToNext()){
            //arrayList.add(cursor1.getString(index1));
            arrayList.add(cursor2.getString(index2));
            //arrayList.add(cursor3.getString(index3));
        }

        while(cursor3.moveToNext()){
            //arrayList.add(cursor1.getString(index1));
            //arrayList.add(cursor2.getString(index2));
            arrayList.add(cursor3.getString(index3));
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

    public String [] getnsmcterm(String word){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String [] details = new String [7];

        Cursor cursor = sqLiteDatabase.rawQuery("Select * from " + Table_Name + " where NSMC_TERM ='"+word+"' OR NSMC_CODE ='"+word+"' OR TAMIL_TERM ='"+word+"'", null);

        while(cursor.moveToNext()){
            details[0]=cursor.getString(cursor.getColumnIndex("NAMC_ID"));
            details[1]=cursor.getString(cursor.getColumnIndex("NSMC_TERM"));
            details[2]=cursor.getString(cursor.getColumnIndex("NSMC_CODE"));
            details[3]=cursor.getString(cursor.getColumnIndex("TAMIL_TERM"));
            details[4]=cursor.getString(cursor.getColumnIndex("SHORT_DEFINITION"));
            details[5]=cursor.getString(cursor.getColumnIndex("LONG_DEFINITION"));
            details[6]=cursor.getString(cursor.getColumnIndex("REFERENCE"));
        }
        return details;
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
