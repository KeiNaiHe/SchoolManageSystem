package huohuo.cn.hncc.schoolmanagesystem.publish;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.suke.widget.SwitchButton;

import java.util.ArrayList;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.UserGridVeiw;
import me.curzbin.library.BottomDialog;
import me.curzbin.library.Item;
import me.curzbin.library.OnItemClickListener;

/**
 * Created by Windows on 2018/6/1.
 * 最大图片数设为9
 * 动态发布
 */

public class PublishDynamicActivity extends AppCompatActivity implements View.OnClickListener {

    EditText mEt_editor;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private ArrayList<String> listImagePath;
    private ArrayList<Bitmap> mList = new ArrayList<>();
    private PublishInfoAdapter adapter;
    private String mCurrentPosition = "";
    private ArrayList<String> list = new ArrayList<String>();
    private SwitchButton mSwitchButton;
    private TextView mTv_address;
    private int PermissionCode = 101;

    public static final int TAKE_PHOTO = 1;
    public static final int SELECT_PHOTO = 2;
    private Uri imageUri;
    private UserGridVeiw mGridView;
    private int REQUEST_IMAGE_CAPTURE = 645;
    private int REQUEST_IMAGE_PHOTO = 544;
    private PublishImageAdapter mAdapter;
    private int totalHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publishdynamic);
        requestPermission();
        initView();
        getCurrentPosition();
        setGridView();

    }

    private void initView() {
        Button btn_push = findViewById(R.id.btn_publishDynamic_push);
        LinearLayout ll_map = findViewById(R.id.ll_publishDynamic_map);
        ImageButton ib_back = findViewById(R.id.ib_publishDynamic_back);


        mEt_editor = findViewById(R.id.et_publishDynamic_editor);
        mGridView = findViewById(R.id.gv_publishDynamic_imageList);
        mSwitchButton = findViewById(R.id.switchButton_publishDynamic);
        mTv_address = findViewById(R.id.tv_publishDynamic_address);

        mSwitchButton.setChecked(true);
        mSwitchButton.toggle(false);//switch without animation
        mSwitchButton.setShadowEffect(true);//disable shadow effect
        mSwitchButton.setEnableEffect(true);//disable the switch animation
        mSwitchButton.setOnCheckedChangeListener(new SwitchButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SwitchButton view, boolean isChecked) {

                if (isChecked) {
                    /**
                     * 检测定位功能是否开启
                     * 调用获取位置信息
                     * */
                    mTv_address.setText(mCurrentPosition);
                    mTv_address.setTextColor(Color.BLUE);
                } else {
                    mTv_address.setText("所在位置");
                    mTv_address.setTextColor(Color.GRAY);
                }
            }
        });


        btn_push.setOnClickListener(this);
        ib_back.setOnClickListener(this);
        ll_map.setOnClickListener(this);

    }


    private void setGridView() {
        /**
         * 理一下思路
         * gridview默认有一个添加的图片，并随着数据不断后移
         *
         * 每次添加图片时清空list，添加之后将添加图片加入
         */
        mAdapter = new PublishImageAdapter(this, mList,mGridView);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position < mList.size() - 1) {
                    //mList.size-1 是添加图片所在的位置
                    Log.i("小半", "position<mList.size()");
                } else {
                    //调用相机或者图库
                    hintKeyboard();//关闭软键盘
                    addImageClick();
                }
            }
        });

