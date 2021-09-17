package com.example.effe_21ca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class login extends AppCompatActivity {
    public EditText loginUsernameId, logInpasswd;

    Button button2;

    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final int RC_SIGN_IN = 234;
    private static final String TAG = "example";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        loginUsernameId = findViewById(R.id.Email);
        loginUsernameId.setHighlightColor(getResources().getColor(R.color.colorPrimary));
        logInpasswd = findViewById(R.id.Password);
        button2 = findViewById(R.id.button2);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.sign_in_button).setOnClickListener(view -> {
            signIn();
        });


        authStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Toast.makeText(login.this, "User logged in ", Toast.LENGTH_SHORT).show();
                Intent I = new Intent(login.this, Bottom_Navigation_Activity.class);
                startActivity(I);
            }


        };

        button2.setOnClickListener(view -> {
            String userEmail = loginUsernameId.getText().toString();
            String userPaswd = logInpasswd.getText().toString();
            if (userEmail.isEmpty()) {
                loginUsernameId.setError("Provide your Email first!");
                loginUsernameId.requestFocus();
            } else if (userPaswd.isEmpty()) {
                logInpasswd.setError("Enter Password!");
                logInpasswd.requestFocus();
            } else {
                firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(login.this, (OnCompleteListener) task -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(login.this, "Not successfull", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(login.this, Bottom_Navigation_Activity.class));
                    }
                });
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, Bottom_Navigation_Activity.class));
        }
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {


            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {

                GoogleSignInAccount account = task.getResult(ApiException.class);


                assert account != null;
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Toast.makeText(login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());


        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);


        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");
                        FirebaseUser user = firebaseAuth.getCurrentUser();

                        Toast.makeText(login.this, "User Signed In", Toast.LENGTH_SHORT).show();
                    } else {

                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(login.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }

                });
    }



//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(login.this, Bottom_Navigation_Activity.class));
//            }
//        });





    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}