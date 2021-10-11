package com.example.effe_21ca.user;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.effe_21ca.databinding.FragmentNotificationsBinding;
import com.example.effe_21ca.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

public class UserFragment extends Fragment {
    FirebaseDatabase database;

    private UserViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;
    //void userInfo();
    String userId;
    FirebaseUser firebaseUser;
   // String profileid;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

//        database = FirebaseDatabase.getInstance();\
//        String name = getIntent().getStringExtra("name");


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        SharedPreferences prefs = Objects.requireNonNull(getContext()).getSharedPreferences("PREFS", MODE_PRIVATE);
//        profileid = prefs.getString("profileid", "none");



        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        binding.date.setText(currentDate);

//    binding.nameUser.setText((CharSequence) FirebaseDatabase.getInstance().getReference().child("Users")
//            .child(FirebaseAuth.getInstance().getUid()).
//                    child("userName"));

        //  binding.nameUser.setText(FirebaseAuth.getInstance().getUid());

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            String name = user.getDisplayName();
//            binding.nameUser.setText(name);
//        }
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            // User is signed in
//            String displayName = user.getDisplayName();
//            //  Uri profileUri = user.getPhotoUrl();
//
//            // If the above were null, iterate the provider data
//            // and set with the first non null data
//            for (UserInfo userInfo : user.getProviderData()) {
//                if (displayName == null && userInfo.getDisplayName() != null) {
//                    displayName = userInfo.getDisplayName();
//                }
//
//            }
//
//
//            binding.nameUser.setText(displayName);
//
//
//        }

//        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//              //  list.clear();
//                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
//                    Users users=dataSnapshot.getValue(Users.class);
//                    users.getUserId(dataSnapshot.getKey());
//                   // list.add(users);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (getContext() == null){
                        return;
                    }
                    Users user = dataSnapshot.getValue(Users.class);

                   assert user != null;

                   // userName.setText(user.getUsername());
                    binding.nameUser.setText(user.getUserName());
                 binding.score.setText(String.valueOf(user.getScore()));

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }
 //   SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}