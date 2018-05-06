package com.wuli.delivery.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wuli.delivery.AppConstants;
import com.wuli.delivery.R;
import com.wuli.delivery.base.BasePagedFragment;
import com.wuli.delivery.portal.bean.dao.ExpressageDao;
import com.wuli.delivery.ui.adapters.MyReleaseExpressageListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ziv on 2018/4/25.
 */

public class MyReleaseExpressageListFragment extends BasePagedFragment {


    @BindView(R.id.listview_release_epressage)
    ListView listviewReleaseEpressage;

    Unbinder unbinder;

    public static MyReleaseExpressageListFragment newInstance() {
        MyReleaseExpressageListFragment myReleaseExpressageListFragment = new MyReleaseExpressageListFragment();
        return myReleaseExpressageListFragment;
    }


    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected void onPageStart() {
        listviewReleaseEpressage.setAdapter(new MyReleaseExpressageListAdapter(ExpressageDao.getExpressageListByLeadType(AppConstants.DB.EXPRESSAGE_LEAD_TYPE_RELEASE)));
    }

    @Override
    protected View onCreatePage(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_release_expressage_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
