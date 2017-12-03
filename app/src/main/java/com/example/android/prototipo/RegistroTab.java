package com.example.android.prototipo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;

public class RegistroTab extends AppCompatActivity {
    int t1;

        TabHost tabHost;
        EditText correo,usuario,contraseña,confcontraseña,nombre,apPat,apMat,fechaNac,placas;
        Spinner genero;
        RadioButton cliente,dueño;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_tab);

       correo=findViewById(R.id.etCorreo);
       usuario=findViewById(R.id.etUsuario);
       contraseña=findViewById(R.id.etContrasena);
       confcontraseña=findViewById(R.id.etConfContra);
       nombre=findViewById(R.id.etNombre);
       apPat=findViewById(R.id.etApPat);
       apMat=findViewById(R.id.etApMat);
       fechaNac=findViewById(R.id.etFechaN);
       genero=findViewById(R.id.spGenero);
       cliente=findViewById(R.id.rbCliente);
       dueño=findViewById(R.id.rbDueño);
        placas=findViewById(R.id.etMatricula);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(getApplicationContext(),R.array.Genero,R.layout.support_simple_spinner_dropdown_item);
        genero.setAdapter(adapter);

    final Button btntab2=findViewById(R.id.p1p2);
    final Button btntab3=findViewById(R.id.p2p3);
    final Button btnRegisCliente=findViewById(R.id.btnRegCliente);

        //Creacion tabhost
        tabHost=findViewById(R.id.tabhost);
        tabHost.setup();
        //Agrega la primera pestaña al TabHost
        TabHost.TabSpec spec=tabHost.newTabSpec("Tab One");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Usuario");
        tabHost.addTab(spec);

        //Agrega segunda pestaña
        spec=tabHost.newTabSpec("Tab Two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Información Personal");
        tabHost.addTab(spec);

        //Agrega tercera pestaña.
        spec=tabHost.newTabSpec("Tab Three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Cliente");
        tabHost.addTab(spec);
//Agrega cuarta pestaña
        spec=tabHost.newTabSpec("Tab Four");
        spec.setContent(R.id.tab4);
        spec.setIndicator("Estacionamiento");
        tabHost.addTab(spec);

        tabHost.getTabWidget().getChildTabViewAt(0).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(1).setVisibility(View.INVISIBLE);
        tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.INVISIBLE);
        tabHost.getTabWidget().getChildTabViewAt(1).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(1).getLayoutParams().width=60;
        tabHost.getTabWidget().getChildTabViewAt(2).setEnabled(false);
        tabHost.getTabWidget().getChildTabViewAt(3).setVisibility(View.INVISIBLE);
        tabHost.getTabWidget().getChildTabViewAt(3).setEnabled(false);


        
        
        btntab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (correo.getText().length()!=0 && usuario.getText().length()!=0 && contraseña.getText().length()!=0 &&
                    confcontraseña.getText().length()!=0){
                tabHost.getTabWidget().getChildTabViewAt(1).setVisibility(View.VISIBLE);
                tabHost.setCurrentTabByTag("Tab Two");


            }
            else{
                genToast("Faltan datos");
            }
            }
        });


        btntab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().length()!=0 && apPat.getText().length()!=0 && apMat.getText().length()!=0
                        && fechaNac.getText().length()!=0 && genero.getSelectedItem()!=null ){

                    if (cliente.isChecked()){
                    tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.VISIBLE);
                    tabHost.setCurrentTabByTag("Tab Three");}
                    else if (dueño.isChecked()){
                        tabHost.getTabWidget().getChildTabViewAt(2).setVisibility(View.GONE);
                        tabHost.getTabWidget().getChildTabViewAt(3).setVisibility(View.VISIBLE);
                        tabHost.setCurrentTabByTag("Tab Four");
                    }
                }
                else{
                    genToast("Faltan datos");
                }
            }
        });

        btnRegisCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (placas.getText().length()!=0) {
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                }
                else{
                    genToast("Faltan datos");
                }
            }
        });

    }


public void genToast(String mensaje){
        Toast.makeText(getApplicationContext(),mensaje,Toast.LENGTH_SHORT).show();
}



    @Override
    public void onBackPressed() {
    genToast(Integer.toString(tabHost.getCurrentTab()));
        int tab=tabHost.getCurrentTab();
        switch (tab){
            case 0:super.onBackPressed();break;
            case 1: tabHost.setCurrentTabByTag("Tab One");break;
            case 2:tabHost.setCurrentTabByTag("Tab Two");break;
            case 3:tabHost.setCurrentTabByTag("Tab Three");
        }
    }
}
