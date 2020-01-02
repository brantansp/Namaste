package com.namaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
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

        String [] result = dbHelper.getnsmcterm(getSearchText());
        textView = findViewById(R.id.display);
        SpannableString ss1=  new SpannableString("NAMC ID : ");
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        textView.append(ss1);
        textView.append(result[0]);
        textView.append("\n");
        ss1=  new SpannableString("NSMC Term : ");
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        textView.append(ss1);
        textView.append(result[1]);
        textView.append("\n");
        ss1=  new SpannableString("NSMC Code : ");
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        textView.append(ss1);
        textView.append(result[2]);
        textView.append("\n");
        ss1=  new SpannableString("TAMIL TERM : ");
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        textView.append(ss1);
        textView.append(result[3]);
        textView.append("\n");
        ss1=  new SpannableString("SHORT DEFINITION : ");
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        textView.append(ss1);
        textView.append(result[4]);
        textView.append("\n");
        ss1=  new SpannableString("LONG DEFINITION : ");
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        textView.append(ss1);
        textView.append(result[5]);
        textView.append("\n");
        ss1=  new SpannableString("REFERENCES : ");
        ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
        textView.append(ss1);
        textView.append(result[6]);
        textView.append("\n");
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
            Intent getSearchIntent = new Intent(this, TabbedSearch.class);
            startActivity(getSearchIntent);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
