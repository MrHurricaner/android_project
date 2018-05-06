package com.wuli.delivery.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuli.delivery.base.BasePagedFragment;

/**
 *
 * @author ziv
 * @date 2018/4/26
 */

public class MyReceiveExpressageListFragment extends BasePagedFragment {


    public static MyReceiveExpressageListFragment newInstance() {
        MyReceiveExpressageListFragment myReceiveExpressageListFragment = new MyReceiveExpressageListFragment();
        return myReceiveExpressageListFragment;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    protected void onPageStart() {

    }

    @Override
    protected View onCreatePage(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }
}
