package com.example.effe_21ca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.effe_21ca.Adaptors.TaskAdaptor;
import com.example.effe_21ca.databinding.ActivityNotificationBinding;
import com.example.effe_21ca.models.TASKS;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {
    ActivityNotificationBinding binding;
    ArrayList<TASKS> list=new ArrayList<>();
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private TaskAdaptor adapter;
    ArrayList<String> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotificationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference().child("TASKS");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("d",snapshot.getKey());
//
//                for(DataSnapshot data : snapshot.getChildren()){
//                    TASKS tasks1 = new TASKS(data.child("title").getValue(String.class),data.child("link").getValue(String.class),data.child("taskId").getValue(String.class),data.child("points").getValue(Integer.class));
//                    if(!arrayList.contains(tasks1.getTaskId())) {
//                        list.add(tasks1);
//                    }
//                }
//
////                for(TASKS tasks : list){
////                    Log.d("key",tasks.getTaskId());
////                }
//
//                adapter=new TaskAdaptor(list , NotificationActivity.this);
//                binding.orderRecyclerView.setAdapter(adapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(NotificationActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
//            }
//        });
//        FirebaseMessaging.getInstance().subscribeToTopic("TopicName");
//        FirebaseMessaging.getInstance().unsubscribeFromTopic("TopicName");

    }
}