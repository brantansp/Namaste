package com.namaste;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    AutoCompleteTextView autoCompleteTextView2;
    AutoCompleteTextView autoCompleteTextView3;
    ArrayList<String> arrayList;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;
    DbHelper dbHelper;
    TextView textView;
    Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        dbHelper = new DbHelper(this,2, "namaste.db");
        try{
            dbHelper.openDatabase();

        }catch (Exception e){}
        try {
            dbHelper.createDatabase();
        }
        catch (Exception e){}


        autoCompleteTextView = findViewById(R.id.nsmccode);
        clear = (Button) findViewById(R.id.clear);
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

                textView.setText(dbHelper.getnsmcterm(word));

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
