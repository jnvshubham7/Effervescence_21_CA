package com.example.effe_21ca;

import static java.security.AccessController.getContext;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.effe_21ca.models.TASKS;
import com.example.effe_21ca.models.Users;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.effe_21ca.databinding.ActivityBottomNavigationBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class Bottom_Navigation_Activity extends AppCompatActivity {

private ActivityBottomNavigationBinding binding;

private DrawerLayout drawerLayout;
private NavigationView navigationView;
FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    Boolean uploaded=false;
    //View name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
        ImageView imgnoti;
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.award, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_bottom_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
         auth=FirebaseAuth.getInstance();
        storage=FirebaseStorage.getInstance();
        database=FirebaseDatabase.getInstance();
         imgnoti=findViewById(R.id.imgNotification);
        // name=findViewById(R.id.nameUser);
         imgnoti.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(Bottom_Navigation_Activity.this,NotificationActivity.class);
                 startActivity(intent);
             }
         });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id= item.getItemId();
        if(id==R.id.signout){
            auth.signOut();
            Intent intent=new Intent(Bottom_Navigation_Activity.this,SignInUpActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You click on ", Toast.LENGTH_SHORT).show();
        }
        else if(id==R.id.Contacts){
            Intent intent =new Intent(Bottom_Navigation_Activity.this,Contacts_Activity.class);
            startActivity(intent);
        }
        else if(id==R.id.AboutUs){
             Intent intent =new Intent(Bottom_Navigation_Activity.this,About_Us.class);
             startActivity(intent);
        }
        else if(id==R.id.AboutCA){
            Intent intent =new Intent(Bottom_Navigation_Activity.this,AboutCA_Activity.class);
            startActivity(intent);
        }

        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("massage", "s2");
         Toast.makeText(Bottom_Navigation_Activity.this, "image selected", Toast.LENGTH_SHORT).show();

        if(data.getData()!=null){




//            DatabaseReference reference2=FirebaseDatabase.getInstance().getReference().child("TASKS")
//                    .child(FirebaseDatabase.getInstance().getReference().getKey());
            Uri sFile=data.getData();

            final StorageReference reference=storage.getReference().child("profile picture")
                    .child(FirebaseAuth.getInstance().getUid()).child("image");

           // System.out.println("hello");
           // Log. d("myTag", "This is my message");

            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    DatabaseReference reference1=FirebaseDatabase.getInstance().getReference().child("Users")
                            .child(FirebaseAuth.getInstance().getUid());

                    reference1.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Users user =  snapshot.getValue(Users.class);
                            if(!uploaded) {
                                int ScoreN = user.getScore() + 50;
                                uploaded=false;

                                HashMap<String, Object> obj = new HashMap<>();
                                obj.put("score", ScoreN);
                                database.getReference().child("Users")
                                        .child(FirebaseAuth.getInstance().getUid())
                                        .updateChildren(obj);

                                Toast.makeText(Bottom_Navigation_Activity.this, "points added", Toast.LENGTH_SHORT).show();
                            }


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    Toast.makeText(Bottom_Navigation_Activity.this, "Image is Uploaded", Toast.LENGTH_SHORT).show();
                }
            });


//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child("userName");
//            reference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    if (getContext() == null){
//                        return;
//                    }
//                    Users user = dataSnapshot.getValue(Users.class);
//
//                    assert user != null;
//
//                    // userName.setText(user.getUsername());
//                    name.setText(user.getUserName());
//                    // bio.setText(user.getBio());
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            });

             }
    }

}