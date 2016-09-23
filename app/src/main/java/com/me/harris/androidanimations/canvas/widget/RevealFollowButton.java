package com.me.harris.androidanimations.canvas.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class RevealFollowButton extends FrameLayout {
    protected boolean mIsFollowed;
    private TextView mFollowTv;
    private TextView mUnFollowTv;
    float mCenterX;
    float mCenterY;
    float mRevealRadius = 0;
    private Path mPath = new Path();

    public RevealFollowButton(Context paramContext) {
        super(paramContext);
        init();
    }

    public RevealFollowButton(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init();
    }

    public RevealFollowButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                mCenterX = event.getX();
                mCenterY = event.getY();
                mRevealRadius = 0;
                mFollowTv.setVisibility(View.VISIBLE);
                mUnFollowTv.setVisibility(View.VISIBLE);
                setFollowed(!mIsFollowed, true);
                break;
        }
        return true;
    }

    private void init() {
        mUnFollowTv = new TextView(getContext());
        mUnFollowTv.setText("未关注");
        mUnFollowTv.setGravity(17);
        mUnFollowTv.setSingleLine();
        mUnFollowTv.setBackgroundColor(Color.RED);
        mUnFollowTv.setTextColor(Color.WHITE);
        addView(this.mUnFollowTv);
        mFollowTv = new TextView(getContext());
        mFollowTv.setText("关注");
        mFollowTv.setGravity(17);
        mFollowTv.setSingleLine();
        mFollowTv.setBackgroundColor(Color.WHITE);
        mFollowTv.setTextColor(Color.BLACK);
        addView(this.mFollowTv);

        mFollowTv.setPadding(40, 40, 40, 40);
        mUnFollowTv.setPadding(40, 40, 40, 40);
        setFollowed(false, false);
    }

    protected void setFollowed(boolean isFollowed, boolean needAnimate) {
        mIsFollowed = isFollowed;
        if (isFollowed) {
            mUnFollowTv.setVisibility(View.VISIBLE);
            mFollowTv.setVisibility(View.VISIBLE);
            mFollowTv.bringToFront();
        } else {
            mUnFollowTv.setVisibility(View.VISIBLE);
            mFollowTv.setVisibility(View.VISIBLE);
            mUnFollowTv.bringToFront();
        }
        if (needAnimate) {
            ValueAnimator animator = ObjectAnimator.ofFloat(mFollowTv, "empty", 0.0F, (float) Math.hypot(getMeasuredWidth(), getMeasuredHeight()));
            animator.setDuration(2000L);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRevealRadius = (Float) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.start();
        }
    }

    private boolean drawBackground(View paramView) {
        if (mIsFollowed && paramView == mUnFollowTv) {
            return true;
        } else if (!mIsFollowed && paramView == mFollowTv) {
            return true;
        }
        return false;
    }

    protected boolean drawChild(Canvas canvas, View paramView, long paramLong) {
        if (drawBackground(paramView)) {
            return super.drawChild(canvas, paramView, paramLong);
        }
        int i = canvas.save();
        mPath.reset();
        mPath.addCircle(mCenterX, mCenterY, mRevealRadius, Path.Direction.CW);
        canvas.clipPath(this.mPath);
        boolean bool2 = super.drawChild(canvas, paramView, paramLong);
        canvas.restoreToCount(i);
        return bool2;
    }

    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
        return true;
    }

}