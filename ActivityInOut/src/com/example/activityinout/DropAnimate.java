package com.example.activityinout;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DropAnimate implements AnimateApdter {
	private Bitmap Globalbmp;
	private boolean _animating = false;
	private int bmpH = -1;
	private int bmpW = -1;
	private Canvas can;
	Paint clearP;
	Matrix matrix;
	private View target;
	private float timeDuring = 100.0F;
	List<BitmapUnit> unit_list = new ArrayList();

	public DropAnimate(View paramView) {
		this.target = paramView;
		init();
	}

	private void init() {
		this.clearP = new Paint();
		clearP.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		this.matrix = new Matrix();
		BitmapUnit localBitmapUnit1 = new BitmapUnit("animate_0");
		BitmapUnit localBitmapUnit2 = new BitmapUnit("animate_1");
		BitmapUnit localBitmapUnit3 = new BitmapUnit("animate_2");
		BitmapUnit localBitmapUnit4 = new BitmapUnit("animate_0");
		BitmapUnit localBitmapUnit5 = new BitmapUnit("animate_1");
		BitmapUnit localBitmapUnit6 = new BitmapUnit("animate_2");
		this.unit_list.add(localBitmapUnit1);
		this.unit_list.add(localBitmapUnit2);
		this.unit_list.add(localBitmapUnit3);
		this.unit_list.add(localBitmapUnit4);
		this.unit_list.add(localBitmapUnit5);
		this.unit_list.add(localBitmapUnit6);
	}

	public void cancleAnimate() {
	}

	public void dispathDraw(Canvas paramCanvas) {
		int i = 0;
		int j = this.Globalbmp.getWidth() / this.unit_list.size();
		int k = this.Globalbmp.getHeight();
		Iterator localIterator = this.unit_list.iterator();
		while (true) {
			if (!localIterator.hasNext()) {
				if (isAnimating())
					this.target.postInvalidate();
				return;
			}
			BitmapUnit localBitmapUnit = (BitmapUnit) localIterator.next();
			//if (localBitmapUnit.isAnimating())
				localBitmapUnit.setUpBitmap(this.Globalbmp, i, 0, j, k);
			localBitmapUnit.dispathDraw(paramCanvas);
			i += j;
		}
		
	}

	public Canvas getIntersectCanvas() {
		this.can.drawPaint(this.clearP);
		return this.can;
	}

	public void intersectDrawing() {
	}

	public boolean isAnimating() {
		boolean bool = false;

		for(BitmapUnit u : unit_list){
			if(u.isAnimating()){
				bool = true;
				break;
			}
		}
		
		if(!bool){
			_animating = false;
		}
		return bool;
	}

	public DropAnimate setTimeDuring(float paramFloat) {
		this.timeDuring = paramFloat;
		return this;
	}

	public void setUpwidthAndHeigh(int paramInt1, int paramInt2) {
		Iterator localIterator = this.unit_list.iterator();
		if (this.Globalbmp == null) {
			this.bmpW = paramInt1;
			this.bmpH = paramInt2;
			Log.d("bpb", "setUpwidthAndHeigh " + this.bmpW + " " + this.bmpH);
			this.Globalbmp = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
			this.can = new Canvas(this.Globalbmp);
			
		}
		while (true) {
			if (!localIterator.hasNext())
				return;
			BitmapUnit localBitmapUnit = (BitmapUnit) localIterator.next();
			localBitmapUnit.setCallBack(new NcallBack());
			localBitmapUnit.setMaxHeight(this.bmpH);
		}
	}

	public void startAnimate() {
		if (!this._animating) {
			this._animating = true;
			((BitmapUnit) this.unit_list.get(0)).startAnimate();
		}
	}

	public class BitmapUnit {
		private int MaxHeight = 0;
		private Bitmap _bmp;
		DropAnimate.CallBack c;
		private Paint clearP = new Paint();
		private int clipX = 0;
		private int clipY = 0;
		private int fallhigh = 0;
		boolean flag = false;
		private Matrix matrix;
		public String tag;
		private long time = 500L;
		private int trigger = 0;

		public BitmapUnit(String arg2) {
			this.clearP.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
			this.matrix = new Matrix();
			this.tag = arg2;
		}

		public void dispathDraw(Canvas paramCanvas) {
			if (this._bmp != null) {
				this.matrix.setTranslate(this.clipX, this.fallhigh);
				paramCanvas.drawBitmap(this._bmp, this.matrix, null);
			}
		}

		public int getFallhigh() {
			return this.fallhigh;
		}

		public boolean isAnimating() {
			return this.flag;
		}

		public void setCallBack(DropAnimate.CallBack paramCallBack) {
			this.c = paramCallBack;
		}

		public void setDurationTime(long paramLong) {
			this.time = paramLong;
		}

		public void setFallhigh(int paramInt) {
			this.fallhigh = paramInt;
			if ((this.c != null) && (this.fallhigh >= this.trigger)) {
				this.trigger = 1000000;
				this.c.notifyChange(this);
			}
			if (this.fallhigh >= this.MaxHeight) {
				Log.d("bpb", " " + this.tag + " stopAnimate!");
				this.flag = false;
				if (this.c != null)
					this.c.notifyStopAnimate(this);
			}
		}

		public void setMaxHeight(int paramInt) {
			this.MaxHeight = 0;
			this.fallhigh = (-paramInt);
			setTriggerNum(this.MaxHeight - 100);
		}

		public void setTriggerNum(int paramInt) {
			this.trigger = paramInt;
		}

		public void setUpBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
			this.clipX = paramInt1;
			this.clipY = paramInt2;
			this._bmp = Bitmap.createBitmap(paramBitmap, this.clipX, this.clipY, paramInt3, paramInt4, this.matrix,
					true);
			this.matrix.setTranslate(this.clipX, this.clipY);
		}

		public void startAnimate() {
			if (!this.flag) {
				this.flag = true;
				ObjectAnimator localObjectAnimator = ObjectAnimator.ofInt(this, "fallhigh",
						new int[] { this.MaxHeight });
				localObjectAnimator.setDuration(this.time);
				localObjectAnimator.start();
			}
		}
	}

	public static abstract interface CallBack {
		public abstract void notifyChange(DropAnimate.BitmapUnit paramBitmapUnit);

		public abstract void notifyStopAnimate(DropAnimate.BitmapUnit paramBitmapUnit);
	}

	class NcallBack implements DropAnimate.CallBack {
		int animateStops = 0;

		NcallBack() {
		}

		public void notifyChange(DropAnimate.BitmapUnit paramBitmapUnit) {
			int i = DropAnimate.this.unit_list.indexOf(paramBitmapUnit) + 1;
			if (i < DropAnimate.this.unit_list.size())
				((DropAnimate.BitmapUnit) DropAnimate.this.unit_list.get(i)).startAnimate();
		}

		public void notifyStopAnimate(DropAnimate.BitmapUnit paramBitmapUnit) {
			this.animateStops += 1;
			if (this.animateStops == DropAnimate.this.unit_list.size())
				DropAnimate.this.target.setEnabled(true);
		}
	}
}