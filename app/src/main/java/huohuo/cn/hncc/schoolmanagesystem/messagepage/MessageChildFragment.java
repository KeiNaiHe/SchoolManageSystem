package huohuo.cn.hncc.schoolmanagesystem.messagepage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/22.
 */

public class MessageChildFragment extends Fragment implements View.OnClickListener {

    private TextView mTv_notifiCount;
    private LinearLayout mLl_notifi;
    private LinearLayout mLl_group;
    private ListView mLv_chatList;
    private List<Object> mList_data;
    private RelativeLayout mRl_noChat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_childmessage, null);
        initServiceData();
        initView(view);
        initData();
        return view;
    }

    /**
     * 获取服务器聊天列表信息，对本地存储进行刷新并显示
     */
    private void initServiceData() {
        mList_data = new ArrayList<>();
    }

    private void initView(View view) {
        mLl_group =  view.findViewById(R.id.ll_childMessage_group);
        mLl_notifi =  view.findViewById(R.id.ll_childMessage_notification);
        mTv_notifiCount =  view.findViewById(R.id.tv_childMessage_notificationCount);
        mLv_chatList = view.findViewById(R.id.lv_childMessage_chatList);
        mRl_noChat = view.findViewById(R.id.rl_childMessage_noChat);

    }

    private void initData() {
        mLl_group.setOnClickListener(this);
        mLl_notifi.setOnClickListener(this);

        if(mList_data==null||mList_data.size()<=0){
            mRl_noChat.setVisibility(View.VISIBLE);
            mLv_chatList.setVisibility(View.INVISIBLE);
        }else{
            mLv_chatList.setVisibility(View.VISIBLE);
            mRl_noChat.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_childMessage_group:
                startActivity(new Intent(getActivity(),NotificationActivity.class));
                break;
            case R.id.ll_childMessage_notification:
                startActivity(new Intent(getActivity(),NotificationActivity.class));
                break;
            default:
                break;
        }
    }
}
