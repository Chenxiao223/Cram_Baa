package com.example.cram_baa.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.cram_baa.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 项目的启动页
 */
public class MainActivity extends BaseActivity {
    private LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private String g_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onCreate();
        //判断网络连接，如果没有网络就报语音
        if (!checkNetworkInfo()) {
            Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
        }
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent it = new Intent(MainActivity.this, HomeActivity.class);
                it.putExtra("city",g_city);
                startActivity(it);
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

    public void onCreate() {
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        option.setScanSpan(1000);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIgnoreKillProcess(false);
//        option.setIgnoreCacheException(false);
//        option.setWifiValidTime(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
        mLocationClient.setLocOption(option);
        option.setIsNeedAddress(true);
//可选，是否需要地址信息，默认为不需要，即参数为false
//如果开发者需要获得当前点的地址信息，此处必须为true
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    private class MyLocationListener extends BDAbstractLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            g_city = city;
        }
    }
}
