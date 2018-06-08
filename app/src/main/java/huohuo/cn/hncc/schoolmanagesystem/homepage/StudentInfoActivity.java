package huohuo.cn.hncc.schoolmanagesystem.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.DateUtil;
import huohuo.cn.hncc.schoolmanagesystem.GlideImageUtil;
import huohuo.cn.hncc.schoolmanagesystem.homepage.activity.EvaluationOfTeacherActivity;


/**
 * Created by Windows on 2018/5/10.
 * 开启这个页面需要先通过传递过来的信息获取服务器数据
 */

public class StudentInfoActivity extends Activity implements View.OnClickListener {

    private ImageButton mIb_back;
    private LinearLayout mLl_dynamicSelect;
    private TextView mTv_name;
    private LinearLayout mLl_signInCount;
    private LinearLayout mLl_weekReportCount;
    private ImageView mIv_sex;
    private CircleImageView mCiv_headPortrait;
    private NineGridImageView mNgiv_conPicture;
    private LinearLayout mLl_personInfo;
    private TextView mTv_info;
    private TextView mTv_nickName;
    private TextView mTv_achool;
    private LinearLayout mLl_evaluate;
    private Button mBtn_intern;
    private TextView mTv_stuNo;
    private TernShipInfo mTernShilData;
    private TextView mTv_signature;
    private TextView mTv_phone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);

        if (!initIntentData()) {
            setExpandData();
        }
        initView();

        initData();

        setAdapter();
    }

    private boolean initIntentData() {
        Intent intent = getIntent();
        if (intent == null || intent.getSerializableExtra("TernShipInfo") == null) {
            Toast.makeText(this, "无数据", Toast.LENGTH_SHORT).show();
            return false;
        }
        mTernShilData = (TernShipInfo) intent.getSerializableExtra("TernShipInfo");
        //获取本地及服务器数据
        return true;
    }

    private void setAdapter() {


        mNgiv_conPicture.setAdapter(new NineGridImageViewAdapter() {
            @Override
            protected void onDisplayImage(Context context, ImageView imageView, Object o) {
                GlideImageUtil.loadIntenetImg((String) o, imageView);
            }

            @Override
            protected void onItemImageClick(Context context, ImageView imageView, int index, List list) {
                Toast.makeText(context, String.valueOf(index + 1), Toast.LENGTH_SHORT).show();
            }
        });
        mNgiv_conPicture.setItemImageClickListener(new ItemImageClickListener() {
            @Override
            public void onItemImageClick(Context context, ImageView imageView, int index, List list) {
                Toast.makeText(context, "这是第" + index + 1 + "张图", Toast.LENGTH_SHORT).show();
            }
        });
        mNgiv_conPicture.setImagesData(mTernShilData.getDynamicImage());
    }

    private void initData() {
        mTv_nickName.setText(mTernShilData.getNickName());
        mTv_achool.setText(mTernShilData.getSchool());
        String sex = "";
        if (mTernShilData.getSex().equals("1")) {
            sex = "男";
            setSexMipmap(false);
        } else if (mTernShilData.getSex().equals("2")) {
            sex = "女";
            setSexMipmap(true);
        }
        StringBuilder builder = new StringBuilder();
        builder.append(sex);
        builder.append("  ");
        builder.append(DateUtil.getAgeByBirthday(mTernShilData.getBirthdayData()));
        builder.append("  ");
        builder.append(mTernShilData.getConstellation());
        builder.append("  ");
        builder.append(mTernShilData.getAddress());
        mTv_info.setText(builder.toString());
        mTv_name.setText(mTernShilData.getName());
        mTv_stuNo.setText(mTernShilData.getStuNo());
        GlideImageUtil.loadIntenetImg(mTernShilData.getHeadPortrait(), mCiv_headPortrait);

        //现在没时间，将来可对NineGridImageView进行一些自定义修改
        //实现自己控制行或列


        mNgiv_conPicture.setGap(3);
        mTv_phone.setText(mTernShilData.getPhone());
        mTv_signature.setText(mTernShilData.getSignature());

    }


    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_studentInfo_back);

        mLl_dynamicSelect = (LinearLayout) findViewById(R.id.ll_studentInfo_dynamicSelect);
        mLl_personInfo = (LinearLayout) findViewById(R.id.ll_studentInfo_personInfo);
        mLl_signInCount = (LinearLayout) findViewById(R.id.ll_studentInfo_signInCount);
        mLl_weekReportCount = (LinearLayout) findViewById(R.id.ll_studentInfo_weekReportCount);
        mLl_evaluate = (LinearLayout) findViewById(R.id.ll_studentInfo_evaluate);

        mIv_sex = (ImageView) findViewById(R.id.iv_studentInfo_sex);
        mCiv_headPortrait = (CircleImageView) findViewById(R.id.civ_studentInfo_headPortrait);
        mNgiv_conPicture = (NineGridImageView) findViewById(R.id.ngiv_studentInfo_dynamicImage);

        mBtn_intern = (Button) findViewById(R.id.btn_studentInfo_intern);

        mTv_name = (TextView) findViewById(R.id.tv_studentInfo_name);
        mTv_info = (TextView) findViewById(R.id.tv_studentInfo_info);
        mTv_nickName = (TextView) findViewById(R.id.tv_StudentInfo_nickName);
        mTv_achool = (TextView) findViewById(R.id.tv_studentInfo_school);
        mTv_signature = (TextView) findViewById(R.id.tv_studentInfo_signature);
        mTv_phone = (TextView) findViewById(R.id.tv_studentInfo_phone);
        mTv_stuNo = (TextView) findViewById(R.id.tv_StudentInfo_stuNo);


        mIb_back.setOnClickListener(this);
        mLl_dynamicSelect.setOnClickListener(this);
        mLl_personInfo.setOnClickListener(this);
        mLl_signInCount.setOnClickListener(this);
        mLl_weekReportCount.setOnClickListener(this);
        mCiv_headPortrait.setOnClickListener(this);
        mLl_evaluate.setOnClickListener(this);
        mBtn_intern.setOnClickListener(this);

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
                startActivity(new Intent(StudentInfoActivity.this, EvaluationOfTeacherActivity.class));
                break;
            case R.id.btn_studentInfo_intern:
                Toast.makeText(this, "实习岗位", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setExpandData() {
        Random random = new Random();
        int i = random.nextInt(5);
        List imageList;
        mTernShilData = new TernShipInfo();
        switch (i) {
            case 0:

                mTernShilData.setClassGrade("计算机应用一班");
                mTernShilData.setName("刘田田");
                mTernShilData.setStuNo("2016016897");
                mTernShilData.setDepartment("交通信息工程系");
                imageList = new ArrayList<String>();
                imageList.add("https://img5.duitang.com/uploads/item/201511/11/20151111213032_dmvRn.jpeg");
                mTernShilData.setDynamicImage(imageList);
                mTernShilData.setHeadPortrait("https://img5.duitang.com/uploads/item/201407/28/20140728222120_wzXK5.thumb.700_0.jpeg");
                mTernShilData.setNickName("无恙、别来");
                mTernShilData.setSchool("河南交通职业技术学院");
                mTernShilData.setPhone("15489657823");
                mTernShilData.setSex("人生，不只为他人而活，还为自己而活");
                mTernShilData.setAddress("河南省洛阳市");
                mTernShilData.setBirthdayData(new Date(System.currentTimeMillis()));
                mTernShilData.setConstellation("射手座");
                break;
            case 1:

                mTernShilData.setClassGrade("数字媒体一班");
                mTernShilData.setName("范亚可");
                mTernShilData.setStuNo("2016016782");
                mTernShilData.setDepartment("交通信息工程系");
                mTernShilData.setPhone("15489657823");
                imageList = new ArrayList<String>();
                imageList.add("https://img5.duitang.com/uploads/item/201407/28/20140728222120_wzXK5.thumb.700_0.jpeg");
                imageList.add("https://img5.duitang.com/uploads/item/201410/09/20141009232121_Ji2uB.jpeg");
                imageList.add("http://pic32.photophoto.cn/20140901/0037037560819466_b.jpg");
                mTernShilData.setDynamicImage(imageList);
                mTernShilData.setHeadPortrait("http://up.qqya.com/allimg/201710-t/17-101800_32138.jpg");
                mTernShilData.setNickName("无恙、别来");
                mTernShilData.setSchool("河南交通职业技术学院");
                mTernShilData.setSex("1");
                mTernShilData.setSignature("胖子，胖子，都是胖子");
                mTernShilData.setAddress("河南省洛阳市");
                mTernShilData.setBirthdayData(new Date(System.currentTimeMillis()));
                mTernShilData.setConstellation("射手座");
                break;
            case 2:
                mTernShilData.setClassGrade("计算机应用一班");
                mTernShilData.setName("孙亚科");
                mTernShilData.setStuNo("2016016897");
                mTernShilData.setDepartment("交通信息工程系");
                imageList = new ArrayList<String>();
                imageList.add("http://pic20.photophoto.cn/20110804/0006019011080395_b.jpg");
                imageList.add("http://pic29.photophoto.cn/20131119/0044040987321641_b.jpg");
                mTernShilData.setDynamicImage(imageList);
                mTernShilData.setPhone("15489657823");
                mTernShilData.setHeadPortrait("http://img.zcool.cn/community/018210594e6f2ca8012193a389132c.jpg@1280w_1l_2o_100sh.png");
                mTernShilData.setNickName("无恙、别来");
                mTernShilData.setSchool("河南交通职业技术学院");
                mTernShilData.setSex("1");
                mTernShilData.setSignature("success or 平庸");
                mTernShilData.setAddress("河南省洛阳市");
                mTernShilData.setBirthdayData(new Date(System.currentTimeMillis()));
                mTernShilData.setConstellation("射手座");
                break;
            case 3:
                mTernShilData.setClassGrade("计算机应用一班");
                mTernShilData.setName("孙逸豪");
                mTernShilData.setStuNo("2016016457");
                mTernShilData.setDepartment("交通信息工程系");
                imageList = new ArrayList<String>();
                mTernShilData.setPhone("15489657823");
                mTernShilData.setDynamicImage(imageList);
                mTernShilData.setHeadPortrait("https://img4.duitang.com/uploads/item/201510/19/20151019103415_cSWLa.thumb.700_0.jpeg");
                mTernShilData.setNickName("无恙、别来");
                mTernShilData.setSchool("河南交通职业技术学院");
                mTernShilData.setSex("1");
                mTernShilData.setSignature("我都不知道我自己是谁，我是哪个人瞎编的");
                mTernShilData.setAddress("河南省洛阳市");
                mTernShilData.setBirthdayData(new Date(System.currentTimeMillis()));
                mTernShilData.setConstellation("射手座");
                break;
            case 4:

                mTernShilData.setClassGrade("数字媒体一班");
                mTernShilData.setName("范亚可");
                mTernShilData.setStuNo("2016016782");
                mTernShilData.setPhone("15489657823");
                mTernShilData.setDepartment("交通信息工程系");
                imageList = new ArrayList<String>();
                imageList.add("http://scimg.158pic.com/allimg/170225/106-1F225104T5K0.jpg");
                mTernShilData.setDynamicImage(imageList);
                mTernShilData.setHeadPortrait("https://cdnq.duitang.com/uploads/item/201505/07/20150507171629_sXZKz.jpeg");
                mTernShilData.setNickName("无恙、别来");
                mTernShilData.setSchool("河南交通职业技术学院");
                mTernShilData.setSex("1");
                mTernShilData.setSignature("我都不知道我自己是谁，我是哪个人瞎编的");
                mTernShilData.setAddress("河南省洛阳市");
                mTernShilData.setBirthdayData(new Date(System.currentTimeMillis()));
                mTernShilData.setConstellation("射手座");
                break;
            default:

                mTernShilData.setClassGrade("数字媒体一班");
                mTernShilData.setName("范亚可");
                mTernShilData.setStuNo("2016016782");
                mTernShilData.setPhone("15489657823");
                mTernShilData.setDepartment("交通信息工程系");
                imageList = new ArrayList<String>();
                imageList.add("http://scimg.158pic.com/allimg/170225/106-1F225104T5K0.jpg");
                mTernShilData.setDynamicImage(imageList);
                mTernShilData.setHeadPortrait("https://cdnq.duitang.com/uploads/item/201505/07/20150507171629_sXZKz.jpeg");
                mTernShilData.setNickName("无恙、别来");
                mTernShilData.setSchool("河南交通职业技术学院");
                mTernShilData.setSex("1");
                mTernShilData.setSignature("我都不知道我自己是谁，我是哪个人瞎编的");
                mTernShilData.setAddress("河南省洛阳市");
                mTernShilData.setBirthdayData(new Date(System.currentTimeMillis()));
                mTernShilData.setConstellation("射手座");
                break;

        }
    }
}
