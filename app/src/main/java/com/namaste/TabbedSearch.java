package com.namaste;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TabbedSearch<searchCode> extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    Button searchTerm;
    Button searchCode;
    Button searchCdsm;
    Button clearSearch1;
    Button clearSearch2;
    String enabledSearch ="term";
    TextView textView;
    ArrayList<String> arrayList1;
    ArrayList<String> arrayList2;
    Drawable d;
    TextView tv;
    DbHelper dbHelper;
    ListView listContent;
    AutoCompleteTextView autoCompleteSearch1;
    AutoCompleteTextView autoCompleteSearch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_search);
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

        clearSearch1 = (Button) findViewById(R.id.clearSearch1);
        clearSearch1.setVisibility(View.INVISIBLE);

        clearSearch2 = (Button) findViewById(R.id.clearSearch2);
        clearSearch2.setVisibility(View.INVISIBLE);

        autoCompleteSearch1 = findViewById(R.id.autoCompleteSearch1);
        autoCompleteSearch2 = findViewById(R.id.autoCompleteSearch2);

        autoCompleteSearch1.setOnFocusChangeListener(this);
        autoCompleteSearch2.setOnFocusChangeListener(this);

        listContent = (ListView) findViewById(R.id.contentlist);

        //to populate the tamil terms by default
        populateAllItemsInListView("TAMIL_TERM");
        displayInfoOfClickedItem();


        searchTerm = (Button) findViewById(R.id.searchTerm);
        searchTerm.setOnClickListener(this);

        searchCode = (Button) findViewById(R.id.searchCode);
        searchCode.setOnClickListener(this);

        searchCdsm = (Button) findViewById(R.id.searchCdsm);
        searchCdsm.setOnClickListener(this);

        d = searchTerm.getBackground();
        searchTerm.setBackgroundColor(Color.WHITE);

        arrayList1 = new ArrayList<>();

        autoCompleteSearch1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                arrayList1.removeAll(arrayList1);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                {
                    if (s.length() > 0) {
                                
                        if(enabledSearch.equals("term")) {
                            Cursor cursor = dbHelper.GetMatchingListViewItems(s.toString(),"tamilterm");
                            startManagingCursor(cursor);
                            SimpleCursorAdapter cursorAdapter = null;

                            String[] from2 = new String[]{"TAMIL_TERM"};
                            int[] to2 = new int[]{R.id.text2};
                            cursorAdapter =new SimpleCursorAdapter(TabbedSearch.this, R.layout.row, cursor, from2, to2);

                            listContent.setAdapter(cursorAdapter);

                            listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> av, View view, int position, long l) {
                                    Cursor word = (Cursor) av.getItemAtPosition(position);

                                    ViewResult vr = new ViewResult();

                                    vr.setSearchText(word.getString(1));

                                    Intent getSearchIntent = new Intent(getApplicationContext(), ViewResult.class);
                                    startActivity(getSearchIntent);
                                }
                            });

                            clearSearch1.setVisibility(View.VISIBLE);
                        } else if (enabledSearch.equals("code")) {
                            Cursor cursor = dbHelper.GetMatchingListViewItems(s.toString(),"nsmccode");
                            startManagingCursor(cursor);
                            SimpleCursorAdapter cursorAdapter = null;

                            String[] from2 = new String[]{"NSMC_CODE"};
                            int[] to2 = new int[]{R.id.text2};
                            cursorAdapter =new SimpleCursorAdapter(TabbedSearch.this, R.layout.row, cursor, from2, to2);

                            listContent.setAdapter(cursorAdapter);

                            listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> av, View view, int position, long l) {
                                    Cursor word = (Cursor) av.getItemAtPosition(position);

                                    ViewResult vr = new ViewResult();

                                    vr.setSearchText(word.getString(1));

                                    Intent getSearchIntent = new Intent(getApplicationContext(), ViewResult.class);
                                    startActivity(getSearchIntent);
                                }
                            });

                            clearSearch1.setVisibility(View.VISIBLE);
                        } else if (enabledSearch.equals("cdsm")) {
                            Cursor cursor = dbHelper.GetMatchingListViewItems(s.toString(),"definition");
                            startManagingCursor(cursor);
                            SimpleCursorAdapter cursorAdapter = null;

                            String[] from2 = new String[]{"SHORT_DEFINITION","LONG_DEFINITION"};
                            int[] to2 = new int[]{R.id.text2};
                            cursorAdapter =new SimpleCursorAdapter(TabbedSearch.this, R.layout.row, cursor, from2, to2);

                            listContent.setAdapter(cursorAdapter);

                            listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> av, View view, int position, long l) {
                                    Cursor word = (Cursor) av.getItemAtPosition(position);

                                    ViewResult vr = new ViewResult();

                                    vr.setSearchText(word.getString(1));

                                    Intent getSearchIntent = new Intent(getApplicationContext(), ViewResult.class);
                                    startActivity(getSearchIntent);
                                }
                            });

                            clearSearch1.setVisibility(View.VISIBLE);
                        }
                    } else if (s.length() == 0) {
                        arrayList1.removeAll(arrayList1);
                        clearSearch1.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textView = findViewById(R.id.display);

        /* Displaying information about clicked item in AutoCompleteListView*/
        autoCompleteSearch1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        });

        arrayList2 = new ArrayList<>();

        autoCompleteSearch2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                arrayList2.removeAll(arrayList2);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                {
                    if (s.length() > 0) {
                        Cursor cursor = dbHelper.GetMatchingListViewItems(s.toString(),"englishterm");
                        startManagingCursor(cursor);
                        String[] from2 = new String[]{"NSMC_TERM"};
                        int[] to2 = new int[]{R.id.text2};
                        SimpleCursorAdapter cursorAdapter2 =
                                new SimpleCursorAdapter(TabbedSearch.this, R.layout.row, cursor, from2, to2);

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

                        clearSearch2.setVisibility(View.VISIBLE);
                    } else if (s.length() == 0) {
                        arrayList2.removeAll(arrayList2);
                        clearSearch2.setVisibility(View.GONE);
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        textView = findViewById(R.id.display);

        /* Displaying information about clicked item in AutoCompleteListView*/
        autoCompleteSearch2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        });

    }

    @Override
    public void onClick(View v) {
         Log.d("namaste","clickID:"+v.getId());
         switch(v.getId()) {

             case R.id.searchTerm:
                 enabledSearch = "term";
                 searchTerm.setBackgroundColor(Color.WHITE);
                 searchCode.setBackgroundDrawable(d);
                 searchCdsm.setBackgroundDrawable(d);
                 autoCompleteSearch1.setVisibility(View.VISIBLE);
                 autoCompleteSearch2.setVisibility(View.VISIBLE);

                 autoCompleteSearch1.setHint("தமிழ் சொற்களை உள்ளிடவும்");
                 autoCompleteSearch2.setHint("Search by English");

                 populateAllItemsInListView("TAMIL_TERM");
                 displayInfoOfClickedItem();

                 break;

             case  R.id.searchCode:
                 enabledSearch = "code";
                 searchTerm.setBackgroundDrawable(d);
                 searchCode.setBackgroundColor(Color.WHITE);
                 searchCdsm.setBackgroundDrawable(d);

                 autoCompleteSearch2.setVisibility(View.GONE);
                 autoCompleteSearch1.setHint("Search by NSMC Code");

                 populateAllItemsInListView("NSMC_CODE");
                 displayInfoOfClickedItem();
                 break;

             case R.id.searchCdsm:
                 enabledSearch = "cdsm";
                 searchTerm.setBackgroundDrawable(d);
                 searchCode.setBackgroundDrawable(d);
                 searchCdsm.setBackgroundColor(Color.WHITE);

                 autoCompleteSearch2.setVisibility(View.GONE);
                 autoCompleteSearch1.setHint("Type in Signs or Symptoms");

                 populateAllItemsInListView("SHORT_DEFINITION");
                 displayInfoOfClickedItem();
                 break;
         }
    }

    /**
     * Going back to Main page on clicking the software back button
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent getSearchIntent = new Intent(this, MainActivity.class);
            startActivity(getSearchIntent);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void populateAllItemsInListView(String colName) {
        Cursor cursor = dbHelper.GetAllListViewItems();
        startManagingCursor(cursor);

        String[] from1 = new String[]{colName};
        int[] to1 = new int[]{R.id.text2};

        SimpleCursorAdapter cursorAdapter2 =
                new SimpleCursorAdapter(this, R.layout.row, cursor, from1, to1);


        listContent.setAdapter(cursorAdapter2);
        //listContent.setAdapter(new MySimpleArrayAdapter(this, from));
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        Log.d("namaste","clickID:"+v.getId());
        switch(v.getId()) {
            case R.id.autoCompleteSearch1:
                if(enabledSearch.equals("term")) {
                    populateAllItemsInListView("TAMIL_TERM");
                } else if(enabledSearch.equals("code")) {
                    populateAllItemsInListView("NSMC_CODE");
                } else if(enabledSearch.equals("cdsm")) {
                    populateAllItemsInListView("SHORT_DEFINITION");
            }

                break;

            case R.id.autoCompleteSearch2:
                populateAllItemsInListView("NSMC_TERM");
                break;
        }
    }

    public void clearSearch1 (View v) {
        autoCompleteSearch1.setText("");
        if(enabledSearch.equals("term")) {
            populateAllItemsInListView("TAMIL_TERM");
        } else if(enabledSearch.equals("code")) {
            populateAllItemsInListView("NSMC_TERM");
        } else if(enabledSearch.equals("cdsm")) {
            populateAllItemsInListView("SHORT_DEFINITION");
        }
        hideKeyboard(this);
        clearSearch1.setVisibility(View.GONE);
    }

    public void clearSearch2 (View v) {
        autoCompleteSearch2.setText("");
        populateAllItemsInListView("NSMC_TERM");
        hideKeyboard(this);
        clearSearch2.setVisibility(View.GONE);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void displayInfoOfClickedItem () {
        //redirect to search result page on click
        listContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> av, View view, int position, long l) {
                Cursor word = (Cursor) av.getItemAtPosition(position);

                ViewResult vr = new ViewResult();

                vr.setSearchText(word.getString(2));

                Intent getSearchIntent = new Intent(getApplicationContext(), ViewResult.class);
                startActivity(getSearchIntent);
            }
        });
    }
}
