package huohuo.cn.hncc.schoolmanagesystem.dynamicpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/19.
 */

public class DynamicMainFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater.inflate(R.layout.fragment_dynamicmain,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
