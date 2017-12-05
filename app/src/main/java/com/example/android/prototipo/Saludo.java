package com.example.android.prototipo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class Saludo extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    static final int PETICION_PERMISO_LOCALIZACION = 101;
    GoogleApiClient apiClient;
    Button mapa;
    static double latA = 0, lonA = 0;
    static String loca[][] = new String[20][4];
    static String esta[][] = new String[20][9];
    static int cont1 = 0, cont2 = 0;
    static boolean desco = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);
        mapa = findViewById(R.id.btnEst);
        loca[0][1] = "1001";
        loca[1][1] = "1002";
        loca[2][1] = "1003";
        loca[0][2] = "24.030675";
        loca[1][2] = "24.025635";
        loca[2][2] = "24.025449";
        loca[0][3] = "-104.671232";
        loca[1][3] = "-104.671062";
        loca[2][3] = "-104.668860";
        esta[0][0] = "1001";
        esta[1][0] = "1002";
        esta[2][0] = "1003";
        esta[0][1] = "Estacionamiento Hidalgo";
        esta[1][1] = "Estacionamiento Catedral";
        esta[2][1] = "Estacionamiento Madero";
        esta[0][2] = "Calle Constitución";
        esta[1][2] = "Calle Negrete";
        esta[2][2] = "Calle Franco I. Madero";
        esta[0][3] = "311";
        esta[1][3] = "450";
        esta[2][3] = "206";
        esta[0][4] = "Zona Centro";
        esta[1][4] = "Zona Centro";
        esta[2][4] = "Zona Centro";
        esta[0][5] = "9:00 a.m.";
        esta[1][5] = "9:00 a.m.";
        esta[2][5] = "10:00 a.m.";
        esta[0][6] = "8:00 p.m.";
        esta[1][6] = "7:00 p.m.";
        esta[2][6] = "10:00 p.m.";
        esta[0][7] = "$20 por hora";
        esta[1][7] = "$34 por hora";
        esta[2][7] = "$25 por hora";
        esta[0][8] = "3";
        esta[1][8] = "0";
        esta[2][8] = "9";
        cont2 = 3;


        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("MissingPermission") Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
                desco = actualizarLocation(location);
                Intent intent = new Intent(Saludo.this, MapsActivity2.class);
                startActivity(intent);


            }
        });


        apiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,  this)
                .addConnectionCallbacks( this)
                .addApi(LocationServices.API)
                .build();
    }

    private boolean actualizarLocation(Location location) {
        boolean g = false;
        if (location!=null){
            latA = location.getLatitude();
            lonA = location.getLongitude();
            g = true;
        } else {
            Toast.makeText(this, "Localización desconocida", Toast.LENGTH_LONG).show();
        }
        return g;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PETICION_PERMISO_LOCALIZACION);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PETICION_PERMISO_LOCALIZACION){
            if (grantResults.length == 1 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                @SuppressWarnings("MissingPermission")
                Location location = LocationServices.FusedLocationApi.getLastLocation(apiClient);
            } else {
                Toast.makeText(this, "Permiso Denegado", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "No hay conexión", Toast.LENGTH_LONG).show();
    }
}
