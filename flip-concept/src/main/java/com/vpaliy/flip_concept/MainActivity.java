package com.vpaliy.flip_concept;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AnimatedViewPager pager= ButterKnife.findById(this,R.id.pager);
        pager.setAdapter(new AuthAdapter(getSupportFragmentManager(),pager));
    }
}
