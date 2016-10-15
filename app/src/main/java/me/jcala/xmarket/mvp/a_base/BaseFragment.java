package me.jcala.xmarket.mvp.a_base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract  class BaseFragment extends Fragment {

    protected abstract int getLayoutResId();

    protected abstract void initViews(View view, Bundle savedInstanceState);


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container, false);
        initViews(view, savedInstanceState);
        return view;
    }

}
