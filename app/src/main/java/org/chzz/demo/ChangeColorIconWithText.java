package org.chzz.demo;


import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by copy on 2017/7/27.
 */

public class ChangeColorIconWithText extends View {
    public ChangeColorIconWithText(Context context) {
        super(context);
    }

    public ChangeColorIconWithText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangeColorIconWithText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
