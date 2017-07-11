package com.vpaliy.flip_concept;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

public class AuthAdapter extends FragmentStatePagerAdapter
        implements AuthFragment.Callback{

    private AnimatedViewPager pager;
    private AuthFragment signUp;
    private AuthFragment logIn;

    public AuthAdapter(FragmentManager manager, AnimatedViewPager pager){
        super(manager);
        this.pager=pager;
        this.pager.setDuration(10000);
        this.pager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                onPreTransform(page,position);
                final float width = page.getWidth();
                final float height = page.getHeight();
                final float rotation = -15f * position * -1.25f;

                page.setPivotX(width * 0.5f);
                page.setPivotY(height);
                page.setRotation(rotation);
            }

            private  void onPreTransform(View view, float position) {
                view.setRotationX(0);
                view.setRotationY(0);
                view.setRotation(0);
                view.setScaleX(1);
                view.setScaleY(1);
                view.setPivotX(0);
                view.setPivotY(0);
                view.setTranslationY(0);
                view.setTranslationX(0f);
                view.setAlpha(position <= -1f || position >= 1f ? 0f : 1f);
            }
        });
    }

    @Override
    public AuthFragment getItem(int position) {
        if(position==0){
            if(logIn==null) logIn=new LogInFragment();
            logIn.setCallback(this);
            return logIn;
        }else if(signUp==null){
            signUp=new SignUpFragment();
            signUp.setCallback(this);
        }
        return signUp;
    }

    @Override
    public void remove(AuthFragment fragment) {
        if(logIn==fragment){
            pager.setCurrentItem(1,true);
            signUp.fireAnimation();
        }else{
            pager.setCurrentItem(0,true);
            logIn.fireAnimation();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
