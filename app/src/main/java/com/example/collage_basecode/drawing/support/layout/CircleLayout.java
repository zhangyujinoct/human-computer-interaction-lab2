package com.example.collage_basecode.drawing.support.layout;

import com.example.collage_basecode.drawing.BaseLayoutVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class CircleLayout extends BaseLayoutVisualElement {

    private float centerX;

    private float centerY;

    private float radius;

    public CircleLayout(float x, float y, float w, float h, float mlayoutCenterX,
                        float centerY, float radius) {
        this.setPosition(x, y);
        this.setSize(w, h);
        this.centerX = mlayoutCenterX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public void doLayout() {

        if (!this.visualElements.isEmpty()) {

            int numChildren = getNumChildren();
            double degreesBetweenChildren = 2 * Math.PI / numChildren;
            double newX = 0;
            double newY = 0;
            double angle = 0;

            for (int i = 0; i < this.visualElements.size(); i++) {

                if (this.visualElements.get(i) == null) continue;

                angle = degreesBetweenChildren * i;
                newX = (int) (this.centerX + this.radius * Math.cos(angle));
                newY = (int) (this.centerY + this.radius * Math.sin(angle));

                float childHeight = this.visualElements.get(i).getH();
                float childWidth = this.visualElements.get(i).getW();
                float childCenterX = childWidth / 2;
                float childCenterY = childHeight / 2;

                newX = newX - childCenterX;
                newY = newY - childCenterY;

                this.visualElements.get(i).setX((float) newX);
                this.visualElements.get(i).setY((float) newY);
                this.visualElements.get(i).doLayout();
            }
        }
    }

}
