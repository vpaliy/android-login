package com.vpaliy.flip_concept;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.transition.AutoTransition;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import butterknife.BindView;
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
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fireAnimation() {
        final ConstraintLayout.LayoutParams params=getParams(first);
        params.horizontalBias=0.1f;
        controller.setVisibility(View.GONE);
        first.setLayoutParams(params);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Transition transition=new AutoTransition();
                transition.setInterpolator(new OvershootInterpolator());
                TransitionManager.beginDelayedTransition(parent,transition);
                controller.setVisibility(View.VISIBLE);
                params.horizontalBias=0.9f;
                first.setLayoutParams(params);
            }
        },150);


    }

    private ConstraintLayout.LayoutParams getParams(View view){
        return ConstraintLayout.LayoutParams.class.cast(view.getLayoutParams());
    }
}
