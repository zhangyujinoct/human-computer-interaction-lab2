package com.example.collage_basecode.drawing;

import android.graphics.Canvas;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:55 下午
 **/
public abstract class BaseLayoutVisualElement extends BaseVisualElement {

    public BaseLayoutVisualElement() {
        super();
    }

    public BaseLayoutVisualElement(float xLoc, float yLoc) {
        super(xLoc, yLoc);
    }

    public BaseLayoutVisualElement(float xLoc, float yLoc, float width, float height) {
        super(xLoc, yLoc, width, height);
    }

    @Override
    public void draw(Canvas onCanvas) {
        super.draw(onCanvas);
    }

    @Override
    protected void drawInner(Canvas onCanvas) {

    }
}
