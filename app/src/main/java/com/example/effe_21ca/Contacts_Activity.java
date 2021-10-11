package com.example.effe_21ca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Contacts_Activity extends AppCompatActivity {
    private static final int REQUEST_CALL=1;
     private ImageView Callimg1,Callimg2,Callimg3,Callimg4,Callimg5,Callimg6,Callimg7;
     ImageView backAroowC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        backAroowC=findViewById(R.id.backArrowContect);
        backAroowC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Contacts_Activity.this,Bottom_Navigation_Activity.class);
                startActivity(intent);

            }
        });



        Callimg1=findViewById(R.id.call1);
        Callimg2=findViewById(R.id.call2);
        Callimg3=findViewById(R.id.call3);
        Callimg4=findViewById(R.id.call4);
        Callimg5=findViewById(R.id.call5);
        Callimg6=findViewById(R.id.call6);
        Callimg7=findViewById(R.id.call7);
        Callimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton();
            }
        });
        Callimg2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton2();
            }
        });
        Callimg3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton3();
            }
        });
        Callimg4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton4();
            }
        });
        Callimg5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton5();
            }
        });
        Callimg6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton6();
            }
        });
        Callimg7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CallButton();
            }
        });


    }



    private void CallButton() {
        String number="6299360050";
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Contacts_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contacts_Activity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }
    private void CallButton2() {
        String number="8875679288";
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Contacts_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contacts_Activity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }
    private void CallButton3() {
        String number="9167026346";
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Contacts_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contacts_Activity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }
    private void CallButton4() {
        String number="9455117105";
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Contacts_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contacts_Activity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }
    private void CallButton5() {
        String number="9167026346";
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Contacts_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contacts_Activity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }
    private void CallButton6() {
        String number="9167026346";
        if(number.trim().length()>0){
            if(ContextCompat.checkSelfPermission(Contacts_Activity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(Contacts_Activity.this,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
            }
            else{
                String dial="tel:"+number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }
    public void onRequestPermissionsResult(int requestCode,@NonNull String permissions,@NonNull int[] grantResults){
        if(requestCode==REQUEST_CALL){
            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                CallButton();
            }
            else{
                Toast.makeText(Contacts_Activity.this, "Permission Deynied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}