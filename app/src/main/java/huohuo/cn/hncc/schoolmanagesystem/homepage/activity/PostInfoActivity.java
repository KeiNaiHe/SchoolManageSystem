package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

/**
 * Created by Windows on 2018/5/16.
 * 界面欠缺
 * 1.PieChart不能随ExpandableListView一起滚动，后期修改
 */

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;

public class PostInfoActivity extends AppCompatActivity {


    private PieChart mPieChart;
    private SmartRefreshLayout mSmartRefreshLayout;
    private ClassicsHeader mClassicsHeader;
    private RecyclerView mRecyclerView;
    private ImageButton mIb_back;
    private ExpandableListView mExpandavleList;
    private List<List<ChildItemBean>> mList_childItem;
    private List<String> mList_superItem;
    private ItemAdapter mItemAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postinfo);

        initView();
        initData();
    }

    private void initData() {
        mIb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //SmartRefreshLau=yout
        mSmartRefreshLayout.setEnableLoadMore(false);
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1500);
                Toast.makeText(PostInfoActivity.this, "刷新监听", Toast.LENGTH_SHORT).show();
            }
        });
        //Header

        //PieChart
        mPieChart.setDrawCenterText(false);//不显示中心文本
        mPieChart.setDrawHoleEnabled(true);//显示中间圆
        mPieChart.setTouchEnabled(true);//可触摸
        mPieChart.setUsePercentValues(true);//百分比
        mPieChart.setMaxAngle(360);//一个整圆
        mPieChart.getDescription().setEnabled(false);//不显示desc

        mPieChart.setTransparentCircleAlpha(90);
        mPieChart.setHoleRadius(40);
        mPieChart.setHoleColor(Color.WHITE);//中间圆颜色
        mPieChart.setTransparentCircleRadius(42);
        mPieChart.setDrawCenterText(true);
        mPieChart.setRotationAngle(0);//偏转角度
        mPieChart.setDrawHoleEnabled(true);//中心的洞

        mPieChart.setCenterText("实习岗位填报情况");
        mPieChart.setCenterTextSize(15);
        // 触摸旋转
        mPieChart.setRotationEnabled(true);
        mPieChart.setHighlightPerTapEnabled(true);
        //设置是否显示区域文字内容
        mPieChart.setDrawSliceText(false);
        //pieChart.setDrawSlicesUnderHole();
        Legend legend = mPieChart.getLegend();
        legend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);//Legend位置
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setEnabled(true);

          mPieChart.animateX(1800);
//        //设置y轴动画
        // mPieChart.animateY(1800);
//        //设置xy轴一起的动画
      //  mPieChart.animateXY(1800, 1800);
        setChartData();


        //ExpandableListView

        setExpandData();
        mItemAdapter = new ItemAdapter();
        mExpandavleList.setAdapter(mItemAdapter);

    }

    private void setExpandData() {
        if (mList_childItem == null) {
            mList_childItem = new ArrayList<>();
        }
        if (mList_superItem == null) {
            mList_superItem = new ArrayList<>();
        }
        mList_superItem.add("未填报实习生");
        mList_superItem.add("已填报实习生");

        List<ChildItemBean> list = new ArrayList<>();
        ChildItemBean bean = new ChildItemBean();
        bean.classes = ("计算机应用一班");
        bean.name = ("刘田田");
        bean.stuNo = ("2016016897");
        bean.headTV = ("田田");
        list.add(bean);
        bean = new ChildItemBean();
        bean.classes = ("计算机应用一班");
        bean.name = ("范潇潇");
        bean.stuNo = ("2016016457");
        bean.headTV = ("潇潇");
        list.add(bean);
        bean = new ChildItemBean();
        bean.classes = ("数字媒体一班");
        bean.name = ("范亚可");
        bean.stuNo = ("2016016782");
        bean.headTV = ("亚可");
        list.add(bean);
        mList_childItem.add(list);

        bean = new ChildItemBean();
        bean.classes = ("计算机应用一班");
        bean.name = ("刘田田");
        bean.stuNo = ("2016016897");
        bean.headTV = ("田田");
        list.add(bean);
        bean = new ChildItemBean();
        bean.classes = ("计算机应用一班");
        bean.name = ("范潇潇");
        bean.stuNo = ("2016016457");
        bean.headTV = ("潇潇");
        list.add(bean);
        bean = new ChildItemBean();
        bean.classes = ("数字媒体一班");
        bean.name = ("范亚可");
        bean.stuNo = ("2016016782");
        bean.headTV = ("亚可");
        list.add(bean);
        mList_childItem.add(list);
    }

    private void setChartData() {
        if (mPieChart == null) {
            return;
        }
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(60, "未填报"));
        pieEntries.add(new PieEntry(40, "已填报"));
        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");

        // 部分区域被选中时多出的长度
        pieDataSet.setSelectionShift(5f);
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        pieDataSet.setColors(colors);
        pieDataSet.setSliceSpace(3);//间隔
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(16);
        PieData pieData = new PieData(pieDataSet);
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextColor(R.color.colorWhite);
        pieData.setDrawValues(true);

        mPieChart.setData(pieData);
        mPieChart.notifyDataSetChanged();
    }

    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_postInfo_back);
        mPieChart = (PieChart) findViewById(R.id.pieChart_postInfo_chart);
        mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout_postInfo);
        mClassicsHeader = (ClassicsHeader) findViewById(R.id.falsifyHeader_srl_info);
        mExpandavleList = (ExpandableListView) findViewById(R.id.ealv_postInfo_con);
    }

    private class ChildItemBean {
        private String name;
        private String classes;
        private String stuNo;
        private String headTV;
    }

    class ItemAdapter extends BaseExpandableListAdapter {
        @Override
        public int getGroupCount() {
            return mList_superItem.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return mList_childItem.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return mList_superItem.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return mList_childItem.get(groupPosition).get(childPosition);
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
            View view;
            if (convertView == null) {
                view = View.inflate(PostInfoActivity.this, R.layout.item_postinfo_superitem, null);
            } else {
                view = convertView;
            }
            TextView tv_desc = (TextView) view.findViewById(R.id.tv_postInfoItem_desc);
            tv_desc.setText(mList_superItem.get(groupPosition));

            return view;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(PostInfoActivity.this, R.layout.item_postinfo_reclerview, null);
            } else {
                view = convertView;
            }
            List<ChildItemBean> list = mList_childItem.get(groupPosition);
            ChildItemBean childItemBean = list.get(childPosition);
            TextView tv_name = (TextView) view.findViewById(R.id.tv_postInfoItem_name);
            TextView tv_headPhoto = (TextView) view.findViewById(R.id.tv_postInfoItem_stuPhoto);
            TextView tv_stuNo = (TextView) view.findViewById(R.id.tv_postInfoItem_stuNo);
            TextView tv_class = (TextView) view.findViewById(R.id.tv_postInfoItem_class);

            tv_class.setText(childItemBean.classes);
            tv_headPhoto.setText(childItemBean.headTV);
            tv_stuNo.setText(childItemBean.stuNo);
            tv_name.setText(childItemBean.name);
            return view;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }
}
