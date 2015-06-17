/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.chen.cxy.utils.xListView;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.chen.cxy.R;
public class XListViewHeader extends LinearLayout {
	private LinearLayout mContainer;
	private ImageView mArrowImageView;
	private ProgressBar mProgressBar;
	private TextView mHintTextView;
	private int mState = STATE_NORMAL;

	private ImageView leftView;
	private ImageView rightView;


	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;
	private Animation mRotateUpAnim2;
	private Animation mRotateDownAnim2;

	private final int ROTATE_ANIM_DURATION = 180;
	
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;

	public XListViewHeader(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public XListViewHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		// 初始情况，设置下拉刷新view高度为0
		LayoutParams lp = new LayoutParams(
				LayoutParams.FILL_PARENT, 0);
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.xlistview_header1, null);
		addView(mContainer, lp);
		setGravity(Gravity.BOTTOM);

		leftView = (ImageView)findViewById(R.id.xlistview_header_left);
		rightView = (ImageView)findViewById(R.id.xlistview_header_right);
		mHintTextView = (TextView)findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (ProgressBar)findViewById(R.id.xlistview_header_progressbar);

		
		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);


		//新改的效果
		mRotateUpAnim2 = new RotateAnimation(0.0f, 360.0f,
				Animation.RELATIVE_TO_SELF, 1.0f, Animation.RELATIVE_TO_SELF,
				1.0f);
		mRotateUpAnim2.setDuration(500);
		mRotateUpAnim2.setFillAfter(true);
		mRotateUpAnim2.setRepeatCount(-1);//重复执行
		mRotateUpAnim2.setRepeatMode(1);//重复方式
		mRotateUpAnim2.setInterpolator(new LinearInterpolator());//不停顿
		//RotateAnimation(开始角度,到角度,旋转针对,旋转中心距离view的左顶点为x%距离,旋转针对,旋转中心距离view的上边边缘顶点为x%距离)
		mRotateDownAnim2 = new RotateAnimation(0.0f, 360.0f,
				Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
				1.0f);
		mRotateDownAnim2.setDuration(5000);
		mRotateDownAnim2.setFillAfter(true);
		mRotateDownAnim2.setRepeatCount(-1);//重复执行
		mRotateDownAnim2.setRepeatMode(1);//重复方式
		mRotateDownAnim2.setInterpolator(new LinearInterpolator());//不停顿

	}

	public void setState(int state) {
		if (state == mState) return ;
		
		if (state == STATE_REFRESHING) {	// 显示进度
			//mArrowImageView.clearAnimation();
			//mArrowImageView.setVisibility(View.INVISIBLE);
			leftView.clearAnimation();
			rightView.clearAnimation();
			leftView.setVisibility(View.VISIBLE);
			rightView.setVisibility(View.VISIBLE);


			//mProgressBar.setVisibility(View.VISIBLE);
		} else {	// 显示箭头图片
			//mArrowImageView.setVisibility(View.VISIBLE);
			leftView.setVisibility(View.VISIBLE);
			rightView.setVisibility(View.VISIBLE);

			//mProgressBar.setVisibility(View.INVISIBLE);
		}
		
		switch(state){
		case STATE_NORMAL:
			if (mState == STATE_READY) {
				//mArrowImageView.startAnimation(mRotateDownAnim);
				//leftView.startAnimation(mRotateUpAnim2);
				//rightView.startAnimation(mRotateDownAnim2);
				//leftView.clearAnimation();
				//rightView.clearAnimation();

			}
			if (mState == STATE_REFRESHING) {
				//mArrowImageView.clearAnimation();
				//leftView.clearAnimation();
				//rightView.clearAnimation();

			}
			leftView.setVisibility(View.VISIBLE);
			rightView.setVisibility(View.VISIBLE);
			mHintTextView.setText(R.string.xlistview_header_hint_normal);
			break;
		case STATE_READY:
			if (mState != STATE_READY) {
				//mArrowImageView.clearAnimation();
				//leftView.clearAnimation();
				//rightView.clearAnimation();

				//mArrowImageView.startAnimation(mRotateUpAnim);
				//leftView.startAnimation(mRotateUpAnim2);
				//rightView.startAnimation(mRotateDownAnim2);
				leftView.setVisibility(View.VISIBLE);
				rightView.setVisibility(View.VISIBLE);
				mHintTextView.setText(R.string.xlistview_header_hint_ready);
			}
			break;
		case STATE_REFRESHING:
			leftView.clearAnimation();
			rightView.clearAnimation();
			leftView.startAnimation(mRotateUpAnim2);
			rightView.startAnimation(mRotateDownAnim2);
			mHintTextView.setText(R.string.xlistview_header_hint_loading);
			break;
			default:
		}
		
		mState = state;
	}
	
	public void setVisiableHeight(int height) {
		if (height < 0)
			height = 0;
		LayoutParams lp = (LayoutParams) mContainer
				.getLayoutParams();
		lp.height = height;
		mContainer.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
		return mContainer.getLayoutParams().height;
	}

}
