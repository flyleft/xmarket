package me.jcala.xmarket.mvp.message;

import android.os.Bundle;
import android.view.View;

import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseFragment;

public class MessageFragment extends BaseFragment implements MessageView {
    @Override
    protected int getLayoutResId() {
        return R.layout.message_fragment;
    }


    @Override
    protected void initViews(View view, Bundle savedInstanceState) {

    }
}
