package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.GlideImageUtil;


/**
 * Created by Windows on 2018/5/3.
 * 实行岗位
 */

public class CADDrafterActivity extends AppCompatActivity {

    private ImageButton mIb_back;
    private ListView mLv_info;
    private List<ItemBean> mList_data;
    private ItemAdapter mItemAdapter;
    private TextView mTv_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caddrafter);

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
        mTv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CADDrafterActivity.this,PostInfoActivity.class));
            }
        });

        mList_data = new ArrayList<>();
        loadData();

        mItemAdapter = new ItemAdapter();
        mLv_info.setAdapter(mItemAdapter);
    }


    private void loadData() {
        ItemBean itemBean = new ItemBean();
        itemBean.name = "蒋一诺";
        itemBean.school = "河南交通职业技术学院";
        itemBean.headPortrait = "https://img5.duitang.com/uploads/item/201507/11/20150711160029_FVUfc.thumb.700_0.jpeg";
        itemBean.time = "2018-05-10 22:22";
        itemBean.startAndEndTime = "1931-01-01 至 2017-12-24";
        itemBean.company = "掌阅科技股份有限公司";
        itemBean.address = "上海";
        itemBean.check = -1;
        mList_data.add(itemBean);

        itemBean = new ItemBean();
        itemBean.name = "祝皮皮";
        itemBean.school = "河南交通职业技术学院";
        itemBean.headPortrait = "https://img5.duitang.com/uploads/item/201407/28/20140728222120_wzXK5.thumb.700_0.jpeg";
        itemBean.time = "2018-09-26 10:22";
        itemBean.startAndEndTime = "2017-08-25 至 2018—09-26";
        itemBean.company = "掌淘科技";
        itemBean.address = "上海浦东";
        itemBean.check = 1;
        mList_data.add(itemBean);

        itemBean = new ItemBean();
        itemBean.name = "霍正帅";
        itemBean.school = "河南交通职业技术学院";
        itemBean.headPortrait = "https://img5.duitang.com/uploads/item/201511/11/20151111213032_dmvRn.jpeg";
        itemBean.time = "2018-01-26 10:22";
        itemBean.startAndEndTime = "2017-08-25 至 2018—06-10";
        itemBean.company = "上海设计公司";
        itemBean.address = "上海浦东";
        itemBean.check = 0;
        mList_data.add(itemBean);


    }

    private void initView() {
        mLv_info = (ListView) findViewById(R.id.lv_cadDrafter_con);
        mIb_back = (ImageButton) findViewById(R.id.ib_cadDrafter_back);
        mTv_info = (TextView) findViewById(R.id.tv_cadDrafter_info);
    }

    class ItemAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mList_data.size();
        }

        @Override
        public Object getItem(int position) {
            return mList_data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(CADDrafterActivity.this, R.layout.item_caddrafter_lv, null);
            } else {
                view = convertView;
            }

            initItemViewAndData(view, position);

            return view;
        }

        private void initItemViewAndData(View view, int position) {
            ItemBean itemBean = mList_data.get(position);

            ImageView iv_check = (ImageView) view.findViewById(R.id.iv_cadDrafterItem_check);
            if (itemBean.check == 0) {
                iv_check.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.post_unapprove_icon));
            } else if (itemBean.check == 1) {
                iv_check.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.post_approve_icon));
            }else{
                iv_check.setImageBitmap(null);
            }

            TextView tv_address = (TextView) view.findViewById(R.id.tv_cadDrafterItem_address);
            tv_address.setText(itemBean.address);

            TextView tv_company = (TextView) view.findViewById(R.id.tv_cadDrafterItem_company);
            tv_company.setText(itemBean.company);

            TextView tv_name = (TextView) view.findViewById(R.id.tv_cadDrafterItem_name);
            tv_name.setText(itemBean.name);

            TextView tv_post = (TextView) view.findViewById(R.id.tv_cadDrafterItem_post);
            tv_post.setText(itemBean.post);

            TextView tv_school = (TextView) view.findViewById(R.id.tv_cadDrafterItem_school);
            tv_school.setText(itemBean.school);

            TextView tv_startAndEnd = (TextView) view.findViewById(R.id.tv_cadDrafterItem_startAndEnd);
            tv_startAndEnd.setText(itemBean.startAndEndTime);

            TextView tv_time = (TextView) view.findViewById(R.id.tv_cadDrafterItem_time);
            tv_time.setText(itemBean.time);

            CircleImageView civ_headPortrait = (CircleImageView) view.findViewById(R.id.civ_cadDrafterItem_headPortrait);
            GlideImageUtil.loadIntenetImg(itemBean.headPortrait, civ_headPortrait);

        }
    }


    private class ItemBean {
        private String headPortrait;
        private String name;
        private String school;
        private String post;//岗位
        private String time;
        private String startAndEndTime;
        private String company;
        private String address;
        private int check;//审核是否通过

    }
}
