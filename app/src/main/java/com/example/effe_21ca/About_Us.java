package com.example.effe_21ca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class About_Us extends AppCompatActivity {
    ImageView backAroowC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        backAroowC=findViewById(R.id.backArrowUs);
        backAroowC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(About_Us.this,Bottom_Navigation_Activity.class);
                startActivity(intent);

            }
        });
    }
}