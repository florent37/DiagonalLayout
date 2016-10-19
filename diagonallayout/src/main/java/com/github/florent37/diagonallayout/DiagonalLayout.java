package com.github.florent37.diagonallayout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.util.ArrayList;
import java.util.List;

public class DiagonalLayout extends FrameLayout {

    DiagonalLayoutContent content;
    DiagonalLayoutSettings settings;

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
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        content = new DiagonalLayoutContent(getContext());
        content.setSettings(settings);

        final List<View> subViews = getAndRemoveSubViews();
        addView(content, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        for(View view : subViews) {
            content.addView(view);
        }
    }

    private List<View> getAndRemoveSubViews(){
        final List<View> subViews = new ArrayList<>();
        for(int i=0;i<getChildCount();++i){
            subViews.add(getChildAt(i));
        }
        removeAllViews();
        return subViews;
    }

}
