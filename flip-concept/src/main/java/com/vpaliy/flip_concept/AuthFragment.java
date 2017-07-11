package com.vpaliy.flip_concept;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import butterknife.OnClick;

public abstract class AuthFragment extends Fragment {

    protected Callback callback;

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
