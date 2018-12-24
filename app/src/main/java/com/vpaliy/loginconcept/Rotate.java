package com.vpaliy.loginconcept;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;

import com.transitionseverywhere.Transition;
import com.transitionseverywhere.TransitionValues;

import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;

public class Rotate extends Transition {

  private static final String PROPNAME_ROTATION = "vpaliy:rotate:rotation";

  private float startAngle;
  private float endAngle;

  public Rotate() {
  }

  public Rotate(Context context, AttributeSet attrs) {
    super(context, attrs);
    if (attrs != null) {
      TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Rotate);
      startAngle = array.getFloat(R.styleable.Rotate_start_angle, 0f);
      endAngle = array.getFloat(R.styleable.Rotate_end_angle, 0f);
      array.recycle();
    }
  }

  public void setEndAngle(float endAngle) {
    this.endAngle = endAngle;
  }

  public void setStartAngle(float startAngle) {
    this.startAngle = startAngle;
  }

  @Override
  public void captureStartValues(TransitionValues transitionValues) {
    transitionValues.values.put(PROPNAME_ROTATION, startAngle);
  }

  @Override
  public void captureEndValues(TransitionValues transitionValues) {
    transitionValues.values.put(PROPNAME_ROTATION, endAngle);
  }

  @Override
  public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues,
                                 TransitionValues endValues) {
    if (startValues == null || endValues == null) {
      return null;
    }
    final View view = endValues.view;
    float startRotation = (Float) startValues.values.get(PROPNAME_ROTATION);
    float endRotation = (Float) endValues.values.get(PROPNAME_ROTATION);
    if (startRotation != endRotation) {
      view.setRotation(startRotation);
      return ObjectAnimator.ofFloat(view, View.ROTATION,
              startRotation, endRotation);
    }
    return null;
  }
}