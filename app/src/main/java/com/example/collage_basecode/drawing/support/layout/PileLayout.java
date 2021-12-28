package com.example.collage_basecode.drawing.support.layout;

import android.graphics.Canvas;

import com.example.collage_basecode.drawing.BaseLayoutVisualElement;
import com.example.collage_basecode.drawing.BaseVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class PileLayout extends BaseLayoutVisualElement {

    public PileLayout(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    @Override
    public void doLayout() {
        if (!this.visualElements.isEmpty()) {
            for (int i = 0; i < this.visualElements.size(); i++) {
                this.visualElements.get(i).setPosition(0, 0);
                this.visualElements.get(i).doLayout();
            }
        }
    }
}
