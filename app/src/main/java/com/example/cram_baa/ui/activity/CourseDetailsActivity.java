package com.example.cram_baa.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.cram_baa.R;
import com.example.cram_baa.util.SYBViewPager;
import com.example.cram_baa.util.SYBViewPagerCotroller;

import java.util.ArrayList;

/**
 * Created by chenxiao on 2017/10/14.
 * 课程详情页
 */
public class CourseDetailsActivity extends BaseActivity implements View.OnClickListener {
    String TAG  = "SYBViewPager";
    view2Cotroller1 cot1;
    view2Cotroller2 cot2;
    view2Cotroller3 cot3;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);
        //
        initView();
    }

    private void initView() {
        TileActivity tileActivity=new TileActivity(getWindow().getDecorView());
        tileActivity.tv_title.setText("课程详情");
        tileActivity.iv_left.setImageResource(R.drawable.back);
        tileActivity.tv_left.setVisibility(View.GONE);
        tileActivity.btn_right.setVisibility(View.GONE);
        tileActivity.line_left.setOnClickListener(this);

        SYBViewPager myviewpager = (SYBViewPager) findViewById(R.id.myiewpager);
        cot1 = new view2Cotroller1(this);
        cot2 = new view2Cotroller2(this);
        cot3 = new view2Cotroller3(this);

        //增加view
        ArrayList<SYBViewPagerCotroller> views = new ArrayList<SYBViewPagerCotroller>();
        views.add(cot1);
        views.add(cot2);
        views.add(cot3);
        myviewpager.setViews(views, 0);


        myviewpager.setOnPageChangeListener(new SYBViewPager.OnPageChange() {
            @Override
            public void onPageSelected(int currindex) {
                //初始化时不会触发
                Log.i(TAG, "onPageSelected:" + currindex);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_left:
                finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        cot1.dosth();
        cot2.dosth();
        cot3.dosth();
        super.onDestroy();
    }

    class view2Cotroller1 extends SYBViewPagerCotroller {
        private Activity mactivity;
        private View mview;
        public view2Cotroller1(Activity activity) {
            super(activity);
            mactivity = activity;
            mview = LayoutInflater.from(mactivity).inflate(R.layout.item_view1, null);
//            mview.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(MainActivity.this,Activity2.class));
//
//                }
//            });
        }

        @Override
        public View getView() {
            return mview;
        }

        @Override
        public String getTitle() {
            return "课程简介";
        }

        @Override
        public void onshow() {
        }


        public void dosth() {
        }

    }


    class view2Cotroller2 extends SYBViewPagerCotroller {
        private Activity mactivity;
        private View mview;
        public view2Cotroller2(Activity activity) {
            super(activity);
            mactivity = activity;
            mview = LayoutInflater.from(mactivity).inflate(R.layout.item_view2, null);
        }

        @Override
        public View getView() {
            return mview;
        }

        @Override
        public String getTitle() {
            return "课程目录";
        }

        @Override
        public void onshow() {
        }

        public void dosth() {
        }

    }



    class view2Cotroller3 extends SYBViewPagerCotroller {
        private Activity mactivity;
        private View mview;
        public view2Cotroller3(Activity activity) {
            super(activity);
            mactivity = activity;
            mview = LayoutInflater.from(mactivity).inflate(R.layout.item_view3, null);
        }

        @Override
        public View getView() {
            return mview;
        }

        @Override
        public String getTitle() {
            return "授课教师";
        }

        @Override
        public void onshow() {
        }


        public void dosth() {
        }
    }
}
