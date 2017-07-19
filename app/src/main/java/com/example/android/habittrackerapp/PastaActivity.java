package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.habittrackerapp.data.PastaContract.PastaEntry;
import com.example.android.habittrackerapp.data.PastaDbHelper;

/**
 * Displays list of pastas that were entered and stored in the app.
 */
public class PastaActivity extends AppCompatActivity {

    /**
     * Database helper that will provide us access to the database
     */
    private PastaDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        mDbHelper = new PastaDbHelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pastas database.
     */
    private Cursor displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                PastaEntry._ID,
                PastaEntry.COLUMN_PASTA_NAME,
                PastaEntry.COLUMN_TIMES_EATEN
        };

        // Perform a query on the pastas table
        Cursor cursor = db.query(
                PastaEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order
        return cursor;

        // Simulate a view
        TextView displayView = new TextView(this);

        try {
            // Create a header in the Text View that looks like this:
            //
            // The pastas table contains <number of rows in Cursor> pastas.
            // _id - name - times_eaten
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            displayView.setText("The pastas table contains " + cursor.getCount() + " pastas.\n\n");
            displayView.append(PastaEntry._ID + " - " +
                    PastaEntry.COLUMN_PASTA_NAME + " - " +
                    PastaEntry.COLUMN_TIMES_EATEN + "\n");

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(PastaEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(PastaEntry.COLUMN_PASTA_NAME);
            int TimesEatenColumnIndex = cursor.getColumnIndex(PastaEntry.COLUMN_TIMES_EATEN);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentTimesEaten = cursor.getInt(TimesEatenColumnIndex);

                // Display the values from each column of the current row in the cursor in the TextView
                displayView.append(("\n" + currentID + " - " +
                        currentName + " - " +
                        currentTimesEaten));
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    /**
     * Helper method to insert hardcoded pet data into the database. For debugging purposes only.
     */
    private void insertPasta() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Penne's pasta attributes are the values.
        ContentValues values = new ContentValues();
        values.put(PastaEntry.COLUMN_PASTA_NAME, "Penne");

        values.put(PastaEntry.COLUMN_TIMES_EATEN, 7);

        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pastas table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Penne.
        long newRowId = db.insert(PastaEntry.TABLE_NAME, null, values);
    }


}
