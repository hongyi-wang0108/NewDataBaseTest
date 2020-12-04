package com.example.newdatabasetest;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {

    private MySQLiteOpenHelper mySQLiteOpenHelper;//
    private SQLiteDatabase sqLiteDatabase;//
    private ContentProvider contentProvider;//
    public static UriMatcher uriMatcher;//
    public static final String AUTHORITY = "com.example.newdatabasetest.provider";// manifest  public

    public static final int BOOK_ALL = 1;//public
    public static final int BOOK_ITEM = 2;

    static {//
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY,"book",BOOK_ALL);
        uriMatcher.addURI(AUTHORITY,"book/#",BOOK_ITEM);
    }
    //1.
    @Override
    public boolean onCreate() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(getContext(),
                "BOOKSTORE.db",null,1);
        return true;
    }
    //2
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection,
                        @Nullable String selection, @Nullable String[] selectionArgs,
                        @Nullable String sortOrder) {
        sqLiteDatabase = mySQLiteOpenHelper.getReadableDatabase();

        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case BOOK_ALL:
                 /*cursor = sqLiteDatabase.query("BOOK",null,null
                         ,null,null,null,null);
                 */
                cursor = sqLiteDatabase.query("BOOK",projection,selection
                        ,selectionArgs,null,null,sortOrder);
                break;
            case BOOK_ITEM:
                /*cursor = sqLiteDatabase.query("BOOK",projection,selection,selectionArgs
                ,null,null,sortOrder);*/
                String id = uri.getPathSegments().get(1);
                cursor = sqLiteDatabase.query("BOOK",projection,"id = ?"
                        ,new String[]{id},null,null,sortOrder);
                break;
            default:
                break;
        }
        //cursor.close();
        return cursor;
    }
 //
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case BOOK_ALL:
                return "vnd.android.cursor.dir/vnd.com.example.newdatabasetest.provider.book";
            case BOOK_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.newdatabasetest.provider.book";
        }
        return null;
    }
//
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        Uri uri1 = null;//
        switch (uriMatcher.match(uri)){
            case BOOK_ALL:
                //return null;
                //break;
            case BOOK_ITEM:
                long newbookid = sqLiteDatabase.insert("BOOK", null, values);
                uri1 = Uri.parse("content://"+ AUTHORITY +"/book/"+newbookid);
                //return uri1;
                break;
            default:
                //return null;
                break;
        }
        return  uri1;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        sqLiteDatabase =  mySQLiteOpenHelper.getWritableDatabase();
        int del = 0;
        switch (uriMatcher.match(uri)){
            case BOOK_ALL:
                del = sqLiteDatabase.delete("BOOK", selection, selectionArgs);
                //return del;
                break;
            case BOOK_ITEM:
                //del = sqLiteDatabase.delete("BOOK",selection,selectionArgs);
                //return del;

                String id = uri.getPathSegments().get(1);
                del = sqLiteDatabase.delete("BOOK","id = ?", new String[]{id});
                break;
            default:
                break;
        }
        return del;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        sqLiteDatabase = mySQLiteOpenHelper.getWritableDatabase();
        int upid =  0;
        switch (uriMatcher.match(uri)){
            case BOOK_ALL://这之前啥也没写
                upid = sqLiteDatabase.update("BOOK",values,selection,selectionArgs);
                //return upid;
                break;
            case BOOK_ITEM:
                String id = uri.getPathSegments().get(1);
                upid = sqLiteDatabase.update("BOOK",values,"id = ?", new String[]{id});
                break;
                //return upid;
        }
        return upid;
    }
}
