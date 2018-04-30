package com.ringthedoctor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BrainPlow on 8/25/2017.
 */

public class BloodAdaptor extends ArrayAdapter<Blood> {

    private Activity context;
    private List<Blood> resaurantsList;
    public static final String TotalAmount = "totalamount";

    public BloodAdaptor(Activity context, List<Blood> resaurantsList)
    {
        super(context, R.layout.listview_rest, resaurantsList);

        this.context = context;
        this.resaurantsList = resaurantsList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listviewitem = inflater.inflate(R.layout.blooditem, null, true);

        TextView name = (TextView)listviewitem.findViewById(R.id.pharname);

        final Blood resaurants = resaurantsList.get(position);
        name.setText(resaurants.getBlooddesc());


        return listviewitem;


    }




}

