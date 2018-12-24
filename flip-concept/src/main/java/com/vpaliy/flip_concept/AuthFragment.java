package com.vpaliy.flip_concept;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class AuthFragment extends Fragment {

  protected Callback callback;

  @BindView(R.id.controller)
  protected TextView controller;

  @BindView(R.id.parent)
  protected ViewGroup parent;

  @BindView(R.id.first)
  protected View first;

  @BindView(R.id.second)
  protected View second;

  @BindView(R.id.last)
  protected View last;

  @BindView(R.id.focus_hider)
  protected View logo;


  public void setCallback(@NonNull Callback callback) {
    this.callback = callback;
  }

  public abstract void fireAnimation();

  public abstract void cleaFocus();

  interface Callback {
    void remove(AuthFragment fragment);
  }

  public SpannableStringBuilder mergeColoredText(String leftPart, String rightPart, int leftColor, int rightColor) {
    final SpannableStringBuilder builder = new SpannableStringBuilder();
    final SpannableString leftPartSpannable = new SpannableString(leftPart.toUpperCase());
    final SpannableString rightPartSpannable = new SpannableString(rightPart.toUpperCase());
    leftPartSpannable.setSpan(new ForegroundColorSpan(leftColor), 0, leftPart.length(), 0);
    rightPartSpannable.setSpan(new ForegroundColorSpan(rightColor), 0, rightPart.length(), 0);
    return builder.append(leftPartSpannable).append("  ").append(rightPartSpannable);
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    KeyboardVisibilityEvent.setEventListener(getActivity(), isOpen -> {
      final float scale = isOpen ? 0.75f : 1f;
      ViewCompat.animate(logo)
              .scaleX(scale)
              .scaleY(scale)
              .setDuration(300)
              .start();
      if (!isOpen) cleaFocus();
    });
  }

  @OnClick(R.id.controller)
  public void makeTransition() {
    if (callback != null) {
      callback.remove(this);
    }
  }
}
