package huohuo.cn.hncc.schoolmanagesystem.homepage.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/31.
 */

public class EvaluationOfTeacherActivity extends AppCompatActivity implements View.OnClickListener {

    private String EvaluationSchool = "EvaluationOfSchool";
    private String EvaluationEnterprise = "EvaluationOfEnterprise";
    private String Evaluation = "Evaluation";

    private HashMap<String, String> mMap_Data;
    private TextView mTv_enterprise;
    private TextView mTv_evaluation;
    private TextView mTv_grade;
    private TextView mTv_school;
    private LinearLayout mLl_layout;
    //显示大字的头部view
    private boolean isHeaderView = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluationofteacher);

        mMap_Data = getData();
        initView();
        initData();
    }

    private void initData() {
        if (mMap_Data.get(EvaluationSchool).equals("")) {
            mTv_school.setText("暂无数据");
            isHeaderView = false;
        } else {
            mTv_school.setText(mMap_Data.get(EvaluationSchool));
        }
        if (mMap_Data.get(EvaluationEnterprise).equals("")) {
            mTv_enterprise.setText("暂无数据");
            isHeaderView = false;
        } else {
            mTv_school.setText(mMap_Data.get(EvaluationEnterprise));
        }
        setHeaderLayout();


    }

    private void setHeaderLayout() {
        if (!isHeaderView) {
            mLl_layout.setVisibility(View.INVISIBLE);
        } else {
            mLl_layout.setVisibility(View.VISIBLE);
            mTv_evaluation.setText(mMap_Data.get(Evaluation));
            setEvaluationGrade(mMap_Data.get(Evaluation));
        }
    }

    private void setEvaluationGrade(String evaluation) {
        int i = Integer.parseInt(evaluation);
        switch (i / 10) {
            case 9:
                mTv_grade.setText("考评优秀");
                break;
            case 8:
                mTv_grade.setText("考评良好");
                break;
            case 7:
                mTv_grade.setText("考评可以");
                break;
            case 6:
                mTv_grade.setText("考评及格");
                break;
            default:
                mTv_grade.setText("你已凉凉");
                break;

        }
    }


    private void initView() {
        mTv_enterprise = (TextView) findViewById(R.id.tv_evaluation_enterprise);
        mTv_evaluation = (TextView) findViewById(R.id.tv_evaluation_evaluation);
        mTv_grade = (TextView) findViewById(R.id.tv_evaluation_grade);
        mTv_school = (TextView) findViewById(R.id.tv_evaluation_school);
        mLl_layout = (LinearLayout) findViewById(R.id.ll_evaluation_layout);
        ImageButton ib_back = (ImageButton) findViewById(R.id.ib_evaluation_back);
        LinearLayout ll_school = (LinearLayout) findViewById(R.id.ll_evaluation_school);
        LinearLayout ll_enterprise = (LinearLayout) findViewById(R.id.ll_evaluation_enterprise);


        ll_school.setOnClickListener(this);
        ll_enterprise.setOnClickListener(this);
        ib_back.setOnClickListener(this);
    }

    public HashMap<String, String> getData() {
        HashMap<String, String> hashMap = new HashMap<>();


        Random random = new Random();
        int i = random.nextInt(6);
        switch (i) {
            case 0:
                hashMap.put("EvaluationOfSchool", "89");
                hashMap.put("EvaluationOfEnterprise", "75");
                hashMap.put("Evaluation", "81");
                break;
            case 1:
                hashMap.put("EvaluationOfSchool", "");
                hashMap.put("EvaluationOfEnterprise", "75");
                hashMap.put("Evaluation", "");
                break;
            case 2:
                hashMap.put("EvaluationOfSchool", "89");
                hashMap.put("EvaluationOfEnterprise", "");
                hashMap.put("Evaluation", "");
                break;
            case 3:
                hashMap.put("EvaluationOfSchool", "");
                hashMap.put("EvaluationOfEnterprise", "");
                hashMap.put("Evaluation", "");
                break;
            case 4:
                hashMap.put("EvaluationOfSchool", "73");
                hashMap.put("EvaluationOfEnterprise", "75");
                hashMap.put("Evaluation", "74");
                break;
            case 5:
                hashMap.put("EvaluationOfSchool", "24");
                hashMap.put("EvaluationOfEnterprise", "45");
                hashMap.put("Evaluation", "32");
                break;
            default:
                hashMap.put("EvaluationOfSchool", "75");
                hashMap.put("EvaluationOfEnterprise", "45");
                hashMap.put("Evaluation", "64");
                break;
        }

        return hashMap;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_evaluation_enterprise:
                break;
            case R.id.ll_evaluation_school:
                break;
            case R.id.ib_evaluation_back:
                finish();
                break;
            default:
                break;
        }
    }
}
