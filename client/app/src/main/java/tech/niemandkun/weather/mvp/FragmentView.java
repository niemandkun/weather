package tech.niemandkun.weather.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public abstract class FragmentView<TPresenter extends Presenter> extends ButterKnifeFragment {
    private TPresenter mPresenter;

    protected TPresenter getPresenter() {
        return mPresenter;
    }

    public abstract TPresenter createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter == null) {
            mPresenter = createPresenter();
        }
        mPresenter.attachView(this, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenter.onSaveInstanceState(outState);
    }
}
