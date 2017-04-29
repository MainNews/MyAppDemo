package com.example.hank.myappdemo.mveiw.animation.draw;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by Jun on 2017/4/29.
 * 该自定义控件类，主要是提供一个可以将Char类型的字符设置在该文本控件中
 */

public class MyCharTextView extends TextView {
    public MyCharTextView(Context context) {
        super(context);
    }
    public MyCharTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCharText(Character text){
        setText(String.valueOf(text));
    }

}
