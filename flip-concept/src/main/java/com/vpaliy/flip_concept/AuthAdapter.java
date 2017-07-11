package com.vpaliy.flip_concept;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class AuthAdapter extends FragmentStatePagerAdapter
        implements AuthFragment.Callback{

    private ViewPager pager;
    private AuthFragment signUp;
    private AuthFragment logIn;

    public AuthAdapter(FragmentManager manager, ViewPager pager){
        super(manager);
        this.pager=pager;
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
            pager.setCurrentItem(1);
            signUp.fireAnimation();
        }else{
            pager.setCurrentItem(0);
            logIn.fireAnimation();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
