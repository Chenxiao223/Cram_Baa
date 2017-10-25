package com.example.cram_baa.util;

import android.app.Activity;
import android.view.View;

/**
 *
 * @author ping
 * 
 */
public abstract class SYBViewPagerCotroller {
	public SYBViewPagerCotroller(Activity activity) {
	};

	public abstract View getView();// viewpager获取view

	public abstract String getTitle();// viewpager获取标题名称

	public abstract void onshow();// 显示界面触发事件
}
