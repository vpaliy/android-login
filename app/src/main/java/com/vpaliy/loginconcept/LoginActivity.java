package com.vpaliy.loginconcept;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewPager pager= ButterKnife.findById(this,R.id.pager);
        AuthAdapter adapter=new AuthAdapter(getSupportFragmentManager(),pager);
        pager.setAdapter(adapter);
    }
}
