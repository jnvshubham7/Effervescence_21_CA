package com.example.effe_21ca;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.messaging.FirebaseMessaging;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
//        FirebaseMessaging.getInstance().subscribeToTopic("TopicName");
//        FirebaseMessaging.getInstance().unsubscribeFromTopic("TopicName");

    }
}