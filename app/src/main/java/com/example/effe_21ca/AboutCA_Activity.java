package com.example.effe_21ca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutCA_Activity extends AppCompatActivity {
    ImageView backAroow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_ca);

        backAroow=findViewById(R.id.backArrowCA);
        backAroow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AboutCA_Activity.this,Bottom_Navigation_Activity.class);
                startActivity(intent);

            }
        });
    }
}