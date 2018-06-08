package huohuo.cn.hncc.schoolmanagesystem.publish;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.util.ArrayList;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/6/4.
 * 图库图片查询显示
 * <p>
 * 未处理：图片压缩功能
 * 图片压缩处理完会再次保存成文件
 */

public class PhotoShowActivity extends AppCompatActivity {

    private ArrayList<String> mImageList;
    private ArrayList<String> mReturnData;
    private boolean firstReturn = true;
    private int maxSelectCount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photoshow);

        //传过来还能选择的最大数量
        Intent intent = getIntent();
         final Bundle bundle = intent.getBundleExtra("Bundle");
        maxSelectCount = bundle.getInt("maxCount");
        /**
         * 查询图片，显示，选择，返回，点击放大
         */
        mReturnData = new ArrayList<>();
        mImageList = QueryPhoto();
        //图片处理，压缩
        setGridView();


        ImageButton ib_back = findViewById(R.id.ib_photoShow_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstReturn) {
                    firstReturn = false;
                    Toast.makeText(PhotoShowActivity.this, "数据未保存，再点击一次返回", Toast.LENGTH_SHORT).show();
                } else {
                    finish();
                }
            }
        });

        Button btn_finish = findViewById(R.id.btn_photoShow_finish);
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle1 = new Bundle();
                bundle1.putStringArrayList("SelectImage",mReturnData);
                intent.putExtra("Bundle", bundle1);
                PhotoShowActivity.this.setResult(RESULT_OK, intent);
                Toast.makeText(PhotoShowActivity.this, "数据返回", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

    private void setGridView() {
        GridView mGridVeiw = findViewById(R.id.gv_photoShow_imageList);
        PhotoShowAdapter adapter = new PhotoShowAdapter();
        mGridVeiw.setAdapter(adapter);


    }

    private ArrayList<String> QueryPhoto() {
        ArrayList<String> list = new ArrayList<>();

        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver mContentResolver = PhotoShowActivity.this
                .getContentResolver();

        Cursor mCursor = mContentResolver.query(mImageUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"},
                MediaStore.Images.Media.DATE_MODIFIED);
        if (mCursor != null) {
            while (mCursor.moveToNext()) {
                // 获取图片的路径
                String path = mCursor.getString(mCursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                list.add(path);
            }
            mCursor.close();
        }

        return list;

    }

    class PhotoShowAdapter extends BaseAdapter {
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

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            if (convertView == null) {
                view = View.inflate(PhotoShowActivity.this, R.layout.item_publishinfo_image, null);
            } else {
                view = convertView;
            }

            final ImageView iv_picture = view.findViewById(R.id.iv_publishImage_picture);
            final CheckBox cb_select = view.findViewById(R.id.cb_publishImage_select);

            // Bitmap bitmap= BitmapFactory.decodeStream(getClass().getResourceAsStream(mImageList.get(position)));
            setViewWidthByHeight(iv_picture);//通过宽设置高，保持长宽一致


            iv_picture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PhotoShowActivity.this, PhotoZoomActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("ImageZoom", mImageList.get(position));
                    intent.putExtra("Bundle",bundle);
                    startActivity(intent);
                }
            });
//            int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
//            // 使用最大可用内存值的1/8作为缓存的大小。
//            int cacheSize = maxMemory / 8;
//            mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
//                @Override
//                protected int sizeOf(String key, Bitmap bitmap) { // 重写此方法来衡量每张图片的大小，默认返回图片数量。
//                    return bitmap.getByteCount() / 1024;
//                }
//            };


//            Bitmap bitmap = decodeSampledBitmapFromFile(mImageList.get(position),
//                    iv_picture.getMeasuredWidth(), iv_picture.getMeasuredWidth());


            //iv_picture.setImageBitmap(bitmap);

            //loadBitmap(542, iv_picture, position);

            // final int measuredWidth = iv_picture.getMeasuredWidth();


            Picasso.get()
                    .load(new File(mImageList.get(position)))
                    .transform(new Transformation() {
                        @Override
                        public Bitmap transform(Bitmap source) {
                            Bitmap result;

                            int size = Math.min(source.getWidth(), source.getHeight());
                            int x = (source.getWidth() - size) / 2;
                            int y = (source.getHeight() - size) / 2;
                            result = Bitmap.createBitmap(source, x, y, size, size);


                            if (result != source)

                            {
                                source.recycle();
                            }
                            return result;
                        }

                        @Override
                        public String key() {
                            return "square()";
                        }
                    })
                    .into(iv_picture);

            cb_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        if (mReturnData.size() == maxSelectCount) {
                            cb_select.setChecked(false);
                            Toast.makeText(PhotoShowActivity.this, "超过最大选择数", Toast.LENGTH_SHORT).show();
                        } else {
                            mReturnData.add(mImageList.get(position));
                        }
                    } else {
                        if (mReturnData.contains(mImageList.get(position))) {
                            mReturnData.remove(mImageList.get(position));
                        }
                    }
                }
            });
            return view;
        }

    }


    public void setViewWidthByHeight(ImageView view) {
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


//    public static Bitmap decodeSampledBitmapFromFile(String fileuri, int reqWidth, int reqHeight) {
//        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
//        final BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(fileuri, options);
//        // 调用上面定义的方法计算inSampleSize值
//        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
//        // 使用获取到的inSampleSize值再次解析图片
//        options.inJustDecodeBounds = false;
//        return BitmapFactory.decodeFile(fileuri, options);
//    }
//
//
//    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth,
//                                            int reqHeight) { // 源图片的高度和宽度
//        final int height = options.outHeight;
//        final int width = options.outWidth;
//        int inSampleSize = 1;
//        if (height > reqHeight || width > reqWidth) { // 计算出实际宽高和目标宽高的比率
//            final int heightRatio = Math.round((float) height / (float) reqHeight);
//            final int widthRatio = Math.round((float) width / (float) reqWidth);
//            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高一定都会大于等于目标的宽和高。
//            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
//        }
//        return inSampleSize;
//    }
//
//    public void loadBitmap(int resId, ImageView imageView, int position) {
//        final String imageKey = String.valueOf(resId);
//        final Bitmap bitmap = getBitmapFromMemCache(imageKey);
//        if (bitmap != null) {
//            imageView.setImageBitmap(bitmap);
//        } else {
//            imageView.setImageBitmap(BitmapFactory.decodeFile(mImageList.get(position)));
//            BitmapWorkerTask task = new BitmapWorkerTask(position);
//            task.execute(resId);
//        }
//    }
//
//    public void addBitmapToMemoryCache(String key, Bitmap bitmap) {
//        if (getBitmapFromMemCache(key) == null) {
//            mMemoryCache.put(key, bitmap);
//        }
//    }
//
//    public Bitmap getBitmapFromMemCache(String key) {
//        return mMemoryCache.get(key);
//    }
//
//    class BitmapWorkerTask extends AsyncTask<Integer, Void, Bitmap> {
//        private final int position; // 在后台加载图片。
//
//        BitmapWorkerTask(int position) {
//            this.position = position;
//        }
//
//        @Override
//        protected Bitmap doInBackground(Integer... params) {
//            final Bitmap bitmap = decodeSampledBitmapFromFile(mImageList.get(position), 100, 100);
//            addBitmapToMemoryCache(String.valueOf(params[0]), bitmap);
//            return bitmap;
//        }
//    }

}
