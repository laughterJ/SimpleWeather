package com.wiker.simpleweather.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CityDatabaseHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;

    private static final String CREATE_PROVINCE = "create table Province ("
            + "id integer primary key,"
            + "name text,"
            + "areacode text)";

    private static final String CREATE_CITY = "create table City ("
            + "id integer primary key,"
            + "name text,"
            + "areacode text,"
            + "parentid integer,"
            + "foreign key (parentid) references Province(id))";

    private static final String CREATE_TOWN = "create table Town ("
            + "id integer primary key,"
            + "name text,"
            + "areacode text,"
            + "parentid integer,"
            + "foreign key (parentid) references City(id))";

    public CityDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_PROVINCE);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_TOWN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
