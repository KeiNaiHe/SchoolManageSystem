package huohuo.cn.hncc.guidepage;

import android.Manifest;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import huohuo.cn.hncc.loginpage.LoginActivity;


/**
 * Created by Windows on 2018/4/27.
 * 引导页面
 * 三秒钟进入登录界面
 */

public class guideActivity extends Activity {

    // 要申请的权限
    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_PHONE_STATE
    };
    private AlertDialog dialog;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            return false;
        }
    });
    private AutoZoomImageView mIv_image;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            for (String permission : permissions) {
//                // 检查该权限是否已经获取
//                int i = ContextCompat.checkSelfPermission(this, permission);
//                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
//                if (i != PackageManager.PERMISSION_GRANTED) {
//                    // 如果没有授予该权限，就去提示用户请求
//                    showDialogTipUserRequestPermission(permission);
//                }
//            }
//            initView();
//            startAnim();
//        } else {
//            initView();
//            startAnim();
//        }


        initView();
        startAnim();
    }

    private void initView() {
        mIv_image = (AutoZoomImageView) findViewById(R.id.iv_guide);
    }

    private void startAnim() {
        //这是什么写法？
        mIv_image.post(new Runnable() {//iv即AutoZoomInImageView
            @Override
            public void run() {
                //简单方式启动放大动画
                //    iv.init()
                //    .startZoomInByScaleDeltaAndDuration(0.3f, 1000, 1000);
                // 放大增量是0.3，放大时间是1000毫秒，放大开始时间是1000毫秒以后
                //使用较为具体的方式启动放大动画
                mIv_image.init()
                        .setScaleDelta(0.2f)//放大的系数是原来的（1 + 0.3）倍
                        .setDurationMillis(1500)//动画的执行时间为1000毫秒
                        .setOnZoomListener(new AutoZoomImageView.OnZoomListener() {
                            @Override
                            public void onStart(View view) {
                                //放大动画开始时的回调
                            }

                            @Override
                            public void onUpdate(View view, float progress) {
                                //放大动画进行过程中的回调 progress取值范围是[0,1]
                            }

                            @Override
                            public void onEnd(View view) {
                                //放大动画结束时的回调
                                Intent intent = new Intent(guideActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .start(1000);//延迟1000毫秒启动
            }

        });
    }

    private void vanishView(final View view) {
        if (view == null) return;
        ValueAnimator va = ValueAnimator.ofFloat(1f, 0f);
        va.setDuration(500);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float alpha = (Float) animation.getAnimatedValue();
                if (alpha != null) {
                    view.setAlpha(alpha);
                }
            }
        });
        va.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        va.start();
    }


    // 提示用户该请求权限的弹出框
    private void showDialogTipUserRequestPermission(String permission) {
        String title = "";
        String content = "";
        switch (permission) {
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                title = "SD卡写入权限";
                content = "";
                break;
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                title = "访问您的大概位置";
                content = "";
                break;
            case Manifest.permission.READ_PHONE_STATE:
                title = "读取手机信息";
                content = "";
                break;

            default:
                break;
        }
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage("由于权限原因，可能会导致本应用不可用")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startRequestPermission();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        System.exit(0);
                    }
                }).setCancelable(false).show();
    }

    // 开始提交请求权限
    private void startRequestPermission() {
        ActivityCompat.requestPermissions(this, permissions, 321);
    }

    // 用户权限 申请 的回调方法
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (int grantResult : grantResults) {
                    if (grantResult != PackageManager.PERMISSION_GRANTED) {
                        // 判断用户是否 点击了不再提醒。(检测该权限是否还可以申请)
                        boolean b = shouldShowRequestPermissionRationale(permissions[0]);
                        if (!b) {
                            // 用户还是想用我的 APP 的
                            // 提示用户去应用设置界面手动开启权限
                            showDialogTipUserGoToAppSettting();
                        } else
                            finish();
                    } else {
                        Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
    }


    // 提示用户去应用设置界面手动开启权限
    private void showDialogTipUserGoToAppSettting() {

        dialog = new AlertDialog.Builder(this)
                .setTitle("权限问题")
                .setMessage("当前权限不可用，请在-应用设置-权限-中，设置所需权限")
                .setPositiveButton("立即开启", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 跳转到应用设置界面
                        goToAppSetting();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setCancelable(false).show();
    }

    // 跳转到当前应用的设置界面
    private void goToAppSetting() {
        Intent intent = new Intent();

        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);

        startActivityForResult(intent, 123);
    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123) {

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 检查该权限是否已经获取
                int i = ContextCompat.checkSelfPermission(this, permissions[0]);
                // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
                if (i != PackageManager.PERMISSION_GRANTED) {
                    // 提示用户应该去应用设置界面手动开启权限
                    showDialogTipUserGoToAppSettting();
                } else {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(this, "权限获取成功", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}
