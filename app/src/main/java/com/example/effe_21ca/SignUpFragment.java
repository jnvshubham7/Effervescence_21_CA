package com.example.effe_21ca;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

import java.util.Objects;


public class SignUpFragment extends Fragment {


    FragmentSignUpBinding binding;
    FirebaseAuth Auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    public EditText EmailAddress, TxPassword,  PersonName;


    GoogleSignInClient mGoogleSignInClient;


    public SignUpFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentSignUpBinding.inflate(inflater, container, false);

        EmailAddress = getActivity().findViewById(R.id.EmailAddress);
        TxPassword = getActivity().findViewById(R.id.TxPassword);
        PersonName = getActivity().findViewById(R.id.PersonName);
        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("358743908709-bm0lkn9hjv1ueogqk2ggpjlj5ribgua9.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


        binding.btnSignUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailID = binding.EmailAddress.getText().toString();
                String paswd = binding.TxPassword.getText().toString();
                String personName = binding.PersonName.getText().toString();

                if (emailID.isEmpty()) {
                    binding.EmailAddress.setError("Provide your Email first!");
                    binding.EmailAddress.requestFocus();
                }
              else  if (paswd.isEmpty()) {
                    binding.TxPassword.setError("Set your password");
                    binding.TxPassword.requestFocus();
                }
              else  if (personName.isEmpty()) {
                    binding.PersonName.setError("Provide your UserName");
                    binding.PersonName.requestFocus();

                }


else if (!(emailID.isEmpty() && paswd.isEmpty() && personName.isEmpty())) {
                    progressDialog.show();
                    Auth.createUserWithEmailAndPassword(binding.EmailAddress.getText().toString(), binding.TxPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {

                                Users user = new Users(binding.PersonName.getText().toString(), binding.EmailAddress.getText().toString(), binding.TxPassword.getText().toString());

                                String id = task.getResult().getUser().getUid();
                                database.getReference().child("Users").child(id).setValue(user);
                                Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
                                startActivity(intent);
                                Toast.makeText(getContext(), "SignUp Succesfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        binding.GoogleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
        if (Auth.getCurrentUser() != null) {
            Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
            startActivity(intent);
        }

        return binding.getRoot();
    }


    int RC_SIGN_IN = 65;

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {

                Log.w("TAG", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        Auth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                          //  FirebaseUser user = Auth.getCurrentUser();


                      //      FirebaseUserMetadata metadata = Auth.getCurrentUser().getMetadata();
//                            if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
//
//                                Log.d("TAG", "signInWithCredential:success");
//
//                                Toast.makeText(getActivity(), "plz sign up", Toast.LENGTH_LONG);
//                                // The user is new, show them a fancy intro screen!
//                            } else {
//                                Log.d("TAG", "fail");
//
//
//                                Toast.makeText(getActivity(), "Sign IN", Toast.LENGTH_LONG);
//                                Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
//                              startActivity(intent);
                            // This is an existing user, show them a welcome back screen.
                            //  }





                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
                            if (acct != null) {





                                String GoogleName = acct.getDisplayName();

                                String personEmail = acct.getEmail();

                                Users user = new Users(GoogleName, personEmail);
                                String id = task.getResult().getUser().getUid();
                             //   int score =user.getScore();
                              //  Log.d("score", String.valueOf(score));

                                database.getReference().child("Users").child(id).setValue(user);
                           //     database.getReference().child("Users").child(id).child("score").setValue(score);


                                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref1",MODE_PRIVATE);
                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
                                myEdit.putString("name", acct.getEmail());
                                myEdit.apply();

                            }



                            Log.d("TAG", "signInWithCredential:success");

                            Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
                            startActivity(intent);

                        } else {

                            Log.w("TAG", "signInWithCredential:failure", task.getException());

                        }
                    }
                });
    }


}