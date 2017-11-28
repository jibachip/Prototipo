package com.example.android.prototipo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;

public class Datos_estacionamiento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_estacionamiento);
        Spinner hrInic=(Spinner)findViewById(R.id.spHoraInic);
        Spinner hrCierre=(Spinner)findViewById(R.id.spHoraCierre);
        ArrayAdapter <CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.HORARIO_MAÑANA,R.layout.support_simple_spinner_dropdown_item);
        hrInic.setAdapter(adapter);
        ArrayAdapter <CharSequence>adapter1=ArrayAdapter.createFromResource(this,R.array.HORARIO_TARDE,R.layout.support_simple_spinner_dropdown_item);
        hrCierre.setAdapter(adapter1);
        Button reg=findViewById(R.id.btnDireccion);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
