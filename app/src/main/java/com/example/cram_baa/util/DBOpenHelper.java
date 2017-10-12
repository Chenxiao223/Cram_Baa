package com.example.cram_baa.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private Context mContext;

    public static final String SetSql = "create table Setting (id integer PRIMARY KEY autoincrement not null," +
            "Name varchar(50) not null,Sex varchar(50) not null,Region varchar(50) not null," +
            "Age varchar(50) not null,School varchar(50) not null," +
            "Grade varchar(50) not null,Clbum varchar(50) not null)";

    public static final String delete_setsql = "drop table if exists Setting";

    public static final String DATABASENAME = "goss.db";

    public static final int DATABASEVERSION = 1;

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        mContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(SetSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL(delete_setsql);
        onCreate(db);
    }

}
