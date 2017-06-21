package com.yangxulei.contentproviderprac;

import android.content.ContentUris;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by yangxulei on 2017/6/21.
 */
public class MyDatabaseHelper {

    final String CREATE_TABLE_SQL ="create table students(_id integer primary key autoincrement , student , information)";

    public MyDatabaseHelper(ContentUris context, String name, int version)
    {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        System.out.println("Method onUpdate has been called.OLD:" + oldVersion + "Changed into NEW:" + newVersion);
    }

}
