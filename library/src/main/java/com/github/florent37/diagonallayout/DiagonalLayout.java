package com.github.florent37.diagonallayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class DiagonalLayout extends FrameLayout {

    int height = 0;
    int width = 0;
    float angle = 15;

    Path path;
    Paint paint;

    public static int LEFT = 0;
    public static int RIGHT = 1;

    int diagonalGravity;

    public DiagonalLayout(Context context) {
        super(context);
        init(context, null);
    }

    public DiagonalLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);

        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DiagonalLayout, 0, 0);
        angle = styledAttributes.getInt(R.styleable.DiagonalLayout_angle, 0);
        diagonalGravity = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonalGravity, LEFT);

        styledAttributes.recycle();

        setWillNotDraw(false);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        calculateLayout();
    }

    private void calculateLayout(){
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        if(width > 0 && height > 0) {

            float perpendicularHeight = (float) (width * Math.tan(Math.toRadians(angle)));

            if (diagonalGravity == RIGHT) {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, height);
                path.lineTo(0, height - perpendicularHeight);
                path.lineTo(0, 0);
            } else {
                path.moveTo(0, 0);
                path.lineTo(width, 0);
                path.lineTo(width, height - perpendicularHeight);
                path.lineTo(0, height);
                path.lineTo(0, 0);
            }

            //Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            //Canvas mCanvas = new Canvas(bitmap);
            //mCanvas.drawPath(path, paint);
            //setBackgroundDrawable(new BitmapDrawable(bitmap));
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

    public void setAngle(float angle) {
        path.reset();
        this.angle = angle;
        invalidate();
    }

    public void setDiagonalGravity(int gravity) {
        path.reset();
        this.diagonalGravity = gravity;
        invalidate();
    }
}
