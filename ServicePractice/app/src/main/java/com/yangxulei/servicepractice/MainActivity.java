package com.yangxulei.servicepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //onStartService()方法启动服务
    public void onStartService(View view){
        // 这里调用了startService()方法，需填入待启动服务的“意图”
        // 创建的Intent对象中第二个参数即要指向的Service类
        startService(new Intent(getBaseContext(),MyService.class));
    }
    // onStopService()方法用于停止服务
    public void onStopService(View view) {
        // 同样需要一个Intent对象来作为stopService()方法的参数
        stopService(new Intent(getBaseContext(), MyService.class));
    }
}
