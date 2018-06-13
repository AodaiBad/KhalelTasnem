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

public class SignUpAct extends AppCompatActivity implements View.OnClickListener {

    private static final String PREF_FILE_NAME="KhaTasnem";
    private Button Save;
    private EditText etYourEmail;
    private EditText etNewpassword;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etYourEmail = (EditText) findViewById(R.id.etYourEmail);
             Save = (Button) findViewById(R.id.Save);
        Save.setOnClickListener(this);
            etNewpassword = (EditText) findViewById(R.id.etNewpassword);
            auth = FirebaseAuth.getInstance();
            firebaseUser = auth.getCurrentUser();



    }
        public void dataHandler(){
    String Email=etYourEmail.getText().toString();
    String passw=etNewpassword.getText().toString();
    boolean isOk=true;
            createAccount(Email,passw);

        }

    @Override
    public void onClick(View v) {
        dataHandler();
        Intent i= new Intent(this,LogInAct.class);
        startActivity(i);
        finish();

    }
    public void createAccount(String Email,String passw){
        auth.createUserWithEmailAndPassword(Email,passw).addOnCompleteListener(SignUpAct.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpAct.this, "Done Succsessfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignUpAct.this,"Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }

            }
        });
}
}