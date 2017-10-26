package com.example.cram_baa.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.cram_baa.R;
import com.example.cram_baa.adapter.SelectionFragmentAdapter;

/**
 * Created by chenxiao on 2017/10/26.
 */
public class MyTeacherActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_all, btn_chinese, btn_math, btn_english, btn_physics, btn_chemistry, btn_biology, btn_history,
            btn_geography, btn_political;
    private SelectionFragmentAdapter selectionFragmentAdapter = null;
    private ViewPager selectpager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_teacher);

        initView();
    }

    private void initView() {
        TileActivity tileActivity = new TileActivity(getWindow().getDecorView());
        tileActivity.tv_title.setText("我的老师");
        tileActivity.iv_left.setImageResource(R.drawable.back);
        tileActivity.tv_left.setVisibility(View.GONE);
        tileActivity.btn_right.setVisibility(View.GONE);
        tileActivity.line_left.setOnClickListener(this);

        btn_all = (Button) findViewById(R.id.btn_all);
        btn_chinese = (Button) findViewById(R.id.btn_chinese);
        btn_math = (Button) findViewById(R.id.btn_math);
        btn_english = (Button) findViewById(R.id.btn_english);
        btn_physics = (Button) findViewById(R.id.btn_physics);
        btn_chemistry = (Button) findViewById(R.id.btn_chemistry);
        btn_biology = (Button) findViewById(R.id.btn_biology);
        btn_history = (Button) findViewById(R.id.btn_history);
        btn_geography = (Button) findViewById(R.id.btn_geography);
        btn_political = (Button) findViewById(R.id.btn_political);

        btn_all.setOnClickListener(this);
        btn_chinese.setOnClickListener(this);
        btn_math.setOnClickListener(this);
        btn_english.setOnClickListener(this);
        btn_physics.setOnClickListener(this);
        btn_chemistry.setOnClickListener(this);
        btn_biology.setOnClickListener(this);
        btn_history.setOnClickListener(this);
        btn_geography.setOnClickListener(this);
        btn_political.setOnClickListener(this);

        selectpager = (ViewPager) findViewById(R.id.selectpager);
        selectionFragmentAdapter = new SelectionFragmentAdapter(getSupportFragmentManager());
        selectpager.setAdapter(selectionFragmentAdapter);
        selectpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    changeColor(true, false, false, false, false, false, false, false, false, false);
                } else if (position == 1) {
                    changeColor(false, true, false, false, false, false, false, false, false, false);
                } else if (position == 2) {
                    changeColor(false, false, true, false, false, false, false, false, false, false);
                } else if (position == 3) {
                    changeColor(false, false, false, true, false, false, false, false, false, false);
                } else if (position == 4) {
                    changeColor(false, false, false, false, true, false, false, false, false, false);
                } else if (position == 5) {
                    changeColor(false, false, false, false, false, true, false, false, false, false);
                } else if (position == 6) {
                    changeColor(false, false, false, false, false, false, true, false, false, false);
                } else if (position == 7) {
                    changeColor(false, false, false, false, false, false, false, true, false, false);
                } else if (position == 8) {
                    changeColor(false, false, false, false, false, false, false, false, true, false);
                } else {
                    changeColor(false, false, false, false, false, false, false, false, false, true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //一进来就显示首页的fragment
        selectpager.setCurrentItem(0);
        changeColor(true, false, false, false, false, false, false, false, false, false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_left:
                finish();
                break;
            case R.id.btn_all:
                selectpager.setCurrentItem(0);
                changeColor(true, false, false, false, false, false, false, false, false, false);
                break;
            case R.id.btn_chinese:
                selectpager.setCurrentItem(1);
                changeColor(false, true, false, false, false, false, false, false, false, false);
                break;
            case R.id.btn_math:
                selectpager.setCurrentItem(2);
                changeColor(false, false, true, false, false, false, false, false, false, false);
                break;
            case R.id.btn_english:
                selectpager.setCurrentItem(3);
                changeColor(false, false, false, true, false, false, false, false, false, false);
                break;
            case R.id.btn_physics:
                selectpager.setCurrentItem(4);
                changeColor(false, false, false, false, true, false, false, false, false, false);
                break;
            case R.id.btn_chemistry:
                selectpager.setCurrentItem(5);
                changeColor(false, false, false, false, false, true, false, false, false, false);
                break;
            case R.id.btn_biology:
                selectpager.setCurrentItem(6);
                changeColor(false, false, false, false, false, false, true, false, false, false);
                break;
            case R.id.btn_history:
                selectpager.setCurrentItem(7);
                changeColor(false, false, false, false, false, false, false, true, false, false);
                break;
            case R.id.btn_geography:
                selectpager.setCurrentItem(8);
                changeColor(false, false, false, false, false, false, false, false, true, false);
                break;
            case R.id.btn_political:
                selectpager.setCurrentItem(9);
                changeColor(false, false, false, false, false, false, false, false, false, true);
                break;
        }
    }

    public void changeColor(boolean all, boolean chinese, boolean math, boolean english, boolean physics, boolean chemistry,
                            boolean biology, boolean history, boolean geography, boolean political) {
        if (all) {
            btn_all.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_all.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (chinese) {
            btn_chinese.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_chinese.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (math) {
            btn_math.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_math.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (english) {
            btn_english.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_english.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (physics) {
            btn_physics.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_physics.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (chemistry) {
            btn_chemistry.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_chemistry.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (biology) {
            btn_biology.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_biology.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (history) {
            btn_history.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_history.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (geography) {
            btn_geography.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_geography.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }

        if (political) {
            btn_political.setTextColor(this.getResources().getColor(R.color.titlebackgroup));
        } else {
            btn_political.setTextColor(this.getResources().getColor(R.color.colorBlack));
        }
    }
}
