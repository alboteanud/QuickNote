package com.alboteanu.notes2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alboteanu.notes2.data.Contract;
import com.alboteanu.notes2.data.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditActivity extends AppCompatActivity {
    static final String STATE_SAVE_MENU_ITEM = "saveMenuItem";
    MenuItem saveMenuItem;
    boolean isSaveVisible = false;
    String currentDateAndTime;
    EditText editTitle, editNote;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTitle = (EditText) findViewById(R.id.editTitle);
        editTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (saveMenuItem != null && !saveMenuItem.isVisible()) {
                    saveMenuItem.setVisible(true);
                }

            }
        });

        editNote = (EditText) findViewById(R.id.editNote);
        editNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (saveMenuItem != null && !saveMenuItem.isVisible()) {
                    saveMenuItem.setVisible(true);
                }

            }
        });

        sdf = new SimpleDateFormat("HH.mm   dd.MM.yyyy");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        saveMenuItem = menu.findItem(R.id.action_save);
        saveMenuItem.setVisible(isSaveVisible);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            Toast.makeText(this, "not implemented yet", Toast.LENGTH_SHORT).show();
            NavUtils.navigateUpFromSameTask(this);
            return true;
        } else if (id == R.id.action_save) {
            currentDateAndTime = sdf.format(new Date());
            ((TextView) findViewById(R.id.savedTime)).setText(currentDateAndTime);
            item.setVisible(false);
            saveToDatabase();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //saving the states
        if (saveMenuItem != null) {
            outState.putBoolean(STATE_SAVE_MENU_ITEM, saveMenuItem.isVisible());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            //recreating the visible state of the Save button
            isSaveVisible = savedInstanceState.getBoolean(STATE_SAVE_MENU_ITEM);
            //...
        } else {
            // initialize members with default values for a new instance
        }
    }

    private void saveToDatabase(){
     DbHelper mDbHelper = new DbHelper(this);
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Contract.Entry.COLUMN_NAME_ENTRY_ID, "1");
        values.put(Contract.Entry.COLUMN_NAME_TITLE, String.valueOf(editTitle.getText()));
        values.put(Contract.Entry.COLUMN_NAME_BODY, String.valueOf(editNote.getText()));
        values.put(Contract.Entry.COLUMN_NAME_LAST_EDITED, currentDateAndTime);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                Contract.Entry.TABLE_NAME,
                Contract.Entry.COLUMN_NAME_NULLABLE,
                values);

        Toast.makeText(this, "saved to database", Toast.LENGTH_SHORT).show();
    }
}
