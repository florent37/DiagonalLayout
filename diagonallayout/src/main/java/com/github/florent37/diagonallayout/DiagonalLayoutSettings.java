package com.github.florent37.diagonallayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class DiagonalLayoutSettings {
    public final static int LEFT = 1;
    public final static int RIGHT = 2;
    public final static int BOTTOM = 4;
    public final static int TOP = 8;

    public final static int DIRECTION_LEFT = 1;
    public final static int DIRECTION_RIGHT = 2;

    private float angle = 15;
    private boolean handleMargins;
    private int gravity = BOTTOM;
    private boolean isDirectionLeft;
    private float elevation;

    DiagonalLayoutSettings(Context context, AttributeSet attrs) {
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DiagonalLayout, 0, 0);
        angle = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_angle, 0);

        gravity = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_position, BOTTOM);
        handleMargins = styledAttributes.getBoolean(R.styleable.DiagonalLayout_diagonal_handleMargins, false);

        isDirectionLeft = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_direction, DIRECTION_LEFT) == DIRECTION_LEFT;

        styledAttributes.recycle();
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public boolean isHandleMargins() {
        return handleMargins;
    }

    public void setHandleMargins(boolean handleMargins) {
        this.handleMargins = handleMargins;
    }

    public boolean isBottom(){
        return gravity == BOTTOM;
    }
    public boolean isTop(){ return gravity == TOP; }
    public boolean isLeft(){
        return gravity == LEFT;
    }
    public boolean isRight(){
        return gravity == RIGHT;
    }

    public boolean isDirectionLeft() {
        return isDirectionLeft;
    }
}
