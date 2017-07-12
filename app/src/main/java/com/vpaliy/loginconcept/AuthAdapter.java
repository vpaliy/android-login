package com.vpaliy.loginconcept;

import android.animation.ObjectAnimator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

public class AuthAdapter extends FragmentStatePagerAdapter
        implements AuthFragment.Callback{

    private AnimatedViewPager pager;
    private SparseArray<AuthFragment> authArray;
    private ImageView authBackground;

    public AuthAdapter(FragmentManager manager,
                       AnimatedViewPager pager,
                       ImageView authBackground){
        super(manager);
        this.authBackground=authBackground;
        this.pager=pager;
        this.authArray=new SparseArray<>(getCount());
        authBackground.setScrollX(-authBackground.getWidth()/2);
        pager.setDuration(pager.getResources().getInteger(R.integer.duration));

    }

    @Override
    public AuthFragment getItem(int position) {
        AuthFragment fragment=authArray.get(position);
        if(fragment==null){
            fragment=position!=1?new LogInFragment():new SignUpFragment();
            authArray.put(position,fragment);
            fragment.setCallback(this);
        }
        return fragment;
    }

    @Override
    public void show(AuthFragment fragment) {
        final int index=authArray.keyAt(authArray.indexOfValue(fragment));
        pager.setCurrentItem(index,true);
        shiftBackground(index==1);
        for(int jIndex=0;jIndex<authArray.size();jIndex++){
            if(jIndex!=index){
                authArray.get(jIndex).fold();
            }
        }
    }

    private void shiftBackground(boolean forward){
        int offset=authBackground.getWidth()/2;
        ObjectAnimator scrollAnimator=ObjectAnimator.ofInt(authBackground,"scrollX",forward?offset:-offset);
        scrollAnimator.setDuration(authBackground.getResources().getInteger(R.integer.duration));
        scrollAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        scrollAnimator.start();
    }

    @Override
    public float getPageWidth(int position) {
        return 0.89f;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
