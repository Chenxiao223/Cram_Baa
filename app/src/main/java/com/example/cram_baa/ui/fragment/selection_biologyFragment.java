package com.example.cram_baa.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.cram_baa.R;
import com.example.cram_baa.adapter.SelectionAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class selection_biologyFragment extends Fragment {
    private ListView lv_biology;
    private ArrayList<HashMap<String,String>> list_selection=new ArrayList<>();
    private SelectionAdapter selectionAdapter;
    private HashMap<String,String> hashMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selection_biology,container,false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    public void initView(){
        lv_biology= (ListView) getView().findViewById(R.id.lv_biology);
        hashMap=new HashMap<>();
        for (int i=0;i<6;i++){
            hashMap.put("content1", "【高中数学】高三知识点强化");
            hashMap.put("content2", "适合高三下学期的同学选修");
            hashMap.put("content3", "周老师");
            hashMap.put("content4", "100");
            list_selection.add(hashMap);
        }
        selectionAdapter =new SelectionAdapter(getActivity(),list_selection);
        lv_biology.setAdapter(selectionAdapter);
    }
}
