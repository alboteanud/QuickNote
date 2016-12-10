package com.alboteanu.notes2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dan on 04/04/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "MyNotes.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + Contract.Entry.TABLE_NAME + " (" +
                    Contract.Entry._ID + " INTEGER PRIMARY KEY," +
                    Contract.Entry.COLUMN_NAME_ENTRY_ID + TEXT_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_TITLE + TEXT_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_BODY + TEXT_TYPE + COMMA_SEP +
                    Contract.Entry.COLUMN_NAME_LAST_EDITED + TEXT_TYPE +
    // Any other options for the CREATE command
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Contract.Entry.TABLE_NAME;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL(SQL_DELETE_ENTRIES);   //use this to discard the data - e.g. if this database is only a cache for online data
        onCreate(db);
    }
}
