package huohuo.cn.hncc.schoolmanagesystem;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import huohuo.cn.hncc.guidepage.R;


/**
 * Created by Windows on 2018/5/10.
 * 开启这个页面需要先通过传递过来的信息获取服务器数据
 */

public class StudentInfoActivity extends Activity implements View.OnClickListener {

    private ImageButton mIb_back;
    private LinearLayout mLl_dynamicSelect;
    private LinearLayout mLl_attentionCount;
    private LinearLayout mLl_employerCount;
    private LinearLayout mLl_fansCount;
    private TextView mTv_name;
    private LinearLayout mLl_signInCount;
    private LinearLayout mLl_weekReportCount;
    private ImageView mIv_sex;
    private CircleImageView mCiv_headPortrait;
    private NineGridImageView mNgiv_conPicture;
    private LinearLayout mLl_personInfo;
    private TextView mTv_info;
    private TextView mTv_account;
    private TextView mTv_achool;
    private List<String> mList_nineImageData;
    private LinearLayout mLl_evaluate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);

        initView();

        initData();

        setAdapter();
    }

    private void setAdapter() {
        mIb_back.setOnClickListener(this);
        mLl_dynamicSelect.setOnClickListener(this);
        mLl_attentionCount.setOnClickListener(this);
        mLl_personInfo.setOnClickListener(this);
        mLl_employerCount.setOnClickListener(this);
        mLl_fansCount.setOnClickListener(this);
        mLl_signInCount.setOnClickListener(this);
        mLl_weekReportCount.setOnClickListener(this);
        mCiv_headPortrait.setOnClickListener(this);
        mLl_evaluate.setOnClickListener(this);

        GlideImageUtil.loadIntenetImg
                ("https://img3.duitang.com/uploads/item/201508/02/20150802102918_UZYdH.jpeg"
                        , mCiv_headPortrait);


        mNgiv_conPicture.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                GlideImageUtil.loadIntenetImg((String) o, imageView);
            }

            @Override
            protected void onItemImageClick(Context context, ImageView imageView, int index, List list) {
                Toast.makeText(context, String.valueOf(index+1), Toast.LENGTH_SHORT).show();
            }
        });
        //现在没时间，将来可对NineGridImageView进行一些自定义修改
        //实现自己控制行或列
        mNgiv_conPicture.setGap(3);
        mNgiv_conPicture.setImagesData(mList_nineImageData);
        mNgiv_conPicture.setItemImageClickListener(new ItemImageClickListener() {
            @Override
            public void onItemImageClick(Context context, ImageView imageView, int index, List list) {
                Toast.makeText(context, "这是第"+index+1+"张图", Toast.LENGTH_SHORT).show();
            }
        });

        mTv_account.setText("100010");
        mTv_achool.setText("河南交通职业技术学院");
        mTv_info.setText("女 21岁 射手座 河南 郑州市");
        mTv_name.setText("祝皮皮");
    }

    private void initData() {
        /**
         * 1.头像
         * 2.性别
         * 3.动态图
         * 4.关注数
         * 5.粉丝数
         * 6.名字
         * 7.学校
         * 8.关注数
         * 9.周记数
         * 10.个人信息
         * 11.账号
         * 12，学校名字
         * */
        mList_nineImageData = new ArrayList<>();
        mList_nineImageData.add("http://p1.4499.cn/touxiang/UploadPic/2014-7/3/2014070318131624354.jpg");
        mList_nineImageData.add("http://p1.qzone.la/upload/20150102/a3zs6l69.jpg");
        mList_nineImageData.add("http://img4.imgtn.bdimg.com/it/u=1856167379,3898717282&fm=214&gp=0.jpg");

    }


    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_studentInfo_back);
        mLl_dynamicSelect = (LinearLayout) findViewById(R.id.ll_studentInfo_dynamicSelect);
        mLl_attentionCount = (LinearLayout) findViewById(R.id.ll_studentInfo_attention);
        mLl_personInfo = (LinearLayout) findViewById(R.id.ll_studentInfo_personInfo);
        mLl_signInCount = (LinearLayout) findViewById(R.id.ll_studentInfo_signInCount);
        mLl_weekReportCount = (LinearLayout) findViewById(R.id.ll_studentInfo_weekReportCount);
        mLl_employerCount = (LinearLayout) findViewById(R.id.ll_studentInfo_employer);
        mLl_fansCount = (LinearLayout) findViewById(R.id.ll_studentInfo_fans);
        mLl_evaluate = (LinearLayout) findViewById(R.id.ll_studentInfo_evaluate);

        mIv_sex = (ImageView) findViewById(R.id.iv_studentInfo_sex);
        mCiv_headPortrait = (CircleImageView) findViewById(R.id.civ_studentInfo_headPortrait);
        mNgiv_conPicture = (NineGridImageView) findViewById(R.id.ngiv_studentInfo_dynamicImage);

        mTv_name = (TextView) findViewById(R.id.tv_studentInfo_name);
        mTv_info = (TextView) findViewById(R.id.tv_studentInfo_info);
        mTv_account = (TextView) findViewById(R.id.tv_StudentInfo_account);
        mTv_achool = (TextView) findViewById(R.id.tv_studentInfo_school);


    }

    private void setSexMipmap(boolean b) {
        if (b) {
            mIv_sex.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.me_sex_f_icon));
        } else {
            mIv_sex.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.me_sex_m_icon));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_studentInfo_back:
                onBackPressed();
                break;
            case R.id.ll_studentInfo_dynamicSelect:
                Toast.makeText(this, "动态查看详情", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_studentInfo_attention:
                Toast.makeText(this, "关注", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_studentInfo_employer:
                Toast.makeText(this, "实习岗位", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_studentInfo_fans:
                Toast.makeText(this, "粉丝", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_studentInfo_personInfo:
                Toast.makeText(this, "个人资料查看详情", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_studentInfo_signInCount:
                Toast.makeText(this, "签到数量", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_studentInfo_weekReportCount:
                Toast.makeText(this, "周记数量", Toast.LENGTH_SHORT).show();
                break;
            case R.id.civ_studentInfo_headPortrait:
                Toast.makeText(this, "我是头像", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_studentInfo_evaluate:
                Toast.makeText(this, "我是考评", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
