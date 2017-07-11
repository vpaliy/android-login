package com.vpaliy.loginconcept;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewPager pager=ViewPager.class.cast(findViewById(R.id.pager));
        AuthAdapter adapter=new AuthAdapter(getSupportFragmentManager(),pager);
        int left=150;
        int right=40;
        pager.setClipToPadding(false);
        pager.setAdapter(adapter);
    }
}
