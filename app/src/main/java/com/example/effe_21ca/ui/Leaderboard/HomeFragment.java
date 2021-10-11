package com.example.effe_21ca.ui.Leaderboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.effe_21ca.R;
import com.example.effe_21ca.Users_Adapter;
import com.example.effe_21ca.databinding.FragmentHomeBinding;
import com.example.effe_21ca.models.Users;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseStorage storage;
   // ArrayList<Users> Users;
   ArrayList<Users>list =new ArrayList<>();
    Users user;
    Users_Adapter Users_Adapter;
  // FragmentHomeBinding binding;





//    private Notification_Adapter notificationAdapter;
//    private List<Notification> notificationList;

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.list_item_leaderboard, container, false);
//
//        RecyclerView recyclerView = view.findViewById(R.id.leaderBoardRecycleView);
//        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        Users = new ArrayList<>();
//        Users_Adapter = new Users_Adapter(getContext(), Users);
//        recyclerView.setAdapter(Users_Adapter);
//
//        readLeaderboard();
//
//        return view;
//    }
//
//    private void readLeaderboard() {
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//        assert firebaseUser != null;
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
//
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Users.clear();
//                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    Users users = snapshot.getValue(Users.class);
//                    Users.add(users);
//                }
//
//                Collections.reverse(Users);
//               // Users.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }

    //private LeaderboardViewModel homeViewModel;
 FragmentHomeBinding binding;
    // Object Users_Adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {


    binding = FragmentHomeBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


        database = FirebaseDatabase.getInstance();


        Users_Adapter adaptor=new Users_Adapter(getContext(), list);
        binding.leaderBoardRecycleView.setAdapter(adaptor);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.leaderBoardRecycleView.setLayoutManager(layoutManager);

        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Users users=dataSnapshot.getValue(Users.class);
                    users.getUserId(dataSnapshot.getKey());
                    list.add(users);

                }
                adaptor.notifyDataSetChanged();
                Collections.sort(list, new Comparator<Users>(){
                        public int compare(Users obj1, Users obj2) {


                         return Integer.valueOf(obj2.getScore()).compareTo(Integer.valueOf(obj1.getScore())); // To compare integer values

                       }

                   });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    // this = your fragment
   // SharedPreferences preferences = this.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
//    SharedPreferences pref = this.getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
//
//
//    SharedPreferences.Editor editor = pref.edit();

//editor.putBoolean("key_name", true); // Storing boolean - true/false
//editor.putString("1", "userName"); // Storing string
////editor.putInt("key_name", "int value"); // Storing integer
////editor.putFloat("key_name", "float value"); // Storing float
////editor.putLong("key_name", "long value"); // Storing long
//
//editor.commit(); // commit changes


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}