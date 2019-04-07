package com.example.sharked.accenture.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sharked.accenture.R;
import com.example.sharked.accenture.models.Repository;

import java.util.ArrayList;

public class RepositoryAdapter extends ArrayAdapter<Repository> {
    public RepositoryAdapter(@NonNull Context context, @NonNull Repository[] objects) {
        super(context, R.layout.li_repository, objects);

    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = (convertView != null)? convertView :inflater.inflate(R.layout.li_repository, parent, false);

        final Repository item = getItem(position);

        ((TextView) view.findViewById(R.id.delivery_location)).setText(String.format("Entrega en %s","abc"));
        ((TextView) view.findViewById(R.id.wrapper_title)).setText(item.getFullName());
        ((TextView) view.findViewById(R.id.wrapper_name_date)).setText(String.format("%s - %s",item.getNodeId(), item.getName()));


        return view;
    }
}
