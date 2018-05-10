package huohuo.cn.hncc.schoolmanagesystem.homepage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Windows on 2018/5/9.
 */

public class XListViewBean {
    private Object headPortrait;
    private String name;
    private String time;
    private String signature;
    private String content;
    private List<Object> listSrc;

    public Object getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(Object headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Object> getListSrc() {
        return listSrc;
    }

    public void setListSrc(ArrayList<Object> listSrc) {
        this.listSrc = listSrc;
    }
}
