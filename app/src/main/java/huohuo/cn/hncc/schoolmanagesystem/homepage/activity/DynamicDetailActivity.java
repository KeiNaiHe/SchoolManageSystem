package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.GlideImageUtil;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

/**
 *
 * 有Android版本问题，后期修复
 * RecyclerView会单独滑动
 * 不跟整个界面一起滑动
 *
 * Created by Windows on 2018/5/18.
 * 动态详情界面，打开进入的单个动态
 * <p>
 * Bundle数据
 * 头像            String   HeadPortrait
 * 时间            String  ShowTime
 * 个性签名         String  Signature
 * 内容            String   ShowContent
 * nineImageView  List<String>   NineGridImageViewData
 * <p>
 * 服务器获取数据
 * 点赞人
 * 名字        String
 * <p>
 * 评论
 * 评论内容       String
 * 评论人头像      String
 * 时间            String
 * 评论人名字        String
 * <p>
 * <p>
 * <p>
 * 该评论点赞 人数    int
 * 后续实现
 * 当前评论的评论
 */

public class DynamicDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> mList_nineGridImageData;
    private String mSignature;
    private String mShowContent;
    private String mShowTime;
    private String mHeadPortrait;
    //评论的父布局
    private RecyclerView mRecyclerView;
    private NineGridImageView mNineGridImageView;
    //头像
    private CircleImageView mCircleHeadPortrait;
    //内容
    private TextView mTv_content;
    //个性签名
    private TextView mTv_signature;
    //发表时间
    private TextView mTv_showTime;
    private ImageButton mIb_back;
    private ArrayList<DetailBean> mList_comment;
    private DetailAdapter mCommentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamicdetail);

        initBundleParam();
        initServiceData();

        initView();
        initData();
    }

    private void initData() {
        mIb_back.setOnClickListener(this);
        //设置头像
        GlideImageUtil.loadIntenetImg(mHeadPortrait, mCircleHeadPortrait);
        mCircleHeadPortrait.setOnClickListener(this);
        //设置图集

        mNineGridImageView.setItemImageClickListener(new ItemImageClickListener() {
            @Override
            public void onItemImageClick(Context context, ImageView imageView, int index, List list) {
                Toast.makeText(context, String.valueOf(index + 1), Toast.LENGTH_SHORT).show();
            }
        });
        mNineGridImageView.setAdapter(new NineGridImageViewAdapter() {
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                GlideImageUtil.loadIntenetImg((String) o, imageView);
            }
        });
        mNineGridImageView.setGap(3);
        mNineGridImageView.setImagesData(mList_nineGridImageData);
        //
        mTv_content.setText(mShowContent);
        mTv_showTime.setText(mShowTime);
        mTv_signature.setText(mSignature);

        //设置RecyclerView
        //设置item的分割，第二个参数 代表方向
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mCommentAdapter = new DetailAdapter(mList_comment);
        mRecyclerView.setAdapter(mCommentAdapter);
        mCommentAdapter.notifyDataSetChanged();

    }

    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_dynamicDetail_back);
        mCircleHeadPortrait = (CircleImageView) findViewById(R.id.civ_dynamicDetail_photo);
        mNineGridImageView = (NineGridImageView) findViewById(R.id.ngiv_dynamicDetail_conPicture);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_dynamicDetail_comment);
        mTv_content = (TextView) findViewById(R.id.tv_dynamicDetail_con);
        mTv_signature = (TextView) findViewById(R.id.tv_dynamicDetail_signature);
        mTv_showTime = (TextView) findViewById(R.id.tv_dynamicDetail_time);
    }

    //获取服务器数据
    private void initServiceData() {
        //主要点赞的数据与评论的数据
        mList_comment = new ArrayList<>();

        DetailBean detailBean = new DetailBean();
        detailBean.commentName="光头像";
        detailBean.commentTime="2016-1-10 13:24";
        detailBean.commentText="我是假的光头强";
        detailBean.headPortrait="https://img4.duitang.com/uploads/item/201602/07/20160207151120_zj5ES.jpeg";
        mList_comment.add(detailBean);

        detailBean = new DetailBean();
        detailBean.commentName="光头像";
        detailBean.commentTime="2016-1-10 13:24";
        detailBean.commentText="没有人明白";
        detailBean.headPortrait="http://www.yyweishi.com/uploads/allimg/140323/010H92234-16.jpg";
        mList_comment.add(detailBean);

        detailBean = new DetailBean();
        detailBean.commentName="光头像";
        detailBean.commentTime="2016-1-10 13:24";
        detailBean.commentText="每当想起你，我都很悲伤";
        detailBean.headPortrait="http://f0.topitme.com/0/3c/31/11318809069cc313c0o.jpg";
        mList_comment.add(detailBean);

        detailBean = new DetailBean();
        detailBean.commentName="光头像";
        detailBean.commentTime="2016-1-10 13:24";
        detailBean.commentText="我已经尝试着去忘记你了，你说你喜欢这个城市，我却想远离，尽管我宁愿留下";
        detailBean.headPortrait="";
        mList_comment.add(detailBean);

        detailBean = new DetailBean();
        detailBean.commentName="光头像";
        detailBean.commentTime="2016-1-10 13:24";
        detailBean.commentText="曾经为赋新词强说愁，如今却道天凉好秋";
        detailBean.headPortrait="http://img.besoo.com/file/201705/25/1050098545908.png";
        mList_comment.add(detailBean);
    }

    //获取Bundle传过来的参数
    private void initBundleParam() {
        Intent intent = getIntent();

        Bundle bundle = intent.getExtras();
        if (bundle == null) {
            throw new NullPointerException("DynamicDetail,bundle为null");
        }
        mHeadPortrait = bundle.getString("HeadPortrait");
        mShowTime = bundle.getString("ShowTime");
        mShowContent = bundle.getString("ShowContent");
        mSignature = bundle.getString("Signature");
        mList_nineGridImageData = bundle.getStringArrayList("NineGridImageViewData");

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_dynamicDetail_back:
                finish();
                break;
            case R.id.civ_dynamicDetail_photo:
                Toast.makeText(this, "我是头像", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    class DetailAdapter extends BaseQuickAdapter<DetailBean, BaseViewHolder> implements View.OnClickListener {

        DetailAdapter(List<DetailBean> data) {
            super(R.layout.item_detailcomment_recyclerview, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DetailBean item) {
            helper.getView(R.id.civ_detailComment_headPortrait).setOnClickListener(this);
            helper.getView(R.id.tv_detailComment_commentName).setOnClickListener(this);
            helper.getView(R.id.tv_detailComment_commentText).setOnClickListener(this);


            helper.setText(R.id.tv_detailComment_commentName, item.commentName)
                    .setText(R.id.tv_detailComment_commentTime, item.commentTime)
                    .setText(R.id.tv_detailComment_commentText, item.commentText);

            GlideImageUtil.loadIntenetImg(item.headPortrait, (ImageView) helper.getView(R.id.civ_detailComment_headPortrait));

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.civ_detailComment_headPortrait:
                    Toast.makeText(mContext, "我是头像", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_detailComment_commentName:
                    Toast.makeText(mContext, "名字", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.tv_detailComment_commentText:
                    Toast.makeText(mContext, "评论内容", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    class DetailBean {
        private String commentName;
        private String commentTime;
        private String headPortrait;
        private String commentText;
    }
}
