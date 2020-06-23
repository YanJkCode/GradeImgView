package com.example.gradeimgview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GradeImgView extends LinearLayout {

    private int pitchImg;
    private int notImg;
    private int max;
    private int gradeNum;
    private float leftPadding;
    private float rightPadding;
    private float topPadding;
    private float bottomPadding;
    private String mThreadName;
    private boolean openGrade;

    public GradeImgView(Context context) {
        this(context, null);
    }

    public GradeImgView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
    }

    public GradeImgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOrientation(HORIZONTAL);
        initAttrs(context, attrs);
    }

    //初始化
    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.GradeImgView);
        if (ta != null) {

            //选中图片
            pitchImg = ta.getResourceId(R.styleable.GradeImgView_pitchImg, R.drawable.ic_xing_y);

            //未选中图片
            notImg = ta.getResourceId(R.styleable.GradeImgView_notImg, R.drawable.ic_xing_n);

            //最大数量
            max = ta.getInt(R.styleable.GradeImgView_max, 1);

            //选中的数量
            gradeNum = ta.getInt(R.styleable.GradeImgView_gradeNum, 0);

            //是否可点击选择星级
            openGrade = ta.getBoolean(R.styleable.GradeImgView_openGrade, false);

            //←边距
            leftPadding = ta.getDimension(R.styleable.GradeImgView_leftPadding, 0);

            //→边距
            rightPadding = ta.getDimension(R.styleable.GradeImgView_rightPadding, 0);

            //↑边距
            topPadding = ta.getDimension(R.styleable.GradeImgView_topPadding, 0);

            //↓边距
            bottomPadding = ta.getDimension(R.styleable.GradeImgView_bottomPadding, 0);

            //获取属性完毕 释放资源
            ta.recycle();
        }
        mThreadName = Thread.currentThread().getName();
        initImgs();
    }

    private OnClickListener imgClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int tag = (int) v.getTag();
            setGradeNum(tag);
        }
    };

    private void initImgs() {
        for (int i = 0 ; i < max ; i++) {
            ImageView img = new ImageView(getContext());
            if (gradeNum > i) {
                img.setImageResource(pitchImg);
            } else {
                img.setImageResource(notImg);
            }
            if (openGrade) {
                img.setTag(i + 1);
                img.setOnClickListener(imgClickListener);
            }
            addView(img);
            LinearLayout.LayoutParams layoutParams = (LayoutParams) img.getLayoutParams();
            layoutParams.setMargins((int) leftPadding, (int) topPadding, (int) rightPadding, (int) bottomPadding);
            img.setLayoutParams(layoutParams);
        }
        reDraw();
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
        removeAllViews();
        initImgs();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 刷新
     */
    private void reDraw() {
        if (mThreadName.equals("main")) {
            invalidate();
        } else {
            postInvalidate();
        }
    }
}
