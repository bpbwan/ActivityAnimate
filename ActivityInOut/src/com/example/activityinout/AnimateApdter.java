package com.example.activityinout;

import android.graphics.Canvas;

public abstract interface AnimateApdter
{
  public abstract void cancleAnimate();

  public abstract void dispathDraw(Canvas paramCanvas);

  public abstract Canvas getIntersectCanvas();

  public abstract void intersectDrawing();

  public abstract boolean isAnimating();

  public abstract void setUpwidthAndHeigh(int paramInt1, int paramInt2);

  public abstract void startAnimate();
}