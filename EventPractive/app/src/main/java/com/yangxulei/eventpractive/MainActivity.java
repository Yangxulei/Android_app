package com.yangxulei.eventpractive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button_sendMessage;
    private EditText editText_mesage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //实例化控件
        button_sendMessage = (Button)findViewById(R.id.button_sendMessage);
        editText_mesage = (EditText)findViewById(R.id.editText_message);


        //通过匿名内部类为button_sendMessage注册点击事件
        button_sendMessage.setOnClickListener(new View.OnClickListener() {
            //重写onClick方法,实现点击事件处理者的功能

            @Override
            public void onClick(View view) {
                //创建一个字符串对象用于接受文本框的内容
                String message = new String();
                //通过
                message = editText_mesage.getText().toString();

                // 创建一个“意图”，用于从MainActivity跳转到SecondActivity
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                // 创建一个Bundle对象，名为data，用于在Activity之间传递信息
                Bundle data = new Bundle();
                // 向这个Bundle对象中存入内容，因为是字符串，所以用putString()方法
                // 参数的形式是key-value形式，在另外一个Activity取出内容的时候就是通过这个key
                // 如果是其他的内容，则根据不同的数据类型选用不同的存入方法
                data.putString("mA",message);

                // 最后将Bundle存入intent对象中，让后者携带到另外一个Activity中
                intent.putExtras(data);

                // 调用startActivity()方法，跳转到另外一个Activity
                startActivity(intent);

                // 如果你想在启动SecondActivity的过程中关闭现在这个Activity，
                // 则可以使用finish()方法来结束当前Activity。
                // MainActivity.this.finish();

            }
        });

    }





}
