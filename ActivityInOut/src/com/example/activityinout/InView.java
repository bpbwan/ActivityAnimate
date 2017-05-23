package com.example.activityinout;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class InView extends FrameLayout {
	AnimateApdter animate = null;
	private boolean firstIn = true;

	public InView(Context paramContext) {
		this(paramContext, null, 0);
	}

	public InView(Context paramContext, AttributeSet paramAttributeSet) {
		this(paramContext, paramAttributeSet, 0);
	}

	public InView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
		setBackgroundColor(0);
		this.firstIn = true;
	}

	protected void dispatchDraw(Canvas paramCanvas) {
		if (this.firstIn) {
			this.firstIn = false;
			this.animate.setUpwidthAndHeigh(getWidth(), getHeight());
			this.animate.startAnimate();
		}
		if ((this.animate != null) && (this.animate.isAnimating())) {
			super.dispatchDraw(this.animate.getIntersectCanvas());
			this.animate.intersectDrawing();
			this.animate.dispathDraw(paramCanvas);
			return;
		}
		super.dispatchDraw(paramCanvas);
	}

	protected void onDraw(Canvas paramCanvas) {
	}

	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	protected void onMeasure(int paramInt1, int paramInt2) {
		super.onMeasure(paramInt1, paramInt2);
	}

	public void setAnimateApdter(AnimateApdter paramAnimateApdter) {
		this.animate = paramAnimateApdter;
	}
}
