package huohuo.cn.hncc.schoolmanagesystem.homepage;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;

import calendar.haibin.com.Calendar;
import calendar.haibin.com.WeekBar;
import huohuo.cn.hncc.guidepage.R;

/**
 * 自定义英文栏
 * Created by huanghaibin on 2017/11/30.
 */

public class EnglishWeekBar extends WeekBar {

    private int mPreSelectedIndex;

    public EnglishWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.english_week_bar, this, true);
        setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void onDateSelected(Calendar calendar, int weekStart, boolean isClick) {
        getChildAt(mPreSelectedIndex).setSelected(false);
        int viewIndex = getViewIndexByCalendar(calendar, weekStart);
        getChildAt(viewIndex).setSelected(true);
        mPreSelectedIndex = viewIndex;
    }
}
