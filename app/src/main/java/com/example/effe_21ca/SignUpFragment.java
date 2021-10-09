package com.example.effe_21ca;

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

import com.example.effe_21ca.databinding.ActivitySignInBinding;
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


public class SignUpFragment extends Fragment {

    GoogleSignInClient mGoogleSignInClient;
    FragmentSignUpBinding binding;
    private FirebaseAuth Auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private static final int RC_SIGN_IN = 234;
    private static final String TAG = "example";

    public SignUpFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // View view= inflater.inflate(R.layout.fragment_sign_up, container, false);

        binding= FragmentSignUpBinding.inflate(inflater,container, false);



        Auth = FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account");
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken("358743908709-bm0lkn9hjv1ueogqk2ggpjlj5ribgua9.apps.googleusercontent.com")
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//        getView().findViewById(R.id.sign_in_button).setOnClickListener(view -> signIn());
////        authStateListener = firebaseAuth -> {
//            FirebaseUser user = firebaseAuth.getCurrentUser();
//            if (user != null) {
//                Toast.makeText(SignUpFragment.this, "User logged in ", Toast.LENGTH_SHORT).show();
//                Intent I = new Intent(SignUpFragment.this, Bottom_Navigation_Activity.class);
//                startActivity(I);
//            }
//
//
//        };


        binding.btnSignUP.setOnClickListener(new View.OnClickListener() {
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
                            Intent intent =new Intent(getContext(),Bottom_Navigation_Activity.class);
                            startActivity(intent);
                            Toast.makeText(getContext(), "SignUp Succesfully", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
//        @Override
//        public void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//
//            if (requestCode == RC_SIGN_IN) {
//
//
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//                try {
//
//                    GoogleSignInAccount account = task.getResult(ApiException.class);
//
//
//                    assert account != null;
//                    getActivity().firebaseAuthWithGoogle(account);
//                } catch (ApiException e) {
//                    Toast.makeText(SignUpFragment.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        }



        return binding.getRoot();
    }

//    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
//        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
//
//
//        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);

//    private void signIn() {
//        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
//    }
}