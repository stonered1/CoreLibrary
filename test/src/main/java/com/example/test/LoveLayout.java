package com.example.test;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.test.util.BezierEvalutor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author Stone
 * @version V1.0
 * @description:
 * @date 17/6/18 20:30
 * @email redsstone@163.com
 */
public class LoveLayout extends RelativeLayout {

    private Drawable firstDrawable;
    private Drawable secondDrawable;
    private Drawable threeDrawable;


    private int dHeight;//爱心的高度
    private int dWidth;//爱心的宽度

    private int mHeight; //整个布局的高度
    private int mWidth; //整个布局的宽度


    List<Drawable> drawables = new ArrayList<>();

    private LayoutParams params;
    private Random random = new Random();

    public LoveLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }

    private void initView() {
        firstDrawable = getResources().getDrawable(R.mipmap.pic1);
        secondDrawable = getResources().getDrawable(R.mipmap.pic2);
        threeDrawable = getResources().getDrawable(R.mipmap.pic3);
        drawables.add(firstDrawable);
        drawables.add(secondDrawable);
        drawables.add(threeDrawable);

        dHeight = firstDrawable.getIntrinsicHeight();
        dWidth = firstDrawable.getIntrinsicWidth();
        params = new LayoutParams(dWidth, dHeight);

        params.addRule(CENTER_HORIZONTAL, TRUE);
        params.addRule(ALIGN_PARENT_BOTTOM, TRUE);

    }

    AnimatorSet set;
    public void addLove() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageDrawable(drawables.get(random.nextInt(3)));
        imageView.setLayoutParams(params);
        addView(imageView);

         set = getAmimator(imageView);
        set.start();


    }

    public void stop() {
        set.end();
    }


    private AnimatorSet getAmimator(final ImageView imageView) {

        ObjectAnimator alphaAnimator= ObjectAnimator.ofFloat(imageView, "alpha", 0.3f,1.0f);
        ObjectAnimator scaleXAnimator= ObjectAnimator.ofFloat(imageView, "scaleX", 0.2f,1.0f);
        ObjectAnimator scaleYAnimator= ObjectAnimator.ofFloat(imageView, "scaleY", 0.2f,1.0f);
        AnimatorSet mAnimatorSet=new AnimatorSet();
        mAnimatorSet.setDuration(500);
        //三个动画同时集合
        mAnimatorSet.playTogether(alphaAnimator,scaleXAnimator,scaleYAnimator);
        mAnimatorSet.setTarget(imageView);
        //贝塞尔曲线动画,不断修改ImageView的坐标,PointF(x,y)
        ValueAnimator bezierValueAnimator=getBeziValueAnimator(imageView);//getBeziValueAnimator得到贝赛尔曲线轨迹位移动画
        AnimatorSet bezierAnimatorSet =new AnimatorSet();
        //按顺序播放动画
        bezierAnimatorSet.playSequentially(mAnimatorSet,bezierValueAnimator);//然后按顺序播放这些动画集合
        //bezierAnimatorSet.setDuration(3000);
        bezierAnimatorSet.setTarget(imageView);

        return bezierAnimatorSet;//返回一个整体爱心所有动画的集合
    }

    private ValueAnimator getBeziValueAnimator( final ImageView imageView) {

        PointF pointF0 = new PointF(mWidth/2, mHeight - dHeight);
        PointF pointF3 = new PointF(random.nextInt(mWidth), 0);
        PointF pointF1 = getPointF(1);
        PointF pointF2 = getPointF(2);

        BezierEvalutor b = new BezierEvalutor(pointF1, pointF2);

        ValueAnimator animator = ValueAnimator.ofObject(b, pointF0, pointF3);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF=(PointF) animation.getAnimatedValue();	//通过addUpdateListener监听事件实时获得从mBezierEvalutor估值器对象evalute方法实时计算出最新点的坐标	。
                imageView.setX(pointF.x);//然后去更新该爱心ImageView的X,Y坐标
                imageView.setY(pointF.y);
                imageView.setAlpha(1-animation.getAnimatedFraction());//getAnimatedFraction()就是mBezierEvalutor估值器对象中evaluate方法t即时间因子,从0~1变化,所以爱心透明度应该是从1~0变化正好到了顶部，t变为1，透明度变为0，即爱心消失
            }
        });

        animator.setTarget(imageView);
        animator.setDuration(3000);
        return animator;
    }



    private PointF getPointF(int i) {

        PointF pointF = new PointF();
        pointF.x = random.nextInt(mWidth);

        if (i==1) {
            pointF.y=random.nextInt(mHeight/2)+mHeight/2;//P1点Y轴坐标变化
        }else if(i==2){//P2点Y轴坐标变化
            pointF.y=random.nextInt(mHeight/2);
        }

        return pointF;

    }
}
