package com.github.florent37.diagonallayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;

import com.github.florent37.diagonallayout.DiagonalLayoutSettings.Direction;
import com.github.florent37.diagonallayout.DiagonalLayoutSettings.Position;

public class DiagonalLayout extends FrameLayout {

    private static final float EPSILON = 0.5f;

    DiagonalLayoutSettings settings;

    int height = 0;

    int width = 0;

    Path clipPath, outlinePath;

    Paint paint;

    Integer defaultMargin_forPosition;

    private PorterDuffXfermode pdMode;

    public DiagonalLayout(Context context) {
        super(context);
        init(context, null);
    }

    public DiagonalLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        settings = new DiagonalLayoutSettings(context, attrs);
        settings.setElevation(ViewCompat.getElevation(this));

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.WHITE);

        pdMode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
    }

    @Override
    public void setBackgroundColor(int color) {
        paint.setColor(color);
        postInvalidate();
    }

    public void setPosition(@Position int position) {
        settings.setPosition(position);
        postInvalidate();
    }

    public void setDirection(@Direction int direction) {
        settings.setDirection(direction);
        postInvalidate();
    }

    public void setAngle(float angle){
        settings.setAngle(angle);
        calculateLayout();
        postInvalidate();
    }

    private void calculateLayout() {
        if (settings == null) {
            return;
        }
        height = getMeasuredHeight();
        width = getMeasuredWidth();
        if (width > 0 && height > 0) {

            final float perpendicularHeight = (float) (width * Math.tan(Math.toRadians(settings.getAngle())));

            clipPath = createClipPath(perpendicularHeight);
            outlinePath = createOutlinePath(perpendicularHeight);

            handleMargins(perpendicularHeight);

            ViewCompat.setElevation(this, settings.getElevation());

            //this needs to be fixed for 25.4.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && ViewCompat.getElevation(this) > 0f) {
                try {
                    setOutlineProvider(getOutlineProvider());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Path createClipPath(float perpendicularHeight) {
        Path path = new Path();
        switch (settings.getPosition()) {
            case DiagonalLayoutSettings.BOTTOM:
                if (settings.isDirectionLeft()) {
                    path.moveTo(width - getPaddingRight() + EPSILON, height - perpendicularHeight - getPaddingBottom() + EPSILON);
                    path.lineTo(width - getPaddingRight() + EPSILON, height - getPaddingBottom() + EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, height - getPaddingBottom() + EPSILON);
                    path.close();
                } else {
                    path.moveTo(width - getPaddingRight() + EPSILON, height - getPaddingBottom() + EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, height - getPaddingBottom() + EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, height - perpendicularHeight - getPaddingBottom() + EPSILON);
                    path.close();
                }
                break;
            case DiagonalLayoutSettings.TOP:
                if (settings.isDirectionLeft()) {
                    path.moveTo(width - getPaddingRight() + EPSILON, getPaddingTop() + perpendicularHeight - EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(width - getPaddingRight() + EPSILON, getPaddingTop() - EPSILON);
                    path.close();
                } else {
                    path.moveTo(width - getPaddingRight() + EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, getPaddingTop() + perpendicularHeight - EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, getPaddingTop() - EPSILON);
                    path.close();
                }
                break;
            case DiagonalLayoutSettings.RIGHT:
                if (settings.isDirectionLeft()) {
                    path.moveTo(width - getPaddingRight() + EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(width - getPaddingRight() + EPSILON, height - getPaddingBottom() + EPSILON);
                    path.lineTo(width - perpendicularHeight - getPaddingRight() + EPSILON, height - getPaddingBottom() + EPSILON);
                    path.close();
                } else {
                    path.moveTo(width - perpendicularHeight - getPaddingRight() - EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(width - getPaddingRight() + EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(width - getPaddingRight() + EPSILON, height - getPaddingBottom() + EPSILON);
                    path.close();
                }
                break;
            case DiagonalLayoutSettings.LEFT:
                if (settings.isDirectionLeft()) {
                    path.moveTo(getPaddingLeft() - EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(getPaddingLeft() + perpendicularHeight + EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, height - getPaddingBottom() + EPSILON);
                    path.close();
                } else {
                    path.moveTo(getPaddingLeft() - EPSILON, getPaddingTop() - EPSILON);
                    path.lineTo(getPaddingLeft() + perpendicularHeight + EPSILON, height - getPaddingBottom() + EPSILON);
                    path.lineTo(getPaddingLeft() - EPSILON, height - getPaddingBottom() + EPSILON);
                    path.close();
                }
                break;
        }
        return path;
    }

    private Path createOutlinePath(float perpendicularHeight) {
        Path path = new Path();
        switch (settings.getPosition()) {
            case DiagonalLayoutSettings.BOTTOM:
                if (settings.isDirectionLeft()) {
                    path.moveTo(getPaddingLeft(), getPaddingRight());
                    path.lineTo(width - getPaddingRight(), getPaddingTop());
                    path.lineTo(width - getPaddingRight(), height - perpendicularHeight - getPaddingBottom());
                    path.lineTo(getPaddingLeft(), height - getPaddingBottom());
                    path.close();
                } else {
                    path.moveTo(width - getPaddingRight(), height - getPaddingBottom());
                    path.lineTo(getPaddingLeft(), height - perpendicularHeight - getPaddingBottom());
                    path.lineTo(getPaddingLeft(), getPaddingTop());
                    path.lineTo(width - getPaddingRight(), getPaddingTop());
                    path.close();
                }
                break;
            case DiagonalLayoutSettings.TOP:
                if (settings.isDirectionLeft()) {
                    path.moveTo(width - getPaddingRight(), height - getPaddingBottom());
                    path.lineTo(width - getPaddingRight(), getPaddingTop() + perpendicularHeight);
                    path.lineTo(getPaddingLeft(), getPaddingTop());
                    path.lineTo(getPaddingLeft(), height - getPaddingBottom());
                    path.close();
                } else {
                    path.moveTo(width - getPaddingRight(), height - getPaddingBottom());
                    path.lineTo(width - getPaddingRight(), getPaddingTop());
                    path.lineTo(getPaddingLeft(), getPaddingTop() + perpendicularHeight);
                    path.lineTo(getPaddingLeft(), height - getPaddingBottom());
                    path.close();
                }
                break;
            case DiagonalLayoutSettings.RIGHT:
                if (settings.isDirectionLeft()) {
                    path.moveTo(getPaddingLeft(), getPaddingTop());
                    path.lineTo(width - getPaddingRight(), getPaddingTop());
                    path.lineTo(width - getPaddingRight() - perpendicularHeight, height - getPaddingBottom());
                    path.lineTo(getPaddingLeft(), height - getPaddingBottom());
                    path.close();
                } else {
                    path.moveTo(getPaddingLeft(), getPaddingTop());
                    path.lineTo(width - getPaddingRight() - perpendicularHeight, getPaddingTop());
                    path.lineTo(width - getPaddingRight(), height - getPaddingBottom());
                    path.lineTo(getPaddingLeft(), height - getPaddingBottom());
                    path.close();
                }
                break;
            case DiagonalLayoutSettings.LEFT:
                if (settings.isDirectionLeft()) {
                    path.moveTo(getPaddingLeft() + perpendicularHeight, getPaddingTop());
                    path.lineTo(width - getPaddingRight(), getPaddingTop());
                    path.lineTo(width - getPaddingRight(), height - getPaddingBottom());
                    path.lineTo(getPaddingLeft(), height - getPaddingBottom());
                    path.close();
                } else {
                    path.moveTo(getPaddingLeft(), getPaddingTop());
                    path.lineTo(width - getPaddingRight(), getPaddingTop());
                    path.lineTo(width - getPaddingRight(), height - getPaddingBottom());
                    path.lineTo(getPaddingLeft() + perpendicularHeight, height - getPaddingBottom());
                    path.close();
                }
                break;
        }
        return path;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public ViewOutlineProvider getOutlineProvider() {
        return new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setConvexPath(outlinePath);
            }
        };
    }

    private void handleMargins(float perpendicularHeight) {
        if (settings.isHandleMargins()) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams instanceof MarginLayoutParams) {
                MarginLayoutParams lp = (MarginLayoutParams) layoutParams;

                switch (settings.getDirection()) {
                    case DiagonalLayoutSettings.BOTTOM:
                        if (defaultMargin_forPosition == null) {
                            defaultMargin_forPosition = lp.bottomMargin;
                        } else {
                            defaultMargin_forPosition = 0;
                        }
                        lp.bottomMargin = (int) (defaultMargin_forPosition - perpendicularHeight);
                        break;
                    case DiagonalLayoutSettings.TOP:
                        if (defaultMargin_forPosition == null) {
                            defaultMargin_forPosition = lp.topMargin;
                        } else {
                            defaultMargin_forPosition = 0;
                        }
                        lp.topMargin = (int) (defaultMargin_forPosition - perpendicularHeight);
                        break;
                }

                setLayoutParams(lp);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            calculateLayout();
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(!isInEditMode()) {
            int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);

            super.dispatchDraw(canvas);

            paint.setXfermode(pdMode);
            canvas.drawPath(clipPath, paint);

            canvas.restoreToCount(saveCount);
            paint.setXfermode(null);
        } else {
            super.dispatchDraw(canvas);
        }
    }
}
