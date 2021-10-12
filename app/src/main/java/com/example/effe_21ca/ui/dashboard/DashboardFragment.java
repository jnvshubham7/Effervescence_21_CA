package com.example.effe_21ca.ui.dashboard;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.Adaptors.TaskAdaptor;
import com.example.effe_21ca.R;
import com.example.effe_21ca.databinding.FragmentDashboardBinding;
import com.example.effe_21ca.models.TASKS;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class DashboardFragment extends Fragment {


    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;
    FirebaseStorage storage;
    FirebaseAuth auth;
    FirebaseDatabase database;
    private TaskAdaptor adapter;
   // ProgressDialog dialog;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        dialog = new ProgressDialog(getActivity());
//        dialog.setMessage("Uploading Task");
//
//        dialog.setCancelable(false);


        storage=FirebaseStorage.getInstance();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.dasaboardRecycleview.setLayoutManager(layoutManager);
        binding.dasaboardRecycleview.setItemAnimator(null);


        FirebaseRecyclerOptions<TASKS> options =
                new FirebaseRecyclerOptions.Builder<TASKS>()

                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TASKS"), TASKS.class)

                        .build();
       // dialog.dismiss();
        FirebaseDatabase.getInstance().getReference().child("TASKS").getKey();


        adapter=new TaskAdaptor(options, getActivity());
        binding.dasaboardRecycleview.setAdapter(adapter);







        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }

}