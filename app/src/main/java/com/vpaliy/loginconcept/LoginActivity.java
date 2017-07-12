package com.vpaliy.loginconcept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindViews(value = {R.id.first,R.id.second,R.id.last,R.id.logo})
    protected List<View> sharedElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AnimatedViewPager pager= ButterKnife.findById(this,R.id.pager);
        ImageView background=ButterKnife.findById(this,R.id.scrolling_background);
        AuthAdapter adapter=new AuthAdapter(getSupportFragmentManager(),pager,background,sharedElements);
        pager.setAdapter(adapter);
    }
}
