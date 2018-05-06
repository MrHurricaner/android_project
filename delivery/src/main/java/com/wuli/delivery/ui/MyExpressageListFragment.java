package com.wuli.delivery.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wuli.delivery.R;
import com.wuli.delivery.base.BasePagedFragment;
import com.wuli.delivery.portal.bean.dao.ExpressageDao;
import com.wuli.delivery.ui.adapters.MyExpressageListAdapter;
import com.wuli.delivery.view.RoundedTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ziv
 * @date 2018/4/26
 */

public class MyExpressageListFragment extends BasePagedFragment {

    @BindView(R.id.rtv_import_taobao_info)
    RoundedTextView rtvImportTaobaoInfo;
    @BindView(R.id.rtv_import_expressage_info)
    RoundedTextView rtvImportExpressageInfo;
    @BindView(R.id.rtv_import_short_message)
    RoundedTextView rtvImportShortMessage;
    @BindView(R.id.listview_expressage)
    ListView listviewExpressage;

    private Unbinder unbinder;


    public static MyExpressageListFragment newInstance() {
        MyExpressageListFragment myExpressageListFragment = new MyExpressageListFragment();
        return myExpressageListFragment;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected void onPageStart() {
        listviewExpressage.setAdapter(new MyExpressageListAdapter(ExpressageDao.getAllExpressageList()));
    }

    @Override
    protected View onCreatePage(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_expressage_list, container, false);
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
