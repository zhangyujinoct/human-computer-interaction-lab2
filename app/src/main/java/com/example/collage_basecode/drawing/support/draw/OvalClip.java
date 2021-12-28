package com.example.collage_basecode.drawing.support.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import com.example.collage_basecode.drawing.BaseImageVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class OvalClip extends BaseImageVisualElement {

    public OvalClip(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    @Override
    public void draw(Canvas onCanvas) {
        onCanvas.save();
        Path oval = new Path();
        RectF rectOval = new RectF(this.x, this.y, this.x + this.w, this.y + this.h);
        oval.addOval(rectOval, Path.Direction.CCW);
        onCanvas.clipPath(oval);
        onCanvas.translate(this.x, this.y);

        if (!this.visualElements.isEmpty()) {
            visualElements.forEach(v -> v.draw(onCanvas));
        }

        onCanvas.restore();
    }

    @Override
    public void drawInner(Canvas onCanvas) {
    }
}
