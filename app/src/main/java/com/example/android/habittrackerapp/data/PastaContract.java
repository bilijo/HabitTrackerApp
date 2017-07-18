package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by dam on 18.07.2017.
 */

public class PastaContract {

    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    private PastaContract() {}

    /**
     * Inner class that defines constant values for the pastas database table.
     * Each entry in the table represents a single pasta.
     */
    public static final class PastaEntry implements BaseColumns {

        /** Name of database table for pastas */
        public final static String TABLE_NAME = "pastas";

        /**
         * Unique ID number for the pasta (only for use in the database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * Name of the pasta.
         *
         * Type: TEXT
         */
        public final static String COLUMN_PASTA_NAME ="name";

        /**
         * Times pasta was eaten.
         *
         * Type: TEXT
         */
        public final static String COLUMN_TIMES_EATEN = "times eaten";

       
    }


}
