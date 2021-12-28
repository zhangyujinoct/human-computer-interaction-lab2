package com.example.collage_basecode.drawing.support.draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.example.collage_basecode.drawing.BaseImageVisualElement;

/**
 * Author: Zhu yuhan
 * Email: zhuyuhan2333@qq.com
 * Date: 2021/12/6 3:14 下午
 **/
public class TextVisualElement extends BaseImageVisualElement {

    private String text;

    private Typeface typeface;

    private Float textSize;

    private int color;

    public TextVisualElement(float x, float y, String text, Typeface type, float size) {
        this.setPosition(x, y);
        this.setText(text, type, size);
    }

    public TextVisualElement(float x, float y, String text, Typeface type, float size, int color) {
        this.setPosition(x, y);
        this.setText(text, type, size);
        this.setColor(color);
    }

    public void setText(String text, Typeface type, float size) {
        this.setText(text);
        this.setTypeface(type);
        this.setTextSize(size);

        Paint textPaint = new Paint();
        Rect bounds = new Rect();
        textPaint.setTextSize(size);
        textPaint.getTextBounds(text, 0, text.length(), bounds);

        this.h = textPaint.getFontMetrics().descent - textPaint.getFontMetrics().ascent + textPaint.getFontMetrics().leading;
        this.w = textPaint.measureText(text);
    }

    @Override
    public void drawInner(Canvas onCanvas) {
        Paint textPaint = new Paint();
        if (color != 0)
            textPaint.setColor(color);
        textPaint.setTypeface(this.typeface);
        textPaint.setTextSize(this.textSize);
        textPaint.setTextAlign(Paint.Align.LEFT);

        onCanvas.drawText(this.text, 0, -textPaint.getFontMetrics().ascent, textPaint);
    }

    @Override
    public boolean sizeIsIntrinsic() {
        return true;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTypeface(Typeface typeface) {
        this.typeface = typeface;
    }

    public void setTextSize(Float textSize) {
        this.textSize = textSize;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
