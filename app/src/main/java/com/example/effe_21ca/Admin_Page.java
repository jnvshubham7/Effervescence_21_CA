package com.example.effe_21ca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.effe_21ca.databinding.ActivityAdminPageBinding;
import com.example.effe_21ca.models.TASKS;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class Admin_Page extends AppCompatActivity {
    private static final String TAG = "s";
    ActivityAdminPageBinding binding;
    FirebaseDatabase database;
    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding =ActivityAdminPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("358743908709-bm0lkn9hjv1ueogqk2ggpjlj5ribgua9.apps.googleusercontent.com")
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(Admin_Page.this, gso);
//        String TaskTitle=binding.Title.getText().toString();
//        String TaskLink=binding.TaskLink.getText().toString();
//        int Taskpoint=Integer.parseInt(binding.Points.getText().toString());


binding.AdminButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseReference tasksRef = FirebaseDatabase.getInstance().getReference().child("TASKS").push();

        String TaskId=tasksRef.getKey();
        String TaskTitle=binding.Title.getText().toString();
        String TaskLink=binding.TaskLink.getText().toString();
        int Taskpoint=Integer.parseInt(binding.Points.getText().toString());
        binding.TaskLink.setText("");
        binding.Title.setText("");
        binding.Points.setText("");



        TASKS Task = new TASKS(TaskTitle,TaskLink,TaskId,Taskpoint);


        database.getReference().child("TASKS").child(TaskId).setValue(Task).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Map map = new HashMap();
                map.put("timestamp", ServerValue.TIMESTAMP);
                ref.child("TASKS").child(TaskId).updateChildren(map);
                Toast.makeText(Admin_Page.this, "Task Added", Toast.LENGTH_SHORT).show();
                try {
                    postData();
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });





    }
});
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
//                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                        DatabaseReference notificationDatabase = database.getReference("user_notifications");
//                        String key = notificationDatabase.push().getKey();
//                        String userSessionId = "peter123";
//                        if(userSessionId.equals("NULL")){
//                            return;
//                        }

//                        {
//                            "message" :{
//                            "notification" : {
//                                "title" : "Effe",
//                                        "body" : "Checking push notification"
//                            },
//                            "topic" : "general"
//                        }
                        String msg = "Successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }
                        Log.d(TAG, msg);
                   //     Toast.makeText(Admin_Page.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


binding.AdminBackButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

      //  auth.signOut();
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener( new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Intent intent=new Intent(Admin_Page.this,SignInUpActivity.class);
//                        startActivity(intent);
//
//                    }
//                });

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Admin_Page.this, Bottom_Navigation_Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
});


    }
//    // Post Request For JSONObject
//    public void postData() {
//
//
//        JSONObject object = new JSONObject();
//        try {
//            //input your API parameters
//            object.put("Title",binding.Title.getText().toString());
//            object.put("TitleLink",binding.TaskLink.getText().toString());
//            object.put("Points",binding.Points.getText().toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        // Enter the correct url for your api service site
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "https://limitless-shore-56363.herokuapp.com/push", object,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
////                        Toast.makeText(Login_screen.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();
//                        try {
//                            Log.d("JSON", String.valueOf(response));
//
//                            String Error = response.getString("httpStatus");
//                            if (Error.equals("")||Error.equals(null)){
//
//                            }else if(Error.equals("OK")){
//                                JSONObject body = response.getJSONObject("body");
//
//                            }else {
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//
//                        }
////                        resultTextView.setText("String Response : "+ response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                VolleyLog.d("Error", "Error: " + error.getMessage());
//                Toast.makeText(Admin_Page.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        requestQueue.add(jsonObjectRequest);
//    }


    // Post Request For JSONObject
    public void postData() throws JSONException {




       // JSONObject object = new JSONObject();

        JSONObject notification1 = new JSONObject();
     //   JSONObject message = new JSONObject();
        notification1.put("title", "message");
        notification1.put("body", "bodym");
        Log.d("notification", String.valueOf(notification1));
//        message.put("notification", notification1);
//        message.put("topic", "general");
//        object.put("message", message);




//        try {
//            //input your API parameters
//            object.put("Title", "String");
//            object.put("TitleLink","ssss");
//           // object.put("Points",binding.Points.getText().toString());
//          String string = "{\"message\" :{  \"notification\" : { \"title\" : \"Effe\", \"body\" : \"Checking push notification\" }, \"topic\" : \"general\" } }";
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "https://limitless-shore-56363.herokuapp.com/push", notification1,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(Login_screen.this,"String Response : "+ response.toString(),Toast.LENGTH_LONG).show();
                        try {
                            Log.d("JSON", String.valueOf(response));

                            String Error = response.getString("httpStatus");
                            if (Error.equals("")||Error.equals(null)){

                            }else if(Error.equals("OK")){
                                JSONObject body = response.getJSONObject("body");

                            }else {

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
//                        resultTextView.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Error", "Error: " + error.getMessage());
                Toast.makeText(Admin_Page.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsonObjectRequest);
    }


}