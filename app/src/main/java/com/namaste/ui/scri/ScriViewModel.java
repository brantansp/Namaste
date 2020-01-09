package com.namaste.ui.scri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScriViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ScriViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is scri page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}