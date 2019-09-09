package com.dicoding.aplikasiphotomurid.Dataset;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME ="database_nama_murid.db";
    private static final String TABLE_NAME = "nama_siswa";
    private static final String COLUMN_ID  = "id";
    private static final String COLUMN_NAME = "nama";
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_SQL_TABLE_NAME = "CREATE TABLE "+TABLE_NAME+" ("
                +COLUMN_ID+" INTEGER PRIMARY KEY autoincrement, "
                +COLUMN_NAME+" TEXT NOT NULL)";
        db.execSQL(CREATE_SQL_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<DataModel> getAllData() {
        ArrayList<DataModel> wordList;
        wordList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DataModel murid = new DataModel();
                murid.setId(cursor.getInt(0));
                murid.setNama(cursor.getString(1));
                wordList.add(murid);
            } while (cursor.moveToNext());
        }

        Log.e("select sqlite ", "" + wordList);

        database.close();
        return wordList;
    }

    public void insert(String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + TABLE_NAME + " (nama) " +
                "VALUES ('" + name + "')";

        Log.e("insert sqlite ", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

    public void update(int id, String name) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "UPDATE " + TABLE_NAME + " SET "
                + COLUMN_NAME + "='" + name + "' "
                + "WHERE " + COLUMN_ID + "=" + id ;
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }

    public void delete(int id) {
        SQLiteDatabase database = this.getWritableDatabase();

        String updateQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + "'" + id + "'";
        Log.e("update sqlite ", updateQuery);
        database.execSQL(updateQuery);
        database.close();
    }
}
