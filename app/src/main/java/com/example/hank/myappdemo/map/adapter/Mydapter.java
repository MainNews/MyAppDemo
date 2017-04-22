package com.example.hank.myappdemo.map.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hank.myappdemo.R;

import java.util.ArrayList;

import static com.baidu.mapapi.BMapManager.getContext;

public class Mydapter extends BaseAdapter {
    /**
     * 记录路线长度，以“米”为单位
     */
    ArrayList<Integer> routeLineDistanceList = new ArrayList<>();
    /**
     * 记录路线耗时，以“秒为单位”
     */
    ArrayList<Integer> routeLinerDurationList = new ArrayList<>();

    public void setRouteLineList(ArrayList<Integer> routeLineDistanceList, ArrayList<Integer>
            routeLinerDurationList) {
        this.routeLinerDurationList = routeLinerDurationList;
        this.routeLineDistanceList = routeLineDistanceList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return routeLineDistanceList.size();
    }

    @Override
    public Object getItem(int position) {
        return routeLineDistanceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.map_rout_result_item,
                    viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.routeLineDistance = (TextView) view.findViewById(R.id.item_distance);
            viewHolder.routeLinerDuration = (TextView) view.findViewById(R.id.item_duration);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.routeLineDistance.setText(distance(routeLineDistanceList.get(position)));
        viewHolder.routeLinerDuration.setText(duration(routeLinerDurationList.get(position)));
        return view;
    }

    /**
     * 计算总长度
     */
    private String distance(int dis) {
        if (dis < 1000) {
            return "总长：" + dis + " 米";
        } else {
            return "总长：" + dis / 1000 + " 公里";
        }
    }

    /**
     * 计算耗时量
     */
    private String duration(int dur) {
        if (dur < 60) {
            return "总用时：" + dur + " 秒";
        } else if (dur < 360 && dur >= 60) {
            return "总长：" + dur / 60 + " 分钟";
        } else {
            return "总长：" + dur / 60 / 60 + " 小时";
        }
    }

    class ViewHolder {
        TextView routeLineDistance;
        TextView routeLinerDuration;
    }
}