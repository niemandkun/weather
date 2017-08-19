package tech.niemandkun.weather.loaders;

import android.support.annotation.NonNull;
import tech.niemandkun.weather.model.WeatherReport;

public class WeatherReportLoaderResult {
    static WeatherReportLoaderResult with(@NonNull WeatherReport report) {
        return new WeatherReportLoaderResult(report, null);
    }

    static WeatherReportLoaderResult with(@NonNull Exception exception) {
        return new WeatherReportLoaderResult(null, exception);
    }

    private final WeatherReport mWeatherReport;
    private final Exception mException;

    private WeatherReportLoaderResult(WeatherReport weatherReport, Exception exception) {
        mWeatherReport = weatherReport;
        mException = exception;
    }

    public boolean isSuccessful() {
        return mException == null;
    }

    public WeatherReport getWeatherReport() {
        return mWeatherReport;
    }

    public Exception getException() {
        return mException;
    }
}
