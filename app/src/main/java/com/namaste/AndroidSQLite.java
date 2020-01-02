package com.namaste;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class AndroidSQLite extends AppCompatActivity {

    private SQLiteAdapter mySQLiteAdapter;

    AutoCompleteTextView autoCompleteTextView2;
    ArrayList<String> arrayList;
    DbHelper dbHelper;
    TextView textView;
    Button clear;
    ArrayList results;
    AdapterView prestListView;
    ListView listContent;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DbHelper(this, 1, "namaste.db");
        try {
            dbHelper.openDatabase();

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            dbHelper.createDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }


        autoCompleteTextView2 = findViewById(R.id.autoCompleteTextView2);
        clear = (Button) findViewById(R.id.clearSearch1);
        clear.setVisibility(View.INVISIBLE);

        listContent = (ListView) findViewById(R.id.contentlist);

        populateAllItemsInListView();

        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int position, long l) {
                Cursor word = (Cursor) av.getItemAtPosition(position);

                ViewResult vr = new ViewResult();

                vr.setSearchText(word.getString(2));

                Intent getSearchIntent = new Intent(getApplicationContext(), ViewResult.class);
                startActivity(getSearchIntent);
            }
        });

        arrayList = new ArrayList<>();

        autoCompleteTextView2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                arrayList.removeAll(arrayList);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    /*Display In AutoCompleteTextView*/
                    /*arrayList.addAll(dbHelper.GetAllWordsnsmcterm(s.toString()));
                    autoCompleteTextView2.setAdapter(new ArrayAdapter<String>(AndroidSQLite.this,
                            android.R.layout.simple_list_item_1, arrayList));*/

                    Cursor cursor = dbHelper.GetMatchingListViewItems(s.toString(),"");
                    startManagingCursor(cursor);
                    String[] from2 = new String[]{"NSMC_TERM"};
                    int[] to2 = new int[]{R.id.text2};
                    SimpleCursorAdapter cursorAdapter2 =
                            new SimpleCursorAdapter(AndroidSQLite.this, R.layout.row, cursor, from2, to2);

                    listContent.setAdapter(cursorAdapter2);

                    listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> av, View view, int position, long l) {
                            Cursor word = (Cursor) av.getItemAtPosition(position);

                            ViewResult vr = new ViewResult();

                            vr.setSearchText(word.getString(1));

                            Intent getSearchIntent = new Intent(getApplicationContext(), ViewResult.class);
                            startActivity(getSearchIntent);
                        }
                    });

                    clear.setVisibility(View.VISIBLE);
                } else if (s.length() == 0) {
                    arrayList.removeAll(arrayList);
                    clear.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        textView = findViewById(R.id.display);

        /* Displaying information about clicked item in AutoCompleteListView*/
        /*autoCompleteTextView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String word = (String) parent.getItemAtPosition(position);

                //textView.setText(dbHelper.getnsmcterm(word));
                ViewResult vr = new ViewResult();
                vr.setSearchText(word);

                Intent getSearchIntent = new Intent(getApplicationContext(), ViewResult.class);
                startActivity(getSearchIntent);

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });*/

        autoCompleteTextView2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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

    private void populateAllItemsInListView() {
        Cursor cursor = dbHelper.GetAllListViewItems();
        startManagingCursor(cursor);

        String[] from = new String[]{"NAMC_ID", "NSMC_TERM"};
        int[] to = new int[]{R.id.text};

        SimpleCursorAdapter cursorAdapter =
                new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);

        String[] from1 = new String[]{"NSMC_TERM"};
        int[] to1 = new int[]{R.id.text2};

        SimpleCursorAdapter cursorAdapter2 =
                new SimpleCursorAdapter(this, R.layout.row, cursor, from1, to1);


        listContent.setAdapter(cursorAdapter2);
        //listContent.setAdapter(new MySimpleArrayAdapter(this, from));
    }

    public void clear(View view) {
        autoCompleteTextView2.setText("");
        listContent = (ListView) findViewById(R.id.contentlist);
        populateAllItemsInListView();
        //textView.setText("");
        clear.setVisibility(View.GONE);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent getSearchIntent = new Intent(this, MainActivity.class);
            startActivity(getSearchIntent);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}