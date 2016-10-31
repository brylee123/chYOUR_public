package com.chyour;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.camera2.params.StreamConfigurationMap;

/**
 * Created by yoseph on 9/29/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_version = 1;

    public static final String DB_NAME = "chyour_db";
    public static final String TABLE_NAME = "task_table";
    public static final String COLUMN_1 = "ID";
    public static final String COLUMN_2 = "NAME";
    public static final String COLUMN_3 = "LOCATION";
    public static final String COLUMN_4 = "START_TIME";
    public static final String COLUMN_5 = "END_TIME";

    public static final String Create_table = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_2 + "TEXT" + COLUMN_3 + "TEXT" + COLUMN_4 + " TEXT NOT NULL"+
            COLUMN_5 +" TEXT NOT NULL );";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(Create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);

    }

    public boolean insert (String name, String location, String startTime , String endTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_2, name);
        contentValues.put(COLUMN_3, location);
        contentValues.put(COLUMN_4, startTime);
        contentValues.put(COLUMN_5, endTime);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;
        else
            return true;


    }

    public Integer delete (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        
        return db.delete(TABLE_NAME, "id =" + id, null);
    }
}
