package com.github.florent37.diagonallayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class DiagonalLayoutSettings {
    public static int LEFT = 0;
    public static int RIGHT = 1;
    private float angle = 15;
    private boolean handleMargins;
    private int diagonalGravity;
    private float elevation;

    DiagonalLayoutSettings(Context context, AttributeSet attrs) {
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DiagonalLayout, 0, 0);
        angle = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_angle, 0);
        diagonalGravity = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_gravity, LEFT);
        handleMargins = styledAttributes.getBoolean(R.styleable.DiagonalLayout_diagonal_handleMargins, false);

        styledAttributes.recycle();
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public boolean isGravityLeft() {
        return LEFT == diagonalGravity;
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

    public int getDiagonalGravity() {
        return diagonalGravity;
    }

    public void setDiagonalGravity(int diagonalGravity) {
        this.diagonalGravity = diagonalGravity;
    }
}
