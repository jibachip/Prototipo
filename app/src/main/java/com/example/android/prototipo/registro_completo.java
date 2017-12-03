package com.example.android.prototipo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TabHost;

import com.example.android.prototipo.AccesoDatosWIP.EsquemasBaseDeDatos;

public class registro_completo extends AppCompatActivity {

    Button btnContinuar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_completo);

        TabHost tabRegistro=findViewById(R.id.tabRegistro);

        tabRegistro.setup();
        TabHost.TabSpec tsTab1=tabRegistro.newTabSpec("tab1");
        tsTab1.setContent(R.id.tab1);
        tsTab1.setIndicator("Registro 1");
        tabRegistro.addTab(tsTab1);

        TabHost.TabSpec tsTab2=tabRegistro.newTabSpec("tab2");
        tsTab1.setContent(R.id.tab2);
        tsTab1.setIndicator("Registro 2");
        tabRegistro.addTab(tsTab1);

        TabHost.TabSpec tsTab3=tabRegistro.newTabSpec("tab3");
        tsTab1.setContent(R.id.tab3);
        tsTab1.setIndicator("Registro 3");
        tabRegistro.addTab(tsTab1);

        //btnContinuar=findViewById(R.id.)

    }
}
