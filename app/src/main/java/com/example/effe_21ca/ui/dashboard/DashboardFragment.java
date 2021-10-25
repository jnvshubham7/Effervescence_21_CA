package com.example.effe_21ca.ui.dashboard;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.effe_21ca.Adaptors.TaskAdaptor;
import com.example.effe_21ca.databinding.FragmentDashboardBinding;
import com.example.effe_21ca.models.TASKS;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DashboardFragment extends Fragment {


 //   private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private TaskAdaptor adapter;

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<TASKS> list=new ArrayList<>();



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        storage=FirebaseStorage.getInstance();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.dasaboardRecycleview.setLayoutManager(layoutManager);
        binding.dasaboardRecycleview.setItemAnimator(null);

        loadData();

        databaseReference = database.getReference().child("TASKS");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("d",snapshot.getKey());

                for(DataSnapshot data : snapshot.getChildren()){
                    TASKS tasks1 = new TASKS(data.child("title").getValue(String.class),data.child("link").getValue(String.class),data.child("taskId").getValue(String.class),data.child("points").getValue(Integer.class));
                    if(!arrayList.contains(tasks1.getTaskId())) {
                        list.add(tasks1);
                    }
                }



                adapter=new TaskAdaptor(list, getActivity());
                binding.dasaboardRecycleview.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasks", null);

        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
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