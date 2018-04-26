package com.wuli.delivery.base;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by Villey on 2017/1/9.
 */
public abstract class BaseDialogFragment extends DialogFragment {

    @Override
    public void show(FragmentManager manager, String tag) {

        FragmentTransaction ft = manager.beginTransaction();
        ft.add(this, tag);
        ft.commit();
    }

    /**
     * 对应神策中$title
     *
     * @return
     */
    public abstract String getTitle();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}

