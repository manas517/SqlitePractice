package com.nayanatech.sqlitedatabasedemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyLocalDb extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DemoSqlite.db";
    public static final int DATABASE_VERSION = 1;
    public static final String MyPersonalInfo_TABLE_NAME = "MyPersonalInfo";
    public static final String MyPersonalInfo_COLUMN_ID = "id";
    public static final String MyPersonalInfo_COLUMN_NAME = "name";

    public MyLocalDb( Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABBLE_MyPersonalInfo="create table "+MyPersonalInfo_TABLE_NAME+" ( id integer primary key,name varchar )";
        sqLiteDatabase.execSQL(CREATE_TABBLE_MyPersonalInfo);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }

    public void insertToMyPersonalInfo(String nameData){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyPersonalInfo_COLUMN_NAME, nameData);
        Long i=sqLiteDatabase.insert(MyPersonalInfo_TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        Log.i("inserted rows ",i+"");
    }

    public ArrayList<String> getDataFromMyPersonalInfo(){
        ArrayList<String> namelist=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        String SQL_GET_NAMELIST="SELECT * from "+MyPersonalInfo_TABLE_NAME;
        Cursor cursor=sqLiteDatabase.rawQuery(SQL_GET_NAMELIST,null);
        while(cursor.moveToNext()){
            String nameValue=cursor.getString(cursor.getColumnIndexOrThrow(MyPersonalInfo_COLUMN_NAME));
            namelist.add(nameValue);
        }
        cursor.close();
        sqLiteDatabase.close();
        return namelist;
    }

    public void clearMyPersonalInfo(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        sqLiteDatabase.delete(MyPersonalInfo_TABLE_NAME,null,null);
        sqLiteDatabase.close();
        /*String SQL_CLEAR_TABLE="DELETE  FROM "+MyPersonalInfo_TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_CLEAR_TABLE);*/
    }

    public void updateMyPersonalInfo(String namevalue){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(MyPersonalInfo_COLUMN_NAME,namevalue);
        sqLiteDatabase.update(MyPersonalInfo_TABLE_NAME,cv,null,null);
        sqLiteDatabase.close();
        /*String SQL_CLEAR_TABLE="DELETE  FROM "+MyPersonalInfo_TABLE_NAME;
        sqLiteDatabase.execSQL(SQL_CLEAR_TABLE);*/
    }
}
