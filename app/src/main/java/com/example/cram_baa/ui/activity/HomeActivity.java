package com.example.cram_baa.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cram_baa.R;
import com.example.cram_baa.adapter.HomePageAdapter;
import com.xys.libzxing.zxing.activity.CaptureActivity;

/**
 * Created by Administrator on 2017/7/17 0017.
 * 主页面
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener {
    public static HomeActivity homeActivity;
    private LinearLayout lv_homepage, lv_store, lv_sign_in, lv_schedule, lv_my;
    private ImageView iv_homepage, iv_selection, iv_sign_in, iv_schedule, iv_my;
    private TextView tv_homepage, tv_selection, tv_sign_in, tv_schedule, tv_my;
    private HomePageAdapter homePageAdapter = null;
    public ViewPager pager = null;
    public String g_city;
    //退出时的时间
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //
        homeActivity = this;
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {//未获取权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                //当用户第一次申请拒绝时，再次申请该权限调用
                Toast.makeText(this, "拨打电话权限", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 0x01);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.LOCATION_HARDWARE)
                != PackageManager.PERMISSION_GRANTED) {//未获取权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.LOCATION_HARDWARE)) {
                //当用户第一次申请拒绝时，再次申请该权限调用
                Toast.makeText(this, "定位权限", Toast.LENGTH_SHORT).show();
            }
            //申请权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.LOCATION_HARDWARE}, 0x02);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 0x01:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//授权成功

                } else {//授权失败
                    Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT).show();
                }
                break;
//            case 0x02:
//                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//授权成功
//
//                } else {//授权失败
//                    Toast.makeText(this, "获取权限失败", Toast.LENGTH_SHORT).show();
//                }
//                break;
        }
    }

    public void initView() {
        //设置系统栏颜色
        ImageView iv_system = (ImageView) findViewById(R.id.iv_system);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) iv_system.getLayoutParams();
        params.height = (int) getStatusBarHeight(this);//设置当前控件布局的高度

        Intent it = getIntent();
        g_city = it.getStringExtra("city");
        iv_homepage = (ImageView) findViewById(R.id.iv_homepage);
        iv_selection = (ImageView) findViewById(R.id.iv_selection);
        iv_sign_in = (ImageView) findViewById(R.id.iv_sign_in);
        iv_schedule = (ImageView) findViewById(R.id.iv_schedule);
        iv_my = (ImageView) findViewById(R.id.iv_my);

        tv_homepage = (TextView) findViewById(R.id.tv_homepage);
        tv_selection = (TextView) findViewById(R.id.tv_selection);
        tv_sign_in = (TextView) findViewById(R.id.tv_sign_in);
        tv_schedule = (TextView) findViewById(R.id.tv_schedule);
        tv_my = (TextView) findViewById(R.id.tv_my);

        lv_homepage = (LinearLayout) findViewById(R.id.lv_homepage);
        lv_store = (LinearLayout) findViewById(R.id.lv_store);
        lv_sign_in = (LinearLayout) findViewById(R.id.lv_sign_in);
        lv_schedule = (LinearLayout) findViewById(R.id.lv_schedule);
        lv_my = (LinearLayout) findViewById(R.id.lv_my);
        lv_homepage.setOnClickListener(this);
        lv_store.setOnClickListener(this);
        lv_sign_in.setOnClickListener(this);
        lv_schedule.setOnClickListener(this);
        lv_my.setOnClickListener(this);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(4);//
        homePageAdapter = new HomePageAdapter(getSupportFragmentManager());
        pager.setAdapter(homePageAdapter);

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    changeColor(true, false, false, false, false);
                } else if (position == 1) {
                    changeColor(false, true, false, false, false);
                } else if (position == 2) {
                    changeColor(false, false, true, false, false);
                } else if (position == 3) {
                    changeColor(false, false, false, true, false);
                } else {
                    changeColor(false, false, false, false, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //一进来就显示首页的fragment
        pager.setCurrentItem(0);
        changeColor(true, false, false, false, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_homepage:
                pager.setCurrentItem(0);
                changeColor(true, false, false, false, false);
                break;
            case R.id.lv_store:
                pager.setCurrentItem(1);
                changeColor(false, true, false, false, false);
                break;
            case R.id.lv_sign_in:
//                pager.setCurrentItem(2);
//                changeColor(false, false, true, false, false);
                //扫描
                Intent it = new Intent();
                it.setClass(this, CaptureActivity.class);
                //返回一个二维码的信息
                startActivityForResult(it, 99);
                break;
            case R.id.lv_schedule:
                pager.setCurrentItem(3);
                changeColor(false, false, false, true, false);
                break;
            case R.id.lv_my:
                pager.setCurrentItem(4);
                changeColor(false, false, false, false, true);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.get("result").toString();
            //返回二维码扫描的信息
            Toast.makeText(HomeActivity.this, result, Toast.LENGTH_SHORT).show();
            System.out.println(">>>>>>>>>>" + result);
        }
    }

    public void changeColor(boolean homepage, boolean store, boolean sigin_in, boolean schedule, boolean my) {
        if (homepage) {
            iv_homepage.setImageResource(R.drawable.btn_homepage_b);
            tv_homepage.setTextColor(this.getResources().getColor(R.color.yellow));
        } else {
            iv_homepage.setImageResource(R.drawable.btn_homepage);
            tv_homepage.setTextColor(this.getResources().getColor(R.color.gray));
        }

        if (store) {
            iv_selection.setImageResource(R.drawable.btn_selection_b);
            tv_selection.setTextColor(this.getResources().getColor(R.color.yellow));
        } else {
            iv_selection.setImageResource(R.drawable.btn_selection);
            tv_selection.setTextColor(this.getResources().getColor(R.color.gray));
        }

        if (sigin_in) {
            iv_sign_in.setImageResource(R.drawable.btn_sign_in_b);
            tv_sign_in.setTextColor(this.getResources().getColor(R.color.yellow));
        } else {
            iv_sign_in.setImageResource(R.drawable.btn_sign_in);
            tv_sign_in.setTextColor(this.getResources().getColor(R.color.gray));
        }

        if (schedule) {
            iv_schedule.setImageResource(R.drawable.btn_schedule_b);
            tv_schedule.setTextColor(this.getResources().getColor(R.color.yellow));
        } else {
            iv_schedule.setImageResource(R.drawable.btn_schedule);
            tv_schedule.setTextColor(this.getResources().getColor(R.color.gray));
        }

        if (my) {
            iv_my.setImageResource(R.drawable.btn_my_b);
            tv_my.setTextColor(this.getResources().getColor(R.color.yellow));
        } else {
            iv_my.setImageResource(R.drawable.btn_my);
            tv_my.setTextColor(this.getResources().getColor(R.color.gray));
        }
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(this, "再按一次退出阿咩爱补习", Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
