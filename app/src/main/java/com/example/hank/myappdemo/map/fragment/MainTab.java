package com.example.hank.myappdemo.map.fragment;

import android.support.v4.app.Fragment;

import com.example.hank.myappdemo.map.base.BaseFragment;

/**
 * 底部功能模块的枚举
 * @author ThinkPad
 *
 */
public enum MainTab {
	TAB_NEWS(1,"自驾","driving",DrivingFragment.class),
	TAB_TWEEN(2,"公交","transit",TransitFragment.class),
	TAB_YOU(3,"步行","wailking",WailkingFragment.class);
	private MainTab(int id, String title, String tag,
			Class<? extends BaseFragment> clz) {
		this.id = id;
		this.title = title;
		this.tag = tag;
		this.clz = clz;
	}
	private int id;
	private String title;//标题
	private String tag;//标记
	private Class<? extends BaseFragment> clz;
	
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getTag() {
		return tag;
	}
	public Class<? extends BaseFragment> getClz() {
		return clz;
	}
	
	
}