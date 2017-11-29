package com.example.android.prototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button siguiente=(Button)findViewById(R.id.btnRegistDueño);

        //Button siguiente=(Button)findViewById(R.id.btnRegistDueño);
        //PRUEBA DE CAMBIOS
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),registro_dueno.class);
                startActivity(intent);
            }
        });
    }
}
