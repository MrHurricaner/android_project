package com.wuli.delivery.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wuli.delivery.R;
import com.wuli.delivery.base.BasePagedFragment;
import com.wuli.delivery.portal.ImageLoader;
import com.wuli.delivery.portal.event.Event;
import com.wuli.delivery.portal.event.EventPublisher;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author ziv
 * @date 2018/4/25
 */

public class WuliCircleFragment extends BasePagedFragment {


    @BindView(R.id.image)
    ImageView image;

    Unbinder unbinder;

    public static WuliCircleFragment newInstance() {
        WuliCircleFragment wuliCircleFragment = new WuliCircleFragment();
        return wuliCircleFragment;
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
        View rootView = inflater.inflate(R.layout.fragment_wuli_circle, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        EventPublisher.getInstance().register(this);
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onInsertDataSuccEvent(Event.InsertDataSuccEvent event) {
        String url = "http://img3.imgtn.bdimg.com/it/u=3326312922,67097319&fm=27&gp=0.jpg";
        ImageLoader.getInstance().loadBitmap(getActivity(),url, image);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
        EventPublisher.getInstance().unRegister(this);
    }
}
