package com.ringthedoctor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rezwan on 8/28/2017.
 */

public class Adapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;
    String[] nameofdiets;
    String[] descofdiets;
    int[] imagesofdiets;
    String diettime;

    public Adapter(Context context, String[] nameofdiets, String[] descofdiets, int[] imagesofdiets, String diettime) {
        this.mContext = context;
        this.nameofdiets = nameofdiets;
        this.descofdiets = descofdiets;
        this.imagesofdiets = imagesofdiets;
        this.diettime = diettime;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return nameofdiets.length;
    }

    @Override
    public Object getItem(int position) {
        return nameofdiets[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_title);

// Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_subtitle);
        //subtitleTextView.setLines(10);
// Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_detail);

// Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);

        // 1
//        final Recipe recipe = (Recipe) getItem(position);

// 2
        titleTextView.setText(nameofdiets[position]);
        subtitleTextView.setText(descofdiets[position]);
        detailTextView.setText(descofdiets[position]);

// 3
        Picasso.with(mContext).load(imagesofdiets[position]).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Details_of_Items.class);
                i.putExtra("pos",position);
                i.putExtra("time",diettime);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });

        return rowView;
    }

    public String getValues()
    {
        String val=null;
        View rowView = mInflater.inflate(R.layout.list_item_recipe, null, false);
        // Get title element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_title);

// Get subtitle element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_subtitle);
        //subtitleTextView.setLines(10);
// Get detail element
        TextView detailTextView =
                (TextView) rowView.findViewById(R.id.recipe_list_detail);

// Get thumbnail element
        ImageView thumbnailImageView =
                (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);

        return titleTextView.toString();
    }
}
