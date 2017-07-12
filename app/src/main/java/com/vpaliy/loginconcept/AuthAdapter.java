package com.vpaliy.loginconcept;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import java.util.List;

public class AuthAdapter extends FragmentStatePagerAdapter
        implements AuthFragment.Callback{

    private AnimatedViewPager pager;
    private SparseArray<AuthFragment> authArray;
    private List<View> sharedElements;
    private ImageView authBackground;

    public AuthAdapter(FragmentManager manager,
                       AnimatedViewPager pager,
                       ImageView authBackground,
                       List<View> sharedElements){
        super(manager);
        this.authBackground=authBackground;
        this.pager=pager;
        this.authArray=new SparseArray<>(getCount());
        this.sharedElements=sharedElements;
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
        shiftSharedElements(getPageOffsetX(fragment,index), index==1);
        for(int jIndex=0;jIndex<authArray.size();jIndex++){
            if(jIndex!=index){
                authArray.get(jIndex).fold();
            }
        }
    }

    private float getPageOffsetX(AuthFragment fragment, int index){
        int pageWidth=fragment.getView().getWidth();
        return pageWidth-pageWidth*getPageWidth(index);
    }

    private void shiftSharedElements(float pageOffsetX, boolean forward){
        //since we're clipping the page, we have to adjust the shared elements
        AnimatorSet shiftAnimator=new AnimatorSet();
        for(View view:sharedElements){
            float translationX=forward?pageOffsetX:-pageOffsetX;
            float temp=pager.getResources().getDimension(R.dimen.option_size)/2;
            translationX+=!forward?temp:-temp;
            ObjectAnimator shift=ObjectAnimator.ofFloat(view,View.TRANSLATION_X,0,translationX);
            shiftAnimator.playTogether(shift);
        }
        //scroll the background by x
        int offset=authBackground.getWidth()/2;
        ObjectAnimator scrollAnimator=ObjectAnimator.ofInt(authBackground,"scrollX",forward?offset:-offset);
        shiftAnimator.playTogether(scrollAnimator);
        shiftAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        shiftAnimator.setDuration(pager.getResources().getInteger(R.integer.duration)/2);
        shiftAnimator.start();
    }

    @Override
    public void scale(boolean hasFocus) {
        float scale=hasFocus?1:1.4f;
        authBackground.animate()
                .scaleX(scale)
                .scaleY(scale)
                .setDuration(200)
                .start();
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
