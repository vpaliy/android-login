package com.vpaliy.loginconcept;

import android.os.Build;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.support.annotation.Nullable;
import android.annotation.TargetApi;

import java.util.List;

import butterknife.BindViews;


public class SignUpFragment extends AuthFragment{

    @BindViews(value = {R.id.email_input_edit,
            R.id.password_input_edit,
            R.id.confirm_password_edit})
    protected List<View> views;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null){
            view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.color_sign_up));
            caption.setText(getString(R.string.sign_up_label));
            foldStuff();
        }
    }

    @Override
    public int authLayout() {
        return R.layout.sign_up_fragment;
    }

    @Override
    public void clearFocus() {
        views.forEach(View::clearFocus);
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fold() {
        lock=false;
        TransitionManager.beginDelayedTransition(parent);
        foldStuff();
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Transition unfoldTransition() {
        return TransitionInflater.from(getContext())
                .inflateTransition(R.transition.right_to_left);
    }

    private void foldStuff(){
        caption.setVerticalText(true);
        caption.setScaleY(0.5f);
        caption.setScaleX(0.5f);
        ConstraintLayout.LayoutParams params=getParams();
        params.rightToRight=ConstraintLayout.LayoutParams.UNSET;
        params.verticalBias=0.5f;
        caption.setLayoutParams(params);
    }
}
