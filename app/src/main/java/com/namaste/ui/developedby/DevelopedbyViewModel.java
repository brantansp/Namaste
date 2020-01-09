package com.namaste.ui.developedby;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DevelopedbyViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DevelopedbyViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Developed By information page");
    }

    public LiveData<String> getText() {
        return mText;
    }
}