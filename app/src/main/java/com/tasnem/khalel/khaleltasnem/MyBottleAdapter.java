package com.tasnem.khalel.khaleltasnem;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;



public class MyBottleAdapter extends ArrayAdapter<Bottle> {
    public MyBottleAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.activity_ocean2,parent,false);
        TextView tvMes= (TextView) view.findViewById(R.id.EtMessege);
        Bottle p=getItem(position);
        tvMes.setText(p.getMessege());
        return view;


    }
}
