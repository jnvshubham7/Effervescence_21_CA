package com.example.effe_21ca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.effe_21ca.Adaptors.TaskNotiAdaptor;
import com.example.effe_21ca.databinding.ActivityNotificationBinding;
import com.example.effe_21ca.models.TASKS;
import com.example.effe_21ca.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class NotificationActivity extends AppCompatActivity {
    ActivityNotificationBinding binding;
    ArrayList<TASKS> list=new ArrayList<>();
    FirebaseDatabase database;
    FirebaseAuth auth;
    DatabaseReference databaseReference;
    private TaskNotiAdaptor adapter;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        database = FirebaseDatabase.getInstance();
        auth= FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);

        adapter=new TaskNotiAdaptor(NotificationActivity.this, list);
        binding.orderRecyclerView.setAdapter(adapter);

        binding.orderRecyclerView.setLayoutManager(layoutManager);
        binding.orderRecyclerView.setItemAnimator(null);


        databaseReference = database.getReference().child("TASKS");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("d",snapshot.getKey());

                list.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    TASKS tasks1 = new TASKS(data.child("title").getValue(String.class),data.child("link").getValue(String.class),data.child("taskId").getValue(String.class),data.child("points").getValue(Integer.class),data.child("timestamp").getValue(Long.class));
                    if(!arrayList.contains(tasks1.getTaskId())) {
                        list.add(tasks1);
                    }



                }

                adapter.notifyDataSetChanged();
                Collections.sort(list, new Comparator<TASKS>(){
                    public int compare(TASKS obj1, TASKS obj2) {


                        return Long.valueOf(obj2.getTimestamp()).compareTo(Long.valueOf(obj1.getTimestamp())); // To compare integer values

                    }

                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NotificationActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });

        binding.backNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NotificationActivity.this, Bottom_Navigation_Activity.class);
                startActivity(intent);
            }
        });


    }




    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }
}