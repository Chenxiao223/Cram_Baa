package com.example.cram_baa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cram_baa.R;
import com.example.cram_baa.adapter.AdapterSchedule;
import com.example.cram_baa.util.MonthDateView;

import java.util.ArrayList;
import java.util.HashMap;
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
    private ListView list_item;
    private AdapterSchedule adapterSchedule;
    private ArrayList<HashMap<String,String>> list_schedule=new ArrayList<>();
    private HashMap<String,String> hashMap;

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
        list_item= (ListView) getView().findViewById(R.id.list_item);
        addData();//添加数据
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

        adapterSchedule=new AdapterSchedule(getActivity(),list_schedule);
        list_item.setAdapter(adapterSchedule);
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

    public void addData(){
        hashMap=new HashMap<>();
        for (int i=0;i<3;i++) {
            hashMap.put("content1", "【高中数学】高三知识点强化");
            hashMap.put("content2", "一对一");
            hashMap.put("content3", "10月5日");
            hashMap.put("content4", "19:30-20:30");
            hashMap.put("content5", "一课时");
            hashMap.put("content6", "赵老师");
            hashMap.put("content7", "苏仙北路3号601室");
            list_schedule.add(hashMap);
        }
    }
}
