package com.vpaliy.loginconcept;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextPaint;
import android.util.AttributeSet;

public class VerticalTextView extends AppCompatTextView {

  private boolean isVerticalText = false;
  private boolean topDown = false;

  public VerticalTextView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    if (attrs != null) {
      TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.VerticalTextView);
      isVerticalText = array.getBoolean(R.styleable.VerticalTextView_is_vertical_text, false);
      topDown = array.getBoolean(R.styleable.VerticalTextView_top_down, false);
      array.recycle();
    }
  }

  public VerticalTextView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public VerticalTextView(Context context) {
    super(context);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    // vise versa
    if (isVerticalText) {
      int height = getMeasuredWidth();
      int width = getMeasuredHeight();
      setMeasuredDimension(width, height);
    }
  }

  public void setVerticalText(boolean verticalText) {
    isVerticalText = verticalText;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (isVerticalText) {
      TextPaint textPaint = getPaint();
      textPaint.setColor(getCurrentTextColor());
      textPaint.drawableState = getDrawableState();

      canvas.save();

      if (topDown) {
        canvas.translate(getWidth(), 0);
        canvas.rotate(90);
      } else {
        canvas.translate(0, getHeight());
        canvas.rotate(-90);
      }

      canvas.translate(getCompoundPaddingLeft(), getExtendedPaddingTop());

      getLayout().draw(canvas);
      canvas.restore();
    } else {
      super.onDraw(canvas);
    }
  }

}