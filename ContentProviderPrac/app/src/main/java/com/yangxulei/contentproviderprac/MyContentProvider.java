package com.yangxulei.contentproviderprac;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {
    //声明一个静态的URI Matcher对象
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    //创建两个常量，用于标记URi的类别
    private static  final int STUDENTS = 1;
    private static  final int STUDENT = 2;

    //创建一个DataHelper对象操作数据库
    private MyDatabaseHelper dbOpenHelper;
    static{
        //为URI matcher添加两个URI
        matcher.addURI(Students.AUTHORITY,"students",STUDENTS);
        matcher.addURI(Students.AUTHORITY,"student/#",STUDENT);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // 获得数据库的实例
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        // 变量num用于记录待删除的记录数
        int num = 0;

        switch (matcher.match(uri))
        {
            // 如果URI的参数代表操作全部的学生信息数据项，则删除这些数据项
            case STUDENTS:
                num = db.delete("students", selection, selectionArgs);
                break;

            // 如果URI的参数代表操作指定的学生信息数据项，则需要解析出指定学生的ID
            case STUDENT:

                long id = ContentUris.parseId(uri);
                String whereClause = Students.Student._ID + "=" + id;
                // 同理，拼接操作语句
                if (where != null && !selection.equals(""))
                {
                    whereClause = whereClause + " and " + selection;
                }
                num = db.delete("students", whereClause, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
        // 调用notifyChange()方法，发出通知，表明数据已经被改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;
    }


    // 自定义方法getType()，用于获得指定URI参数所对应数据的MIME类型
    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        switch (matcher.match(uri))
        {

            // 该项对应操作的是多项数据记录
            case STUDENTS:
                return "vnd.android.cursor.dir/com.shiyanlou.students";
            // 该项对应操作的是单项数据记录
            case STUDENT:
                return "vnd.android.cursor.item/com.shiyanlou.student";
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }


       // throw new UnsupportedOperationException("Not yet implemented");


    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        // 获得数据库的实例
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri))
        {
            // 如果URI的参数代表操作全部的学生信息数据项
            case STUDENTS:
                // 插入学生相关数据，然后返回插入后的ID
                long rowId = db.insert("students", Students.Student._ID, values);
                // 如果插入成功，rowID肯定多于0个，所以返回其URI
                if (rowId > 0)
                {
                    // 在已存在的URI后面追加ID
                    Uri studentUri = ContentUris.withAppendedId(uri, rowId);
                    // 调用notifyChange()方法，发出通知，表明数据已经被改变
                    getContext().getContentResolver().notifyChange(studentUri, null);
                    return studentUri;
                }
                break;
            default :
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
        return null;
    }

    // 在该内容提供者首次被调用时，该方法会被回调
    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.

        dbOpenHelper = NEW MyDataseHelper(this.getContext(),"myStudents.db",1);



        return true;
    }

    // 自定的query()方法，用于查询数据
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        switch (matcher.match(uri))
        {
            // 如果URI的参数代表操作全部的学生信息数据项
            case STUDENTS:
                // 执行相应的查询
                return db.query("students", projection, selection,
                        selectionArgs, null, null, sortOrder);
            // 如果URI的参数代表操作指定某个学生的数据
            case STUDENT:
                // 如果URI的参数代表操作指定的学生信息数据项，则需要解析出指定学生的ID
                long id = ContentUris.parseId(uri);

                String whereClause = Students.Student._ID + "=" + id;

                // 如果之前的where表达式存在，则直接用它来拼接新的操作语句
                if (selection != null && !"".equals(selection))
                {
                    whereClause = whereClause + " and " + selection;
                }

                // 执行查询，返回结果
                return db.query("students", projection, whereClause, selectionArgs,
                        null, null, sortOrder);
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }
    }
//修改数据
    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // 获得数据库的实例
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

        int num = 0;
        switch (matcher.match(uri))
        {
            // 如果URI的参数代表操作全部的学生信息数据项
            case STUDENTS:
                // 更新指定的记录
                num = db.update("students", values, selection, selectionArgs);
                break;

            // 如果URI的参数代表操作指定的学生信息数据项，则需要解析出指定学生的ID
            case STUDENT:

                long id = ContentUris.parseId(uri);
                String whereClause = Students.Student._ID + "=" + id;
                // 同理，进行操作语句的拼接
                if (where != null && !where.equals(""))
                {
                    whereClause = whereClause + " and " + selection;
                }
                num = db.update("students", values, whereClause, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri:" + uri);
        }

        // 调用notifyChange()方法，发出通知，表明数据已经被改变
        getContext().getContentResolver().notifyChange(uri, null);
        return num;

    }
}
