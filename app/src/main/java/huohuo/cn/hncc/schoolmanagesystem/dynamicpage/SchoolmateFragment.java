package huohuo.cn.hncc.schoolmanagesystem.dynamicpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import huohuo.cn.hncc.guidepage.R;

/**
 * Created by Windows on 2018/5/20.
 *
 */

public class SchoolmateFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamicschoolmate, null);
        return view;
    }
}
