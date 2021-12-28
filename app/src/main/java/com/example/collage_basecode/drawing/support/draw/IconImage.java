package com.example.collage_basecode.drawing.support.draw;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.collage_basecode.drawing.BaseImageVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class IconImage extends BaseImageVisualElement {

    private Bitmap bitmap;

    public IconImage(float x, float y, Bitmap image) {
        this.setPosition(x, y);
        this.setBitmap(image);
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.h = bitmap.getHeight();
        this.w = bitmap.getWidth();
    }

    @Override
    public boolean sizeIsIntrinsic() {
        return true;
    }

    @Override
    public void drawInner(Canvas onCanvas) {
        Paint bitmapPaint = new Paint();
        if (bitmap != null) {
            onCanvas.drawBitmap(bitmap, 0, 0, bitmapPaint);
        }
    }
}
