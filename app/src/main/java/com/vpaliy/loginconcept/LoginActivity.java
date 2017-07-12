package com.vpaliy.loginconcept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnimatedViewPager pager= ButterKnife.findById(this,R.id.pager);
        ImageView background=ButterKnife.findById(this,R.id.scrolling_background);
        AuthAdapter adapter=new AuthAdapter(getSupportFragmentManager(),pager,background);
        pager.setAdapter(adapter);
    }
}
