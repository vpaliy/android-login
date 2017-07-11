package com.vpaliy.loginconcept;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

public class VerticalTextView extends AppCompatTextView {

    private int width, height;
    private final Rect bounds = new Rect();

    private boolean isVerticalText=false;

    public VerticalTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(attrs!=null){
            TypedArray array=getContext().obtainStyledAttributes(attrs,R.styleable.VerticalTextView);
            isVerticalText=array.getBoolean(R.styleable.VerticalTextView_is_vertical_text,false);
            array.recycle();
        }
    }

    public VerticalTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public VerticalTextView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // vise versa
        if(isVerticalText) {
            height = getMeasuredWidth()+10;
            width = getMeasuredHeight()+10;
            setMeasuredDimension(width, height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(isVerticalText) {
            canvas.save();

            canvas.translate(width, height);
            canvas.rotate(-90);

            TextPaint paint = getPaint();
            paint.setColor(getTextColors().getDefaultColor());

            String text = text();

            paint.getTextBounds(text, 0, text.length(), bounds);
            canvas.drawText(text, getCompoundPaddingLeft(), (bounds.height() - width) / 2, paint);

            canvas.restore();
        }else{
            super.onDraw(canvas);
        }
    }

    private String text() {
        return super.getText().toString();
    }
}