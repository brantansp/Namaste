package com.namaste.ui.ccrs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CcrsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CcrsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is ccrs page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}