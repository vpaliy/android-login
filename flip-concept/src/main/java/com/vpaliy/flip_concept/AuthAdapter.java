package com.vpaliy.flip_concept;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class AuthAdapter extends FragmentStatePagerAdapter
        implements AuthFragment.Callback {

  private AnimatedViewPager pager;
  private AuthFragment signUp;
  private AuthFragment logIn;

  private FlipTransformer transformer;

  public AuthAdapter(FragmentManager manager, AnimatedViewPager pager) {
    super(manager);
    this.pager = pager;
    transformer = new FlipTransformer(160);
    this.pager.setDuration(200);
    this.pager.setPageTransformer(true, transformer);
  }

  @Override
  public AuthFragment getItem(int position) {
    if (position == 0) {
      if (logIn == null) logIn = new LogInFragment();
      logIn.setCallback(this);
      return logIn;
    } else if (signUp == null) {
      signUp = new SignUpFragment();
      signUp.setCallback(this);
    }
    return signUp;
  }

  @Override
  public void remove(AuthFragment fragment) {
    if (logIn == fragment) {
      transformer.setMovingForward(true);
      pager.setCurrentItem(1, true);
      signUp.fireAnimation();
    } else {
      transformer.setMovingForward(false);
      pager.setCurrentItem(0, true);
      logIn.fireAnimation();
    }
  }

  @Override
  public int getCount() {
    return 2;
  }
}
