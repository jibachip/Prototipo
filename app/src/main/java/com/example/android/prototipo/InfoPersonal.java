package com.example.android.prototipo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class InfoPersonal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_personal);
        final RadioButton cliente=findViewById(R.id.rbCliente);
        final RadioButton dueño=findViewById(R.id.rbDueño);
        Button siguiente=findViewById(R.id.btnSigRegis);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dueño.isChecked()){
                    Intent intent=new Intent(getApplicationContext(),Datos_estacionamiento.class);
                    startActivity(intent);
                }
                else if (cliente.isChecked()){

                }
            }
        });
    }
}
