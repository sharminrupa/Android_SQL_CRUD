package com.crudsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final static int DB_VERSION = 1;
    private final static String DB_NAME = "crudappDb";

    private static DatabaseHelper instance;

    private final static String CREATE_STUDENT_TABLE ="CREATE TABLE students " +
            "(" +
                "id integer primary key AUTOINCREMENT, " +
                "name text, " +
                "mobile text " +
            ")" ;

    private final static String CREATE_USER_TABLE ="CREATE TABLE users " +
            "(" +
            "id integer primary key AUTOINCREMENT, " +
            "name text, " +
            "username text, " +
            "password text " +
            ")" ;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context){

        if(instance == null){
            instance = new DatabaseHelper(context);
        }

        return instance;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDENT_TABLE);
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS users");

        // Create tables again
        onCreate(db);
    }
}
