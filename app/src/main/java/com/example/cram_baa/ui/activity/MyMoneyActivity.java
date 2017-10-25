package com.example.cram_baa.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.cram_baa.R;

/**
 * Created by chenxiao on 2017/10/25.
 * 我的余额页面
 */
public class MyMoneyActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_money);

        initView();
    }

    private void initView() {
        TileActivity tileActivity=new TileActivity(getWindow().getDecorView());
        tileActivity.tv_title.setText("我的学费");
        tileActivity.iv_left.setImageResource(R.drawable.back);
        tileActivity.tv_left.setVisibility(View.GONE);
        tileActivity.btn_right.setVisibility(View.GONE);
        tileActivity.line_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_left:
                finish();
                break;
        }
    }
}
