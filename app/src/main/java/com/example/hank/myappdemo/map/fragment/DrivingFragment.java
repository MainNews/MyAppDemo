package com.example.hank.myappdemo.map.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.MAPActivity;
import com.example.hank.myappdemo.map.adapter.Mydapter;
import com.example.hank.myappdemo.map.base.BaseFragment;
import com.example.hank.myappdemo.map.mapPresenter.LoactionGetPresenter;

import java.util.ArrayList;

/**
 * Created by Jun on 2017/4/22.
 * 展示自驾路线
 */

public class DrivingFragment extends BaseFragment {
    private ListView listView;
    private Mydapter mydapter;
    private MAPActivity mapActivity;
    private View mViewContent;//缓存视图

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapActivity = (MAPActivity) getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (mViewContent == null){
            mViewContent = inflater.inflate(R.layout.map_rout_result_list_layout, container, false);
        }
        // 缓存View判断是否含有parent, 如果有需要从parent删除, 否则发生已有parent的错误.
        ViewGroup parent = (ViewGroup) mViewContent.getParent();
        if (parent != null) {
            parent.removeView(mViewContent);
        }
        listView = (ListView) mViewContent.findViewById(android.R.id.list);
        return mViewContent;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mydapter = new Mydapter();
        listView.setAdapter(mydapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                LoactionGetPresenter loactionGetPresenter = mapActivity.getLocationGetPresenter();
                loactionGetPresenter.showDrivingRoutLine(position);
            }
        });
    }

    /**
     * 设置数据
     */
    @Override
    public void setAdapterDatas(ArrayList<Integer> routeLineDistanceList, ArrayList<Integer>
            routeLinerDurationList) {
            mydapter.setRouteLineList(routeLineDistanceList,routeLinerDurationList);
    }
}
