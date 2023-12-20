package com.example.smarthome.ui.mine;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MineSettingsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MineSettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}