package tech.niemandkun.weather.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public final class WeatherReport implements Parcelable {
    private final static int SCALE = 1000;

    public static final Creator<WeatherReport> CREATOR = new Creator<WeatherReport>() {
        @Override
        public WeatherReport createFromParcel(Parcel in) {
            return new WeatherReport(in);
        }

        @Override
        public WeatherReport[] newArray(int size) {
            return new WeatherReport[size];
        }
    };

    @SerializedName("temperature") private final int mTemperature;
    @SerializedName("humidity") private final int mHumidity;

    public WeatherReport(int temperature, int humidity) {
        mTemperature = temperature;
        mHumidity = humidity;
    }

    private WeatherReport(Parcel in) {
        mTemperature = in.readInt();
        mHumidity = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mTemperature);
        dest.writeInt(mHumidity);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public int getTemperature() {
        return mTemperature / SCALE;
    }

    public int getHumidity() {
        return mHumidity / SCALE;
    }

    @Override
    public String toString() {
        return "{" +
                "humidity=" + mHumidity + "," +
                "temperature=" + mTemperature +
                "}";
    }
}
