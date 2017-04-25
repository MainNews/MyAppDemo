package com.example.hank.myappdemo.map.fragment;


import com.example.hank.myappdemo.map.base.BaseFragment;
import com.example.hank.myappdemo.map.base.BaseMAPPathFragment;

/**
 * 底部功能模块的枚举
 * @author ThinkPad
 *
 */
public enum MainTab {
	DRIVING(1,"自驾","DRIVING",DrivingFragment.class),
	TRANSIT(2,"公交","TRANSIT",TransitFragment.class),
	WAILKING(3,"步行","WAILKING",WailkingFragment.class);
	private MainTab(int id, String title, String tag,
			Class<? extends BaseMAPPathFragment> clz) {
		this.id = id;
		this.title = title;
		this.tag = tag;
		this.clz = clz;
	}
	private int id;
	private String title;//标题
	private String tag;//标记
	private Class<? extends BaseMAPPathFragment> clz;
	
	
	public int getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getTag() {
		return tag;
	}
	public Class<? extends BaseMAPPathFragment> getClz() {
		return clz;
	}
	
	
}