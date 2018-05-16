package huohuo.cn.hncc.schoolmanagesystem.homepage;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Map;

import cn.bingoogolapple.bgabanner.BGABanner;
import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.App;
import huohuo.cn.hncc.schoolmanagesystem.MainFragmentBean;
import huohuo.cn.hncc.schoolmanagesystem.homepage.activity.CADDrafterActivity;
import huohuo.cn.hncc.schoolmanagesystem.homepage.activity.CheckAllActivity;
import huohuo.cn.hncc.schoolmanagesystem.homepage.activity.CheckTheNotifiActivity;
import huohuo.cn.hncc.schoolmanagesystem.homepage.activity.MyTernsActivity;
import huohuo.cn.hncc.schoolmanagesystem.homepage.activity.SignInActivity;
import huohuo.cn.hncc.schoolmanagesystem.homepage.activity.WeeklyReportActivity;
import huohuo.cn.hncc.schoolmanagesystem.homepage.engine.Engine;
import huohuo.cn.hncc.schoolmanagesystem.homepage.model.BannerModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Windows on 2018/5/1.
 */

public class HomeFragment extends Fragment implements BGABanner.Delegate<ImageView, String> {

    private BGABanner mBanner;
    private Engine mEngine;
    private View mView;
    private Context mCtx;
    private Context mActivityCtx;
    private MainFragmentBean mFragBean;
    private String desc[] =
            {
                    "我的实习生",
                    "签到",
                    "查看周记",
                    "实习岗位",
                    "查看通知",
                    "全部"
            };
    private int picture[] = {
            R.mipmap.me_group_icon,
            R.mipmap.icon_sign,
            R.mipmap.icon_report,
            R.mipmap.icon_post,
            R.mipmap.message_icon,
            R.mipmap.find_friend_icon
    };

    private GridView mGv_main;
    private List<Map<String, Object>> mList_data;
    private View mScrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);

        initView();
        initData();
        return mView;
    }


    private void initData() {
        //获取上下文
        mCtx = App.getInstance();
        mActivityCtx = getActivity();

        //加载Banner
        mEngine = App.getInstance().getEngine();
        loadData(mBanner, 4);


        initTableLayout();
        initGridListView();

    }

    /**
     * GridView与ScrollView不兼容
     * 改为tableLayout
     * 设置GridView上的图片、文字数据
     */
    private void initGridListView() {


    }

    private void initView() {
        mBanner = (BGABanner) mView.findViewById(R.id.banner_home);
        //tableLayout加载布局


    }


    private void initTableLayout() {
        /**
         * 将Item布局放入tableLayout中
         *  table里面每个Item取出相应对象，再去解析对象里面所包含的View
         * */
        View view;
        view = mView.findViewById(R.id.layout_tabItem1);
        initTableItemLayout(view, 0);
        view = mView.findViewById(R.id.layout_tabItem2);
        initTableItemLayout(view, 1);
        view = mView.findViewById(R.id.layout_tabItem3);
        initTableItemLayout(view, 2);
        view = mView.findViewById(R.id.layout_tabItem4);
        initTableItemLayout(view, 3);
        view = mView.findViewById(R.id.layout_tabItem5);
        initTableItemLayout(view, 4);
        view = mView.findViewById(R.id.layout_tabItem6);
        initTableItemLayout(view, 5);
    }

    private void initTableItemLayout(View view, int pos) {
        //关于TableItem点击事件的，没崩
//        05-03 05:07:48.546 1144-1580/? E/AudioMixer: AudioMixer::getTrackName out of available tracks
//        05-03 05:07:48.546 1144-1580/? E/AudioFlinger: no more track names available
//        05-03 05:07:48.546 1144-1580/? E/AudioFlinger: createTrack_l() initCheck failed -12; no control block?
//        05-03 05:07:48.546 1511-1578/system_process E/AudioTrack: AudioFlinger could not create track, status: -12
//        05-03 05:07:48.546 1511-1578/system_process E/SoundPool: Error creating AudioTrack

        ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_homeTable_icon);
        iv_icon.setImageBitmap(BitmapFactory.decodeResource(getResources(),picture[pos]));
        TextView tv_desc = (TextView) view.findViewById(R.id.tv_homeTable_desc);

        tv_desc.setText(desc[pos]);
        switch (pos) {
            case 0://我的实习生
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivityCtx, MyTernsActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 1://签到
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivityCtx, SignInActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 2://查看周记
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivityCtx, WeeklyReportActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 3://实习岗位

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivityCtx, CADDrafterActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 4://查看通知

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivityCtx, CheckTheNotifiActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            case 5://全部
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mActivityCtx, CheckAllActivity.class);
                        startActivity(intent);
                    }
                });
                break;
            default:
        }


    }


    private void loadData(final BGABanner banner, final int count) {
        mEngine.fetchItemsWithItemCount(count).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();

                /**
                 * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
                 * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
                 */
                banner.setAutoPlayAble(bannerModel.imgs.size() > 1);

                banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
                    @Override
                    public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable String model, int position) {
                        //model是图片的url
                        Glide.with(itemView.getContext())
                                .load(model)
                                .apply(new RequestOptions().placeholder(R.drawable.holder).error(R.drawable.holder).dontAnimate().centerCrop())
                                .into(itemView);
                    }

                });

                //第二个参数为显示的文字集合
                banner.setData(bannerModel.imgs, null);

            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        Toast.makeText(banner.getContext(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onAttach(Context context) {
        mActivityCtx = context;
        super.onAttach(context);
    }
}
