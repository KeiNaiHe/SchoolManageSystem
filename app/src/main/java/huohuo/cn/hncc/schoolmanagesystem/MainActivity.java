package huohuo.cn.hncc.schoolmanagesystem;


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


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageButton mIv_home;
    private ImageButton mIv_dynamic;
    private ImageButton mIv_message;
    private ImageButton mIv_me;
    private FrameLayout mFl_main;
    private FragmentBean mFragmentBean;
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
        loadFragment(trans, mIv_home);
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

        mFl_main = (FrameLayout) findViewById(R.id.fl_main_content);
    }


    @Override
    public void onClick(View v) {
        FragmentTransaction trans = getFragmentTrans();

        switch (v.getId()) {
            case R.id.ll_main_dynamic://动态

                loadFragment(trans, mIv_dynamic);
                break;
            case R.id.ll_main_home://首页

                loadFragment(trans, mIv_home);
                break;
            case R.id.ll_main_me://我的

                loadFragment(trans, mIv_me);
                break;
            case R.id.ll_main_message://messsage

                loadFragment(trans, mIv_message);
                break;
            default:
        }
    }

    /**
     * Fragment在这个方法里面切换
     */
    private void loadFragment(FragmentTransaction trans, ImageView image) {
        transportImageClick(image);

        if (mFragmentBean == null) {
            mFragmentBean = new FragmentBean();
        }

        if (image == mIv_home) {

//            if ((mHomeFragment = mFragmentBean.getHomeFragment()) == null) {
//                mHomeFragment = new HomeFragment();
////                //设置携带参数
//                mFragmentBean.setHomeFragment(mHomeFragment);
////                trans.add(R.id.fl_main_content, mHomeFragment);
//            }
//            trans.show(mHomeFragment);
            trans.replace(R.id.fl_main_content, new HomeFragment());
        } else if (image == mIv_dynamic) {

            if ( mFragmentBean.dynamicFragment == null) {
                //这个if只执行一次
                mFragmentBean.dynamicFragment = new DynamicMainFragment();
            }
            trans.replace(R.id.fl_main_content,mFragmentBean.dynamicFragment);

        } else if (image == mIv_message) {
            if ((mMessFragment = mFragmentBean.messFragment) == null) {
                mMessFragment = new MessageFragment();
                mFragmentBean.messFragment = mMessFragment;
                trans.add(R.id.fl_main_content, mMessFragment);
            }
            trans.show(mMessFragment);
        } else {
            if ((mMyFragment = mFragmentBean.myFragment) == null) {
                mMyFragment = new MyFragment();
                mFragmentBean.myFragment = mMyFragment;
                trans.add(R.id.fl_main_content, mMyFragment);
            }
            trans.show(mMyFragment);
        }
        trans.commit();
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

    public FragmentTransaction getFragmentTrans() {
        FragmentManager supportManager = getSupportFragmentManager();
        return supportManager.beginTransaction();
    }

     class FragmentBean {
        private HomeFragment homeFragment;
        private MessageFragment messFragment;
        private DynamicMainFragment dynamicFragment;
        private MyFragment myFragment;
    }
}
