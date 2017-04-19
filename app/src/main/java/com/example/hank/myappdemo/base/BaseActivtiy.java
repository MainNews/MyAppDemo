package com.example.hank.myappdemo.base;

import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Jun on 2017/4/19.
 */

public class BaseActivtiy extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = ExampleApplication.getRefWatcher(this);
        refWatcher.watch(this);
    }
}
