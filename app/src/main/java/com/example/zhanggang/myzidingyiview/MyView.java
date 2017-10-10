package com.example.zhanggang.myzidingyiview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhanggang on 2017/10/10.
 */

public class MyView extends View {

    private Path path;//绘制三角形必须
    private Paint paint;//画笔
    private Bitmap bitmap;//图形

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        //创建图形  由于 画笔不能直接画图形
        bitmap = bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        path = new Path();
        //抗锯齿
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        path.moveTo(300, 300);//move到那个位置
        path.lineTo(0, 600);//连接到那个位置
        path.rLineTo(300, 200);//r就是相对于的话
        path.close();//封闭图形

        //隔离层
        int saveLayer = canvas.saveLayer(0, 0, bitmap.getWidth(), bitmap.getHeight(), paint, Canvas.ALL_SAVE_FLAG);
        //画图形
        canvas.drawPath(path,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));//图文混合模式
        canvas.drawBitmap(bitmap, 0, 0, paint);//画哪张图片
        paint.setXfermode(null);//清除图文混合模式
        canvas.restoreToCount(saveLayer);//清除隔离层 回复画布
    }
}
