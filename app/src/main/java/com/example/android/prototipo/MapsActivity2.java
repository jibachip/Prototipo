package com.example.android.prototipo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static String estac = "No ha seleccionado un estacionamiento";
    static String direc = "No ha seleccionado un estacionamiento";
    static String horaAC = "No ha seleccionado un estacionamiento";
    static String precio = "No ha seleccionado un estacionamiento";
    static String disp = "No ha seleccionado un estacionamiento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        int i = 0;
        while(i<Saludo.cont2){
            LatLng loc = new LatLng(Float.parseFloat(Saludo.loca[i][2]),Float.parseFloat(Saludo.loca[i][3]));
            String est = Saludo.loca[i][1],salida = "";
            boolean a = false;
            int s = 0;
            while(!a){
                if(est.equals(Saludo.esta[s][0])){
                    a = true;
                    break;
                }
                s++;
            }
            salida = Saludo.esta[s][1];
            mMap.addMarker(new MarkerOptions().position(loc).title(salida).icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador)));
            i++;
        }
        if(Saludo.desco){
            LatLng actual = new LatLng(Saludo.latA,Saludo.lonA);
            mMap.addMarker(new MarkerOptions().position(actual).title("Estás aquí"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(actual,15));
        } else {
            LatLng dgo = new LatLng(24.025635,-104.671062);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(dgo,13));
        }

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                estac = marker.getTitle();
                if(!estac.equals("Estás aquí")){
                    boolean a = false;
                    int v = 0;
                    while(!a){
                        if(Saludo.esta[v][1].equals(estac)){
                            a = true;
                            break;
                        }
                        v++;
                    }
                    direc = Saludo.esta[v][2]+" #"+Saludo.esta[v][3]+" colonia "+Saludo.esta[v][4];
                    horaAC = Saludo.esta[v][5]+ " - " + Saludo.esta[v][6];
                    precio = Saludo.esta[v][7];
                    disp = Saludo.esta[v][8];
                    /*Intent intent = new Intent(MapsActivity.this,Datos_Esta.class);
                    startActivity(intent);*/
                }
                return false;
            }
        });
    }
}
