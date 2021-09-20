package com.example.effe_21ca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.effe_21ca.databinding.ActivitySignUpBinding;
import com.example.effe_21ca.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth Auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();
        Auth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
         progressDialog=new ProgressDialog(SignUpActivity.this);
         progressDialog.setTitle("Creating Account");
         progressDialog.setMessage("We are creating your account");
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                Auth.createUserWithEmailAndPassword(binding.EmailAddress.getText().toString(),binding.TxPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                         if(task.isSuccessful()){

                             Users user=new Users(binding.PersonName.getText().toString(),binding.EmailAddress.getText().toString(),binding.TxPassword.getText().toString());

                             String id=task.getResult().getUser().getUid();
                             database.getReference().child("Users").child(id).setValue(user);
                             Toast.makeText(SignUpActivity.this, "SignUp Succesfully", Toast.LENGTH_SHORT).show();
                         }
                         else{
                             Toast.makeText(SignUpActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         }
                    }
                });
            }
        });
        binding.tvSignUpToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}