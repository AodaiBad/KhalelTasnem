package com.tasnem.khalel.khaleltasnem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ocean extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {

    private ImageButton giveme;
    private ListView include;

    private MyBottleAdapter bottleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocean2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ImageButton giveme=(ImageButton)findViewById(R.id.giveme);
        include = (ListView) findViewById(R.id.Bottles);
        bottleAdapter = new MyBottleAdapter(this, R.layout.item_bottle);
        include.setAdapter(bottleAdapter);
        include.setOnItemClickListener(this);
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
                        bottleAdapter.clear();
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Bottle p = ds.getValue(Bottle.class);
                            Log.d("SL", p.toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final String[] a={"DELETE"};
        final Bottle p= (Bottle) parent.getItemAtPosition(position);


        AlertDialog.Builder builder = new AlertDialog.Builder(ocean.this);
        builder.setTitle("Delete");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(a, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(ocean.this, a[i], Toast.LENGTH_SHORT).show();
                if(i==0) {
                    DatabaseReference reference;
                    //todo לקבלת קישור למסד הנתונים שלנו
                    //todo  קישור הינו לשורש של המסד הנתונים

                    reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("Bottles").child(p.getKeyId()).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(ocean.this, "delete successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ocean.this, "delete failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }



        });
        AlertDialog dialog=builder.create();
        dialog.show();


    }

    @Override
    public void onClick(View v) {
        if (v==giveme){
            readAndListen();
        }
    }
}






