package com.example.effe_21ca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
        loginUsernameId = findViewById(R.id.Username);
        loginUsernameId.setHighlightColor(getResources().getColor(R.color.colorPrimary));
        logInpasswd = findViewById(R.id.Password);
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

//        button2.setOnClickListener(view -> {
//            String userEmail = loginUsernameId.getText().toString();
//            String userPaswd = logInpasswd.getText().toString();
//            if (userEmail.isEmpty()) {
//                loginUsernameId.setError("Provide your Username first!");
//                loginUsernameId.requestFocus();
//            }
//            else if (userPaswd.isEmpty()) {
//                logInpasswd.setError("Enter Password!");
//                logInpasswd.requestFocus();
//            }
//            else {
//                firebaseAuth.signInWithEmailAndPassword(userEmail, userPaswd).addOnCompleteListener(login.this, (OnCompleteListener) task -> {
//                    if (!task.isSuccessful()) {
//                        Toast.makeText(login.this, "Not successfull", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        startActivity(new Intent(login.this, Bottom_Navigation_Activity.class));
//                    }
//                });
//            }
//        });


        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, Bottom_Navigation_Activity.class));
            }
        });
    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
}