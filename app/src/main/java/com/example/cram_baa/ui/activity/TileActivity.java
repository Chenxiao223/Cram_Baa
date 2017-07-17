package com.example.cram_baa.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.cram_baa.R;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class TileActivity {
    public TextView tv_title;
    public Button btn_left,btn_right;
    public TileActivity(View view){
        tv_title= (TextView) view.findViewById(R.id.tv_title);
        btn_left= (Button) view.findViewById(R.id.btn_left);
        btn_right= (Button) view.findViewById(R.id.btn_right);
    }
}
