package com.ringthedoctor;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapsActivity extends FragmentActivity  implements OnMapReadyCallback {

    private GoogleMap mMap;

    String newString;
    LatLng latLngmain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("latlng");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("latlng");
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        String dat = null;

        try {


        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(newString);
        while(m.find()) {
            dat=m.group(1);
        }

       String [] split = dat.split(",");
        double lat = Double.parseDouble(split[0]);
        double lon = Double.parseDouble(split[1]);
        mMap = googleMap;

        LatLng sydney = new LatLng(lat, lon);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney,14));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lon), 12.0f));
//        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
        mMap.addMarker(new MarkerOptions().position(sydney).title("Hospital"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        }catch (Exception e){}

    }

}