package tech.niemandkun.weather.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.*;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import tech.niemandkun.weather.utils.BindLayout;

public class ButterKnifeFragment extends Fragment {
    private Unbinder mUnbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        BindLayout bindingAnnotation = getClass().getAnnotation(BindLayout.class);
        if (bindingAnnotation != null) {
            Log.e("ButterKnifeFragment", "annotation is not null");
            return inflater.inflate(bindingAnnotation.value(), container, false);
        }
        Log.e("ButterKnifeFragment", "annotation is null");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
