package com.example.androidstudiodemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DY on 2017/1/17.
 */
public class CardView extends ViewGroup {

    private TypedArray ta;
    private int cardViewTop;
    private int cardViewLeft;
    private int childCount;
    private View childAt;

    public CardView(Context context) {
        this(context,null);
        Log.e("123", "一个");
    }

    public CardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        Log.e("123","两个");
    }

    public CardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.e("123", "三个");
        TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.CardView);
        cardViewTop = ta.getDimensionPixelSize(R.styleable.CardView_margin_top, getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin));
        cardViewLeft = ta.getDimensionPixelSize(R.styleable.CardView_margin_left, getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin));
        ta.recycle();
    }
    public static class LayoutParams extends ViewGroup.LayoutParams{
        int x;
        int y;
        public int verticalSpacing;

        public LayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);

            TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.CardView);
            try {
                verticalSpacing = a
                        .getDimensionPixelSize(
                                R.styleable.CardView_margin_top,
                                -1);
            } finally {
                a.recycle();
            }
        }

        public LayoutParams(int w, int h) {
            super(w, h);
        }
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getPaddingLeft();
        int height = getPaddingTop();
        childCount = getChildCount();
        for (int i=0;i<childCount;i++){
            childAt = getChildAt(i);
            measureChild(childAt,widthMeasureSpec,heightMeasureSpec);
            LayoutParams lp= (LayoutParams) childAt.getLayoutParams();
            width=getPaddingLeft()+cardViewLeft*i;
            lp.x=width;
            lp.y=height;
            width+=childAt.getMeasuredWidth();
            height+=cardViewTop;
        }
        width+=getPaddingRight();
        height+=getChildAt(getChildCount()-1).getMeasuredHeight()+getPaddingBottom();
        setMeasuredDimension(resolveSize(width,widthMeasureSpec),resolveSize(height,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
            int count=getChildCount();
        for (int i=0;i<count;i++){
            View child=getChildAt(i);
            LayoutParams lp= (LayoutParams) childAt.getLayoutParams();
            child.layout(lp.x,lp.y,lp.x+child.getMeasuredWidth(),lp.y+child.getMeasuredHeight());
        }
    }
}
