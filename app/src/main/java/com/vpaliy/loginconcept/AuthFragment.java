package com.vpaliy.loginconcept;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import java.util.List;

public abstract class AuthFragment extends Fragment {

    protected Callback callback;

    public void setCallback(@NonNull Callback callback) {
        this.callback = callback;
    }

    interface Callback {
        void show(AuthFragment fragment);
    }

    public abstract void fold();
}
