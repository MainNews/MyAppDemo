package com.example.hank.myappdemo.map.base;

import java.util.ArrayList;

/**
 * 实现Fragment所需要实现的方法
 */
public interface BaseFragmentInterface {

	/**
	 * 给Adapter添加数据
     */
	public void setAdapterDatas(ArrayList<Integer> routeLineDistanceList, ArrayList<Integer>
			routeLinerDurationList);
}