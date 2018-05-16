package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.homepage.MyTerns_ChildItemBean;
import huohuo.cn.hncc.schoolmanagesystem.homepage.MyTerns_SuperItemBean;

/**
 * Created by Windows on 2018/5/3.
 */

public class MyTernsActivity extends Activity {

    private ExpandableListView mExpandListView;
    private ImageButton mIb_back;
    private List<List<MyTerns_ChildItemBean>> mList_childList;
    private List<MyTerns_SuperItemBean> mList_super;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_myterns);

        initView();
        initData();
    }

    private void initData() {
        mList_super = new ArrayList<>();
        mList_childList = new ArrayList<>();

        mIb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        getExabInternetData();

        setAdapter();
    }

    private void setAdapter() {
        mExpandListView.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return mList_super.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return mList_childList.get(groupPosition).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return mList_super.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return mList_childList.get(groupPosition).get(childPosition);
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
                //isExpanded  当前Group是否打开
                View view;
                if (convertView == null) {
                    view = View.inflate(MyTernsActivity.this, R.layout.item_myterns_superitem, null);
                } else {
                    view = convertView;
                }

                //设置数据

                TextView tv_descript = view.findViewById(R.id.tv_myTernsSuper_descript);

                MyTerns_SuperItemBean bean = mList_super.get(groupPosition);

                tv_descript.setText(bean.getDescript());

                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View view;
                if (convertView == null) {
                    view = View.inflate(MyTernsActivity.this, R.layout.item_myterns_childitem, null);
                } else {
                    view = convertView;
                }
                TextView tv_photo = (TextView) view.findViewById(R.id.tv_myTernsChild_photo);
                TextView tv_name = (TextView) view.findViewById(R.id.tv_myTernsChild_name);
                TextView tv_stuNo = (TextView) view.findViewById(R.id.tv_myTernsChild_stuNo);

                List<MyTerns_ChildItemBean> superBean = mList_childList.get(groupPosition);
                MyTerns_ChildItemBean childBean = superBean.get(childPosition);

                tv_name.setText(childBean.getName());
                tv_stuNo.setText(childBean.getStuNo());
                tv_photo.setText((CharSequence) childBean.getObjPhoto());

                return view;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                //子类item显示事件

                return false;
            }
        });
    }

    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_myTerns_back);
        mExpandListView = (ExpandableListView) findViewById(R.id.expand_myTerns_content_);
    }

    private void getExabInternetData() {
        //从服务器端获取数据，填充入list
        /**
         * 假设数据
         * 一个superItem对应着一组childItem
         * 这里采用相同ID的方式
         * 设置两个集合，一个集合装superItem
         * 一个集合装childItem的list集合
         * */


        List<MyTerns_ChildItemBean> list = new ArrayList<>();
        MyTerns_ChildItemBean bean = new MyTerns_ChildItemBean();
        bean.setName("张启迪");
        bean.setObjPhoto("启迪");
        bean.setStuNo("2016036854");
        list.add(bean);
        MyTerns_ChildItemBean bean1 = new MyTerns_ChildItemBean();
        bean1.setName("朱文琪");
        bean1.setObjPhoto("文琪");
        bean1.setStuNo("2016845967");
        list.add(bean1);

        MyTerns_SuperItemBean superItemBean1 = new MyTerns_SuperItemBean();
        superItemBean1.setDescript("一年级五班");
        //superItemBean1.setObj(R.mipmap.ic_launcher_round);

        mList_childList.add(list);
        mList_super.add(superItemBean1);

        List<MyTerns_ChildItemBean> list1 = new ArrayList<>();
        MyTerns_ChildItemBean bean2 = new MyTerns_ChildItemBean();
        bean2.setName("刘天");
        bean2.setObjPhoto("刘天");
        bean2.setStuNo("20187459");
        list1.add(bean2);
        MyTerns_ChildItemBean bean3 = new MyTerns_ChildItemBean();
        bean3.setName("赵柳");
        bean3.setObjPhoto("赵柳");
        bean3.setStuNo("201765425");
        list1.add(bean3);

        MyTerns_SuperItemBean superItemBean = new MyTerns_SuperItemBean();
        superItemBean.setDescript("二年级五班");
        //superItemBean.setObj(R.mipmap.ic_launcher_round);

        mList_childList.add(list1);
        mList_super.add(superItemBean);

    }
}
