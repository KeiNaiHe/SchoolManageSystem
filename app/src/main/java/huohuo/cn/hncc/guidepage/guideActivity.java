package huohuo.cn.hncc.guidepage;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;


import huohuo.cn.hncc.loginpage.LoginActivity;


/**
 * Created by Windows on 2018/4/27.
 * 引导页面
 * 三秒钟进入登录界面
 */

public class guideActivity extends Activity {

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    });
    private AutoZoomImageView mIv_image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        initView();
        startAnim();
    }

    private void initView() {
        mIv_image = (AutoZoomImageView) findViewById(R.id.iv_guide);
    }

    private void startAnim() {
        //这是什么写法？
        mIv_image.post(new Runnable() {//iv即AutoZoomInImageView
            @Override
            public void run() {
                //简单方式启动放大动画
                //    iv.init()
                //    .startZoomInByScaleDeltaAndDuration(0.3f, 1000, 1000);
                // 放大增量是0.3，放大时间是1000毫秒，放大开始时间是1000毫秒以后
                //使用较为具体的方式启动放大动画
                mIv_image.init()
                        .setScaleDelta(0.2f)//放大的系数是原来的（1 + 0.3）倍
                        .setDurationMillis(1500)//动画的执行时间为1000毫秒
                        .setOnZoomListener(new AutoZoomImageView.OnZoomListener() {
                            @Override
                            public void onStart(View view) {
                                //放大动画开始时的回调
                            }

                            @Override
                            public void onUpdate(View view, float progress) {
                                //放大动画进行过程中的回调 progress取值范围是[0,1]
                            }

                            @Override
                            public void onEnd(View view) {
                                //放大动画结束时的回调
                                Intent intent = new Intent(guideActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .start(1000);//延迟1000毫秒启动
            }

        });
    }

    private void vanishView(final View view) {
        if (view == null) return;
        ValueAnimator va = ValueAnimator.ofFloat(1f, 0f);
        va.setDuration(500);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float alpha = (Float) animation.getAnimatedValue();
                if (alpha != null) {
                    view.setAlpha(alpha);
                }
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        va.start();
    }
}
