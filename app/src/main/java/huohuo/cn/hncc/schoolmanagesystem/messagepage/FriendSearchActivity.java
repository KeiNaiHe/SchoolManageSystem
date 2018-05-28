package huohuo.cn.hncc.schoolmanagesystem.messagepage;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import huohuo.cn.hncc.guidepage.R;
import huohuo.cn.hncc.schoolmanagesystem.database.DBUtil;
import huohuo.cn.hncc.schoolmanagesystem.database.ManagerDatabase;

/**
 * Created by Windows on 2018/5/27.
 */

public class FriendSearchActivity extends AppCompatActivity {

    private MaterialSearchView mSearchVeiw;
    private List<String> mList_record;
    private SQLiteDatabase mWritableDatabase;
    private SQLiteDatabase mReadableDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendsearch);

        initNativeData();
        initView();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initData();

    }

    private void initData() {

        mSearchVeiw.setVoiceSearch(false);
        mSearchVeiw.setCursorDrawable(R.drawable.custom_cursor);
        mSearchVeiw.setEllipsize(true);

        mSearchVeiw.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        mSearchVeiw.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Snackbar.make(findViewById(R.id.container), "Query: " + query, Snackbar.LENGTH_LONG)
                        .show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic
                return false;
            }
        });
        mSearchVeiw.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
            }
        });
        //下面这个填充的影视list集合的数据
     //   mSearchVeiw.setAdapter();
    }

    private void initView() {
        mSearchVeiw = (MaterialSearchView) findViewById(R.id.searchView);

    }

    private void initNativeData() {
        //获取本地缓存的搜索记录
        mList_record = new ArrayList<>();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        mSearchVeiw.setMenuItem(item);

        return true;
    }
    @Override
    public void onBackPressed() {
        if (mSearchVeiw.isSearchOpen()) {
            mSearchVeiw.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (matches != null && matches.size() > 0) {
                String searchWrd = matches.get(0);
                if (!TextUtils.isEmpty(searchWrd)) {
                    mSearchVeiw.setQuery(searchWrd, false);
                }
            }

            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 插入数据
     */
    private void insertData(String tempName) {
        mWritableDatabase = ManagerDatabase.obtain(this).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("search_record",tempName);
        contentValues.put("search_time",System.currentTimeMillis());
        mWritableDatabase.insert(DBUtil.SEARCH_RECORD_TABLE,null,contentValues);
        mWritableDatabase.close();
    }

    /**
     * 模糊查询数据
     */
    private void queryData(String tempName) {
        mReadableDatabase = ManagerDatabase.obtain(this).getReadableDatabase();
        Cursor cursor = mReadableDatabase.rawQuery(
                "select id as _id,name from records where name like '%" + tempName + "%' order by id desc ", null);
        // 创建adapter适配器对象
//        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, new String[] { "name" },
//                new int[] { android.R.id.text1 }, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//        // 设置适配器
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
    }
    /**
     * 检查数据库中是否已经有该条记录
     */
  //  private boolean hasData(String tempName) {
//        Cursor cursor = helper.getReadableDatabase().rawQuery(
//                "select id as _id,name from records where name =?", new String[]{tempName});
//        //判断是否有下一个
//        return cursor.moveToNext();
  //  }

    /**
     * 清空数据
     */
    private void deleteData() {
//        db = helper.getWritableDatabase();
//        db.execSQL("delete from records");
//        db.close();
    }

}
