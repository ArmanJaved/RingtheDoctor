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

public class PharmacyActivity extends AppCompatActivity {



    DatabaseReference artistreference;
    FirebaseAuth auth;
    ListView listViewartists;
    List<Pharmacy> resaurantsList;

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

        getSupportActionBar().setTitle("Pharmacy");

        artistreference = FirebaseDatabase.getInstance().getReference("Pharmacy");
        listViewartists = (ListView) findViewById(R.id.listviewitems);

        resaurantsList = new ArrayList<>();

        artistreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                resaurantsList.clear();

                resaurantsList.size();
//
                for (DataSnapshot artistsnapshot : dataSnapshot.getChildren()) {

                    Pharmacy pharmacy = artistsnapshot.getValue(Pharmacy.class);
                    resaurantsList.add(pharmacy);

                    PharmacyAdaptor adaptor = new PharmacyAdaptor(PharmacyActivity.this, resaurantsList);
                    listViewartists.setAdapter(adaptor);
                }





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
