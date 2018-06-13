package com.tasnem.khalel.khaleltasnem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addmessege extends AppCompatActivity implements View.OnClickListener {
    private EditText EtMessege;
    private Button BTNsend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle);
        EtMessege = (EditText) findViewById(R.id.EtMessege);
        BTNsend = (Button) findViewById(R.id.BTNsend);
        BTNsend.setOnClickListener(this);
    }


    public void dataHandler() {
        String messege = EtMessege.getText().toString();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        Bottle p = new Bottle();
        p.setMessege(messege);
        p.setEmail(user.getEmail().replace('.','*'));
        DatabaseReference reference;

        reference= FirebaseDatabase.getInstance().getReference();

        String key=reference.child("Bottles").push().getKey();
        p.setKeyId(key);

        reference.child("Bottles").child(key).setValue(p).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(addmessege.this, "Bottle added successfully", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(addmessege.this,ocean.class);
                    startActivity(i);
                }

                else
                {
                    Toast.makeText(addmessege.this, "Bottle add failed", Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();

                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (BTNsend==v){
            dataHandler();
        }
    }
}

