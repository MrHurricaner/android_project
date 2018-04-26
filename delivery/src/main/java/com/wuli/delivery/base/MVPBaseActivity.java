package com.wuli.delivery.base;

import android.os.Bundle;

import com.app.activity.CoreFragmentActivity;

/**
 * Created by ziv on 2017/12/25.
 */

public abstract class MVPBaseActivity<V, T extends BasePresenter> extends CoreFragmentActivity {

    /**
     * presenter对象
     */
    private T mPresenter;

    protected abstract T createPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPresenter = createPresenter();
        mPresenter.attachView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
