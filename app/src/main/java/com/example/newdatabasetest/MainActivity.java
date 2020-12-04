package com.example.newdatabasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button_insert;
   // private Button button_delete;
    //private Button button_update;
    //private Button button_query;
    //private MyContentProvider myContentProvider;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    //private SQLiteDatabase sqLiteDatabase;
    private String newid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this,"BOOKSTORE.db",null,1);
        button_insert.setOnClickListener(this);
        /*button_query.setOnClickListener(this);
        button_update.setOnClickListener(this);
        button_delete.setOnClickListener(this);*/
    }

    private void init() {
        button_insert = findViewById(R.id.button_insert);
       // button_query = findViewById(R.id.button_query);
        //button_update = findViewById(R.id.button_update);
        //button_delete = findViewById(R.id.button_delete);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_insert:
                mySQLiteOpenHelper.getWritableDatabase();
                break;
        }
    }
}