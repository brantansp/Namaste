package com.namaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {


    AutoCompleteTextView autoCompleteTextView;
    ArrayList<String> arrayList;
    DbHelper dbHelper;
    TextView textView;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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


        autoCompleteTextView = findViewById(R.id.nsmccode);
        clear = (Button) findViewById(R.id.clearSearch1);
        clear.setVisibility(View.INVISIBLE);

        arrayList = new ArrayList<>();

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() == 1){
                    arrayList.addAll(dbHelper.GetAllWordsnsmcterm(s.toString()));

                    autoCompleteTextView.setAdapter(new ArrayAdapter<String>(SearchActivity.this,
                            android.R.layout.simple_list_item_1,arrayList));
                    clear.setVisibility(View.VISIBLE);
                } else if (s.length() == 0) {
                    clear.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        textView = findViewById(R.id.displayresult);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String word = (String) parent.getItemAtPosition(position);

                String [] result = dbHelper.getnsmcterm(word);
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
                ss1=  new SpannableString("Tamil Term : ");
                ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
                textView.append(ss1);
                textView.append(result[3]);
                textView.append("\n");
                ss1=  new SpannableString("Short Definition : ");
                ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
                textView.append(ss1);
                textView.append(result[4]);
                textView.append("\n");
                ss1=  new SpannableString("Long Definition : ");
                ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
                textView.append(ss1);
                textView.append(result[5]);
                textView.append("\n");
                ss1=  new SpannableString("Reference : ");
                ss1.setSpan(new StyleSpan(Typeface.BOLD), 0, ss1.length(), 0);
                textView.append(ss1);
                textView.append(result[6]);
                textView.append("\n");

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        autoCompleteTextView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                clear.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                clear.setVisibility(View.GONE);
            }

        });
    }

    public void clear(View view) {
        autoCompleteTextView.setText("");
        textView.setText("");
        clear.setVisibility(View.GONE);
    }
}
