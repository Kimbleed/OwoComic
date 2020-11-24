package com.example.cen.owocomic.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.example.cen.owocomic.R;

public class LinearGradientView extends View {

    private LinearGradient linearGradient = null;
    private Paint paint = null;
    private Rect mRect = null;

    public LinearGradientView(Context context) {
        super(context);
    }

    public LinearGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        linearGradient = new LinearGradient(0, 0, 0, bottom, new int[]{
                getContext().getResources().getColor(R.color.comic_theme_color),
                getContext().getResources().getColor(R.color.comic_theme_color_light)}, null,
                Shader.TileMode.REPEAT);
        paint = new Paint();
        mRect = new Rect(left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //设置渲染器
        if (paint == null)
            return;
        paint.setShader(linearGradient);
        //绘制圆环
        canvas.drawRect(mRect, paint);
    }

}

