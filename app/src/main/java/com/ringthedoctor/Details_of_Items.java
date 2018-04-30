package com.ringthedoctor;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details_of_Items extends AppCompatActivity {

    ImageView dietimg;
    int imgurl;
    String title, method;
    String desc;
    TextView titletext, detailtext, tv_method;
    RecyclerView lv_ingredients;
    int pos;
    String diettime;
    HomeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of__items);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dietimg = (ImageView) findViewById(R.id.imageView);

        pos = getIntent().getIntExtra("pos", 0);
        diettime = getIntent().getStringExtra("time");


        titletext = (TextView) findViewById(R.id.title_txt);
        detailtext = (TextView) findViewById(R.id.detail_txt);
        tv_method = (TextView) findViewById(R.id.tv_method);
        lv_ingredients = (RecyclerView) findViewById(R.id.lv_ingredients);


        if (diettime.equalsIgnoreCase("b")) {
            imgurl = Class_Diet_Constants.b_imagesofdiets[pos];
            title = Class_Diet_Constants.b_namesofdiets[pos];
            desc = Class_Diet_Constants.b_descofdiets[pos];
            method = Class_Diet_Constants.b_methods[pos];
            adapter = new HomeAdapter(Class_Diet_Constants.b_ingredients[pos], Details_of_Items.this);
        } else if (diettime.equalsIgnoreCase("l")) {
            imgurl = Class_Diet_Constants.l_imagesofdiets[pos];
            title = Class_Diet_Constants.l_namesofdiets[pos];
            desc = Class_Diet_Constants.l_descofdiets[pos];
            method = Class_Diet_Constants.l_methods[pos];
            adapter = new HomeAdapter(Class_Diet_Constants.l_ingredients[pos], Details_of_Items.this);
        } else {
            imgurl = Class_Diet_Constants.d_imagesofdiets[pos];
            title = Class_Diet_Constants.d_namesofdiets[pos];
            desc = Class_Diet_Constants.d_descofdiets[pos];
            method = Class_Diet_Constants.d_methods[pos];
            adapter = new HomeAdapter(Class_Diet_Constants.d_ingredients[pos], Details_of_Items.this);
        }


        titletext.setText(title);
        detailtext.setText(desc);
        tv_method.setText(method);

        Picasso.with(Details_of_Items.this).load(imgurl).placeholder(R.mipmap.ic_launcher).into(dietimg);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(Details_of_Items.this, LinearLayoutManager.VERTICAL, false);
        lv_ingredients.setLayoutManager(layoutManager3);
        lv_ingredients.setAdapter(adapter);

    }

    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        String[] ingredients;
        Context mContext;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView num, tv_ing;


            public MyViewHolder(View view) {
                super(view);
                num = (TextView) view.findViewById(R.id.number);
                tv_ing = (TextView) view.findViewById(R.id.tv_ing);
            }
        }

        public HomeAdapter(String[] ingredients, Context mContext) {
            this.ingredients = ingredients;
            this.mContext = mContext;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_diet_ingredients, parent, false);

            return new MyViewHolder(itemView);
        }


        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.num.setText(position + 1 + ". ");
            holder.tv_ing.setText(ingredients[position]);

        }

        @Override
        public int getItemCount() {
            return ingredients.length;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // finish the activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
