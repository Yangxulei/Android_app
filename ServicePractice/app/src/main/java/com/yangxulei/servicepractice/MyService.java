package com.yangxulei.servicepractice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        // 该方法会持续运行直到服务已经被关闭
        // Toast为屏幕中下方要显示的提示框

        Toast.makeText(this,"Service status:started",Toast.LENGTH_LONG).show();
        // 这里的START_STICKY是onStartCommand(0方法的常量返回值
        return START_STICKY;

    }
    @Override
    public void onDestroy() {
        // 继承父类的onDestroy()方法
        super.onDestroy();

        Toast.makeText(this, "Service status: destroyed", Toast.LENGTH_LONG).show();
    }

}
