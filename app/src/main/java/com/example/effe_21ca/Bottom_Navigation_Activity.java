package com.example.effe_21ca;

import static java.security.AccessController.getContext;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.effe_21ca.models.TASKS;
import com.example.effe_21ca.models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.example.effe_21ca.databinding.ActivityBottomNavigationBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Bottom_Navigation_Activity extends AppCompatActivity {

private ActivityBottomNavigationBinding binding;

private DrawerLayout drawerLayout;
private NavigationView navigationView;
FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    GoogleSignInClient mGoogleSignInClient;
    Boolean uploaded=false;
    ProgressDialog dialog;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    Button TaskAdd;
    String text;


    ArrayList<String> arrayList;

    //View name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityBottomNavigationBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());
        dialog = new ProgressDialog(this);
        dialog.setMessage("Uploading Image...");





        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("358743908709-bm0lkn9hjv1ueogqk2ggpjlj5ribgua9.apps.googleusercontent.com")
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(Bottom_Navigation_Activity.this, gso);


        ImageView imgnoti;
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);

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

         imgnoti.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(Bottom_Navigation_Activity.this,NotificationActivity.class);
                 startActivity(intent);
             }
         });

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);


        String s1 = sh.getString("name", "");

        View b = findViewById(R.id.TaskAdd);
        b.setVisibility(View.INVISIBLE);

        SharedPreferences sh1 = getSharedPreferences("MySharedPref1", MODE_PRIVATE);


        String s2 = sh1.getString("name", "");




        if (s1.equals("effervescence@iiita.ac.in")) {
         binding.TaskAdd.setVisibility(View.VISIBLE);
        }

        if (!s2.equals("effervescence@iiita.ac.in")) {
            binding.TaskAdd.setVisibility(View.GONE);
        }
        Log.d("s1", s1);
        Log.d("s2", s2);



        binding.TaskAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent intent = new Intent(Bottom_Navigation_Activity.this, Admin_Page.class);
                    startActivity(intent);


            }
        });


    }





    int doubleBackToExitPressed = 1;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressed == 2) {
            finishAffinity();
            System.exit(0);
        }
        else {
            doubleBackToExitPressed++;
            Toast.makeText(this, "Please press Back again to exit", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressed=1;
            }
        }, 2000);
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
            mGoogleSignInClient.signOut()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                           Intent intent=new Intent(Bottom_Navigation_Activity.this,SignInUpActivity.class);
                           startActivity(intent);

                           finish();

                        }
                    });


            finish();

//

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

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("tasks", null);

        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        arrayList = gson.fromJson(json, type);
        if (arrayList == null) {
            arrayList = new ArrayList<>();
        }
    }

    public void saveData(String id){

        loadData();

        arrayList.add(id);

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(arrayList);
        editor.putString("tasks", json);
        editor.apply();
    }

    private void sendMessage(int pos) {
        Log.d("sender", "Broadcasting message");
        Intent intent = new Intent("custom-event-name");
        intent.putExtra("message", "delete");
        intent.putExtra("position",pos);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("massage", "s2");
         Toast.makeText(Bottom_Navigation_Activity.this, "Image Selected!", Toast.LENGTH_SHORT).show();

        if(data.getData()!=null && requestCode==33){

            String id = getIntent().getStringExtra("id");
            int pos = getIntent().getIntExtra("position",0);
            int points = getIntent().getIntExtra("score",50);

            Log.d("received", String.valueOf(pos));

            saveData(id);

            dialog.show();
            Uri sFile=data.getData();

            final StorageReference reference=storage.getReference().child("profile picture")
                    .child(FirebaseAuth.getInstance().getUid()).child("image");

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
                                int ScoreN = user.getScore() + points;
                                uploaded=false;

                                HashMap<String, Object> obj = new HashMap<>();
                                obj.put("score", ScoreN);
                                database.getReference().child("Users")
                                        .child(FirebaseAuth.getInstance().getUid())
                                        .updateChildren(obj);

                                sendMessage(pos);

                                dialog.dismiss();

                                Toast.makeText(Bottom_Navigation_Activity.this, "Points Added!", Toast.LENGTH_SHORT).show();
                            }


                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    Toast.makeText(Bottom_Navigation_Activity.this, "Image is Uploaded", Toast.LENGTH_SHORT).show();
                }
            });





             }
    }

}