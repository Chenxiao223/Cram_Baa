package com.example.cram_baa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cram_baa.R;
import com.example.cram_baa.util.MonthDateView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class ScheduleFragment extends Fragment {
    private ImageView iv_left;
    private ImageView iv_right;
    private TextView tv_date;
    private TextView tv_week;
    private TextView tv_today;
    private MonthDateView monthDateView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_schedule,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(12);
        list.add(15);
        list.add(16);
        iv_left = (ImageView) getView().findViewById(R.id.iv_left);
        iv_right = (ImageView) getView().findViewById(R.id.iv_right);
        monthDateView = (MonthDateView) getView().findViewById(R.id.monthDateView);
        tv_date = (TextView) getView().findViewById(R.id.date_text);
        tv_week  =(TextView) getView().findViewById(R.id.week_text);
        tv_today = (TextView) getView().findViewById(R.id.tv_today);
        monthDateView.setTextView(tv_date,tv_week);
        monthDateView.setDaysHasThingList(list);
        monthDateView.setDateClick(new MonthDateView.DateClick() {

            @Override
            public void onClickOnDate() {
                Toast.makeText(getContext(), "点击了：" + monthDateView.getmSelDay(), Toast.LENGTH_SHORT).show();
            }
        });
        setOnlistener();
    }

    private void setOnlistener(){
        iv_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onLeftClick();
            }
        });

        iv_right.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.onRightClick();
            }
        });

        tv_today.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                monthDateView.setTodayToView();
            }
        });
    }
}
