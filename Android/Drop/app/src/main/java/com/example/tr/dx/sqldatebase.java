package com.example.tr.dx;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqldatebase extends SQLiteOpenHelper {
    public final static String TABLE_NAME = "photo";

    public final static String COLUMN_USER_NAME = "id";
    public final static String COLUMN_USER_pass = "pass";
    public final static String COLUMN_USER_IMG = "img";
    public final static String COLUMN_USER_DATA = "data";

    public final static int GENDER_UNKONWN = 0;
    public final static int GENDER_MALE = 1;
    public final static int GENDER_FORMALE = 2;

        public static final String LOG_TAG = sqldatebase.class.getSimpleName();

        private static final String DATABASE_NAME = "photo.db";


        private static final int DATABASE_VERSION = 1;


    public sqldatebase(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db) {

            String SQL_CREATE_PETS_TABLE =  "CREATE TABLE " + TABLE_NAME + " ("
                    + COLUMN_USER_NAME + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USER_pass + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USER_IMG + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USER_DATA + " TEXT NOT NULL "+")";


            db.execSQL(SQL_CREATE_PETS_TABLE);
        }

        /**
         * This is called when the database needs to be upgraded.
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // The database is still at version 1, so there's nothing to do be done here.
        }
    }

