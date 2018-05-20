package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/3.
 * 实习岗位
 */

public class CheckAllActivity extends Activity {

    private GridView mGridView;
    private ImageButton mIb_back;
    private CheckAllAdapter mAdapter;
    private String Texts[] =
            {
                    "我的实习生",
                    "签到",
                    "查看周记",
                    "实习岗位",
                    "查看通知",
                    "全部"
            };
    private int Icons[] = {
            R.mipmap.me_group_icon,
            R.mipmap.icon_sign,
            R.mipmap.icon_report,
            R.mipmap.icon_post,
            R.mipmap.message_icon,
            R.mipmap.find_friend_icon
    };
    private ArrayList<CheckAllBean> mList_data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkall);
        initServiceData();
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


        mAdapter = new CheckAllAdapter();
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position) {
                    case 0:
                        intent = new Intent(CheckAllActivity.this, MyTernsActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(CheckAllActivity.this, SignInActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent = new Intent(CheckAllActivity.this, WeeklyReportActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent = new Intent(CheckAllActivity.this, CADDrafterActivity.class);
                        startActivity(intent);

                        break;
                    case 5:
                        intent = new Intent(CheckAllActivity.this, CheckTheNotifiActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        Toast.makeText(CheckAllActivity.this, "后补", Toast.LENGTH_SHORT).show();
                        break;
                    case 7:
                        Toast.makeText(CheckAllActivity.this, "后补", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initView() {
        mIb_back = (ImageButton) findViewById(R.id.ib_checkAll_back);
        mGridView = (GridView) findViewById(R.id.gv_checkAll_icon);
    }

    private void initServiceData() {
        mList_data = new ArrayList<>();
        CheckAllBean bean;
        for (int i = 0; i < Icons.length; i++) {
            bean = new CheckAllBean();
            bean.icon = Icons[i];
            bean.text = Texts[i];
            mList_data.add(bean);
        }
    }

    private class CheckAllAdapter extends BaseAdapter {
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
                view = View.inflate(CheckAllActivity.this, R.layout.item_checkall_grid, null);
            } else {
                view = convertView;
            }
            initViewData(view, position);

            return view;
        }

        private void initViewData(View view, int position) {
            ImageView iv_picture = (ImageView) view.findViewById(R.id.iv_checkAllItem_picture);
            TextView tv_desc = (TextView) view.findViewById(R.id.tv_checkAllItem_desc);

            CheckAllBean checkAllBean = mList_data.get(position);

            iv_picture.setImageBitmap(BitmapFactory.decodeResource(getResources(), checkAllBean.icon));
            tv_desc.setText(checkAllBean.text);

        }
    }

    private class CheckAllBean {
        private int icon;
        private String text;

    }
}
