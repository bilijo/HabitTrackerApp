package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.version;
import com.example.android.habittrackerapp.data.PastaContract.PastaEntry;

/**
 * Created by dam on 18.07.2017.
 */

public class PastaDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = PastaDbHelper.class.getSimpleName();


    /** Name of the database file */
    private static final String DATABASE_NAME = "pasta.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;
    

    /**
     * Constructs a new instance of {@link PastaDbHelper}.
     *
     * @param context of the app
     */
    public PastaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_PASTAS_TABLE =  "CREATE TABLE " + PastaEntry.TABLE_NAME + " ("
                + PastaEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PastaEntry.COLUMN_PASTA_NAME + " TEXT NOT NULL, "
                + PastaEntry.COLUMN_TIMES_EATEN + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_PASTAS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.

    }
}
