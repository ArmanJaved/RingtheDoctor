package com.ringthedoctor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

    DatabaseReference slotreference;
    FirebaseAuth auth;

    TextView name , docspe, docadd, hospnam, contad;

    CircleImageView prof;

    String newString;
    String newid;

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
                    newid =artistsnapshot.getKey();
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
    protected void onRestart() {
        super.onRestart();

        String nam = name.getText().toString().trim();
//        Toast.makeText(getApplicationContext(), "Restart", Toast.LENGTH_LONG).show();

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Book an appointment");
        alertDialogBuilder.setMessage("Have you booked your appointment with "+nam + " ?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                String asd = newString;
                                slotreference = FirebaseDatabase.getInstance().getReference("categories").child(newString).child(newid).child("Slot");

                                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(DoctorDetails_Activity.this, android.R.layout.select_dialog_singlechoice);

                                // Attach a listener to read the data at our posts reference
                                slotreference.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        for (DataSnapshot artistsnapshot : dataSnapshot.getChildren()) {

                                            DoctorSlot doctorSlot = artistsnapshot.getValue(DoctorSlot.class);
                                            String slottime = doctorSlot.getSlottiming();

                                            if (!doctorSlot.getSlotflag().equals("B"))
                                            {
                                                arrayAdapter.add(slottime);
                                            }

                                        }


                                        AlertDialog.Builder builderSingle = new AlertDialog.Builder(DoctorDetails_Activity.this);
                                        builderSingle.setIcon(R.mipmap.ic_launcher);
                                        builderSingle.setTitle("Select Slot ");


                                        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        });

                                        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                final String strName = arrayAdapter.getItem(which);
                                                AlertDialog.Builder builderInner = new AlertDialog.Builder(DoctorDetails_Activity.this);
                                                builderInner.setMessage(strName);
                                                builderInner.setTitle("Your Selected slot time is");
                                                builderInner.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog,int which) {
                                                        dialog.dismiss();

                                                        // Attach a listener to read the data at our posts reference
                                                        slotreference.addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {

                                                                for (DataSnapshot artistsnapshot : dataSnapshot.getChildren()) {

                                                                    DoctorSlot doctorSlot = artistsnapshot.getValue(DoctorSlot.class);
                                                                    if (strName.contains(doctorSlot.getSlottiming()))
                                                                    {
                                                                        String assd=artistsnapshot.getKey();

                                                                        setslotdata(strName, assd);

                                                                    }
                                                                }

                                                            }


                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {
                                                                System.out.println("The read failed: " + databaseError.getCode());
                                                            }
                                                        });
                                                    }
                                                });
                                                builderInner.show();
                                            }
                                        });
                                        builderSingle.show();
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        System.out.println("The read failed: " + databaseError.getCode());
                                    }
                                });


                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


    public void setslotdata (final String slot, final String idofchangeflag)
    {
        final FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("users/"+user.getUid());

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String result;

                DataSnapshot propertiesRef;

                if (dataSnapshot.child("Slot").exists()) {
                    propertiesRef = dataSnapshot.child("Slot");
                    result = propertiesRef.getValue(String.class);

                    if (!result.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Your selected slot is "+result,Toast.LENGTH_LONG).show();

                    }
                }
                else if (!dataSnapshot.child("Slot").exists()) {
                    final DatabaseReference myRef = database.getReference("users/"+user.getUid());
                    myRef.child("Slot").setValue(slot);

                    slotreference.child(idofchangeflag).
                            child("slotflag").setValue("B");
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
