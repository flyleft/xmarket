package me.jcala.xmarket.mvp.message;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.jcala.xmarket.R;
import me.jcala.xmarket.mvp.a_base.BaseFragment;
import me.jcala.xmarket.view.RecyclerCommonAdapter;

public class MessageFragment extends BaseFragment implements MessageView {

    @BindView(R.id.message_list)
    protected RecyclerView recyclerView;
    private Unbinder unbinder;
    protected MessagePresenter presenter;
    @Override
    protected int getLayoutResId() {
        return R.layout.message_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder= ButterKnife.bind(this,view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        presenter=new MessagePresenterImpl(getActivity(),this);
        presenter.gainMessages();
    }


    @Override
    public void whenNeedUpdateMsgList(RecyclerCommonAdapter<?> adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
