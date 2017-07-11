package com.vpaliy.flip_concept;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    public void fireAnimation() {

    }
}
