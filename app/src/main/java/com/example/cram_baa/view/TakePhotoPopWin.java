package com.example.cram_baa.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cram_baa.R;
import com.example.cram_baa.ui.fragment.MyFragment;

import java.io.File;

/**
 * Created by Administrator on 2016/12/19 0019.
 */
public class TakePhotoPopWin extends PopupWindow implements View.OnClickListener {
    private Context mContext;
    private View view;
    private TextView tv_photograph,tv_photo_album;

    public TakePhotoPopWin(final Context mContext, View.OnClickListener itemsOnClick, int flag) {
        this.mContext = mContext;
        this.view = LayoutInflater.from(mContext).inflate(R.layout.view_pictrue, null);
        initView();
        // 设置外部可点击
        this.setOutsideTouchable(true);
        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);//高
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);//宽

        // 设置弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(false);
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
//        // 设置弹出窗体的背景
//        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.take_photo_anim);
    }

    public void initView(){
        tv_photograph= (TextView) view.findViewById(R.id.tv_photograph);
        tv_photo_album= (TextView) view.findViewById(R.id.tv_photo_album);
        tv_photograph.setOnClickListener(this);
        tv_photo_album.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_photograph://拍照
                MyFragment.fragment.photograph();
                dismiss();
                break;
            case R.id.tv_photo_album://相册
                MyFragment.fragment.photoAlbum();
                dismiss();
                break;
        }
    }


}