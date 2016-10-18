package com.github.florent37.diagonallayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class DiagonalLayoutContent extends FrameLayout {

    @Nullable DiagonalLayoutSettings settings;
    int height = 0;
    int width = 0;

    Path path;
    Paint paint;

    Integer defaultMarginBottom;

    public DiagonalLayoutContent(Context context) {
        super(context);
        init();
    }

    public DiagonalLayoutContent(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);

        setWillNotDraw(false);
    }

    public void setSettings(@Nullable DiagonalLayoutSettings settings) {
        this.settings = settings;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        calculateLayout();
    }

    private void calculateLayout(){
        if(settings == null){
            return;
        }
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        if(width > 0 && height > 0) {

            float perpendicularHeight = (float) (width * Math.tan(Math.toRadians(settings.getAngle())));

            if (settings.isGravityLeft()) {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, height - perpendicularHeight);
                path.lineTo(0, height);
                path.lineTo(0, 0);
            } else {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height - perpendicularHeight);
                path.lineTo(0, 0);
            }

            if(settings.isHandleMargins()){
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if(layoutParams instanceof MarginLayoutParams){
                    MarginLayoutParams lp = (MarginLayoutParams)layoutParams;
                    if(defaultMarginBottom == null){
                        defaultMarginBottom = lp.bottomMargin;
                    } else {
                        defaultMarginBottom = 0;
                    }
                    lp.bottomMargin = (int) (defaultMarginBottom - perpendicularHeight);
                    setLayoutParams(lp);
                }
            }

            float elevation = settings.getElevation();
            if(elevation > 0) {
                final ViewGroup parent = (ViewGroup) getParent();
                View back = new View(getContext());
                back.setBackgroundColor(Color.WHITE);
                double w = (Math.sqrt(Math.pow(width, 2) + Math.pow(perpendicularHeight, 2)));
                MarginLayoutParams marginLayoutParams = new MarginLayoutParams((int) Math.floor(w), height);
                ViewCompat.setElevation(back, elevation);
                ViewCompat.setElevation(this, elevation+1);
                if (settings.isGravityLeft()) {
                    back.setRotation(-settings.getAngle());
                    back.setPivotX(0);
                    back.setPivotY(height);
                } else {
                    back.setRotation(settings.getAngle());
                    back.setPivotX(width);
                    back.setPivotY(height);
                }

                parent.addView(back, marginLayoutParams);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(changed){
            calculateLayout();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.clipPath(path);
        super.onDraw(canvas);
    }
}
