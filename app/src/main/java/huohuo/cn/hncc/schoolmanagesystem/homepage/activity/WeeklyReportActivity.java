package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.homepage.XListViewAdapter;
import huohuo.cn.hncc.schoolmanagesystem.homepage.XListViewBean;
import view.XListView;

/**
 * Created by Windows on 2018/5/3.
 *
 */

public class WeeklyReportActivity extends Activity {

    private XListView mXListView;
    private ImageButton mIb_back;
    private List<XListViewBean> mList_xListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeklyreport);

        initView();

        initData();

        setAdapter();
    }

    private void initView() {
        mXListView = (XListView) findViewById(R.id.xlv_weeklyReport_con);
        mIb_back = (ImageButton) findViewById(R.id.ib_weeklyReport_back);
    }

    private void initData() {
        mList_xListView = new ArrayList<>();
        /**
         * 图片类型全部为URL
         */
        XListViewBean bean = new XListViewBean();
        bean.setHeadPortrait("http://imgsrc.baidu.com/forum/w=580/sign=1588b7c5d739b6004dce0fbfd9503526/7bec54e736d12f2eb97e1a464dc2d56285356898.jpg");
        bean.setName("祝皮皮");
        bean.setContent("看到那个人给我点的赞，我确实不敢动，怕把她吓跑了");
        bean.setTime("2017/7/10 下午5:45");
        bean.setSignature("不是我太帅，而是你们太丑");
        ArrayList<Object> list = new ArrayList<>();
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://p1.qzone.la/upload/20150102/a3zs6l69.jpg");
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2202196942,3715188487&fm=27&gp=0.jpg");
        list.add("https://img5q.duitang.com/uploads/item/201410/18/20141018201220_UELnM.jpeg");
        list.add("http://www.qqzhi.com/uploadpic/2015-01-10/160955418.jpg");
        bean.setListSrc(list);
        mList_xListView.add(bean);

        bean = new XListViewBean();
        bean.setHeadPortrait("http://imgtu.5011.net/uploads/content/20170209/4934501486627131.jpg");
        bean.setName("范继科");
        bean.setContent("哎，那个姑娘，我中意你");
        bean.setTime("2015/7/14 下午2:45");
        bean.setSignature("学会享受这个世界，而不是去理解");
        list = new ArrayList<>();
        list.add("http://h.hiphotos.baidu.com/zhidao/wh%3D450%2C600/sign=ad052ed74b90f60304e594430c229f2d/7e3e6709c93d70cf1c4008ebf9dcd100bba12b99.jpg");
        list.add("http://p1.qzone.la/upload/20150102/a3zs6l69.jpg");
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2202196942,3715188487&fm=27&gp=0.jpg");
        bean.setListSrc(list);
        mList_xListView.add(bean);


    }

    private void setAdapter() {
        mIb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final XListViewAdapter mXListViewAdapter = new XListViewAdapter
                (WeeklyReportActivity.this, R.layout.item_weeklyreport_xlistview,
                        WeeklyReportActivity.this);
        mXListViewAdapter.setXListViewData(mList_xListView);
        mXListView.setAdapter(mXListViewAdapter);

        mXListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                //刷新

                XListViewBean bean = new XListViewBean();
                bean.setHeadPortrait("http://p1.qzone.la/upload/3/c2btmgha.jpg");
                bean.setName("张水军");
                bean.setContent("活出自己的样子");
                bean.setTime(new SimpleDateFormat().format(new Date()));
                bean.setSignature("他活成了诗，却丢了自己的样子");
                ArrayList<Object> list = new ArrayList<>();
                list.add("http://p1.qzone.la/upload/20150222/5xpgue83.png");
                list.add("https://www.feizl.com/upload2007/allimg/170828/14001a328-1.jpg");
                list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
                list.add("http://img.jsqq.net/uploads/allimg/140323/1_140323070820_12.jpg");
                bean.setListSrc(list);
                mList_xListView.add(bean);
                mXListViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                //加载更多
                XListViewBean bean = new XListViewBean();
                bean.setHeadPortrait("http://imgtu.5011.net/uploads/content/20170209/4934501486627131.jpg");
                bean.setName("范继科");
                bean.setContent("          Hello,好久未见，还是喜欢你");
                bean.setTime(new SimpleDateFormat().format(new Date()));
                bean.setSignature("学会享受这个世界，而不是去理解");
                ArrayList<Object> list = new ArrayList<>();
                list.add("http://p1.qzone.la/upload/9/zz3qidar.jpg");
                list.add("https://img5.duitang.com/uploads/item/201507/23/20150723201510_EXWBR.thumb.700_0.jpeg");
                list.add("https://img5.duitang.com/uploads/item/201610/18/20161018000643_YNmhT.jpeg");
                list.add("http://img1.imgtn.bdimg.com/it/u=2202196942,3715188487&fm=27&gp=0.jpg");
                bean.setListSrc(list);


                mList_xListView.add(bean);
                mXListViewAdapter.notifyDataSetChanged();
            }
        });
    }
}
