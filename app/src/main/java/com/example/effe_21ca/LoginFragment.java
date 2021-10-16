package com.example.effe_21ca;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.effe_21ca.databinding.FragmentLoginBinding;
import com.example.effe_21ca.databinding.FragmentSignUpBinding;
import com.example.effe_21ca.models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;


public class LoginFragment extends Fragment {


    FragmentLoginBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseDatabase database;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding= FragmentLoginBinding.inflate(inflater,container, false);

        auth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog( getContext());
        progressDialog.setTitle("login");
        progressDialog.setMessage("login to your account");


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("358743908709-bm0lkn9hjv1ueogqk2ggpjlj5ribgua9.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.EmailAddress.getText().toString(),binding.TxPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if(task.isSuccessful()){
                            if(binding.EmailAddress.getText().toString().equals("x@gmail.com"))
                            {
                                Intent intent=new Intent(getContext(),Admin_Page.class);
                                startActivity(intent);
                            }

                            else {

                                Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
                                startActivity(intent);
                            }

                        }
                        else{
                            Toast.makeText(getContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        binding.GoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        if(auth.getCurrentUser()!=null){


                Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
                startActivity(intent);

        }
        return binding.getRoot();
    }

    int RC_SIGN_IN=65;
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

//                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
//                            if (acct != null) {
//
//
//                                String GoogleName = acct.getDisplayName();
////                                String personGivenName = acct.getGivenName();
////                                String personFamilyName = acct.getFamilyName();
//                                String personEmail = acct.getEmail();
////                                String personId = acct.getId();
//                                // Uri personPhoto = acct.getPhotoUrl();
//                                Users user=new Users(GoogleName,personEmail);
//                                String id=task.getResult().getUser().getUid();
//                                database.getReference().child("Users").child(id).setValue(user);
//                            }

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = auth.getCurrentUser();
                            if(user.getEmail().equals("effervescence@iiita.ac.in"))
                            {
                                Intent intent=new Intent(getContext(),Admin_Page.class);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
                                startActivity(intent);
                            }
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            //updateUI(null);
                        }
                    }
                });
    }


}