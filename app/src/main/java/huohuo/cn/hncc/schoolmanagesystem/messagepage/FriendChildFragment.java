package huohuo.cn.hncc.schoolmanagesystem.messagepage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.GlideImageUtil;

/**
 * Created by Windows on 2018/5/22.
 * 后续实现类似QQ好友列表层级迭代效果
 */

public class FriendChildFragment extends Fragment implements View.OnClickListener {

    private ExpandableListView mExpandableListView;
    private List<FriendGroup> mList_group;
    private List<List<FriendInfo>> mList_person;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_childfriend, null);
        initServiceData();
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        FriendAdapter adapter = new FriendAdapter();
        mExpandableListView.setAdapter(adapter);
    }

    private void initView(View view) {
        View mLl_search = view.findViewById(R.id.ll_childFriend_search);
        View mLl_newFriend = view.findViewById(R.id.ll_childFriend_newFriend);
        View mLl_newGroup = view.findViewById(R.id.ll_childFriend_newGroup);
        mLl_newFriend.setOnClickListener(this);
        mLl_newGroup.setOnClickListener(this);
        mLl_search.setOnClickListener(this);
        mExpandableListView = view.findViewById(R.id.ealv_childFriend_friend);


    }

    /**
     * 获取服务器双层List数据
     * 也需要实现本地缓存
     */
    private void initServiceData() {
        mList_group = new ArrayList<>();
        mList_person = new ArrayList<>();

        //填充groupList数据
        FriendGroup friendGroup = new FriendGroup();
        friendGroup.groupName = "特别关心";
        friendGroup.headCount = "1";
        friendGroup.onlineNumber = "0";
        mList_group.add(friendGroup);

        friendGroup = new FriendGroup();
        friendGroup.groupName = "我的好友";
        friendGroup.headCount = "10";
        friendGroup.onlineNumber = "5";
        mList_group.add(friendGroup);

        friendGroup = new FriendGroup();
        friendGroup.groupName = "熟悉";
        friendGroup.headCount = "4";
        friendGroup.onlineNumber = "2";
        mList_group.add(friendGroup);

        friendGroup = new FriendGroup();
        friendGroup.groupName = "朋友";
        friendGroup.headCount = "7";
        friendGroup.onlineNumber = "4";
        mList_group.add(friendGroup);
        //填充个人信息数据
        List<FriendInfo> lists = new ArrayList<>();
        FriendInfo friendInfo = new FriendInfo();
        friendInfo.headPortrait = "https://img4.duitang.com/uploads/item/201512/13/20151213124621_YE8aW.jpeg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "小半";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);

        sort(lists);
        mList_person.add(lists);



        lists = new ArrayList<>();
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "https://img3.duitang.com/uploads/item/201608/04/20160804185119_vB2Ad.jpeg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "杨瘦瘦";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "https://img5.duitang.com/uploads/item/201604/30/20160430095702_cxC4B.jpeg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "杨芳芳";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "https://img3.duitang.com/uploads/item/201604/04/20160404154540_smNdr.thumb.700_0.jpeg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "杨芳";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "https://img5.duitang.com/uploads/item/201508/26/20150826140428_tEjUM.thumb.700_0.jpeg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "巴蜀";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "https://img3.duitang.com/uploads/item/201604/30/20160430100115_hinz5.jpeg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "奈何";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://pic.qqtn.com/up/2017-8/2017081711333680232.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "可奈何";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://pic.qqtn.com/up/2017-8/2017081711333629755.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "无味";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://images.liqucn.com/img/h1/h965/img201709161640400_info300X300.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "无问";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://images.liqucn.com/img/h1/h965/img201709161637250_info300X300.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "艾玛";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://images.liqucn.com/img/h1/h965/img201709161640020_info300X300.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "江南";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);

        sort(lists);
        mList_person.add(lists);


        lists = new ArrayList<>();
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://www.qqzhi.com/uploadpic/2014-05-06/161146262.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "心死";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://p1.4499.cn/touxiang/UploadPic/2014-7/2/201407021140306943.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "小半";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://p1.4499.cn/touxiang/UploadPic/2014-7/2/2014070211403241269.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "小半";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://www.qqzhi.com/uploadpic/2014-10-26/054739574.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "小半";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);

        sort(lists);
        mList_person.add(lists);


        lists = new ArrayList<>();
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://www.ld12.com/upimg358/allimg/20160630/1006536941159657.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "不醉";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "https://www.feizl.com/upload2007/2015_04/1504151632653414.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "作乐";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://tx.haiqq.com/uploads/allimg/170916/1055064118-8.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "浇水";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://www.qqzhi.com/uploadpic/2014-09-19/092347106.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "小半";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://tx.haiqq.com/uploads/allimg/170508/034149C56-9.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "丁达";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://tx.haiqq.com/uploads/allimg/170507/0556152617-1.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "小半";
        friendInfo.onlineState = 1;
        lists.add(friendInfo);
        friendInfo = new FriendInfo();
        friendInfo.headPortrait = "http://img0.imgtn.bdimg.com/it/u=840155555,4122660149&fm=214&gp=0.jpg";
        friendInfo.Signature = "苦中作乐哪有那么容易，有你在我身边罢了";
        friendInfo.userName = "不易";
        friendInfo.onlineState = 0;
        lists.add(friendInfo);

        sort(lists);
        mList_person.add(lists);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_childFriend_newFriend:
                break;
            case R.id.ll_childFriend_newGroup:
                break;
            case R.id.ll_childFriend_search:
                startActivity(new Intent(getActivity(),FriendSearchActivity.class));
                break;
            default:
        }
    }

    class FriendAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return mList_group.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mList_person.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mList_group.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mList_person.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = View.inflate(getActivity(), R.layout.fragment_childfriend_superitem, null);
            } else {
                view = convertView;
            }

            TextView tv_groupName = (TextView) view.findViewById(R.id.tv_childFriendSuper_groupName);
            TextView tv_countInfo = (TextView) view.findViewById(R.id.tv_childFriendSuper_countInfo);

            tv_countInfo.setText(merge(mList_group.get(groupPosition).headCount,
                    mList_group.get(groupPosition).onlineNumber));
            tv_groupName.setText(mList_group.get(groupPosition).groupName);
            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = View.inflate(getActivity(), R.layout.fragment_childfriend_childitem, null);
            } else {
                view = convertView;
            }

            initItemChildView(view, groupPosition, childPosition);
            return view;
        }

        private void initItemChildView(View view, int groupId, int childId) {
            CircleImageView civ_headPortrait = (CircleImageView) view.findViewById(R.id.civ_childFriendChild_headPortrait);
            TextView tv_userName = (TextView) view.findViewById(R.id.tv_childFriendChild_user);
            TextView tv_singnature = (TextView) view.findViewById(R.id.tv_childFriendChild_signature);

            List<FriendInfo> infoList = mList_person.get(groupId);

            tv_userName.setText(infoList.get(childId).userName);
            tv_singnature.setText(infoList.get(childId).Signature);
            GlideImageUtil.loadIntenetImg(infoList.get(childId).headPortrait, civ_headPortrait);
            if (infoList.get(childId).onlineState != 1) {
                civ_headPortrait.setColorFilter(Color.parseColor("#aaffffff"));
            } else {
                civ_headPortrait.setColorFilter(Color.parseColor("#00ffffff"));
            }
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

    private class FriendGroup {
        private String onlineNumber;
        private String headCount;
        private String groupName;
    }

    private class FriendInfo {
        private String headPortrait;
        private String userName;
        private String Signature;
        private int onlineState;//是否在线
    }

    /**
     * @param headCount 一个组的总人数
     * @param online    在线人数
     * @return 组合字符串
     * 类似  4/5
     */
    private String merge(String headCount, String online) {
        return online + "/" + headCount;
    }

    /**
     * 对组里面的好友按在线与不在线进行排序
     * 再按首字母进行排序
     *
     * @return 排序后的list集合
     */
    private List<FriendInfo> sort(List<FriendInfo> list) {
        List<FriendInfo> list_online = new ArrayList<>();
        List<FriendInfo> list_drop = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).onlineState == 0) {
                list_drop.add(list.get(i));
            } else if (list.get(i).onlineState == 1) {
                list_online.add(list.get(i));
            } else {
                Toast.makeText(getActivity(), "小半:好友数据排序问题", Toast.LENGTH_SHORT).show();
            }
        }
        //中文语言环境
        // Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
        /**
         * 没起到想要的完美效果
         */
        Collections.sort(list_drop, new Comparator<FriendInfo>() {
            @Override
            public int compare(FriendInfo o1, FriendInfo o2) {
                return o1.userName.compareTo(o2.userName);
            }
        });
        Collections.sort(list_online, new Comparator<FriendInfo>() {
            @Override
            public int compare(FriendInfo o1, FriendInfo o2) {
                return o1.userName.compareTo(o2.userName);
            }
        });

        list.clear();
        list.addAll(list_online);
        list.addAll(list_drop);
        Log.i("tag", list.toString());
        return list;
    }

}
