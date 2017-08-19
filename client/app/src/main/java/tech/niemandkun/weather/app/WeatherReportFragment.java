package tech.niemandkun.weather.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import tech.niemandkun.weather.R;
import tech.niemandkun.weather.model.WeatherReport;
import tech.niemandkun.weather.mvp.FragmentView;
import tech.niemandkun.weather.utils.BindLayout;

@BindLayout(R.layout.fragment_weather_report)
public class WeatherReportFragment extends FragmentView<WeatherReportPresenter>
        implements WeatherReportPresenter.ReportView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.fragment_weather_report_swipe_refresh)
    SwipeRefreshLayout mSwipeRefresh;

    @BindView(R.id.fragment_weather_report_error)
    TextView mError;

    @BindView(R.id.fragment_weather_report_humidity_card)
    View mHumidityCard;

    @BindView(R.id.fragment_weather_report_humidity)
    TextView mHumidity;

    @BindView(R.id.fragment_weather_report_temperature_card)
    View mTemperatureCard;

    @BindView(R.id.fragment_weather_report_temperature)
    TextView mTemperature;

    @BindString(R.string.error_fmt)
    String mErrorFmt;

    @BindString(R.string.temperature_fmt)
    String mTemperatureFmt;

    @BindString(R.string.humidity_fmt)
    String mHumidityFmt;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getPresenter().startLoadingReport();
    }

    @Override
    public WeatherReportPresenter createPresenter() {
        return new WeatherReportPresenter(getContext(), getLoaderManager());
    }

    @Override
    public void showReport(@NonNull WeatherReport report) {
        mError.setVisibility(View.GONE);
        mTemperature.setText(String.format(mTemperatureFmt, report.getTemperature()));
        mTemperatureCard.setVisibility(View.VISIBLE);
        mHumidity.setText(String.format(mHumidityFmt, report.getHumidity()));
        mHumidityCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(@NonNull String text) {
        mHumidityCard.setVisibility(View.GONE);
        mTemperatureCard.setVisibility(View.GONE);
        mError.setVisibility(View.VISIBLE);
        mError.setText(String.format(mErrorFmt, text));
    }

    @Override
    public void showProgress(boolean isLoading) {
        mSwipeRefresh.setRefreshing(isLoading);
    }
}
