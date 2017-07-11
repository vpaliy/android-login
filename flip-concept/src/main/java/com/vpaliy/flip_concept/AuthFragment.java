package com.vpaliy.flip_concept;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class AuthFragment extends Fragment {

    protected Callback callback;

    @BindView(R.id.controller)
    protected TextView controller;

    @BindView(R.id.parent)
    protected ViewGroup parent;

    @BindView(R.id.first)
    protected View first;

    @BindView(R.id.second)
    protected View second;

    @BindView(R.id.last)
    protected View last;


    public void setCallback(@NonNull Callback callback) {
        this.callback = callback;
    }

    public abstract void fireAnimation();

    interface Callback {
        void remove(AuthFragment fragment);
    }


    @OnClick(R.id.controller)
    public void makeTransition(){
        if(callback!=null){
            callback.remove(this);
        }
    }
}
