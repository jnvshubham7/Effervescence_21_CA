package com.example.effe_21ca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    SwitchCompat switchCompat;
    FirebaseAuth auth;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();

        switchCompat = findViewById(R.id.switchBtn);

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));


            }
        });

        if(auth.getCurrentUser()!=null){
            Intent intent=new Intent(MainActivity.this,Bottom_Navigation_Activity.class);
            startActivity(intent);
        }
    }


}