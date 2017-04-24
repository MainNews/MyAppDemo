package com.example.hank.myappdemo.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jun on 2017/4/23.
 */
public class TestFragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int[] datas = bundle.getIntArray("datas");
        TextView textView = new TextView(getContext());
        textView.setText("试试"+datas[0]);
        textView.setTextSize(30);
        return textView;
    }
}
