package com.vpaliy.flip_concept;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;

import butterknife.ButterKnife;

public class SignUpFragment extends AuthFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.sign_up_fragment,container,false);
        ButterKnife.bind(this,root);
        return root;
    }

    @Override
    public void fireAnimation() {
        float offsetX=parent.getWidth()-(parent.getWidth()-first.getLeft()+getResources().getDimension(R.dimen.option_size));
        first.setTranslationX(-offsetX);
        second.setTranslationX(-offsetX);
        last.setTranslationX(-offsetX);
        controller.setTranslationX(-controller.getWidth());
        ObjectAnimator firstAnimator=ObjectAnimator.ofFloat(first,View.TRANSLATION_X,0);
        ObjectAnimator secondAnimator=ObjectAnimator.ofFloat(second,View.TRANSLATION_X,0);
        ObjectAnimator lastAnimator=ObjectAnimator.ofFloat(last,View.TRANSLATION_X,0);
        ObjectAnimator buttonAnimator=ObjectAnimator.ofFloat(controller,View.TRANSLATION_X,0);

        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.setStartDelay(500);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.playTogether(firstAnimator,secondAnimator,lastAnimator,buttonAnimator);
        animatorSet.start();

    }

    private ConstraintLayout.LayoutParams getParams(View view){
        return ConstraintLayout.LayoutParams.class.cast(view.getLayoutParams());
    }
}
