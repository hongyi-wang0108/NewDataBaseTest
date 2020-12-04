package com.example.newdatabasetest;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//1.先继承SQLiteOpenHelper创建类MySQLiteOpenHelper
public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String BOOK = //2.创建表的字符串
        "create table BOOK(" +
                "id integer primary key autoincrement," +
                "author text," +
                "price real," +
                "pages integer," +
                "name text);";
    private Context mcon;//xie toast
    //3.有参构造，用改动
    public MySQLiteOpenHelper(@Nullable Context context,
                              @Nullable String name,
                              @Nullable SQLiteDatabase.CursorFactory factory,
                              int version) {
        super(context, name, factory, version);
        mcon = context;
    }

    //4.oncreate中建表
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BOOK);
    }

    //5...不用写onUpgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
