package com.example.cram_baa.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.cram_baa.ui.fragment.selection_allFragment;
import com.example.cram_baa.ui.fragment.selection_biologyFragment;
import com.example.cram_baa.ui.fragment.selection_chemistryFragment;
import com.example.cram_baa.ui.fragment.selection_chineseFragment;
import com.example.cram_baa.ui.fragment.selection_englishFragment;
import com.example.cram_baa.ui.fragment.selection_geographyFragment;
import com.example.cram_baa.ui.fragment.selection_historyFragment;
import com.example.cram_baa.ui.fragment.selection_mathFragment;
import com.example.cram_baa.ui.fragment.selection_physicsFragment;
import com.example.cram_baa.ui.fragment.selection_politicalFragment;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class SelectionFragmentAdapter extends FragmentPagerAdapter {
    public SelectionFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new selection_allFragment();
        } else if (position == 1) {
            return new selection_chineseFragment();
        } else if (position == 2) {
            return new selection_mathFragment();
        } else if (position == 3) {
            return new selection_englishFragment();
        } else if (position == 4) {
            return new selection_physicsFragment();
        } else if (position == 5) {
            return new selection_chemistryFragment();
        } else if (position == 6) {
            return new selection_biologyFragment();
        } else if (position == 7) {
            return new selection_historyFragment();
        } else if (position == 8) {
            return new selection_geographyFragment();
        } else {
            return new selection_politicalFragment();
        }
    }

    @Override
    public int getCount() {
        return 10;
    }
}
