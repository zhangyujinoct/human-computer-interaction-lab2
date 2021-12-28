package com.example.collage_basecode.drawing.support.draw;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.collage_basecode.drawing.BaseImageVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class SolidBackDrop extends BaseImageVisualElement {

    private int color;

    public SolidBackDrop(float x, float y, float w, float h, int color) {
        super(x, y, w, h);
        this.color = color;
    }

    @Override
    protected void drawInner(Canvas onCanvas) {
        Paint fillPaint = new Paint();
        fillPaint.setColor(color);
        onCanvas.drawRect(0, 0, this.w, this.h, fillPaint);
    }

}