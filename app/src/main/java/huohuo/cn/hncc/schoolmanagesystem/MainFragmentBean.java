package huohuo.cn.hncc.schoolmanagesystem;

import huohuo.cn.hncc.schoolmanagesystem.homepage.HomeFragment;
import huohuo.cn.hncc.schoolmanagesystem.messagepage.MessageFragment;
import huohuo.cn.hncc.schoolmanagesystem.mypage.MyFragment;

/**
 * Created by Windows on 2018/5/2.
 */

public class MainFragmentBean {
    public HomeFragment getHomeFragment() {
        return homeFragment;
    }

    public void setHomeFragment(HomeFragment homeFragment) {
        this.homeFragment = homeFragment;
    }

    private HomeFragment homeFragment;

    public MessageFragment getMessFragment() {
        return messFragment;
    }

    public void setMessFragment(MessageFragment messFragment) {
        this.messFragment = messFragment;
    }

    public DynamicFragment getDynamicFragment() {
        return dynamicFragment;
    }

    public void setDynamicFragment(DynamicFragment dynamicFragment) {
        this.dynamicFragment = dynamicFragment;
    }

    public MyFragment getMyFragment() {
        return myFragment;
    }

    public void setMyFragment(MyFragment myFragment) {
        this.myFragment = myFragment;
    }

    private MessageFragment messFragment;
    private DynamicFragment dynamicFragment;
    private MyFragment myFragment;
}
