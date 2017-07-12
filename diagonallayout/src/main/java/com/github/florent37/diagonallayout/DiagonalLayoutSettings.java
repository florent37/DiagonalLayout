package com.github.florent37.diagonallayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IntDef;
import android.util.AttributeSet;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class DiagonalLayoutSettings {

    @Retention(SOURCE)
    @IntDef({ LEFT, RIGHT, BOTTOM, TOP })
    public @interface Position {
    }

    public final static int LEFT = 1;
    public final static int RIGHT = 2;
    public final static int BOTTOM = 4;
    public final static int TOP = 8;

    @Retention(SOURCE)
    @IntDef({ DIRECTION_LEFT, DIRECTION_RIGHT })
    public @interface Direction {
    }

    public final static int DIRECTION_LEFT = 1;
    public final static int DIRECTION_RIGHT = 2;

    private float angle = 15;
    private float elevation;
    private int position = BOTTOM;
    private int direction = DIRECTION_LEFT;
    private boolean handleMargins;


    DiagonalLayoutSettings(Context context, AttributeSet attrs) {
        TypedArray styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.DiagonalLayout, 0, 0);
        try {
            angle = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_angle, 0);
            position = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_position, BOTTOM);
            handleMargins = styledAttributes.getBoolean(R.styleable.DiagonalLayout_diagonal_handleMargins, false);
            direction = styledAttributes.getInt(R.styleable.DiagonalLayout_diagonal_direction, DIRECTION_LEFT);
        } finally {
            styledAttributes.recycle();
        }
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

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isDirectionLeft() {
        return direction == DIRECTION_LEFT;
    }
}
