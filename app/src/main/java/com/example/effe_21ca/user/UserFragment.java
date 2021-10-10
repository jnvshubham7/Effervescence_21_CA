package com.example.effe_21ca.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.effe_21ca.databinding.FragmentNotificationsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.FirebaseDatabase;

public class UserFragment extends Fragment {
    //FirebaseDatabase database;

    private UserViewModel notificationsViewModel;
    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(UserViewModel.class);

//        database = FirebaseDatabase.getInstance();\
//        String name = getIntent().getStringExtra("name");

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//    binding.nameUser.setText((CharSequence) FirebaseDatabase.getInstance().getReference().child("Users")
//            .child(FirebaseAuth.getInstance().getUid()).
//                    child("userName"));

        //  binding.nameUser.setText(FirebaseAuth.getInstance().getUid());

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            String name = user.getDisplayName();
//            binding.nameUser.setText(name);
//        }
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            String displayName = user.getDisplayName();
          //  Uri profileUri = user.getPhotoUrl();

            // If the above were null, iterate the provider data
            // and set with the first non null data
            for (UserInfo userInfo : user.getProviderData()) {
                if (displayName == null && userInfo.getDisplayName() != null) {
                    displayName = userInfo.getDisplayName();
                }

                }


        binding.nameUser.setText(displayName);


        }

//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}