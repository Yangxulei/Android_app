package com.yangxulei.contentproviderprac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    //声明一个ListView对象用来显示查询结果
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //实例化listView
        listView = (ListView)findViewById(R.id.listView_result);

        //从Intent对象中取出携带的数据
        Intent intent = getIntent();
        //从bundle独享进一步获得数据
        Bundle data = intent.getExtras();

        //@SuppressWarnings("unchecked");
        List<Map<String,String>> list = (List<Map<String,String>>)data.getSerializable("data");

        //讲上面List封装成SimpleAdapter对象，用于填充列表数据
        SimpleAdapter adapter = new SimpleAdapter(ResultActivity.this,list,
                android.R.layout.item,
                new String[]{"student","Information"},
                new int[]{R.id.editText_item_student,
                R.id.editText_item_information}
                );


    }
}
