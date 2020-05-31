package com.example.managetime.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String NAME = "mvpsample";
    public static final int VERSION = 1;

    public static final String USERS_TABLE_NAME = "user";
    public static final String PATTERNS_TABLE_NAME = "pattern";
    public static final String PROJECTS_TABLE_NAME = "project";

    public static class COLUMN {
        public static final String ID = "_id";
        public static final String NAME = "name";
        public static final String EMAIL = "email";
    }

    public static final String CREATE_SCRIPT =
            String.format("create table %s ("
                            + "%s integer primary key autoincrement,"
                            + "%s text,"
                            + "%s text" + ");",
                    USERS_TABLE_NAME, COLUMN.ID, COLUMN.NAME, COLUMN.EMAIL);

    public DBHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}