package com.ringthedoctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by BrainPlow on 3/8/2018.
 */

class CategoriesListAdaptor extends BaseAdapter {

    Context context;
    String[] data;
    int[] images;
    private static LayoutInflater inflater = null;

    public CategoriesListAdaptor(Context context, String[] data, int[] images) {
        // TODO Auto-generated constructor stub
        this.context = context;
        this.data = data;
        this.images = images;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.row, null);
        TextView text = (TextView) vi.findViewById(R.id.text);
        ImageView catimage = (ImageView)vi.findViewById(R.id.catimage);

        catimage.setImageResource(images[position]);

        text.setText(data[position]);
        return vi;
    }
}