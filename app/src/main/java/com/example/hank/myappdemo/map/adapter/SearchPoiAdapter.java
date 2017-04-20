package com.example.hank.myappdemo.map.adapter;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.example.hank.myappdemo.R;
import java.util.ArrayList;
import java.util.List;

import static com.baidu.mapapi.BMapManager.getContext;

/**
 * Created by Jun on 2017/4/19.
 */

public class SearchPoiAdapter extends BaseAdapter{

    private List<PoiInfo> poiInfosList = new ArrayList<>();
    public SearchPoiAdapter(List<PoiInfo> poiInfosList){
        this.poiInfosList = poiInfosList;
    }
    public void setPoiInfosList(List<PoiInfo> poiInfosList){
        this.poiInfosList = poiInfosList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return poiInfosList.size();
    }

    @Override
    public Object getItem(int position) {
        return poiInfosList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PoiInfo poiBean = (PoiInfo) getItem(position);//取得Bean实例
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.map_search_poi_list_item, parent, false);
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
