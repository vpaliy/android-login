package com.vpaliy.loginconcept;

import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.transition.TransitionManager;
import android.view.View;
import android.annotation.TargetApi;
import android.support.annotation.Nullable;

public class LogInFragment extends AuthFragment{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null){
            caption.setText(getString(R.string.log_in_label));
            caption.setTopDown(true);
            view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.purple_300));
        }
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fold() {
        TransitionManager.beginDelayedTransition(parent);
        caption.setVerticalText(true);
        caption.setScaleY(0.5f);
        caption.setScaleX(0.5f);
        ConstraintLayout.LayoutParams params=getParams();
        params.leftToLeft=ConstraintLayout.LayoutParams.UNSET;
        params.verticalBias=0.5f;
        caption.setLayoutParams(params);

    }
}
