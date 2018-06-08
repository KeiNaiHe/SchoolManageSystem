package huohuo.cn.hncc.schoolmanagesystem;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.GridView;

/**
 * Created by Windows on 2018/6/7.
 */

public class UserGridVeiw extends GridView {
    public UserGridVeiw(Context context) {
        super(context);
    }

    public UserGridVeiw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public UserGridVeiw(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSpec;
        // 这几行代码比较重要
        if (getLayoutParams().height == AbsListView.LayoutParams.WRAP_CONTENT) {
            heightSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        } else {
            heightSpec = heightMeasureSpec;
        }
        super.onMeasure(widthMeasureSpec, heightSpec);


    }


}
