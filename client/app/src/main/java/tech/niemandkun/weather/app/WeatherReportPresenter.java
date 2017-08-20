package tech.niemandkun.weather.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import tech.niemandkun.weather.loaders.WeatherReportLoader;
import tech.niemandkun.weather.loaders.WeatherReportLoaderResult;
import tech.niemandkun.weather.model.WeatherReport;
import tech.niemandkun.weather.mvp.Presenter;

public class WeatherReportPresenter extends Presenter<WeatherReportPresenter.ReportView>
        implements LoaderManager.LoaderCallbacks<WeatherReportLoaderResult> {

    private final static String STATE_REPORT = "STATE_REPORT";

    private final LoaderManager mLoaderManager;
    private final Context mContext;

    private WeatherReport mLastReport;

    WeatherReportPresenter(Context context, LoaderManager loaderManager) {
        mContext = context;
        mLoaderManager = loaderManager;
    }

    void startLoadingReport() {
        mLoaderManager.initLoader(WeatherReportLoader.ID, new Bundle(), this).startLoading();
        getView().showProgress(true);
    }

    @Override
    public void onViewAttached(ReportView reportView, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mLastReport = savedInstanceState.getParcelable(STATE_REPORT);
        } else {
            startLoadingReport();
        }
        if (mLastReport != null) {
            reportView.showReport(mLastReport);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(STATE_REPORT, mLastReport);
    }

    @Override
    public Loader<WeatherReportLoaderResult> onCreateLoader(int id, Bundle args) {
        return new WeatherReportLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<WeatherReportLoaderResult> loader, WeatherReportLoaderResult data) {
        getView().showProgress(false);
        if (data.isSuccessful()) {
            getView().showReport(mLastReport = data.getWeatherReport());
        } else {
            getView().showError(data.getException().getLocalizedMessage());
        }
    }

    @Override
    public void onLoaderReset(Loader<WeatherReportLoaderResult> loader) {
    }

    public interface ReportView {
        void showReport(@NonNull WeatherReport report);
        void showError(@NonNull String text);
        void showProgress(boolean isLoading);
    }
}
