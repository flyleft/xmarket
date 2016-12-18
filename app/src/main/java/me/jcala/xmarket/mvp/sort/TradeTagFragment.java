package me.jcala.xmarket.mvp.sort;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;
import com.github.johnpersano.supertoasts.library.utils.PaletteUtils;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import me.jcala.xmarket.R;
import me.jcala.xmarket.di.components.DaggerSortTagComponent;
import me.jcala.xmarket.di.modules.SortTagModule;
import me.jcala.xmarket.mvp.a_base.BaseFragment;

public class TradeTagFragment extends BaseFragment implements TradeTagView {
    @BindView(R.id.sort_grid)
    GridView gridView;
    @Inject
    TradeTagPresenter presenter;
    private Unbinder unbinder;
    private Realm realm;
    @Override
    protected int getLayoutResId() {
        return R.layout.sort_fragment;
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder=ButterKnife.bind(this,view);
        realm=Realm.getDefaultInstance();
        DaggerSortTagComponent
                .builder()
                .sortTagModule(new SortTagModule(getActivity(),this))
                .build()
                .inject(this);
        presenter.initView(realm);
    }
    @Override
    public void whenSuccess(BaseAdapter adapter,AdapterView.OnItemClickListener listener) {
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(listener);
    }
    @Override
    public void whenFail(String msg) {
        new SuperToast(getActivity())
                .setText(msg)
                .setDuration(Style.DURATION_LONG)
                .setColor(PaletteUtils.getTransparentColor(PaletteUtils.MATERIAL_RED))
                .setAnimations(Style.ANIMATIONS_POP)
                .show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        realm.close();
    }
}
