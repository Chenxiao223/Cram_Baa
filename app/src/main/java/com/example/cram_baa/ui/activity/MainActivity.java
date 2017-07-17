package com.example.cram_baa.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.example.cram_baa.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目的启动页
 */
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //判断网络连接，如果没有网络就报语音
        if (!checkNetworkInfo()){
            Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
        }
        Timer timer=new Timer();
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                finish();
            }
        };
        timer.schedule(timerTask, 3000);
    }

    public boolean checkNetworkInfo() {
        ConnectivityManager conMan = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = conMan.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING)
            return true;
        if (wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)
            return true;
        return false;
    }
}
