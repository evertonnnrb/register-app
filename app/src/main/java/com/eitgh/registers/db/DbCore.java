package com.eitgh.registers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbCore extends SQLiteOpenHelper {

    private static final String DB_NAME = "db.8eithg";
    public static final int VERSION = 1;

    public DbCore(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableUser());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(dropTableUser());
        onCreate(db);
    }

    private String createTableUser() {
        StringBuilder sb = new StringBuilder();
        sb.append("create table user (_id integer primary key autoincrement,");
        sb.append("name text not null, email text not null, password text not null);");
        return sb.toString();
    }

    private String dropTableUser() {
        StringBuilder sb = new StringBuilder();
        sb.append("drop table user");
        return sb.toString();
    }
}
