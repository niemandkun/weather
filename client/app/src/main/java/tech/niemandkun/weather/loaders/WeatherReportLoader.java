package tech.niemandkun.weather.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import tech.niemandkun.weather.model.WeatherReport;
import tech.niemandkun.weather.network.ApiException;
import tech.niemandkun.weather.network.WeatherClient;

public class WeatherReportLoader extends AsyncTaskLoader<WeatherReportLoaderResult> {
    private final static String TAG = WeatherReportLoader.class.getSimpleName();

    public final static int ID = 42;

    public WeatherReportLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public WeatherReportLoaderResult loadInBackground() {
        WeatherClient client = WeatherClient.getInstance();
        try {
            WeatherReport report = client.getReport();
            if (report == null) {
                Log.e(TAG, "Report body was null");
                return WeatherReportLoaderResult.with(new Exception());
            }
            Log.i(TAG, "Report loaded: " + report.toString());
            return WeatherReportLoaderResult.with(report);
        } catch (ApiException e) {
            Log.e(TAG, "ApiException was thrown while retrieving result: " + e.getCode() + " - " + e.getMessage());
            Log.e(TAG, e.toString());
            return WeatherReportLoaderResult.with(e);
        } catch (Exception e) {
            Log.e(TAG, "Exception was thrown while retrieving result: " + e.getMessage());
            Log.e(TAG, e.toString());
            return WeatherReportLoaderResult.with(e);
        }
    }
}
