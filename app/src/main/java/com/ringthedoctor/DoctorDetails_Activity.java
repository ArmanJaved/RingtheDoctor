package com.ringthedoctor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorDetails_Activity extends AppCompatActivity {



    DatabaseReference artistreference;
    FirebaseAuth auth;

    TextView name , docspe, docadd, hospnam, contad;

    CircleImageView prof;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        prof = (CircleImageView)findViewById(R.id.profile);
        name = (TextView)findViewById(R.id.name);
        docspe = (TextView)findViewById(R.id.docspec);
        docadd = (TextView)findViewById(R.id.docadd);
        hospnam = (TextView)findViewById(R.id.hospname);
        contad = (TextView)findViewById(R.id.contadet);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }





        getSupportActionBar().setTitle("Doctor Profile");


        final String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("Category");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("Category");
        }

        final String email = newString.replaceAll("_"  ,  " ");

        docspe.setText(email);

        artistreference = FirebaseDatabase.getInstance().getReference("categories").child(newString);

        artistreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {



                for (DataSnapshot artistsnapshot : dataSnapshot.getChildren()) {

                    final DoctorDetails resaurants = artistsnapshot.getValue(DoctorDetails.class);
                    String asd =resaurants.getDocadd();
                    name.setText(resaurants.getDoctorname());
                    docadd.setText(resaurants.getDocadd());
                    hospnam.setText(resaurants.getDochosp());
                    contad.setText(resaurants.getDoccont());
                    Picasso.with(getApplicationContext())
                            .load(resaurants.getDocpic())
                            .placeholder(R.drawable.man)
                            .error(R.drawable.man)
                            .into(prof);


                    ImageView book_app = (ImageView) findViewById(R.id.appint);
                    book_app.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View view) {


                            try {
                                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + resaurants.getDoccont()));
                                startActivity(intent);
                            } catch (Exception e) {
                                //TODO smth
                            }


                        }
                    });

                    ImageView loc_img = (ImageView)findViewById(R.id.maplocation);
                    loc_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(DoctorDetails_Activity.this, MapsActivity.class));

                            Intent intent = new Intent(DoctorDetails_Activity.this, MapsActivity.class);
                            intent.putExtra("latlng", resaurants.getLatlong());
                            startActivity(intent);
                        }
                    });
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            finish();
        }

        return super.onOptionsItemSelected(item);
    }






}
