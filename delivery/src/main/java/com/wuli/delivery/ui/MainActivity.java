package com.wuli.delivery.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.app.activity.CoreFragmentActivity;
import com.wuli.delivery.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @author ziv
 */
public class MainActivity extends CoreFragmentActivity {

    @BindView(R.id.iv_open_left_content)
    ImageView ivOpenLeftContent;
    @BindView(R.id.iv_chat)
    ImageView ivChat;
    @BindString(R.string.msg_not_support_chat)
    String msgNotSupportChat;
    @BindView(R.id.layout_drawer)
    DrawerLayout layoutDrawer;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_express_delivery)
    RadioButton rbExpressDelivery;
    @BindView(R.id.rb_wuli_circle)
    RadioButton rbWuliCircle;
    @BindView(R.id.rb_user_center)
    RadioButton rbUserCenter;

    private Unbinder unbinder;
    private DrawerLayout.SimpleDrawerListener mSimpleDrawerListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mSimpleDrawerListener = new MySimpleDrawerListener();
        layoutDrawer.addDrawerListener(mSimpleDrawerListener);
        layoutDrawer.setScrimColor(Color.TRANSPARENT);
        showTab(0);
    }

    @OnClick({R.id.iv_open_left_content, R.id.iv_chat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_open_left_content:
                if (layoutDrawer.isDrawerOpen(Gravity.LEFT)) {
                    layoutDrawer.closeDrawer(Gravity.LEFT, true);
                } else {
                    layoutDrawer.openDrawer(Gravity.LEFT, true);
                }
                break;
            case R.id.iv_chat:
                Toast.makeText(this, msgNotSupportChat, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    @OnCheckedChanged({R.id.rb_home, R.id.rb_express_delivery, R.id.rb_wuli_circle, R.id.rb_user_center})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_home:
                if (isChecked) {
                    rbExpressDelivery.setChecked(false);
                    rbWuliCircle.setChecked(false);
                    rbUserCenter.setChecked(false);
                }
                break;
            case R.id.rb_express_delivery:
                if (isChecked) {
                    rbHome.setChecked(false);
                    rbWuliCircle.setChecked(false);
                    rbUserCenter.setChecked(false);
                }
                break;
            case R.id.rb_wuli_circle:
                if (isChecked) {
                    rbHome.setChecked(false);
                    rbExpressDelivery.setChecked(false);
                    rbUserCenter.setChecked(false);
                }
                break;
            case R.id.rb_user_center:
                if (isChecked) {
                    rbHome.setChecked(false);
                    rbExpressDelivery.setChecked(false);
                    rbWuliCircle.setChecked(false);
                }
                break;
            default:
                break;
        }
    }

    private void showTab(int index) {
        switch (index) {
            case 0:
                rbHome.setChecked(true);
                break;
            case 1:
                rbExpressDelivery.setChecked(true);
                break;
            case 2:
                rbWuliCircle.setChecked(true);
                break;
            case 3:
                rbUserCenter.setChecked(true);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (layoutDrawer != null) {
            layoutDrawer.removeDrawerListener(mSimpleDrawerListener);
        }
    }

    static class MySimpleDrawerListener extends DrawerLayout.SimpleDrawerListener {
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }

        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            super.onDrawerStateChanged(newState);
        }
    }
}
