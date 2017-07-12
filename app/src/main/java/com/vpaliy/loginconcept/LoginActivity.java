package com.vpaliy.loginconcept;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindViews(value = {R.id.logo,R.id.first,R.id.second,R.id.last})
    protected List<View> sharedElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        AnimatedViewPager pager= ButterKnife.findById(this,R.id.pager);
        ImageView background=ButterKnife.findById(this,R.id.scrolling_background);
        int[] screenSize=screenSize();
        Glide.with(this)
                .load(R.drawable.busy)
                .asBitmap()
                .override(screenSize[0]*2,screenSize[1])
                .into(background);
        AuthAdapter adapter=new AuthAdapter(getSupportFragmentManager(),pager,background,sharedElements);
        pager.setAdapter(adapter);
    }

    private int[] screenSize(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return new int[]{size.x,size.y};
    }

}
