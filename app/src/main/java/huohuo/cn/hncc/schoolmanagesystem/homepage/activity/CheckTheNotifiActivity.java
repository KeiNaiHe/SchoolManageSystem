package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.app.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.GlideImageUtil;


/**
 * Created by Windows on 2018/5/3.
 * 查看通知
 */

public class CheckTheNotifiActivity extends Activity {

    private ImageButton mIb_back;
    private SmartRefreshLayout mSmartRefreshLayout;
    private ClassicsHeader mClassicsHeader;
    private BallPulseFooter mBallPulFooter;
    private List<NotifiItemBean> mList_listData;
    private ListView mLv_notifi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkthenotifi);

        initBundleParam();
        initServiceData();

        initView();
        initData();
    }

    private void initServiceData() {
        mList_listData = new ArrayList<NotifiItemBean>();

        NotifiItemBean bean = new NotifiItemBean();
        bean.headPortrait = ("http://img0.imgtn.bdimg.com/it/u=2863985818,115858520&fm=27&gp=0.jpg");
        bean.showName = ("祝皮皮");
        bean.showText = ("看到那个人给我点的赞，我确实不敢动，怕把她吓跑了");
        bean.showTime = ("2017/7/10 下午5:45");
        ArrayList<String> list = new ArrayList<>();
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://p1.qzone.la/upload/20150102/a3zs6l69.jpg");
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2202196942,3715188487&fm=27&gp=0.jpg");
        list.add("https://img5q.duitang.com/uploads/item/201410/18/20141018201220_UELnM.jpeg");
        list.add("http://www.qqzhi.com/uploadpic/2015-01-10/160955418.jpg");
        bean.nineGridData = list;
        mList_listData.add(bean);

        bean = new NotifiItemBean();
        bean.headPortrait = ("http://img0.imgtn.bdimg.com/it/u=2863985818,115858520&fm=27&gp=0.jpg");
        bean.showName = ("祝皮皮");
        bean.showText = ("看到那个人给我点的赞，我确实不敢动，怕把她吓跑了");
        bean.showTime = ("2017/7/10 下午5:45");
        list = new ArrayList<>();
        list.add("http://img.25pp.com/uploadfile/youxi/images/2015/0324/20150324035941178.jpg");
        list.add("http://p1.qzone.la/upload/20150102/a3zs6l69.jpg");
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://img1.imgtn.bdimg.com/it/u=2202196942,3715188487&fm=27&gp=0.jpg");
        list.add("https://img5q.duitang.com/uploads/item/201410/18/20141018201220_UELnM.jpeg");
        list.add("http://img001.yygexing.com/20170607/5dfc9168d42e8c63e931c847030eb941.jpg");
        bean.nineGridData = list;
        mList_listData.add(bean);

        bean = new NotifiItemBean();
        bean.headPortrait = ("http://img05.tooopen.com/images/20140424/sy_59699024632.jpg");
        bean.showName = ("祝皮皮");
        bean.showText = ("看到那个人给我点的赞，我确实不敢动，怕把她吓跑了");
        bean.showTime = ("2017/7/10 下午5:45");
        list = new ArrayList<>();
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://p1.qzone.la/upload/20150102/a3zs6l69.jpg");
        list.add("http://www.5wants.cc/WEB/File/U3325P704T93D1661F3923DT20090612155225.jpg");
        list.add("http://2t.5068.com/uploads/allimg/150611/1-1506110U430.jpg");
        list.add("https://img5q.duitang.com/uploads/item/201410/18/20141018201220_UELnM.jpeg");
        list.add("http://www.qqzhi.com/uploadpic/2015-01-10/160955418.jpg");
        bean.nineGridData = list;
        mList_listData.add(bean);


    }

    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_checkNotifi_back);
        mSmartRefreshLayout = (SmartRefreshLayout) findViewById(R.id.smartRefreshLayout_checkNotifi);
        mClassicsHeader = (ClassicsHeader) findViewById(R.id.srl_checkNotifi_classicsHeader);
        mBallPulFooter = (BallPulseFooter) findViewById(R.id.srl_checkNotifi_ballPulFooter);
        mLv_notifi = (ListView) findViewById(R.id.lv_checkNotifi_notifi);

    }

    private void initData() {
        mIb_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSmartRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(1500);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(1500);
            }
        });

        NotificationAdapter mAdapter = new NotificationAdapter();
        mLv_notifi.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


    private void initBundleParam() {

    }

    class NotificationAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return mList_listData.size();
        }

        @Override
        public Object getItem(int position) {
            return mList_listData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;
            if (convertView == null) {
                view = View.inflate(CheckTheNotifiActivity.this, R.layout.item_checknotifi_lv, null);
            } else {
                view = convertView;
            }
            initItemView(view, position);

            return view;
        }

        private void initItemView(View view, int position) {
            CircleImageView civ_headPortrait = (CircleImageView) view.findViewById(R.id.civ_checkNotifiItem_photo);
            NineGridImageView nineGridImageView = (NineGridImageView) view.findViewById(R.id.ngiv_checkNotifiItem_conPicture);
            TextView tv_showName = (TextView) view.findViewById(R.id.tv_checkNotifiItem_name);
            TextView tv_showTime = (TextView) view.findViewById(R.id.tv_checkNotifiItem_time);
            TextView tv_showText = (TextView) view.findViewById(R.id.tv_checkNotifiItem_text);

            NotifiItemBean bean = mList_listData.get(position);

            GlideImageUtil.loadIntenetImg(bean.headPortrait, civ_headPortrait);

            tv_showName.setText(bean.showName);
            tv_showTime.setText(bean.showTime);
            tv_showText.setText(bean.showText);

            nineGridImageView.setAdapter(new NineGridImageViewAdapter<String>() {
                @Override
                protected void onDisplayImage(Context context, ImageView imageView, String o) {
                    GlideImageUtil.loadIntenetImg(o, imageView);
                }
            });
            nineGridImageView.setGap(3);
            nineGridImageView.setImagesData(bean.nineGridData);
        }

    }

    private class NotifiItemBean {
        private String headPortrait;
        private String showName;
        private String showTime;
        private String showText;
        private ArrayList<String> nineGridData;
        private int commentCount;
        private int likeCount;
    }
}
