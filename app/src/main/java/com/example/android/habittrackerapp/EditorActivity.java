package com.example.android.habittrackerapp;

/**
 * Created by dam on 18.07.2017.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.habittrackerapp.data.PastaDbHelper;
import com.example.android.habittrackerapp.data.PastaContract.PastaEntry;

/**
 * Allows user to create a new pasta or edit an existing one.
 */
public class EditorActivity extends AppCompatActivity {

 /** EditText field to enter the pasta's name */
    private EditText mNameEditText;

    /** EditText field to enter how many times the pasta's were eaten */
    private EditText mTimesEatenEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Find all relevant views that we will need to read user input from
        mNameEditText = (EditText) findViewById(R.id.edit_pasta_name);
        mTimesEatenEditText = (EditText) findViewById(R.id.edit_pasta_times_eaten);

    }

    /**
     * Get user input from editor and save new pasta into database.
     */
    private void insertPet() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mNameEditText.getText().toString().trim();
        String timesEatenString = mTimesEatenEditText.getText().toString().trim();
        int timesEaten = Integer.parseInt(timesEatenString);

        // Create database helper
        PastaDbHelper mDbHelper = new PastaDbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pasta attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(PastaEntry.COLUMN_PASTA_NAME, nameString);
        values.put(PastaEntry.COLUMN_TIMES_EATEN, timesEaten);


        // Insert a new row for pasta in the database, returning the ID of that new row.
        long newRowId = db.insert(PastaEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving pasta", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Pet saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


} // end of EditorActivity