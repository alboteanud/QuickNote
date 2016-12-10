package com.alboteanu.notes2.data;

import android.provider.BaseColumns;

/**
 * Created by Dan on 04/04/2016.
 */
public final class  Contract {
    public Contract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class Entry implements BaseColumns {
        public static final String TABLE_NAME = "entry";
        public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_NAME_LAST_EDITED = "editedtime";
        public static final String COLUMN_NAME_NULLABLE = "null" ;
    }
}
