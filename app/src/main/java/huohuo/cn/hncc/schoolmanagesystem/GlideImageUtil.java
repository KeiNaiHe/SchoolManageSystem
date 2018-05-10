package huohuo.cn.hncc.schoolmanagesystem;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/9.
 */

public class GlideImageUtil {
    /**
     * Glide网络获取图片
     * */
    public static void loadIntenetImg(String imgUrl, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imgUrl)
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.error)
                        .error(R.mipmap.error)
                        .centerCrop()
                        .dontAnimate()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);

    }

    /**
     * Glide从资源文件中加载图片
     */
    public static void loadNativeImg(int imgRes, ImageView imageView) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(imgRes)
                .apply(new RequestOptions().dontAnimate()
                .placeholder(R.mipmap.error)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);
    }


}
