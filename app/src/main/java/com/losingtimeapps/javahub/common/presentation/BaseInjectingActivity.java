package com.losingtimeapps.javahub.common.presentation;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
public abstract class BaseInjectingActivity<Component> extends BaseActivity {

    @Nullable
    private Component component;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        component = createComponent();
        onInject(component);

        super.onCreate(savedInstanceState);
    }

    @NonNull
    public Component getComponent() {
        return component;
    }

    protected abstract void onInject(@NonNull final Component component);

    @NonNull
    protected abstract Component createComponent();
}
