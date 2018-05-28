package huohuo.cn.hncc.schoolmanagesystem.messagepage;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 * Created by Windows on 2018/5/27.
 */

public class NotificationActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<NotifiBean> mList_recyclerView;
    private NotifiAdapter mAdapter;
    private SmartRefreshLayout mSmartrefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        initServiceData();
        initView();

        initData();
    }

    private void initServiceData() {
        mList_recyclerView = new ArrayList<>();

        NotifiBean bean = new NotifiBean();
        bean.iconId = R.mipmap.me_post_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "实习岗位";
        bean.desc = "小半 填饱了岗位信息";
        mList_recyclerView.add(bean);


        bean = new NotifiBean();
        bean.iconId = R.mipmap.me_leaveapplication_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "请假";
        bean.desc = "小半向您提交了请假申请";
        mList_recyclerView.add(bean);

        bean = new NotifiBean();
        bean.iconId = R.mipmap.me_post_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "实习岗位";
        bean.desc = "小半 填饱了岗位信息";
        mList_recyclerView.add(bean);


        bean = new NotifiBean();
        bean.iconId = R.mipmap.me_leaveapplication_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "请假";
        bean.desc = "小半向您提交了请假申请";
        mList_recyclerView.add(bean);

        bean = new NotifiBean();
        bean.iconId = R.mipmap.me_post_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "实习岗位";
        bean.desc = "小半 填饱了岗位信息";
        mList_recyclerView.add(bean);


        bean = new NotifiBean();
        bean.iconId = R.mipmap.me_leaveapplication_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "请假";
        bean.desc = "小半向您提交了请假申请";
        mList_recyclerView.add(bean);

        bean = new NotifiBean();
        bean.iconId = R.mipmap.me_post_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "实习岗位";
        bean.desc = "小半 填饱了岗位信息";
        mList_recyclerView.add(bean);


        bean = new NotifiBean();
        bean.iconId = R.mipmap.me_leaveapplication_icon;
        bean.time = "2018-05-18 12:12";
        bean.title = "请假";
        bean.desc = "小半向您提交了请假申请";
        mList_recyclerView.add(bean);
    }

    private void initData() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(NotificationActivity.this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(NotificationActivity.this, VERTICAL));
        mAdapter = new NotifiAdapter(R.layout.item_notification_recyclerview, mList_recyclerView);
        mRecyclerView.setAdapter(mAdapter);

        mSmartrefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        mSmartrefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                 //不理解怎么写，现在也不需要实现
                refreshLayout.finishLoadMore(1500);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                 //网络刷新数据
                refreshLayout.finishRefresh(1500);
            }
        });

    }

    private void initView() {
        findViewById(R.id.ib_notification_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView_notification_con);
        mSmartrefreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_notification_layout);
    }

    class NotifiAdapter extends BaseQuickAdapter<NotifiBean, BaseViewHolder> {


        public NotifiAdapter(int layoutResId, @Nullable List<NotifiBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, NotifiBean item) {
            helper.setText(R.id.tv_notifiItem_title, item.title)
                    .setText(R.id.tv_notifiItem_desc, item.desc)
                    .setText(R.id.tv_notifiItem_time, item.time);

            helper.setImageBitmap(R.id.civ_notifiItem_icon,
                    BitmapFactory.decodeResource(getResources(), item.iconId));

        }
    }

    class NotifiBean {
        /**
         * 通知界面
         * 图片都是本地图片
         * 服务器获取的数据用来判断该通知类型
         */
        private int iconId;
        private String title;
        private String desc;
        private String time;
    }
}
