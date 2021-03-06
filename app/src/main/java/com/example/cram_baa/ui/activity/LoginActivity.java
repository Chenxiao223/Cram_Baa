package com.example.cram_baa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cram_baa.R;

/**
 * Created by chenxiao on 2017/10/25.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        TileActivity tileActivity=new TileActivity(getWindow().getDecorView());
        tileActivity.tv_title.setText("免注册登录");
        tileActivity.iv_left.setImageResource(R.drawable.back);
        tileActivity.tv_left.setVisibility(View.GONE);
        tileActivity.btn_right.setVisibility(View.GONE);
        tileActivity.line_left.setOnClickListener(this);

        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_left:
                finish();
                break;
            case R.id.btn_login:
                finish();
                HomeActivity.homeActivity.pager.setCurrentItem(4);
                HomeActivity.homeActivity.changeColor(false, false, false, false, true);
                break;
        }
    }
}
