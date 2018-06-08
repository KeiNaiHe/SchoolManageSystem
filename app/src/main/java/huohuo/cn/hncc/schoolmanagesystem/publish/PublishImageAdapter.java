package huohuo.cn.hncc.schoolmanagesystem.publish;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/6/3.
 * <p>
 * 需要多家在一个view
 * 用来装添加的那张图片
 */

public class PublishImageAdapter extends BaseAdapter {
    private Context mCtx;
    private List<Bitmap> mImageList;
    private final GridView mGridView;

    PublishImageAdapter(Context ctx, List<Bitmap> list, GridView gridView) {
        mCtx = ctx;
        mImageList = list;
        mGridView = gridView;
        addLastImage();
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        View view;

        if (convertView == null) {
            LayoutInflater from = LayoutInflater.from(mCtx);
            view = from.inflate(R.layout.item_publishinfo_image, null);
            viewHolder = new ViewHolder();
        } else {
            ViewHolder tag = (ViewHolder) convertView.getTag();
            if (tag.ItemId == 2) {
                //添加
                if (position == mImageList.size() - 1) {
                    view = convertView;
                } else {
                    LayoutInflater from = LayoutInflater.from(mCtx);
                    view = from.inflate(R.layout.item_publishinfo_image, null);
                    viewHolder = new ViewHolder();
                }
            } else {
                //图片
                if (position != mImageList.size() - 1) {
                    view = convertView;
                } else {
                    LayoutInflater from = LayoutInflater.from(mCtx);
                    view = from.inflate(R.layout.item_publishinfo_image, null);
                    viewHolder = new ViewHolder();
                }
            }

        }
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
        }
        viewHolder.imageView = view.findViewById(R.id.iv_publishImage_picture);
        CheckBox cb_select = view.findViewById(R.id.cb_publishImage_select);

        setViewWidthByHeight(viewHolder.imageView);
        cb_select.setChecked(false);
        cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImageList.remove(mImageList.get(position));
                    notifyDataSetChanged();
                }
            }
        });

        Log.i("小半", position + "position");
        Log.i("小半", mImageList.size() + "mImageList.size()");


        viewHolder.imageView.setImageBitmap(mImageList.get(position));


        //这一串代码用来判断图片的显示
        if (mImageList.size() < 9) {
            if (position == mImageList.size() - 1) {
                viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                cb_select.setVisibility(View.INVISIBLE);
                viewHolder.ItemId = 2;
            } else {
                viewHolder.ItemId = 1;
            }
        } else if (mImageList.size() == 9) {
            if (position == 8) {
                viewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                cb_select.setVisibility(View.INVISIBLE);
                viewHolder.ItemId = 2;
            } else {
                viewHolder.ItemId = 1;
            }
        } else {
            if (position >= 9) {
                view.setVisibility(View.INVISIBLE);
            } else {
                viewHolder.ItemId = 1;
            }
            //position=10
        }
        setViewWidthByHeight(mGridView);

        view.setTag(viewHolder);
        return view;
    }

    public void notifyDataSetChanged(List<Bitmap> list) {
        //mImageList = new ArrayList<>(list);
        addLastImage();
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    private void setViewWidthByHeight(ImageView view) {
        final ImageView mv = view;
        final ViewTreeObserver vto = mv.getViewTreeObserver();
        /**
         * 当执行onCreate的时候，View 也许还没执行完 measure 阶段，getWidth获取的结果为0
         * getMeasuredWidth()  是自身请求的
         * getWidth()   是父类绘制的
         */
        /**
         * 画这个view之前调用
         *
         * 疑问：画view的时候是先获取用户给的宽、高还是先计算可用的宽高？
         *    利用先计算父类分配的大小后获取用户给的大小的间歇
         *    获取分配的宽在赋给高
         *
         * 这个使用方法需要注意其版本变化，具体什么版本暂时不清楚
         */

        final ViewTreeObserver.OnPreDrawListener preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {

                int width = mv.getMeasuredWidth();
                int height = mv.getMeasuredHeight();

                Log.i("小半", "width" + width);
                Log.i("小半", "height" + height);

                android.view.ViewGroup.LayoutParams lp = mv.getLayoutParams();

                lp.height = mv.getMeasuredWidth();

                mv.setLayoutParams(lp);


                final ViewTreeObserver vto1 = mv.getViewTreeObserver();

                //调用一次之后移除，不影响性能
                vto1.removeOnPreDrawListener(this);

                return true;
            }
        };

        vto.addOnPreDrawListener(preDrawListener);
    }

    public void setData(List<Bitmap> list) {
        //mImageList = new ArrayList<>(list);
        addLastImage();
    }

    private void addLastImage() {
        mImageList.add(BitmapFactory.decodeResource(mCtx.getResources(), R.mipmap.icon_publish_add));
    }

    private class ViewHolder {
        //两种类型，一种添加图片，一种图片 1,2
        private int ItemId;
        private ImageView imageView;
    }


    //改变gridview的高
    private void setViewWidthByHeight(View view) {
        final View mv = view;
        final ViewTreeObserver vto = mv.getViewTreeObserver();
        final int listSize = mImageList.size();
        /**
         * 当执行onCreate的时候，View 也许还没执行完 measure 阶段，getWidth获取的结果为0
         * getMeasuredWidth()  是自身请求的
         * getWidth()   是父类绘制的
         */
        /**
         * 画这个view之前调用
         *
         * 疑问：画view的时候是先获取用户给的宽、高还是先计算可用的宽高？
         *    利用先计算父类分配的大小后获取用户给的大小的间歇
         *    获取分配的宽在赋给高
         *
         * 这个使用方法需要注意其版本变化，具体什么版本暂时不清楚
         */

        final ViewTreeObserver.OnPreDrawListener preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {

                int width = mv.getMeasuredWidth();
                int height = mv.getMeasuredHeight();

                Log.i("小半", "width" + width);
                Log.i("小半", "height" + height);

                android.view.ViewGroup.LayoutParams lp = mv.getLayoutParams();

                //lp.height = mv.getMeasuredWidth();
                int i = (mv.getMeasuredWidth() - 20 - 10) / 3;
                if (listSize <= 3) {
                    lp.height = i + 20 + 20;
                } else if (listSize <= 6) {
                    lp.height = 2 * i + 20 + 5;
                } else {
                    lp.height = 3 * i + 20 + 10;
                }

                mv.setLayoutParams(lp);


                final ViewTreeObserver vto1 = mv.getViewTreeObserver();

                //调用一次之后移除，不影响性能
                vto1.removeOnPreDrawListener(this);

                return true;
            }
        };

        vto.addOnPreDrawListener(preDrawListener);
    }
}
