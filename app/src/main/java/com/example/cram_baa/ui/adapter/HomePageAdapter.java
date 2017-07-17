package com.example.cram_baa.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cram_baa.ui.fragment.HomePageFragment;
import com.example.cram_baa.ui.fragment.MyFragment;
import com.example.cram_baa.ui.fragment.ScheduleFragment;
import com.example.cram_baa.ui.fragment.Sign_In_Fragment;
import com.example.cram_baa.ui.fragment.StoreFragment;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class HomePageAdapter extends FragmentPagerAdapter {
    public HomePageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return new HomePageFragment();
        }else if(position==1){
            return new StoreFragment();
        }else if(position==2){
            return new Sign_In_Fragment();
        }else if(position==3){
            return new ScheduleFragment();
        }else {
            return new MyFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
