package com.vpaliy.loginconcept;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public abstract class AuthFragment extends Fragment {

    protected Callback callback;

    @BindView(R.id.caption)
    protected VerticalTextView caption;

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
        ButterKnife.bind(this,root);
        return root;
    }

    public void setCallback(@NonNull Callback callback) {
        this.callback = callback;
    }

    public abstract void fold();


    @OnClick(R.id.caption)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void unfold(){
        TransitionManager.beginDelayedTransition(parent);
        ConstraintLayout.LayoutParams params=getParams();
        params.rightToRight=ConstraintLayout.LayoutParams.PARENT_ID;
        params.leftToLeft=ConstraintLayout.LayoutParams.PARENT_ID;
        params.verticalBias=0.8f;
        caption.setScaleY(1f);
        caption.setScaleX(1f);
        caption.setRotation(-90);
        caption.setLayoutParams(params);
        callback.show(this);
    }

    protected ConstraintLayout.LayoutParams getParams(){
        return ConstraintLayout.LayoutParams.class.cast(caption.getLayoutParams());
    }

    interface Callback {
        void show(AuthFragment fragment);
    }

}
