package com.swir.fingerprint.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;


public final class FingerPrintView extends ImageView {

    private AnimatedVectorDrawable mAnimateVector;

    public enum State {
        DEFAULT,
        SUCCESS,
        FAIL,
    }
    private Animatable2.AnimationCallback animationCallback;
    public FingerPrintView(Context context) {
        this(context, null);
    }

    public FingerPrintView(Context context, AttributeSet attrs) {
        super(context, attrs);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            throw new AssertionError("API 21 required.");
        }
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.swirl_Swirl);
        int state = a.getInteger(R.styleable.swirl_Swirl_swirl_state, -1);
        if (state != -1) {
            setState(State.values()[state]);
        }
        a.recycle();
    }

    public void setState(State newState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int resId = getDrawable(newState);
            Drawable icon = getContext().getDrawable(resId);
            setImageDrawable(icon);
            if (icon instanceof AnimatedVectorDrawable) {
                mAnimateVector = (AnimatedVectorDrawable) icon;
                mAnimateVector.start();
            }
        }
    }

    private static int getDrawable(State newState) {
        switch (newState) {
            case DEFAULT://new
                return R.drawable.swirl_draw_on_default_animation;
            case SUCCESS:
                return R.drawable.swirl_draw_on_default_success_animation;
            case FAIL:
                return R.drawable.swirl_draw_on_default_fail_animation;
            default:
                throw new IllegalArgumentException("Unknown mLastState: " + newState);
        }
    }
}
