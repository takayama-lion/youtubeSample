package com.example.hiroyuki.androidyoutubesample;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import static java.lang.Float.parseFloat;

/**
 * Created by hiroyuki on 2017/04/03.
 */

/**
 * viewの横幅を1として高さを算出してサイズを決定する。
 */
public class DisplayAspectRatioFrameLayout extends FrameLayout {
    private float mAspectRate;

    public DisplayAspectRatioFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.aspect_FixedAspectFrameLayout);
        String aspect = a.getString(0);
        String[] asp = aspect.split(":");
        float w = parseFloat(asp[0]);
        float h = parseFloat(asp[1]);
        mAspectRate = h / w;
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int h = (int) (MeasureSpec.getSize(widthMeasureSpec) * mAspectRate);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(h, MeasureSpec.EXACTLY));
    }
}
