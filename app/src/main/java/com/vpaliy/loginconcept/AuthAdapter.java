package com.vpaliy.loginconcept;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import java.util.List;
import java.util.Objects;

public class AuthAdapter extends FragmentStatePagerAdapter
        implements AuthFragment.Callback {

  private final AnimatedViewPager pager;
  private final SparseArray<AuthFragment> authArray;
  private final List<ImageView> sharedElements;
  private final ImageView authBackground;
  private float factor;

  AuthAdapter(FragmentManager manager,
              AnimatedViewPager pager,
              ImageView authBackground,
              List<ImageView> sharedElements) {
    super(manager);
    this.authBackground = authBackground;
    this.pager = pager;
    this.authArray = new SparseArray<>(getCount());
    this.sharedElements = sharedElements;
    pager.setDuration(350);
    final float textSize = pager.getResources().getDimension(R.dimen.folded_size);
    final float textPadding = pager.getResources().getDimension(R.dimen.folded_label_padding);
    factor = 1 - (textSize + textPadding) / (pager.getWidth());
  }

  @Override
  public AuthFragment getItem(int position) {
    AuthFragment fragment = authArray.get(position);
    if (fragment == null) {
      fragment = position != 1 ? new LogInFragment() : new SignUpFragment();
      authArray.put(position, fragment);
      fragment.setCallback(this);
    }
    return fragment;
  }

  @Override
  public void show(AuthFragment fragment) {
    final int index = authArray.keyAt(authArray.indexOfValue(fragment));
    pager.setCurrentItem(index, true);
    shiftSharedElements(getPageOffsetX(fragment), index == 1);
    for (int jIndex = 0; jIndex < authArray.size(); jIndex++) {
      if (jIndex != index) {
        authArray.get(jIndex).fold();
      }
    }
  }

  private float getPageOffsetX(AuthFragment fragment) {
    int pageWidth = Objects.requireNonNull(fragment.getView()).getWidth();
    return pageWidth - pageWidth * factor;
  }

  private void shiftSharedElements(float pageOffsetX, boolean forward) {
    final Context context = pager.getContext();
    //since we're clipping the page, we have to adjust the shared elements
    AnimatorSet shiftAnimator = new AnimatorSet();
    for (View view : sharedElements) {
      float translationX = forward ? pageOffsetX : -pageOffsetX;
      float temp = view.getWidth() / 3f;
      translationX -= forward ? temp : -temp;
      ObjectAnimator shift = ObjectAnimator.ofFloat(view, View.TRANSLATION_X, 0, translationX);
      shiftAnimator.playTogether(shift);
    }

    int color = ContextCompat.getColor(context, forward ? R.color.color_logo_sign_up : R.color.color_logo_log_in);
    DrawableCompat.setTint(sharedElements.get(0).getDrawable(), color);
    //scroll the background by x
    int offset = authBackground.getWidth() / 2;
    ObjectAnimator scrollAnimator = ObjectAnimator.ofInt(authBackground, "scrollX", forward ? offset : -offset);
    shiftAnimator.playTogether(scrollAnimator);
    shiftAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
    shiftAnimator.setDuration(pager.getResources().getInteger(R.integer.duration) / 2);
    shiftAnimator.start();
  }

  @Override
  public void scale(boolean hasFocus) {

    final float scale = hasFocus ? 1 : 1.4f;
    final float logoScale = hasFocus ? 0.75f : 1f;
    View logo = sharedElements.get(0);

    AnimatorSet scaleAnimation = new AnimatorSet();
    scaleAnimation.playTogether(ObjectAnimator.ofFloat(logo, View.SCALE_X, logoScale));
    scaleAnimation.playTogether(ObjectAnimator.ofFloat(logo, View.SCALE_Y, logoScale));
    scaleAnimation.playTogether(ObjectAnimator.ofFloat(authBackground, View.SCALE_X, scale));
    scaleAnimation.playTogether(ObjectAnimator.ofFloat(authBackground, View.SCALE_Y, scale));
    scaleAnimation.setDuration(200);
    scaleAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    scaleAnimation.start();
  }

  @Override
  public float getPageWidth(int position) {
    return factor;
  }

  @Override
  public int getCount() {
    return 2;
  }
}