//        mAdapter.setGridViewHeight(mGridView);
//        mAdapter.notifyDataSetChanged();

        // setViewWidthByHeight(mGridView);
        //
        //setViewWidthByHeight(mGridView);
    }


    private void hintKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ib_publishDynamic_back:
                break;
            case R.id.btn_publishDynamic_push:
                break;
            case R.id.ll_publishDynamic_map:
                startActivity(new Intent(this, BaiDuMapActivity.class));
                break;
            default:
                break;
        }
    }


    /**
     * 获取当前位置
     */
    public void getCurrentPosition() {

        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取位置描述信息相关的结果
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            mCurrentPosition = location.getLocationDescribe();
        }
    }

    /**
     * 定位权限请求
     * 读取文件权限
     * 调用相机权限
     */
    private void requestPermission() {
        String[] permissions = {Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ArrayList<String> permissionList = new ArrayList<>();

        //for (String permission : permissions) {
        // 检查该权限是否已经获取
        //int i = PermissionChecker.checkPermission(this, permissions[0], Process.myPid(), Process.myUid(), getPackageName());

        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permissions[i]);
            }
        }
        if (permissionList.isEmpty()) {//未授予的权限为空，表示都授予了
            Toast.makeText(this, "已经授权", Toast.LENGTH_LONG).show();
        } else {//请求权限方法
            String[] permission = permissionList.toArray(new String[permissionList.size()]);//将List转为数组
            ActivityCompat.requestPermissions(this, permission, PermissionCode);
        }


    }


    private void setViewWidthByHeight(View view) {
        final View mv = view;
        final ViewTreeObserver vto = mv.getViewTreeObserver();
        final int listSize = mList.size();
        /**
         * 当执行onCreate的时候，View 也许还没执行完 measure 阶段，getWidth获取的结果为0
         * getMeasuredWidth()  是自身请求的
         * getWidth()   是父类绘制的
         */
        /**
         * 画这个view之前调用
         *
         * 疑问：画view的时候是先获取用户给的宽、高还是先计算可用的宽高？
         *    利用先计算父类分配的大小后获取用户给的大小的间歇
         *    获取分配的宽在赋给高
         *
         * 这个使用方法需要注意其版本变化，具体什么版本暂时不清楚
         */

        final ViewTreeObserver.OnPreDrawListener preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {

                int width = mv.getMeasuredWidth();
                int height = mv.getMeasuredHeight();

                Log.i("小半", "width" + width);
                Log.i("小半", "height" + height);

                android.view.ViewGroup.LayoutParams lp = mv.getLayoutParams();

                //lp.height = mv.getMeasuredWidth();
                int i = (mv.getMeasuredWidth() - 20 - 10) / 3;
                if (listSize < 3) {
                    lp.height = i + 20 + 20;
                } else if (listSize < 6) {
                    lp.height = 2 * i + 20 + 5;
                } else{
                    lp.height = 3 * i + 20 + 10;
                }

                mv.setLayoutParams(lp);


                final ViewTreeObserver vto1 = mv.getViewTreeObserver();

                //调用一次之后移除，不影响性能
                vto1.removeOnPreDrawListener(this);

                return true;
            }
        };

        vto.addOnPreDrawListener(preDrawListener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (requestCode == PermissionCode) {

            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    //判断是否勾选禁止后不再询问
                    boolean showRequestPermission = ActivityCompat.shouldShowRequestPermissionRationale(this, permissions[i]);
                    if (showRequestPermission) {
                        Toast.makeText(this, "权限未申请", Toast.LENGTH_SHORT).show();
                    }
                }
            }


        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    //添加图片点击
    private void addImageClick() {
        final BottomDialog bottomDialog = new BottomDialog(this);
        // .title(R.string.title_item)
        // .layout(BottomDialog.GRID)
        bottomDialog.orientation(BottomDialog.VERTICAL);
        bottomDialog.inflateMenu(R.menu.menu_publish);
        //源码131行，view消失，但他妈的调不到
        bottomDialog.itemClick(new OnItemClickListener() {
            @Override
            public void click(Item item) {
                switch (item.getId()) {
                    case R.id.item_take_camera:
                        //调用相机
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                        }
                        onBackPressed();
                        break;
                    case R.id.item_take_photo:
                        //照片
                        // 只查询jpeg和png的图片
                        Intent intent = new Intent(PublishDynamicActivity.this, PhotoShowActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("maxCount", 9 - mList.size());
                        intent.putExtra("Bundle", bundle);
                        startActivityForResult(intent, REQUEST_IMAGE_PHOTO);
                        break;
                }
            }
        });
        bottomDialog.show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mList.remove(mList.size()-1);
            mList.add(imageBitmap);

            Log.i("小半", "相机数据返回正常");
        } else if (requestCode == REQUEST_IMAGE_PHOTO && resultCode == RESULT_OK) {
            Bundle bundle = data.getBundleExtra("Bundle");
            ArrayList<String> selectImage = bundle.getStringArrayList("SelectImage");
            mList.remove(mList.size()-1);
            for (String imagePath : selectImage) {
                //此处未压缩
                Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                mList.add(bitmap);
            }
            Toast.makeText(this, "相册数据返回正常", Toast.LENGTH_SHORT).show();
        }
        mAdapter.setData(mList);
        //  mAdapter.setGridViewHeight(mGridView);
        //setViewWidthByHeight(mGridView);
        mAdapter.notifyDataSetChanged();
    }


}