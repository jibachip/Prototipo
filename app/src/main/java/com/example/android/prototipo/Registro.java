package com.example.android.prototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button siguiente=(Button)findViewById(R.id.btnSig);
        Spinner spGenero=(Spinner)findViewById(R.id.spGenero);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Genero,R.layout.support_simple_spinner_dropdown_item);
        spGenero.setAdapter(adapter);

        //Button siguiente=(Button)findViewById(R.id.btnRegistDueño);
        //PRUEBA DE CAMBIOS
        //X2
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),InfoPersonal.class);
                startActivity(intent);
            }
        });
    }
}
