package com.example.collage_basecode.drawing.support.draw;

import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.RectF;

import com.example.collage_basecode.drawing.BaseImageVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class NinePartImage extends BaseImageVisualElement {

    private NinePatch ninePatch;

    public NinePartImage(float x, float y, float w, float h, NinePatch patch) {
        super(x, y, w, h);
        this.setNinePatch(patch);
    }

    public void setNinePatch(NinePatch ninePatch) {
        this.ninePatch = ninePatch;
    }

    @Override
    public void drawInner(Canvas onCanvas) {
        RectF ninePatchRect = new RectF(0, 0, this.w, this.h);
        this.ninePatch.draw(onCanvas, ninePatchRect);
    }
}
