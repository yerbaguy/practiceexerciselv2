package com.bartoszmaliszewski.practiceexerciselv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bartoszmaliszewski on 19.02.18.
 */

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    public SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {

        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();

        return this;

    }

    public void close() {
        dbHelper.close();
    }

    public Cursor fetch() {

        String[] columns = new String[] { DatabaseHelper._id, DatabaseHelper.engword, DatabaseHelper.plword };

        Cursor cursor = database.query( DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    public int update(long _id, String engword, String plword) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.engword, engword);
        contentValues.put(DatabaseHelper.plword, plword);

        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._id + " = " + _id, null);

        return i;



    }



    //   public Cursor fetch() {

    //       Cursor cursor = database.rawQuery("SELECT * FROM Word", null);

    //       if (cursor != null) {
    //           cursor.moveToFirst();
    //       }

    //       return cursor;

    //   }
}
