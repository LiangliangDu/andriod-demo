package com.shanlin.creadit.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shanlin.creadit.R;

/**
 * Description:
 * User:         沈亮
 * Date:         2016-05-10  13{MINUTE}
 */
public class MoreFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static MoreFragment newInstance() {
        Bundle args = new Bundle();
        MoreFragment fragment = new MoreFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
