package com.vpaliy.loginconcept;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogInFragment extends AuthFragment{

    @BindView(R.id.caption)
    protected TextView caption;

    @BindView(R.id.root)
    protected ViewGroup parent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.login_fragment,container,false);
        ButterKnife.bind(root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null){
            caption.setText(getString(R.string.log_in_label));
            view.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.purple_300));
        }
    }

    @OnClick(R.id.caption)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void unfold(){
        TransitionManager.beginDelayedTransition(parent);
        caption.setRotation(0f);
        ConstraintLayout.LayoutParams params=getParams();
        params.rightToRight=ConstraintLayout.LayoutParams.PARENT_ID;
        params.verticalBias=0.7f;
    }

    private ConstraintLayout.LayoutParams getParams(){
        return ConstraintLayout.LayoutParams.class.cast(caption.getLayoutParams());
    }

    @Override
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void fold() {
        TransitionManager.beginDelayedTransition(parent);
        caption.setRotation(90f);
        ConstraintLayout.LayoutParams params=getParams();
        params.rightToRight=ConstraintLayout.LayoutParams.UNSET;
        params.verticalBias=0.5f;

    }
}
