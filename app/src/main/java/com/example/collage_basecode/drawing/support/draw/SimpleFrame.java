package com.example.collage_basecode.drawing.support.draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.collage_basecode.drawing.BaseImageVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/7 4:14 下午
 **/
public class SimpleFrame extends BaseImageVisualElement {

    public SimpleFrame(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    @Override
    protected void drawInner(Canvas onCanvas) {
        Paint rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(1);
        onCanvas.drawRect(0, 0, this.w, this.h, rectPaint);
    }
}
