package com.example.activityinout;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class BoomView extends View{
	
	private boolean Using = false;
	private int MaxFrame = 12;
	private float scale = 1.0f;
	private int CurrentFrame = 0;
	private Rect FrameRect;
	Drawable AllFram;
	private Rect DrawingRect;
	public BoomView(Context context) {
		this(context, null);

	}
	

	
	public BoomView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		AllFram = getResources().getDrawable(R.drawable.animate_1);
		FrameRect = new Rect(0, 0, 150, 150);
		AllFram.setBounds(FrameRect);
		DrawingRect = new Rect();
		CurrentFrame = 2;
	}

	
	public synchronized boolean isUsing(){
		return Using;
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		// TODO Auto-generated method stub
	//	Log.d("bpb", "CurrentFrame "+CurrentFrame+" "+this.getHeight()+" "+this.getWidth());
		AllFram.setLevel(CurrentFrame);
		AllFram.draw(canvas);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
	
	}
	
	public void setFrame(int f){
		CurrentFrame = f;
		if(CurrentFrame <= MaxFrame){
			getDrawingRect(DrawingRect);
			this.postInvalidate(DrawingRect.left, DrawingRect.top, DrawingRect.right, DrawingRect.bottom);
		}else {
			Using = false;
			CurrentFrame = 0;
		}
	}
	
	public int getFrame(){
		return CurrentFrame;
	}
	
	
	public void startDrawFram(){
		if(!Using) {
			Using = true;
			CurrentFrame = 0;
			ObjectAnimator oa = new ObjectAnimator().ofInt(this, "Frame", MaxFrame+1);
			oa.setDuration(500);
			oa.start();
		}
	}
}
