package com.example.activityinout;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter
{
  public List<View> mListViews;

  public ViewPagerAdapter(List<View> paramList)
  {
    this.mListViews = paramList;
  }

  public void destroyItem(View paramView, int paramInt, Object paramObject)
  {
    ((ViewPager)paramView).removeView((View)this.mListViews.get(paramInt));
  }

  public void finishUpdate(View paramView)
  {
  }

  public int getCount()
  {
    return this.mListViews.size();
  }

  public Object instantiateItem(View paramView, int paramInt)
  {
    ((ViewPager)paramView).addView((View)this.mListViews.get(paramInt), 0);
    return this.mListViews.get(paramInt);
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == paramObject;
  }

  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
  {
  }

  public Parcelable saveState()
  {
    return null;
  }

  public void startUpdate(View paramView)
  {
  }
}

/* Location:           C:\Users\Administrator\Desktop\dex2jar-2.0\classes-dex2jar.jar
 * Qualified Name:     com.example.activityinout.ViewPagerAdapter
 * JD-Core Version:    0.6.2
 */