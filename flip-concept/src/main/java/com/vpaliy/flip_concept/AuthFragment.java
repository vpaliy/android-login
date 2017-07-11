package com.vpaliy.flip_concept;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.transition.Transition;

public abstract class AuthFragment extends Fragment {

    protected Callback callback;

    public void setCallback(@NonNull Callback callback) {
        this.callback = callback;
    }

    //public abstract void fold();

    interface Callback {
        void show(AuthFragment fragment);
    }

}
