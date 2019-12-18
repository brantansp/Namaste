package com.namaste;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyDatabase extends SQLiteOpenHelper {
    public static String PACKAGE_NAME;
    public static String DB_PATH;
    public static String DB_NAME;
    public Context mContext;
    private SQLiteDatabase mDatabase;
    private static final int DATABASE_VERSION = 1;

    public CopyDatabase(Context context, int version, String databaseName) {
        super(context, databaseName, (CursorFactory)null, DATABASE_VERSION);
        this.mContext = context;
        PACKAGE_NAME = this.getPackageName(context);
        DB_NAME = databaseName;
        DB_PATH = "/data/data/" + PACKAGE_NAME + "/databases/";
    }

    public void onCreate(SQLiteDatabase db) {
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public synchronized void close() {
        if (this.mDatabase != null && this.mDatabase.isOpen()) {
            this.mDatabase.close();
        }

        super.close();
    }

    public void createDatabase() throws Exception {
        boolean dbExists = this.checkDatabase();
        if (!dbExists) {

            SQLiteDatabase db = this.getReadableDatabase();
            if (db.isOpen()){
                db.close();
            }
            try {
                this.copyDatabase();
            } catch (Exception var3) {
                Log.e("DB_ERROR", "createDatabase(): Could not copy DB");
                throw new Error("Could not copy DB'");
            }
        }

    }

    private void copyDatabase() throws Exception {
        try {
            InputStream input = this.mContext.getAssets().open(DB_NAME);
            String outFileName = DB_PATH + DB_NAME;
            OutputStream output = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];

            int length;
            while((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }

            output.flush();
            input.close();
            output.close();
        } catch (IOException var6) {
            var6.printStackTrace();
            Log.e("DB_ERROR", "copyDatabase(): Could not copy DB");
        }

    }

    private boolean checkDatabase() {
        SQLiteDatabase checkDB = null;

        try {
            String path = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(path, (CursorFactory)null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLException var3) {
            var3.printStackTrace();
            Log.e("DB_ERROR", "checkDatabase(): Could not open DB");
        }

        if (checkDB != null) {
            checkDB.close();
        }

        return checkDB != null;
    }

    public void openDatabase() {
        String path = DB_PATH + DB_NAME;
        this.mDatabase = SQLiteDatabase.openDatabase(path, (CursorFactory)null, SQLiteDatabase.OPEN_READONLY);
    }

    public String getPackageName(Context context) {
        return context.getPackageName();
    }
}
