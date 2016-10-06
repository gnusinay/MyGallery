package com.mercury.gnusin.mygallery;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;


public class GalleryGridView extends GridView {

    GalleryGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);

        int numColumns = widthMeasureSpec > heightMeasureSpec ? 2 : 4;
        setNumColumns(numColumns);

        setRotation(90);
        setRotationY(180);

    }


    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
