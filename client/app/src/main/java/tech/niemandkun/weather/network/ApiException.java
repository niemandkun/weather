package tech.niemandkun.weather.network;

public class ApiException extends Exception {
    private final int mCode;

    ApiException(int code, String message) {
        super(message);
        mCode = code;
    }

    public int getCode() {
        return mCode;
    }
}
