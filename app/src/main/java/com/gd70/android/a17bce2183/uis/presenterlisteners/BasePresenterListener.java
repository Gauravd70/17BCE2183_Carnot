package com.gd70.android.a17bce2183.uis.presenterlisteners;

import android.app.Activity;
import android.content.Context;

public interface BasePresenterListener {
    Activity getActivity();
    Context getContext();
    void showToast(String toast);
}
