package com.vpaliy.loginconcept;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
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
        View root=inflater.inflate(authLayout(),container,false);
        ButterKnife.bind(this,root);
        KeyboardVisibilityEvent.setEventListener(getActivity(), new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                callback.scale(isOpen);
                if(!isOpen){
                   clearFocus();
                }
            }
        });
        return root;
    }

    public void setCallback(@NonNull Callback callback) {
        this.callback = callback;
    }

    @LayoutRes
    public abstract int authLayout();
    public abstract void fold();
    public abstract void clearFocus();
    public abstract Transition unfoldTransition();

    @OnClick(R.id.caption)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void unfold(){
        Transition transition= unfoldTransition();
        transition.addListener(new TransitionAdapterListener(){
            @Override
            public void onTransitionEnd(Transition transition) {
                super.onTransitionEnd(transition);
                caption.setVerticalText(false);
                caption.setRotation(0);
                caption.requestLayout();
            }
        });
        TransitionManager.beginDelayedTransition(parent, transition);
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
        void scale(boolean hasFocus);
    }

}
