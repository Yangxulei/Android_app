package com.yangxulei.contentproviderprac;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //创建一个数据库对象操作数据库
    MyDatabaseHelper dbHelper;
    // 声明两个按钮
    Button button_insert;
    Button button_search;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 实例化这个MyDatabaseHelper对象，指定它的数据库版本为1，
        // 这个myStudents.db文件可以在应用的data/databases目录下找到
        dbHelper = new MyDatabaseHelper(this, "myStudents.db", 1);

        // 实例化两个按钮
        button_insert = (Button) findViewById(R.id.button_insert);
        button_search = (Button) findViewById(R.id.button_search);

        // 为按钮注册点击事件监听器
        button_insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View source) {

                // 获取用户在文本框上的输入信息
                String student = ((EditText) findViewById(R.id.editText_student)).getText().toString();
                String information = ((EditText) findViewById(R.id.editText_information)).getText().toString();

                // 将输入信息插入到数据库中，这里用到了自定义的insertData方法
                insertData(dbHelper.getReadableDatabase(), student, information);

                // 显示一个Toast信息提示用于已经添加成功了
                Toast.makeText(MainActivity.this, "Add student successfully!", Toast.LENGTH_LONG).show();
            }
        });

        button_search.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View source)
            {
                // 获取用户在文本框上输入的关键词
                String key = ((EditText) findViewById(R.id.editText_keyword)).getText().toString();

                // 利用游标对象，执行“行查询”，结果存入了游标对象中
                Cursor cursor = dbHelper.getReadableDatabase()
                        .rawQuery("select * from students where student like ? or information like ?",
                                new String[]{"%" + key + "%", "%" + key + "%"});

                // 创建一个Bundle对象来存入待传递的信息
                Bundle data = new Bundle();
                // 将游标转化为list对象放入Bundle对象中
                data.putSerializable("data", CursorConverToList(cursor));
                // 创建一个Intent来携带Bundle对象
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtras(data);
                // 启动查询结果的Activity
                startActivity(intent);
            }
        });
    }

    protected ArrayList<Map<String, String>> CursorConverToList(Cursor cursor){

        // 创建一个数组列表用于存放查询结果
        ArrayList<Map<String, String>> result = new ArrayList<Map<String, String>>();

        // 在Cursor对象中遍历整个结果集
        while (cursor.moveToNext())
        {
            // 将结果集中的数据存入ArrayList对象里面
            Map<String, String> map = new HashMap<String, String>();
            // 取出记录中第2、3列的值，即学生的名字和信息（第1列为id）
            map.put("student", cursor.getString(1));
            map.put("information", cursor.getString(2));
            result.add(map);
        }

        // 返回转换的结果
        return result;
    }

    private void insertData(SQLiteDatabase db, String student, String information)
    {
        // 执行插入语句，插入学生的姓名以及信息
        db.execSQL("insert into students values(null , ? , ?)", new String[] {student, information });
    }


    // 需要重写onDestroy()方法，用于保证应用应用退出时，数据库能被正常关闭
    @Override
    public void onDestroy()
    {
        super.onDestroy();

        if (dbHelper != null)
        {
            dbHelper.close();
        }
    }

}
