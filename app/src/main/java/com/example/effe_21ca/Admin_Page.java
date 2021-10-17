package com.example.effe_21ca;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;


public class Admin_Page extends AppCompatActivity {
    ActivityAdminPageBinding binding;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding =ActivityAdminPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
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



            }
        });





    }
});


binding.AdminBackButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Admin_Page.this, SignInUpActivity.class);
        startActivity(intent);
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
//    public void postData() {
//
//
//
//
//        JSONObject object = new JSONObject();
//        try {
//            //input your API parameters
//            object.put("Title",binding.Title.getText().toString());
//            object.put("TitleLink",binding.TaskLink.getText().toString());
//           // object.put("Points",binding.Points.getText().toString());
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


}