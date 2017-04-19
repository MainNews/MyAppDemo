package com.example.hank.myappdemo.map.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.search.core.PoiInfo;
import com.example.hank.myappdemo.R;
import com.example.hank.myappdemo.map.bean.PoiBean;

import java.util.List;

/**
 * Created by Jun on 2017/4/19.
 */

public class SearchPoiAdapter extends ArrayAdapter<PoiInfo> {
    private int resourceId;
    public SearchPoiAdapter(Context context, int textViewResourceId, List<PoiInfo> objects) {
        super(context, textViewResourceId, objects);
        this.resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PoiInfo poiBean = getItem(position);//取得Bean实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.poiItemAddress = (TextView) view.findViewById(R.id.map_search_poi_item_address);
            viewHolder.poiItemName = (TextView) view.findViewById(R.id.map_search_poi_item_name);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.poiItemAddress.setText(poiBean.address);
        viewHolder.poiItemName.setText("城市："+poiBean.city+" "+poiBean.name);
        return view;
    }
    class ViewHolder{
        TextView poiItemAddress;
        TextView poiItemName;
    }
}
