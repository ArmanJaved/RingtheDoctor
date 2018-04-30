package com.ringthedoctor;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by BrainPlow on 8/25/2017.
 */

public class RestaurantsList extends ArrayAdapter<DoctorDetails> {

    private Activity context;
    private List<DoctorDetails> resaurantsList;
    public static final String TotalAmount = "totalamount";

    public RestaurantsList(Activity context, List<DoctorDetails> resaurantsList)
    {
        super(context, R.layout.listview_rest, resaurantsList);

        this.context = context;
        this.resaurantsList = resaurantsList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listviewitem = inflater.inflate(R.layout.listview_rest, null, true);

        TextView name = (TextView)listviewitem.findViewById(R.id.Textartistname);
        CircleImageView imag = (CircleImageView) listviewitem.findViewById(R.id.circleImageView);

        TextView hospit = (TextView)listviewitem.findViewById(R.id.hospital);



        DoctorDetails resaurants = resaurantsList.get(position);
        name.setText(resaurants.getDoctorname());
        hospit.setText(resaurants.getDochosp());
        Picasso.with(context)
                .load(resaurants.getDocpic())
                .placeholder(R.drawable.man)
                .error(R.drawable.man)
                .into(imag);
        return listviewitem;


    }




}

