package com.namaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class ViewResult extends AppCompatActivity {

    TextView textView;
    DbHelper dbHelper;
    private static String searchTerm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DbHelper(this,2, "namaste.db");
        try{
            dbHelper.openDatabase();

        }catch (Exception e){}
        try {
            dbHelper.createDatabase();
        }
        catch (Exception e){}

        textView = findViewById(R.id.display);
        textView.setText(dbHelper.getnsmcterm(getSearchText()));
    }

    public String getSearchText () {
        return searchTerm;
    }

    public void setSearchText(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            Intent getSearchIntent = new Intent(this, AndroidSQLite.class);
            startActivity(getSearchIntent);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
