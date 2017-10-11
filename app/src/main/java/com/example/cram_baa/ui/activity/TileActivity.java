package com.example.cram_baa.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.cram_baa.R;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class TileActivity {
    public TextView tv_title,tv_left;
    public Button btn_right;
    public ImageView iv_left;
    public LinearLayout line_left;
    public TileActivity(View view){
        tv_title= (TextView) view.findViewById(R.id.tv_title);
        tv_left= (TextView) view.findViewById(R.id.tv_left);
        btn_right= (Button) view.findViewById(R.id.btn_right);
        iv_left= (ImageView) view.findViewById(R.id.iv_left);
        line_left= (LinearLayout) view.findViewById(R.id.line_left);
    }
}
