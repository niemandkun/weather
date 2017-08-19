package tech.niemandkun.weather.mvp;

import android.os.Bundle;

import java.lang.ref.WeakReference;

public abstract class Presenter<TView> {
    private WeakReference<TView> mView = new WeakReference<>(null);

    public TView getView() {
        return mView.get();
    }

    void attachView(TView view, Bundle savedInstanceState) {
        mView = new WeakReference<>(view);
        onViewAttached(view, savedInstanceState);
    }

    public void onViewAttached(TView view, Bundle savedInstanceState) { }

    void detachView() {
        mView = new WeakReference<>(null);
        onViewDetached();
    }

    public void onViewDetached() { }

    public void onSaveInstanceState(Bundle outState) { }
}
