package com.example.effe_21ca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.jaeger.library.StatusBarUtil;


public class Splash_Screen_Activity extends AppCompatActivity {

    private static final String TAG = "s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference notificationDatabase = database.getReference("user_notifications");
//                        String key = notificationDatabase.push().getKey();
//                        String userSessionId = "peter123";
//                        if(userSessionId.equals("NULL")){
//                            return;
//                        }

//                        {
//                            "message" :{
//                            "notification" : {
//                                "title" : "Effe",
//                                        "body" : "Checking push notification"
//                            },
//                            "topic" : "general"
//                        }
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d(TAG, msg);
                        Toast.makeText(Splash_Screen_Activity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

        // getSupportActionBar().hide();

        setContentView(R.layout.activity_splash_screen);


         StatusBarUtil.setTransparent(this);
        Handler handler = new Handler();
        Runnable runnable = () -> {
            Intent intent = new Intent(Splash_Screen_Activity.this, MainActivity1.class);
            startActivity(intent);
        };
        handler.postDelayed(runnable, 500);

    }
}