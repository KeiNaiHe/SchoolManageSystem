package huohuo.cn.hncc.schoolmanagesystem.homepage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Windows on 2018/5/31.
 * 实习生信息类
 */

public class TernShipInfo implements Serializable {
    private String name;
    private String nickName;
    private String signature;
    private String headPortrait;
    private String stuNo;
    private String sex;
    private String classGrade;
    private List<String> dynamicImage;
    private String school;
    private String phone;
    private String department;
    private Date birthdayData;
    private String constellation;
    private String address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthdayData() {
        return birthdayData;
    }

    public void setBirthdayData(Date birthdayData) {
        this.birthdayData = birthdayData;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClassGrade() {
        return classGrade;
    }

    public void setClassGrade(String classGrade) {
        this.classGrade = classGrade;
    }

    public List<String> getDynamicImage() {
        return dynamicImage;
    }

    public void setDynamicImage(List<String> dynamicImage) {
        this.dynamicImage = dynamicImage;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
