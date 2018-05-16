package huohuo.cn.hncc.schoolmanagesystem.homepage.solay;

import android.content.Context;
import android.view.LayoutInflater;

import com.haibin.calendarview.WeekBar;

import huohuo.cn.hncc.guidepage.R;


/**
 * 自定义英文栏
 * Created by huanghaibin on 2017/11/30.
 */

public class SolarWeekBar extends WeekBar {

    public SolarWeekBar(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.solar_week_bar, this, true);
        setBackgroundColor(context.getResources().getColor(R.color.solar_background));
    }
}
