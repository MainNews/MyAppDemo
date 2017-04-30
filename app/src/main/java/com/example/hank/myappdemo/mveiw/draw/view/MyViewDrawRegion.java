package com.example.hank.myappdemo.mveiw.draw.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.view.View;

/**
 * Created by Jun on 2017/4/30.
 * 该类用于使用Region来画图
 */

public class MyViewDrawRegion extends View {
    public MyViewDrawRegion(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();//初始化画笔
        paint.setStrokeWidth(5);//画笔宽度
        paint.setColor(Color.RED);//画笔颜色
        paint.setStyle(Paint.Style.FILL);//样式为填充

        Region region = new Region(10,10,300,300);
        drawRegion(canvas,region,paint);


        //使用set()方法使，替换为新的区域，即新建了一个Region对象
        region.set(320, 10, 600, 300);
        drawRegion(canvas,region,paint);

        /*
        使用SetPath（）构造不规则区域
                boolean setPath (Path path, Region clip)
                参数说明：
                    Path path：用来构造的区域的路径
                    Region clip：与前面的path所构成的路径取交集，并将两交集设置为最终的区域
         */
        paint.setStyle(Paint.Style.STROKE);//将画笔样式改为描边
        //构造一个椭圆路径
        Path pathOval = new Path();
        RectF rectFOval = new RectF(10,320,320,600);
        pathOval.addOval(rectFOval, Path.Direction.CCW);
        //SetPath时，传入一个比椭圆区域小的矩形区域，让其取交集
        Region ovalRegion = new Region();
        ovalRegion.setPath(pathOval,new Region(10,320,320,500));
        //画出路径
        drawRegion(canvas,ovalRegion,paint);

        /*
                区域的合并、交叉等操作
         */
        //构建两个距形
        Rect rect1 = new Rect(400,320,500,600);
        Rect rect2 = new Rect(350,420,600,500);
        //画出矩形轮廓
        canvas.drawRect(rect1,paint);
        canvas.drawRect(rect2,paint);
        //构建Region区域
        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);
        //取两个区域的交集,这里的参数二，有多个参数可选，我写在下面了，可以对照查看一下
        region1.op(region2, Region.Op.INTERSECT);
        //将交集区域填充起来
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.FILL);
        drawRegion(canvas,region1,paint);



    }

    /**
     * 由于在Canvas中没有直接绘制Region的函数，我们想要绘制一个区域，就只能通过利用RegionIterator构造矩形集来逼近的显示区域。
     *          首先，根据区域构建一个矩形集，
     *          然后利用next(Rect r)来逐个获取所有矩形，绘制出来，
     *          最终得到的就是整个区域，
     *          如果我们将上面的画笔Style从FILL改为STROKE，重新绘制椭圆路径，会看得更清楚
     */
    private void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator iter = new RegionIterator(region);
        Rect r = new Rect();

        while (iter.next(r)){
            canvas.drawRect(r,paint);
        }
    }
}
/*

构造Region
1、基本构造函数
    public Region()  //创建一个空的区域
    public Region(Region region) //拷贝一个region的范围
    public Region(Rect r)  //创建一个矩形的区域
    public Region(int left, int top, int right, int bottom) //创建一个矩形的区域

上面的四个构造函数，第一个还要配合其它函数使用，暂时不提。
第二个构造函数是通过其它的Region来复制一个同样的Region变量
第三个，第四个才是正规常的，根据一个矩形或矩形的左上角和右下角点构造出一个矩形区域

间接构造
        public void setEmpty()  //置空
        public boolean set(Region region)
        public boolean set(Rect r)
        public boolean set(int left, int top, int right, int bottom)
        public boolean setPath(Path path, Region clip)//后面单独讲

这是Region所具有的一系列Set方法，我这里全部列了出来，下面一一对其讲解：
注意：无论调用Set系列函数的Region是不是有区域值，当调用Set系列函数后，原来的区域值就会被替换成Set函数里的区域。
        SetEmpty（）：从某种意义上讲置空也是一个构造函数，即将原来的一个区域变量变成了一个空变量，要再利用其它的Set方法重新构造区域。
        set(Region region)：利用新的区域值来替换原来的区域
        set(Rect r)：利用矩形所代表的区域替换原来的区域
        set(int left, int top, int right, int bottom)：同样，根据矩形的两个点构造出矩形区域来替换原来的区域值
        setPath(Path path, Region clip)：根据路径的区域与某区域的交集，构造出新区域，这个后面具体讲解


矩形集枚举区域——RegionIterator类
    函数：
        RegionIterator(Region region) //根据区域构建对应的矩形集
        boolean	next(Rect r) //获取下一个矩形，结果保存在参数Rect r 中

区域的合并、交叉等操作
无论是区域还是矩形，都会涉及到与另一个区域的一些操作，比如取交集、取并集等，涉及到的函数有：
    public final boolean union(Rect r)
    public boolean op(Rect r, Op op) {
    public boolean op(int left, int top, int right, int bottom, Op op)
    public boolean op(Region region, Op op)
    public boolean op(Rect rect, Region region, Op op)
除了Union(Rect r)是指定合并操作以外，其它四个op（）构造函数，都是指定与另一个区域的操作
其中最重要的指定Op的参数，Op的参数有下面四个：

    假设用region1  去组合region2
    public enum Op {
        DIFFERENCE(0), //最终区域为region1 与 region2不同的区域
        INTERSECT(1), // 最终区域为region1 与 region2相交的区域
        UNION(2),      //最终区域为region1 与 region2组合一起的区域
        XOR(3),        //最终区域为region1 与 region2相交之外的区域
        REVERSE_DIFFERENCE(4), //最终区域为region2 与 region1不同的区域
        REPLACE(5); //最终区域为为region2的区域
    }

    几个判断方法
    public native boolean isEmpty();//判断该区域是否为空
    public native boolean isRect(); //是否是一个矩阵
    public native boolean isComplex();//是否是多个矩阵组合


    一系列的getBound方法，返回一个Region的边界
    public Rect getBounds()
    public boolean getBounds(Rect r)
    public Path getBoundaryPath()
    public boolean getBoundaryPath(Path path)

    一系列的判断是否包含某点 和是否相交
    public native boolean contains(int x, int y);//是否包含某点
    public boolean quickContains(Rect r)   //是否包含某矩形
    public native boolean quickContains(int left, int top, int right,
                                        int bottom) //是否没有包含某矩阵形
    public boolean quickReject(Rect r) //是否没和该矩形相交
    public native boolean quickReject(int left, int top, int right, int bottom); //是否没和该矩形相交
    public native boolean quickReject(Region rgn);  //是否没和该矩形相交

    几个平移变换的方法
    public void translate(int dx, int dy)
    public native void translate(int dx, int dy, Region dst);
    public void scale(float scale) //hide
    public native void scale(float scale, Region dst);//hide
 */
