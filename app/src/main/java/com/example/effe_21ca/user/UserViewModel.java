package com.example.effe_21ca.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.FirebaseDatabase;

public class UserViewModel extends ViewModel {
    FirebaseDatabase database;

    private MutableLiveData<String> mText;

    public UserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}