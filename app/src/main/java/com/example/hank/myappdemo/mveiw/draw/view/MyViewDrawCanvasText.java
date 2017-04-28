package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.view.View;

/**
 * Created by Jun on 2017/4/28.
 * 该类使用Canvas画出文字效果
 */

public class MyViewDrawCanvasText extends View {
    public MyViewDrawCanvasText(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);//画笔颜色
        paint.setStrokeWidth(5);//画笔宽度
        paint.setAntiAlias(true);//设置是否抗锯齿，如果使用，会使绘图速度变慢
        paint.setTextSize(30);//设置文字大小
        paint.setStyle(Paint.Style.FILL);//设置样式为填充

        //该方法已经被淘汰了，每个文字都要去设置位置，这样会累死。。。
        float posText[] = new float[]{10,30,
                                        10,60,
                                        10,90,
                                        10,120,
                                        10,150,
                                        10,180};
        canvas.drawPosText("指定文字位置",posText,paint);

        /*
            沿路径绘制
         */
        String textStr = "少壮不努力，老大徒伤悲";
        paint.setStyle(Paint.Style.STROKE);//设置样式改为描边
        //先创建两个圆形路径，并先画出两个路径原图
        Path circlePathCCW = new Path();
        circlePathCCW.addCircle(180,200,130, Path.Direction.CW);//逆向绘制
        canvas.drawPath(circlePathCCW,paint);//绘制路径原图

        Path circlePathCW = new Path();
        circlePathCW.addCircle(450,200,130, Path.Direction.CW);//顺时针绘制
        canvas.drawPath(circlePathCW,paint);//绘制路径原图

        paint.setColor(Color.GREEN);//改变画笔颜色
        //hoffset、voffset参数值全部设为0，看原始状态是怎样的
        canvas.drawTextOnPath(textStr,circlePathCW,0,0,paint);
        //第二个路径，改变hoffset、voffset参数值
        canvas.drawTextOnPath(textStr,circlePathCCW,80,30,paint);


        /*
            设置字体的样式
         */
        String familyName = "宋体";
        Typeface font = Typeface.create(familyName,Typeface.NORMAL);//正常体，系统自带的
        paint.setTypeface(font);
        canvas.drawText("使用系统字体",10,400,paint);

        /*
               自定义字全，由于没有自定义字体文件，这里写下步骤
                    自定义字体的话，我们就需要从外部字体文件加载我们所需要的字形的，从外部文件加载字形所使用的Typeface构造函数如下面三个：
                Typeface	createFromAsset(AssetManager mgr, String path) //通过从Asset中获取外部字体来显示字体样式
                Typeface	createFromFile(String path)//直接从路径创建
                Typeface	createFromFile(File path)//从外部路径来创建字体样式
                    后面两个从路径加载难度不大，而我们一般也不会用到，这里我们说说从Asset文件中加载；
                首先在Asset下建一个文件夹，命名为Fonts，然后将字体文件jian_luobo.ttf 放入其中

                //自定义字体，，，迷你简罗卜
                    Paint paint=new Paint();
                    paint.setColor(Color.RED);  //设置画笔颜色
                    paint.setStrokeWidth (5);//设置画笔宽度
                    paint.setAntiAlias(true); //指定是否使用抗锯齿功能，如果使用，会使绘图速度变慢
                    paint.setTextSize(60);//设置文字大小
                    paint.setStyle(Paint.Style.FILL);//绘图样式，设置为填充

                    AssetManager mgr=m_context.getAssets();//得到AssetManager
                    Typeface typeface=Typeface.createFromAsset(mgr, "fonts/jian_luobo.ttf");//根据路径得到Typeface
                    paint.setTypeface(typeface);
                    Log.v("msg",typeface.toString());
                    canvas.drawText("欢迎光临Harvic的博客",10,100, paint);//两个构造函数
         */







    }
}
/*
普通水平绘制
    构造函数：
        void drawText (String text, float x, float y, Paint paint)
        void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
        void drawText (String text, int start, int end, float x, float y, Paint paint)
        void drawText (char[] text, int index, int count, float x, float y, Paint paint)
    说明：
        第一个构造函数：最普通简单的构造函数；
        第三、四个构造函数：实现截取一部分字体给图；
        第二个构造函数：最强大，因为传入的可以是charSequence类型字体，所以可以实现绘制带图片的扩展文字（待续），而且还能截取一部分绘制

指定每个文字位置
        void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
        void drawPosText (String text, float[] pos, Paint paint)
    说明：
        第一个构造函数：实现截取一部分文字绘制；一般用不到
    参数说明：
        char[] text：要绘制的文字数组
        int index:：第一个要绘制的文字的索引
        int count：要绘制的文字的个数，用来算最后一个文字的位置，从第一个绘制的文字开始算起
        float[] pos：每个字体的位置，同样两两一组，如｛x1,y1,x2,y2,x3,y3……｝


沿路径绘制
        void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
        void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
    参数说明：
            有关截取部分字体绘制相关参数（index,count），没难度，就不再讲了，下面首重讲hOffset、vOffset
            float hOffset  : 与路径起始点的水平偏移距离
            float vOffset  : 与路径中心的垂直偏移量


Typeface相关
    概述：Typeface是专门用来设置字体样式的，通过paint.setTypeface()来指定。可以指定系统中的字体样式，也可以指定自定义的样式文件中获取。要构建Typeface时，可以指定所用样式的正常体、斜体、粗体等，如果指定样式中，没有相关文字的样式就会用系统默认的样式来显示，一般默认是宋体。
        创建Typeface：
    Typeface	create(String familyName, int style) //直接通过指定字体名来加载系统中自带的文字样式
    Typeface	create(Typeface family, int style)     //通过其它Typeface变量来构建文字样式
    Typeface	createFromAsset(AssetManager mgr, String path) //通过从Asset中获取外部字体来显示字体样式
    Typeface	createFromFile(String path)//直接从路径创建
    Typeface	createFromFile(File path)//从外部路径来创建字体样式
    Typeface	defaultFromStyle(int style)//创建默认字体
上面的各个参数都会用到Style变量,Style的枚举值如下:
        Typeface.NORMAL  //正常体
        Typeface.BOLD	 //粗体
        Typeface.ITALIC	 //斜体
        Typeface.BOLD_ITALIC //粗斜体
 */