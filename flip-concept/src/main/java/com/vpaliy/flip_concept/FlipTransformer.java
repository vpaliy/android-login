package com.vpaliy.flip_concept;


import android.support.v4.view.ViewPager;
import android.util.FloatProperty;
import android.util.Log;
import android.view.View;

public class FlipTransformer implements ViewPager.PageTransformer {

  private boolean movingForward = true;
  private float minAlpha;
  private int degrees;
  private float distanceToCentreFactor;


  public FlipTransformer(int degrees) {
    this(degrees, 0.7f);
  }

  public FlipTransformer(int degrees, float minAlpha) {
    this.degrees = degrees;
    distanceToCentreFactor = (float) Math.tan(Math.toRadians(degrees / 2)) / 2;
    this.minAlpha = minAlpha;
  }

  public void transformPage(View view, float position) {
    int pageWidth = view.getWidth();
    int pageHeight = view.getHeight();
    view.setPivotX((float) pageWidth / 2);
    view.setPivotY((float) (pageHeight + pageWidth * distanceToCentreFactor));


    if (position < -1) { //[-infinity,1)
      view.setRotation(0);
      view.setAlpha(0);
    } else if (position <= 1) { //[-1,1]
      view.setTranslationX((-position) * pageWidth); //shift the view over
      if (movingForward) {
        if (position >= 0) {
          view.setRotation(position * (180 - degrees)); //rotate it
          // Fade the page relative to its distance from the center
          view.setAlpha(Math.max(minAlpha, 1 - Math.abs(position) / 3));
        } else {
          Log.d("Flip", Float.toString(position));
          view.setAlpha(1 + position);
        }
      } else if (position <= 0) {
        view.setRotation(position * (180 - degrees)); //rotate it
        // Fade the page relative to its distance from the center
        view.setAlpha(Math.max(minAlpha, 1 - Math.abs(position) / 3));
      } else {
        Log.d("Flip", Float.toString(position));
        view.setAlpha(1 - position);
      }
    } else { //(1, +infinity]
      view.setRotation(0);
      view.setAlpha(0);
    }
  }

  public void setMovingForward(boolean movingForward) {
    this.movingForward = movingForward;
  }
}
