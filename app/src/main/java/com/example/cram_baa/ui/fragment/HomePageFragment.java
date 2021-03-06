package com.example.cram_baa.ui.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cram_baa.R;
import com.example.cram_baa.ui.activity.HomeActivity;
import com.example.cram_baa.ui.activity.LoginActivity;
import com.example.cram_baa.ui.activity.TileActivity;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.xys.libzxing.zxing.activity.CaptureActivity;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {
    private RollPagerView mRollViewPager;
    private TextView tv_yuwen, tv_english, tv_math, tv_history, tv_physics, tv_chemistry, tv_biology, tv_more;
    private LinearLayout line_sign_in, line_class_schedule;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_homepage, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //
        initView();
    }

    public void initView() {
        line_class_schedule = (LinearLayout) getView().findViewById(R.id.line_class_schedule);
        line_sign_in = (LinearLayout) getView().findViewById(R.id.line_sign_in);
        TileActivity tileActivity = new TileActivity(getActivity().getWindow().getDecorView());
        tileActivity.tv_title.setText("阿咩爱补习");
        tileActivity.tv_left.setText(HomeActivity.homeActivity.g_city);
        tileActivity.btn_right.setText("登录");
        tileActivity.btn_right.setOnClickListener(this);
        //获取手机屏幕的宽度,并计算平均宽
        DisplayMetrics dm = getResources().getDisplayMetrics();
        int height = dm.widthPixels / 4 - 19;
        System.out.println("高度：" + dm.heightPixels);
        tv_yuwen = (TextView) getView().findViewById(R.id.iv_yuwen);
        tv_english = (TextView) getView().findViewById(R.id.iv_english);
        tv_math = (TextView) getView().findViewById(R.id.iv_math);
        tv_history = (TextView) getView().findViewById(R.id.iv_history);

        tv_physics = (TextView) getView().findViewById(R.id.iv_physics);
        tv_chemistry = (TextView) getView().findViewById(R.id.iv_chemistry);
        tv_biology = (TextView) getView().findViewById(R.id.iv_biology);
        tv_more = (TextView) getView().findViewById(R.id.iv_more);

        tv_yuwen.setOnClickListener(this);
        tv_english.setOnClickListener(this);
        tv_math.setOnClickListener(this);
        tv_history.setOnClickListener(this);

        tv_physics.setOnClickListener(this);
        tv_chemistry.setOnClickListener(this);
        tv_biology.setOnClickListener(this);
        tv_more.setOnClickListener(this);
        //设置图片和text之间的间距
        tv_yuwen.setCompoundDrawablePadding(2);
        tv_english.setCompoundDrawablePadding(2);
        tv_math.setCompoundDrawablePadding(2);
        tv_history.setCompoundDrawablePadding(2);
        //获取当前控件的布局对象
        LinearLayout.LayoutParams p_iv_yuwen = (LinearLayout.LayoutParams) tv_yuwen.getLayoutParams();
        p_iv_yuwen.height = height;//设置当前控件布局的高度
        LinearLayout.LayoutParams p_iv_english = (LinearLayout.LayoutParams) tv_english.getLayoutParams();
        p_iv_english.height = height;
        LinearLayout.LayoutParams p_iv_math = (LinearLayout.LayoutParams) tv_math.getLayoutParams();
        p_iv_math.height = height;
        LinearLayout.LayoutParams p_iv_history = (LinearLayout.LayoutParams) tv_history.getLayoutParams();
        p_iv_history.height = height;

        LinearLayout.LayoutParams p_iv_physics = (LinearLayout.LayoutParams) tv_physics.getLayoutParams();
        p_iv_physics.height = height;//设置当前控件布局的高度
        LinearLayout.LayoutParams p_iv_chemistry = (LinearLayout.LayoutParams) tv_chemistry.getLayoutParams();
        p_iv_chemistry.height = height;
        LinearLayout.LayoutParams p_iv_biology = (LinearLayout.LayoutParams) tv_biology.getLayoutParams();
        p_iv_biology.height = height;
        LinearLayout.LayoutParams p_iv_more = (LinearLayout.LayoutParams) tv_more.getLayoutParams();
        p_iv_more.height = height;

        //轮播图
        mRollViewPager = (RollPagerView) getView().findViewById(R.id.roll_view_pager);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(3000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());
        //设置指示器（顺序依次）
        //自定义指示器图片
        //设置圆点指示器颜色
        //设置文字指示器
        //隐藏指示器
        //mRollViewPager.setHintView(new IconHintView(this, R.drawable.point_focus, R.drawable.point_normal));
        mRollViewPager.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.WHITE));

        line_class_schedule.setOnClickListener(this);
        line_sign_in.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_class_schedule:
                HomeActivity.homeActivity.pager.setCurrentItem(3);
                HomeActivity.homeActivity.changeColor(false, false, false, true, false);
                break;
            case R.id.line_sign_in:
                //扫描
                Intent it = new Intent();
                it.setClass(getActivity(), CaptureActivity.class);
                //返回一个二维码的信息
                startActivityForResult(it, 99);
                ;
                break;
            case R.id.btn_right:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
            case R.id.iv_yuwen:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                //定位到语文碎片
                selectionFragment.selectionFragment.selectpager.setCurrentItem(1);
                selectionFragment.selectionFragment.changeColor(false, true, false, false, false, false, false, false, false, false);
                break;
            case R.id.iv_english:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                //定位到英语碎片
                selectionFragment.selectionFragment.selectpager.setCurrentItem(3);
                selectionFragment.selectionFragment.changeColor(false, false, false, true, false, false, false, false, false, false);
                break;
            case R.id.iv_math:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                //定位到数学碎片
                selectionFragment.selectionFragment.selectpager.setCurrentItem(2);
                selectionFragment.selectionFragment.changeColor(false, false, true, false, false, false, false, false, false, false);
                break;
            case R.id.iv_history:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                //定位到历史碎片
                selectionFragment.selectionFragment.selectpager.setCurrentItem(7);
                selectionFragment.selectionFragment.changeColor(false, false, false, false, false, false, false, true, false, false);
                break;
            case R.id.iv_physics:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                //定位到物理碎片
                selectionFragment.selectionFragment.selectpager.setCurrentItem(4);
                selectionFragment.selectionFragment.changeColor(false, false, false, false, true, false, false, false, false, false);
                break;
            case R.id.iv_chemistry:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                //定位到化学碎片
                selectionFragment.selectionFragment.selectpager.setCurrentItem(5);
                selectionFragment.selectionFragment.changeColor(false, false, false, false, false, true, false, false, false, false);
                break;
            case R.id.iv_biology:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                //定位到生物碎片
                selectionFragment.selectionFragment.selectpager.setCurrentItem(6);
                selectionFragment.selectionFragment.changeColor(false, false, false, false, false, false, true, false, false, false);
                break;
            case R.id.iv_more:
                HomeActivity.homeActivity.pager.setCurrentItem(1);
                HomeActivity.homeActivity.changeColor(false, true, false, false, false);
                break;
        }
    }

    private class TestNormalAdapter extends StaticPagerAdapter {
        private int[] imgs = {
                R.drawable.img5,
                R.drawable.img2,
                R.drawable.img3,
                R.drawable.img4,
        };

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setImageResource(imgs[position]);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getCount() {
            return imgs.length;
        }
    }
}
