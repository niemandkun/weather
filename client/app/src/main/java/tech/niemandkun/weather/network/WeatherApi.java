package tech.niemandkun.weather.network;

import retrofit2.Call;
import retrofit2.http.GET;
import tech.niemandkun.weather.model.WeatherReport;

public interface WeatherApi {
    @GET("/weather/report") Call<WeatherReport> getReport();
}
