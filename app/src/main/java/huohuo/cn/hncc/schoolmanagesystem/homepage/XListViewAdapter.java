package huohuo.cn.hncc.schoolmanagesystem.homepage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaeger.ninegridimageview.ItemImageClickListener;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.GlideImageUtil;


/**
 * Created by Windows on 2018/5/9.
 *
 */

public class XListViewAdapter extends BaseAdapter {

    private final Context context;
    private List<WeeklyReportBean> mList_XListView;
    private final int mLayoutResId;
    private final Activity activity;
    //头像
    private ImageView mIv_itemHeadPortrait;
    /**
     * NineGridImageView这个控件
     * 需要先实现Adapter
     * 再填充数据
     * 除非进行修改里面代码
     */
    private NineGridImageView mNgiv_itemconPicture;
    //正文
    private TextView mTv_itemCon;
    //名字
    private TextView mTv_itemName;
    //个性签名
    private TextView mTv_itemSignature;
    //时间
    private TextView mTv_itemTime;
    //九宫格布局
    private NineGridImageViewAdapter mNineGridImageViewAdapter;
    private ItemImageClickListener mItemClickListener;
    private View.OnClickListener mOnHeadPortraitClickListener;

    public XListViewAdapter(Context context, int layoutResId, Activity activity) {
        this.context = context;
        this.mLayoutResId = layoutResId;
        mList_XListView = new ArrayList<WeeklyReportBean>();
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return mList_XListView.size();
    }

    @Override
    public Object getItem(int position) {
        return mList_XListView.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = View.inflate(context, mLayoutResId, null);
        } else {
            view = convertView;
        }

        initItemView(view);
        initItemAdapter();
        initItemData(position);

        return view;
    }

    //头像点击事件
    public void setOnHeadPortraitClickListener(@NotNull View.OnClickListener listener) {
        this.mOnHeadPortraitClickListener=listener;
    }

    //NineGridImage ClickListener
    public void setOnNineGridImageItemClickListener(@NotNull ItemImageClickListener itemClicklistener) {
        this.mItemClickListener = itemClicklistener;
    }

    public void setOnNineGridImageAdapter(@NotNull NineGridImageViewAdapter adapter) {
        this.mNineGridImageViewAdapter = adapter;

    }

    private void initItemAdapter() {

        if (mOnHeadPortraitClickListener != null) {
            mIv_itemHeadPortrait.setOnClickListener(mOnHeadPortraitClickListener);
        }

        if (mItemClickListener != null) {
            mNgiv_itemconPicture.setItemImageClickListener(mItemClickListener);
        }
        if (mNineGridImageViewAdapter != null) {
            mNgiv_itemconPicture.setAdapter(mNineGridImageViewAdapter);
        }
    }

    private void initItemData(int position) {
        WeeklyReportBean xListBean = mList_XListView.get(position);

        /**
         * 图片全部使用URL
         * */
        GlideImageUtil.loadIntenetImg(String.valueOf(xListBean.getHeadPortrait()), mIv_itemHeadPortrait);

        mNgiv_itemconPicture.setImagesData(xListBean.getListSrc());

        mTv_itemCon.setText(xListBean.getContent());
        mTv_itemName.setText(xListBean.getName());
        mTv_itemSignature.setText(xListBean.getSignature());
        mTv_itemTime.setText(xListBean.getTime());

    }

    private void initItemView(View view) {
        mIv_itemHeadPortrait = (ImageView) view.findViewById(R.id.iv_weeklyReportItem_photo);
        mNgiv_itemconPicture = (NineGridImageView) view.findViewById(R.id.ngiv_weeklyReportItem_conPicture);
        mTv_itemCon = (TextView) view.findViewById(R.id.tv_weeklyReportItem_con);
        mTv_itemName = (TextView) view.findViewById(R.id.tv_weeklyReportItem_name);
        mTv_itemSignature = (TextView) view.findViewById(R.id.tv_weeklyReportItem_signature);
        mTv_itemTime = (TextView) view.findViewById(R.id.tv_weeklyReportItem_time);

    }

    public void setXListViewData(List<WeeklyReportBean> list) {
        this.mList_XListView = list;
    }
}
