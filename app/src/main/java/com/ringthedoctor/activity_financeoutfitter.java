package com.ringthedoctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class activity_financeoutfitter extends AppCompatActivity {



    DatabaseReference artistreference;
    FirebaseAuth auth;
    ListView listViewartists;
    List<DoctorDetails> resaurantsList;

    TextView warning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_list);


        warning = (TextView)findViewById(R.id.error);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


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

        getSupportActionBar().setTitle(email);

        artistreference = FirebaseDatabase.getInstance().getReference("categories").child(newString);
        listViewartists = (ListView) findViewById(R.id.listviewitems);

        resaurantsList = new ArrayList<>();

        artistreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                resaurantsList.clear();

                resaurantsList.size();
//
                for (DataSnapshot artistsnapshot : dataSnapshot.getChildren()) {

                    DoctorDetails resaurants = artistsnapshot.getValue(DoctorDetails.class);
                    resaurantsList.add(resaurants);

                    RestaurantsList adaptor = new RestaurantsList(activity_financeoutfitter.this, resaurantsList);
                    listViewartists.setAdapter(adaptor);
                }





                listViewartists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, android.view.View view, int i, long l) {

                        Intent intent = new Intent(activity_financeoutfitter.this, DoctorDetails_Activity.class);
                        intent.putExtra("Category", newString);
                        startActivity(intent);


                    }

                });

                if (resaurantsList.isEmpty())
                {

                    warning.setText("No Record found.");
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
