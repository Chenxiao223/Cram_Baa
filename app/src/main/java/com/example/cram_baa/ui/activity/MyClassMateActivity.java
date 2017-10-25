package com.example.cram_baa.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.cram_baa.R;
import com.example.cram_baa.adapter.MyClassMateAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by chenxiao on 2017/10/24.
 */
public class MyClassMateActivity extends BaseActivity implements View.OnClickListener {
    private ListView lv_classmate;
    private MyClassMateAdapter adapter=null;
    private ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
    private HashMap<String,String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclassmate);
        
        initView();
    }

    private void initView() {
        //设置标题栏
        TileActivity tileActivity=new TileActivity(getWindow().getDecorView());
        tileActivity.tv_title.setText("我的同学");
        tileActivity.btn_right.setVisibility(View.GONE);
        tileActivity.iv_left.setImageResource(R.drawable.back);
        tileActivity.tv_left.setVisibility(View.INVISIBLE);
        tileActivity.line_left.setOnClickListener(this);

        lv_classmate= (ListView) findViewById(R.id.lv_classmate);
        addData();
        adapter=new MyClassMateAdapter(this,arrayList);
        lv_classmate.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.line_left:
                finish();
                break;
        }
    }

    public void addData(){
        hashMap=new HashMap<>();
        for (int i=0;i<3;i++) {
            hashMap.put("content1", "周磊");
            hashMap.put("content2", "郴州市二中235班");
            hashMap.put("content3", "18221119714");
            arrayList.add(hashMap);
        }
    }
}
