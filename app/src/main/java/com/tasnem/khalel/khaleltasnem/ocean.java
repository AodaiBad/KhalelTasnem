package com.tasnem.khalel.khaleltasnem;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ocean extends AppCompatActivity implements View.OnClickListener {
    private ImageButton giveme;
    private ListView include;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageButton giveme=(ImageButton)findViewById(R.id.giveme);
        giveme.setOnClickListener(this);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ocean.this, addmessege.class));
            }
        });

    }


    private void readAndListen() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        String email = user.getEmail();
        email = email.replace('.', '*');
        DatabaseReference reference;
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Bottles").
                        addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) //todo copy from the details
                    {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            addmessege p = ds.getValue(addmessege.class);
                            Log.d("SL", p.toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });


    }

    @Override
    public void onClick(View v) {
        if (v==giveme){
            readAndListen();
        }
    }
}






