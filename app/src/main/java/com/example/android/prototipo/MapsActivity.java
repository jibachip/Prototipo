package com.example.android.prototipo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    SharedPreferences prec;
    private GoogleMap mMap;
    LatLng ubi;
    double lat = 0,lon = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        prec=getSharedPreferences("Datos cuenta",MODE_PRIVATE);
        String s =prec.getString("Usuario","User");
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MapsActivity.this,"Locación guardada",Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng dgo = new LatLng(24.025635,-104.671062);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dgo,13));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();
                ubi = latLng;
                lat = ubi.latitude;
                lon = ubi.longitude;
                mMap.addMarker(new MarkerOptions().position(ubi).title("Ubicacion"));
            }
        });

    }
}
