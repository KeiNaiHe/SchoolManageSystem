package huohuo.cn.hncc.schoolmanagesystem.mypage;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/2.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout mRl_myInfo;
    private TextView mTv_myName;
    private TextView mTv_uid;
    private CircleImageView mHeadPortrait;
    private ImageView mSexImage;
    private LinearLayout mLl_attention;
    private TextView mTv_attention;
    private LinearLayout mLl_fans;
    private TextView mTv_fans;
    private LinearLayout mLl_authentication;
    private LinearLayout mLl_helper;
    private LinearLayout mLl_setting;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo, null);

        initServiceData();
        initView(view);
        initData();
        return view;
    }

    /**
     * 网络获取信息列表
     * username
     * uid
     * headPortrait
     * attentionCount
     * fansCount
     * identityCheck
     */
    private void initServiceData() {

    }

    private void initView(View view) {
        mRl_myInfo = view.findViewById(R.id.rl_myInfo_personInfo);
        mTv_myName = view.findViewById(R.id.tv_myInfo_myName);
        mTv_uid = view.findViewById(R.id.tv_myInfo_myID);
        mHeadPortrait = view.findViewById(R.id.civ_myInfo_headPortrait);
        mSexImage = view.findViewById(R.id.iv_myInfo_sexImage);
        mLl_attention = view.findViewById(R.id.ll_myInfo_attention);
        mTv_attention = view.findViewById(R.id.tv_myInfo_attention);
        mLl_fans = view.findViewById(R.id.ll_myInfo_fans);
        mTv_fans = view.findViewById(R.id.tv_myInfo_fans);
        mLl_authentication = view.findViewById(R.id.ll_myInfo_authentication);
        mLl_helper = view.findViewById(R.id.ll_myInfo_helper);
        mLl_setting = view.findViewById(R.id.ll_myInfo_setting);

    }



    private void initData() {
        mRl_myInfo.setOnClickListener(this);
        mLl_attention.setOnClickListener(this);
        mLl_authentication.setOnClickListener(this);
        mLl_helper.setOnClickListener(this);
        mLl_setting.setOnClickListener(this);
        mLl_fans.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_myInfo_personInfo:
                Log.i("setOnClickEvent", "personInfo");
                break;
            case R.id.ll_myInfo_attention:
                Log.i("setOnClickEvent", "attentition");
                break;
            case R.id.ll_myInfo_fans:
                Log.i("setOnClickEvent", "fans");
                break;
            case R.id.ll_myInfo_authentication:
                Log.i("setOnClickEvent", "authentication");
                break;
            case R.id.ll_myInfo_helper:
                Log.i("setOnClickEvent", "helper");
                break;
            case R.id.ll_myInfo_setting:
                Log.i("setOnClickEvent", "setting");
                break;
            default:
                break;
        }
    }
}
