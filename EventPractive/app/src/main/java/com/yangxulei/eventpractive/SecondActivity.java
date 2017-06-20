package com.yangxulei.eventpractive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    // 声明一个文本标签控件
    private TextView textView_message;

    // 声明一个字符串变量用于存放前一个Activity中传过来的信息
    private String message ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // 实例化这个文本标签
        textView_message = (TextView)findViewById(R.id.textView_message);

        // 通过getIntent()方法来获得启动当前Activity的上一个Intent对象
        Intent intent = getIntent();

        // 从intent对象中通过getExtras()方法获得其携带的消息，
        // 接下来调用getString()方法来得到这个字符串，注意该方法的参数为(字符串的key，默认值)
        message = intent.getExtras().getString("mA","null");

    }

    public void onShowMessage(View view){
        // 如果获得了上个Activity传过来的消息，则显示在TextView中
        if(message!=null){
            textView_message.setText(message);
        }

    }

}
