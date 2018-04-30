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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BrainPlow on 8/25/2017.
 */

public class PharmacyAdaptor extends ArrayAdapter<Pharmacy> {

    private Activity context;
    private List<Pharmacy> resaurantsList;
    public static final String TotalAmount = "totalamount";

    public PharmacyAdaptor(Activity context, List<Pharmacy> resaurantsList)
    {
        super(context, R.layout.listview_rest, resaurantsList);

        this.context = context;
        this.resaurantsList = resaurantsList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listviewitem = inflater.inflate(R.layout.pharmacyitem, null, true);

        TextView name = (TextView)listviewitem.findViewById(R.id.pharname);
        Button imag = (Button) listviewitem.findViewById(R.id.btncal);

        TextView hospit = (TextView)listviewitem.findViewById(R.id.pharadd);



        final Pharmacy resaurants = resaurantsList.get(position);
        name.setText(resaurants.getPharname());
        hospit.setText(resaurants.getPharadd());


        imag.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + resaurants.getPharcont()));
                    context.startActivity(intent);
                } catch (Exception e) {
                    //TODO smth
                }


            }
        });
//        Picasso.with(context)
//                .load(resaurants.getDocpic())
//                .placeholder(R.drawable.man)
//                .error(R.drawable.man)
//                .into(imag);
        return listviewitem;


    }




}

