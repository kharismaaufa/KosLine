package com.example.koslineapk.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class Helper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "dataKos";

    public Helper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE = "CREATE TABLE kos (id INTEGER PRIMARY KEY autoincrement, nama TEXT NOT NULL, alamat TEXT NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS kos");
        onCreate(sqLiteDatabase);
    }

    public ArrayList<HashMap<String, String>> getAll() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String QUERY = "SELECT * FROM kos";
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(QUERY, null);
        if (cursor.moveToFirst()){
            do{
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("alamat", cursor.getString(2));
                list.add(map);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void insert(String nama, String alamat){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY =  "INSERT INTO kos (nama,alamat) VALUES('"+nama+"', '"+alamat+"')";
        database.execSQL(QUERY);
    }

    public void update(int id, String nama, String alamat){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "UPDATE kos SET nama = '"+nama+"', alamat = '"+alamat+"' WHERE id = "+id;
        database.execSQL(QUERY);
    }


    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "DELETE FROM kos WHERE id = "+id;
        database.execSQL(QUERY);
    }


}
