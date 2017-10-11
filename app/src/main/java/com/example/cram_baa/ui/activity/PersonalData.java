package com.example.cram_baa.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.example.cram_baa.R;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
public class PersonalData extends BaseActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        //
        initView();
    }

    private void initView() {
        TileActivity tileActivity=new TileActivity(getWindow().getDecorView());
        tileActivity.tv_title.setText("个人资料");
        tileActivity.btn_right.setVisibility(View.GONE);
        tileActivity.iv_left.setImageResource(R.drawable.back);
        tileActivity.tv_left.setVisibility(View.INVISIBLE);
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
