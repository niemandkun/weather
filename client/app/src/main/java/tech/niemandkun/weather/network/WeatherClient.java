package tech.niemandkun.weather.network;

import android.support.annotation.Nullable;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tech.niemandkun.weather.model.WeatherReport;

import java.io.IOException;

public class WeatherClient {
    private static final String WEATHER_API_URL = "http://.../";

    private static WeatherClient sInstance;

    public static WeatherClient getInstance() {
        if (sInstance == null) {
            sInstance = new WeatherClient();
        }
        return sInstance;
    }

    private final WeatherApi mWeatherApi;

    private WeatherClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mWeatherApi = retrofit.create(WeatherApi.class);
    }

    public @Nullable WeatherReport getReport() throws IOException, ApiException {
        Response<WeatherReport> response = mWeatherApi.getReport().execute();
        if (response.isSuccessful()) {
            return response.body();
        }
        throw new ApiException(response.code(), response.message());
    }
}
