package com.tasnem.khalel.khaleltasnem;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MyBottleAdapter extends ArrayAdapter<Bottle> {
    public MyBottleAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_bottle,parent,false);
        TextView tvMes= (TextView) view.findViewById(R.id.ETMESSAGE);
        final ImageButton btnDel = (ImageButton) view.findViewById(R.id.btnDel);




        final Bottle p=getItem(position);
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v==btnDel)
                {
                    del(p);
                }
            }
        });
        tvMes.setText(p.getMessage());
        return view;




    }

    private void del ( final Bottle p)
    {

        final String[] a = {"DELETE"};


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setCancelable(true);
        builder.setSingleChoiceItems(a, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(final DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
                Toast.makeText(getContext(), a[i], Toast.LENGTH_SHORT).show();
                if (i == 0) {
                    DatabaseReference reference;
                    //todo לקבלת קישור למסד הנתונים שלנו
                    //todo  קישור הינו לשורש של המסד הנתונים

                    reference = FirebaseDatabase.getInstance().getReference();
                    reference.child("Bottles").child(p.getKeyId()).removeValue(new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                            if (databaseError == null) {
                                Toast.makeText(getContext(), "delete successful", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(getContext(), "delete failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }


        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
