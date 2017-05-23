package com.example.activityinout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
  private void initView()
  {
    Object localObject = (InView)findViewById(2131361792);
    ((InView)localObject).setAnimateApdter(new DropAnimate((View)localObject));
    localObject = (ViewPager)findViewById(2131361794);
    View localView1 = getLayoutInflater().inflate(2130968578, null);
    View localView2 = getLayoutInflater().inflate(2130968578, null);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(localView1);
    localArrayList.add(localView2);
    ((ViewPager)localObject).setAdapter(new ViewPagerAdapter(localArrayList));
    ((Button)findViewById(2131361793)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Toast.makeText(MainActivity.this.getApplicationContext(), "µã»÷²âÊÔ£¡", 0).show();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968576);
    initView();
  }

  protected void onResume()
  {
    super.onResume();
  }
}


