package org.chzz.demo;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by copy on 2017/7/27.
 */

public class ChangeColorIconWithText extends View {

    private int mColor = 0xFF45C01A;
    private Bitmap mIconBitmap;
    private String mText = "微信";
    private int mTextSize = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());

    //画布
    private Canvas mCanvas;

    private Bitmap mBitmap;
    //画笔
    private Paint mPaint;
    //透明度 渐变
    private float mAlpha;
    //图标矩形
    private Rect mIconRect;
    //文字矩形
    private Rect mTextBound;
    //文字的画笔
    private Paint mTextPaint;

    public ChangeColorIconWithText(Context context) {
        this(context,null);
    }

    public ChangeColorIconWithText(Context context, @Nullable AttributeSet attrs) {
      this(context,attrs,0);
    }

    public ChangeColorIconWithText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText);

        int n = typedArray.getIndexCount();

        for (int i = 0; i < n; i++) {
            int arrt = typedArray.getIndex(i);

            switch (arrt) {
                case R.styleable.ChangeColorIconWithText_icon:
                    BitmapDrawable drawable = (BitmapDrawable) typedArray.getDrawable(arrt);
                    mIconBitmap = drawable.getBitmap();
                    break;
                case R.styleable.ChangeColorIconWithText_name:
                    mText = typedArray.getString(arrt);
                    break;
                case R.styleable.ChangeColorIconWithText_textSize:
                    mTextSize = typedArray.getDimensionPixelSize(arrt, mTextSize);
                    break;
                case R.styleable.ChangeColorIconWithText_textColor:
                    mColor = typedArray.getColor(arrt, mColor);
                    break;
            }
            typedArray.recycle();

            mPaint =new Paint();
            mTextBound =new Rect();

            mPaint.setTextSize(mTextSize);
            mPaint.setColor(mColor);

            mPaint.getTextBounds(mText,0,mText.length(),mTextBound);
        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight(), getMeasuredHeight() - getPaddingTop()
                - getPaddingBottom() - mTextBound.height());

        int left = getMeasuredWidth() / 2 - iconWidth / 2;
        int top = getMeasuredHeight() / 2 - (mTextBound.height() + iconWidth)
                / 2;
        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mIconBitmap, null, mIconRect, null);
    }
}
