package com.example.greenflag;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoDatabase extends SQLiteOpenHelper {

    public ToDoDatabase(Context context) {
        super(context,
                DatabaseUtil.databaseName,
                null,
                DatabaseUtil.dabtabaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE "+
                        DatabaseUtil.TaskTable.tableName +
                        " (" +
                        DatabaseUtil.TaskTable._ID +
                        " INTEGER PRIMARY KEY," +
                        DatabaseUtil.TaskTable.nameColumn +
                        " VARCHAR(255)," +
                        DatabaseUtil.TaskTable.usernameColumn +
                        " VARCHAR(255)," +
                        DatabaseUtil.TaskTable.photoColumn +
                        " VARCHAR(255)," +
                        DatabaseUtil.TaskTable.ageColumn +
                        " VARCHAR(255)," +
                        DatabaseUtil.TaskTable.birthDateColumn +
                        " VARCHAR(255)," +
                        DatabaseUtil.TaskTable.countryColumn +
                        " VARCHAR(255)," +
                        DatabaseUtil.TaskTable.genderColumn +
                        " VARCHAR(255)," +
                        DatabaseUtil.TaskTable.postalCodeColumn +
                        " VARCHAR(255))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
