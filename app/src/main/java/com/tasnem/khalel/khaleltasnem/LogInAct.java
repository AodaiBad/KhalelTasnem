package com.tasnem.khalel.khaleltasnem;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInAct extends AppCompatActivity implements View.OnClickListener {
    private static final String PREF_FILE_NAME = "Khatas";
    private EditText ETpassword;
    private Button btnLogin;
    private Button btnSignin;
    private EditText ETusername;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        ETusername=(EditText) findViewById(R.id.ETusername);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        ETpassword = (EditText) findViewById(R.id.ETpassword);
        btnLogin.setOnClickListener(this);
        btnSignin.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();


    }

    private void dataHandler() {
        String email = ETusername.getText().toString();
        String passw = ETpassword.getText().toString();

        signIn(email, passw);

    }

    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(LogInAct.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LogInAct.this, "signIn Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getBaseContext(), ocean.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LogInAct.this, "signIn failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view == btnLogin) {
            dataHandler();
        }


        if (view == btnSignin) {
            Intent intent = new Intent(this, SignUpAct.class);
            startActivity(intent);
        }

    }
}
