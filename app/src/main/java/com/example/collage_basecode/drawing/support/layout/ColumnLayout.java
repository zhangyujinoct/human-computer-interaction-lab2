package com.example.collage_basecode.drawing.support.layout;

import com.example.collage_basecode.drawing.BaseLayoutVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class ColumnLayout extends BaseLayoutVisualElement {

    public ColumnLayout(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    @Override
    public void doLayout() {

        float runningCount = 0;

        if (!this.visualElements.isEmpty()) {
            for (int i = 0; i < this.visualElements.size(); i++) {

                if (this.visualElements.get(i) == null) continue;

                float childWidth = this.visualElements.get(i).getW();
                this.visualElements.get(i).setX(this.w / 2 - childWidth / 2);

                this.visualElements.get(i).setY(runningCount);
                runningCount += this.visualElements.get(i).getH();

                this.visualElements.get(i).doLayout();
            }
        }
    }

}
