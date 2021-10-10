package com.example.effe_21ca.ui.home;

  import android.os.Bundle;
  import android.util.Log;
  import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.effe_21ca.Adaptors.Users_Adapter;
import com.example.effe_21ca.databinding.FragmentHomeBinding;
import com.example.effe_21ca.models.Users;
  import com.google.firebase.auth.FirebaseAuth;
  import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
  import java.util.Collections;
  import java.util.Comparator;
  import java.util.HashMap;

public class HomeFragment extends Fragment {

    FirebaseDatabase database;
    FirebaseStorage storage;

    ArrayList<Users>list =new ArrayList<>();
    Users user;
    com.example.effe_21ca.Adaptors.Users_Adapter Users_Adapter;

    FragmentHomeBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        database = FirebaseDatabase.getInstance();


        Users_Adapter adaptor=new Users_Adapter(getContext(), list);
        binding.leaderBoardRecycleView.setAdapter(adaptor);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.leaderBoardRecycleView.setLayoutManager(layoutManager);


//        binding
//        HashMap<String,Object> obj=new HashMap<>();
//         obj.put("score",status);

//        database.getReference().child("Users")
//                .child(FirebaseAuth.getInstance().getUid())
//                .updateChildren(obj);
        //
//                            //Get user map
//                            Map singleUser = (Map) entry.getValue();
//                            //Get phone field and append to list
//                             score.add((Long) singleUser.get("score"));
//                        }
//
//                          Log.i("score",score.toString());
//
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError databaseError) {
//                        //handle databaseError
//                    }
//                });


        database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Users users=dataSnapshot.getValue(Users.class);
                    users.getUserId(dataSnapshot.getKey());

                    list.add(users);
//                    FirebaseDatabase.getInstance().getReference().child("Users")
//                            .child(FirebaseAuth.getInstance().getUid())
//                            .orderByChild("userName");
                    //

//                    System.out.println(user.getScore());
//

                  //  Log. d("myTag", "This is my message");
                   // list.add(users);


                }

                Collections.sort(list, new Comparator<Users>(){
                    public int compare(Users obj1, Users obj2) {
                       // Log.d("myTag", "This is my message");

                        return Integer.compare(obj1.getScore(), obj2.getScore()); // To compare integer values




                    }
                });

                adaptor.notifyDataSetChanged();


                // ## Ascending order
                //return obj1.getScore().compareToIgnoreCase(obj2.getScore()); // To compare string values
              //  int temp = 0;
//                            if (obj1.getScore() < obj2.getScore()) {
//



//                                // Swapping
//                                temp = obj1.getScore();
//                                obj1.getScore() = obj2.getScore();
//                                obj2.getScore() = temp;
                // }

                //  System.out.println(obj1.getScore());



                // ## Descending order
                // return obj2.firstName.compareToIgnoreCase(obj1.firstName); // To compare string values


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}