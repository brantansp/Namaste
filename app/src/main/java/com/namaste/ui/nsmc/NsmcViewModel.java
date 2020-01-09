package com.namaste.ui.nsmc;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NsmcViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NsmcViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is nsmc page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}