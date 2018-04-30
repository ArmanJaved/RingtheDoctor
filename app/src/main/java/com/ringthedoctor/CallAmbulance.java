package com.ringthedoctor;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CallAmbulance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_ambulance);


        getSupportActionBar().setTitle("Call Ambulance");


        TextView aman = (TextView) findViewById(R.id.aman);
        TextView Chippa = (TextView)findViewById(R.id.chippa);
        TextView Edhi = (TextView)findViewById(R.id.edhi);
        TextView kkf = (TextView)findViewById(R.id.kkf);



        aman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+92 21 1021"));
                    startActivity(intent);
                } catch (Exception e) {
                    //TODO smth
                }
            }
        });

        Chippa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "021 111 111 134"));
                    startActivity(intent);
                } catch (Exception e) {
                    //TODO smth
                }
            }
        });

        Edhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "(042) 35156363"));
                    startActivity(intent);
                } catch (Exception e) {
                    //TODO smth
                }
            }
        });
        kkf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+92-21-36333811"));
                    startActivity(intent);
                } catch (Exception e) {
                    //TODO smth
                }
            }
        });
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            startActivity(new Intent(CallAmbulance.this, MainPage.class));
            CallAmbulance.this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
