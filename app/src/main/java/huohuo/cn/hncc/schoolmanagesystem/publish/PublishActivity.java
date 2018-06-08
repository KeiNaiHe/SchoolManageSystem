package huohuo.cn.hncc.schoolmanagesystem.publish;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/6/1.
 */

public class PublishActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);

        initView();
    }

    private void initView() {
        LinearLayout ll_dynamic = (LinearLayout) findViewById(R.id.ll_publish_dynamic);
        LinearLayout ll_notifi = (LinearLayout) findViewById(R.id.ll_publish_notification);
        ImageButton ib_back = (ImageButton) findViewById(R.id.ib_publish_back);

        ll_dynamic.setOnClickListener(this);
        ll_notifi.setOnClickListener(this);
        ib_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_publish_dynamic:
                startActivity(new Intent(PublishActivity.this,PublishDynamicActivity.class));
                finish();
                break;
            case R.id.ll_publish_notification:
                startActivity(new Intent(PublishActivity.this,PublishNotificationActivity.class));
                finish();
                break;
            case R.id.ib_publish_back:
                finish();
                break;
                default:
                    break;

        }
    }
}
