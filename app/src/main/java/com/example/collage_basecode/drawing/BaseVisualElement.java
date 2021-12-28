package com.example.collage_basecode.drawing;

import android.graphics.Canvas;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:55 下午
 **/
public abstract class BaseVisualElement extends PrebaseVisualElement {

    protected List<VisualElement> visualElements = new ArrayList<>();

    protected VisualElement parent;

    protected float x;

    protected float y;

    protected float w;

    protected float h;

    /**
     * Default constructor.
     */
    public BaseVisualElement() {
        // do default initialization at an odd size so its easy to see when
        // the size is never set up.
        super(0, 0);
    }

    /**
     * Constructor with position only.
     */
    public BaseVisualElement(float xLoc, float yLoc) {
        // do default initialization at an odd size so its easy to see when
        // the size is never set up.
        super(xLoc, yLoc);
    }

    /**
     * Full constructor giving position and size.
     */
    public BaseVisualElement(float xLoc, float yLoc, float width, float height) {
        super(xLoc, yLoc, width, height);
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setW(float w) {
        this.w = w;
    }

    @Override
    public void setH(float h) {
        this.h = h;
    }

    @Override
    public float getW() {
        return w;
    }

    @Override
    public float getH() {
        return h;
    }

    @Override
    public VisualElement getParent() {
        return this.parent;
    }

    @Override
    public void setParent(VisualElement newParent) {
        // 更新parent's child list
        VisualElement oldParent = this.getParent();
        if (oldParent != null) {
            oldParent.removeChild(this);
        }
        this.parent = newParent;
    }

    @Override
    public int getNumChildren() {
        return visualElements.size();
    }

    @Override
    public VisualElement getChildAt(int index) {
        if (index < 0 || index > getNumChildren() - 1) return null;
        return visualElements.get(index);
    }

    @Override
    public int findChild(VisualElement child) {
        if (visualElements == null || visualElements.size() == 0 || child == null) {
            return -1;
        }
        return this.visualElements.indexOf(child);
    }

    @Override
    public void addChild(VisualElement child) {
        if (child == null) return;
        // 移除原有parent
        if (child.getParent() != null) {
            child.setParent(this);
        }
        this.visualElements.add(child);
    }

    @Override
    public void removeChildAt(int index) {
        if (visualElements == null || index < 0 || index >= visualElements.size()) return;
        VisualElement remove = this.visualElements.remove(index);
        remove.setParent(null);
    }

    @Override
    public void removeChild(VisualElement child) {
        if (visualElements == null) return;
        this.visualElements.remove(child);
        child.setParent(null);
    }

    @Override
    public void moveChildFirst(VisualElement child) {
        if (findChild(child) != -1) {
            // already have
            removeChild(child);
            visualElements.add(0, child);
        }
    }

    @Override
    public void moveChildLast(VisualElement child) {
        if (visualElements.size() == 0) {
            visualElements.add(child);
            return;
        }
        if (findChild(child) != -1) {
            // already have
            removeChild(child);
            visualElements.add(visualElements.size() - 1, child);
        }
    }

    @Override
    public void moveChildEarlier(VisualElement child) {
        int index = findChild(child);
        if (visualElements.size() == 0 || visualElements.size() == 1 || index == -1 || index == 0) {
            return;
        }
        visualElements.remove(index);
        visualElements.add(index - 1, child);
    }

    @Override
    public void moveChildLater(VisualElement child) {
        int index = findChild(child);
        if (visualElements.size() == 0 || visualElements.size() == 1 || index == -1 || index == visualElements.size() - 1) {
            return;
        }
        visualElements.remove(index);
        visualElements.add(index + 1, child);
    }

    @Override
    public void doLayout() {
        if (getNumChildren() > 0) {
            this.visualElements.forEach(VisualElement::doLayout);
        }
    }

    @Override
    public void draw(Canvas onCanvas) {
        onCanvas.save();
        onCanvas.clipRect(this.x, this.y, this.x + this.w, this.y + this.h);
        onCanvas.translate(this.x, this.y);

        drawInner(onCanvas);

        if (!this.visualElements.isEmpty()) {
            visualElements.forEach((v) -> v.draw(onCanvas));
        }

        onCanvas.restore();
    }

    @Override
    public boolean sizeIsIntrinsic() {
        return super.sizeIsIntrinsic();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof VisualElement)) return false;
        if (obj.getClass() != getClass()) return false;
        VisualElement visualElement = (VisualElement) obj;
        return this.getH() == visualElement.getH() && this.getW() == visualElement.getW()
                && this.getX() == visualElement.getX() && this.getY() == visualElement.getY();
    }

    protected abstract void drawInner(Canvas onCanvas);

}
