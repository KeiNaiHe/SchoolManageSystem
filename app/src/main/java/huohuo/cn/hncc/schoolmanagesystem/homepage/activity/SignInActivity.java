package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.homepage.SignInI_ChildtemBean;

/**
 * Created by Windows on 2018/5/3.
 */

public class SignInActivity extends Activity {

    private ImageButton mIb_back;
    private ExpandableListView mEalv_con;
    private List<String> mList_super;
    private List<List<SignInI_ChildtemBean>> mList_child;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        initView();
        initData();
        setAdapter();
    }


    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_signIn_back);
        mEalv_con = (ExpandableListView) findViewById(R.id.ealv_signIn_con);
    }

    private void initData() {
        mList_super = new ArrayList<>();
        mList_child = new ArrayList<>();


        mList_super.add("已签到：15");
        mList_super.add("未签到：12");


        List<SignInI_ChildtemBean> list = new ArrayList<>();
        SignInI_ChildtemBean bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);

        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);

        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);

        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);

        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);
        mList_child.add(list);



        list = new ArrayList<>();
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);

        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);


        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);


        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("数字媒体一班");
        bean.setName("范亚可");
        bean.setStuNo("2016016782");
        bean.setStuPhoto("亚可");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("刘田田");
        bean.setStuNo("2016016897");
        bean.setStuPhoto("田田");
        list.add(bean);
        bean = new SignInI_ChildtemBean();
        bean.setClassAndGrade("计算机应用一班");
        bean.setName("范潇潇");
        bean.setStuNo("2016016457");
        bean.setStuPhoto("潇潇");
        list.add(bean);



        mList_child.add(list);
    }

    private void setAdapter() {
        mIb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mEalv_con.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return mList_super.size();
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return mList_child.get(groupPosition).size();
            }

            @Override
            public Object getGroup(int groupPosition) {
                return mList_super.get(groupPosition);
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return mList_child.get(groupPosition).get(childPosition);
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
                    view = View.inflate(SignInActivity.this, R.layout.item_signin_superitem, null);
                } else {
                    view = convertView;
                }
                TextView tv_desc = (TextView) view.findViewById(R.id.tv_signInSuper_desc);
                tv_desc.setText(mList_super.get(groupPosition));

                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View view = null;

                if (convertView == null) {
                    view = View.inflate(SignInActivity.this, R.layout.item_signin_childitem, null);
                } else {
                    view = convertView;
                }
                SignInI_ChildtemBean bean = mList_child.get(groupPosition).get(childPosition);
                TextView tv_stuPhoto = (TextView) view.findViewById(R.id.tv_signInChile_stuPhoto);
                TextView tv_name = (TextView) view.findViewById(R.id.tv_signInChile_name);
                TextView tv_stuNo = (TextView) view.findViewById(R.id.tv_signInChile_stuNo);
                TextView tv_class = (TextView) view.findViewById(R.id.tv_signInChile_class);

                tv_class.setText(bean.getClassAndGrade());
                tv_name.setText(bean.getName());
                tv_stuPhoto.setText(bean.getStuPhoto());
                tv_stuNo.setText(bean.getStuNo());

                return view;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        });
        mEalv_con.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //需要将数据当参数传过去，获取服务器数据
                Log.i("Test" , "test");
                Intent intent = new Intent(SignInActivity.this, SignIn_CalendarActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
}
