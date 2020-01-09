package com.namaste.ui.features;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FeaturesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FeaturesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is features page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}