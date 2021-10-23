package com.example.effe_21ca;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginFragment extends Fragment {


    FragmentLoginBinding binding;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseAuth Auth;

    FirebaseDatabase database;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";



    GoogleSignInClient mGoogleSignInClient;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);

        auth = FirebaseAuth.getInstance();
        Auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("login");
        progressDialog.setMessage("login to your account");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("358743908709-bm0lkn9hjv1ueogqk2ggpjlj5ribgua9.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("358743908709-bm0lkn9hjv1ueogqk2ggpjlj5ribgua9.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);


     //   updateUI(account);


        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailID = binding.EmailAddress.getText().toString();
                String paswd = binding.TxPassword.getText().toString();


                if (emailID.isEmpty()) {
                    binding.EmailAddress.setError("Provide your Email first!");
                    binding.EmailAddress.requestFocus();
                } else if (paswd.isEmpty()) {
                    binding.TxPassword.setError("Provide your password");
                    binding.TxPassword.requestFocus();
                }

                else if (!(emailID.isEmpty() && paswd.isEmpty())) {
                    progressDialog.show();
                    auth.signInWithEmailAndPassword(binding.EmailAddress.getText().toString(), binding.TxPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
//                                if (binding.EmailAddress.getText().toString().equals("effervescence@iiita.ac.in")) {
//                                    Intent intent = new Intent(getContext(), Admin_Page.class);
//                                    startActivity(intent);
//                                } else {

                                    Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
                                    startActivity(intent);
//                                }

                            } else {
                                Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

//        binding.GoogleLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                signIn();
//
//
//
//
//            }
//        });


        binding.GoogleLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
//        if (Auth.getCurrentUser() != null) {
//
////            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
////            Log.d("acccount", String.valueOf(account));
////
////            if(account!=null) {
////                Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
////                startActivity(intent);
////
////            }
////            else {
////                Toast.makeText(getContext(), "please Sign Up First", Toast.LENGTH_SHORT);
////
////            }
//
//
//            Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
//            startActivity(intent);
//
//        }
        return binding.getRoot();
    }

//    @Override
//    public void onStart() {
//        super.onStart();

//        FirebaseUser firebaseUser = auth.getCurrentUser();
//        if(firebaseUser!=null){
//
//        }
//        else {
//            startActivity(new Intent(getContext(), Bottom_Navigation_Activity.class));
//        }

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
//        Log.d("acccount", String.valueOf(account));
//
//        if(account!=null) {
//            Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
//            startActivity(intent);
//
//        }
//        else {
//            Toast.makeText(getContext(), "please Sign Up First", Toast.LENGTH_SHORT);
//
//        }
 //   }

    int RC_SIGN_IN = 65;

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

//    int RC_SIGN_IN = 65;
//
//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }



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
        Auth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //  FirebaseUser user = Auth.getCurrentUser();








                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
                            if (acct != null) {





                                


                                String GoogleName = acct.getDisplayName();

                                String personEmail = acct.getEmail();

                                Users user = new Users(GoogleName, personEmail);
                                Log.d("personEmail", personEmail);
                                Log.d("GoogleName", GoogleName);
                                String id = task.getResult().getUser().getUid();
                                //   int score =user.getScore();
                                //  Log.d("score", String.valueOf(score));

                                database.getReference().child("Users").child(id).setValue(user);
                                //     database.getReference().child("Users").child(id).child("score").setValue(score);


                                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
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




//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                // Google Sign In was successful, authenticate with Firebase
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                Log.d("TAG", "firebaseAuthWithGoogle:" + account.getId());
//                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                // Google Sign In failed, update UI appropriately
//                Log.w("TAG", "Google sign in failed", e);
//            }
//        }
//    }

//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//        auth.signInWithCredential(credential)
//                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//
//
//
//
//
//
//
//
//
//
//                       //     database.getReference().child("U")
//                            FirebaseUser user = auth.getCurrentUser();
//
//
//
//
//
////                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getUid());
////                            reference.addValueEventListener(new ValueEventListener() {
////                                @Override
////                                public void onDataChange(DataSnapshot dataSnapshot) {
////                                    if (getContext() == null){
////                                        return;
////                                    }
////                                    Users user = dataSnapshot.getValue(Users.class);
////
////                      //              assert user != null;
////                                    Log.d("emailId", user.getMail());
////
//////
//////                                    binding.nameUser.setText(user.getUserName());
//////                                    binding.score.setText(String.valueOf(user.getScore()));
////
////                                }
////
////                                @Override
////                                public void onCancelled(DatabaseError databaseError) {
////
////                                }
////                            });
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//                        //    database.getReference().child("Users").child("mail").equals("jnvshubham7@gmail.com");
//                         //   database.getReference().
////                          if(  database.getReference().child("Users").child("mail").toString().equals(user.getEmail())){
////                              Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
////                              startActivity(intent);
////                           //   Log.d("email", )
////                            }
////                          else {
////                              Toast.makeText(getContext(), "please Sign Up First", Toast.LENGTH_SHORT);
////
////
////                          }
//
////
////                            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
////                            if (acct != null) {
////
////
////
////
////                                String GoogleName = acct.getDisplayName();
////
////                                String personEmail = acct.getEmail();
////
////                                Users user = new Users(GoogleName, personEmail);
////                                String id = task.getResult().getUser().getUid();
//                                //   int score =user.getScore();
//                                //  Log.d("score", String.valueOf(score));
////
////                          //      database.getReference().child("Users").child(id).setValue(user);
////                                //     database.getReference().child("Users").child(id).child("score").setValue(score);
////
////
////                                SharedPreferences sharedPreferences = requireContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
////                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
////                                myEdit.putString("name", acct.getEmail());
////                                myEdit.apply();
////
////                            }
//
//
////
////                            Log.d("TAG", "signInWithCredential:success");
////
////                            Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
////                            startActivity(intent);
//
//
//                            Log.d("TAG", "signInWithCredential:success");
//                       //     FirebaseUser user = auth.getCurrentUser();
//
//
//
//                                SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPref",MODE_PRIVATE);
//
//
//                                SharedPreferences.Editor myEdit = sharedPreferences.edit();
//
//
//                                myEdit.putString("name", user.getEmail());
//
//
//                                myEdit.commit();
//                            Log.d("user.getEmail", user.getEmail());
//
////                            GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getActivity());
////                            Log.d("acccount", String.valueOf(account));
////
////                            if(account!=null) {
////                                Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
////                                startActivity(intent);
////
////                            }
////                            else {
////                                Toast.makeText(getContext(), "please Sign Up First", Toast.LENGTH_SHORT);
////
////                            }
//
//
//
//                            Intent intent = new Intent(getContext(), Bottom_Navigation_Activity.class);
//                            startActivity(intent);
//
//
////
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Log.w("TAG", "signInWithCredential:failure", task.getException());
//                            //updateUI(null);
//                        }
//                    }
//                });
//    }
//
//
//
//
//}