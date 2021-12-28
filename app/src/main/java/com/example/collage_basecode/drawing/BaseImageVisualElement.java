package com.example.collage_basecode.drawing;

import android.graphics.Canvas;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:55 下午
 **/
public abstract class BaseImageVisualElement extends BaseVisualElement {

    public BaseImageVisualElement() {
        super();
    }

    public BaseImageVisualElement(float xLoc, float yLoc) {
        super(xLoc, yLoc);
    }

    public BaseImageVisualElement(float xLoc, float yLoc, float width, float height) {
        super(xLoc, yLoc, width, height);
    }

    @Override
    public void doLayout() {
        super.doLayout();
    }

}
