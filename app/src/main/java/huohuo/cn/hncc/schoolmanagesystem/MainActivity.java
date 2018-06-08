package huohuo.cn.hncc.schoolmanagesystem;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.dynamicpage.DynamicMainFragment;
import huohuo.cn.hncc.schoolmanagesystem.homepage.HomeFragment;
import huohuo.cn.hncc.schoolmanagesystem.messagepage.MessageFragment;
import huohuo.cn.hncc.schoolmanagesystem.mypage.MyFragment;
import huohuo.cn.hncc.schoolmanagesystem.publish.PublishActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageButton mIv_home;
    private ImageButton mIv_dynamic;
    private ImageButton mIv_message;
    private ImageButton mIv_me;
    private FrameLayout mFl_main;
    private MessageFragment mMessFragment;
    private MyFragment mMyFragment;
    private DynamicMainFragment mDynaragment;
    private HomeFragment mHomeFragment;
    private LinearLayout mLl_home;
    private LinearLayout mLl_dynamic;
    private LinearLayout mLl_message;
    private LinearLayout mLl_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        mLl_dynamic.setOnClickListener(this);
        mLl_home.setOnClickListener(this);
        mLl_me.setOnClickListener(this);
        mLl_message.setOnClickListener(this);

        getFragmentTrans();

        defaultFragment();
    }

    /**
     * 设置默认界面
     */
    private void defaultFragment() {
        FragmentTransaction trans = getFragmentTrans();
        if (mHomeFragment == null) {
            mHomeFragment = new HomeFragment();
        }
        trans.replace(R.id.fl_main_content, mHomeFragment);
        transportImageClick(mIv_home);
        trans.commit();
    }


    private void initView() {
        mIv_home = (ImageButton) findViewById(R.id.iv_main_home);
        mIv_dynamic = (ImageButton) findViewById(R.id.iv_main_dynamic);
        mIv_message = (ImageButton) findViewById(R.id.iv_main_message);
        mIv_me = (ImageButton) findViewById(R.id.iv_main_me);

        mLl_home = (LinearLayout) findViewById(R.id.ll_main_home);
        mLl_dynamic = (LinearLayout) findViewById(R.id.ll_main_dynamic);
        mLl_message = (LinearLayout) findViewById(R.id.ll_main_message);
        mLl_me = (LinearLayout) findViewById(R.id.ll_main_me);

        LinearLayout ll_publish = (LinearLayout) findViewById(R.id.ll_main_publish);
        mFl_main = (FrameLayout) findViewById(R.id.fl_main_content);


        ll_publish.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        FragmentTransaction trans;
        switch (v.getId()) {
            case R.id.ll_main_dynamic://动态
                trans = getFragmentTrans();
                if (mDynaragment == null) {
                    mDynaragment = new DynamicMainFragment();
                }
                trans.replace(R.id.fl_main_content, mDynaragment);
                transportImageClick(mIv_dynamic);
                trans.commit();
                break;
            case R.id.ll_main_home://首页
                trans = getFragmentTrans();
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                }
                trans.replace(R.id.fl_main_content, mHomeFragment);
                transportImageClick(mIv_home);
                trans.commit();
                break;
            case R.id.ll_main_me://我的
                trans = getFragmentTrans();
                if (mMyFragment == null) {
                    mMyFragment = new MyFragment();
                }
                trans.replace(R.id.fl_main_content, mMyFragment);
                transportImageClick(mIv_me);
                trans.commit();
                break;
            case R.id.ll_main_message://messsage
                trans = getFragmentTrans();
                if (mMessFragment == null) {
                    mMessFragment = new MessageFragment();
                }
                trans.replace(R.id.fl_main_content, mMessFragment);
                transportImageClick(mIv_message);
                trans.commit();
                break;
            case R.id.ll_main_publish:
                startActivity(new Intent(MainActivity.this, PublishActivity.class));
                //动画设置
//                overridePendingTransition(R.anim.publish_activity_anim, 0);
                break;
            default:
                break;

        }

    }


    /**
     * 点击底部图片切换
     */
    private void transportImageClick(ImageView iv) {
        mIv_home.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_home_normal));
        mIv_dynamic.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_dynamic_normal));
        mIv_message.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_message_normal));
        mIv_me.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_me_normal));
        if (iv == mIv_home) {
            iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_home_selected));
        } else if (iv == mIv_dynamic) {
            iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_dynamic_selected));
        } else if (iv == mIv_message) {
            iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_message_selected));
        } else {
            iv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.tab_me_selected));
        }
    }

    @SuppressLint("CommitTransaction")
    public FragmentTransaction getFragmentTrans() {
        FragmentManager supportManager = getSupportFragmentManager();
        return supportManager.beginTransaction();
    }


}
