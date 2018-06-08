package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.GlideImageUtil;
import huohuo.cn.hncc.schoolmanagesystem.homepage.StudentInfoActivity;
import huohuo.cn.hncc.schoolmanagesystem.homepage.WeeklyReportBean;
import huohuo.cn.hncc.schoolmanagesystem.homepage.XListViewAdapter;


/**
 * Created by Windows on 2018/5/3.
 */

public class WeeklyReportActivity extends Activity {

    private ListView mListView;
    private ImageButton mIb_back;
    private List<WeeklyReportBean> mList_ListData;
    private XListViewAdapter mXListViewAdapter;
    private ClassicsHeader mClassicHeader;
    private ClassicsFooter mClassicsFooter;
    private SmartRefreshLayout mSamrtRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weeklyreport);

        initView();

        initData();

        setAdapter();
    }

    private void initView() {
        mListView = findViewById(R.id.lv_weeklyReport_con);
        mIb_back = findViewById(R.id.ib_weeklyReport_back);
        mClassicHeader = (ClassicsHeader) findViewById(R.id.srl_weeklyReport_classicHeader);
        mClassicsFooter = (ClassicsFooter) findViewById(R.id.srl_weeklyReport_classicFooter);
        mSamrtRefreshLayout = (SmartRefreshLayout) findViewById(R.id.srl_weeklyReport_refreshLayout);
    }

    private void initData() {
        mList_ListData = new ArrayList<>();

        //图片类型全部为URL
        WeeklyReportBean bean = new WeeklyReportBean();
        bean.setHeadPortrait("http://imgsrc.baidu.com/forum/w=580/sign=1588b7c5d739b6004dce0fbfd9503526/7bec54e736d12f2eb97e1a464dc2d56285356898.jpg");
        bean.setName("祝皮皮");
        bean.setContent("看到那个人给我点的赞，我确实不敢动，怕把她吓跑了");
        bean.setTime("2017/7/10 下午5:45");
        bean.setSignature("不是我太帅，而是你们太丑");
        ArrayList<String> list = new ArrayList<>();
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://p1.qzone.la/upload/20150102/a3zs6l69.jpg");
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2202196942,3715188487&fm=27&gp=0.jpg");
        list.add("https://img5q.duitang.com/uploads/item/201410/18/20141018201220_UELnM.jpeg");
        list.add("http://www.qqzhi.com/uploadpic/2015-01-10/160955418.jpg");
        bean.setListSrc(list);
        mList_ListData.add(bean);

        bean = new WeeklyReportBean();
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
        mList_ListData.add(bean);


    }

    private void setAdapter() {
        mIb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mXListViewAdapter = new XListViewAdapter(WeeklyReportActivity.this,
                R.layout.item_weeklyreport_xlistview,
                WeeklyReportActivity.this);
        mXListViewAdapter.setOnHeadPortraitClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要带点数据过去，以确认该生身份
                startActivity(new Intent(WeeklyReportActivity.this, StudentInfoActivity.class));
            }
        });
        mXListViewAdapter.setOnNineGridImageAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                GlideImageUtil.loadIntenetImg((String) o, imageView);
            }

        });
        mXListViewAdapter.setOnNineGridImageItemClickListener(new ItemImageClickListener() {
            @Override
            public void onItemImageClick(Context context, ImageView imageView, int index, List list) {
                Toast.makeText(context, "这是第" + index + 1 + "张图", Toast.LENGTH_SHORT).show();
            }
        });
        mXListViewAdapter.setXListViewData(mList_ListData);
        mListView.setAdapter(mXListViewAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WeeklyReportBean bean = mList_ListData.get(position);

                Intent intent = new Intent(WeeklyReportActivity.this, DynamicDetailActivity.class);

                /** 头像            String   HeadPortrait
                 * 时间            String  ShowTime
                 * 个性签名         String  Signature
                 * 内容            String   ShowContent
                 * nineImageView  List<String>  NineGridImageViewData
                 * */
                Bundle bundle = new Bundle();
                bundle.putString("HeadPortrait", String.valueOf(bean.getHeadPortrait()));
                bundle.putString("ShowTime", bean.getTime());
                bundle.putString("Signature", bean.getSignature());
                bundle.putString("ShowContent", bean.getContent());
                bundle.putStringArrayList("NineGridImageViewData", bean.getListSrc());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //SmartRefreshLayout设置刷新和加载更多监听
        mSamrtRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                WeeklyReportBean bean = new WeeklyReportBean();
                bean.setHeadPortrait("http://p1.qzone.la/upload/3/c2btmgha.jpg");
                bean.setName("张水军");
                bean.setContent("活出自己的样子");
                bean.setTime(new SimpleDateFormat().format(new Date()));
                bean.setSignature("他活成了诗，却丢了自己的样子");
                ArrayList<String> list = new ArrayList<>();
                list.add("http://p1.qzone.la/upload/20150222/5xpgue83.png");
                list.add("https://www.feizl.com/upload2007/allimg/170828/14001a328-1.jpg");
                list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
                list.add("http://img.jsqq.net/uploads/allimg/140323/1_140323070820_12.jpg");
                bean.setListSrc(list);

                mList_ListData.add(bean);
                mXListViewAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(2000);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                WeeklyReportBean bean = new WeeklyReportBean();
                bean.setHeadPortrait("http://imgtu.5011.net/uploads/content/20170209/4934501486627131.jpg");
                bean.setName("范继科");
                bean.setContent("          Hello,好久未见，还是喜欢你");
                bean.setTime(new SimpleDateFormat().format(new Date()));
                bean.setSignature("学会享受这个世界，而不是去理解");
                ArrayList<String> list = new ArrayList<>();
                list.add("http://p1.qzone.la/upload/9/zz3qidar.jpg");
                list.add("https://img5.duitang.com/uploads/item/201507/23/20150723201510_EXWBR.thumb.700_0.jpeg");
                list.add("https://img5.duitang.com/uploads/item/201610/18/20161018000643_YNmhT.jpeg");
                list.add("http://img1.imgtn.bdimg.com/it/u=2202196942,3715188487&fm=27&gp=0.jpg");
                bean.setListSrc(list);

                mList_ListData.add(bean);
                mXListViewAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh(2000);
            }
        });

    }
}
