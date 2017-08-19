package tech.niemandkun.weather.utils;

import android.support.annotation.LayoutRes;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindLayout {
    @LayoutRes int value();
}
