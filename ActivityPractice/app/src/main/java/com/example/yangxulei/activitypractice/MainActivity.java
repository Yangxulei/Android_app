package com.example.yangxulei.activitypractice;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button_showName;
    private TextView textView_content;
    private EditText editText_name;

    String msg = "yxl:";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Acitvity类通过xml文件来加载所有UI组件
        setContentView(R.layout.activity_main);

        Log.d(msg,"The function onCreate() was called");

        // 通过findViewById()方法来实例化各个组件
        editText_name = (EditText)findViewById(R.id.editText_name);
        button_showName = (Button)findViewById(R.id.button_showName);
        textView_content = (TextView)findViewById(R.id.textView_content);

        // 调用button_showName对象的setOnClickListener()方法来注册监听器
        // 这里的注册方式是匿名内部类，即直接在setOnClickListener()方法的参数中，
        // 声明一个点击事件监听器，并实现onClick()事件处理者里面的逻辑

        button_showName.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // 调用editText_name对象的getText()方法来获取文本框中的输入内容
                // 再用toString()方法将输入内容转为String格式
                String name = editText_name.getText().toString();

                // 获取到输入值后，将其用textView_content对象的setText()方法显示出来
                textView_content.setText(name);

                // 还可以调用它的setTextColor()方法来改变文本的颜色
                textView_content.setTextColor(Color.RED);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(msg,"onStart() was called");
    }
    //当activity已经变得可见的时候调用
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(msg,"onResume() was called");
    }
    //当activity已经获得焦点时会调用
    @Override
    protected void  onPause(){
        super.onPause();
        Log.d(msg,"onPause() was called");
    }
    //当activity不再可见的时候调用
    @Override
    protected void onStop(){
        super.onStop();
        Log.d(msg,"onStop() was called");
    }
}
